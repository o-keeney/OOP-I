import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import Factory.*;
import Factory.Enums.MembershipType;
import Factory.Enums.ProductionStatus;

public class Main
{
    private static final CarFactory carFactory = new CarFactory("SuperCars Factory");

    public static void main(String[] args) throws FactoryFullException
    {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do
        {
            System.out.println("\nCar Factory Menu:");
            System.out.println("1. Add Car(s)");
            System.out.println("2. View All Cars");
            System.out.println("3. Get Production Status");
            System.out.println("4. Search Factory Inventory By Make");
            System.out.println("5. Run Demo");
            System.out.println("6. Place Order");
            System.out.println("7. Get Order History");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice)
            {
                case 1:
                    if (carFactory.getProductionStatus() == ProductionStatus.FULL)
                    {
                        throw new FactoryFullException("Factory at capacity...");
                    }
                    else
                    {
                        addCars(scanner);
                    }
                    break;
                case 2:
                    if (carFactory.getCurrentStockLevel() == 0)
                    {
                        System.out.println("Factory is empty...");
                    }
                    else
                    {
                        System.out.println("Cars produced: ");
                        var allCars = carFactory.getAllCars();
                        for (Car car : allCars)
                        {
                            System.out.println(car.toString());
                        }
                    }
                    break;
                case 3:
                    System.out.println("Production Status: " + carFactory.getProductionStatus());
                    break;
                case 4:
                    System.out.println("Searching factory inventory...");
                    searchFactoryInventoryByMake(scanner);
                    break;
                case 5:
                    System.out.println("Running demo...");
                    demoSuite();
                    break;
                case 6:
                    System.out.println("Placing an order...");
                    placeOrder(scanner);
                    break;
                case 7:
                    System.out.println("Order History");
                    var allOrders = carFactory.getOrderHistory();
                    for (Order order : allOrders)
                    {
                        System.out.println(order.toString());
                    }
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        } while (choice != 8);

        // Close scanner to prevent resource leak
        scanner.close();
    }

    private static void placeOrder(Scanner scanner)
    {
        // Get customer details
        System.out.print("Enter customer name: ");
        scanner.nextLine();
        String customerName = scanner.nextLine();
        Customer customer = new Customer(customerName, MembershipType.PRIVATE);

        // Display available cars for ordering
        System.out.println("Select a car number from menu: ");
        List<Car> availableCars = carFactory.getAllCars();
        for (int i = 0; i < availableCars.size(); i++) {
            System.out.println((i + 1) + ". " + availableCars.get(i).toString());
        }

        int carChoice = scanner.nextInt();
        Car selectedCar = availableCars.get(carChoice - 1);

        // Place the order
        try
        {
            carFactory.placeOrder(customer, selectedCar);
        }
        catch (FactoryEmptyException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchFactoryInventoryByMake(Scanner scanner)
    {
        System.out.println("Enter car make: ");
        scanner.nextLine();
        String userChoice = scanner.nextLine();
        var carList = carFactory.searchCarsByMake(userChoice);
        if (!carList.isEmpty())
        {
            for (Car car : carList)
            {
                System.out.println(car.toString());
            }
        }
    }

    private static void addCars(Scanner scanner)
    {
        int choice;
        List<Car> carsToAdd = new ArrayList<>();
        do
        {
            System.out.println("\nSelect Car Type:");
            System.out.println("1. Sports Car");
            System.out.println("2. SUV");
            System.out.println("3. Electric Car");
            System.out.println("4. Hybrid Car");
            System.out.println("5. Finished Adding Cars");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            Car car = null;
            String make, model;
            int year;

            switch (choice) {
                case 1:
                    System.out.print("Enter make: ");
                    make = scanner.nextLine();
                    System.out.print("Enter model: ");
                    model = scanner.nextLine();
                    System.out.print("Enter year: ");
                    year = scanner.nextInt();
                    System.out.print("Enter horsepower: ");
                    int horsepower = scanner.nextInt();
                    car = new SportsCar(make, model, year, horsepower);
                    break;
                case 2:
                    System.out.print("Enter make: ");
                    make = scanner.nextLine();
                    System.out.print("Enter model: ");
                    model = scanner.nextLine();
                    System.out.print("Enter year: ");
                    year = scanner.nextInt();
                    System.out.println("Seating Capacity: ");
                    var seatingCapacity = scanner.nextInt();
                    car = new SUV(make, model, year, seatingCapacity);
                    break;
                case 3:
                    System.out.print("Enter make: ");
                    make = scanner.nextLine();
                    System.out.print("Enter model: ");
                    model = scanner.nextLine();
                    System.out.print("Enter year: ");
                    year = scanner.nextInt();
                    System.out.println("Range (miles): ");
                    var rangeInMiles = scanner.nextInt();
                    car = new ElectricCar(make, model, year, rangeInMiles);
                    break;
                case 4:
                    System.out.print("Enter make: ");
                    make = scanner.nextLine();
                    System.out.print("Enter model: ");
                    model = scanner.nextLine();
                    System.out.print("Enter year: ");
                    year = scanner.nextInt();
                    System.out.println("Range (Miles per kwh): ");
                    var milesPerKwh = scanner.nextInt();
                    car = new HybridCar(make, model, year, milesPerKwh);
                    break;
                case 5:
                    System.out.println("Finalising list...");
                    break;
                default:
                    System.out.println("Invalid car type selected.");
                    return; // Exit this method
            }

            if (car != null)
            {
                carsToAdd.add(car);
                System.out.println(car + " has been added to the list");
            }
        }
        while (choice != 5);

        try
        {
            Main.carFactory.addCarsWithValidation(carsToAdd.toArray(new Car[0]));
        }
        catch (FactoryFullException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void demoSuite() throws FactoryFullException
    {
        //LVTI examples
        var car1 = new ElectricCar("Tesla", "S", 2020, 200);
        var car2 = new SUV("Range Rover", "Evoque", 2021, 5);

        // Declaring factory object
        CarFactory factory = new CarFactory("General Motors", 3, 0);

        // Add cars to factory
        factory.addCars(car1);
        factory.addCars(car2);

        // Iterate over car list and print information
        var allCars = factory.getAllCars();
        for (Car car : allCars)
        {
            System.out.println(car.toString());
        }

        //Polymorphism example
        Car sportsCar = new SportsCar("Ferrari", "F150", 1980, 550);
        factory.addCars(sportsCar);

        // Lambda expression using predicate
        Predicate<Car> isHighPerformance = c -> c instanceof SportsCar && ((SportsCar) c).getHorsePower() > 500;
        System.out.println(car1 + " - High Performance ? " + isHighPerformance.test(car1));
        System.out.println(sportsCar + " - High Performance ? " + isHighPerformance.test(sportsCar));

        //Default interface method
        carFactory.showCurrentCapacity(carFactory.getAllCars().size(), carFactory.getCapacity());

        // Check factory production status - enum example
        System.out.println("Factory production status: " + factory.getProductionStatus());

        // Exception handling example
        try
        {
            factory.addCars(new HybridCar("Toyota", "Prius", 2015, 150));
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

