package kata.supermarket.core;


import kata.supermarket.core.Exception.StrategyAlreadyAssigned;
import kata.supermarket.pricing.startegy.PriceStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Product {

    private String name;
    private BigDecimal price;
    private Unit unit;

    private List<PriceStrategy> priceStrategies = new ArrayList<>();

    public Product() {
        this.unit = new Unit();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<PriceStrategy> getPriceStrategies() {
        return priceStrategies;
    }

    public void addStrategy(PriceStrategy priceStrategy) throws StrategyAlreadyAssigned {
        for (PriceStrategy strategy : priceStrategies)
            if (strategy.getName().equals(strategy))
                throw new StrategyAlreadyAssigned();
        this.priceStrategies.add(priceStrategy);
    }

    public void removeStrategy(PriceStrategy priceStrategy) {
        this.priceStrategies.remove(priceStrategy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
