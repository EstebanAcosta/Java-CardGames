package President;

import java.util.ArrayList;
import java.util.Scanner;

public class President
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
        System.out.println("Welcome To President \n");

        Scanner kbd = new Scanner(System.in);

        System.out.println("How many players are going to play in the game?");
        System.out.println("(Mininumum # of players is 4 and Maximum # of players is 6) ");

        String numberOfPlayers = kbd.nextLine();

        // if user gives a non-numerical answer
        // continue prompting user until they give a numeric answer
        while (!numberOfPlayers.matches("[0-9]+"))
        {
            System.out.println("Please enter a number for the number of players in this game");

            numberOfPlayers = kbd.nextLine();
        }

        // Convert the string input into an integer
        int numPlayers = Integer.parseInt(numberOfPlayers);

        // If the user puts a number greater than 6 or less than 4
        // Continue prompting the user until they give
        // a number between 2 and 4
        while (numPlayers < 4 || numPlayers > 6)
        {
            System.out.println("Please keep the number of players between 4 and 6");

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

        System.out.println("\n" + numPlayers + " players have been added to the game\n");
    }

    public void setUpPlayers()
    {
        Scanner kbd = new Scanner(System.in);

        // Create a deck of 52 cards
        Deck deck = new Deck();

        // shuffle the game deck
        deck.shuffle();

        System.out.println(deck.getSize());

        // calculate how many cards we are supposed to distribute to each player
        int howManyCardsToEachPlayer = deck.getSize() / players.size();

        // if we can't give each player the same amount of cards
        if (deck.getSize() % players.size() != 0)
        {
            // calculate how many cards would be left over if we gave each player
            // roughly the same number of cards
            int leftOverCards = deck.getSize() % players.size();

            // and then give a certain number of random players
            // (that number is determined by the number of leftover cards left)
            // a card
            for (int i = 0; i < leftOverCards; i++)
            {
                players.get(i).addOneToPlayerHand(deck.draw());
            }

        }

        System.out.println("remain " + deck.getSize() % players.size());

        // loop through the list of players and give each player a name and their 10
        // cards
        for (int i = 0; i < players.size(); i++)
        {
            System.out.println("What is player " + (i + 1) + "'s name:");

            String playerName = kbd.nextLine();

            // give each player their name
            players.get(i).setName(playerName);

            ArrayList<Card> cardsPerPerson = deck.draw(howManyCardsToEachPlayer);

            players.get(i).addMultipleToPlayerHand(cardsPerPerson);

            System.out.println(players.get(i).getName() + " has " + players.get(i).getNumOfPlayerCards() + " cards");
            
            System.out.println("__________________________________________________\n");

        }
    }

    public static void main(String[] args)
    {
        President pres = new President();
        pres.setUpNumOfPlayers();
        pres.setUpPlayers();
    }
}
