package President;

import java.util.ArrayList;
import java.util.Scanner;

public class President
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
        System.out.println("Welcome To President \n");

        Scanner kbd = new Scanner(System.in);

        System.out.println("How many players are going to play in the game?");
        System.out.println("(Mininumum # of players is 4 and Maximum # of players is 6) ");

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

        // If the user puts a number greater than 6 or less than 4
        // Continue prompting the user until they give
        // a number between 2 and 4
        while (numPlayers < 4 || numPlayers > 7)
        {
            System.out.println("Please keep the number of players between 4 and 6");

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

        System.out.println("\n" + numPlayers + " players have been added to the game\n");
    }

    public void setUpPlayers()
    {
        Scanner kbd = new Scanner(System.in);

        // Create a deck of 52 cards
        Deck deck = new Deck();

        // shuffle the game deck
        deck.shuffle();

        // calculate how many cards we are supposed to distribute to each player
        int howManyCardsToEachPlayer = deck.getSize() / players.size();

        // if we can't give each player the same amount of cards
        if (deck.getSize() % players.size() != 0)
        {
            // calculate how many cards would be left over if we gave each player
            // roughly the same number of cards
            int leftOverCards = deck.getSize() % players.size();

            // and then give a certain number of random players
            // (that number is determined by the number of leftover cards left)
            // a card
            for (int i = 0; i < leftOverCards; i++)
            {
                players.get(i).addOneToPlayerHand(deck.draw());
            }

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

            // make sure that each status of the player is set to false
            // their out status at the beginning of each round should be set to false
            players.get(i).setOut(false);

            // their president status at the beginning of the first round should be set to false
            players.get(i).setPresident(false);

            // their vice president status at the beginning of the first round should be set to false
            players.get(i).setVicePresident(false);

            // their scum status at the beginning of the first round should be set to false
            players.get(i).setScum(false);

            // their vice scum status at the beginning of the first round should be set to false
            players.get(i).setViceScum(false);

            System.out.println(players.get(i).getName() + " has " + players.get(i).getNumOfPlayerCards() + " cards");

            System.out.println("__________________________________________________\n");

        }
    }

    public void setUpGame()
    {
        Scanner kbd = new Scanner(System.in);

        System.out.println("What is the number of rounds you want?\n");
        System.out.println("The min # is 1 and the max # is 10");
        String numRounds = kbd.nextLine();

        // if user gives a non-numerical answer
        // continue prompting user until they give a numeric answer
        while (!numRounds.matches("[0-9]+"))
        {
            System.out.println("Please enter a number for the number of rounds in this new game");

            numRounds = kbd.nextLine();
        }

        // convert input into an integer
        int numberOfRounds = Integer.parseInt(numRounds);

        // Continue promoting the user until they provide
        // a number between 0 and 5
        while (numberOfRounds > 10 || numberOfRounds < 1)
        {
            System.out.println("Please enter a number that is between 1 and 10");

            // get user input
            numRounds = kbd.nextLine();

            // continue prompting user until the user gives a number
            while (!numRounds.matches("[0-9]+"))
            {
                System.out.println("Please enter a number for the number of rounds in this new game");

                numRounds = kbd.nextLine();
            }
            // convert the user input into an integer
            numberOfRounds = Integer.parseInt(numRounds);
        }

        System.out.println("__________________________________________________\n");

        startGame(numberOfRounds);
    }

    public void startGame(int numRounds)
    {

        Scanner kbd = new Scanner(System.in);

        ArrayList<Card> middleCards = new ArrayList<Card>();

        Card threeOfClubs = new Card(Suit.CLUBS, Value.THREE);

        int whoseTurn = 0;

        // loop through the players participating in the game
        for (int j = 0; j < players.size(); j++)
        {
            // if this player has the three of clubs
            if (players.get(j).containsThisCard(threeOfClubs))
            {
                // this person will be the first player to put down a card
                whoseTurn = j;

                // loop through the player's hand
                for (int i = 0; i < players.get(j).getPlayerHand().size(); i++)
                {
                    // if the player's card is equal to the one we are looking for (in this case a three of clubs)
                    if (players.get(j).getCard(i).equals(threeOfClubs))
                    {

                        // remove the card from the person's hand and place it in the middle of table
                        middleCards.add(players.get(whoseTurn).removeOneFromPlayerCards(i + 1));

                    }
                }

                break;
            }
        }

        // since the player that has a three of clubs put down their card in the middle, it's the next player's turn
        whoseTurn = changeTurn(whoseTurn);

        // continue playing the game until there are no more rounds left
        while (numRounds > 0)
        {

            boolean putDownSingle = true;

            boolean putDownDouble = false;

            boolean putDownTriple = false;

            boolean putDownQuadruple = false;
            
            // continue playing this round until everyone is out
            while (isEveryoneOut() == false)
            {

                System.out.println("Middle Card:");

                System.out.println(middleCards.get(middleCards.size() - 1));

                // System.out.println("---------------------------------------------------\n");

                // show this player's hand
                players.get(whoseTurn).showPlayerCards();

                System.out.println("Which of these " + players.get(whoseTurn).getNumOfPlayerCards() + " cards do you wish to choose ?");

                // ask the player which card they want to put down
                String selectedCard = kbd.nextLine();

                // if user gives a non-numerical answer
                // continue prompting user until they give a numeric answer
                while (!selectedCard.matches("[0-9]+"))
                {
                    System.out.println("Please enter a number for the card you wish to put down in this game");

                    selectedCard = kbd.nextLine();
                }

                // Convert the string input into an integer
                int whichCard = Integer.parseInt(selectedCard);

                // If the user puts a number greater than the number of cards in the player's hand or less than 1
                // Continue prompting the user until they give
                // a number between 1 and the # of cards in the player's hand
                while (whichCard < 1 || whichCard > players.get(whoseTurn).getNumOfPlayerCards())
                {
                    System.out.println("Please choose a card that's within the range of between 1 and " + players.get(whoseTurn).getNumOfPlayerCards());

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
                    whichCard = Integer.parseInt(selectedCard);
                }

                Card thisCard = players.get(whoseTurn).getCardInPlayerCards(whichCard);

                while (isValidSelection(thisCard, middleCards.get(middleCards.size() - 1)) == false)
                {
                    System.out.println("Please choose a card that is higher than the middle card or a two");

                    whichCard = 0;

                    while (whichCard < 1 || whichCard > players.get(whoseTurn).getNumOfPlayerCards())
                    {

                        System.out.println("Please keep the selected card number between 1 and " + players.get(whoseTurn).getNumOfPlayerCards());

                        whichCard = 0;

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
                        whichCard = Integer.parseInt(selectedCard);
                    }

                    thisCard = players.get(whoseTurn).getCardInPlayerCards(whichCard);

                }

                // remove the selected card from the player's hand and put it down in the middle
                middleCards.add(players.get(whoseTurn).removeOneFromPlayerCards(whichCard));

                // if the card that's been placed down is a two, clear the middle cards
                if (thisCard.getValue() == Value.TWO)
                {
                    middleCards.clear();

                    // show this player's hand
                    players.get(whoseTurn).showPlayerCards();

                    System.out.println("How many cards do you wish to put down at the same time?\n");
                    System.out.println("1. Singles");
                    System.out.println("2. Doubles");
                    System.out.println("3. Triples");
                    System.out.println("4. Quadruples");

                    // ask the player which card they want to put down
                    String howManyCardsOfSameRank = kbd.nextLine();

                    // if user gives a non-numerical answer
                    // continue prompting user until they give a numeric answer
                    while (!howManyCardsOfSameRank.matches("[0-9]+"))
                    {
                        System.out.println("Please enter a number for the number of cards of the same rank you want to put down");

                        howManyCardsOfSameRank = kbd.nextLine();
                    }

                    // Convert the string input into an integer
                    int manyCardsOfSameRank = Integer.parseInt(howManyCardsOfSameRank);

                    // If the user puts a number greater than 4 or less than 1
                    // Continue prompting the user until they give
                    // a number between 1 and the # of cards in the player's hand
                    while (manyCardsOfSameRank < 1 || manyCardsOfSameRank > 4)
                    {
                        System.out.println("Please choose a card that's within the range of between 1 and 4");

                        // get user input
                        howManyCardsOfSameRank = kbd.nextLine();

                        // if user gives a non-numerical answer
                        // continue prompting user until they give a numeric answer
                        while (!howManyCardsOfSameRank.matches("[0-9]+"))
                        {
                            System.out.println("Please enter a number");

                            // get user input
                            howManyCardsOfSameRank = kbd.nextLine();
                        }

                        // convert the user input into an integer
                        manyCardsOfSameRank = Integer.parseInt(howManyCardsOfSameRank);
                    }

                    if(manyCardsOfSameRank == 2)
                    {
                        putDownSingle = false;

                        putDownDouble = true;

                        putDownTriple = false;

                        putDownQuadruple = false;
                    }
                    
                    else if(manyCardsOfSameRank == 3)
                    {
                        putDownSingle = false;

                        putDownDouble = false;

                        putDownTriple = true;

                        putDownQuadruple = false;
                    }
                    
                    else if(manyCardsOfSameRank == 4)
                    {
                        putDownSingle = false;

                        putDownDouble = false;

                        putDownTriple = false;

                        putDownQuadruple = true;
                    }
                    // now the player can put down another card
                    System.out.println("Which of these " + players.get(whoseTurn).getNumOfPlayerCards() + " cards do you wish to choose ?");

                    // ask the player which card they want to put down
                    selectedCard = kbd.nextLine();

                    // if user gives a non-numerical answer
                    // continue prompting user until they give a numeric answer
                    while (!selectedCard.matches("[0-9]+"))
                    {
                        System.out.println("Please enter a number for the card you wish to put down in this game");

                        selectedCard = kbd.nextLine();
                    }

                    // Convert the string input into an integer
                    whichCard = Integer.parseInt(selectedCard);

                    // If the user puts a number greater than the number of cards in the player's hand or less than 1
                    // Continue prompting the user until they give
                    // a number between 1 and the # of cards in the player's hand
                    while (whichCard < 1 || whichCard > players.get(whoseTurn).getNumOfPlayerCards())
                    {
                        System.out.println("Please choose a card that's within the range of between 1 and " + players.get(whoseTurn).getNumOfPlayerCards());

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
                        whichCard = Integer.parseInt(selectedCard);
                    }

                    // remove the selected card from the player's hand and put it down in the middle
                    middleCards.add(players.get(whoseTurn).removeOneFromPlayerCards(whichCard));

                }

                // change turns
                whoseTurn = changeTurn(whoseTurn);

                System.out.println("__________________________________________________\n");

            }

            numRounds--;

        }

    }

    /***
     * Checks to see if the player has put down card that can be placed on top of the card in the middle
     * @param selectedCard
     * @param middleCard
     * @return
     */
    public boolean isValidSelection(Card selectedCard, Card middleCard)
    {

        if (selectedCard.getValue() == Value.TWO || selectedCard.getValueOfCard() > middleCard.getValueOfCard())
        {
            return true;
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

    /***
     * Checks to see if every player in the game is out of the game for that round
     * @return
     */
    public boolean isEveryoneOut()
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
        President pres = new President();

        pres.setUpNumOfPlayers();

        pres.setUpPlayers();

        pres.setUpGame();
    }
}
