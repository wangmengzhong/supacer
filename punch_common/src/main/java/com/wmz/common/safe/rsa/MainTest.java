package com.wmz.common.safe.rsa;

public class MainTest {
	
	public static void main(String[] args) {
		String ming="123";
		try {
			String mi=SecurityUtils.encrypt(ming);
			String jie=SecurityUtils.decrypt(mi);
			System.out.println("mi:"+mi);
			System.out.println("ming:"+jie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
