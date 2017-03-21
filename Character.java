public class Character
{
    private String name;
    private int money;
    private int location;
    private int randomEvent;
    private int numberKids;
    public Character(String name, int money)
    {
        this.name = name;
        this.money = money;
        this.location = 0;
        this.numberKids = 0;
    }

    public int getMoney()
    {
        return money;
    }

    public String getName()
    {
        return name;
    }

    public void addMoney(int a)
    {
        this.money += a;
    }

    public void removeMoney(int a)
    {
        this.money -= a;
    }

    public void move(int a)
    {
        this.location += a;
    }

    public int getLocation()
    {
        return location;
    }

    public int getNumberKids()
    {
        return numberKids;
    }

    public void randomEvent(Spot board[])
    {
        randomEvent = (int) (Math.random() * 11);
        int randomTaxes = (int) ((Math.random() * 10) + 1) * 10000;
        switch(randomEvent)
        {
            case 0:
            System.out.println("The government has taken " + randomTaxes + " in taxes from " + getName());
            removeMoney(randomTaxes);
            break;
            case 1:
            System.out.println(getName() + " has won " + randomTaxes + " money on a gameshow");
            addMoney(randomTaxes);
            break;
            case 2:
            System.out.println(getName() + " has received a 50000 dollar bonus");
            addMoney(50000);
            break;
            case 3:
            System.out.println(getName() + " has been caught stealing a magazinge, and lost 1000 dollars");
            removeMoney(1000);
            break;
            case 4:
            System.out.println(getName() + " has been caught robbing a bank, and lost all of his/her money");
            if(getMoney() > 0)
            {
                removeMoney(getMoney());
            }
            break;
            case 5:
            System.out.println(getName() + " has narrowly avoided a terrible car accident");
            break;
            case 6:
            System.out.println(getName() + " has gotten in a terrible car accident and must pay for repairs");
            removeMoney(100000);
            break;
            case 7:
            System.out.println("It's " + getName() + "'s birthday, and he/she received presents");
            addMoney(randomTaxes);
            break;
            case 8:
            System.out.println(getName() + " had a kid");
            numberKids++;
            break;
            case 9:
            if(numberKids > 0)
            {
                System.out.println(getName() + ", one of your kids has died");
                numberKids--;
            }
            break;
            case 10:
            if(numberKids > 0)
            {
                System.out.println(getName() + ", you must pay your kids' college tuition");
                removeMoney(numberKids * 100000);
            }
            break;
        }
    }
}
