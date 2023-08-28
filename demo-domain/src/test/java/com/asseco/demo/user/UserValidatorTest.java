package com.asseco.demo.user;

import com.asseco.demo.exception.ErrorCode;
import com.asseco.demo.exception.MyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;


@ExtendWith(MockitoExtension.class)
public class UserValidatorTest {

    @InjectMocks
    private UserValidator userValidator;

    @ParameterizedTest
    @MethodSource("validateIdNumberInvalidTestInput")
    void validateIdNumberInvalidTest(Long idNumber) {
        var exception = Assertions.assertThrows(MyException.class, () -> userValidator.validateIdNumber(idNumber));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        Assertions.assertEquals(ErrorCode.DEMO_1.name(), exception.getErrorCode());
    }

    private static Stream<Arguments> validateIdNumberInvalidTestInput() {
        return Stream.of(
                Arguments.of(123456789012L),
                Arguments.of(1234567890L)
        );
    }

    @Test
    void validateIdNumberTest() {
        userValidator.validateIdNumber(12345678901L);
    }

}
