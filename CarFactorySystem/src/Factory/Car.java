package Factory;

// Fundamentals demonstrated: Inheritance, Overriding, Encapsulation, Sealed Class
public sealed class Car
permits SportsCar, SUV, ElectricCar, HybridCar
{
    private String make;
    private String model;
    private int year;

    public Car(String make, String model, int year)
    {
        // The 'this' keyword refers to the current object in a method or constructor.
        // The most common use of the 'this' keyword is to eliminate the confusion between class
        // attributes and parameters with the same name

        //this. keyword is used to refer to the current object, i.e. through which the method is called.
        //this() is used to call one constructor from the other of the same class.
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake()
    {
        return make;
    }

    public String getModel()
    {
        return model;
    }

    public int getYear()
    {
        return year;
    }

    // Override in parent class because all objects implicitly extend toString() from Object class
    @Override
    public String toString()
    {
        return make + " - " + model + " (" + year + ")";
    }
}
