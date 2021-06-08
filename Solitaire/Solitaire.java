package Solitaire;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Map.Entry;

public class Solitaire
{
    private Player p = new Player(1);

    private Hashtable<Integer, ArrayList<Card>> foundation;

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

        System.out.println("Player wants to play " + numberOfRounds + " rounds of Solitaire\n");

        System.out.println("_________________________________________________________________\n");

        startGame(deck, numberOfRounds);
    }

    public void startGame(Deck deck, int rounds)
    {

        System.out.println("Solitaire Has Started, " + p.getName() + " \n");

        Scanner kbd = new Scanner(System.in);

        int currentRound = 1;

        // create a foundation
        // There will be four piles above the tableau (the seven piles )
        // These four piles will all start with an ace
        // The foundation will be empty up until the point the player can
        // start putting aces of each suit down
        foundation = new Hashtable<Integer, ArrayList<Card>>();

        // Go through each pile of the foundation
        for (int i = 0; i < 4; i++)
        {
            // Make the key the pile number and the value an empty array list for future cards
            foundation.put(i + 1, new ArrayList<Card>());
        }

        // continue looping until all rounds have been completed
        while (currentRound <= rounds)
        {

            // Continue looping until the game ends either with the player winning it
            // or with a stalemate
            while (endGame(deck))
            {
                System.out.println("Round " + currentRound + "\n");

                if (doesFoundationHavePiles())
                {
                    printFoundation();
                }

                p.showPlayerTableau();

                // Find out if there are any aces in the top flipped cards of the tableau
                if (p.hasAceInTableau())
                {
                    // find all the aces and store them in a list
                    ArrayList<Card> aces = p.findAces();

                    // loop through each pile of the foundation
                    for (Entry<Integer, ArrayList<Card>> thatPile : foundation.entrySet())
                    {

                        if (aces.isEmpty())
                        {
                            break;
                        }
                        // if this pile is completely empty
                        // (there are no aces to start the foundation)
                        // and there are still aces left to the side
                        else if (thatPile.getValue().size() == 0)
                        {
                            // remove one card from the list of aces and add it
                            // to the foundation
                            thatPile.getValue().add(aces.remove(0));
                        }

                    }

                }

                printFoundation();

                System.out.println("------------------------------------------------------------------");

                break;
            }

            currentRound++;

        }

    }

    public boolean canMoveWithinTableau()
    {
        return false;
    }

    public boolean canMoveToFoundation()
    {
        return false;
    }

    /**
     * Determines if the player can place a card in the empty space of the tableau
     * @param deck
     * @return
     */
    public boolean canMoveToEmptySpaceInTableau(Deck deck)
    {
        if (p.isThereEmptySpaceInTableau() && deckHasAKing(deck))
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    /***
     * Checks to see if a king is in the deck
     * @param deck
     * @return true if the deck has a king, false if the deck doesn't have a king
     */
    public boolean deckHasAKing(Deck deck)
    {
        if (deck.isEmpty())
        {
            return false;
        }

        for (Card c : deck.getDeck())
        {
            if (c.getRank() == Rank.KING)
            {
                return true;
            }
        }

        return false;
    }

    /***
     * This method determines when the game ends
     * @param deck
     * @return
     */
    public boolean endGame(Deck deck)
    {
        if (canMoveWithinTableau() || canMoveToEmptySpaceInTableau(deck)
        || canMoveToFoundation() || deck.getSize() > 0)
        {
            return true;
        }

        return false;
    }

    public void printFoundation()
    {

        System.out.println("Foundation: \n");

        String[][] f = new String[4][10];

        for (int row = 0; row < f.length; row++)
        {
            for (int col = 0; col < f[row].length; col++)
            {

                f[row][col] = "";
            }

        }
        int pileNum = 0;
        
        // loop through the tableau table
        for (Entry<Integer, ArrayList<Card>> thatPile : foundation.entrySet())
        {

            int posit = 0;

            for (Card c : thatPile.getValue())
            {

                if (c.isUnflipped() == false)
                {
                    f[posit][pileNum] = "( " + c + " )   ";

                }

                else
                {
                    f[posit][pileNum] = "( Card " + (posit + 1) + " )                  ";

                }

                posit++;
            }

            pileNum++;

        }

        for (int i = 0; i < f.length; i++)
        {
            System.out.print("Pile " + (i + 1) + "                      ");
        }

        System.out.println();

        for (int row = 0; row < f.length; row++)
        {
            for (int col = 0; col < f[row].length; col++)
            {

                System.out.print(f[row][col]);
            }

            System.out.println();
            System.out.println();
        }

    }

    /**
     * This method looks to see if the foundation has any starting piles
     * @return true if there is at least one pile in the foundation otherwise
     *         return false
     */
    public boolean doesFoundationHavePiles()
    {
        // loop through the piles of the foundation
        for (Entry<Integer, ArrayList<Card>> thatPile : foundation.entrySet())
        {
            // if this pile has at least one card
            if (thatPile.getValue().size() > 0)
            {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args)
    {
        Solitaire s = new Solitaire();

        s.setUpPlayer();

        s.setUpGame();

    }
}
