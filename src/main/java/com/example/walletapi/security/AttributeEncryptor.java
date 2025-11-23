package com.example.walletapi.security;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Converter
public class AttributeEncryptor implements AttributeConverter<String, String> {

    private static final String SECRET = "MySuperSecretKey"; // 16 байт, хранить безопасно
    private final SecretKeySpec secretKey;
    private final Cipher encryptCipher;
    private final Cipher decryptCipher;

    public AttributeEncryptor() throws Exception {
        byte[] keyBytes = SECRET.getBytes("UTF-8");
        secretKey = new SecretKeySpec(keyBytes, "AES");

        encryptCipher = Cipher.getInstance("AES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        decryptCipher = Cipher.getInstance("AES");
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            byte[] encrypted = encryptCipher.doFinal(attribute.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            byte[] decoded = Base64.getDecoder().decode(dbData);
            byte[] decrypted = decryptCipher.doFinal(decoded);
            return new String(decrypted);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
