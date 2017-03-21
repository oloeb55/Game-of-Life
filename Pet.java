public class Pet
{
    private String petName;
    private int petCost;
    public Pet(String petName, int petCost)
    {
        this.petName = petName;
        this.petCost = petCost;
    }
    public String getPetName()
    {
        return petName;
    }
    public int getPetCost()
    {
        return petCost;
    }
}
