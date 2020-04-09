/**
 *  _____ _____ _   _       _      ___ ______ _____ _____
 * |  __ |  ___| \ | |  _  | |    / _ \| ___ |  _  |____ |
 * | |  \| |__ |  \| | (_) | |   / /_\ | |_/ | | | |   / /
 * | | __|  __|| . ` |     | |   |  _  | ___ | | | |   \ \
 * | |_\ | |___| |\  |  _  | |___| | | | |_/ \ \_/ .___/ /
 *  \____\____/\_| \_/ (_) \_____\_| |_\____/ \___/\____/
 *
 * AUTHORS : Mattei Simon, Janssens Emmanuel, Potet Bastien
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;
    private String[] names = new String[]{"Kevin", "Henri", "Jean-Patrick"};

    @BeforeEach
    void initEach()
    {
        Random r = new Random();
        player = new Player(names[r.nextInt(names.length)]);
    }

    @AfterEach
    void afterEach(){
        player = null;
    }


    @DisplayName("The player's name cannot be null")
    @Test
    void nullPlayerNameShouldNotWork()
    {
        assertThrows(InvalidParameterException.class,() -> { Player p = new Player(null);});
    }

    @DisplayName("The player's name cannot be empty")
    @Test
    void emptyPlayerNameShouldNotWork()
    {
        assertThrows(InvalidParameterException.class,() -> { Player p = new Player("");});
    }

    @DisplayName("The initial cash is 1500")
    @Test
    void initialCashIs1500(){
        assertEquals(player.getNetWorth(), 1500);
    }

    @DisplayName("The turn cannot be taken on a null board")
    @Test
    void nullBoardShouldNotWork()
    {
        Board board = null;
        Cup cup = new Cup(2);
        Player p = new Player("kevin");
        assertThrows(InvalidParameterException.class,() -> { p.takeTurn(cup,board);} );
    }

    @DisplayName("The dices to be rolled must not be null")
    @Test
    void nullDicesShouldNotWork()
    {
        Board board = new Board();
        Cup cup = null;
        Player p = new Player("kevin");

        assertThrows(InvalidParameterException.class,() -> { p.takeTurn(cup,board);} );
    }


    @DisplayName("Player has moved from his initial Square")
    @Test
    void turnTaked()
    {
        Board board = new Board();
        Cup cup = new Cup(2);
        Player p = new Player("kevin");

        Square oldLoc = p.getPiece().getLocation();
        p.takeTurn(cup,board);
        Square newLoc = p.getPiece().getLocation();

        assertNotEquals(oldLoc.getName(),newLoc.getName());
    }
}