import java.util.ArrayList;
import java.util.List;
import Factory.Car;
import Factory.Enums.ProductionStatus;

// Interface example
public class CarFactory implements ICarFactory
{
    private String factoryName;
    private int capacity;
    private List<Car> cars = new ArrayList<>();
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

    public List<Car> getAllCars()
    {
        return cars;
    }

    public int getCurrentStockLevel(){
        return currentStockLevel;
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
