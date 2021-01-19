package Rummy;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Rummy
{
    private ArrayList<Player> players = new ArrayList<Player>();
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

    public void setUpNumOfPlayers()
    {
        Scanner kbd = new Scanner(System.in);
        System.out.println("Welcome To Rummy\n");
        System.out.println("How many players are going to play in the game?");
        System.out.println("(Mininumum # of players is 2 and Maximum # of players is 6) \n");

        String numberOfPlayers = "";

        // Convert the string input into an integer
        int numPlayers = 0;

        // If the user puts a number greater than 4 or less than 2
        // Continue prompting the user until they give
        // a number between 2 and 4
        while (numPlayers < 2 || numPlayers > 6)
        {
            System.out.println("Please keep the number of players between 2 and 6");

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
        Scanner kbd = new Scanner(System.in);

        // Create a deck of 52 cards
        Deck deck = new Deck();

        // shuffle the game deck
        deck.shuffle();

        // calculate how many cards we are supposed to distribute to each player
        int howManyCardsToEachPlayer = 0;


        if(players.size() == 2)
        {
            howManyCardsToEachPlayer = 10;
        }
        
        else if(players.size() == 3 || players.size() == 4)
        {
            howManyCardsToEachPlayer = 7;
        }
        
        else
        {
            howManyCardsToEachPlayer = 6;
        }

        // loop through the list of players and give each player a name and their
        // cards
        for (int i = 0; i < players.size(); i++)
        {
            System.out.println("What is player " + (i + 1) + "'s name:");

            String playerName = kbd.nextLine();

            // ask each player for their name
            players.get(i).setName(playerName);

            // draw a predetermined number of cards from the deck
            ArrayList<Card> cardsPerPerson = deck.draw(howManyCardsToEachPlayer);

            // give this player the predetermined number of cards
            players.get(i).addMultipleToPlayerHand(cardsPerPerson);

            // give each player a score of 0
            players.get(i).addsPointsWon(0);

            System.out.println(players.get(i).getName() + " has " + players.get(i).getNumOfPlayerCards() + " cards");

            System.out.println("__________________________________________________\n");

        }
    }

    public void setUpGame(Deck deck)
    {
        Scanner kbd = new Scanner(System.in);

        System.out.println("What is the number of rounds you want?\n");
        System.out.println("The min # is 1 and the max # is 5");
        String numRounds = kbd.nextLine();

        // if player gives a non-numerical answer
        // continue prompting player until they give a numeric answer
        while (!numRounds.matches("[0-9]+"))
        {
            System.out.println("Please enter a number for the number of rounds in this new game");

            numRounds = kbd.nextLine();
        }

        // convert input into an integer
        int numberOfRounds = Integer.parseInt(numRounds);

        // Continue promoting the player until they provide
        // a number between 0 and 5
        while (numberOfRounds > 5 || numberOfRounds < 1)
        {
            System.out.println("Please enter a number that is between 1 and 5");

            // get player input
            numRounds = kbd.nextLine();

            // continue prompting player until the player gives a number
            while (!numRounds.matches("[0-9]+"))
            {
                System.out.println("Please enter a number for the number of rounds in this new game");

                numRounds = kbd.nextLine();
            }
            // convert the player input into an integer
            numberOfRounds = Integer.parseInt(numRounds);
        }

        System.out.println("__________________________________________________\n");

        startGame(deck, numberOfRounds);
    }

    public void startGame(Deck stack, int numRounds)
    {
        ArrayList<Card> discardPile = new ArrayList<Card>();
        
        //flip over a card from the stack and put it in the discard pile
        discardPile.add(stack.draw());
        
        int currentRound = 0;
        
        Random rand = new Random();
        
        //randomly choose who can start the game
        int whoseTurn = rand.nextInt(players.size());
        
        while(currentRound != numRounds)
        {
            while(true)
            {
                System.out.println("Round " + currentRound);
                
                whoseTurn = changeTurn(whoseTurn);
            }

        }
        
        
    }

    /***
     * Determine which player goes next in the game
     * @param currentTurn
     * @return
     */
    public int changeTurn(int currentTurn)
    {
        // If it's the last person's turn then we need to reset whoseTurn to 0
        // So we can start off with the first player in the list of players
        if (currentTurn + 1 >= players.size())
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
        Rummy rummy = new Rummy();
        
        rummy.setUpNumOfPlayers();
    }
}
