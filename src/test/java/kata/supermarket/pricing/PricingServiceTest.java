package kata.supermarket.pricing;

import static org.hamcrest.MatcherAssert.assertThat;

import kata.supermarket.Utils.UnitUtils;
import kata.supermarket.core.Order;
import kata.supermarket.core.Product;
import kata.supermarket.core.Unit;
import kata.supermarket.pricing.startegy.XProductForYPriceStrategy;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void setup() {
        product = new Product();
        product.setName(PRODUCT_NAME_A);
        product.setPrice(PRODUCT_PRICE_A);

        PRODUCT_UNIT_OUNCE = new Unit();
        PRODUCT_UNIT_OUNCE.setDescription("ounce");

        PRODUCT_UNIT_POUND = new Unit();
        PRODUCT_UNIT_POUND.setDescription("pound");
    }

    @Test
    public void should_calculate_order_price() throws Exception {
        //Given
        Order order = new Order();
        order.addProduct(product, UnitUtils.convert(PRODUCT_PRICE_QUANTITY_A, product.getUnit(), product.getUnit()));
        //When
        BigDecimal result = pricingService.getOrderTotal(order);
        //Then
        assertThat(result, Matchers.comparesEqualTo(new BigDecimal(30)));
    }

    @Test
    public void should_calculate_order_price_in_different_unit() throws Exception {
        //Given
        Product productWithUnit = new Product();
        product.setName(PRODUCT_NAME_A);
        product.setUnit(PRODUCT_UNIT_POUND);
        BigDecimal priceOfOnePound = new BigDecimal(16);
        product.setPrice(priceOfOnePound);
        Order order = new Order();
        double quantityInOunce = 4;
        order.addProduct(product, UnitUtils.convert(quantityInOunce, PRODUCT_UNIT_OUNCE, product.getUnit()));
        //When
        BigDecimal result = pricingService.getOrderTotal(order);
        //Then
        assertThat(result, Matchers.comparesEqualTo(new BigDecimal(4)));
    }

    @Test
    public void should_calculate_order_complex_price_X_product_for_Y_price() throws Exception {
        //Given
        Order order = new Order();
        double XProductQuantity = 3;
        BigDecimal YPrice = new BigDecimal(1);
        product.addStrategy(new XProductForYPriceStrategy(XProductQuantity, YPrice));
        order.addProduct(product, UnitUtils.convert(XProductQuantity, product.getUnit(), product.getUnit()));
        //When
        BigDecimal resultPriceForXProduct = pricingService.getOrderTotal(order);
        //Then
        assertThat(resultPriceForXProduct, Matchers.comparesEqualTo(YPrice));
    }

}
