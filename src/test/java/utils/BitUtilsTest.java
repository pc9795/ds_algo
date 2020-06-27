package utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * Created By: Prashant Chaubey
 * Created On: 05-10-2019 23:19
 **/
class BitUtilsTest {

    static Stream<Arguments> testMsb() {
        return Stream.of(
                Arguments.of(3, 1),
                Arguments.of(21, 4)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testMsb(int n, int expected) {
        assert BitUtils.msb(n) == expected;
    }

}
