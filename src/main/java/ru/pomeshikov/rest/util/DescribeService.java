package ru.pomeshikov.rest.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Service
@RequestMapping("/util/describe")
public class DescribeService {
	
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	@RequestMapping(value="describe", method=RequestMethod.GET)
	public @ResponseBody String describe(){
		String result = "var Client = function() {\n";
		result += String.format("\tthis.url = '%s',\n", description.url);
		for(Service service : description.services){
			result += String.format("\tthis.%s = {\n", service.url.replaceAll("/", ""));
			for(Method method : service.methods){
				result += String.format("\t\t%s: ", method.url.replaceAll("/", ""));
				result += "function(";
				for(int paramIndex = 0; paramIndex < method.params.size(); paramIndex++){
					Param param = method.params.get(paramIndex);
					result += String.format("/*%s %s %s*/ %s" + (paramIndex != method.params.size() -1 ? ", " : ""), param.type, param.clazz, param.optional ? "optional" : "", param.name != null && !param.name.isEmpty() ? param.name : "param" + paramIndex);
				}
				result += "){\n";
				result += "\t\t\tjQuery.post({\n";
				//result += "\t\t\t\t"
				result += "\t\t},\n";
			}
			result += "\t},\n";
		}
		result += "}\n";
		return result;
	}
	
	private Description description = null;
	@PostConstruct
	void init(){
		description = new Description();
		description.url = requestMappingHandlerMapping.getApplicationContext().getApplicationName() + "/rest";
		
		Map<String, Service> servicesMap = new HashMap<String, Service>();
		
		for(Entry<RequestMappingInfo, HandlerMethod> h : requestMappingHandlerMapping.getHandlerMethods().entrySet()){
			String[] explodedUrl = h.getKey().getPatternsCondition().getPatterns().iterator().next().split("/");
			String serviceUrl = "/" + explodedUrl[1];
			if(servicesMap.get(serviceUrl) == null){
				Service service = new Service();
				service.url = serviceUrl;
				servicesMap.put(serviceUrl, service);
			}
			
			String methodUrl = "/" + explodedUrl[2];
			Method method = new Method();
			method.url = methodUrl;
			servicesMap.get(serviceUrl).methods.add(method);
			for(MethodParameter p : h.getValue().getMethodParameters()){
				if(p.getParameterAnnotations().length == 0){
					continue;
				}
				Annotation annotation = p.getParameterAnnotations()[0];
				
				Param param = new Param();
				param.clazz = p.getParameterType().getSimpleName();
				if(annotation.annotationType().equals(RequestParam.class)){
					RequestParam reqParamAnn = (RequestParam)annotation;
					param.name = reqParamAnn.value();
					param.optional = !reqParamAnn.required();
				}
				if(annotation.annotationType().equals(CookieValue.class)){
					CookieValue cookieValAnn = (CookieValue)annotation;
					param.name = cookieValAnn.value();
					param.optional = !cookieValAnn.required();
				}
				param.type = annotation.annotationType().getSimpleName();
				
				method.params.add(param);
			}
		}
		
		for(Entry<String, Service> service : servicesMap.entrySet()){
			description.services.add(service.getValue());
		}
	}
	
	
	
	private class Description {
		String url;
		List<Service> services = new ArrayList<Service>();
	}
	
	private class Service {
		String url;
		List<Method> methods = new ArrayList<Method>();
	}
	
	private class Method {
		String url;
		List<Param> params = new ArrayList<Param>();
	}
	
	private class Param {
		String name;
		String clazz;
		String type;
		boolean optional = false;
	}
}
