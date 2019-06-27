package kata.supermarket.pricing;

import kata.supermarket.core.Order;

import java.math.BigDecimal;

public class PricingService {

    public BigDecimal getOrderTotal(Order order) {
        return order.getProduct().getPrice().multiply(new BigDecimal(order.getQuantity()));
    }
}
