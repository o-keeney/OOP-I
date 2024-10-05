package Factory;

final class SUV extends Car
{
    private int numberOfSeats;

    public SUV(String make, String model, int year, int numberOfSeats)
    {
        super(make, model, year);
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats()
    {
        return numberOfSeats;
    }

    @Override
    public String toString()
    {
        return super.toString() + " - Seating Capacity: " + numberOfSeats;
    }
}
