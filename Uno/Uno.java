package Uno;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Blackjack.Player;

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
    }

    public void setUpGame()
    {

        startGame();
    }

    public void startGame()
    {
        System.out.println("Welcome To Uno");

        Random rand = new Random();

        int whichPlayer = rand.nextInt(players.size());
    }

    public static void main(String[] args)
    {
        Uno game = new Uno();

        game.setUpNumOfPlayers();

        game.setUpPlayers();

        game.setUpGame();
    }
}
