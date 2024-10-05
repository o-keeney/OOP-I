import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.function.Predicate;
import Factory.*;
import Factory.Enums.MembershipType;

public class Main
{
    public static void main(String[] args) throws FactoryFullException {
        //LVTI examples
        var car1 = new ElectricCar("Tesla", "S", 2020, 200);
        var car2 = new SUV("Range Rover", "Evoque", 2021, 5);

        // Declaring factory object
        CarFactory factory = new CarFactory("General Motors", 3);

        // Add cars to factory
        factory.addCar(car1);
        factory.addCar(car2);

        // Iterate over car list and print information
        var allCars = factory.getAllCars();
        for (Car car : allCars)
        {
            System.out.println(car.toString());
        }

        //Polymorphism example
        Car sportsCar = new SportsCar("Ferarri", "F150", 1980, 550);
        factory.addCar(sportsCar);

        // Lambda expression using predicate
        Predicate<Car> isHighPerformance = c -> c instanceof SportsCar && ((SportsCar) c).getHorsePower() > 500;
        System.out.println(car1.toString() + " - High Performance ? " + isHighPerformance.test(car1));
        System.out.println(sportsCar.toString() + " - High Performance ? " + isHighPerformance.test(sportsCar));

        // Check factory production status - enum example
        System.out.println("Factory production status: " + factory.getProductionStatus());

        // Exception handling example
        try
        {
            factory.addCar(new HybridCar("Toyota", "Prius", 2015, 150));
        }
        catch (FactoryFullException ex)
        {
            System.out.println(ex.getMessage());
        }

        // Records, Switch expressions and pattern matching examples
        var customer = new Customer("Joe Soap", MembershipType.PRIVATE);
        switch (customer.membershipType())
        {
            case MembershipType.PRIVATE -> System.out.println(customer.name() + " is a private customer");
            case MembershipType.COMMERCIAL -> System.out.println(customer.name() + "is a commercial client");
            default -> System.out.println("Invalid membership");
        }
    }
}