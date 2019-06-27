package kata.supermarket.utils;

import kata.supermarket.Utils.UnitUtils;
import kata.supermarket.core.Exception.ConversionRuleNotFound;
import kata.supermarket.core.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
    public void should_return_value_in_converted_unit() throws Exception {
        //Given
        //When
        double resultFromTo = UnitUtils.convert(1, UNIT_POUND, UNIT_OUNCE);
        double resultToFrom = UnitUtils.convert(16, UNIT_OUNCE, UNIT_POUND);
        //Then
        Assertions.assertEquals(16, resultFromTo);
        Assertions.assertEquals(1, resultToFrom);
    }

    @Test
    public void should_return_same_vale_fromAndTo_TheSameUnit() throws Exception {
        //Given
        double toBeConvertedValue = 16;
        //When
        double result = UnitUtils.convert(toBeConvertedValue, UNIT_POUND, UNIT_POUND);
        //Then
        Assertions.assertEquals(toBeConvertedValue, result);
    }

    @Test
    public void should_throw_exception_conversion_rule_not_found() {
        //Given
        Unit doesNotExist = new Unit();
        doesNotExist.setDescription("does_not_exist");
        double toBeConvertedValue = 16;
        //When //Then
        Assertions.assertThrows(ConversionRuleNotFound.class,
                () -> UnitUtils.convert(toBeConvertedValue, doesNotExist, doesNotExist));
    }
}
