package Palace;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/***
 * @author esteban acosta
 */
public class Palace
{
    private ArrayList<Player> players = new ArrayList<Player>();

    /***
     * Add players to the game
     * @param numPlayers
     */
    public void addPlayers(int numPlayers)
    {

        for (int i = 0; i < numPlayers; i++)
        {

            players.add(new Player(i + 1));
        }

    }

    /***
     * Creates a list of size of number of players
     */
    public void setUpNumOfPlayers()
    {
        Scanner kbd = new Scanner(System.in);

        System.out.println("How many players are going to play in the game?");
        System.out.println("(Mininumum # of players is 2 and Maximum # of players is 4) \n");

        String numberOfPlayers = "";

        // Convert the string input into an integer
        int numPlayers = 0;

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

    /***
     * Gives each player 6 cards and sets up the first part of each player's palace
     */
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

            // Draw 9 cards from the deck
            ArrayList<Card> playerCards = deck.draw(9);

            for (int j = 0; j < playerCards.size(); j++)
            {
                // the first three cards will be in the palace and will be face down
                if (j < 3)
                {
                    playerCards.get(j).setFaceDown(true);

                }

                // the remaining 6 cards will not be face down
                else
                {
                    playerCards.get(j).setFaceDown(false);
                }

            }

            // This will hold all 6 cards in the player's palace
            ArrayList<Card> playerPalace = new ArrayList<Card>();

            Iterator<Card> pCards = playerCards.iterator();

            // loop through the player's palace
            while (pCards.hasNext())
            {
                // get the next card in the player's hand
                Card nextCard = pCards.next();

                // if that card is face down
                if (nextCard.isFaceDown())
                {
                    // add that card to the player's palace
                    playerPalace.add(nextCard);

                    // remove that card from the player's hand
                    pCards.remove();
                }
            }

            // give each player their 6 cards
            players.get(i).setPlayerCards(playerCards);

            // put 3 of the face down cards in the player's palace
            players.get(i).setPlayerPalace(playerPalace);

            // make sure that each player's out status is false since no player until they have gotten rid of
            // all their card should be out
            players.get(i).setIsOutStatus(false);

            System.out.println(players.get(i).getNumPlayerCards() + " cards have been drawn from the deck\n");
            System.out.println("Three of the cards are faced down. The rest are in the player's hand");
            System.out.println(players.get(i).getName() + " has " + players.get(i).getNumPlayerCards() + " cards");
            System.out.println("__________________________________________________\n");
        }

