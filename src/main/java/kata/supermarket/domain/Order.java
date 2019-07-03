package kata.supermarket.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.Set;

public class Order {

    private Set<OrderLine> orderLines;

    public Order() {
        orderLines = new HashSet<OrderLine>();
    }

    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Set<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public void addProduct(WeightedProduct product, double quantity) {
        System.out.println(product + " " + "add weighted product");
        this.addProduct((Product) product, quantity);
    }

    public void addProduct(QuantifiedProduct product, int quantity) {
        System.out.println(product + " " + "add quantified product");
        this.addProduct((Product) product, quantity);
    }

    private void addProduct(Product product, double quantity) {
        for (OrderLine orderLine : orderLines) {
            if (orderLine.getProduct().equals(product)) {
                orderLine.setQuantity(orderLine.getQuantity() + quantity);
                return;
            }
        }
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(quantity);
        orderLine.setProduct(product);
        orderLines.add(orderLine);

    }
}
