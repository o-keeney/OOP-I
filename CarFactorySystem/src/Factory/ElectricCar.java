package Factory;

final class ElectricCar extends Car
{
    private int rangeInMiles;

    public ElectricCar(String make, String model, int year, int rangeInMiles)
    {
        super(make, model, year);
        this.rangeInMiles = rangeInMiles;
    }

    public int getRangeInMiles()
    {
        return rangeInMiles;
    }

    @Override
    public String toString()
    {
        return super.toString() + " - Range (Miles): " + rangeInMiles;
    }
}
