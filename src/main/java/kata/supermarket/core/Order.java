package kata.supermarket.core;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<OrderLine> orderLines;

    public Order() {
        orderLines = new ArrayList<>();
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public void addProduct(Product product, double quantity) {
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
