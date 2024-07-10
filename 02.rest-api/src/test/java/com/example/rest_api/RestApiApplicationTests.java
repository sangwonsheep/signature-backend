package com.example.rest_api;

import com.example.rest_api.model.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestApiApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() throws JsonProcessingException {
//		UserRequest user = new UserRequest("홍길동", 10, "hong@gmail.com", true);
//		user.setUserName("홍길동");
//		user.setUserAge(10);
//		user.setEmail("hong@gmail.com");
//		user.setIsKorean(true);

		// 객체 -> Json
		String json = "{\"user_name\":\"홍길동\",\"user_age\":10,\"email\":\"hong@gmail.com\",\"is_korean\":true}";
		System.out.println(json);

		// Json -> 객체 : 이 과정에서 UserRequest의 변수를 참조하는게 아니라 setter, getter 메서드를 참조해서 객체로 만들어줌.
		UserRequest dto = objectMapper.readValue(json, UserRequest.class);
		System.out.println(dto);
	}

}
