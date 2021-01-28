package com.korea.Springdi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//스프링에서 환경설정할때 사용하는 어노테이션
public class AppContext {
	//사용하기 전에 먼저 생성되어있음(메인이 실행되기전에 먼저 만들어져 있다.)
	@Bean
	public Greeting greeter() {
		Greeting g = new Greeting(1000, "id=test");
		return g;
	}
	
	@Bean
	public Person person() {
		return new Person("홍길동", 20);
	}
}
