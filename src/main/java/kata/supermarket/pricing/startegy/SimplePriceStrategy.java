package kata.supermarket.pricing.startegy;

import kata.supermarket.domain.Product;

import java.math.BigDecimal;
import java.util.Objects;

public class SimplePriceStrategy implements PriceStrategy {

    private final static String STRATEGY_NAME = "SIMPLE_PRICING_STRATEGY";

    @Override
    public String getName() {
        return this.STRATEGY_NAME;
    }

    @Override
    public BigDecimal apply(Product product, double quantity) {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplePriceStrategy simplePriceStrategy = (SimplePriceStrategy) o;
        return getName().equals(simplePriceStrategy.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "PRICING STRATEGY : " + STRATEGY_NAME;
    }
}
