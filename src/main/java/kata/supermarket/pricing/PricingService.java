package kata.supermarket.pricing;

import kata.supermarket.core.Order;
import kata.supermarket.core.OrderLine;

import java.math.BigDecimal;

public class PricingService {

    public BigDecimal getOrderTotal(Order order) {
        BigDecimal total = new BigDecimal(0);
        for (OrderLine orderLine : order.getOrderLines())
            total = total.add(orderLine.getProduct().getPrice().multiply(new BigDecimal(orderLine.getQuantity())));
        return total;
    }
}
