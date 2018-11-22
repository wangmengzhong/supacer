package com.wmz.common.safe.rsa;

import java.security.PrivateKey;
import java.security.PublicKey;

public class SecurityUtils {
	
	/** 
     * 解密 
     * @param cipherText 密文 
     * @return 返回解密后的字符串 
     * @throws Exception  
     */  
    public static String decrypt(String cipherText) throws Exception{  
        // 从字符串中得到私钥  
        PrivateKey privateKey = RSAUtils.loadPrivateKey(KeyConfig.privateKey);  
        // 解密  
        byte[] decryptByte = RSAUtils.decryptData(Base64Utils.decode(cipherText), privateKey);  
        String decryptStr = new String(decryptByte,"utf-8");  
        return decryptStr;  
    }  
    /** 
     * 加密 
     * @param plainTest 明文 
     * @return  返回加密后的密文 
     * @throws Exception  
     */  
    public static String encrypt(String plainTest){  
     	// 从字符串中得到公钥  
        PublicKey publicKey;
		try {
			publicKey = RSAUtils.loadPublicKey(KeyConfig.publicKey);
			// 加密  
	        byte[] encryptByte = RSAUtils.encryptData(plainTest.getBytes(), publicKey);  
	        String afterencrypt = Base64Utils.encode(encryptByte);  
	        return afterencrypt;  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;  
    }  
}
