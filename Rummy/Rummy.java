package Rummy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
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
            int turn = 1;

            while (atLeastSomeoneIsOut() == false)
            {

                System.out.println("Round " + currentRound + "\n");

                System.out.println("Turn " + turn);

                System.out.println("Discard Pile:");

                for (int i = 0; i < discardPile.size(); i++)
                {
                    System.out.println((i + 1) + ": " + discardPile.get(i));
                }

                Card dontPutBackDown = null;

                // System.out.println(players.get(whoseTurn).getName() + " has " + players.get(whoseTurn).getMelds().size() + (players.get(whoseTurn).getMelds().size() == 1 ? " meld " : " melds " + "so far\n"));

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

                System.out.println("---------------------------------------------------\n");

                // if the player chooses to draw one from the stack
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
                        System.out.println();

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

                        System.out.println("---------------------------------------------------\n");

                        // if player chooses to pick up one card from the discard pile
                        if (pickUpOneFromDiscard == 1)
                        {
                            // add that card to their hand
                            players.get(whoseTurn).addOneToPlayerHand(discardPile.get(discardPile.size() - 1));

                            // store the card that player picked up
                            dontPutBackDown = discardPile.get(discardPile.size() - 1);

                            // and remove it from the discard pile
                            discardPile.remove(discardPile.size() - 1);

                        }

                        // if the player chooses to pick up multiple cards from the discard pile
                        else
                        {
                            // show all the cards in the discard pile
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

                    // if there's only card
                    else if (discardPile.size() == 1)
                    {
                        // remove the only card in the discard pile and place it in the player's hand
                        players.get(whoseTurn).addOneToPlayerHand(discardPile.get(discardPile.size() - 1));

                        dontPutBackDown = discardPile.get(discardPile.size() - 1);

                        discardPile.clear();

                    }

                    System.out.println("---------------------------------------------------\n");

                }

                System.out.println(players.get(whoseTurn).getName() + "'s new hand: \n");

                players.get(whoseTurn).showPlayerCards();

                System.out.println("---------------------------------------------------\n");

                ArrayList<Player> playersWithMelds = new ArrayList<Player>();

                // loop through the players
                for (Player p : players)
                {
                    // if this player has melds
                    if (p.hasAMeld())
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
                        System.out.println();

                        // reset this back to zero so it has to ask you which options you want to choose
                        thisOption = 0;

                        option = 1;

                        // show each player that has at least one meld
                        for (Player p : playersWithMelds)
                        {
                            System.out.println(option + ": " + p.getName());

                            option++;

                        }

                        // show the quit option
                        System.out.println(option + ": quit\n");

                        int listOfOptions = playersWithMelds.size() + 1;
                        System.out.println("Whose melds do you want to see? ");

                        // continue prompting the player until they enter a legal option number
                        while (thisOption < 1 || thisOption > listOfOptions)
                        {
                            System.out.println("Please select an option between 1 and " + listOfOptions);

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

                        System.out.println("---------------------------------------------------\n");

                        // if the option the player selected isn't the quit option
                        if (thisOption != quitOption)
                        {
                            // show this player's runs
                            playersWithMelds.get(thisOption - 1).showMelds();

                            System.out.println();

                            // Ask the player if they want to add to this player's meld
                            System.out.println("\nDo you wish to add to " + playersWithMelds.get(thisOption - 1).getName() + "'s meld?");

                            System.out.println("1.Yes");
                            System.out.println("2.No");

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

                            System.out.println("---------------------------------------------------\n");

                            if (decisionToMeld == 1)
                            {

                                System.out.println();

                                String whichMeld = "";

                                int thisMeld = 0;

                                Hashtable<Integer, ArrayList<Card>> otherPlayersMelds = playersWithMelds.get(thisOption - 1).getMelds();

                                // if this player can add to any of these melds that this other player possesses
                                if (canAddToMelds(otherPlayersMelds, players.get(whoseTurn).getPlayerHand()) == true)
                                {

                                    // if the other player has more than one meld
                                    if (otherPlayersMelds.size() > 1)
                                    {

                                        // show their melds
                                        playersWithMelds.get(thisOption - 1).showMelds();

                                        System.out.println("\nWhich meld do you want to add to?");

                                        // if the player selects an option that's not there
                                        while ((thisMeld < 1 || thisMeld > playersWithMelds.get(thisOption - 1).getMelds().size()) || canAddAnyCardToThisMeld(otherPlayersMelds, players.get(whoseTurn).getPlayerHand(), thisMeld) == false)
                                        {

                                            if (thisMeld < 1 || thisMeld > playersWithMelds.get(thisOption - 1).getMelds().size())
                                            {
                                                System.out.println("Please select an option between 1 and " + playersWithMelds.get(thisOption - 1).getMelds().size());

                                            }
                                            else if (canAddAnyCardToThisMeld(otherPlayersMelds, players.get(whoseTurn).getPlayerHand(), thisMeld) == false)
                                            {
                                                System.out.println("Please select a meld you can add a card to");
                                            }

                                            // get player input
                                            whichMeld = kbd.nextLine();

                                            // if the player input isn't a number
                                            while (!whichMeld.matches("[0-9]+"))
                                            {
                                                System.out.println("Please enter a number");

                                                // get player input again
                                                whichMeld = kbd.nextLine();
                                            }

                                            // convert input to a number
                                            thisMeld = Integer.parseInt(whichMeld);
                                        }

                                        System.out.println("---------------------------------------------------\n");

                                    }

                                    // if the other player has only one meld
                                    // the position of the only meld this player possesses is 1.

                                    else
                                    {
                                        thisMeld = 1;
                                    }

                                    System.out.println("Now choose a card to form a meld");

                                    players.get(whoseTurn).showPlayerCards();

                                    String whichCardToMeld = "";

                                    int thisCardToMeld = 0;

                                    // If the user puts a number greater than the # of cards in the player's hand or less than 1
                                    // or if the user selects a card that can't be added to this other player's specific meld
                                    // Continue prompting the user
                                    while ((thisCardToMeld < 1 || thisCardToMeld > players.get(whoseTurn).getNumOfPlayerCards()) ||
                                    canAddThisCardToThisMeld(otherPlayersMelds, players.get(whoseTurn).getCardInPlayerCards(thisCardToMeld), thisMeld) == false)
                                    {
                                        // display an error message if the player chose a number that's not within the range of the # of cards they have
                                        if (thisCardToMeld < 1 || thisCardToMeld > players.get(whoseTurn).getNumOfPlayerCards())
                                        {
                                            System.out.println("Please keep the option number between 1 and " + players.get(whoseTurn).getNumOfPlayerCards());

                                        }

                                        // display an error message if the card they selected can't be added to this meld
                                        else if (canAddThisCardToThisMeld(otherPlayersMelds, players.get(whoseTurn).getCardInPlayerCards(thisCardToMeld), thisMeld) == false)
                                        {
                                            System.out.println("Please choose a different card that can be added to this meld");
                                        }
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

                                    System.out.println(players.get(whoseTurn).getName() + " has selected " + players.get(whoseTurn).getCardInPlayerCards(thisCardToMeld));

                                    System.out.println("---------------------------------------------------\n");

                                    // add that specific card to the other player's meld
                                    playersWithMelds.get(thisOption - 1).addToPlayersMeld(players.get(whoseTurn).getCardInPlayerCards(thisCardToMeld), thisMeld);

                                    // remove that card from the player's hand
                                    players.get(whoseTurn).removeOneFromPlayerCards(thisCardToMeld);

                                }

                                else
                                {
                                    if (playersWithMelds.size() > 1)
                                    {
                                        System.out.println("There are no melds that you can add to. Choose another player.");

                                    }

                                    else
                                    {
                                        System.out.println("There are no melds that you can add to. Please quit");
                                    }

                                    System.out.println("----------------------------------------------------------------\n");

                                }

                            }

                        }

                    }

                }

                players.get(whoseTurn).findRuns();

                players.get(whoseTurn).findSets();

                int decision = 0;

                // continue looping until the player has decided to stop forming melds or until they've melded all their runs/sets
                while ((players.get(whoseTurn).hasARun() == true || players.get(whoseTurn).hasASet() == true) && decision != 2)
                {

                    if (players.get(whoseTurn).hasARun() && players.get(whoseTurn).hasASet())
                    {
                        players.get(whoseTurn).showRuns();

                        System.out.println();

                        players.get(whoseTurn).showSets();
                    }

                    else if (players.get(whoseTurn).hasARun())
                    {
                        players.get(whoseTurn).showRuns();
                    }

                    else
                    {
                        players.get(whoseTurn).showSets();

                    }

                    System.out.println();

                    System.out.println("Do you wish to form a meld?");

                    System.out.println("1.Yes");

                    System.out.println("2.No");

                    System.out.println();

                    decision = 0;

                    String confirm = "";

                    while (decision < 1 || decision > 2)
                    {
                        System.out.println("Please select an option between 1 and 2");

                        // get player input
                        confirm = kbd.nextLine();

                        // if the player input isn't a number
                        while (!confirm.matches("[0-9]+"))
                        {
                            System.out.println("Please enter a number");

                            // get player input
                            confirm = kbd.nextLine();
                        }

                        // convert input to a number
                        decision = Integer.parseInt(confirm);
                    }

                    // if the player decides to form a meld
                    if (decision == 1)
                    {
                        System.out.println("-----------------------------------------------------------------------------------\n");

                        // if the player has both a run and a set
                        if (players.get(whoseTurn).hasARun() == true && players.get(whoseTurn).hasASet() == true)
                        {
                            players.get(whoseTurn).showRuns();

                            System.out.println();

                            players.get(whoseTurn).showSets();

                            System.out.println();

                            System.out.println("Which meld do you want to form?");

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

                            System.out.println("-----------------------------------------------------------------------\n");

                            // if the player chose to meld the run
                            if (thisMeld == 1)
                            {
                                // if the player has more than one run
                                if (players.get(whoseTurn).getRuns().size() > 1)
                                {

                                    players.get(whoseTurn).showRuns();

                                    System.out.println();

                                    System.out.println("Which run do you want to meld?");

                                    String whichRun = "";

                                    int thisRun = 0;

                                    // continue looping until the player has chosen an option
                                    // between the first and the last run
                                    while (thisRun < 1 || thisRun > players.get(whoseTurn).getRuns().size())
                                    {
                                        System.out.println("Please select an option between 1 and " + players.get(whoseTurn).getRuns().size());

                                        // get player input
                                        whichRun = kbd.nextLine();

                                        // if the player input isn't a number
                                        while (!whichRun.matches("[0-9]+"))
                                        {
                                            System.out.println("Please enter a number");

                                            // get player input
                                            whichRun = kbd.nextLine();
                                        }

                                        // convert input to a number
                                        thisRun = Integer.parseInt(whichRun);
                                    }

                                    System.out.print(players.get(whoseTurn).getName() + " has melded this run:");

                                    for (Card c : players.get(whoseTurn).getRuns().get(thisRun))
                                    {
                                        System.out.print(" ( " + c + " ) ");

                                    }

                                    System.out.println();

                                    // meld that specific run
                                    players.get(whoseTurn).meldRun(thisRun);

                                }

                                // if the player only has one run
                                else
                                {
                                    int thisRun = 0;

                                    // loop through the table of runs
                                    for (Entry<Integer, ArrayList<Card>> entry : players.get(whoseTurn).getRuns().entrySet())
                                    {

                                        // since there's only one entry, store the key value of the one entry
                                        thisRun = entry.getKey();
                                    }

                                    System.out.print(players.get(whoseTurn).getName() + " has melded this run:");

                                    for (Card c : players.get(whoseTurn).getRuns().get(thisRun))
                                    {
                                        System.out.print(" ( " + c + " ) ");

                                    }

                                    System.out.println();
                                    System.out.println();

                                    // meld that specific run
                                    players.get(whoseTurn).meldRun(thisRun);
                                }
                            }

                            // if the player chooses to meld the set
                            else
                            {
                                // if the player has more than one set
                                if (players.get(whoseTurn).getSets().size() > 1)
                                {

                                    players.get(whoseTurn).showSets();

                                    System.out.println();

                                    System.out.println("Which set do you want to meld?");

                                    String whichSet = "";

                                    int thisSet = 0;

                                    // continue looping until the player has chosen an option
                                    // between the first and the last run
                                    while (thisSet < 1 || thisSet > players.get(whoseTurn).getSets().size())
                                    {
                                        System.out.println("Please select an option between 1 and " + players.get(whoseTurn).getSets().size());

                                        // get player input
                                        whichSet = kbd.nextLine();

                                        // if the player input isn't a number
                                        while (!whichSet.matches("[0-9]+"))
                                        {
                                            System.out.println("Please enter a number");

                                            // get player input
                                            whichSet = kbd.nextLine();
                                        }

                                        // convert input to a number
                                        thisSet = Integer.parseInt(whichSet);
                                    }

                                    // create a counter
                                    int position = 1;

                                    // loop through the table of sets
                                    for (Entry<Integer, ArrayList<Card>> entry : players.get(whoseTurn).getSets().entrySet())
                                    {

                                        // if this is the meld the player wants
                                        if (thisSet == position)
                                        {
                                            // store the key value of that meld
                                            thisSet = entry.getKey();
                                        }

                                        // add one to counter
                                        position++;

                                    }

                                    System.out.print(players.get(whoseTurn).getName() + " has melded this set:");

                                    for (Card c : players.get(whoseTurn).getSets().get(thisSet))
                                    {
                                        System.out.print(" ( " + c + " ) ");

                                    }

                                    System.out.println();
                                    System.out.println();

                                    // meld that specific run
                                    players.get(whoseTurn).meldSet(thisSet);

                                }

                                // if the player has only has one set
                                else
                                {
                                    int thisSet = 0;

                                    // loop through the table of sets
                                    for (Entry<Integer, ArrayList<Card>> entry : players.get(whoseTurn).getSets().entrySet())
                                    {
                                        // since there's only one entry, store the key value of the one entry
                                        thisSet = entry.getKey();
                                    }

                                    System.out.print(players.get(whoseTurn).getName() + " has melded this set:");

                                    for (Card c : players.get(whoseTurn).getSets().get(thisSet))
                                    {
                                        System.out.print(" ( " + c + " ) ");

                                    }

                                    System.out.println();

                                    // meld that specific set
                                    players.get(whoseTurn).meldSet(thisSet);
                                }

                            }

                        }

                        // if the player only has a set
                        else if (players.get(whoseTurn).hasASet() == true)
                        {
                            System.out.println("You have " + players.get(whoseTurn).getSets().size() + (players.get(whoseTurn).getSets().size() == 1 ? " set " : " sets ") + " in your hand \n");

                            players.get(whoseTurn).showSets();

                            System.out.println();

                            // if the player has more than one set
                            if (players.get(whoseTurn).getSets().size() > 1)
                            {
                                System.out.println("Which set do you want to meld?");

                                String whichSet = "";

                                int thisSet = 0;

                                // continue looping until the player has chosen an option
                                // between the first and the last run
                                while (thisSet < 1 || thisSet > players.get(whoseTurn).getSets().size())
                                {
                                    System.out.println("Please select an option between 1 and " + players.get(whoseTurn).getSets().size());

                                    // get player input
                                    whichSet = kbd.nextLine();

                                    // if the player input isn't a number
                                    while (!whichSet.matches("[0-9]+"))
                                    {
                                        System.out.println("Please enter a number");

                                        // get player input
                                        whichSet = kbd.nextLine();
                                    }

                                    // convert input to a number
                                    thisSet = Integer.parseInt(whichSet);
                                }

                                // create a counter
                                int position = 1;

                                // loop through the table of sets
                                for (Entry<Integer, ArrayList<Card>> entry : players.get(whoseTurn).getSets().entrySet())
                                {

                                    // if this is the meld the player wants
                                    if (thisSet == position)
                                    {
                                        // store the key value of that meld
                                        thisSet = entry.getKey();
                                    }

                                    // add one to counter
                                    position++;

                                }

                                System.out.print(players.get(whoseTurn).getName() + " has melded this set:");

                                for (Card c : players.get(whoseTurn).getSets().get(thisSet))
                                {
                                    System.out.print(" ( " + c + " ) ");

                                }

                                System.out.println();

                                // meld that specific run
                                players.get(whoseTurn).meldSet(thisSet);

                            }

                            // if the player has only has one set
                            else
                            {
                                int thisSet = 0;

                                // loop through the table of sets
                                for (Entry<Integer, ArrayList<Card>> entry : players.get(whoseTurn).getSets().entrySet())
                                {
                                    // since there's only one entry, store the key value of the one entry
                                    thisSet = entry.getKey();
                                }

                                System.out.print(players.get(whoseTurn).getName() + " has melded this set:");

                                for (Card c : players.get(whoseTurn).getSets().get(thisSet))
                                {
                                    System.out.print(" ( " + c + " ) ");

                                }

                                System.out.println();

                                // meld that specific set
                                players.get(whoseTurn).meldSet(thisSet);
                            }

                            System.out.println();

                        }

                        // if the player only has a run
                        else if (players.get(whoseTurn).hasARun() == true)
                        {
                            System.out.println("You have " + players.get(whoseTurn).getRuns().size() + (players.get(whoseTurn).getRuns().size() == 1 ? " run " : " runs ") + "in your hand \n");

                            players.get(whoseTurn).showRuns();

                            System.out.println();

                            // if the player has more than one run
                            if (players.get(whoseTurn).getRuns().size() > 1)
                            {
                                System.out.println("Which run do you want to meld?");

                                String whichRun = "";

                                int thisRun = 0;

                                // continue looping until the player has chosen an option
                                // between the first and the last run
                                while (thisRun < 1 || thisRun > players.get(whoseTurn).getRuns().size())
                                {
                                    System.out.println("Please select an option between 1 and " + players.get(whoseTurn).getRuns().size());

                                    // get player input
                                    whichRun = kbd.nextLine();

                                    // if the player input isn't a number
                                    while (!whichRun.matches("[0-9]+"))
                                    {
                                        System.out.println("Please enter a number");

                                        // get player input
                                        whichRun = kbd.nextLine();
                                    }

                                    // convert input to a number
                                    thisRun = Integer.parseInt(whichRun);
                                }

                                System.out.print(players.get(whoseTurn).getName() + " has melded this run:");

                                for (Card c : players.get(whoseTurn).getRuns().get(thisRun))
                                {
                                    System.out.print(" ( " + c + " ) ");

                                }

                                System.out.println();

                                // meld that specific run
                                players.get(whoseTurn).meldRun(thisRun);

                            }

                            // if the player only has one run
                            else
                            {
                                int thisRun = 0;

                                // loop through the table of runs
                                for (Entry<Integer, ArrayList<Card>> entry : players.get(whoseTurn).getRuns().entrySet())
                                {

                                    // since there's only one entry, store the key value of the one entry
                                    thisRun = entry.getKey();
                                }

                                System.out.print(players.get(whoseTurn).getName() + " has melded this run:");

                                for (Card c : players.get(whoseTurn).getRuns().get(thisRun))
                                {
                                    System.out.print(" ( " + c + " ) ");

                                }

                                System.out.println();

                                // meld that specific run
                                players.get(whoseTurn).meldRun(thisRun);

                            }

                        }

                    }

                    // Since the
                    players.get(whoseTurn).clearRuns();

                    players.get(whoseTurn).clearSets();

                    players.get(whoseTurn).findRuns();

                    players.get(whoseTurn).findSets();

                    System.out.println("------------------------------------------------------------------------------------\n");

                }

                if (players.get(whoseTurn).getNumOfPlayerCards() >= 1)
                {
                    players.get(whoseTurn).showPlayerCards();

                    System.out.println("Which card do you wish to discard?");

                    String whichCardToDiscard = "";

                    int thisCardToDiscard = 0;

                    // stores the position of the card that can't be placed down again
                    int notThisCardPosition = 1;

                    // loop through the player's hand
                    for (Card c : players.get(whoseTurn).getPlayerHand())
                    {
                        // if the card in the hand is equal to the card the player is not supposed to put down again
                        if (c.equals(dontPutBackDown))
                        {

                            break;

                        }
                        // add one to the counter
                        notThisCardPosition++;
                    }

                    // If the user puts a number greater than the # of cards in the player's hand or less than 1
                    // Continue prompting the user
                    while (thisCardToDiscard < 1 || thisCardToDiscard > players.get(whoseTurn).getNumOfPlayerCards() || (thisCardToDiscard == notThisCardPosition && players.get(whoseTurn).getNumOfPlayerCards() > 1))
                    {
                        if (thisCardToDiscard < 1 || thisCardToDiscard > players.get(whoseTurn).getNumOfPlayerCards())
                        {
                            System.out.println("Please keep the option number between 1 and " + players.get(whoseTurn).getNumOfPlayerCards());

                        }

                        else if (thisCardToDiscard == notThisCardPosition)
                        {
                            System.out.println("Please don't put down the same card you just picked up from the discard pile back into the discard pile");
                        }

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
                }

                // if there are no more cards in the stack
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

                // clear the player's runs and sets
                // this has to be done because if these aren't cleared
                // by the time it's this player's turn again, there's a chance
                // that some of the runs and sets will no longer exist anymore.
                // This happens either because some of these cards have been discarded or have been used
                // to form a meld somewhere else. This is merely a way to prevent
                // the player from picking runs or sets that no longer exist/aren't valid
                // when it's their turn again
                players.get(whoseTurn).clearRuns();

                players.get(whoseTurn).clearSets();

                System.out.println("______________________________________________________________________________________________");

                whoseTurn = changeTurn(whoseTurn);

                turn++;

            }

            stack = new Deck();

            // shuffle the game deck
            stack.shuffle();

            Player winner = null;

            for (Player p : players)
            {
                if (p.getNumOfPlayerCards() == 0)
                {
                    winner = p;

                    break;
                }
            }

            System.out.println("The winner of Round " + currentRound + " is " + winner.getName());

            int totalPoints = 0;

            // loop through the list of players
            for (Player p : players)
            {

                // loop through the player's hand
                for (Card c : p.getPlayerHand())
                {

                    if (turn == 1)
                    {
                        // if the card's value is greater than or equal to 10 (king, jack, queen, or ten)
                        if (c.getValueOfCard() >= 10)
                        {
                            // add ten points
                            totalPoints += 20;

                        }

                        // if it's a regular card
                        else
                        {
                            // add their normal value to the total points
                            totalPoints += (c.getValueOfCard() * 2);
                        }

                    }

                    else
                    {
                        // if the card's value is greater than or equal to 10 (king, jack, queen, or ten)
                        if (c.getValueOfCard() >= 10)
                        {
                            // add ten points
                            totalPoints += 10;

                        }

                        // if it's a regular card
                        else
                        {
                            // add their normal value to the total points
                            totalPoints += c.getValueOfCard();

                        }
                    }
                }
            }

            // add the total points to the winner's total points
            winner.addsPointsWon(totalPoints);

            for (Player p : players)
            {

                System.out.println(p.getName() + " has " + p.getPointsWon() + " points so far");

                p.getPlayerHand().clear();

                // clear every meld each player has
                p.clearMelds();

                System.out.println("________________________________________________________________________________________________\n");

            }

            System.out.println();
            System.out.println();

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

            // loop through the list of players and give each player their
            // cards
            for (int i = 0; i < players.size(); i++)
            {
                // draw a predetermined number of cards from the deck
                ArrayList<Card> cardsPerPerson = stack.draw(howManyCardsToEachPlayer);

                // give this player the predetermined number of cards
                players.get(i).addMultipleToPlayerHand(cardsPerPerson);

                System.out.println(players.get(i).getName() + " has " + players.get(i).getNumOfPlayerCards() + " cards");

                System.out.println("________________________________________________________________________________________________\n");

            }

            // clear the discard pile
            discardPile.clear();

            // add the first card from the stack
            discardPile.add(stack.draw());

            // the first person to play is the winner
            whoseTurn = winner.getPlayerId() - 1;

            currentRound++;

        }

        int maxPlayerPoints = players.get(0).getPointsWon();

        int maxPlayerPosition = 0;

        for (int i = 0; i < players.size(); i++)
        {
            if (players.get(i).getPointsWon() > maxPlayerPoints)
            {
                maxPlayerPosition = i;

                maxPlayerPoints = players.get(i).getPointsWon();
            }
        }

        for (Player p : players)
        {
            System.out.println(p.getName() + " has " + p.getPointsWon() + " points");

            System.out.println("________________________________________________________________________________________________\n");
        }

        System.out.println();

        System.out.println("The winner of Rummy is " + players.get(maxPlayerPosition).getName() + " with " + players.get(maxPlayerPosition).getPointsWon() + " points");

        System.out.println();

    }

    /***
     * Method loops through the player's hand and checks every card against a specific meld.
     * @param otherPlayersMelds
     * @param playerHand
     * @param position
     *            of meld
     * @return true if at least one card can be added to the meld, false if no card can be added to the meld
     */
    public boolean canAddAnyCardToThisMeld(Hashtable<Integer, ArrayList<Card>> otherPlayersMelds, ArrayList<Card> playerHand, int position)
    {

        int typeOfMeld = 0;

        int count = 1;

        ArrayList<Card> thisMeld = new ArrayList<Card>();

        // loop through the table that contains this other player's melds
        for (Entry<Integer, ArrayList<Card>> entry : otherPlayersMelds.entrySet())
        {
            // if the position is equal to the position the player chose
            if (count == position)
            {
                // store the key
                typeOfMeld = entry.getKey();

                // store the value
                thisMeld = entry.getValue();

                break;
            }

            // add one to move to the next position
            count++;

        }

        // loop through the player's hand
        for (Card c : playerHand)
        {
            // if the entry is a set/book
            if (typeOfMeld % 2 == 0)
            {
                // check the rank of the player's card against the meld card's rank
                // if they're the same
                if (c.getRank() == thisMeld.get(0).getRank())
                {
                    return true;
                }
            }

            // if this entry is a run
            else if (typeOfMeld % 2 != 0)
            {
                // check the suit of the player's card against the meld card's suit
                // if they're the same
                if (c.getSuit() == thisMeld.get(0).getSuit())
                {
                    // make sure that the card's rank is either one below the first card in the run
                    // or one above the last card in the run
                    // if it means either requirement
                    if ((c.getValueOfCard() + 1) == thisMeld.get(0).getValueOfCard() ||
                    (c.getValueOfCard() - 1) == thisMeld.get(thisMeld.size() - 1).getValueOfCard())
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /****
     * Method takes the player's hand and the other player's melds as input. This method loops through the player's hand and checks each card against each meld
     * this other player possesses. If the card can be added to at least one meld in the other player's melds then the method should return true. However if none
     * of the cards in the player's hand can be added to any of the other player's melds then the method should return false
     * @param otherPlayersMelds
     * @param playerHand
     * @return false if there are no melds that this player can add a card to this other player's melds or true if this player can add a card to
     *         this other player's melds
     */
    public boolean canAddToMelds(Hashtable<Integer, ArrayList<Card>> otherPlayersMelds, ArrayList<Card> playerHand)
    {
        // loop through the player's hand
        for (Card c : playerHand)
        {
            // loop through the other player's melds
            for (Entry<Integer, ArrayList<Card>> entry : otherPlayersMelds.entrySet())
            {
                // if the entry is a set/book
                if (entry.getKey() % 2 == 0)

                {
                    // check the rank of the player's card against the meld card's rank
                    // if they're the same
                    if (c.getRank() == entry.getValue().get(0).getRank())
                    {
                        return true;
                    }
                }

                // if this entry is a run
                else if (entry.getKey() % 2 != 0)
                {

                    // check the suit of the player's card against the meld card's suit
                    // if they're the same
                    if (c.getSuit() == entry.getValue().get(0).getSuit())
                    {
                        // make sure that the card's rank is either one below the first card in the run
                        // or one above the last card in the run
                        // if it means either requirement
                        if ((c.getValueOfCard() + 1) == entry.getValue().get(0).getValueOfCard() ||
                        (c.getValueOfCard() - 1) == entry.getValue().get(entry.getValue().size() - 1).getValueOfCard())
                        {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /****
     * Method takes the card the player selected and the meld the player chose. Method checks the player's card against the meld the player chose and the method
     * determines if the card can be a part of that run or set
     * @param c
     * @param meld
     * @return true if this specific card can be added to this other player's meld.
     *         Returns false if this specific card can't be added to this other player's meld
     */
    public boolean canAddThisCardToThisMeld(Hashtable<Integer, ArrayList<Card>> otherPlayersMelds, Card c, int position)
    {

        int count = 1;

        // loop through the table that contains this other player's melds
        for (Entry<Integer, ArrayList<Card>> entry : otherPlayersMelds.entrySet())
        {
            // if the position is equal to the position the player chose
            if (count == position)
            {
                // if the entry is a set/book
                if (entry.getKey() % 2 == 0)
                {
                    // check the rank of the player's card against the meld card's rank
                    // if they're the same
                    if (c.getRank() == entry.getValue().get(0).getRank())

                    {
                        return true;
                    }
                }

                // if this entry is a run
                else if (entry.getKey() % 2 != 0)
                {
                    // check the suit of the player's card against the meld card's suit
                    // if they're the same
                    if (c.getSuit() == entry.getValue().get(0).getSuit())
                    {
                        // make sure that the card's rank is either one below the first card in the run
                        // or one above the last card in the run
                        // if it means either requirement
                        if ((c.getValueOfCard() + 1) == entry.getValue().get(0).getValueOfCard() ||
                        (c.getValueOfCard() - 1) == entry.getValue().get(entry.getValue().size() - 1).getValueOfCard())
                        {
                            return true;
                        }
                    }
                }

                break;
            }

            // add one to move to the next position
            count++;

        }

        return false;
    }

    /***
     * Loops through each player in the list of players and checks to see how many cards each player has. Method only
     * returns true if at least one player has gotten rid of all of their cards
     * @return true if at least one player got rid of all of their cards. false if no one has gotten rid of all their cards
     */
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
