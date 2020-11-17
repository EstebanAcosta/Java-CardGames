package GoFish;

import java.util.ArrayList;
import java.util.Scanner;

public class GoFish
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
        System.out.println("(Mininumum # of players is 2 and Maximum # of players is 6) \n");

        String numberOfPlayers = "";

        // Convert the string input into an integer
        int numPlayers = 0;

        // If the player puts a number greater than 4 or less than 2
        // Continue prompting the player until they give
        // a number between 2 and 4
        while (numPlayers < 2 || numPlayers > 6)
        {
            System.out.println("Please keep the number of players between 2 and 6");

            // get player input
            numberOfPlayers = kbd.nextLine();

            // if player gives a non-numerical answer
            // continue prompting player until they give a numeric answer
            while (!numberOfPlayers.matches("[0-9]+"))
            {
                System.out.println("Please enter a number");

                // get player input
                numberOfPlayers = kbd.nextLine();
            }

            // convert the player input into an integer
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

        //give each player a certain number of cards depending on how many players there are in the game
        if (players.size() < 4)
        {
            for (Player p : players)
            {
                p.addMultipleToPlayerHand(deck.draw(7));
            }
        }

        else
        {
            for (Player p : players)
            {
                p.addMultipleToPlayerHand(deck.draw(5));
            }
        }

        // loop through the list of players and ask each player for a name a
        for (int i = 0; i < players.size(); i++)
        {
            System.out.println("What is player " + (i + 1) + "'s name:");

            // ask each player for their name
            String playerName = kbd.nextLine();

            // store their name
            players.get(i).setName(playerName);

            System.out.println(players.get(i).getName() + " has " + players.get(i).getNumPlayerCards() + " cards");

            System.out.println("__________________________________________________\n");
        }
    }

    public void setUpGame()
    {

    }

    public void startGame()
    {

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

        GoFish game = new GoFish();

        game.setUpNumOfPlayers();

        game.setUpPlayers();

        game.setUpGame();

    }
}
