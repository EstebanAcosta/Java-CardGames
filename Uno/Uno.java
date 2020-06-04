package Uno;

import java.util.ArrayList;

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

    public void setUpNumPlayers()
    {

    }

    public void setUpPlayers()
    {

    }

    public void setUpGame()
    {

        startGame();
    }

    public void startGame()
    {

    }

    public static void main(String[] args)
    {
        Uno game = new Uno();

        game.setUpNumPlayers();

        game.setUpPlayers();

        game.setUpGame();
    }
}
