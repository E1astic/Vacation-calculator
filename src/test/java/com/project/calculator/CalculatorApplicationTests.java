package com.project.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class CalculatorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void test()
	{
		String a="Hello ";
		String b="World";
		assertEquals("Hello World", a+b);
	}

}
