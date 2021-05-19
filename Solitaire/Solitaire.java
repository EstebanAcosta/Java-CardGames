package Solitaire;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Map.Entry;

public class Solitaire
{
    private Player p = new Player(1);

    public void setUpPlayer()
    {
        Scanner kbd = new Scanner(System.in);

        System.out.println("Welcome To Solitaire \n");

        System.out.println("What is this player's name:");

        // ask the player for their name
        String playerName = kbd.nextLine();

        p.setName(playerName);

        System.out.println("_____________________________________________________________________");

    }

    public void setUpGame()
    {
        Scanner kbd = new Scanner(System.in);

        // Create a deck of 52 cards
        Deck deck = new Deck();

        // shuffle the game deck
        deck.shuffle();

        // create a tableau
        Hashtable<Integer, ArrayList<Card>> tableau = new Hashtable<Integer, ArrayList<Card>>();

        // loop seven times
        for (int i = 0; i < 7; i++)
        {

            // draw a certain number of cards for each iteration
            ArrayList<Card> cardsDrawn = deck.draw(i + 1);

            // loop through the cards drawn
            for (int j = 0; j < cardsDrawn.size(); j++)
            {
                // if it's the first card in the list of cards drawn
                if (j == 0)
                {
                    // make sure to set that card to flipped
                    cardsDrawn.get(j).setUnflipped(false);

                }

                // if it's any other card in the list of cards
                else
                {
                    // make sure to set that card to unflipped
                    cardsDrawn.get(j).setUnflipped(true);

                }

            }

            // add this new entry to the table,
            // which contains the pile number and the cards that were put down in that pile
            tableau.put(i + 1, cardsDrawn);
        }

        p.setTableau(tableau);
        
        System.out.println("Tableau has been created");

        System.out.println("___________________________________________________________");

        int min = 1;

        int max = 5;

        System.out.println("What is the number of rounds you want?\n");

        System.out.println("The min # is " + min + " and the max # is " + max);

        String numRounds = "";

        // convert input into an integer
        int numberOfRounds = 0;

        // Continue promoting the player until they provide
        // a number between 0 and 5
        while (numberOfRounds > max || numberOfRounds < min)
        {
            System.out.println("Please enter a number that is between " + min + " and " + max);

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
        
        System.out.println("Player wants to play " + numberOfRounds + " of Solitaire\n");

        System.out.println("_________________________________________________________________\n");

        startGame(deck, numberOfRounds);
    }

    public void startGame(Deck deck, int rounds)
    {

        System.out.println("Solitaire Has Started, " + p.getName() + " \n");

        Scanner kbd = new Scanner(System.in);

        p.showPlayerTableau();

        // create a foundation
        // There will be four piles above the tableau (the seven piles )
        // These four piles will all start with an ace
        // The foundation will be empty up until the point the player can
        // start putting aces of each suit down
        Hashtable<Integer, ArrayList<Card>> foundation = new Hashtable<Integer, ArrayList<Card>>();

        for (int i = 0; i < 4; i++)
        {
            foundation.put(i + 1, new ArrayList<Card>());
        }

        while (endGame())
        {
            if (p.hasAceInTableau())
            {
                
            }
            
           

        }

    }       
    

    public boolean endGame()
    {
        
        return false;
    }

    public static void main(String[] args)
    {
        Solitaire s = new Solitaire();

        s.setUpPlayer();

        s.setUpGame();

    }
}
