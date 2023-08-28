package com.asseco.demo.user.generator;

import com.asseco.demo.common.UserContactCode;
import com.asseco.demo.user.UserDbForm;
import com.asseco.demo.usercontact.UserContactDbForm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class UserGenerator {

    public static final int USERS_NUMBER = 15000;
    public static final int CHUNK_SIZE = 100;

    public Set<UserDbForm> generateUsers() {
        var result = new HashSet<UserDbForm>();
        for (int i = 0; i < CHUNK_SIZE; i++) {
            result.add(generateUser());
        }
        return result;
    }

    private UserDbForm generateUser() {
        return UserDbForm.builder()
                .firstName(RandomStringUtils.randomAlphabetic(10))
                .lastName(RandomStringUtils.randomAlphabetic(15))
                .idNumber(Long.parseLong(RandomStringUtils.randomNumeric(11)))
                .contacts(generateUserContacts())
                .build();
    }

    private Set<UserContactDbForm> generateUserContacts() {
        var result = new HashSet<UserContactDbForm>();
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 2;
        for (int i = 0; i < randomNumber; i++) {
            var codes = UserContactCode.values();
            var index = random.nextInt(codes.length);
            var randomCode = codes[index];
            result.add(generateUserContact(randomCode));
        }
        return result;
    }

    private UserContactDbForm generateUserContact(UserContactCode randomCode) {
        return UserContactDbForm.builder()
                .value(generateValue(randomCode))
                .code(randomCode.name())
                .build();
    }

    private String generateValue(UserContactCode randomCode) {
        switch (randomCode) {
            case EMAIL -> {
                return generateEmail();
            }
            case PRIVATE_PHONE_NUMBER, BUSINESS_PHONE_NUMBER -> {
                return generatePhone();
            }
            case ADDRESS, REGISTERED_ADDRESS -> {
                return generateAddress();
            }
            default -> {
                return null;
            }
        }
    }

    private String generateAddress() {
        return RandomStringUtils.randomAlphanumeric(40);
    }

    private String generatePhone() {
        return RandomStringUtils.randomNumeric(9);
    }

    private String generateEmail() {
        return RandomStringUtils.randomAlphanumeric(5) + "@gmail.com";
    }

}
