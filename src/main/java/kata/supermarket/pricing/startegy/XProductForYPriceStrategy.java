package kata.supermarket.pricing.startegy;

import kata.supermarket.domain.Product;

import java.math.BigDecimal;

public class XProductForYPriceStrategy implements PriceStrategy {

    private final static String STRATEGY_NAME = "X_PRODUCT_FOR_Y_PRICE_PRICING_STRATEGY";

    private double productQuantity;
    private BigDecimal price;

    public XProductForYPriceStrategy(double productQuantity, BigDecimal price) {
        this.productQuantity = productQuantity;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.STRATEGY_NAME;
    }


    @Override
    public BigDecimal apply(Product product, double quantity) {
        double result = (int) (quantity / productQuantity);
        double rest = (quantity - (result * productQuantity));
        return price.multiply(new BigDecimal((result)).add(product.getPrice().multiply(new BigDecimal(rest))));
    }
}
