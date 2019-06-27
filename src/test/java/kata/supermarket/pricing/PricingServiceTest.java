package kata.supermarket.pricing;

import static org.hamcrest.MatcherAssert.assertThat;

import kata.supermarket.core.Order;
import kata.supermarket.core.Product;
import kata.supermarket.core.Unit;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class PricingServiceTest {

    private PricingService pricingService = new PricingService();

    private static final String PRODUCT_NAME_A = "AAAAA";
    private static final BigDecimal PRODUCT_PRICE_A = new BigDecimal(10);
    private static final int PRODUCT_PRICE_QUANTITY_A = 3;
    private static Product product;
    private static Unit PRODUCT_UNIT_OUNCE;
    private static Unit PRODUCT_UNIT_POUND;

    @BeforeAll
    public static void setup() {
        product = new Product();
        product.setName(PRODUCT_NAME_A);
        product.setPrice(PRODUCT_PRICE_A);

        PRODUCT_UNIT_OUNCE = new Unit();
        PRODUCT_UNIT_OUNCE.setDescription("ounce");

        PRODUCT_UNIT_OUNCE = new Unit();
        PRODUCT_UNIT_OUNCE.setDescription("pound");
    }

    @Test
    public void should_calculate_order_price() {
        //Given
        Order order = new Order();
        order.addProduct(product, PRODUCT_PRICE_QUANTITY_A);
        //When
        BigDecimal result = pricingService.getOrderTotal(order);
        //Then
        assertThat(result, Matchers.comparesEqualTo(new BigDecimal(30)));
    }

    @Test
    public void should_calculate_order_price_in_different_unit() {
        //Given
        Product productWithUnit = new Product();
        product.setName(PRODUCT_NAME_A);
        product.setUnit(PRODUCT_UNIT_POUND);
        BigDecimal priceOfOnePound = new BigDecimal(16);
        product.setPrice(priceOfOnePound);
        Order order = new Order();
        int quantityInOunce = 4;
        order.addProduct(product, quantityInOunce);
        //When
        BigDecimal result = pricingService.getOrderTotal(order);
        //Then
        assertThat(result, Matchers.comparesEqualTo(new BigDecimal(4)));
    }
}
