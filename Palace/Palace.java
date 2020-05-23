package Palace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/***
 * @author esteban acosta
 */
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

            ArrayList<Card> playerPalace = new ArrayList<Card>();

            for (int j = 0; j < playerCards.size(); j++)
            {
                if (playerCards.get(i).isFaceDown())
                {
                    playerPalace.add(playerCards.get(i));

                    playerCards.remove(i);
                }
            }

            // give each player their 6 cards
            players.get(i).setPlayerCards(playerCards);

            // put 3 of the face down cards in the player's palace
            players.get(i).setPlayerPalace(playerPalace);

            players.get(i).setIsOutStatus(false);

            System.out.println(players.get(i).getNumPlayerCards() + " cards have been drawn from the deck\n");
            System.out.println("Three of the cards are faced down. The rest are in the player's hand");
            System.out.println(players.get(i).getName() + " has " + players.get(i).getNumPlayerCards() + " cards");
            System.out.println("__________________________________________________\n");
        }

        setUpPalace(deck);

    }

    public void setUpPalace(Deck deck)
    {

        Scanner kbd = new Scanner(System.in);

        for (Player p : players)
        {
            boolean notSure = true;

            System.out.println("Player " + p.getPlayerId() + " " + p.getName());

            String choice = "";

            int numChoice = 0;

            while (notSure)
            {
                ArrayList<Card> savedChoices = new ArrayList<Card>();

                // create a list of 7 choices for the player
                ArrayList<Integer> availableChoices = p.getAvailablePlayerCards();

                // ask the user three times which card they wish to put on their palace
                for (int i = 0; i < 3; i++)
                {
                    System.out.println("Choose a card you would like to put on your palace? ");

                    // show the cards in the player's hands
                    p.showPlayerCards();

                    while (availableChoices.contains(numChoice) == false)
                    {
                        System.out.println("Please choose a card that is available");

                        choice = kbd.nextLine();

                        while (!choice.matches("[0-9]+"))
                        {
                            System.out.println("Please enter a number");

                            choice = kbd.nextLine();
                        }

                        numChoice = Integer.parseInt(choice);
                    }

                    savedChoices.add(p.getCardInPlayerCards(numChoice));

                    p.addToPlayerPalace(p.removeFromPlayerCards(numChoice));
                    
   

                    numChoice = 0;

                    System.out.println();
                }
                System.out.println(p.getPlayerPalace().size() + "sizee");

                System.out.println("This is your palace");
                p.showPlayerPalace();

                System.out.println("Are you sure these are the cards you wish to place on your palace? y/n ");

                String confirmation = kbd.nextLine();

                while (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n"))
                {
                    System.out.println("Please enter y for yes or n for no");

                    confirmation = kbd.nextLine();
                }

                if (confirmation.equalsIgnoreCase("y"))
                {
                    notSure = false;
                }

                else
                {
                    p.addMultipleToPlayerCards(savedChoices);

                    p.removeMultipleFromPlayerPalace(savedChoices);
                }

            }

            System.out.println("__________________________________________________\n");

        }
        // startGame(deck);
    }

    public void startGame(Deck deck)
    {
        System.out.println("Welcome to Palace");

        Random rand = new Random();

        int whoseTurn = rand.nextInt(players.size());

        while (areAllPlayersOut() == false)
        {
            System.out.println("It's player " + players.get(whoseTurn).getPlayerId() + " " + players.get(whoseTurn).getName() + "'s turn");

            players.get(whoseTurn).showPlayerCards();

            break;
        }
    }

    public boolean areAllPlayersOut()
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

    public static void main(String[] args)
    {
        Palace p = new Palace();

        p.setUpNumOfPlayers();

        p.setUpPlayers();

    }
}
