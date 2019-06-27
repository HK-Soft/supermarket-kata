package kata.supermarket.utils;

import kata.supermarket.Utils.UnitUtils;
import kata.supermarket.core.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class unitUtilsTest {

    private static Unit UNIT_POUND;
    private static Unit UNIT_OUNCE;

    @BeforeAll
    public static void setup() {
        UNIT_OUNCE = new Unit();
        UNIT_OUNCE.setDescription("ounce");
        UNIT_POUND = new Unit();
        UNIT_POUND.setDescription("pound");
    }

    @Test
    public void should_return_value_in_converted_unit() {
        //Given
        //When
        double resultFromTo = UnitUtils.convert(1, UNIT_POUND, UNIT_OUNCE);
        double resultToFrom = UnitUtils.convert(16, UNIT_OUNCE, UNIT_POUND);
        //Then
        Assertions.assertEquals(16, resultFromTo);
        Assertions.assertEquals(1, resultToFrom);
    }
}
