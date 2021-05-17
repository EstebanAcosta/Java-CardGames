package Solitaire;

import java.util.Scanner;

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
        
        System.out.println("_____________________________________________________________________0");

    }
    
    public void setUpGame()
    {
        
    }

    public void startGame()
    {
        // Create a deck of 52 cards
        Deck deck = new Deck();

        // shuffle the game deck
        deck.shuffle();
        
        while(endGame())
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
