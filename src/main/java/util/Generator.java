package util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class Generator {

    public static int getVerificationCode() {
        Random random = new Random();
        int randomValue = random.nextInt(9999) + 1000;
        if (randomValue > 9999) {
            randomValue = randomValue / 10;
        }
        return randomValue;
    }

    public static String getSalt() {
        return RandomStringUtils.randomAlphanumeric(4);
    }
}
