package vn.edu.hcmut.core.utilities.encrypt;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;

public class EncryptRSAProvider {

	private String keyStoreName;
	private String alias;
	private String passWord;
	private String pathFileKeyStore;
	private Cipher cipher;
	private Map<String, String> rsaProperties;
	private boolean salt = true;

	private EncryptRSAProvider() {
		super();
	}
	
	@Autowired
	public EncryptRSAProvider(Map<String, String> RSAProperties)
			throws NoSuchAlgorithmException, NoSuchPaddingException {
		super();
		this.rsaProperties = RSAProperties;
		this.keyStoreName = this.rsaProperties.get("keyStoreName");
		this.alias = this.rsaProperties.get("alias");
		this.passWord = this.rsaProperties.get("passWord");
		if (salt) {
			this.cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} else {
			this.cipher = Cipher.getInstance("RSA/ECB/NoPadding");
		}
		ClassLoader classLoader = new EncryptRSAProvider().getClass().getClassLoader();
		URL pathFile = classLoader.getResource("jks/" + this.keyStoreName);
		pathFileKeyStore = pathFile.getFile();
	}

	private KeyPair getKeyPairFromKeyStore() throws Exception {
		try {
			InputStream ins = new FileInputStream(pathFileKeyStore);
			KeyStore keyStore = KeyStore.getInstance("JCEKS");
			keyStore.load(ins, passWord.toCharArray()); // Keystore password
			KeyStore.PasswordProtection keyPassword = // Key password
					new KeyStore.PasswordProtection(passWord.toCharArray());
			KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, keyPassword);
			java.security.cert.Certificate cert = keyStore.getCertificate(alias);
			PublicKey publicKey = cert.getPublicKey();
			PrivateKey privateKey = privateKeyEntry.getPrivateKey();
			return new KeyPair(publicKey, privateKey);
		} catch (Exception e) {
			throw e;
		}
	}

	public String encrypt(String plainText) throws Exception {
		try {
			PublicKey publicKey = getKeyPairFromKeyStore().getPublic();
			Cipher encryptCipher = this.cipher;
			encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] cipherText = encryptCipher.doFinal(plainText.getBytes("UTF-8"));
			return Base64.getEncoder().encodeToString(cipherText).trim();
		} catch (Exception e) {
			throw e;
		}
	}

	public String decrypt(String cipherText) throws Exception {
		try {
			PrivateKey privateKey = getKeyPairFromKeyStore().getPrivate();
			byte[] bytes = Base64.getDecoder().decode(cipherText);
			Cipher decriptCipher = this.cipher;
			decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
			return new String(decriptCipher.doFinal(bytes), "UTF-8").trim();
		} catch (Exception e) {
			throw e;
		}
	}

	public String sign(String plainText) throws Exception {
		try {
			PrivateKey privateKey = getKeyPairFromKeyStore().getPrivate();
			Signature privateSignature = Signature.getInstance("SHA256withRSA");
			privateSignature.initSign(privateKey);
			privateSignature.update(plainText.getBytes("UTF-8"));
			byte[] signature = privateSignature.sign();
			return Base64.getEncoder().encodeToString(signature);
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean verify(String plainText, String signature) throws Exception {
		try {
			PublicKey publicKey = getKeyPairFromKeyStore().getPublic();
			Signature publicSignature = Signature.getInstance("SHA256withRSA");
			publicSignature.initVerify(publicKey);
			publicSignature.update(plainText.getBytes("UTF-8"));
			byte[] signatureBytes = Base64.getDecoder().decode(signature);
			return publicSignature.verify(signatureBytes);
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isSalt() {
		return salt;
	}

	public void setSalt(boolean salt) {
		this.salt = salt;
	}

	
}