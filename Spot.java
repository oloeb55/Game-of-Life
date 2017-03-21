public class Spot
{
    private boolean mustStop;
    private boolean payDay;
    private int spotType;
    public Spot(int spotType)
    {
        this.spotType = spotType;
        switch(spotType)
        {
            case 0: this.mustStop = false; // Normal Spot, nothing happens
            this.payDay = false;
            break;
            case 3: this.mustStop = true; // Pay Day, get payed
            this.payDay = true;
            break;
            case 4: this.mustStop = true; // Choose a job, only happens once at location [9]
            this.payDay = false;
            break;
            case 5: this.mustStop = true; // Choose a pet at [33]
            this.payDay = false;
            break;
            case 6: this.mustStop = true; // Choose a house at [66]
            this.payDay = false;
            break;
            case 1: this.mustStop = false; // Random event, one of a couple random things will happen
            this.payDay = false;
            break;
            case 7: this.mustStop = true; // End, game over on [99]
            this.payDay = false;
            break;
            case 2: this.mustStop = false; // Lawsuit, take money, but you can also lose money
            this.payDay = false;
            break;
        }
    }
    public int getSpotType()
    {
        return spotType;
    }
    public boolean getMustStop()
    {
        return mustStop;
    }
    public boolean getPayDay()
    {
        return payDay;
    }
    public String toString()
    {
        return "" + mustStop + " " + spotType + " " + " " + payDay;
    }
}
