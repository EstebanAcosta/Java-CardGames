package Garbage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Garbage
{
    private ArrayList<Player> players = new ArrayList<Player>();

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

        System.out.println("How many players are going to play in the game?");
        System.out.println("(Mininumum # of players is 2 and Maximum # of players is 4) ");

        String numberOfPlayers = kbd.nextLine();

        // if user gives a non-numerical answer
        // continue prompting user until they give a numeric answer
        while (!numberOfPlayers.matches("[0-9]+"))
        {
            System.out.println("Please enter a number for the number of players in this game");

            numberOfPlayers = kbd.nextLine();
        }

        // Convert the string input into an integer
        int numPlayers = Integer.parseInt(numberOfPlayers);

        // If the user puts a number greater than 4 or less than 2
        // Continue prompting the user until they give
        // a number between 2 and 4
        while (numPlayers < 2 || numPlayers > 4)
        {
            System.out.println("Please keep the number of players between 2 and 4");

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

        // loop through the list of players and give each player a name and their 10
        // cards
        for (int i = 0; i < players.size(); i++)
        {
            System.out.println("What is player " + (i + 1) + "'s name:");

            String playerName = kbd.nextLine();

            // give each player their name
            players.get(i).setName(playerName);

            // Draw 10 cards from the deck
            ArrayList<Card> playerCards = deck.draw(10);

            // Make sure that the cards these players have are set to unflipped
            for (Card cards : playerCards)
            {
                cards.setUnflipped(true);
            }

            System.out.println("Ten cards have been drawn from the deck\n");

            // give each player their 10 cards
            players.get(i).setPlayerCards(playerCards);

            // set their goal to 10
            players.get(i).setPlayerGoalCards(10);

            System.out.println(players.get(i).getName() + " has " + players.get(i).getNumPlayerCards() + " cards");
            System.out.println("__________________________________________________\n");
        }

        setUpGame(deck);
    }

    public void setUpGame(Deck deck)
    {
        Scanner kbd = new Scanner(System.in);

        System.out.println("What is the goal number of cards?\n");
        System.out.println("The min # is 0 and the max # is 5");
        String goalNumOfCards = kbd.nextLine();

        // if user gives a non-numerical answer
        // continue prompting user until they give a numeric answer
        while (!goalNumOfCards.matches("[0-9]+"))
        {
            System.out.println("Please enter a number for the goal number of cards in this new game");

            goalNumOfCards = kbd.nextLine();
        }

        // convert input into an integer
        int goalCards = Integer.parseInt(goalNumOfCards);

        // Continue promoting the user until they provide
        // a number between 0 and 5
        while (goalCards > 5 || goalCards < 0)
        {
            System.out.println("Please enter a number that is between 0 and 5");

            // get user input
            goalNumOfCards = kbd.nextLine();

            // continue prompting user until the user gives a number
            while (!goalNumOfCards.matches("[0-9]+"))
            {
                System.out.println("Please enter a number for the goal number of cards in this new game");

                goalNumOfCards = kbd.nextLine();
            }
            // convert the user input into an integer
            goalCards = Integer.parseInt(goalNumOfCards);
        }

        System.out.println("This new game's goal number of cards is  " + goalCards + " cards \n");
        System.out.println("_____________________________________________________________________\n");
        startGame(deck, goalCards);

    }

    public void startGame(Deck deck, int goalCards)
    {
        System.out.println("Garbage has started\n");

        Scanner kbd = new Scanner(System.in);

        Random rand = new Random();

        // draw one card in the deck and place it in the middle
        Card middleCard = deck.draw();

        Card drawnCard = null;

        String switching = "";

        // Randomly determine whose turn it is
        int whoseTurn = rand.nextInt(players.size());

        // determines if the player has not made uncovered any of their cards
        boolean noChange = false;

        while (!goalCardReached(goalCards))
        {
            System.out.println("Middle Card:");

            // Print out the middle card
            System.out.println(middleCard);
            System.out.println();

            System.out
            .println("\nIt's player " + (whoseTurn + 1) + " " + players.get(whoseTurn).getName() + "'s turn\n");

            // Show the player's cards
            players.get(whoseTurn).showPlayerCards();

            if (middleCard.getValue() == Value.BLACK_JOKER || middleCard.getValue() == Value.RED_JOKER
            || middleCard.getValue() == Value.JACK)
            {
                System.out.println("Which card would you like to switch with " + middleCard + " ? ");

                System.out.println("Here are the available cards: \n");

                // Get all the hidden cards
                ArrayList<Integer> switchableCards = players.get(whoseTurn).showAvailableCardsToSwitch();

                // Get user input
                switching = kbd.nextLine();

                // if user input isn't a number, continue prompting them until they give a
                // number
                while (!switching.matches(("[0-9]+")))
                {
                    System.out.println("Please enter a number");
                    switching = kbd.nextLine();
                }

                // Convert user input into a number
                int whichCard = Integer.parseInt(switching);

                // If the card they have chosen isn't less than the smallest or the largest card
                // # then,
                // prompt them until they have given a proper number
                while (switchableCards.contains(whichCard) == false)
                {

                    System.out.println("Please enter a card number that can be flipped");
                    // get user input
                    switching = kbd.nextLine();

                    // if the user puts anything but a number, continue prompting them
                    while (!switching.matches(("[0-9]+")))
                    {
                        System.out.println("Please enter a number");
                        switching = kbd.nextLine();
                    }

                    // convert user input into a number
                    whichCard = Integer.parseInt(switching);
                }

                // Take the drawn card and switch the card in the player's hand with this drawn
                // card
                // The method will return the old card that was in that position
                drawnCard = players.get(whoseTurn).changeCards(whichCard, middleCard);

                // show the player cards again
                players.get(whoseTurn).showPlayerCards();

                System.out.println("Drawn Card:");
                System.out.println(drawnCard);
                System.out.println();
            }

            else if (middleCard.getValueOfCard() > players.get(whoseTurn).getNumPlayerCards()
            || middleCard.getValue() == Value.KING || middleCard.getValue() == Value.QUEEN
            || players.get(whoseTurn).getCard(middleCard.getValueOfCard()).isUnflipped() == false)
            {

                if (middleCard.getValue() != Value.KING && middleCard.getValue() != Value.QUEEN && middleCard.getValueOfCard() <= players.get(whoseTurn).getNumPlayerCards()
                && (players.get(whoseTurn).getCard(middleCard.getValueOfCard()).getValue() == Value.BLACK_JOKER
                || players.get(whoseTurn).getCard(middleCard.getValueOfCard())
                .getValue() == Value.RED_JOKER
                || players.get(whoseTurn).getCard(middleCard.getValueOfCard())
                .getValue() == Value.JACK))
                {
                    System.out.println("Would you like to make a switch between " + middleCard + " and "
                    + players.get(whoseTurn).getCard(middleCard.getValueOfCard()) + "?");

                    switching = kbd.nextLine();

                    while (!switching.equalsIgnoreCase("y"))
                    {
                        System.out.println("Please enter y for yes");

                        switching = kbd.nextLine();
                    }

                    drawnCard = players.get(whoseTurn).changeCards(middleCard.getValueOfCard(), middleCard);

                    players.get(whoseTurn).showPlayerCards();

                    System.out.println("Drawn Card:");
                    System.out.println(drawnCard);
                    System.out.println();

                }

                else
                {
                    // draw a card from the deck
                    drawnCard = deck.draw();

                    System.out.println("Drawn Card:");
                    System.out.println(drawnCard);
                    System.out.println();
                }

            }

            else
            {

                System.out.println("Would you like to make a switch between " + middleCard + " and Card "
                + middleCard.getValueOfCard() + "?");

                switching = kbd.nextLine();

                while (!switching.equalsIgnoreCase("y"))
                {
                    System.out.println("Please enter y for yes");

                    switching = kbd.nextLine();
                }
                // Take the middle card and switch the card in the player's hand with the middle
                // card
                // The method will return the old card that was in that position
                drawnCard = players.get(whoseTurn).changeCards(middleCard.getValueOfCard(), middleCard);

                players.get(whoseTurn).showPlayerCards();

                System.out.println("Drawn Card:");
                System.out.println(drawnCard);
                System.out.println();

            }

            while (noChange == false && players.get(whoseTurn).hasReachedPersonalGoalCards() == false)
            {

                if (drawnCard.getValue() == Value.BLACK_JOKER || drawnCard.getValue() == Value.RED_JOKER
                || drawnCard.getValue() == Value.JACK)
                {
                    System.out.println("Which card would you like to switch with " + drawnCard + " ? ");

                    System.out.println("Here are the available cards: \n");

                    ArrayList<Integer> switchableCards = players.get(whoseTurn).showAvailableCardsToSwitch();

                    switching = kbd.nextLine();

                    // if user input isn't a number
                    // continue prompting them until they gave you a number
                    while (!switching.matches(("[0-9]+")))
                    {
                        System.out.println("Please enter a number");
                        switching = kbd.nextLine();
                    }

                    // Convert user input into a number
                    int whichCard = Integer.parseInt(switching);

                    // If the card they have chosen isn't less than the smallest or the largest card
                    // # then,
                    // prompt them until the have given a proper number
                    while (switchableCards.contains(whichCard) == false)
                    {

                        System.out.println("Please enter a card number that can be flipped");
                        // get which card to switch
                        switching = kbd.nextLine();

                        // if the user puts anything but a number, continue prompting them
                        while (!switching.matches(("[0-9]+")))
                        {
                            System.out.println("Please enter a number");
                            switching = kbd.nextLine();
                        }

                        // convert user input into a number
                        whichCard = Integer.parseInt(switching);
                    }

                    // Take the drawn card and switch the card in the player's hand with this drawn
                    // card
                    // The method will return the old card that was in that position
                    drawnCard = players.get(whoseTurn).changeCards(whichCard, drawnCard);

                    players.get(whoseTurn).showPlayerCards();

                    System.out.println("Drawn Card:");
                    System.out.println(drawnCard);
                    System.out.println();

                }

                // if the drawn card is a king, a queen , or if the drawn card is already in the
                // player's
                // hand
                // then move on to the next player
                else if (drawnCard.getValueOfCard() > players.get(whoseTurn).getNumPlayerCards()
                || drawnCard.getValue() == Value.KING || drawnCard.getValue() == Value.QUEEN
                || players.get(whoseTurn).getCard(drawnCard.getValueOfCard()).isUnflipped() == false)
                {

                    if (drawnCard.getValue() != Value.KING && drawnCard.getValue() != Value.QUEEN
                    && drawnCard.getValueOfCard() <= players.get(whoseTurn).getNumPlayerCards()
                    && (players.get(whoseTurn).getCard(drawnCard.getValueOfCard())
                    .getValue() == Value.BLACK_JOKER
                    || players.get(whoseTurn).getCard(drawnCard.getValueOfCard())
                    .getValue() == Value.RED_JOKER
                    || players.get(whoseTurn).getCard(drawnCard.getValueOfCard())
                    .getValue() == Value.JACK))
                    {
                        System.out.println("Would you like to make a switch between " + drawnCard + " and "
                        + players.get(whoseTurn).getCard(drawnCard.getValueOfCard()) + "?");

                        switching = kbd.nextLine();

                        while (!switching.equalsIgnoreCase("y"))
                        {
                            System.out.println("Please enter y for yes");

                            switching = kbd.nextLine();
                        }

                        drawnCard = players.get(whoseTurn).changeCards(drawnCard.getValueOfCard(), drawnCard);

                        players.get(whoseTurn).showPlayerCards();

                        System.out.println("Drawn Card:");
                        System.out.println(drawnCard);
                        System.out.println();

                    }

                    else
                    {
                        System.out.println("You cannot use this card. It's the next player's turn\n");

                        System.out.println("Drawn Card:");
                        System.out.println(drawnCard);
                        System.out.println();

                        System.out.println("Please enter n for the next player");
                        switching = kbd.nextLine();

                        while (!switching.equalsIgnoreCase("n"))
                        {
                            System.out.println("Please enter n to move on to the next player");
                            switching = kbd.nextLine();
                        }

                        // Make the the recently drawn card the middle card
                        middleCard = drawnCard;

                        noChange = true;
                    }

                }

                // if the card isn't already in the player's hand, switch the card in the
                // player's hand in
                // that respective
                // position with the drawn card
                else
                {
                    System.out.println("Would you like to make a switch between " + drawnCard + " and Card "
                    + drawnCard.getValueOfCard() + "?");

                    switching = kbd.nextLine();

                    while (!switching.equalsIgnoreCase("y"))
                    {
                        System.out.println("Please enter y for yes or n for no");
                        switching = kbd.nextLine();
                    }

                    // Take the drawn card and switch the card in the player's hand with this drawn
                    // card
                    // The method will return the old card that was in that position
                    drawnCard = players.get(whoseTurn).changeCards(drawnCard.getValueOfCard(), drawnCard);

                    players.get(whoseTurn).showPlayerCards();

                    System.out.println("Drawn Card:");
                    System.out.println(drawnCard);
                    System.out.println();

                }

            }

            System.out.println("_____________________________________________________________________________\n");

            if (players.get(whoseTurn).hasReachedPersonalGoalCards() == false)
            {
                // Move on to the next player
                whoseTurn = changeTurn(whoseTurn);

                // set it to false since we are starting with a new player
                noChange = false;
            }

            else
            {
                System.out.println(
                "Player " + (whoseTurn + 1) + " " + players.get(whoseTurn).getName() + " has won this round");

                int num = players.get(whoseTurn).getPlayerGoalCards() - 1;

                players.get(whoseTurn).setPlayerGoalCards(num);

                // reset deck
                deck = new Deck();

                deck.shuffle();

                System.out.println("A New Round Has Begun");

                setupNewRound(deck);

                // reset the middle card
                middleCard = deck.draw();

                System.out.println("_____________________________________________________________________________\n");

            }

        }

        System.out.println(
        "Player " + (whoseTurn + 1) + " " + players.get(whoseTurn).getName() + " HAS WON THE GAME !!!!!");

    }

    public void setupNewRound(Deck deck)
    {

        for (Player p : players)
        {
            ArrayList<Card> playerCards = deck.draw(p.getPlayerGoalCards());

            // Make sure that the cards these players have are set to hidden
            for (Card cards : playerCards)
            {
                cards.setUnflipped(true);
            }

            p.setPlayerCards(playerCards);

            System.out.println(
            "Player " + p.getPlayerId() + " " + p.getName() + " has " + p.getNumPlayerCards() + " cards");
        }
    }

    public boolean goalCardReached(int goalCards)
    {

        for (Player eachPlayer : players)
        {
            if (eachPlayer.getNumPlayerCards() == goalCards)
            {
                return true;
            }

        }

        return false;
    }

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

        Garbage game = new Garbage();

        game.setUpNumOfPlayers();
        game.setUpPlayers();

    }
}
