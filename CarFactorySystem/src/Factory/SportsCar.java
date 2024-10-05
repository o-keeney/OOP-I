package Factory;

public final class SportsCar extends Car
{
    private int horsePower;

    public SportsCar (String make, String model, int year, int horsePower)
    {
        super(make, model, year);
        this.horsePower = horsePower;
    }

    public int getHorsePower ()
    {
        return horsePower;
    }

    @Override
    public String toString()
    {
        return super.toString() + " - HP: " + horsePower;
    }
}
