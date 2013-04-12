package ru.pomeshikov.rest.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Service
@RequestMapping("/client/jquery/getClient")
public class Jquery {
	
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
//	var client = {
//		auth: {
//			register: function(/* String, String */params, success, error){
//				$.post("/budget-server/rest/auth/register", params, success).error(error);
//			},
//			login: function(/* String, String */params, success, error){
//				$.post("/budget-server/rest/auth/login", params, success).error(error);
//			}
//		}
//	}
	
	private class Client {
		public String url;
		public Map<String, Service> services = new HashMap<String, Service>();
		
		@Override
		public String toString() {
			String result = "var client = {\n";
			for(Entry<String, Service> service : services.entrySet()){
				result += "\t" + service.getKey().substring(1) + ": {\n";
				for(Entry<String, Method> method : service.getValue().methods.entrySet()){
					result += "\t\t" + method.getKey().substring(1) + ": function /* ";
					for(String paramType : method.getValue().paramTypes){
						result += paramType + ", ";
					}
					result += " */ params, success, error){\n";
					result += "\t\t\t$.post('";
					result += url + service.getKey() + method.getKey();
					result += "', params, success).error(error);\n";
					result += "\t\t},\n";
				}
				result += "\t},\n";
			}
			result += "}";
			return result;
		}
	}
	
	private class Service {
		public Map<String, Method> methods = new HashMap<String, Method>();
	}
	
	private class Method {
		public List<String> paramTypes = new ArrayList<String>();
	}
	
	@PostConstruct
	void init(){
		Client client = new Client();
		client.url = requestMappingHandlerMapping.getApplicationContext().getApplicationName() + "/rest";
		
		for(Entry<RequestMappingInfo, HandlerMethod> h : requestMappingHandlerMapping.getHandlerMethods().entrySet()){
			String[] explodedUrl = h.getKey().getPatternsCondition().getPatterns().iterator().next().split("/");
			String serviceUrl = "/" + explodedUrl[1];
			if(client.services.get(serviceUrl) == null){
				client.services.put(serviceUrl, new Service());
			}
			
			String methodUrl = "/" + explodedUrl[2];
			client.services.get(serviceUrl).methods.put(methodUrl, new Method());
			for(MethodParameter p : h.getValue().getMethodParameters()){
				if(p.getParameterAnnotation(RequestParam.class) != null || p.getParameterAnnotation(RequestBody.class) != null){
					client.services.get(serviceUrl).methods.get(methodUrl).paramTypes.add(p.getParameterType().getSimpleName());
				}
			}			
		}
		
		System.out.println(client.toString());
	}
}
