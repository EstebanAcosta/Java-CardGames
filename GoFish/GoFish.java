package GoFish;

import java.util.ArrayList;
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

        // Convert the string input into an integer
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

            // convert the player input into an integer
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
        if (players.size() < 4)
        {
            for (Player p : players)
            {
                p.addMultipleToPlayerHand(deck.draw(7));
            }
        }

        else
        {
            for (Player p : players)
            {
                p.addMultipleToPlayerHand(deck.draw(5));
            }
        }

        // loop through the list of players and ask each player for a name a
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

        while (currentRound < numOfRounds)
        {
            while (isSomeoneOut() == false && deck.getSize() > 0)
            {

                boolean askForMore = true;

                while (askForMore)
                {

                    players.get(whoseTurn).showPlayerCards();

                    System.out.println("Which player do you want to ask for a card," + players.get(whoseTurn).getName() + "? \n");

                    int count = 1;

                    for (Player p : players)
                    {
                        if (!p.equals(players.get(whoseTurn)))
                        {
                            System.out.println(count + ": " + p.getName());

                        }

                        count++;

                    }

                    String whichPlayerToAsk = kbd.nextLine();

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

                    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    System.out.println("Which card do you want " + players.get(thisPlayerIWantToAsk - 1).getName() + " to give you? ");

                    count = 1;

                    ArrayList<Value> currentPlayersRanks = players.get(whoseTurn).getAllRanksPlayerHas();

                    // Show all the ranks the current player has in their hand
                    for (Value rank : currentPlayersRanks)
                    {
                        System.out.println(count + ": " + rank);

                        count++;
                    }

                    String whichRankToAsk = kbd.nextLine();

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

                    players.get(thisPlayerIWantToAsk - 1).showPlayerCards();

                    // the current player's rank that the player selected
                    Value rankIWant = currentPlayersRanks.get(thisRankIWant - 1);

                    // an array list of all the ranks that the selected player has in their hand
                    ArrayList<Value> selectedPlayersRanks = players.get(thisPlayerIWantToAsk - 1).getAllRanksPlayerHas();

                    // see if the current player's rank is in the selected player's ranks
                    // if the selected player has the card the current player is looking for
                    if (selectedPlayersRanks.contains(rankIWant))
                    {
                        // display a message showing that the selected player has that rank
                        System.out.println(players.get(thisPlayerIWantToAsk - 1).getName() + " has a " + rankIWant);

                        // and make sure that this is set to true so the current player can keep asking for more cards
                        askForMore = true;

                        // loop through the selected player's hand
                        for (int i = 0; i < players.get(thisPlayerIWantToAsk - 1).getPlayerCards().size(); i++)
                        {
                            // if the card's rank in the selected player's hand is what the current player is looking for
                            if (players.get(thisPlayerIWantToAsk - 1).getPlayerCards().get(i).getValue() == rankIWant)
                            {
                                // remove the card from the selected player's hand and put it in the current player's hand
                                players.get(whoseTurn).addOneToPlayerHand(players.get(thisPlayerIWantToAsk - 1).removeOneFromPlayerHand(i + 1));
                            }
                        }

                        for (Card c : players.get(whoseTurn).getPlayerCards())
                        {
                            if (true)
                            {

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

                        System.out.println("\nPress any key to continue");

                        String next = kbd.nextLine();
                    }

                    System.out.println("------------------------------------------------------------------------");

                }

                System.out.println("____________________________________________________________________________\n");

                whoseTurn = changeTurn(whoseTurn);

            }
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
