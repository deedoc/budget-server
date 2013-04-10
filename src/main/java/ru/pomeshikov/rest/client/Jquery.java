package ru.pomeshikov.rest.client;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.core.PrioritizedParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.pomeshikov.rest.AuthService;

@Service
@RequestMapping("/client/jquery/getClient")
public class Jquery {
	
	private static Class[] REST_CLASSES = {AuthService.class/*, TransactionService.class*/};
	// TODO: Переделать с классов на "спросите у спринга"
	@RequestMapping(value="/", method=RequestMethod.GET)
	public @ResponseBody String getClient(){
		String client = "";
		for(int i = 0; i < REST_CLASSES.length; i++){
			Class<?> clazz = REST_CLASSES[i];
			String serviceUrl = ((RequestMapping)clazz.getAnnotation(RequestMapping.class)).value()[0];
			System.out.println(serviceUrl);
			
			
			Method[] methods = clazz.getMethods();
			for(int j = 0; j < methods.length; j++){
				Method method = methods[j];
				RequestMapping requestMappingAnnotation = method.getAnnotation(RequestMapping.class);
				if(requestMappingAnnotation != null){
					String methodUrl = requestMappingAnnotation.value()[0];
					System.out.println("\t" + methodUrl);
					
					Class<?>[] parameterTypes = method.getParameterTypes();
					for(int k = 0; k < parameterTypes.length; k++){
						Class<?> paramType = parameterTypes[k];
						PrioritizedParameterNameDiscoverer ppp = new PrioritizedParameterNameDiscoverer();
						ppp.getParameterNames(method);
						System.out.println("\t\t" + paramType + " ");
						Annotation[] annotations = method.getParameterAnnotations()[k];
						for(int d = 0; d < annotations.length; d++){
							Annotation annotation = annotations[d];
							if(annotation.annotationType().equals(RequestParam.class)){
								System.out.println("\t\t\t" + annotation);
							}
						}
					}
				}
			}
		}
		System.out.println(client);
		return client;
	}
	
	public static void main(String[] args){
		new Jquery().getClient();
	}

}
