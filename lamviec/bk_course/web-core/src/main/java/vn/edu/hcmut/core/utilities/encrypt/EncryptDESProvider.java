package vn.edu.hcmut.core.utilities.encrypt;

import java.security.Key;
import java.util.Base64;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("encryptDESProvider")
public class EncryptDESProvider {
	private SecretKeySpec skeySpec;
	private Cipher cipher;
	private Map<String, String> desProperties;

	@Autowired
	public EncryptDESProvider(Map<String, String> DESProperties) {
		super();
		this.desProperties = DESProperties;
		this.skeySpec = new SecretKeySpec(this.desProperties.get("secretKey").getBytes(), "DES");
		//this.skeySpec = new SecretKeySpec("07SXm0sR".getBytes(), "DES");
	}

	public String encrypt(String plainText) throws Exception {
		try {
			this.cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
			this.cipher.init(Cipher.ENCRYPT_MODE, this.skeySpec);
			byte[] byteEncrypted = this.cipher.doFinal(plainText.getBytes("UTF-8"));
			return Base64.getEncoder().encodeToString(byteEncrypted);
		} catch (Exception e) {
			throw e;
		}
	}

	public String decrypt(String cipherText) throws Exception {
		try {
			this.cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
			this.cipher.init(Cipher.DECRYPT_MODE, this.skeySpec);
			byte[] byteEncrypted = Base64.getDecoder().decode(cipherText);
			byte[] byteDecrypted = this.cipher.doFinal(byteEncrypted);
			return new String(byteDecrypted, "UTF8").trim();
		} catch (Exception e) {
			throw e;
		}
	}

	public String encrypt2Key(String plainText) throws Exception {
		byte[] encoding;
		try {
			encoding = Base64.getEncoder().encode(plainText.getBytes("UTF-8"));
			byte[] str5 = des3EncodeCBC(this.desProperties.get("secretKey").getBytes(),
					this.desProperties.get("secretKey2").getBytes(), encoding);
			byte[] encoding1 = Base64.getEncoder().encode(str5);
			return new String(encoding1);
		} catch (Exception e) {
			throw e;
		}
	}

	public String decrypt2Key(String cipherText) throws Exception {
		try {
			byte[] decode = Base64.getDecoder().decode(cipherText.getBytes("UTF-8"));
			byte[] str6 = des3DecodeCBC(this.desProperties.get("secretKey").getBytes(),
					this.desProperties.get("secretKey2").getBytes(), decode);
			String data = new String(str6);
			byte[] decode1 = Base64.getDecoder().decode(data.trim().getBytes("UTF-8"));
			return new String(decode1);
		} catch (Exception e) {
			throw e;
		}
	}

	private byte[] des3EncodeCBC(byte[] key, byte[] keyiv, byte[] data) throws Exception {
		try {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(key);
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(keyiv);
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] bout = cipher.doFinal(data);
			return bout;

		} catch (Exception e) {
			throw e;
		}
	}

	private static byte[] des3DecodeCBC(byte[] key, byte[] keyiv, byte[] data) throws Exception {
		try {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(key);
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");// PKCS5Padding NoPadding
			IvParameterSpec ips = new IvParameterSpec(keyiv);
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
			byte[] bout = cipher.doFinal(data);
			return bout;
		} catch (Exception e) {
			throw e;
		}
	}

}
