package Solitaire;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Map.Entry;

public class Solitaire
{
    private Player p;

    public void setUpPlayer()
    {
        Scanner kbd = new Scanner(System.in);

        System.out.println("What is this player's name:");

        String playerName = kbd.nextLine();

        // ask each player for their name
        p.setName(playerName);

        System.out.println("_____________________________________________________________________");

    }

    public void setUpGame()
    {

        // Create a deck of 52 cards
        Deck deck = new Deck();

        // shuffle the game deck
        deck.shuffle();

        Hashtable<Integer, ArrayList<Card>> tableau = new Hashtable<Integer, ArrayList<Card>>();

        for (int i = 0; i < 7; i++)
        {

            ArrayList<Card> cardsDrawn = deck.draw(i + 1);

            tableau.put(i + 1, cardsDrawn);
        }
    }

    public void startGame(Hashtable<Integer, ArrayList<Card>> tableau, Deck deck)
    {
        
        for (Entry<Integer, ArrayList<Card>> entry :  tableau.entrySet())
        {
            
        }
        
        while (endGame())
        {

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
