package com.wmz.punch_zuul;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PunchZuulApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	public static void main(String[] args) {
		String a="a";
		String b="a";
		System.out.println(a.hashCode()==b.hashCode());
	}

}
