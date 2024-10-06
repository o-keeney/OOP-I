import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.List;
import Factory.Car;
import Factory.Customer;
import Factory.Enums.ProductionStatus;
import Factory.Order;

// Interface example
public class CarFactory implements ICarFactory
{
    private String factoryName;
    private int capacity;
    private List<Car> cars = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private int currentStockLevel;

    public CarFactory(String factoryName, int capacity, int currentStockLevel)
    {
        this.factoryName = factoryName;
        this.capacity = capacity;
        this.currentStockLevel = currentStockLevel;
    }

    // Primary constructor - uses this() to assign name and and default value for capacity
    public CarFactory(String factoryName)
    {
        this(factoryName, 5, 0);
    }

    public void placeOrder(Customer customer, Car car)
    {
        if (!cars.contains(car))
        {
            throw new FactoryEmptyException("Car is not available in stock...");
        }
        cars.remove(car);
        currentStockLevel--;
        orders.add(new Order(customer, car));
        System.out.println("Order placed: " + customer.name() + " - " + car.toString());
    }

    public List<Order> getOrderHistory()
    {
        return orders;
    }

    public List<Car> getAllCars()
    {
        return cars;
    }

    public int getCurrentStockLevel(){
        return currentStockLevel;
    }

    public List<Car> searchCarsByMake(String make)
    {
        // Define a predicate to filter cars by the make property
        Predicate<Car> byMake = car -> car.getMake().equalsIgnoreCase(make);

        // Use stream with the predicate to filter and collect matching cars
        return cars.stream()
                .filter(byMake)
                .collect(Collectors.toList());
    }

    public void addCar(Car car) throws FactoryFullException
    {
        if (cars.size() >= capacity)
        {
            throw new FactoryFullException("Factory at capacity...");
        }

        cars.add(car);
        currentStockLevel++;
        System.out.println(car.toString() + " added to " + factoryName);
    }

    public ProductionStatus getProductionStatus()
    {
        if (cars.size() < capacity)
        {
            return ProductionStatus.ACTIVE;
        }
        else
        {
            return ProductionStatus.FULL;
        }
    }
}
