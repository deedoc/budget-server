package ru.pomeshikov.rest.client;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.core.PrioritizedParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import ru.pomeshikov.rest.AuthService;

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
	
	@PostConstruct
	void init(){
		String url = requestMappingHandlerMapping.getApplicationContext().getApplicationName() + "/rest";
		for(Entry<RequestMappingInfo, HandlerMethod> h : requestMappingHandlerMapping.getHandlerMethods().entrySet()){
			String metUrl = h.getKey().getPatternsCondition().getPatterns().iterator().next();
			System.out.println(metUrl);
			List<String> parameterTypes = new ArrayList<String>();
			for(MethodParameter p : h.getValue().getMethodParameters()){
				if(p.getParameterAnnotation(RequestParam.class) != null || p.getParameterAnnotation(RequestBody.class) != null){
					System.out.println("\t"+p.getParameterType().getSimpleName());
					parameterTypes.add(p.getParameterType().getSimpleName());
				}
			}
		}
	}
}
