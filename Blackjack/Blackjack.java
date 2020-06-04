package Blackjack;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Blackjack
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

            System.out.println();

        }

        int whoIsDealer = rand.nextInt(players.size());

        players.get(whoIsDealer).setDealerStatus(true);

        System.out.println(players.get(whoIsDealer).getName() + " is the dealer");

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

            if (p.isDealer())
            {

                two.setFaceDown(true);

            }
            else
            {

                two.setFaceDown(false);
            }

            p.addToPlayerCards(one);

            p.addToPlayerCards(two);

            System.out.println("Two cards have been added to " + p.getName() + "'s hand\n");
        }

        startGame(deck);
    }

    public void startGame(Deck deck)
    {

        Scanner kbd = new Scanner(System.in);

        System.out.println("Welcome to Blackjack");
        
        System.out.println("__________________________________________________\n");

        Player dealer = null;

        for (Player p : players)
        {
            if (p.isDealer())
            {

                dealer = p;
            }
        }

        int whichPlayer = 0;

        while (whichPlayer < players.size())
        {

            if (players.get(whichPlayer).isDealer())
            {
                whichPlayer++;

                continue;
            }

            while (players.get(whichPlayer).over21() == false)
            {
                           
                System.out.println("Dealer " + dealer.getName() + "'s hand:");

                dealer.showPlayerCards();

                System.out.println("---------------------------------------------------\n");

                System.out.println("Player " + players.get(whichPlayer).getName() + "'s hand:");

                players.get(whichPlayer).showPlayerCards();
                
                System.out.println("Would you like to hit or stand, " + players.get(whichPlayer).getName() + " ? h or s");

                String nextPlay = kbd.nextLine();

                while (nextPlay.equalsIgnoreCase("h") == false && nextPlay.equalsIgnoreCase("s") == false)
                {
                    System.out.println("Please either write h for hit or s for stand");

                    nextPlay = kbd.nextLine();
                }

                if (nextPlay.equalsIgnoreCase("h"))
                {
                    players.get(whichPlayer).addToPlayerCards(deck.draw());                  
                    
                    if(players.get(whichPlayer).over21())
                    {
                        System.out.println(players.get(whichPlayer).getName() + "  has busted\n");
                    }
                }
                
                else
                {
                    System.out.println(players.get(whichPlayer).getName() + " stands. Onto the next player\n");
                    
                    break;

                }

            }

            whichPlayer++;

        }

    }

    public static void main(String[] args)
    {
        Blackjack game = new Blackjack();

        game.setUpNumOfPlayers();

        game.setUpPlayers();

        game.setUpGame();

    }

}
