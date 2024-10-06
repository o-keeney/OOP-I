import Factory.Car;
import Factory.Customer;
import Factory.Enums.ProductionStatus;
import Factory.Order;

import java.util.List;

public interface ICarFactory
{
    void addCars (Car... carsList) throws FactoryFullException;
    List<Car> getAllCars();
    ProductionStatus getProductionStatus();
    List<Car> searchCarsByMake(String make);
    List<Order> getOrderHistory();
    void placeOrder(Customer customer, Car car);
}
