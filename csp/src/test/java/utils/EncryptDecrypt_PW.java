package utils;

import java.io.File;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class EncryptDecrypt_PW {
	private static String secretKey;
	private static String secretkeyfilepath = System.getProperty("user.dir") +"\\src\\test\\resources\\config\\PasswordSecretKey.pdf";
	private static String salt = "QASecurity!!!!";

	public static void main(String[] args) {
		String originalString = "Welcome123"; // Enter the string you need to Encrypt
		String encryptedString = encrypt(originalString);
		//String decryptedString = decrypt("Welcome123");

	//	System.out.println("secretkeyfilepath = " + secretkeyfilepath);
	//	System.out.println("Original Password = " + originalString);
		System.out.println("Encrypted Password = " + encryptedString);
	//	System.out.println("Decrypted Password = " + decryptedString);
	}

	public static String encrypt(String strToEncrypt) {
		try {
			secretKey = GetSecretKey();
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 128);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static String decrypt(String strToDecrypt) {
		try {
			secretKey = GetSecretKey();
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 128);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	public static String GetSecretKey() {
		PDDocument pd;
		String key = null;
		try {
			File input = new File(secretkeyfilepath);
			pd = PDDocument.load(input, "abcqa123");
			pd.setAllSecurityToBeRemoved(true);

			// for printing pdf file data
			PDFTextStripper reader = new PDFTextStripper();
			String pageText = reader.getText(pd);
			String[] data = pageText.split("=");
			key = data[1];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;

	}

}
