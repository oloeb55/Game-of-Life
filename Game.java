import javax.swing.*;
import java.util.*;
public class Game
{
    Job jobList[] = new Job[10];
    public void createJobList()
    {
        jobList[0] = new Job(false, 2000, "Hobo");
        jobList[1] = new Job(false, 10000, "Janitor");
        jobList[2] = new Job(false, 20000, "Waiter");
        jobList[3] = new Job(false, 30000, "Chef");
        jobList[4] = new Job(false, 50000, "Gym Teacher");
        jobList[5] = new Job(true, 50000, "Manager");
        jobList[6] = new Job(true, 60000, "Physicist");
        jobList[7] = new Job(true, 90000, "Mechanical Engineer");
        jobList[8] = new Job(true, 100000, "Software Developer");
        jobList[9] = new Job(true, 120000, "Doctor");
    }
    House houseList[] = new House[6];
    public void createHouseList()
    {
        houseList[0] = new House("Mobile Home", 10000);
        houseList[1] = new House("Apartment", 20000);
        houseList[2] = new House("Suburban House", 30000);
        houseList[3] = new House("Mansion", 50000);
        houseList[4] = new House("Personal City", 100000);
        houseList[5] = new House("Colony on Mars", 200000);
    }
    Pet petList[] = new Pet[6];
    public void createPetList()
    {
        petList[0] = new Pet("Mini-alaskan Bull Worm", 200);
        petList[1] = new Pet("Goldfish", 1000);
        petList[2] = new Pet("Cat", 20000);
        petList[3] = new Pet("Dog", 30000);
        petList[4] = new Pet("Ferret", 50000);
        petList[5] = new Pet("Komodo Dragon", 100000);
    }

