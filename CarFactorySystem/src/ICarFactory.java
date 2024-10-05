import Factory.Car;
import Factory.Enums.ProductionStatus;

import java.util.List;

public interface ICarFactory
{
    void addCar (Car car) throws FactoryFullException;
    List<Car> getAllCars();
    ProductionStatus getProductionStatus();
}
