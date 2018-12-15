package utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha512Hash;

public class PasswordUtil {

	private static int HASHEITERATIONS = 2;

	public static String encryptPassword(String password, String salt) {
		SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
		generator.setSeed(salt.getBytes());
		String publicSalt = generator.nextBytes().toHex();

		Sha512Hash hash = new Sha512Hash(password, salt + publicSalt, HASHEITERATIONS);
		String encodedPassword = hash.toBase64();
		return encodedPassword;
	}

	public static void main(String[] args) {
		String password = "123";
		String salt = "tom";

		String str = encryptPassword(password, salt);

		System.out.println(str);

	}
}
