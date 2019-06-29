package kata.supermarket.pricing;

import static org.hamcrest.MatcherAssert.assertThat;

import kata.supermarket.Utils.UnitUtils;
import kata.supermarket.core.Order;
import kata.supermarket.core.Product;
import kata.supermarket.core.Unit;
import kata.supermarket.pricing.startegy.BuyYGetXForFree;
import kata.supermarket.pricing.startegy.XProductForYPriceStrategy;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

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

    @Test
    public void should_calculate_order_complex_price_X_product_for_Y_price_for_N_product() throws Exception {
        //Given
        BigDecimal YPrice = new BigDecimal(1);

        double XProductQuantity = 3;
        double NProductQuantity = 2;

        product.setPrice(new BigDecimal(.5));
        product.addStrategy(new XProductForYPriceStrategy(XProductQuantity, YPrice));

        Order order = new Order();
        order.addProduct(product, UnitUtils.convert(NProductQuantity, product.getUnit(), product.getUnit()));
        //When
        BigDecimal resultPriceForXProduct = pricingService.getOrderTotal(order);
        //Then
        assertThat(resultPriceForXProduct, Matchers.comparesEqualTo(new BigDecimal(1)));
    }

    @Test
    public void should_calculate_order_complex_price_offer_Y_for_x_Bought() throws Exception {
        //Given
        Order order = new Order();
        double quantityBought = 2;
        double quantityOffered = 1;
        product.setPrice(new BigDecimal(1));
        product.addStrategy(new BuyYGetXForFree(quantityBought, quantityOffered));
        order.addProduct(product, UnitUtils.convert(quantityBought + quantityOffered, product.getUnit(), product.getUnit()));
        //When
        BigDecimal resultPrice = pricingService.getOrderTotal(order);
        //Then
        assertThat(resultPrice, Matchers.comparesEqualTo(new BigDecimal(2)));
    }

    @Test
    public void should_calculate_order_complex_price_offer_Y_for_x_Bought_N_product() throws Exception {
        //Given
        Order order = new Order();

        double quantityBought = 2;
        double quantityOffered = 1;
        double quantitySoled = 4;

        product.setPrice(new BigDecimal(1));
        product.addStrategy(new BuyYGetXForFree(quantityBought, quantityOffered));
        order.addProduct(product, UnitUtils.convert(quantitySoled, product.getUnit(), product.getUnit()));

        //When
        BigDecimal resultPrice = pricingService.getOrderTotal(order);
        //Then
        assertThat(resultPrice, Matchers.comparesEqualTo(new BigDecimal(2)));
    }

    @Test
    public void should_fail_when_uings_decimal_value_for_unit_product() throws Exception {
        //Given
        Order order = new Order();
        double invalidQuantityForProductUnit = 1.5d ;
        Unit unit = new Unit();
        unit.setDescription("Unit");
        product.setUnit(unit);
        order.addProduct(product, UnitUtils.convert(invalidQuantityForProductUnit, product.getUnit(), product.getUnit()));
        //When //Then
        Assertions.assertThrows(Exception.class, () -> pricingService.getOrderTotal(order));
    }
}
