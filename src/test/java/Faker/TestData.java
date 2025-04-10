package Faker;

import com.github.javafaker.Faker;

public class TestData {

    public static String fakeNumber() {
        return new Faker().number().digits(8);
    }

    public static String emailGenerator() {
        return "test" + System.currentTimeMillis() + "@mailinator.com";
    }

    public static String numberGenerator() {
        return "000" + fakeNumber();
    }
}
