public class House
{
    private String houseType;
    private int houseCost;
    public House(String houseType, int houseCost)
    {
        this.houseType = houseType;
        this.houseCost = houseCost;
    }
    public int getHouseCost()
    {
        return houseCost;
    }
    public String getHouseType()
    {
        return houseType;
    }
}