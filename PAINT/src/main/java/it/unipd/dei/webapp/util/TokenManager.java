package it.unipd.dei.webapp.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class TokenManager {

    public static final long EXPIRATION_TIME = 3600_000;

    private static final String SECRET_KEY = "TokenKeyForPAINT";

    public static String generateToken(String email) {
        long expirationTime = System.currentTimeMillis() + EXPIRATION_TIME;
        String base64Email = Base64.getEncoder().encodeToString(email.getBytes(StandardCharsets.UTF_8));
        String base64Expiration = Base64.getEncoder().encodeToString(String.valueOf(expirationTime).getBytes(StandardCharsets.UTF_8));
        String signature = generateHmac(base64Email + "." + base64Expiration);

        return base64Email + "." + base64Expiration + "." + signature;
    }

    public static boolean validateToken(String token) {
        // Separate token
        String[] parts = token.split("\\.");
        // The token must have 3 parts: email, expiry, signature
        if (parts.length != 3) {
            return false;
        }

        String base64Email = parts[0];
        String base64Expiration = parts[1];
        String signature = parts[2];

        // Check if the signature correspond
        String data = base64Email + "." + base64Expiration;
        String regeneratedSignature = generateHmac(data);

        if (!signature.equals(regeneratedSignature)) {
            return false;  // La firma non corrisponde
        }

        // Check if the token has expired
        long expirationTime = Long.parseLong(new String(Base64.getDecoder().decode(base64Expiration), StandardCharsets.UTF_8));
        if (System.currentTimeMillis() > expirationTime) {
            return false; 
        }

        return true;
    }

    private static String generateHmac(String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKey);
            byte[] hmacBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hmacBytes);
        } catch (Exception e) {
            // TODO sistemare
            throw new RuntimeException("Errore nella generazione dell'HMAC", e);
        }
    }
}
