package vn.edu.hcmut.core.utilities.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

@Component("encryptAESProvider")
public class EncryptAESProvider {
	private String ALGO = "AES";
	private String keyStr = "G-KaPdRgUkXp2s5v8y/B?E(H+MbQeThV";

	@Autowired
	public EncryptAESProvider(Map<String, String> AESProperties) {
		keyStr = AESProperties.get("secretKey");
	}

	private Key generateKey() throws Exception {
		byte[] keyValue = keyStr.getBytes("UTF-8");
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		keyValue = sha.digest(keyValue);
		keyValue = Arrays.copyOf(keyValue, 16); // use only first 128 bit
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}

	public String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes("UTF-8"));
		String encryptedValue = DatatypeConverter.printBase64Binary(encVal);
		return encryptedValue;
	}

	public String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = DatatypeConverter.parseBase64Binary(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue, "UTF-8").trim();
		return decryptedValue;
	}
}