    public void play()
    {
        Character player1 = new Character(JOptionPane.showInputDialog("Welcome to the Game of Life! Please enter your name"), 20000);
        Character opponent = new Character("Opponent", 20000);

        boolean gameOver = false;
        boolean inputMoneyLawsuit = false;
        double lawsuitChance = 0.0;
        int numberRandoms = 0;
        int numberSuits = 21;
        int numLawsuit = 0;
        int numberPayDays = 0;
        int player1Job = 0;
        int player1Pet = 0;
        int player1House = 0;
        boolean player1College;
        boolean player2College;
        int opponentJob = 0;
        int opponentPet = 0;
        int opponentHouse = 0;
        int turn = 0;
        int computerChoice = 0;
        boolean validInput;
        boolean player1Finished = false;
        boolean opponentFinished = false;
        int spinner = 0;
        Scanner input = new Scanner(System.in);
        String buffer;
        String lawsuitChoice;

        createJobList();
        createHouseList();
        createPetList();

        Spot board[] = new Spot[100];
        int randomSpotType;
        while((numberPayDays < 7 || numberPayDays > 12) || numberSuits > 20 || numberSuits < 10 || numberRandoms > 80 || numberRandoms < 40)
        {
            numberPayDays = 0;
            numberRandoms = 0;
            numberSuits = 0;
            for(int i = 0; i < 10; i++)
            {
                randomSpotType = (int) (Math.random() * 3);
                if(randomSpotType == 1)
                {
                    numberRandoms++;
                }
                else if(randomSpotType == 2)
                {
                    numberSuits++;
                }
                board[i] = new Spot(randomSpotType);
            }
            for(int i = 10; i < board.length; i++)
            {
                randomSpotType = (int) (Math.random() * 4);
                if(randomSpotType == 3)
                {
                    numberPayDays++;
                }
                else if(randomSpotType == 1)
                {
                    numberRandoms++;
                }
                else if(randomSpotType == 2)
                {
                    numberSuits++;
                }
                board[i] = new Spot(randomSpotType);
            } 
        }
        board[10] = new Spot(4);
        board[33] = new Spot(5);
        board[66] = new Spot(6);
        board[99] = new Spot(7);
        while(gameOver == false && player1Finished == false && opponentFinished == false)
        {
            System.out.println();
            System.out.println("Turn " + turn);
            System.out.println(player1.getName() + " is at location " + player1.getLocation() + " out of 99, and has " + player1.getMoney() + " money.");
            System.out.println(opponent.getName() + " is at location " + opponent.getLocation() + " out of 99, and has " + opponent.getMoney() + " money.");
            if(turn == 0)
            {
                String choiceCollege = JOptionPane.showInputDialog(player1.getName() + ", would you like to go to college? (Yes/No)");
                validInput = false;
                while(validInput == false)
                {
                    if(choiceCollege.equalsIgnoreCase("Yes"))
                    {
                        player1.removeMoney(100000);
                        System.out.println(player1.getName() + " has spent 100000 dollars on college");
                        validInput = true;
                        player1College = true;
                    }
                    else if(choiceCollege.equalsIgnoreCase("No"))
                    {
                        player1.move(10);
                        System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
                        System.out.println();
                        System.out.println(player1.getName() + " has chosen to skip college and immidietly get a job");
                        player1Job = (int) (Math.random() * 5);
                        System.out.println("Congratulations! " + player1.getName() + " is a " + jobList[player1Job].getTitle() + ", and makes " + jobList[player1Job].getSalary() + " dollars when paid.");
                        validInput = true;
                        player1College = false;
                    }
                    else
                    {
                        choiceCollege = JOptionPane.showInputDialog("Invalid responce. " + player1.getName() + ", would you like to go to college? (Yes/No)");
                    }
                }
                computerChoice = (int) (Math.random() * 2);
                if(computerChoice == 0)
                {
                    opponent.move(10);
                    System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
                    System.out.println(opponent.getName() + " has chosen to skip college and immidietly get a job");
                }
                else if(computerChoice == 1)
                {
                    opponent.removeMoney(100000);
                    System.out.println(opponent.getName() + " has spent 100000 dollars on college");
                }
                turn++;
            }
            System.out.println(player1.getName() + ", please enter anything to spin the spinner (Results: 1-10)");
            buffer = input.next();
            spinner = (int) (Math.random() * 10) + 1;
            if(player1.getLocation() < 10 && player1.getLocation() + spinner >= 10)
            {
                player1Job = (int) (Math.random() * 8 + 2);
                System.out.println("Please enter a value to gain a career and spin the spinner");
                buffer = input.next();
                System.out.println("Congratulations! " + player1.getName() + " is a " + jobList[player1Job].getTitle() + ", and makes " + jobList[player1Job].getSalary() + " dollars when paid.");
                player1.move(10 - player1.getLocation());
                System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
                spinner = (int) (Math.random() * 10) + 1;
                for(int i = 0; i < board.length; i++)
                {
                    if(board[i].getSpotType() == 3)
                    {
                        if(player1.getLocation() < i && player1.getLocation() + spinner >= i)
                        {
                            System.out.println(player1.getName() + " has been paid");
                            player1.addMoney(jobList[player1Job].getSalary());
                        }
                    }
                }
                player1.move(spinner);
                System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
            }
            else if(player1.getLocation() < 33 && player1.getLocation() + spinner >= 33)
            {
                player1Pet = (int) (Math.random() * 6);
                System.out.println("Please enter a value to purchase a pet and spin the spinner");
                buffer = input.next();
                System.out.println("Nice! " + player1.getName() + " has bought a " + petList[player1Pet].getPetName() + " for " + petList[player1Pet].getPetCost() + " dollars");
                player1.removeMoney(petList[player1Pet].getPetCost());
                for(int i = 0; i < board.length; i++)
                {
                    if(board[i].getSpotType() == 3)
                    {
                        if(player1.getLocation() < i && player1.getLocation() + spinner >= i)
                        {
                            System.out.println(player1.getName() + " has been paid");
                            player1.addMoney(jobList[player1Job].getSalary());
                        }
                    }
                }
                player1.move(33 - player1.getLocation());
                System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
                spinner = (int) (Math.random() * 10) + 1;
                for(int i = 0; i < board.length; i++)
                {
                    if(board[i].getSpotType() == 3)
                    {
                        if(player1.getLocation() < i && player1.getLocation() + spinner >= i)
                        {
                            System.out.println(player1.getName() + " has been paid");
                            player1.addMoney(jobList[player1Job].getSalary());
                        }
                    }
                }
                player1.move(spinner);
                System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
            }
            else if(player1.getLocation() < 66 && player1.getLocation() + spinner >= 66)
            {
                player1House = (int) (Math.random() * 6);
                System.out.println("Please enter a value to purchase a house and spin the spinner");
                buffer = input.next();
                System.out.println("Sweet! " + player1.getName() + " has bought a " + houseList[player1House].getHouseType() + " for " + houseList[player1House].getHouseCost() + " dollars");
                player1.removeMoney(houseList[player1House].getHouseCost());
                for(int i = 0; i < board.length; i++)
                {
                    if(board[i].getSpotType() == 3)
                    {
                        if(player1.getLocation() < i && player1.getLocation() + spinner >= i)
                        {
                            System.out.println(player1.getName() + " has been paid");
                            player1.addMoney(jobList[player1Job].getSalary());
                        }
                    }
                }
                player1.move(66 - player1.getLocation());
                System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
                spinner = (int) (Math.random() * 10) + 1;
                for(int i = 0; i < board.length; i++)
                {
                    if(board[i].getSpotType() == 3)
                    {
                        if(player1.getLocation() < i && player1.getLocation() + spinner >= i)
                        {
                            System.out.println(player1.getName() + " has been paid");
                            player1.addMoney(jobList[player1Job].getSalary());
                        }
                    }
                }
                player1.move(spinner);
                System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
            }
            else if(player1.getLocation() < 99 && player1.getLocation() + spinner >= 99)
            {
                for(int i = 0; i < board.length; i++)
                {
                    if(board[i].getSpotType() == 3)
                    {
                        if(player1.getLocation() < i && player1.getLocation() + spinner >= i)
                        {
                            System.out.println(player1.getName() + " has been paid");
                            player1.addMoney(jobList[player1Job].getSalary());
                        }
                    }
                }
                player1Finished = true;
                player1.move(99 - player1.getLocation());
                System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
                System.out.println(player1.getName() + " has retired and will earn interest until the game is over");
            } 
            else
            {
                for(int i = 0; i < board.length; i++)
                {
                    if(board[i].getSpotType() == 3)
                    {
                        if(player1.getLocation() < i && player1.getLocation() + spinner >= i)
                        {
                            System.out.println(player1.getName() + " has been paid");
                            player1.addMoney(jobList[player1Job].getSalary());
                        }
                    }
                }
                player1.move(spinner);
                System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
            }
            if(board[player1.getLocation()].getSpotType() == 2)
            {
                lawsuitChoice = JOptionPane.showInputDialog(player1.getName() + " has the option to sue " + opponent.getName() + ". Would you like to do so? (Yes/No)");
                validInput = false;
                while(validInput == false)
                {
                    if(lawsuitChoice.equalsIgnoreCase("Yes"))
                    {
                        if(player1.getMoney() <= 0)
                        {
                            System.out.println(player1.getName() + " does not have enough money to sue");
                            validInput = true;
                        }
                        else
                        {
                            inputMoneyLawsuit = false;
                            System.out.println(player1.getName() + ", please enter an integer between 1 and " + player1.getMoney() + " (Current Money)");
                            numLawsuit = input.nextInt();
                            while(inputMoneyLawsuit == false)
                            {
                                if(numLawsuit > 0 && numLawsuit <= player1.getMoney())
                                {
                                    lawsuitChance = Math.random();
                                    if(lawsuitChance >= 0.25)
                                    {
                                        System.out.println(player1.getName() + " has successfully taken " + numLawsuit + " from " + opponent.getName());
                                        player1.addMoney(numLawsuit);
                                        opponent.removeMoney(numLawsuit);
                                        inputMoneyLawsuit = true;
                                    }
                                    else
                                    {
                                        System.out.println("The lawsuit has failed and " + player1.getName() + " has lost the money.");
                                        player1.removeMoney(numLawsuit);
                                        inputMoneyLawsuit = true;
                                    }
                                }
                                else
                                {
                                    System.out.println("Invalid Input, please try again");
                                    numLawsuit = input.nextInt();
                                }
                            }
                            validInput = true;
                        }
                    }
                    else if(lawsuitChoice.equalsIgnoreCase("No"))
                    {
                        System.out.println(player1.getName() + " has not chosen to sue " + opponent.getName());
                        validInput = true;
                    }
                    else
                    {
                        lawsuitChoice = JOptionPane.showInputDialog("Invalid Input. Try again. " + player1.getName() + " has the option to sue " + opponent.getName() + ". Would you like to do so? (Yes/No)");
                    }
                }
            }
            if(board[player1.getLocation()].getSpotType() == 1)
            {
                player1.randomEvent(board);
            }
            // Opponent Turn now starts
            System.out.println();
            System.out.println("Opponents Turn");
            System.out.println();
            System.out.println(opponent.getName() + ", please enter anything to spin the spinner (Results: 1-10)");
            spinner = (int) (Math.random() * 10) + 1;
            if(player1.getLocation() < 10 && player1.getLocation() + spinner >= 10)
            {
                opponentJob = (int) (Math.random() * 8 + 2);
                System.out.println("Please enter a value to gain a career and spin the spinner");
                System.out.println("Congratulations! " + opponent.getName() + " is a " + jobList[opponentJob].getTitle() + ", and makes " + jobList[opponentJob].getSalary() + " dollars when paid.");
                opponent.move(10 - opponent.getLocation());
                System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
                spinner = (int) (Math.random() * 10) + 1;
                for(int i = 0; i < board.length; i++)
                {
                    if(board[i].getSpotType() == 3)
                    {
                        if(opponent.getLocation() < i && opponent.getLocation() + spinner >= i)
                        {
                            System.out.println(opponent.getName() + " has been paid");
                            opponent.addMoney(jobList[opponentJob].getSalary());
                        }
                    }
                }
                opponent.move(spinner);
                System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
            }
            else if(opponent.getLocation() < 33 && opponent.getLocation() + spinner >= 33)
            {
                opponentPet = (int) (Math.random() * 6);
                System.out.println("Please enter a value to purchase a pet and spin the spinner");
                System.out.println("Nice! " + opponent.getName() + " has bought a " + petList[opponentPet].getPetName() + " for " + petList[opponentPet].getPetCost() + " dollars");
                opponent.removeMoney(petList[opponentPet].getPetCost());
                for(int i = 0; i < board.length; i++)
                {
                    if(board[i].getSpotType() == 3)
                    {
                        if(opponent.getLocation() < i && opponent.getLocation() + spinner >= i)
                        {
                            System.out.println(opponent.getName() + " has been paid");
                            opponent.addMoney(jobList[opponentJob].getSalary());
                        }
                    }
                }
                opponent.move(33 - opponent.getLocation());
                System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
                spinner = (int) (Math.random() * 10) + 1;
                for(int i = 0; i < board.length; i++)
                {
                    if(board[i].getSpotType() == 3)
                    {
                        if(opponent.getLocation() < i && opponent.getLocation() + spinner >= i)
                        {
                            System.out.println(opponent.getName() + " has been paid");
                            opponent.addMoney(jobList[opponentJob].getSalary());
                        }
                    }
                }
                opponent.move(spinner);
                System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
            }
            else if(opponent.getLocation() < 66 && opponent.getLocation() + spinner >= 66)
            {
                opponentHouse = (int) (Math.random() * 6);
                System.out.println("Please enter a value to purchase a house and spin the spinner");
                System.out.println("Sweet! " + opponent.getName() + " has bought a " + houseList[opponentHouse].getHouseType() + " for " + houseList[opponentHouse].getHouseCost() + " dollars");
                opponent.removeMoney(houseList[opponentHouse].getHouseCost());
                for(int i = 0; i < board.length; i++)
                {
                    if(board[i].getSpotType() == 3)
                    {
                        if(opponent.getLocation() < i && opponent.getLocation() + spinner >= i)
                        {
                            System.out.println(opponent.getName() + " has been paid");
                            opponent.addMoney(jobList[opponentJob].getSalary());
                        }
                    }
                }
                opponent.move(66 - opponent.getLocation());
                System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
                spinner = (int) (Math.random() * 10) + 1;
                for(int i = 0; i < board.length; i++)
                {
                    if(board[i].getSpotType() == 3)
                    {
                        if(opponent.getLocation() < i && opponent.getLocation() + spinner >= i)
                        {
                            System.out.println(opponent.getName() + " has been paid");
                            opponent.addMoney(jobList[opponentJob].getSalary());
                        }
                    }
                }
                opponent.move(spinner);
                System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
            }
            else if(opponent.getLocation() < 99 && opponent.getLocation() + spinner >= 99)
            {
                for(int i = 0; i < board.length; i++)
                {
                    if(board[i].getSpotType() == 3)
                    {
                        if(opponent.getLocation() < i && opponent.getLocation() + spinner >= i)
                        {
                            System.out.println(opponent.getName() + " has been paid");
                            opponent.addMoney(jobList[opponentJob].getSalary());
                        }
                    }
                }
                opponentFinished = true;
                opponent.move(99 - opponent.getLocation());
                System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
                System.out.println(opponent.getName() + " has retired and will earn interest until the game is over");
            } 
            else
            {
                for(int i = 0; i < board.length; i++)
                {
                    if(board[i].getSpotType() == 3)
                    {
                        if(opponent.getLocation() < i && opponent.getLocation() + spinner >= i)
                        {
                            System.out.println(opponent.getName() + " has been paid");
                            opponent.addMoney(jobList[opponentJob].getSalary());
                        }
                    }
                }
                opponent.move(spinner);
                System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
            }
            if(board[opponent.getLocation()].getSpotType() == 2)
            {
                System.out.println(opponent.getName() + " has a chance to sue " + player1.getName());
                lawsuitChance = Math.random();
                if(lawsuitChance > 0.25) 
                {
                    lawsuitChoice = "Yes";
                }
                else
                {
                    lawsuitChoice = "No";
                }
                validInput = false;
                while(validInput == false)
                {
                    if(lawsuitChoice.equalsIgnoreCase("Yes"))
                    {
                        if(opponent.getMoney() <= 0)
                        {
                            System.out.println(opponent.getName() + " does not have enough money to sue");
                            validInput = true;
                        }
                        else
                        {
                            inputMoneyLawsuit = false;
                            numLawsuit = (int) (opponent.getMoney() * 0.9);
                            while(inputMoneyLawsuit == false)
                            {
                                if(numLawsuit > 0 && numLawsuit <= opponent.getMoney())
                                {
                                    lawsuitChance = Math.random();
                                    if(lawsuitChance >= 0.25)
                                    {
                                        System.out.println(opponent.getName() + " has successfully taken " + numLawsuit + " from " + opponent.getName());
                                        opponent.addMoney(numLawsuit);
                                        player1.removeMoney(numLawsuit);
                                        inputMoneyLawsuit = true;
                                    }
                                    else
                                    {
                                        System.out.println("The lawsuit has failed and " + opponent.getName() + " has lost the money.");
                                        opponent.removeMoney(numLawsuit);
                                        inputMoneyLawsuit = true;
                                    }
                                }
                                else
                                {
                                    System.out.println("Invalid Input, please try again");
                                    numLawsuit = input.nextInt();
                                }
                            }
                            validInput = true;
                        }
                    }
                    else if(lawsuitChoice.equalsIgnoreCase("No"))
                    {
                        System.out.println(opponent.getName() + " has not chosen to sue " + player1.getName());
                        validInput = true;
                    }
                }
            }
            if(board[opponent.getLocation()].getSpotType() == 1)
            {
                opponent.randomEvent(board);
            }
            turn++;
        }
        if(gameOver == false && opponentFinished == true && player1Finished == false)
        {
            while(gameOver == false && player1Finished == false && opponentFinished == true)
            {
                System.out.println();
                System.out.println("Turn " + turn);
                if(opponent.getMoney() > 0)
                {
                    System.out.println(opponent.getName() + "'s retirement money has compounded");
                    opponent.addMoney(opponent.getMoney() / 3);
                }
                System.out.println(player1.getName() + " is at location " + player1.getLocation() + " out of 99, and has " + player1.getMoney() + " money.");
                System.out.println(opponent.getName() + " has " + opponent.getMoney() + " dollars");
                if(turn == 0)
                {
                    String choiceCollege = JOptionPane.showInputDialog(player1.getName() + ", would you like to go to college? (Yes/No)");
                    validInput = false;
                    while(validInput == false)
                    {
                        if(choiceCollege.equalsIgnoreCase("Yes"))
                        {
                            player1.removeMoney(100000);
                            System.out.println(player1.getName() + " has spent 100000 dollars on college");
                            validInput = true;
                            player1College = true;
                        }
                        else if(choiceCollege.equalsIgnoreCase("No"))
                        {
                            player1.move(10);
                            System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
                            System.out.println();
                            System.out.println(player1.getName() + " has chosen to skip college and immidietly get a job");
                            player1Job = (int) (Math.random() * 5);
                            System.out.println("Congratulations! " + player1.getName() + " is a " + jobList[player1Job].getTitle() + ", and makes " + jobList[player1Job].getSalary() + " dollars when paid.");
                            validInput = true;
                            player1College = false;
                        }
                        else
                        {
                            choiceCollege = JOptionPane.showInputDialog("Invalid responce. " + player1.getName() + ", would you like to go to college? (Yes/No)");
                        }
                    }
                    computerChoice = (int) (Math.random() * 2);
                    if(computerChoice == 0)
                    {
                        opponent.move(10);
                        System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
                        System.out.println(opponent.getName() + " has chosen to skip college and immidietly get a job");
                    }
                    else if(computerChoice == 1)
                    {
                        opponent.removeMoney(100000);
                        System.out.println(opponent.getName() + " has spent 100000 dollars on college");
                    }
                    turn++;
                }
                System.out.println(player1.getName() + ", please enter anything to spin the spinner (Results: 1-10)");
                buffer = input.next();
                spinner = (int) (Math.random() * 10) + 1;
                if(player1.getLocation() < 10 && player1.getLocation() + spinner >= 10)
                {
                    player1Job = (int) (Math.random() * 8 + 2);
                    System.out.println("Please enter a value to gain a career and spin the spinner");
                    buffer = input.next();
                    System.out.println("Congratulations! " + player1.getName() + " is a " + jobList[player1Job].getTitle() + ", and makes " + jobList[player1Job].getSalary() + " dollars when paid.");
                    player1.move(10 - player1.getLocation());
                    System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
                    spinner = (int) (Math.random() * 10) + 1;
                    for(int i = 0; i < board.length; i++)
                    {
                        if(board[i].getSpotType() == 3)
                        {
                            if(player1.getLocation() < i && player1.getLocation() + spinner >= i)
                            {
                                System.out.println(player1.getName() + " has been paid");
                                player1.addMoney(jobList[player1Job].getSalary());
                            }
                        }
                    }
                    player1.move(spinner);
                    System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
                }
                else if(player1.getLocation() < 33 && player1.getLocation() + spinner >= 33)
                {
                    player1Pet = (int) (Math.random() * 6);
                    System.out.println("Please enter a value to purchase a pet and spin the spinner");
                    buffer = input.next();
                    System.out.println("Nice! " + player1.getName() + " has bought a " + petList[player1Pet].getPetName() + " for " + petList[player1Pet].getPetCost() + " dollars");
                    player1.removeMoney(petList[player1Pet].getPetCost());
                    for(int i = 0; i < board.length; i++)
                    {
                        if(board[i].getSpotType() == 3)
                        {
                            if(player1.getLocation() < i && player1.getLocation() + spinner >= i)
                            {
                                System.out.println(player1.getName() + " has been paid");
                                player1.addMoney(jobList[player1Job].getSalary());
                            }
                        }
                    }
                    player1.move(33 - player1.getLocation());
                    System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
                    spinner = (int) (Math.random() * 10) + 1;
                    for(int i = 0; i < board.length; i++)
                    {
                        if(board[i].getSpotType() == 3)
                        {
                            if(player1.getLocation() < i && player1.getLocation() + spinner >= i)
                            {
                                System.out.println(player1.getName() + " has been paid");
                                player1.addMoney(jobList[player1Job].getSalary());
                            }
                        }
                    }
                    player1.move(spinner);
                    System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
                }
                else if(player1.getLocation() < 66 && player1.getLocation() + spinner >= 66)
                {
                    player1House = (int) (Math.random() * 6);
                    System.out.println("Please enter a value to purchase a house and spin the spinner");
                    buffer = input.next();
                    System.out.println("Sweet! " + player1.getName() + " has bought a " + houseList[player1House].getHouseType() + " for " + houseList[player1House].getHouseCost() + " dollars");
                    player1.removeMoney(houseList[player1House].getHouseCost());
                    for(int i = 0; i < board.length; i++)
                    {
                        if(board[i].getSpotType() == 3)
                        {
                            if(player1.getLocation() < i && player1.getLocation() + spinner >= i)
                            {
                                System.out.println(player1.getName() + " has been paid");
                                player1.addMoney(jobList[player1Job].getSalary());
                            }
                        }
                    }
                    player1.move(66 - player1.getLocation());
                    System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
                    spinner = (int) (Math.random() * 10) + 1;
                    for(int i = 0; i < board.length; i++)
                    {
                        if(board[i].getSpotType() == 3)
                        {
                            if(player1.getLocation() < i && player1.getLocation() + spinner >= i)
                            {
                                System.out.println(player1.getName() + " has been paid");
                                player1.addMoney(jobList[player1Job].getSalary());
                            }
                        }
                    }
                    player1.move(spinner);
                    System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
                }
                else if(player1.getLocation() < 99 && player1.getLocation() + spinner >= 99)
                {
                    for(int i = 0; i < board.length; i++)
                    {
                        if(board[i].getSpotType() == 3)
                        {
                            if(player1.getLocation() < i && player1.getLocation() + spinner >= i)
                            {
                                System.out.println(player1.getName() + " has been paid");
                                player1.addMoney(jobList[player1Job].getSalary());
                            }
                        }
                    }
                    player1Finished = true;
                    player1.move(99 - player1.getLocation());
                    System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
                    System.out.println(player1.getName() + " has retired and will earn interest until the game is over");
                } 
                else
                {
                    for(int i = 0; i < board.length; i++)
                    {
                        if(board[i].getSpotType() == 3)
                        {
                            if(player1.getLocation() < i && player1.getLocation() + spinner >= i)
                            {
                                System.out.println(player1.getName() + " has been paid");
                                player1.addMoney(jobList[player1Job].getSalary());
                            }
                        }
                    }
                    player1.move(spinner);
                    System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
                }
                if(board[player1.getLocation()].getSpotType() == 1)
                {
                    player1.randomEvent(board);
                }
                turn++;
            }
        }
        else if(gameOver == false && opponentFinished == false && player1Finished == true)
        {
            while(gameOver == false && opponentFinished == false && player1Finished == true)
            {
                System.out.println();
                System.out.println("Turn " + turn);
                if(player1.getMoney() > 0)
                {
                    System.out.println(player1.getName() + "'s retirement money has compounded");
                    player1.addMoney(player1.getMoney() / 3);
                }
                System.out.println(opponent.getName() + " is at location " + opponent.getLocation() + " out of 99, and has " + opponent.getMoney() + " money.");
                System.out.println(player1.getName() + " has " + player1.getMoney() + " dollars");
                if(turn == 0)
                {
                    String choiceCollege = JOptionPane.showInputDialog(player1.getName() + ", would you like to go to college? (Yes/No)");
                    validInput = false;
                    while(validInput == false)
                    {
                        if(choiceCollege.equalsIgnoreCase("Yes"))
                        {
                            player1.removeMoney(100000);
                            System.out.println(player1.getName() + " has spent 100000 dollars on college");
                            validInput = true;
                            player1College = true;
                        }
                        else if(choiceCollege.equalsIgnoreCase("No"))
                        {
                            player1.move(10);
                            System.out.println(player1.getName() + " has moved to spot " + player1.getLocation());
                            System.out.println();
                            System.out.println(player1.getName() + " has chosen to skip college and immidietly get a job");
                            player1Job = (int) (Math.random() * 5);
                            System.out.println("Congratulations! " + player1.getName() + " is a " + jobList[player1Job].getTitle() + ", and makes " + jobList[player1Job].getSalary() + " dollars when paid.");
                            validInput = true;
                            player1College = false;
                        }
                        else
                        {
                            choiceCollege = JOptionPane.showInputDialog("Invalid responce. " + player1.getName() + ", would you like to go to college? (Yes/No)");
                        }
                    }
                    computerChoice = (int) (Math.random() * 2);
                    if(computerChoice == 0)
                    {
                        opponent.move(10);
                        System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
                        System.out.println(opponent.getName() + " has chosen to skip college and immidietly get a job");
                    }
                    else if(computerChoice == 1)
                    {
                        opponent.removeMoney(100000);
                        System.out.println(opponent.getName() + " has spent 100000 dollars on college");
                    }
                    turn++;
                }
                System.out.println();
                System.out.println("Please enter something to continue with " + opponent.getName() + "'s turn");
                buffer = input.next();
                System.out.println(opponent.getName() + ", please enter anything to spin the spinner (Results: 1-10)");
                spinner = (int) (Math.random() * 10) + 1;
                if(player1.getLocation() < 10 && player1.getLocation() + spinner >= 10)
                {
                    opponentJob = (int) (Math.random() * 8 + 2);
                    System.out.println("Please enter a value to gain a career and spin the spinner");
                    System.out.println("Congratulations! " + opponent.getName() + " is a " + jobList[opponentJob].getTitle() + ", and makes " + jobList[opponentJob].getSalary() + " dollars when paid.");
                    opponent.move(10 - opponent.getLocation());
                    System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
                    spinner = (int) (Math.random() * 10) + 1;
                    for(int i = 0; i < board.length; i++)
                    {
                        if(board[i].getSpotType() == 3)
                        {
                            if(opponent.getLocation() < i && opponent.getLocation() + spinner >= i)
                            {
                                System.out.println(opponent.getName() + " has been paid");
                                opponent.addMoney(jobList[opponentJob].getSalary());
                            }
                        }
                    }
                    opponent.move(spinner);
                    System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
                }
                else if(opponent.getLocation() < 33 && opponent.getLocation() + spinner >= 33)
                {
                    opponentPet = (int) (Math.random() * 6);
                    System.out.println("Please enter a value to purchase a pet and spin the spinner");
                    System.out.println("Nice! " + opponent.getName() + " has bought a " + petList[opponentPet].getPetName() + " for " + petList[opponentPet].getPetCost() + " dollars");
                    opponent.removeMoney(petList[opponentPet].getPetCost());
                    for(int i = 0; i < board.length; i++)
                    {
                        if(board[i].getSpotType() == 3)
                        {
                            if(opponent.getLocation() < i && opponent.getLocation() + spinner >= i)
                            {
                                System.out.println(opponent.getName() + " has been paid");
                                opponent.addMoney(jobList[opponentJob].getSalary());
                            }
                        }
                    }
                    opponent.move(33 - opponent.getLocation());
                    System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
                    spinner = (int) (Math.random() * 10) + 1;
                    for(int i = 0; i < board.length; i++)
                    {
                        if(board[i].getSpotType() == 3)
                        {
                            if(opponent.getLocation() < i && opponent.getLocation() + spinner >= i)
                            {
                                System.out.println(opponent.getName() + " has been paid");
                                opponent.addMoney(jobList[opponentJob].getSalary());
                            }
                        }
                    }
                    opponent.move(spinner);
                    System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
                }
                else if(opponent.getLocation() < 66 && opponent.getLocation() + spinner >= 66)
                {
                    opponentHouse = (int) (Math.random() * 6);
                    System.out.println("Please enter a value to purchase a house and spin the spinner");
                    System.out.println("Sweet! " + opponent.getName() + " has bought a " + houseList[opponentHouse].getHouseType() + " for " + houseList[opponentHouse].getHouseCost() + " dollars");
                    opponent.removeMoney(houseList[opponentHouse].getHouseCost());
                    for(int i = 0; i < board.length; i++)
                    {
                        if(board[i].getSpotType() == 3)
                        {
                            if(opponent.getLocation() < i && opponent.getLocation() + spinner >= i)
                            {
                                System.out.println(opponent.getName() + " has been paid");
                                opponent.addMoney(jobList[opponentJob].getSalary());
                            }
                        }
                    }
                    opponent.move(66 - opponent.getLocation());
                    System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
                    spinner = (int) (Math.random() * 10) + 1;
                    for(int i = 0; i < board.length; i++)
                    {
                        if(board[i].getSpotType() == 3)
                        {
                            if(opponent.getLocation() < i && opponent.getLocation() + spinner >= i)
                            {
                                System.out.println(opponent.getName() + " has been paid");
                                opponent.addMoney(jobList[opponentJob].getSalary());
                            }
                        }
                    }
                    opponent.move(spinner);
                    System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
                }
                else if(opponent.getLocation() < 99 && opponent.getLocation() + spinner >= 99)
                {
                    for(int i = 0; i < board.length; i++)
                    {
                        if(board[i].getSpotType() == 3)
                        {
                            if(opponent.getLocation() < i && opponent.getLocation() + spinner >= i)
                            {
                                System.out.println(opponent.getName() + " has been paid");
                                opponent.addMoney(jobList[opponentJob].getSalary());
                            }
                        }
                    }
                    opponentFinished = true;
                    opponent.move(99 - opponent.getLocation());
                    System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
                    System.out.println(opponent.getName() + " has retired and will earn interest until the game is over");
                } 
                else
                {
                    for(int i = 0; i < board.length; i++)
                    {
                        if(board[i].getSpotType() == 3)
                        {
                            if(opponent.getLocation() < i && opponent.getLocation() + spinner >= i)
                            {
                                System.out.println(opponent.getName() + " has been paid");
                                opponent.addMoney(jobList[opponentJob].getSalary());
                            }
                        }
                    }
                    opponent.move(spinner);
                    System.out.println(opponent.getName() + " has moved to spot " + opponent.getLocation());
                }
                if(board[opponent.getLocation()].getSpotType() == 1)
                {
                    opponent.randomEvent(board);
                }
                turn++;
            }
        }
        if(player1.getNumberKids() > 0)
        {
            System.out.println(player1.getName() + " had " + player1.getNumberKids() + " kids, and each kid brought back 200000 dollars");
            player1.addMoney(player1.getNumberKids() * 200000);
        }
        if(opponent.getNumberKids() > 0)
        {
            System.out.println(opponent.getName() + " had " + opponent.getNumberKids() + " kids, and each kid brought back 200000 dollars");
            opponent.addMoney(opponent.getNumberKids() * 200000);
        }
        System.out.println("Please enter something to find out the winner...");
        buffer = input.next();
        if(player1.getMoney() > opponent.getMoney())
        {
            System.out.println("Congratulations! " + player1.getName() + " has won!");
        }
        else if(player1.getMoney() == opponent.getMoney())
        {
            System.out.println("It's a tie!");
        }
        else
        {
            System.out.println("Congratulations! " + opponent.getName() + " has won!");
        }
    }
}