package Factory;

import java.time.LocalDate;

public class Order
{
    private final Customer customer;
    private final Car car;
    private final LocalDate orderDate;

    public Order(Customer customer, Car car)
    {
        this.customer = customer;
        this.car = car;
        this.orderDate = LocalDate.now(); // Order date is set to the current date
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public Car getCar()
    {
        return car;
    }

    public LocalDate getOrderDate()
    {
        return orderDate;
    }

    @Override
    public String toString()
    {
        return "Order placed by " + customer.name() + " for " + car + " on " + orderDate;
    }
}
