import Factory.Car;
import Factory.Customer;
import Factory.Enums.ProductionStatus;
import Factory.Order;

import java.util.List;

public interface ICarFactory
{
    void addCars (Car... carsList) throws FactoryFullException;
    List<Car> getAllCars();
    default void showCurrentCapacity(int currentStock, int maxCapacity)
    {
        System.out.println("Current stock: " + currentStock + "/" + maxCapacity);
    }
    static String factoryInfo()
    {
        return "This is a car factory for producing various types of cars.";
    }
    private boolean isValidCarData(Car car)
    {
        return car.getMake() != null && !car.getMake().isEmpty() &&
                car.getModel() != null && !car.getModel().isEmpty() &&
                car.getYear() > 0;
    }
    default void addCarsWithValidation(Car... carsList) throws FactoryFullException
    {
        for (Car car : carsList)
        {
            if (isValidCarData(car))
            {
                addCars(car);
            }
            else
            {
                System.out.println("Invalid car data. Cannot add");
            }
        }
    }
    ProductionStatus getProductionStatus();
    List<Car> searchCarsByMake(String make);
    List<Order> getOrderHistory();
    void placeOrder(Customer customer, Car car);
}
