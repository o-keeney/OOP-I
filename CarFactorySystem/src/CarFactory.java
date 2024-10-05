import java.util.ArrayList;
import java.util.List;
import Factory.Car;
import Factory.Enums.ProductionStatus;

public class CarFactory
{
    private String factoryName;
    private int capacity;
    private List<Car> cars = new ArrayList<>();

    public CarFactory(String factoryName, int capacity)
    {
        this.factoryName = factoryName;
        this.capacity = capacity;
    }

    // Primary constructor - uses this() to assign value to factory
    // while assigning default value to capacity
    public CarFactory(String factoryName)
    {
        this(factoryName, 5);
    }

    public List<Car> getAllCars()
    {
        return cars;
    }

    public void addCar(Car car) throws FactoryFullException
    {
        if (cars.size() >= capacity)
        {
            throw new FactoryFullException("Factory at capacity...");
        }

        cars.add(car);
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
