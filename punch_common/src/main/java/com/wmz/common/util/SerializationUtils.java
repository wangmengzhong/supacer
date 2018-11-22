package com.wmz.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class SerializationUtils {

	public static byte[] toByte(Serializable object) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);

			objectOutputStream.writeObject(object);

			objectOutputStream.close();

			return os.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static Serializable toObject(byte[] contents) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(contents);
			ObjectInputStream oi = new ObjectInputStream(bis);
			Serializable obj = (Serializable) oi.readObject();
			oi.close();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
