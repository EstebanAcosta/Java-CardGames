package Blackjack;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Blackjack
{

    private ArrayList<Player> players = new ArrayList<Player>();

    public void setUpPlayers()
    {
        Random rand = new Random();
        
        Scanner kbd = new Scanner(System.in);

        for (int i = 0; i < 2; i++)
        {
            System.out.println("What is the player's name ? ");

            Player p = new Player(i + 1);

            String name = kbd.nextLine();

            p.setName(name);

            players.add(p);
            
            System.out.println();

        }
        
        int whoIsDealer = rand.nextInt(2);
        
        players.get(whoIsDealer).setIsDealerStatus(true);
        
        System.out.println(players.get(whoIsDealer).getName() + " is the dealer");
        
        System.out.println();
    }
    
    public void startGame()
    {

        Deck deck = new Deck();

        deck.shuffle();

        ArrayList<Card> middleCards = new ArrayList<Card>();

        middleCards.add(deck.draw());

        System.out.println("Welcome to Blackjack");

        System.out.println("Middle Card: " + middleCards.get(middleCards.size() - 1));

        while (over21() == false)
        {

        }
    }

    public boolean over21()
    {
        return false;
    }

    public static void main(String[] args)
    {
        Blackjack game = new Blackjack();

        game.setUpPlayers();

        game.startGame();
    }

}
