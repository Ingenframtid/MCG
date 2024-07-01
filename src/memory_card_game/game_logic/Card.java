package memory_card_game.game_logic;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The Card class represents a clickable card in a matching card game.
 * It manages the appearance, state (face-up or face-down), and interactions (mouse clicks) of the card.
 */
public class Card extends MouseAdapter
{
    // Declaring class variables
    private JLabel label;
    private CardController controller;
    public Icon faceIcon;
    public Icon backIcon;
    public boolean faceUp=false;   //Cards are flipped when the app is initialized
    public int num;
    public int iconWidthHalf, iconHeightHalf;
    public boolean mousePressedOnMe=false;

    /**
     * Constructor. Creates a new instance of the "Card" class with the specified parameters.
     * @param controller
     * @param face
     * @param back
     * @param num
     */
    public Card (CardController controller, Icon face, Icon back, int num) {
	this.label = new JLabel(back);
	this.controller=controller;
	this.faceIcon =face;
	this.backIcon=back;
	this.num=num;
	//Catch/listen after mouse clicks
	this.label.addMouseListener(this);
	this.iconHeightHalf=back.getIconHeight()/2;
	this.iconWidthHalf=face.getIconWidth()/2;
    }

    /**
     * Retrieves the JLabel associated with the card.
     * @return the JLabel representing the card
     */
    public JLabel getLabel() {
	return label;
    }

    /**
     * Method to get card number
     */
    public int getNum() {
	return num;
    }

    /**
     * Method to check if the mouse is over card icon
     */
    private boolean overIcon(int x, int y) {
	//Distance of the mouse click from the center of the card
	int distX=Math.abs(x-(this.label.getWidth()/2));
	int distY=Math.abs(y-(this.label.getHeight()/2));
	//Click if outside
	if (distX>this.iconHeightHalf||distY>this.iconWidthHalf)
	    return false;
	//Click is inside
	return true;
    }

    /**
     * Handles the mouseClicked event when the card is clicked. Turns the card face-up if clicked within its icon area.
     * @param e the MouseEvent representing the mouse click event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
	//Turn card if clicked
	if (overIcon(e.getX(),e.getY())) this.turnUp();
    }

    /**
     * Method to turn card up
     */
    public void turnUp() {
	//Card already facing up? do nothing
	if (this.faceUp) return;
	//test if it works to turn card up
	//this.faceUp=true;
	//Turn the card up when by controller (game logic)
	this.faceUp=this.controller.turnCardUp(this);
	if (this.faceUp) this.label.setIcon(this.faceIcon);
    }

    /**
     * Method to turn the card down
     */
    public void turnDown() {
	//already down? do nothing
	if (!this.faceUp) return;
	this.label.setIcon(this.backIcon);
	this.faceUp=false; //the card is facing down
    }

    /**
     * Records the mouse press event if it occurs within the card's icon area.
     * @param e the MouseEvent representing the mouse press event
     */
    @Override
    public void mousePressed(MouseEvent e) {
	if (overIcon(e.getX(),e.getY())) this.mousePressedOnMe = true;
    }

    /**
     * Handles the mouse release event to trigger a mouseClicked event if the mouse was pressed on the card's icon area.
     * @param e the MouseEvent representing the mouse release event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
	if (this.mousePressedOnMe){
	    this.mousePressedOnMe=false;
	    this.mouseClicked(e);
	}
    }

    /**
     * Resets the state when the mouse exits the card's icon area.
     * @param e the MouseEvent representing the mouse exit event
     */
    @Override
    public void mouseExited(MouseEvent e) {
	this.mousePressedOnMe=false;
    }
}

