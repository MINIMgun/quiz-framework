package net.minimgun.quizframework.util;

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
}
