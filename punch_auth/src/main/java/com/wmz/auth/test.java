package com.wmz.auth;

import java.util.List;

import org.springframework.boot.devtools.autoconfigure.RemoteDevToolsProperties.Debug;

public class test {

	public static void main(String[] args) {
		List s=null;
		System.out.println(s.isEmpty());
		/*int s=1;
		
		int a=Integer.valueOf(s);
		Double d=2.1;
		//System.out.println(String.valueOf(d));
		
		Object [] obj=new Object[]{1,"ss"};
		for(int i=0;i<obj.length;i++){
			System.out.println(obj[i].toString());
		}*/
		
	}
	
	public static boolean sql_check(Object [] obj) 	{
		for(int i=0;i<obj.length;i++){
			return sql_inj(obj[i].toString());
		}
		return false;
	}
	
	public static boolean sql_inj(String str) 	{ 
	String inj_str = "’|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,|--"; 
	String inj_stra[] = inj_str.split("|"); 
	for (int i=0 ; i < inj_stra.length ; i++ ) 
	{ 
	if (str.indexOf(inj_stra[i])>=0) 
	{ 
	return true; 
	} 
	} 
	return false; 
	}
}
