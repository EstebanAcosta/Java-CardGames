package Uno;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Uno
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
        System.out.println("Welcome To Uno\n");
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
        Random rand = new Random();

        Deck deck = new Deck();

        deck.shuffle();

        Scanner kbd = new Scanner(System.in);

        for (int i = 0; i < players.size(); i++)
        {
            System.out.println("What is player " + (i + 1) + "'s name ?");

            Player p = new Player(i + 1);

            String name = kbd.nextLine();

            players.get(i).setName(name);

            players.get(i).setOutStatus(false);

            players.get(i).setPlayerCards(deck.draw(7));

            System.out.println();

            System.out.println("__________________________________________________\n");

        }

        startGame(deck);
    }

    public void startGame(Deck deck)
    {
        System.out.println("Welcome To Uno");

        Scanner kbd = new Scanner(System.in);

        Random rand = new Random();

        int whichPlayer = rand.nextInt(players.size());

        ArrayList<Card> middle = new ArrayList<Card>();

        Card initialCard = deck.draw();

        while (initialCard.getValue() == Value.DRAW_TWO || initialCard.getValue() == Value.REVERSE || initialCard.getValue() == Value.SKIP || initialCard.isWild())
        {
            initialCard = deck.draw();
        }

        middle.add(initialCard);

        while (allPlayersAreOut() == false)
        {

            System.out.println("\nMiddle Card: ");

            System.out.println(middle.get(middle.size() - 1));

            System.out.println();

            System.out.println("It's " + players.get(whichPlayer).getName() + "'s turn:");

            players.get(whichPlayer).showPlayerCards();

            if (middle.size() == 0 || players.get(whichPlayer).canPlayHand(middle.get(middle.size() - 1)))
            {

                System.out.println("Which card would you like put down in the middle ?");

                int whichCardPosition = 0;

                String selectedCard = "";

                // if there is more than one card in the middle, and the middle card is a draw four
                if (middle.size() > 0 && middle.get(middle.size() - 1).isWild() && middle.get(middle.size() - 1).getSpecialValue() == SpecialValue.WILD_DRAW_FOUR)
                {
                    // draw four cards from the deck and add them to the player's hand
                    players.get(whichPlayer).addMultipleToPlayerCards(deck.draw(4));

                    // make sure that this player status on picking up cards is set to true
                    players.get(whichPlayer).pickedUpCardsStatus(true);

                    // change turns
                    whichPlayer = changeTurn(whichPlayer, true);

                    continue;
                }
                // if there is more than one card in the middle, and the middle card is a draw two

                else if (middle.size() > 0 && middle.get(middle.size() - 1).getValue() == Value.DRAW_TWO)
                {
                    // draw two cards from the deck and add them to the player's hand
                    players.get(whichPlayer).addMultipleToPlayerCards(deck.draw(2));

                    // make sure that this player status on picking up cards is set to true
                    players.get(whichPlayer).pickedUpCardsStatus(true);

                    // change turns

                    whichPlayer = changeTurn(whichPlayer, true);

                    continue;
                }
                
                //if there is more than one in the middle and the middle card is a reverse
                else if (middle.size() > 0 && middle.get(middle.size() - 1).getValue() == Value.REVERSE)
                {

                }

                //if there is more than one in the middle and the middle card is a reverse
                else if (middle.size() > 0 && middle.get(middle.size() - 1).getValue() == Value.SKIP)
                {
                    //move on to the next player
                    whichPlayer = changeTurn(whichPlayer, true);

                    continue;
                }

                while (whichCardPosition < 1 || whichCardPosition > players.get(whichPlayer).getNumPlayerCards())
                {
                    System.out.println("Please keep the selected card number between 1 and " + players.get(whichPlayer).getNumPlayerCards());

                    // get user input
                    selectedCard = kbd.nextLine();

                    // if user gives a non-numerical answer
                    // continue prompting user until they give a numeric answer
                    while (!selectedCard.matches("[0-9]+"))
                    {
                        System.out.println("Please enter a number");

                        // get user input
                        selectedCard = kbd.nextLine();
                    }

                    // convert the user input into an integer
                    whichCardPosition = Integer.parseInt(selectedCard);
                }

                Card thisCard = players.get(whichPlayer).getCardInPlayerCards(whichCardPosition);

                while (isValidSelection(thisCard, middle.get(middle.size() - 1)) == false)
                {
                    System.out.println("Please choose a card that is a wild card, has the same color or same number as the middle card\n");

                    whichCardPosition = 0;

                    while (whichCardPosition < 1 || whichCardPosition > players.get(whichPlayer).getNumPlayerCards())
                    {

                        System.out.println("Please keep the selected card number between 1 and " + players.get(whichPlayer).getNumPlayerCards());

                        whichCardPosition = 0;

                        // get user input
                        selectedCard = kbd.nextLine();

                        // if user gives a non-numerical answer
                        // continue prompting user until they give a numeric answer
                        while (!selectedCard.matches("[0-9]+"))
                        {
                            System.out.println("Please enter a number");

                            // get user input
                            selectedCard = kbd.nextLine();
                        }

                        // convert the user input into an integer
                        whichCardPosition = Integer.parseInt(selectedCard);
                    }

                    thisCard = players.get(whichPlayer).getCardInPlayerCards(whichCardPosition);

                }

                if (thisCard.isWild())
                {

                    if (thisCard.getSpecialValue() == SpecialValue.WILD || thisCard.getSpecialValue() == SpecialValue.WILD_DRAW_FOUR)
                    {
                        System.out.println("Please choose the color you want this card to be ? ");
                        System.out.println("The card has to be blue, red , green, or yellow\n");
                        System.out.println("Write b for blue, r for red, g for green, y for yellow");

                        String newColor = kbd.nextLine();

                        while (isValidColor(newColor) == false)
                        {
                            System.out.println("Please choose BLUE, RED, GREEN or YELLOW");

                            newColor = kbd.nextLine();
                        }

                        thisCard.setColor(changeToEnum(newColor));
                    }

                }

                players.get(whichPlayer).removeFromPlayerCards(whichCardPosition);

                middle.add(thisCard);
            }

            else
            {
                System.out.println("You can't add a card. Draw a card");

                players.get(whichPlayer).addToPlayerCards(deck.draw());

                System.out.println("Please enter n for next to move onto the next player");

                String next = kbd.nextLine();

                while (!next.equalsIgnoreCase("n"))
                {
                    System.out.println("Please enter n for next");

                    next = kbd.nextLine();
                }
            }

            System.out.println("__________________________________________________\n");

            whichPlayer = changeTurn(whichPlayer, true);

        }
    }

    /**
     * Determines if all the players in the game are out
     * @return
     */
    public boolean allPlayersAreOut()
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

    public boolean isValidSelection(Card selectedCard, Card middleCard)
    {

        if (selectedCard.getSpecialValue() == SpecialValue.WILD_DRAW_FOUR || selectedCard.getSpecialValue() == SpecialValue.WILD)
        {
            return true;
        }

        else if (selectedCard.getColor() == middleCard.getColor() || selectedCard.getValue() == middleCard.getValue())
        {
            return true;
        }

        return false;

    }

    public boolean isValidColor(String color)
    {

        if (color.equalsIgnoreCase("g"))
        {
            return true;
        }

        else if (color.equalsIgnoreCase("b"))
        {
            return true;
        }

        else if (color.equalsIgnoreCase("y"))
        {
            return true;
        }

        else if (color.equalsIgnoreCase("r"))
        {
            return true;
        }

        return false;
    }

    public int changeTurn(int currentTurn, boolean forward)
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

    public Color changeToEnum(String color)
    {

        if (color.equalsIgnoreCase("g"))
        {
            return Color.GREEN;
        }

        else if (color.equalsIgnoreCase("b"))
        {
            return Color.BLUE;
        }

        else if (color.equalsIgnoreCase("y"))
        {
            return Color.YELLOW;
        }
        else
        {
            return Color.RED;
        }

    }

    public int getPreviousPlayer(int currentTurn)
    {
        int previousTurn = 0;
        if(currentTurn == 0)
        {
            previousTurn = players.size() - 1;
        }
        
        else {
            
            previousTurn = currentTurn - 1;
        }
        
        return previousTurn;
    }

    public static void main(String[] args)
    {
        Uno game = new Uno();

        game.setUpNumOfPlayers();

        game.setUpPlayers();

    }
}
