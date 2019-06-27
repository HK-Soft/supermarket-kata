package kata.supermarket.pricing;

import static org.hamcrest.MatcherAssert.assertThat;

import kata.supermarket.core.Order;
import kata.supermarket.core.Product;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class PricingServiceTest {

    private PricingService pricingService = new PricingService();

    private static final String PRODUCT_NAME_A = "AAAAA" ;
    private static final BigDecimal PRODUCT_PRICE_A = new BigDecimal(10);
    private static Product product ;

    @BeforeAll
    public static void setup(){
        product = new Product();
        product.setName(PRODUCT_NAME_A);
        product.setPrice(PRODUCT_PRICE_A);
    }

    @Test
    public void should_calculate_one_product_order_total() {
        //Given
        Order order = new Order();
        //When
        BigDecimal result = pricingService.getOrderTotal(order);
        //Then
        assertThat(result,  Matchers.comparesEqualTo(new BigDecimal(20)));
    }

    @Test
    public void should_calculate_multi_product_order_total() {

    }
}
