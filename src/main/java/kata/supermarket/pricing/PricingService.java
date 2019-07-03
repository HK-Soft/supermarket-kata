package kata.supermarket.pricing;

import kata.supermarket.domain.Order;
import kata.supermarket.domain.OrderLine;
import kata.supermarket.pricing.startegy.PriceStrategy;
import kata.supermarket.pricing.startegy.SimplePriceStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PricingService {

    private static final PriceStrategy DEFAULT_PRICING_STRATEGY = new SimplePriceStrategy();

    public BigDecimal getOrderTotal(Order order) {
        BigDecimal total = new BigDecimal(0);

        for (final OrderLine orderLine : order.getOrderLines()) {
            List<PriceStrategy> strategies = new ArrayList<>(orderLine.getProduct().getPriceStrategies());
            strategies.add(DEFAULT_PRICING_STRATEGY);
            BigDecimal price = strategies.stream()
                    .min(Comparator.comparing(strategy -> strategy.apply(orderLine.getProduct(), orderLine.getQuantity())))
                    .get()
                    .apply(orderLine.getProduct(), orderLine.getQuantity()); // BigDecimal;
            total = total.add(price);
        }
        return total;
    }
}
