package generators;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {

    //private static Locale locale = new Locale("pl", "PL", "UNIX");

    private static Faker faker = new Faker(Locale.US);


    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getStreet() {
        return faker.address().streetAddress();
    }

    public static String getCity() {
        return faker.address().city();
    }

    public static String getState() {
        return faker.address().state();
    }

    public static String getZipCode() {
        return faker.address().zipCode();
    }

    public static String getPhone() {
        return faker.phoneNumber().cellPhone();
    }

    public static String getSsn() {
        return faker.idNumber().ssnValid();
    }

    public static String getUsername() {
        return faker.name().username();
    }

    public static String getPassword() {
        return faker.internet().password(8,12);
    }






}