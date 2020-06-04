package Blackjack;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Blackjack
{

    private ArrayList<Player> players = new ArrayList<Player>();

    private Player house = new Player();

    /**
     * Adds that number of players to the list of players
     * @param numPlayers
     */
    public void addPlayers(int numPlayers)
    {
        for (int i = 0; i < numPlayers; i++)
        {
            players.add(new Player(i + 1));
        }
    }

    /*
     * Creates that number of player objects
     */
    public void setUpNumOfPlayers()
    {
        Scanner kbd = new Scanner(System.in);

        System.out.println("WELCOME TO BLACKJACK \n");
        System.out.println("How many players are going to play in the game?");
        System.out.println("(Mininumum # of players is 2 and Maximum # of players is 5) \n");

        String numberOfPlayers = "";

        // Convert the string input into an integer
        int numPlayers = 0;

        // If the user puts a number greater than 5 or less than 2
        // Continue prompting the user until they give
        // a number between 2 and 5
        while (numPlayers < 2 || numPlayers > 5)
        {
            System.out.println("Please keep the number of players between 2 and 5");

            // get user input
            numberOfPlayers = kbd.nextLine();

            // if user gives a non-numerical answer
            // continue prompting user until they give a numeric answer
            while (!numberOfPlayers.matches("[0-9]+"))
            {
                System.out.println("Please enter a number");

                // get user input
                numberOfPlayers = kbd.nextLine();
            }

            // convert the user input into an integer
            numPlayers = Integer.parseInt(numberOfPlayers);
        }

        // add the players to the game
        addPlayers(numPlayers);

        System.out.println(numPlayers + " players have been added to the game\n");
    }

    public void setUpPlayers()
    {
        Random rand = new Random();

        Scanner kbd = new Scanner(System.in);

        for (int i = 0; i < players.size(); i++)
        {
            System.out.println("What is player " + (i + 1) + "'s name ? ");

            Player p = new Player(i + 1);

            String name = kbd.nextLine();

            players.get(i).setName(name);

            players.get(i).setOutStatus(false);

            System.out.println();

            System.out.println("__________________________________________________\n");

        }

        house.setName("The House");

        System.out.println();
    }

    public void setUpGame()
    {
        Deck deck = new Deck();

        deck.shuffle();

        for (Player p : players)
        {
            Card one = deck.draw();

            Card two = deck.draw();

            one.setFaceDown(false);

            two.setFaceDown(false);

            p.addToPlayerCards(one);

            p.addToPlayerCards(two);

            System.out.println("Two cards have been added to " + p.getName() + "'s hand\n");
        }

        Card one = deck.draw();

        Card two = deck.draw();

        one.setFaceDown(true);

        two.setFaceDown(false);

        house.addToPlayerCards(one);

        house.addToPlayerCards(two);

        System.out.println("Two cards have been added to " + house.getName() + "'s hand\n");

        System.out.println("__________________________________________________\n");

        startGame(deck);
    }

    public int setUpRounds()
    {
        Scanner kbd = new Scanner(System.in);

        System.out.println("How many rounds would you like to play?\n ");

        System.out.println("The limit is ten rounds\n");

        String numberOfRounds = "";

        // Convert the string input into an integer
        int numRounds = 0;

        // If the user puts a number greater than 4 or less than 2
        // Continue prompting the user until they give
        // a number between 2 and 4
        while (numRounds < 1 || numRounds > 10)
        {
            System.out.println("Please keep the number of rounds between 1 and 10");

            // get user input
            numberOfRounds = kbd.nextLine();

            // if user gives a non-numerical answer
            // continue prompting user until they give a numeric answer
            while (!numberOfRounds.matches("[0-9]+"))
            {
                System.out.println("Please enter a number");

                // get user input
                numberOfRounds = kbd.nextLine();
            }

            // convert the user input into an integer
            numRounds = Integer.parseInt(numberOfRounds);
        }

        return numRounds;

    }

    public void startGame(Deck deck)
    {

        int numRounds = setUpRounds();

        int currentRound = 1;

        System.out.println("There will be " + numRounds + " rounds\n");

        Scanner kbd = new Scanner(System.in);

        System.out.println("Welcome to Blackjack");

        System.out.println("__________________________________________________\n");

        Random rand = new Random();

        int whichPlayer = rand.nextInt(players.size());

        while (currentRound <= numRounds)
        {

            System.out.println("ROUND " + currentRound + "\n");

            while (allPlayersAreOut() == false)
            {
                while (players.get(whichPlayer).over21() == false)
                {

                    System.out.println(house.getName() + "'s hand:");

                    house.showPlayerCards();

                    System.out.println("---------------------------------------------------\n");

                    System.out.println("Player " + players.get(whichPlayer).getName() + "'s hand:");

                    players.get(whichPlayer).showPlayerCards();

                    System.out.println("Would you like to hit or stand, " + players.get(whichPlayer).getName() + " ? Please write h for hit or s for stand");

                    String nextPlay = kbd.nextLine();

                    while (nextPlay.equalsIgnoreCase("h") == false && nextPlay.equalsIgnoreCase("s") == false)
                    {
                        System.out.println("Please write h for hit or s for stand");

                        nextPlay = kbd.nextLine();
                    }

                    if (nextPlay.equalsIgnoreCase("h"))
                    {
                        players.get(whichPlayer).addToPlayerCards(deck.draw());

                        if (players.get(whichPlayer).over21())
                        {
                            System.out.println(players.get(whichPlayer).getName() + " has busted\n");

                            players.get(whichPlayer).setOutStatus(true);

                            players.get(whichPlayer).showPlayerCards();

                            System.out.println("__________________________________________________\n");
                        }
                    }

                    else
                    {
                        System.out.println(players.get(whichPlayer).getName() + " stands. Onto the next player\n");

                        players.get(whichPlayer).setOutStatus(true);

                        break;

                    }

                }

                whichPlayer = changeTurn(whichPlayer);

            }

            for (Player p : players)
            {
                p.setOutStatus(false);
                
                
            }

            currentRound++;
            
            deck = new Deck();
            
            
            
            System.out.println("________________________________________________________________________\n");

        }

    }

    private boolean allPlayersAreOut()
    {
        for (Player p : players)
        {
            if (p.isOut() == false)
            {
                return false;
            }

        }
        return true;
    }
    

    /**
     * Determine which player goes next
     * @param currentTurn
     * @return
     */
    public int changeTurn(int currentTurn)
    {
        // If it's the last person's turn then we need to reset whoseTurn to 0
        // So we can start off with the first player in the list of players
        if (currentTurn + 1 == players.size())
        {

            currentTurn = 0;
        }

        // It's the next player's turn, add one more to whoseTurn
        else
        {
            currentTurn++;
        }

        return currentTurn;
    }

    public static void main(String[] args)
    {
        Blackjack game = new Blackjack();

        game.setUpNumOfPlayers();

        game.setUpPlayers();

        game.setUpGame();

    }

}
