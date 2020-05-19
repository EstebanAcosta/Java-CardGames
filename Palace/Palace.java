package Palace;

import java.util.ArrayList;
import java.util.Scanner;

public class Palace
{
    private ArrayList<Player> players = new ArrayList<Player>();

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

        System.out.println("How many players are going to play in the game?");
        System.out.println("(Mininumum # of players is 2 and Maximum # of players is 4) \n");

        String numberOfPlayers = "";

        // Convert the string input into an integer
        int numPlayers = 0;

        // If the user puts a number greater than 4 or less than 2
        // Continue prompting the user until they give
        // a number between 2 and 4
        while (numPlayers < 2 || numPlayers > 4)
        {
            System.out.println("Please keep the number of players between 2 and 4");

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

        // loop through the list of players and give each player a name and their 10
        // cards
        for (int i = 0; i < players.size(); i++)
        {
            System.out.println("What is player " + (i + 1) + "'s name:");

            String playerName = kbd.nextLine();

            // give each player their name
            players.get(i).setName(playerName);

            // Draw 10 cards from the deck
            ArrayList<Card> playerCards = deck.draw(9);

            // Make sure that the cards these players have are set to unflipped
            for (int j = 0; j < playerCards.size(); j++)
            {
                if (j < 3)
                {
                    playerCards.get(j).setFaceDown(true);
                }
                else
                {
                    playerCards.get(j).setFaceDown(false);
                }

            }

            // give each player their 9 cards
            players.get(i).setPlayerCards(playerCards);

            System.out.println(players.get(i).getNumPlayerCards() + " cards have been drawn from the deck\n");
            System.out.println("Three of the cards are faced down. The rest are in the player's hand");
            System.out.println(players.get(i).getName() + " has " + players.get(i).getNumPlayerCards() + " cards");
            System.out.println("__________________________________________________\n");
        }

        startGame(deck);
    }


    public void startGame(Deck deck)
    {
        System.out.println("Welcome to Palace");
    }

    public void endGame()
    {

    }

    public static void main(String[] args)
    {
        Palace p = new Palace();

        p.setUpNumOfPlayers();

        p.setUpPlayers();
        
    }
}
