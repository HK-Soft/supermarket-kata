package kata.supermarket.pricing.startegy;

import kata.supermarket.domain.Product;

import java.math.BigDecimal;

public interface PriceStrategy {

    String getName();

    BigDecimal apply(Product product, double quantity);

}
