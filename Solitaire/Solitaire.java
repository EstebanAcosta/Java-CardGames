package Solitaire;

import java.util.Scanner;

public class Solitaire
{
    private Player p;



    public void setUpPlayer()
    {
        Scanner kbd = new Scanner(System.in);

        System.out.println("What is player 1's name:");

        String playerName = kbd.nextLine();

        // ask each player for their name
        p.setName(playerName);

        // Create a deck of 52 cards
        Deck deck = new Deck();

        // shuffle the game deck
        deck.shuffle();
    }
    
    public void startGame()
    {
        
    }
    
    public static void main(String[] args)
    {
        
    }
}
