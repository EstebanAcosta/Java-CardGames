package Uno;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Uno
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
        System.out.println("Welcome To Uno\n");
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
        Random rand = new Random();

        Deck deck = new Deck();

        Scanner kbd = new Scanner(System.in);

        for (int i = 0; i < players.size(); i++)
        {
            System.out.println("What is player " + (i + 1) + "'s name ?");

            Player p = new Player(i + 1);

            String name = kbd.nextLine();

            players.get(i).setName(name);

            players.get(i).setOutStatus(false);

            players.get(i).setPlayerCards(deck.draw(7));

            System.out.println();

            System.out.println("__________________________________________________\n");

        }

        startGame(deck);
    }

    public void startGame(Deck deck)
    {
        System.out.println("Welcome To Uno");

        Scanner kbd = new Scanner(System.in);

        Random rand = new Random();

        int whichPlayer = rand.nextInt(players.size());

        ArrayList<Card> middle = new ArrayList<Card>();

        middle.add(deck.draw());

        while (allPlayersAreOut() == false)
        {

            System.out.println("Middle Card: ");

            System.out.println(middle.get(middle.size() - 1));
            
            System.out.println();

            System.out.println("It's " + players.get(whichPlayer).getName() + "'s turn:");

            players.get(whichPlayer).showPlayerCards();

            System.out.println("Which card would you like put down in the middle ?");

            int whichCard = 0;

            String selectedCard = "";

            while (whichCard < 2 || whichCard > 6)
            {
                System.out.println("Please keep the selected card number between 2 and 6");

                // get user input
                selectedCard = kbd.nextLine();

                // if user gives a non-numerical answer
                // continue prompting user until they give a numeric answer
                while (!selectedCard.matches("[0-9]+"))
                {
                    System.out.println("Please enter a number");

                    // get user input
                    selectedCard = kbd.nextLine();
                }

                // convert the user input into an integer
                whichCard = Integer.parseInt(selectedCard);
            }

            System.out.println("__________________________________________________\n");

            whichPlayer = changeTurn(whichPlayer);

        }
    }

    /**
     * Determines if all the players in the game are out
     * @return
     */
    public boolean allPlayersAreOut()
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
        Uno game = new Uno();

        game.setUpNumOfPlayers();

        game.setUpPlayers();

    }
}
