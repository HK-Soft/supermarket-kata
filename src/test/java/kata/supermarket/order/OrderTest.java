package kata.supermarket.order;

import kata.supermarket.Utils.UnitUtils;
import kata.supermarket.core.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OrderTest {

    private static final String PRODUCT_NAME_A = "AAAAA";
    private static final BigDecimal PRODUCT_PRICE_A = new BigDecimal(10);
    private static final int PRODUCT_PRICE_QUANTITY_A = 3;

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
    public void should_add_weighted_product_to_order() {
        //Given
        Order order = new Order();
        int sizeBefore = order.getOrderLines().size();
        //When
        order.addProduct(getWeightedProduct(), PRODUCT_PRICE_QUANTITY_A);
        //Then
        Assertions.assertEquals(order.getOrderLines().size(), sizeBefore + 1);
        Assertions.assertEquals(order.getOrderLines().get(sizeBefore).getQuantity(), PRODUCT_PRICE_QUANTITY_A);
    }

    @Test
    public void should_add_quantified_product_to_order() {
        //Given
        Order order = new Order();
        int sizeBefore = order.getOrderLines().size();
        //When
        order.addProduct(getQuantifiedProduct(), PRODUCT_PRICE_QUANTITY_A);
        //Then
        Assertions.assertEquals(order.getOrderLines().size(), sizeBefore + 1);
        Assertions.assertEquals(order.getOrderLines().get(sizeBefore).getQuantity(), PRODUCT_PRICE_QUANTITY_A);
    }

    @Test
    public void should_update_quantity_when_adding_exiting_product() {
        //Given
        Order order = new Order();
        int sizeBefore = order.getOrderLines().size();
        //When
        order.addProduct(getWeightedProduct(), PRODUCT_PRICE_QUANTITY_A);
        order.addProduct(getWeightedProduct(), PRODUCT_PRICE_QUANTITY_A);
        //Then
        Assertions.assertEquals(order.getOrderLines().size(), sizeBefore + 1);
        Assertions.assertEquals(order.getOrderLines().get(sizeBefore).getQuantity(),
                (PRODUCT_PRICE_QUANTITY_A + PRODUCT_PRICE_QUANTITY_A));

    }

}
