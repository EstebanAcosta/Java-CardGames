package Rummy;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Rummy
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
        System.out.println("Welcome To Rummy\n");
        System.out.println("How many players are going to play in the game?");
        System.out.println("(Mininumum # of players is 2 and Maximum # of players is 6) \n");

        String numberOfPlayers = "";

        // Convert the string input into an integer
        int numPlayers = 0;

        // If the user puts a number greater than 4 or less than 2
        // Continue prompting the user until they give
        // a number between 2 and 4
        while (numPlayers < 2 || numPlayers > 6)
        {
            System.out.println("Please keep the number of players between 2 and 6");

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
        Scanner kbd = new Scanner(System.in);

        // Create a deck of 52 cards
        Deck deck = new Deck();

        // shuffle the game deck
        deck.shuffle();

        // calculate how many cards we are supposed to distribute to each player
        int howManyCardsToEachPlayer = 0;

        if (players.size() == 2)
        {
            howManyCardsToEachPlayer = 10;
        }

        else if (players.size() == 3 || players.size() == 4)
        {
            howManyCardsToEachPlayer = 7;
        }

        else
        {
            howManyCardsToEachPlayer = 6;
        }

        // loop through the list of players and give each player a name and their
        // cards
        for (int i = 0; i < players.size(); i++)
        {
            System.out.println("What is player " + (i + 1) + "'s name:");

            String playerName = kbd.nextLine();

            // ask each player for their name
            players.get(i).setName(playerName);

            // draw a predetermined number of cards from the deck
            ArrayList<Card> cardsPerPerson = deck.draw(howManyCardsToEachPlayer);

            // give this player the predetermined number of cards
            players.get(i).addMultipleToPlayerHand(cardsPerPerson);

            // give each player a score of 0
            players.get(i).addsPointsWon(0);

            System.out.println(players.get(i).getName() + " has " + players.get(i).getNumOfPlayerCards() + " cards");

            System.out.println("__________________________________________________\n");

        }

        setUpGame(deck);
    }

    public void setUpGame(Deck stack)
    {
        Scanner kbd = new Scanner(System.in);

        System.out.println("What is the number of rounds you want?\n");
        System.out.println("The min # is 1 and the max # is 5");
        String numRounds = kbd.nextLine();

        // if player gives a non-numerical answer
        // continue prompting player until they give a numeric answer
        while (!numRounds.matches("[0-9]+"))
        {
            System.out.println("Please enter a number for the number of rounds in this new game");

            numRounds = kbd.nextLine();
        }

        // convert input into an integer
        int numberOfRounds = Integer.parseInt(numRounds);

        // Continue promoting the player until they provide
        // a number between 0 and 5
        while (numberOfRounds > 5 || numberOfRounds < 1)
        {
            System.out.println("Please enter a number that is between 1 and 5");

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

        System.out.println("__________________________________________________\n");

        startGame(stack, numberOfRounds);
    }

    public void startGame(Deck stack, int numRounds)
    {
        ArrayList<Card> discardPile = new ArrayList<Card>();

        // flip over a card from the stack and put it in the discard pile
        discardPile.add(stack.draw());

        int currentRound = 0;

        Random rand = new Random();

        Scanner kbd = new Scanner(System.in);

        // randomly choose who can start the game
        int whoseTurn = rand.nextInt(players.size());

        while (currentRound != numRounds)
        {
            while (atLeastSomeoneIsOut() == false)
            {

                System.out.println("Round " + currentRound);

                System.out.println("Discard Pile: ");

                for (int i = 0; i < discardPile.size(); i++)
                {
                    System.out.println((i + 1) + ": " + discardPile.get(i));
                }

                players.get(whoseTurn).showPlayerCards();

                System.out.println("Do you want to take a card from the discard pile or the stock pile?\n");

                System.out.println("1. Stack ");
                System.out.println("2. Discard Pile\n");

                String whichPile = "";

                // Convert the string input into an integer
                int thisPile = 0;

                // If the user puts a number greater than 2 or less than 1
                // Continue prompting the user until they give
                // a number between 1 and 2
                while (thisPile < 1 || thisPile > 2)
                {
                    System.out.println("Please keep the option number between 1 and 2");

                    // get user input
                    whichPile = kbd.nextLine();

                    // if user gives a non-numerical answer
                    // continue prompting user until they give a numeric answer
                    while (!whichPile.matches("[0-9]+"))
                    {
                        System.out.println("Please enter a number");

                        // get user input
                        whichPile = kbd.nextLine();
                    }

                    // convert the user input into an integer
                    thisPile = Integer.parseInt(whichPile);
                }

                // if the player chose to draw one from the stack
                if (thisPile == 1)
                {
                    // remove the card from the stack and add it to the player's hand
                    players.get(whoseTurn).addOneToPlayerHand(stack.draw());
                }

                else
                {

                    int pickUpOneFromDiscard = 1;

                    String pickUpFromDiscard = "";

                    // if there's more than one card in the middle
                    if (discardPile.size() > 1)
                    {

                        System.out.println("Do you want to pick up one or more cards from the discard pile, " + players.get(whoseTurn).getName() + "?");

                        // print out a list of options
                        System.out.println("1: One");

                        System.out.println("2: Multiple\n");

                        // If the user puts a number greater than the # of cards in the player's hand or less than 1
                        // Continue prompting the user
                        while (pickUpOneFromDiscard < 1 || pickUpOneFromDiscard > discardPile.size())
                        {
                            System.out.println("Please keep the option number between 1 and " + discardPile.size());

                            // get user input
                            pickUpFromDiscard = kbd.nextLine();

                            // if user gives a non-numerical answer
                            // continue prompting user until they give a numeric answer
                            while (!pickUpFromDiscard.matches("[0-9]+"))
                            {
                                System.out.println("Please enter a number");

                                // get user input
                                pickUpFromDiscard = kbd.nextLine();
                            }

                            // convert the user input into an integer
                            pickUpOneFromDiscard = Integer.parseInt(pickUpFromDiscard);
                        }
                    }

                    // if player chooses to pick up one card from the discard pile
                    if (pickUpOneFromDiscard == 1)
                    {
                        players.get(whoseTurn).addOneToPlayerHand(discardPile.get(discardPile.size() - 1));

                        discardPile.clear();

                    }

                    // if the player chooses to pick up multiple cards from the discard pile
                    else
                    {
                        System.out.println("Which card in the discard pile do you want?");

                        String whichCardFromDiscard = "";

                        int thisCardFromDiscard = 0;

                        // If the user puts a number greater than the # of cards in the discard pile or less than 1
                        // Continue prompting the user
                        while (thisCardFromDiscard < 1 || thisCardFromDiscard > discardPile.size())
                        {
                            System.out.println("Please keep the option number between 1 and " + discardPile.size());

                            // get user input
                            whichCardFromDiscard = kbd.nextLine();

                            // if user gives a non-numerical answer
                            // continue prompting user until they give a numeric answer
                            while (!whichCardFromDiscard.matches("[0-9]+"))
                            {
                                System.out.println("Please enter a number");

                                // get user input
                                whichCardFromDiscard = kbd.nextLine();
                            }

                            // convert the user input into an integer
                            thisCardFromDiscard = Integer.parseInt(whichCardFromDiscard);
                        }

                        System.out.println("Card selected is: " + players.get(whoseTurn).getCardInPlayerCards(thisCardFromDiscard) + "\n");

                        // loop through the discard pile from the back to whichever card the player wants in their hand
                        // the last card in the array list version of the discard pile is the last card placed in the discard
                        for (int i = discardPile.size() - 1; i >= thisCardFromDiscard; i--)

                        {
                            // add each card to this new list
                            players.get(whoseTurn).addOneToPlayerHand(discardPile.get(i));

                            // show which card has been added
                            System.out.println(discardPile.get(i) + " has been added to " + players.get(whoseTurn).getName() + "'s hand");

                            // remove this card from the discard pile
                            discardPile.remove(i);

                            System.out.println("______________________________________________________________________________________________");
                        }

                    }

                }

                players.get(whoseTurn).showPlayerCards();

                System.out.println("Which card do you wish to discard?\n");

                String whichCardToDiscard = "";

                int thisCardToDiscard = 0;

                // If the user puts a number greater than the # of cards in the player's hand or less than 1
                // Continue prompting the user
                while (thisCardToDiscard < 1 || thisCardToDiscard > players.get(whoseTurn).getNumOfPlayerCards())
                {
                    System.out.println("Please keep the option number between 1 and " + players.get(whoseTurn).getNumOfPlayerCards());

                    // get user input
                    whichCardToDiscard = kbd.nextLine();

                    // if user gives a non-numerical answer
                    // continue prompting user until they give a numeric answer
                    while (!whichCardToDiscard.matches("[0-9]+"))
                    {
                        System.out.println("Please enter a number");

                        // get user input
                        whichCardToDiscard = kbd.nextLine();
                    }

                    // convert the user input into an integer
                    thisCardToDiscard = Integer.parseInt(whichCardToDiscard);
                }

                // remove the card the player selected from their hand and put it in the discard pile
                discardPile.add(players.get(whoseTurn).removeOneFromPlayerCards(thisCardToDiscard));

                System.out.println("______________________________________________________________________________________________");

                whoseTurn = changeTurn(whoseTurn);

                break;
            }

        }

    }

    public boolean atLeastSomeoneIsOut()
    {
        for (Player p : players)
        {
            if (p.getNumOfPlayerCards() == 0)
            {
                return true;
            }

        }
        return false;
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
        if (currentTurn + 1 >= players.size())
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
        Rummy rummy = new Rummy();

        rummy.setUpNumOfPlayers();

        rummy.setUpPlayers();
    }
}
