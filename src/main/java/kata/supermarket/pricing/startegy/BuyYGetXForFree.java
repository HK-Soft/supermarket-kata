package kata.supermarket.pricing.startegy;

import kata.supermarket.domain.Product;

import java.math.BigDecimal;

public class BuyYGetXForFree implements PriceStrategy {

    private final static String STRATEGY_NAME = "X_BOUGHT_Y_OFFERED_STRATEGY";

    private double quantityBought ;
    private double quantityOffered ;

    public BuyYGetXForFree(double quantityBought,double quantityOffered){
        this.quantityBought = quantityBought;
        this.quantityOffered = quantityOffered;
    }

    @Override
    public String getName() {
        return STRATEGY_NAME;
    }

    @Override
    public BigDecimal apply(Product product, double quantity) {
        double totalQuantity = this.quantityBought + this.quantityOffered;
        BigDecimal result = new BigDecimal(0);
        double rest = quantity;
        while ((rest - totalQuantity) >= 0) {
            result = result.add(product.getPrice().multiply(new BigDecimal(quantityBought)));
            rest -= totalQuantity;
        }
        result.add(product.getPrice().multiply(new BigDecimal(rest)));
        return result;
    }
}
