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

    public void addProduct(Product product,int quantity){
        OrderLine orderLine = new OrderLine();
        orderLine.setProduct(product);
        orderLine.setQuantity(quantity);
        this.orderLines.add(orderLine);
    }
}
