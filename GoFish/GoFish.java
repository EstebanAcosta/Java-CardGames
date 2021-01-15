package GoFish;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

public class GoFish
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
        System.out.println("(Mininumum # of players is 2 and Maximum # of players is 6) \n");

        String numberOfPlayers = "";

        int numPlayers = 0;

        // If the player puts a number greater than 4 or less than 2
        // Continue prompting the player until they give
        // a number between 2 and 4
        while (numPlayers < 2 || numPlayers > 6)
        {
            System.out.println("Please keep the number of players between 2 and 6");

            // get player input
            numberOfPlayers = kbd.nextLine();

            // if player gives a non-numerical answer
            // continue prompting player until they give a numeric answer
            while (!numberOfPlayers.matches("[0-9]+"))
            {
                System.out.println("Please enter a number");

                // get player input
                numberOfPlayers = kbd.nextLine();
            }

            // convert the string input into an integer
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

        // give each player a certain number of cards depending on how many players there are in the game
        // if there are less than four players, each player gets seven cards
        if (players.size() < 4)
        {
            for (Player p : players)
            {
                p.addMultipleToPlayerHand(deck.draw(7));
            }
        }

        // if there are more than or equal to four players, each player gets five cards
        else
        {
            for (Player p : players)
            {
                p.addMultipleToPlayerHand(deck.draw(5));
            }
        }

        // loop through the list of players and ask each player for a name
        for (int i = 0; i < players.size(); i++)
        {
            System.out.println("What is player " + (i + 1) + "'s name:");

            // ask each player for their name
            String playerName = kbd.nextLine();

            // store their name
            players.get(i).setName(playerName);

            System.out.println(players.get(i).getName() + " has " + players.get(i).getNumPlayerCards() + " cards");

            System.out.println("__________________________________________________\n");
        }

        setUpGame(deck);
    }

    public void setUpGame(Deck deck)
    {

        Scanner kbd = new Scanner(System.in);

        System.out.println("What is the number of rounds you want?\n");
        System.out.println("The min # is 1 and the max # is 10");

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
        // a number between 1 and 10
        while (numberOfRounds > 10 || numberOfRounds < 1)
        {
            System.out.println("Please enter a number that is between 1 and 10");

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

        startGame(deck, numberOfRounds);
    }

    public void startGame(Deck deck, int numOfRounds)
    {
        Random rand = new Random();

        int whoseTurn = rand.nextInt(players.size());

        Scanner kbd = new Scanner(System.in);

        int currentRound = 0;

        // continue looping until there are no more rounds
        while (currentRound < numOfRounds)
        {
            // continue looping until someone has no more cards in their hand or there are no more cards in the middle
            while (isSomeoneOut() == false && deck.getSize() > 0)
            {

                boolean askForMore = true;

                // continue looping until the current player can't ask for more cards because
                // another player doesn't have the card they're looking for
                while (askForMore)
                {

                    System.out.println(players.get(whoseTurn).getName() + " has " + players.get(whoseTurn).getListOfBooks().size() + (players.get(whoseTurn).getListOfBooks().size() > 1 ? " books" : "  book ") + " so far\n");
                    // show the current player's card
                    players.get(whoseTurn).showPlayerCards();

                    System.out.println("Which player do you want to ask for a card," + players.get(whoseTurn).getName() + "? \n");

                    int count = 1;

                    // show all the players that the current player can ask for a card
                    for (Player p : players)
                    {
                        // Don't include the current player in the list of options
                        if (!p.equals(players.get(whoseTurn)))
                        {
                            System.out.println(count + ": " + p.getName());

                        }

                        count++;

                    }

                    // get user input on which player they wish to ask
                    String whichPlayerToAsk = kbd.nextLine();

                    // continue looping until they finally enter a numerical response
                    while (!whichPlayerToAsk.matches("[0-9]+"))
                    {
                        System.out.println("Please enter a number for which player you want to ask a card");

                        whichPlayerToAsk = kbd.nextLine();

                    }

                    // convert input into an integer
                    int thisPlayerIWantToAsk = Integer.parseInt(whichPlayerToAsk);

                    // Continue promoting the player until they provide
                    // a number between 1 and the max number of players in the game
                    while (thisPlayerIWantToAsk > players.size() || thisPlayerIWantToAsk < 1 || thisPlayerIWantToAsk == players.get(whoseTurn).getPlayerId())
                    {

                        if (thisPlayerIWantToAsk > players.size() || thisPlayerIWantToAsk < 1)
                        {
                            System.out.println("Please enter a number that is between 1 and " + players.size());

                        }

                        else
                        {
                            System.out.println("You cannot choose yourself. Please choose another player for cards.");
                        }

                        // get player input
                        whichPlayerToAsk = kbd.nextLine();

                        // continue prompting player until the player gives a number
                        while (!whichPlayerToAsk.matches("[0-9]+"))
                        {
                            System.out.println("Please enter a number for which player you want to ask a card");

                            whichPlayerToAsk = kbd.nextLine();
                        }
                        // convert the player input into an integer
                        thisPlayerIWantToAsk = Integer.parseInt(whichPlayerToAsk);
                    }

                    System.out.println();

                    System.out.println("Which card do you want " + players.get(thisPlayerIWantToAsk - 1).getName() + " to give you? ");

                    count = 1;

                    ArrayList<Rank> currentPlayersRanks = players.get(whoseTurn).getAllRanksPlayerHas();

                    // Show all the ranks the current player has in their hand
                    for (Rank rank : currentPlayersRanks)
                    {
                        System.out.println(count + ": " + rank);

                        count++;
                    }

                    // get user input for which rank they want to ask the other player
                    String whichRankToAsk = kbd.nextLine();

                    // continue looping until user inputs a numerical answer
                    while (!whichRankToAsk.matches("[0-9]+"))
                    {
                        System.out.println("Please enter a number for which rank you want");

                        whichRankToAsk = kbd.nextLine();
                    }

                    // convert input into an integer
                    int thisRankIWant = Integer.parseInt(whichRankToAsk);

                    // Continue promoting the player until they provide
                    // a number between 1 and the number of ranks
                    while (thisRankIWant > players.get(whoseTurn).getAllRanksPlayerHas().size() || thisRankIWant < 1)
                    {
                        System.out.println("Please enter a number that is between 1 and " + players.get(whoseTurn).getAllRanksPlayerHas().size());

                        // get player input
                        whichRankToAsk = kbd.nextLine();

                        // continue prompting player until the player gives a number
                        while (!whichRankToAsk.matches("[0-9]+"))
                        {
                            System.out.println("Please enter a number for which rank you want");

                            whichRankToAsk = kbd.nextLine();
                        }
                        // convert the player input into an integer
                        thisRankIWant = Integer.parseInt(whichRankToAsk);
                    }

                    // the current player's rank that the player selected
                    Rank rankIWant = currentPlayersRanks.get(thisRankIWant - 1);

                    // an array list of all the ranks that the selected player has in their hand
                    ArrayList<Rank> selectedPlayersRanks = players.get(thisPlayerIWantToAsk - 1).getAllRanksPlayerHas();

                    // see if the current player's rank is in the selected player's ranks
                    // if the selected player has the card the current player is looking for
                    if (selectedPlayersRanks.contains(rankIWant))
                    {
                        // display a message showing that the selected player has that rank
                        System.out.println(players.get(thisPlayerIWantToAsk - 1).getName() + " has a " + rankIWant);

                        // and make sure that this is set to true so the current player can keep asking for more cards
                        askForMore = true;

                        Iterator<Card> selectedPlayerCards = players.get(thisPlayerIWantToAsk - 1).getPlayerCards().iterator();

                        int position = 0;

                        while (selectedPlayerCards.hasNext())
                        {
                            Card nextCard = selectedPlayerCards.next();

                            if (position < players.get(thisPlayerIWantToAsk - 1).getPlayerCards().size() && nextCard.getRank() == rankIWant)
                            {
                                players.get(whoseTurn).addOneToPlayerHand(nextCard);

                                selectedPlayerCards.remove();

                                position++;
                            }
                        }

                        // make a hash table of all the ranks and how many times they appear in the player's hand
                        Hashtable<Rank, Integer> timesRankAppears = players.get(whoseTurn).howManyTimesThisRankAppears();

                        ArrayList<Card> book = new ArrayList<Card>();

                        // loop through the hash table
                        for (Entry<Rank, Integer> entry : timesRankAppears.entrySet())
                        {
                            // if a certain rank appears four times in a hash table
                            if (entry.getValue() == 4)
                            {
                                position = 0;

                                Iterator<Card> currentPlayerCards = players.get(whoseTurn).getPlayerCards().iterator();

                                while (currentPlayerCards.hasNext())
                                {
                                    Card nextCard = currentPlayerCards.next();

                                    if (position < players.get(whoseTurn).getPlayerCards().size() && nextCard.getRank() == entry.getKey())
                                    {

                                        book.add(nextCard);

                                        currentPlayerCards.remove();

                                        position++;
                                    }
                                }

                                players.get(whoseTurn).addBooks(book);
                            }
                        }

                    }

                    // if the selected player doesn't have the card the current player is looking for
                    else
                    {
                        // display a message showing that the selected player doesn't have that rank
                        System.out.println("GO FISH!!!\n");

                        System.out.println(players.get(thisPlayerIWantToAsk - 1).getName() + " doesn't have a " + rankIWant);

                        // make sure that this is set to false so the current player can't ask for more cards
                        askForMore = false;

                        // current player has to now pick up a card from the middle
                        players.get(whoseTurn).addOneToPlayerHand(deck.draw());

                        System.out.println("------------------------------------------------------------------------");

                        System.out.println("\nPress enter to continue");

                        kbd.nextLine();
                    }

                    System.out.println("------------------------------------------------------------------------");

                }

                System.out.println("____________________________________________________________________________\n");

                whoseTurn = changeTurn(whoseTurn);

            }

            // select a random player to be the winner
            Player winner = players.get(0);

            // loop through the players
            for (Player p : players)
            {
                // if this player has more books than the winner, make that player the winner
                // otherwise continue looping
                if (p.getListOfBooks().size() > winner.getListOfBooks().size())
                {
                    winner = p;
                }

            }

            System.out.println("The winner for round " + currentRound + " is player # " + winner.getPlayerId() + " " + winner.getName());

            currentRound++;

        }
    }

    /***
     * Determine if at least one player doesn't have any cards in their hand
     * @return
     */
    public boolean isSomeoneOut()
    {
        for (Player p : players)
        {
            if (p.getNumPlayerCards() == 0)
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

        GoFish game = new GoFish();

        game.setUpNumOfPlayers();

        game.setUpPlayers();

    }
}
