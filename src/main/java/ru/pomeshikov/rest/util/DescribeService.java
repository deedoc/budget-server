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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Service
@RequestMapping("/util/client")
public class DescribeService {
	
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	private static String firstSymbolToLowerCase(String s){
		return s.toLowerCase().substring(0, 1) + s.substring(1);
	}
	
	@RequestMapping(value="/jquery", method=RequestMethod.GET)
	public @ResponseBody String describe(){
		String result = "window.serv = {\n";
		result += "\t_doPost: function(url, data, success, error){\n";
		result += "\t\treturn $.ajax({type: 'POST', url: url, data: data, success: success, error: error || serv._error});\n";
		result += "\t},\n";
		
		result += "\t_doPostJson: function(url, data, success, error){\n";
		result += "\t\treturn $.ajax({type: 'POST', url: url, contentType: 'application/json', data: data, success: success, error: error || serv._error});\n";
		result += "\t},\n";
		
		result += Joiner.on(",\n").join(Iterables.transform(description.services, new Function<Service, String>() {

			@Override
			public String apply(final Service service) {
				String result = String.format("\t%s:{\n", service.url.replace("/", ""));
				result += Joiner.on(",\n").join(Iterables.transform(service.methods, new Function<Method, String>() {

					@Override
					public String apply(Method method) {
						List<Param> cookies = Lists.newArrayList(Iterables.filter(method.params, new Predicate<Param>() {

							@Override
							public boolean apply(Param param) {
								return param.type.equals("CookieValue");
							}
						}));
						List<Param> notCookies = Lists.newArrayList(Iterables.filter(method.params, new Predicate<Param>() {

							@Override
							public boolean apply(Param param) {
								return !param.type.equals("CookieValue");
							}
						}));
						String result = String.format("\t\t/* %s */\n", Joiner.on(", ").join(Iterables.transform(cookies, new Function<Param, String>() {

							@Override
							public String apply(Param param) {
								String optional = param.optional ? "optional " : "";
								String cookie = "cookie ";
								String name = param.name;
								return optional + cookie + param.clazz + " " + name;
							}
						})));
						result += String.format("\t\t%s: function(%s" + (notCookies.size() > 0 ? ", " : "") + "success, error){\n", method.url.replace("/", ""), Joiner.on(", ").join(Iterables.transform(notCookies, new Function<Param, String>() {

							@Override
							public String apply(Param param) {
								String optional = param.optional ? "optional " : "";
								String cookie = "";
								String name = param.type.equals("RequestBody") ? firstSymbolToLowerCase(param.clazz) : param.name;
								return "/* " + optional + cookie + param.clazz + " */ " + name;
							}
						})));
						result += String.format("\t\t\treturn serv._doPost" + (notCookies.size() == 1 &&  notCookies.get(0).type.equals("RequestBody") ? "Json" : "") + "('%s', %s, success, error);\n", description.url+service.url+method.url, notCookies.size() == 1 &&  notCookies.get(0).type.equals("RequestBody") ? firstSymbolToLowerCase(notCookies.get(0).clazz) : "{" + Joiner.on(", ").join(Iterables.transform(notCookies, new Function<Param, String>() {

							@Override
							public String apply(Param param) {
								return String.format("%s:%s", param.name, param.name);
							}
						})) + "}");
						result += "\t\t}";
						return result;
					}
				}));
				result += "\n\t}";
				return result;
			}
		}));
		
		return result + "\n}";
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