        setUpPalace(deck);

    }

    /**
     * This will give the players the choice of putting 3 cards on their palace
     * @param deck
     */
    public void setUpPalace(Deck deck)
    {

        Scanner kbd = new Scanner(System.in);

        // loop through each player in the list of players
        for (Player p : players)
        {
            boolean notSure = true;

            System.out.println("Player " + p.getPlayerId() + " " + p.getName());

            String choice = "";

            int numChoice = 0;

            // continue looping until all players are fine with their selection of palace cards
            while (notSure)
            {
                ArrayList<Card> savedChoices = new ArrayList<Card>();

                // ask the user three times which cards they wish to put on their palace
                for (int i = 0; i < 3; i++)
                {
                    System.out.println("Choose a card you would like to put on your palace? ");

                    // create a list of choices for the player
                    ArrayList<Integer> availableChoices = p.getAvailablePlayerCards();

                    // show the cards in the player's hands
                    p.showPlayerCards();

                    // if the player's choice isn't one of the available choice
                    while (availableChoices.contains(numChoice) == false)
                    {
                        System.out.println("Please choose a card that is available");

                        // get user input
                        choice = kbd.nextLine();

                        // continue promoting user until they enter a number
                        while (!choice.matches("[0-9]+"))
                        {
                            System.out.println("Please enter a number");

                            // get user input
                            choice = kbd.nextLine();
                        }

                        // convert user input into an integer value
                        numChoice = Integer.parseInt(choice);
                    }

                    // add the card they want to add to their palace in this list of saved choices
                    savedChoices.add(p.getCardInPlayerCards(numChoice));

                    // add the card the player wants to add to their palace
                    p.addToPlayerPalace(p.removeFromPlayerCards(numChoice));

                    // reset to 0 in order for the while loop to work
                    numChoice = 0;

                    System.out.println();
                }

                // print the player's palace
                p.showPlayerPalace();

                System.out.println("Are you sure these are the cards you wish to place on your palace? y/n ");

                // get user input
                String confirmation = kbd.nextLine();

                // continue prompting the user until they enter y or n
                while (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n"))
                {
                    System.out.println("Please enter y for yes or n for no");

                    confirmation = kbd.nextLine();
                }

                // if the player is ok with their selection of cards
                if (confirmation.equalsIgnoreCase("y"))
                {
                    // break out of the loop
                    notSure = false;
                }

                // if the player is not ok with their selection of cards
                else
                {
                    // add the cards they've originally chosen to add to their palace back into their hand
                    p.addMultipleToPlayerCards(savedChoices);

                    // and remove the original cards they've chosen from their palace
                    p.removeMultipleFromPlayerPalace(savedChoices);
                }

            }

            System.out.println("__________________________________________________\n");

        }
        startGame(deck);
    }

    public void startGame(Deck deck)
    {
        System.out.println("Welcome to Palace\n");

        Random rand = new Random();

        Scanner kbd = new Scanner(System.in);

        ArrayList<Card> middleCards = new ArrayList<Card>();

        // randomly determine whose turn it will be
        int whoseTurn = rand.nextInt(players.size());

        // continue playing the game until every single person is out
        while (areAllPlayersOut() == false)
        {
            if (middleCards.size() > 0)
            {
                System.out.println("Middle Card: ");
                System.out.println(middleCards.get(middleCards.size() - 1));
                System.out.println();
                System.out.println("# Of Middle Cards: " + middleCards.size());
                System.out.println();
            }

            System.out.println("It's player " + players.get(whoseTurn).getPlayerId() + " " + players.get(whoseTurn).getName() + "'s turn");

            // print the player's hand
            players.get(whoseTurn).showPlayerCards();

            System.out.println();

            System.out.println("Would you like to draw one card before putting a card down ? y/n");

            String confirmation = kbd.nextLine();

            while (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n"))
            {
                System.out.println("Please enter y for yes or n for n. Would you like to draw one card before putting a card down ? ");

                confirmation = kbd.nextLine();

            }

            if (confirmation.equalsIgnoreCase("y"))
            {
                players.get(whoseTurn).addToPlayerCards(deck.draw());

                // print the player's hand again
                players.get(whoseTurn).showPlayerCards();
            }

            System.out.println();

            // print the player's palace
            players.get(whoseTurn).showPlayerPalace();

            System.out.println("Which card would you like to put in the middle ?");

            String whichCard = "";

            int selectedCard = 0;

            if (middleCards.size() == 0 || players.get(whoseTurn).canPlayHand(middleCards.get(middleCards.size() - 1)))
            {
                // continue prompting the user until they enter a number between 1 and 3
                while (selectedCard < 1 || selectedCard > players.get(whoseTurn).getPlayerCards().size())
                {

                    System.out.println("Please put a number that is between 1 and " + players.get(whoseTurn).getPlayerCards().size());

                    // get user input
                    whichCard = kbd.nextLine();

                    // continue prompting the user until they enter a number
                    while (whichCard.matches("[0-9]+") == false)
                    {
                        System.out.println("Please enter a number");

                        // get user input
                        whichCard = kbd.nextLine();
                    }

                    // convert user input into an integer value
                    selectedCard = Integer.parseInt(whichCard);

                }

                Card playedCard = players.get(whoseTurn).getCardInPlayerCards(selectedCard);

                // continue prompting the user until they put a card that is either a two, a ten or a card whose value
                // is greater than or equal to the card that is in the middle
                while (middleCards.size() > 0 && isValid(playedCard, middleCards.get(middleCards.size() - 1)) == false)
                {

                    // print the player's hand
                    players.get(whoseTurn).showPlayerCards();

                    System.out.println();

                    System.out.println("Please select a card that is greater than or equal to the middle card\n");

                    System.out.println("Please put a number that is between 1 and " + players.get(whoseTurn).getPlayerCards().size());

                    selectedCard = 0;
                    // continue prompting the user until they enter a number between 1 and 3
                    while (selectedCard < 1 || selectedCard > players.get(whoseTurn).getPlayerCards().size())
                    {

                        // get user input
                        whichCard = kbd.nextLine();

                        // continue prompting the user until they enter a number
                        while (whichCard.matches("[0-9]+") == false)
                        {
                            System.out.println("Please enter a number");

                            // get user input
                            whichCard = kbd.nextLine();
                        }

                        // convert user input into an integer value
                        selectedCard = Integer.parseInt(whichCard);

                    }

                    // get the selected card from the player's hand
                    playedCard = players.get(whoseTurn).getCardInPlayerCards(selectedCard);
                }

                if (players.get(whoseTurn).getPlayerCards().size() <= 3)
                {

                    playedCard = players.get(whoseTurn).getCardInPlayerCards(selectedCard);

                    if (playedCard.getValue() == Value.TEN)
                    {
                        middleCards.add(playedCard);

                        players.get(whoseTurn).removeFromPlayerCards(selectedCard);

                        middleCards.clear();

                        System.out.println("---------------------------------------------------\n");

                        System.out.println("It's still " + players.get(whoseTurn).getName() + " 's turn");
                        System.out.println();

                        continue;
                    }

                    else if (playedCard.getValue() == Value.TWO)
                    {

                        middleCards.add(playedCard);

                        players.get(whoseTurn).removeFromPlayerCards(selectedCard);

                        System.out.println("---------------------------------------------------\n");

                        System.out.println("It's still " + players.get(whoseTurn).getName() + " 's turn");
                        System.out.println();

                        continue;
                    }

                    else
                    {
                        // player puts the selected card in the middle
                        middleCards.add(playedCard);

                        // since the player needs to have 3 cards every time they draw a card in order
                        // to find out how many cards they need to draw, we calculate it by taking the
                        // the number of cards they currently have (either 0,1 or 2) and subtract it from 3.
                        int howManyToDraw = 3 - players.get(whoseTurn).getPlayerCards().size();

                        // add the drawn cards to the player's hand
                        players.get(whoseTurn).addMultipleToPlayerCards(deck.draw(howManyToDraw));

                    }

                }
                else if (players.get(whoseTurn).getPlayerCards().size() > 3)
                {

                    playedCard = players.get(whoseTurn).getCardInPlayerCards(selectedCard);

                    if (playedCard.getValue() == Value.TEN)
                    {
                        System.out.println("It's still " + players.get(whoseTurn).getName() + " 's turn");
                        System.out.println();

                        middleCards.add(playedCard);

                        players.get(whoseTurn).removeFromPlayerCards(selectedCard);

                        middleCards.clear();

                        System.out.println("---------------------------------------------------\n");

                        continue;
                    }

                    else if (playedCard.getValue() == Value.TWO)
                    {

                        middleCards.add(playedCard);

                        players.get(whoseTurn).removeFromPlayerCards(selectedCard);

                        System.out.println("---------------------------------------------------\n");

                        System.out.println("It's still " + players.get(whoseTurn).getName() + " 's turn");
                        System.out.println();

                        continue;
                    }

                    else
                    {

                        middleCards.add(players.get(whoseTurn).removeFromPlayerCards(selectedCard));

                    }

                }

            }

            else
            {
                System.out.println("You cannot play any card in your hand. You have to pick up from the middle.");

                players.get(whoseTurn).addMultipleToPlayerCards(middleCards);

                System.out.println();
                System.out.println(middleCards.size() + " cards have been added to player " + players.get(whoseTurn).getPlayerId() + " " + players.get(whoseTurn).getName() + "'s hand");

                middleCards.clear();

                System.out.println("Please enter n for next to move onto the next player");

                String next = kbd.nextLine();

                while (!next.equalsIgnoreCase("n"))
                {
                    System.out.println("Please enter n for next");

                    next = kbd.nextLine();
                }

            }

            // change turn
            whoseTurn = changeTurn(whoseTurn);

            System.out.println("__________________________________________________\n");

        }

        System.out.println("Would you like to play again ? y/n ");

        String confirmation = kbd.nextLine();

        while (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n"))
        {
            System.out.println("Please enter y for yes or n for n. Do you wish to play Palace again? ");

            confirmation = kbd.nextLine();

        }

        if (confirmation.equalsIgnoreCase("y"))
        {
            setUpNumOfPlayers();
        }

        else
        {
            System.out.println("Thank you for playing Palace");
        }
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

    /**
     * If the selected card is greater than or equal to the middle card or is a two or ten,
     * then that is a valid card to put down in the middle
     * @param selectedCard
     * @param middleCard
     * @return
     */
    public boolean isValid(Card selectedCard, Card middleCard)
    {

        if (selectedCard.getValue() == Value.TWO || selectedCard.getValue() == Value.TEN)
        {
            return true;
        }

        else if (selectedCard.getValueOfCard() >= middleCard.getValueOfCard())
        {
            return true;
        }

        return false;

    }

    /**
     * Determine when the game ends
     * @return
     */
    public boolean areAllPlayersOut()
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

    public static void main(String[] args)
    {
        Palace p = new Palace();

        p.setUpNumOfPlayers();

        p.setUpPlayers();

    }
}
