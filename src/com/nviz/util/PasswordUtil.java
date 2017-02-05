package com.nviz.util;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordUtil {

    private static final String ALGORITHM = "AES";
    private static final int ITERATIONS = 2;
    private static final byte[] KEYVALUE = new byte[] { 'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't',
            'K', 'e', 'y' };
    private static final Logger LOG = LoggerFactory.getLogger(PasswordUtil.class);
    private static Base64 coder = new Base64();
    private static String salt = "nviz360";

    public static String encrypt(String value) {
        Key key;
        String eValue = null;
        try {
            key = generateKey();
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            String valueToEnc = null;
            eValue = value;
            for (int i = 0; i < ITERATIONS; i++) {
                valueToEnc = salt + eValue;
                byte[] encValue = c.doFinal(valueToEnc.getBytes());
                eValue = coder.encodeAsString(encValue);
            }
        } catch (Exception e) {
            LOG.error("Error while encrypting the password in encrypt() method:", e);
        }
        return eValue;
    }

    public static String decrypt(String value) {
        Key key;
        String dValue = null;
        try {
            key = generateKey();
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            String valueToDecrypt = value;
            for (int i = 0; i < ITERATIONS; i++) {
                byte[] decordedValue = coder.decode(valueToDecrypt);
                byte[] decValue = c.doFinal(decordedValue);
                dValue = new String(decValue).substring(salt.length());
                valueToDecrypt = dValue;
            }
        } catch (Exception e) {
            LOG.error("Error while decrypting the password in decrypt() method:", e);
        }
        return dValue;
    }

    private static Key generateKey() {
        return new SecretKeySpec(KEYVALUE, ALGORITHM);

    }

}
