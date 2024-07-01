package memory_card_game.game_logic;

import javax.swing.*;

public interface GameGrid
{
    /**
     * Creates a JPanel containing a grid of cards. The grid is populated with pairs of cards
     * arranged in a random order, according to the specified number of rows and columns.
     * @param controller the CardController instance that manages the game logic and card interactions
     * @param rows       the number of rows in the grid
     * @param columns    the number of columns in the grid
     * @return a JPanel containing the grid of cards
     */
    JPanel createGrid(CardController controller, int rows, int columns);
}
