package net.minimgun.quizframework.util;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.security.core.token.Sha512DigestUtils;

public class TokenGenerator {

    private static AtomicInteger counter = new AtomicInteger(0);

    public static String generateToken() {
        String uuid = UUID.randomUUID().toString();
        return generateToken(uuid);
    }

    public static String generateToken(String id) {
        String time = String.valueOf(System.currentTimeMillis());
        return Sha512DigestUtils.shaHex(time + id + String.valueOf(counter.getAndIncrement()));
    }
    
    public static String generateSessionId() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();

        return generatedString.toUpperCase();
    }
}
