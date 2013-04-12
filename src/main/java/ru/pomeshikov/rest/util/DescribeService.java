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

@Service
@RequestMapping("/util/describe/")
public class DescribeService {
	
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	@RequestMapping(value="describe", method=RequestMethod.GET)
	public @ResponseBody Description describe(){
		return description;
	}
	
	private Description description = null;
	@PostConstruct
	void init(){
		description = new Description();
		description.setUrl(requestMappingHandlerMapping.getApplicationContext().getApplicationName() + "/rest");
		
		Map<String, Service> servicesMap = new HashMap<String, Service>();
		
		for(Entry<RequestMappingInfo, HandlerMethod> h : requestMappingHandlerMapping.getHandlerMethods().entrySet()){
			String[] explodedUrl = h.getKey().getPatternsCondition().getPatterns().iterator().next().split("/");
			String serviceUrl = "/" + explodedUrl[1];
			if(servicesMap.get(serviceUrl) == null){
				Service service = new Service();
				service.setUrl(serviceUrl);
				servicesMap.put(serviceUrl, service);
			}
			
			String methodUrl = "/" + explodedUrl[2];
			Method method = new Method();
			method.setUrl(methodUrl);
			servicesMap.get(serviceUrl).getMethods().add(method);
			for(MethodParameter p : h.getValue().getMethodParameters()){
				if(p.getParameterAnnotations().length == 0){
					continue;
				}
				Annotation annotation = p.getParameterAnnotations()[0];
				
				Param param = new Param();
				param.setClazz(p.getParameterType().getSimpleName());
				if(annotation.annotationType().equals(RequestParam.class)){
					RequestParam reqParamAnn = (RequestParam)annotation;
					param.setName(reqParamAnn.value());
					param.setOptional(!reqParamAnn.required());
				}
				if(annotation.annotationType().equals(CookieValue.class)){
					CookieValue cookieValAnn = (CookieValue)annotation;
					param.setName(cookieValAnn.value());
					param.setOptional(!cookieValAnn.required());
				}
				param.setType(annotation.annotationType().getSimpleName());
				
				method.getParams().add(param);
			}
		}
		
		for(Entry<String, Service> service : servicesMap.entrySet()){
			description.getServices().add(service.getValue());
		}
	}
	
	
	
	private class Description {
		private String url;
		private List<Service> services = new ArrayList<Service>();
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
		public List<Service> getServices() {
			return services;
		}
		public void setServices(List<Service> services) {
			this.services = services;
		}
	}
	
	private class Service {
		private String url;
		private List<Method> methods = new ArrayList<Method>();
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public List<Method> getMethods() {
			return methods;
		}
		public void setMethods(List<Method> methods) {
			this.methods = methods;
		}
	}
	
	private class Method {
		private String url;
		private List<Param> params = new ArrayList<Param>();
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
		public List<Param> getParams() {
			return params;
		}
		public void setParams(List<Param> params) {
			this.params = params;
		}
	}
	
	private class Param {
		private String name;
		private String clazz;
		private String type;
		private boolean optional = false;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getClazz() {
			return clazz;
		}
		public void setClazz(String clazz) {
			this.clazz = clazz;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public boolean isOptional() {
			return optional;
		}
		public void setOptional(boolean optional) {
			this.optional = optional;
		}
	}
}
