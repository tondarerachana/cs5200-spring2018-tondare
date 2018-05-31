package edu.northeastern.cs5200.controllers.hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**(
 *  this is class for HelloController
 * @author rachanatondare
 *
 */
@RestController
public class HelloController {
	
	@Autowired
	HelloRepository helloRepository;
	
	@RequestMapping("/api/hello/insert")
	public HelloObject insertHelloObject() {
		HelloObject obj = new HelloObject("Hello Rachana Tondare!");
		helloRepository.save(obj);
		return obj;
	}
	
	@RequestMapping("/api/hello/insert/{msg}")
	public HelloObject insertMessage(@PathVariable("msg") String message) {
		HelloObject obj = new HelloObject(message);
		helloRepository.save(obj);
		return obj;
	}


	@RequestMapping("/api/hello/select/all")
	public List<HelloObject> selectAllHelloObjects() {
				return helloRepository.findAll();
	
	}

	
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
		return new HelloObject("Hello Rachana Tondare!");
		
	}


	
}
