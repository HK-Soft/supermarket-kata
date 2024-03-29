package kata.supermarket.pricing;

import static org.hamcrest.MatcherAssert.assertThat;

import kata.supermarket.Utils.UnitUtils;
import kata.supermarket.domain.Order;
import kata.supermarket.domain.QuantifiedProduct;
import kata.supermarket.domain.Unit;
import kata.supermarket.domain.WeightedProduct;
import kata.supermarket.pricing.startegy.BuyYGetXForFree;
import kata.supermarket.pricing.startegy.XProductForYPriceStrategy;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

public class PricingServiceTest {

    private PricingService pricingService = new PricingService();

    private static final String PRODUCT_NAME_A = "AAAAA";
    private static final BigDecimal PRODUCT_PRICE_A = new BigDecimal(10);
    private static Unit PRODUCT_UNIT_OUNCE;
    private static Unit PRODUCT_UNIT_POUND;

    @BeforeEach
    public void setup() {
        PRODUCT_UNIT_OUNCE = new Unit();
        PRODUCT_UNIT_OUNCE.setDescription("ounce");

        PRODUCT_UNIT_POUND = new Unit();
        PRODUCT_UNIT_POUND.setDescription("pound");
    }


    private WeightedProduct getWeightedProduct() {
        WeightedProduct product = new WeightedProduct();
        product.setName(PRODUCT_NAME_A);
        product.setPrice(PRODUCT_PRICE_A);
        return product;
    }

    private QuantifiedProduct getQuantifiedProduct() {
        QuantifiedProduct product = new QuantifiedProduct();
        product.setName(PRODUCT_NAME_A);
        product.setPrice(PRODUCT_PRICE_A);
        return product;
    }

    @Test
    public void should_calculate_order_price_for_weighted_product() throws Exception {
        //Given
        Order order = new Order();
        WeightedProduct product = getWeightedProduct();
        double quantity = 1.5;
        order.addProduct(product, UnitUtils.convert(quantity, product.getUnit(), product.getUnit()));
        //When
        BigDecimal result = pricingService.getOrderTotal(order);
        //Then
        assertThat(result, Matchers.comparesEqualTo(new BigDecimal(15)));
    }

    @Test
    public void should_calculate_order_price_for_quantified_product() throws Exception {
        //Given
        Order order = new Order();
        QuantifiedProduct product = getQuantifiedProduct();
        int quantity = 2;
        order.addProduct(product, (int) UnitUtils.convert(quantity, product.getUnit(), product.getUnit()));
        //When
        BigDecimal result = pricingService.getOrderTotal(order);
        //Then
        assertThat(result, Matchers.comparesEqualTo(new BigDecimal(20)));
    }

    @Test
    public void should_calculate_order_price_in_different_unit() throws Exception {
        //Given
        WeightedProduct productWithUnit = new WeightedProduct();
        productWithUnit.setName(PRODUCT_NAME_A);
        productWithUnit.setUnit(PRODUCT_UNIT_POUND);
        BigDecimal priceOfOnePound = new BigDecimal(16);
        productWithUnit.setPrice(priceOfOnePound);
        Order order = new Order();
        double quantityInOunce = 4;
        order.addProduct(productWithUnit, UnitUtils.convert(quantityInOunce, PRODUCT_UNIT_OUNCE, productWithUnit.getUnit()));
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
        WeightedProduct product = getWeightedProduct();
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
        WeightedProduct product = getWeightedProduct();
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
        QuantifiedProduct product = getQuantifiedProduct();
        product.setPrice(new BigDecimal(1));
        product.addStrategy(new BuyYGetXForFree(quantityBought, quantityOffered));
        order.addProduct(product, (int) UnitUtils.convert(quantityBought + quantityOffered, product.getUnit(), product.getUnit()));
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
        QuantifiedProduct product = getQuantifiedProduct();
        product.setPrice(new BigDecimal(1));
        product.addStrategy(new BuyYGetXForFree(quantityBought, quantityOffered));
        order.addProduct(product, (int) UnitUtils.convert(quantitySoled, product.getUnit(), product.getUnit()));

        //When
        BigDecimal resultPrice = pricingService.getOrderTotal(order);
        //Then
        assertThat(resultPrice, Matchers.comparesEqualTo(new BigDecimal(2)));
    }
}
