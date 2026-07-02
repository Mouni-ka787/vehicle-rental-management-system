package services;

import java.util.Random;

public class OTPService {

    private static int generatedOTP;

    public static int generateOTP() {

        Random random = new Random();

        generatedOTP = 100000 + random.nextInt(900000);

        return generatedOTP;

    }

    public static boolean verifyOTP(int userOTP) {

        return generatedOTP == userOTP;

    }

}