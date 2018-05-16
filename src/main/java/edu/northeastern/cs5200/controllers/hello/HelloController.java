package edu.northeastern.cs5200.controllers.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**(
 *  this is class for HelloController
 * @author rachanatondare
 *
 */
@RestController
public class HelloController {
	
	/**
	 * this method only says hello
	 * @return string saying hello
	 */
	@RequestMapping("/api/hello/string")
	public String sayHello() {
		return "Hello Rachana Tondare";
	}
	
	/**
	 * method to return an hello object
	 * @return  hello as object
	 */
	@RequestMapping("/api/hello/object")
	public HelloObject sayHelloObject() {
		return new HelloObject("Hello Jose Annunziato!");
		
	}


	
}
