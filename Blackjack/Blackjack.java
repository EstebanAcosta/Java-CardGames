package Blackjack;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Blackjack
{

    private ArrayList<Player> players = new ArrayList<Player>();

    private Player house = new Player();

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

            players.get(i).setOutStatus(false);

            System.out.println();

            System.out.println("__________________________________________________\n");

        }

        house.setName("The House");

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

            two.setFaceDown(false);

            p.addToPlayerCards(one);

            p.addToPlayerCards(two);

            System.out.println("Two cards have been added to " + p.getName() + "'s hand\n");
        }

        Card one = deck.draw();

        Card two = deck.draw();

        one.setFaceDown(true);

        two.setFaceDown(false);

        house.addToPlayerCards(one);

        house.addToPlayerCards(two);

        System.out.println("Two cards have been added to " + house.getName() + "'s hand\n");

        System.out.println("__________________________________________________\n");

        startGame(deck);
    }

    public int setUpRounds()
    {
        Scanner kbd = new Scanner(System.in);

        System.out.println("How many rounds would you like to play?\n ");

        System.out.println("The limit is ten rounds\n");

        String numberOfRounds = "";

        // Convert the string input into an integer
        int numRounds = 0;

        // If the user puts a number greater than 4 or less than 2
        // Continue prompting the user until they give
        // a number between 2 and 4
        while (numRounds < 1 || numRounds > 10)
        {
            System.out.println("Please keep the number of rounds between 1 and 10");

            // get user input
            numberOfRounds = kbd.nextLine();

            // if user gives a non-numerical answer
            // continue prompting user until they give a numeric answer
            while (!numberOfRounds.matches("[0-9]+"))
            {
                System.out.println("Please enter a number");

                // get user input
                numberOfRounds = kbd.nextLine();
            }

            // convert the user input into an integer
            numRounds = Integer.parseInt(numberOfRounds);
        }

        return numRounds;

    }

    public void startGame(Deck deck)
    {

        int numRounds = setUpRounds();

        int currentRound = 1;

        System.out.println("There will be " + numRounds + " rounds\n");

        Scanner kbd = new Scanner(System.in);

        System.out.println("Welcome to Blackjack");

        System.out.println("__________________________________________________\n");

        // randomly select a player
        int whichPlayer = 0;

        // continue looping until the current is over
        while (currentRound <= numRounds)
        {

            System.out.println("ROUND " + currentRound + "\n");

            // continue looping until all players have either busted, stood or made 21
            while (allPlayersAreOut() == false)
            {
                // continue looping until this player busts
                while (players.get(whichPlayer).over21() == false)
                {

                    System.out.println("---------------------------------------------------\n");

                    // print the house's name
                    System.out.println(house.getName() + "'s hand:");

                    // show the house's hand
                    house.showPlayerCards();

                    System.out.println("---------------------------------------------------\n");

                    System.out.println("Player " + players.get(whichPlayer).getName() + "'s hand:                           Current Score: " + players.get(whichPlayer).getTotalSumOfCards());

                    players.get(whichPlayer).showPlayerCards();

                    if (players.get(whichPlayer).getTotalSumOfCards() == 21)
                    {
                        System.out.println("Congrats. You made 21.");

                        // the player is now out for this round
                        players.get(whichPlayer).setOutStatus(true);

                        // display this player's cards to the screen
                        players.get(whichPlayer).showPlayerCards();

                        // since they never busted , set that to false
                        players.get(whichPlayer).setHasBusted(false);

                        // if they are the last player, print this
                        if (whichPlayer + 1 == players.size())
                        {
                            System.out.println("Please enter n for the results ");

                        }

                        // if they aren't the last player, print this
                        else
                        {
                            System.out.println("Please enter n for the next player's turn: ");

                        }

                        // get user input
                        String next = kbd.nextLine();

                        // continue looping until given the correct input
                        while (!next.equalsIgnoreCase("n"))
                        {
                            System.out.println("Please try again. Please enter n for next");

                            // get user input
                            next = kbd.nextLine();
                        }

                        System.out.println("__________________________________________________\n");

                        // break out of the loop so it will be the next player's turn
                        break;
                    }

                    System.out.println("Would you like to hit or stand, " + players.get(whichPlayer).getName() + " ? Please write h for hit or s for stand");

                    // get user input
                    String nextPlay = kbd.nextLine();

                    // continue looping until the user either presses h or s
                    while (nextPlay.equalsIgnoreCase("h") == false && nextPlay.equalsIgnoreCase("s") == false)
                    {
                        System.out.println("Please write h for hit or s for stand");

                        // get user input
                        nextPlay = kbd.nextLine();
                    }

                    // if the user input is an H
                    if (nextPlay.equalsIgnoreCase("h"))
                    {
                        // draw a card and put in their hand
                        players.get(whichPlayer).addToPlayerCards(deck.draw());

                        // if their total number of cards is over 21
                        if (players.get(whichPlayer).over21())
                        {
                            System.out.println("\n" + players.get(whichPlayer).getName() + " HAS BUSTED\n");

                            // make sure they are out for this round
                            players.get(whichPlayer).setOutStatus(true);

                            // make sure to set true to their bust status
                            players.get(whichPlayer).setHasBusted(true);

                            // display their cards to the screen
                            players.get(whichPlayer).showPlayerCards();

                            // if they are the last player, print this
                            if (whichPlayer + 1 == players.size())
                            {
                                System.out.println("Please enter n for the results ");

                            }

                            // if they aren't the last player, print this
                            else
                            {
                                System.out.println("Please enter n for the next player's turn: ");

                            }

                            // get user input
                            String next = kbd.nextLine();

                            while (!next.equalsIgnoreCase("n"))
                            {
                                System.out.println("Please try again. Please enter n for next");

                                // get user input
                                next = kbd.nextLine();
                            }

                            System.out.println("__________________________________________________\n");
                        }

                    }

                    else
                    {

                        // if they are the last player, print this
                        if (whichPlayer + 1 == players.size())
                        {
                            System.out.println("Time for the results \n ");

                        }

                        // if they aren't the last player, print this
                        else
                        {
                            System.out.println(players.get(whichPlayer).getName() + " stands. Onto the next player\n");
                        }

                        players.get(whichPlayer).setOutStatus(true);

                        players.get(whichPlayer).setHasBusted(false);

                        System.out.println("__________________________________________________\n");

                        break;

                    }

                }

                // it's the next player's turn
                whichPlayer = changeTurn(whichPlayer);

            }
            System.out.println("__________________________________________________________________________________________\n");

            System.out.println("Now it's the House's turn to flip their card\n ");

            // go through the house's cards and flip their unflipped card
            for (Card c : house.getPlayerCards())
            {
                if (c.isFaceDown())
                {
                    c.setFaceDown(false);
                }

            }

            // display the house's cards to the screen
            house.showPlayerCards();

            // make the house draw a card until their total sum
            while (house.getTotalSumOfCards() < 17)
            {
                house.addToPlayerCards(deck.draw());

            }

            // if the house is over 21
            if (house.over21())
            {
                System.out.println("The House has busted\n");

                // all players have won the game
                for (Player p : players)
                {
                    System.out.println(p.getName() + " is the winner of this round \n");
                }
            }

            else
            {
                // show the House's total sum
                System.out.println("The House total sum is : " + house.getTotalSumOfCards() + "\n");

                // loop through the players
                for (Player p : players)
                {
                    // if the player hasn't busted and their total sum of cards is greater than or equal to the house's
                    if (p.HasBusted() == false && (p.getTotalSumOfCards() >= house.getTotalSumOfCards() || p.getTotalSumOfCards() == 21))
                    {
                        // print out that they have won the game
                        System.out.println(p.getName() + " is the winner of this round \n");
                    }
                }
            }

            System.out.println("Please enter n for the next round: ");

            // get user input
            String next = kbd.nextLine();

            // continue looping until given the correct input
            while (!next.equalsIgnoreCase("n"))
            {
                System.out.println("Please try again. Please enter n for next");

                // get user input
                next = kbd.nextLine();
            }

            // make sure to reset the out status for all players for the following round
            for (Player p : players)
            {
                p.setOutStatus(false);

                // clear the cards in their hand
                p.getPlayerCards().clear();

            }

            // clear the cards in the house's hand
            house.getPlayerCards().clear();

            // add one more to the round
            currentRound++;

            // start with a fresh deck
            deck = new Deck();

            // draw two new cards for each player
            for (Player p : players)
            {
                p.addToPlayerCards(deck.draw());

                p.addToPlayerCards(deck.draw());
            }

            // draw a card
            Card unflipped = deck.draw();

            // put it face down
            unflipped.setFaceDown(true);

            // add another card to the house's hand
            house.addToPlayerCards(deck.draw());

            // add the unflipped card to the house's hand
            house.addToPlayerCards(unflipped);

            System.out.println("________________________________________________________________________\n");

        }

    }

    /***
     * Loop through the player's to determine if they are out for that round
     * @return
     */
    private boolean allPlayersAreOut()
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

    /**
     * Determine which player goes next
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
        Blackjack game = new Blackjack();

        game.setUpNumOfPlayers();

        game.setUpPlayers();

        game.setUpGame();

    }

}
