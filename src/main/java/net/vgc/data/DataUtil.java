package net.vgc.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class DataUtil {
	
	public static DataInputStream inputStream(Path path) throws IOException {
		return new DataInputStream(new BufferedInputStream(new FileInputStream(path.toFile())));
	}
	
	public static DataOutputStream outputStream(Path path) throws IOException {
		return new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path.toFile())));
	}
	
	public static String encrypt(long key, String text) {
		byte[] encryptBytes = new byte[text.getBytes().length];
		for (int i = 0; i < encryptBytes.length; i++) {
			encryptBytes[i] = (byte) (text.getBytes()[i] + key);
		}
		return new String(encryptBytes); 
	}
	
	public static String decrypt(long key, String text) {
		byte[] decryptBytes = new byte[text.getBytes().length];
		for (int i = 0; i < decryptBytes.length; i++) {
			decryptBytes[i] = (byte) (text.getBytes()[i] - key);
		}
		return new String(decryptBytes);
	}
	
}
