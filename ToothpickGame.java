public class ToothpickGame
{
    private static int toothpicks;
    private static int currentPlayer;
    private static int winsNeeded = 0;
    private static int player1Wins = 0;
    private static int player2Wins = 0;
    private static int computerWins = 0;
    private static int max;
    private static int best;
    private static int skillLevel;
    private static String name1 = "";
    private static String name2 = "";
    private static String computerName = "";
    private static boolean computerOpponent = false;
    public static void main (String [] args)
    {
        displayWelcomeBanner();
        setGameParameters();
        while (player1Wins != winsNeeded && player2Wins != winsNeeded)
            playOneGame();
        displayEndOfSeriesStats();
    }  

    private static void displayWelcomeBanner()
    {
        System.out.println ("\n**********************************");
        System.out.println ("*Welcome to the Toothpick Game!!!*");
        System.out.println ("**********************************");
    } 

    private static void setGameParameters()
    {
        int i = 0;
        int player = 0;
        KeyboardReader reader = new KeyboardReader();
        name1 = reader.readString("\nPlayer 1, please enter your name: ");
        while (i == 0)
        {
            player = reader.readInt("\n" + name1 + ", is player 2 going to be a human or a computer opponent?\nEnter in 1 for human or 2 for computer. ");
            if (player != 1 && player != 2)
                System.out.println("I'm sorry, " + name1 + ", but that is an invalid choice. Please choose 1 or 2.");
            else if (player == 1) //HUMAN   
            {   
                name2 = reader.readString("\nPlayer 2, please enter your name: ");
                break;
            }
            else //COMPUTER
            {
                while (i == 0)
                {
                    System.out.println("**********************");
                    System.out.println("*Computer Skill Level*");
                    System.out.println("**********************");
                    System.out.println("*1) Garry (Dumb)             *");
                    System.out.println("*2) Larry (Not So Smart)     *");
                    System.out.println("*3) Terry (Smart)            *");
                    System.out.println("**********************");
                    skillLevel = reader.readInt("" + name1 + ", which computer do you want to take on? ");
                    if (skillLevel != 1 && skillLevel != 2 && skillLevel != 3)
                        System.out.println("I'm sorry, " + name1 + ", but that is an invalid choice. Please choose 1, 2, or 3.");
                    else
                    {
                        computerOpponent = true;
                        if (skillLevel == 1)
                            computerName = "Garry";
                        if (skillLevel == 2)
                            computerName = "Larry";
                        if (skillLevel == 3)
                            computerName = "Terry";
                        break;
                    }
                }
                break;
            }
        }
        while (i == 0)
        {
            best = reader.readInt("" + name1 + ", will you be playing best out of 1, 3, 5, or 7? ");
            if (best != 1 && best != 3 && best != 5 && best != 7)
                System.out.println("I’m sorry, " + name1 + ", but that is an invalid choice. Please choose 1, 3, 5, or 7.");
            else
            {
                winsNeeded = (best + 1)/2;
                break;
            }
        }
        while (i == 0)
        {
            max = reader.readInt("\n" + name1 + ", what is the maximum number of toothpicks that a player can take per turn?\nEnter 3, 4, 5, or 6 (or 0 to have it randomly chosen before each game):\n");
            if (max != 0 && max != 3 && max != 4 && max != 5 && max != 6)
                System.out.println("I’m sorry, " + name1 + ", but that is an invalid choice. Please choose 3, 4, 5, 6, or 0");
            else if (max == 0)
            {
                max = (int)(Math.random() * 4) + 3;
                System.out.println("The computer has randomly selected " + max + " to be the maximum number of toothpicks that a player can take per turn.");
                break;
            }
            else
                break;
        }
    }

    private static void initializeGame()
    {
        int i = 0;
        KeyboardReader reader = new KeyboardReader();
        currentPlayer = (int)(Math.random() * 2);
        if (currentPlayer == 1)
            System.out.println("\n" + name1 + " will go first!");
        else if (currentPlayer != 1 && computerOpponent == false)
            System.out.println("\n" + name2 + " will go first!");
        else
            System.out.println("\n" + computerName + " will go first!");
        toothpicks = (int)(Math.random() * 21) + 15;
        System.out.println("\nThe game generates a random number of toothpicks between 15 and 35. " + toothpicks + " is chosen.");
    }

    private static void playOneGame()
    {
        initializeGame();
        while (toothpicks > 0)
        {
            if (toothpicks == 1)
                System.out.println("\nThere is 1 toothpick remaining.");
            else
                System.out.println("\nThere are " + toothpicks + " toothpicks remaining.");
            currentPlayerTakesTurn();
            if (toothpicks != 0)
                currentPlayer = (currentPlayer % 2) + 1; //Switch Player turn
        }
        if (player1Wins == winsNeeded || player2Wins == winsNeeded)
            congratulateWinner();
    }

    private static void congratulateWinner()
    {
        if (player1Wins == winsNeeded)
        {
            System.out.println("\n*****Congratulations " + name1 + "!*****\n**********YOU WON!!!**********");
        }
        else if (player2Wins == winsNeeded && computerOpponent == false)
        {
            System.out.println("\n*****Congratulations " + name2 + "!*****\n**********YOU WON!!!**********");
        }
        else
            System.out.println("\n**********YOU LOSE!!!**********");
    }

    private static void currentPlayerTakesTurn()
    {
        if (currentPlayer == 1)
            player1TakesTurn();
        else if (computerOpponent == false)
            player2TakesTurn();
        else if (skillLevel == 1) //Easy
            easyComputerTakesTurn();
        else if (skillLevel == 2) //Medium
            mediumComputerTakesTurn();
        else if (skillLevel == 3) //Hard
            hardComputerTakesTurn();
    }

    private static void easyComputerTakesTurn()
    {
        int i = 0;
        int num = 0;
        int phrase = (int)(Math.random() * 4);
        KeyboardReader reader = new KeyboardReader();
        while (i == 0)
        {
            if (max < toothpicks)
                num = (int)(Math.random() * max) + 1;
            else 
                num = 1;
            if (phrase == 0)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: You're going down.");
            else if(phrase == 1)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: Nice Move...NOT!.");
            else if (phrase == 2)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: YOU'RE BAD!.");
            else if (phrase == 3)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: You're like a Neanderthal.");
            toothpicks -= num;
            if (toothpicks == 0 && currentPlayer != 1)
            {
                computerWins++;
                player2Wins++;
                System.out.println("\n" + computerName + " wins this round!");
            }
            break;
        }
    }

    private static void mediumComputerTakesTurn()
    {
        int i = 0;
        int num = 0;
        int ran = (int)(Math.random() * 2);
        int phrase = (int)(Math.random() * 4);
        KeyboardReader reader = new KeyboardReader();
        while (i == 0)
        {
            if (max < toothpicks)
            {
                if (ran == 0)
                    num = (int)(Math.random() * max) + 1;
                else
                    num = max;
            }
            else 
                num = toothpicks;
            if (phrase == 0)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: You're going down.");
            else if(phrase == 1)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: Nice Move...NOT!.");
            else if (phrase == 2)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: YOU'RE BAD!.");
            else if (phrase == 3)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: You're like a Neanderthal.");
            toothpicks -= num;
            if (toothpicks == 0 && currentPlayer != 1)
            {
                computerWins++;
                player2Wins++;
                System.out.println("\n" + computerName + " wins this round!");
            }
            break;
        }
    }

    private static void hardComputerTakesTurn()
    {
        int i = 0;
        int num = 0;
        int go = toothpicks % (max + 1);
        int phrase = (int)(Math.random() * 4);
        KeyboardReader reader = new KeyboardReader();
        while (i == 0)
        {
            if (max < toothpicks)
            {
                if (go != 0)
                    num = go;
                else if (go == 0)
                    num = max;
            }
            else
                num = toothpicks;
            if (phrase == 0 && go != 0 && num != toothpicks)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: You're going down.");
            else if(phrase == 1 && go != 0 && num != toothpicks)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: Nice Move...NOT!.");
            else if (phrase == 2 && go != 0 && num != toothpicks)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: YOU'RE BAD!.");
            else if (phrase == 3 && go != 0 && num != toothpicks)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: You're like a Neanderthal.");
            else if (phrase == 0 && go == 0 && num != toothpicks)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: No good move there.");
            else if(phrase == 1 && go == 0 && num != toothpicks)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: Whatever.");
            else if (phrase == 2 && go == 0 && num != toothpicks)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: Man, you got me.");
            else if (phrase == 3 && go == 0 && num != toothpicks)
                System.out.println("" + computerName + " picked up " + num + " toothpicks.\n" + computerName + " says: I guess I'll just do that.");
            toothpicks -= num;
            if (toothpicks == 0 && currentPlayer != 1)
            {
                computerWins++;
                player2Wins++;
                System.out.println("\n" + computerName + " wins this round!");
            }
            break;
        }
    }

    private static void player1TakesTurn()
    {
        int i = 0;
        KeyboardReader reader = new KeyboardReader();
        while (i == 0)
        {
            int num = reader.readInt("" + name1 + ", how many toothpicks would you like to pick up? ");
            if (num <= 0 || num > max)
                System.out.println("I'm sorry, " + name1 + ", but that is an invalid choice. Please choose between 1 and " + max + ".");
            else if (num > toothpicks)
                System.out.println("I'm sorry, " + name1 + ", but that is an invalid choice. There are less toothpicks than that.\nPlease choose between 1 and " + toothpicks + ".");
            else
            {
                toothpicks -= num;
                if (toothpicks == 0 && currentPlayer == 1)
                {
                    player1Wins++;
                    System.out.println("\n" + name1 + " wins this round!\n");
                }
                break;
            }
        }
    }

    private static void player2TakesTurn()
    {
        int i = 0;
        KeyboardReader reader = new KeyboardReader();
        while (i == 0)
        {
            int num = reader.readInt("" + name2 + ", how many toothpicks would you like to pick up? ");
            if (num <= 0 || num > max)
                System.out.println("I'm sorry, " + name2 + ", but that is an invalid choice. Please choose between 1 and " + max + ".");
            else if (num > toothpicks)
                System.out.println("I'm sorry, " + name2 + ", but that is an invalid choice. There are less toothpicks than that.\nPlease choose between 1 and " + toothpicks + ".");
            else
            {
                toothpicks -= num;
                if (toothpicks == 0 && currentPlayer != 1)
                {
                    player2Wins++;
                    System.out.println("\n" + name2 + " wins this round!\n");
                }
                break;
            }
        }
    }

    private static void displayEndOfSeriesStats()
    {
        System.out.println("\n************************");
        System.out.println("*Overall Series Results*");
        System.out.println("************************");
        if (player1Wins == 1)
            System.out.println(""+ name1 + " won " + player1Wins + " game.");
        else
            System.out.println(""+ name1 + " won " + player1Wins + " games.");
        if (player2Wins == 1 && computerOpponent == false)
            System.out.println(""+ name2 + " won " + player2Wins + " game.");
        else if (player2Wins != 1 && computerOpponent == false)
            System.out.println(""+ name2 + " won " + player2Wins + " games.");
        if (computerWins == 1)
            System.out.println("" + computerName + " won " + computerWins + " game.");
        else if (computerOpponent == true)
            System.out.println("" + computerName + " won " + computerWins + " games.");
        if (player1Wins > player2Wins && computerOpponent == false)
            System.out.println("" + name1 + " is the overall winner! Congratulations on crushing " + name2 + " like a little bug.");
        else if (player1Wins < player2Wins && computerOpponent == false)
            System.out.println("" + name2 + " is the overall winner! Congratulations on crushing " + name1 + " like a little bug.");
        if (player1Wins > computerWins && computerOpponent == true)
            System.out.println("" + name1 + " is the overall winner! Congratulations on crushing " + computerName + " like a little bug.");
        else if (player1Wins < computerWins && computerOpponent == true)
            System.out.println("" + computerName + " is the overall winner! Congratulations on crushing " + name1 + " like a little bug.");
    }
}