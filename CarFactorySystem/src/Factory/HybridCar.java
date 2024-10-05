package Factory;

public final class HybridCar extends Car
{
    private int milesPerKwh;

    public HybridCar (String make, String model, int year, int milesPerKwh)
    {
        super(make, model, year);
        this.milesPerKwh = milesPerKwh;
    }

    public int getMilesPerKwh() {
        return milesPerKwh;
    }

    @Override
    public String toString()
    {
        return super.toString() + " - Range (Miles per Kwh): " + milesPerKwh;
    }
}
