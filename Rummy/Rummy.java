package Rummy;

import java.util.ArrayList;
import java.util.Collections;
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

        int currentRound = 1;

        Random rand = new Random();

        Scanner kbd = new Scanner(System.in);

        // randomly choose who can start the game
        int whoseTurn = rand.nextInt(players.size());

        while (currentRound <= numRounds)
        {
            while (atLeastSomeoneIsOut() == false)
            {

                System.out.println("Round " + currentRound + "\n");

                System.out.println("Discard Pile:");

                for (int i = 0; i < discardPile.size(); i++)
                {
                    System.out.println((i + 1) + ": " + discardPile.get(i));
                }

                players.get(whoseTurn).showPlayerCards();

                System.out.println("1. Stack ");
                System.out.println("2. Discard Pile\n");

                System.out.println("Do you want to take a card from the discard pile or the stock pile?");

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

                // if the player chooses to draw a card or cards from the discard pile
                else
                {

                    int pickUpOneFromDiscard = 0;

                    String pickUpFromDiscard = "";

                    // if there's more than one card in the middle
                    if (discardPile.size() > 1)
                    {

                        // print out a list of options
                        System.out.println("1: One");

                        System.out.println("2: Multiple\n");

                        System.out.println("Do you want to pick up one or more cards from the discard pile, " + players.get(whoseTurn).getName() + "?");

                        // If the user puts a number greater than the # of cards in the player's hand or less than 1
                        // Continue prompting the user
                        while (pickUpOneFromDiscard < 1 || pickUpOneFromDiscard > 2)
                        {
                            System.out.println("Please keep the option number between 1 and 2");

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

                        discardPile.remove(discardPile.size() - 1);
                    }

                    // if the player chooses to pick up multiple cards from the discard pile
                    else
                    {
                        for (int i = 0; i < discardPile.size(); i++)
                        {
                            System.out.println((i + 1) + ": " + discardPile.get(i));
                        }

                        System.out.println("\nWhich card in the discard pile do you want?");

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

                        System.out.println("Card selected is: " + discardPile.get(thisCardFromDiscard - 1) + "\n");

                        // loop through the discard pile from the back to whichever card the player wants in their hand
                        // the last card in the array list version of the discard pile is the last card placed in the discard
                        // Have to subtract 1 from the variable because the option the player selected is one more than the
                        // actual index position of the card
                        for (int i = discardPile.size() - 1; i >= (thisCardFromDiscard - 1); i--)

                        {
                            // add each card to this new list
                            players.get(whoseTurn).addOneToPlayerHand(discardPile.get(i));

                            // show which card has been added
                            System.out.println(discardPile.get(i) + " has been added to " + players.get(whoseTurn).getName() + "'s hand\n");

                            // remove this card from the discard pile
                            discardPile.remove(i);

                        }

                    }

                }

                ArrayList<Player> playersWithMelds = new ArrayList<Player>();

                // loop through the players
                for (Player p : players)
                {
                    // if this player has melds
                    if (p.getRuns().size() > 0 || p.getSets().size() > 0)
                    {
                        // add this player to this new list of players that have melds
                        playersWithMelds.add(p);
                    }

                }

                // if there is at least one player who has a meld
                if (playersWithMelds.size() > 0)
                {
                    int option = 1;

                    String whichOption = "";

                    int thisOption = 0;

                    int quitOption = playersWithMelds.size() + 1;

                    // continue showing the menu options
                    while (thisOption != quitOption)
                    {

                        // show each player that has at least one meld
                        for (Player p : playersWithMelds)
                        {
                            System.out.println(option + ": " + p.getName());

                            option++;

                        }

                        // show the quit option
                        System.out.println(option + ": quit");

                        System.out.println("Whose melds do you want to see? ");

                        // continue prompting the player until they enter a legal option number
                        while (thisOption < 1 || thisOption > playersWithMelds.size())
                        {
                            System.out.println("Please select an option between 1 and " + players.size());

                            // get player input
                            whichOption = kbd.nextLine();

                            // if the player input isn't a number
                            while (!whichOption.matches("[0-9]+"))
                            {
                                System.out.println("Please enter a number");

                                // get player input
                                whichOption = kbd.nextLine();
                            }

                            // convert input to a number
                            thisOption = Integer.parseInt(whichOption);
                        }

                        System.out.println();

                        // if the option the player selected isn't the quit option
                        if (thisOption != quitOption)
                        {
                            // show this player's runs
                            playersWithMelds.get(thisOption - 1).showRuns();

                            // show this player's sets
                            playersWithMelds.get(thisOption - 1).showSets();

                            // Ask the player if they want to add to this player's meld
                            System.out.println("Do you wish to add to " + playersWithMelds.get(thisOption - 1).getName() + "'s meld");

                            System.out.println("1. Yes");
                            System.out.println("2. No");

                            String wantToMeld = "";

                            int decisionToMeld = 0;

                            while (decisionToMeld < 1 || decisionToMeld > 2)
                            {
                                System.out.println("Please select an option between 1 and 2");

                                // get player input
                                wantToMeld = kbd.nextLine();

                                // if the player input isn't a number
                                while (!wantToMeld.matches("[0-9]+"))
                                {
                                    System.out.println("Please enter a number");

                                    // get player input
                                    wantToMeld = kbd.nextLine();
                                }

                                // convert input to a number
                                decisionToMeld = Integer.parseInt(wantToMeld);
                            }

                            if (decisionToMeld == 1)
                            {

                                System.out.println();

                                players.get(thisOption - 1).showRuns();

                                players.get(thisOption - 1).showSets();

                                System.out.println("Which meld do you want to add to?");

                                System.out.println("1.Runs");

                                System.out.println("2.Sets");

                                System.out.println();

                                String whichMeld = "";

                                int thisMeld = 0;

                                while (thisMeld < 1 || thisMeld > 2)
                                {
                                    System.out.println("Please select an option between 1 and 2");

                                    // get player input
                                    whichMeld = kbd.nextLine();

                                    // if the player input isn't a number
                                    while (!whichMeld.matches("[0-9]+"))
                                    {
                                        System.out.println("Please enter a number");

                                        // get player input
                                        whichMeld = kbd.nextLine();
                                    }

                                    // convert input to a number
                                    thisMeld = Integer.parseInt(whichMeld);
                                }

                                if (thisMeld == 1)
                                {
                                    players.get(thisOption - 1).showRuns();
                                }

                                else
                                {
                                    players.get(thisOption - 1).showSets();

                                }

                                System.out.println();

                                System.out.println("Now choose a card to form a meld");

                                players.get(whoseTurn).showPlayerCards();

                                String whichCardToMeld = "";

                                int thisCardToMeld = 0;

                                // If the user puts a number greater than the # of cards in the player's hand or less than 1
                                // Continue prompting the user
                                while (thisCardToMeld < 1 || thisCardToMeld > players.get(whoseTurn).getNumOfPlayerCards())
                                {
                                    System.out.println("Please keep the option number between 1 and " + players.get(whoseTurn).getNumOfPlayerCards());

                                    // get user input
                                    whichCardToMeld = kbd.nextLine();

                                    // if user gives a non-numerical answer
                                    // continue prompting user until they give a numeric answer
                                    while (!whichCardToMeld.matches("[0-9]+"))
                                    {
                                        System.out.println("Please enter a number");

                                        // get user input
                                        whichCardToMeld = kbd.nextLine();
                                    }

                                    // convert the user input into an integer
                                    thisCardToMeld = Integer.parseInt(whichCardToMeld);
                                }

                                ////////
                                ////
                                ////
                                ////

                            }

                        }

                    }

                }

                // players.get(whoseTurn).findSets();

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

                if (stack.getSize() == 0)
                {
                    // shuffle the discard pile
                    Collections.shuffle(discardPile);

                    // create a stack using the discard pile
                    stack.addAll(discardPile);

                    // clear the discard pile
                    discardPile.clear();

                    // draw one from the stack and add it to the new discard pile
                    discardPile.add(stack.draw());

                }

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
