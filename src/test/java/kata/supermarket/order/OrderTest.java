package kata.supermarket.order;

import kata.supermarket.core.Order;
import kata.supermarket.core.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OrderTest {

    private static final String PRODUCT_NAME_A = "AAAAA";
    private static final BigDecimal PRODUCT_PRICE_A = new BigDecimal(10);
    private static final int PRODUCT_PRICE_QUANTITY_A = 3;
    private static Product product;

    @BeforeAll
    public static void setup() {
        product = new Product();
        product.setName(PRODUCT_NAME_A);
        product.setPrice(PRODUCT_PRICE_A);
    }

    @Test
    public void should_add_product_to_order() {
        //Given
        Order order = new Order();
        int sizeBefore = order.getOrderLines().size();
        //When
        order.addProduct(product, PRODUCT_PRICE_QUANTITY_A);
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
        order.addProduct(product, PRODUCT_PRICE_QUANTITY_A);
        order.addProduct(product, PRODUCT_PRICE_QUANTITY_A);
        //Then
        Assertions.assertEquals(order.getOrderLines().size(), sizeBefore + 1);
        Assertions.assertEquals(order.getOrderLines().get(sizeBefore).getQuantity(),
                (PRODUCT_PRICE_QUANTITY_A + PRODUCT_PRICE_QUANTITY_A));

    }


}
