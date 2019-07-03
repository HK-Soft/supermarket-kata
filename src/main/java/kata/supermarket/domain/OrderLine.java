package kata.supermarket.domain;

import java.util.Objects;

public class OrderLine {

    private Product product;

    private double quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return product.equals(orderLine.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}
