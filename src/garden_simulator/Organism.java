/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Lab 4: Bees
 * Author:     Jonathan Keane & Kyle Senebouttarath
 * Date:       1/6/22
 */
package garden_simulator;

import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * An organism responsible for recognizing and handling collisions, managing health, and managing
 * positioning for an organism in the garden. A Bee or Flower can be an Organism in the garden.
 *
 * @author Kyle Senebouttarath (& some from Jonathan Keane)
 */
public abstract class Organism {

    //-------- CONSTANTS --------\\

    /**
     * The height of the energy bar, in pixels
     */
    protected static final int ENERGY_BAR_HEIGHT = 10;

    //-------- ATTRIBUTES --------\\

    //The x and y coordinates of the organism in the garden
    protected int centerX;
    protected int centerY;

    //The radius of the collision circle around the organism; its size
    protected int collisionRadius;

    //The initial and maximum amount of energy values for organisms
    protected int maxEnergy;
    protected int energy;

    //The organism's description
    protected String description;

    //The JavaFX implementation stuff for organisms
    protected Pane organismContainer;
    protected ImageView organismImage;
    protected Rectangle energyBackgroundBar;
    protected Rectangle energyBar;
    protected Text energyBarText;

    //---------------- METHODS ----------------\\

    //-------- CONSTRUCTORS

    /**
     * Secondary constructor for organisms, but it's assumed that the organism will be in the garden and display energy
     *
     * @param centerX The x-coordinate position of the organism
     * @param centerY The y-coordinate position of the organism
     * @param collisionRadius The size of the organism
     * @param maxEnergy The max amount of energy can have
     * @param energy The initial energy amount of the organism. Increases/decreases over time
     * @param imgUrl The image that represents the organism
     * @param description The description of the organism
     */
    public Organism(int centerX, int centerY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String description) {
        this(centerX, centerY, collisionRadius, maxEnergy, energy, imgUrl, description, true);
    }

    /**
     * Primary constructor for organisms. Sets up the JavaFX of the organism as well
     *
     * @param centerX The x-coordinate position of the organism
     * @param centerY The y-coordinate position of the organism
     * @param collisionRadius The size of the organism
     * @param maxEnergy The max amount of energy can have
     * @param energy The initial energy amount of the organism. Increases/decreases over time
     * @param imgUrl The image that represents the organism
     * @param description The description of the organism
     * @param shouldDrawEnergy Whether the health bar of the organism should show
     */
    public Organism(int centerX, int centerY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String description, boolean shouldDrawEnergy) {

        //Establish basic properties
        this.centerX = centerX;
        this.centerY = centerY;
        this.collisionRadius = collisionRadius;
        this.maxEnergy = maxEnergy;
        this.energy = energy;
        this.description = description;

        //Creates a Pane to hold the organism's GUI representation elements
        organismContainer = new Pane();
        organismContainer.setMaxWidth(collisionRadius * 2);
        organismContainer.setMaxHeight(shouldDrawEnergy ? collisionRadius * 2 + ENERGY_BAR_HEIGHT : collisionRadius * 2);

        //Creates the organism's energy bar, if set to do so. Adds it to Pane created above
        if (shouldDrawEnergy) {

            //Background bar
            energyBackgroundBar = new Rectangle(collisionRadius * 2, ENERGY_BAR_HEIGHT, Color.GRAY);
            energyBackgroundBar.setX(0);
            energyBackgroundBar.setY(0);
            drawEnergyBackground();

            //yellow energy bar
            energyBar = new Rectangle(collisionRadius * 2, ENERGY_BAR_HEIGHT, Color.LIGHTYELLOW);
            energyBar.setX(0);
            energyBar.setY(0);

            //Energy bar text
            energyBarText = new Text();
            energyBarText.setX(0);
            energyBarText.setY(-5);

            //Draw energy bar and text
            drawEnergy();

            //Add to organism Pane
            organismContainer.getChildren().addAll(energyBackgroundBar, energyBar, energyBarText);
        }

        //Creates image representation of organism
        organismImage = new ImageView(new Image("file:" + imgUrl));
        organismImage.setPreserveRatio(true);
        organismImage.setFitWidth(collisionRadius * 2);
        if (shouldDrawEnergy) {     //Readjust the height of the image if the health bar exists
            organismImage.setY(ENERGY_BAR_HEIGHT);
        }

        //Add the image to the Pane
        organismContainer.getChildren().addAll(organismImage);

    }   //end constructor Organism

    //-------- ABSTRACT METHODS

    /**
     * The behavior the organism will perform when collided with
     *
     * @param otherOrganism The other organism that was collided with
     */
    public abstract void collide(Organism otherOrganism);

    /**
     * The code that runs every update interval for the organism
     */
    public abstract void update();

    //-------- CONCRETE DRAWING AND UI METHODS

    /**
     * Adds the organism pane to a given pane; expected as the garden
     *
     * @param garden The garden's Pane
     */
    public void addToGarden(Pane garden) {
        garden.getChildren().add(organismContainer);
    }

    /**
     * Removes the organism pane to a given pane; expected as the garden
     *
     * @param garden The garden's Pane
     */
    public void removeFromGarden(Pane garden)  {
        garden.getChildren().remove(organismContainer);
    }

    /**
     * The default drawing of an organism. The center of the organism's image is set to the x and y
     * This method also updates the health bar UI
     */
    public void draw() {
        if (organismContainer.getLayoutX() != Garden.GARDEN_X_PADDING + centerX - collisionRadius ||
                organismContainer.getLayoutY() != Garden.GARDEN_Y_PADDING + centerY - collisionRadius - (ENERGY_BAR_HEIGHT)) {
            organismContainer.setLayoutX(Garden.GARDEN_X_PADDING + centerX - collisionRadius);
            organismContainer.setLayoutY(Garden.GARDEN_Y_PADDING + centerY - collisionRadius - (ENERGY_BAR_HEIGHT));
        }
        drawEnergy();
    }

    /**
     * Draws/updates the energy UI
     */
    public void drawEnergy() {
        energyBar.setWidth((int) (collisionRadius * 2 * (energy / (double) maxEnergy)));
        energyBar.toFront();
        energyBarText.setText("" + energy + "/" + getMaxEnergy());
    }

    /**
     * Updates the background of the energy UI
     */
    public void drawEnergyBackground() {
        energyBackgroundBar.setWidth(collisionRadius * 2);
        energyBackgroundBar.toBack();
    }

    //-------- CONCRETE METHODS

    /**
     * Returns whether the organism is alive or dead. It is alive as long as the energy is greater than 0.
     *
     * @return The living state of the organism
     */
    public boolean isAlive() {
        return energy > 0;
    }

    /**
     * Returns the x-position of the organism
     *
     * @return The x-coordinate position
     */
    public int getCenterX() {
        return centerX;
    }

    /**
     * Returns the y-position of the organism
     *
     * @return The y-coordinate position
     */
    public int getCenterY() {
        return centerY;
    }

    /**
     * Sets the x-position of the organism
     *
     * @param centerX The new x-coordinate position
     */
    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    /**
     * Sets the y-position of the organism
     *
     * @param centerY The new y-coordinate position
     */
    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    /**
     * Sets both the x and y position of the organism
     *
     * @param centerX The new x-coordinate position
     * @param centerY The new y-coordinate position
     */
    public void setPosition(int centerX, int centerY) {
        setCenterX(centerX);
        setCenterY(centerY);
    }

    /**
     * Returns the collision radius of the organism
     *
     * @return The size of the organism, as a circle radius. Inscribed in the JavaFX Pane
     */
    public int getCollisionRadius() {
        return this.collisionRadius;
    }

    /**
     * Adjusts the collision radius of the organism
     *
     * @param collisionRadius The new collision radius of the organsim
     */
    public void setCollisionRadius(int collisionRadius) {
        this.collisionRadius = collisionRadius;
    }

    /**
     * Performs the calculation and returns whether two organisms are inside each other's collision radii.
     * Collision is also true if the borders of the radii are also touching.
     *
     * @param otherOrganism The other organism being collided with
     * @return Whether the two organisms are colliding or not
     */
    public boolean isCollided(Organism otherOrganism) {
        int maxRadius = this.getCollisionRadius() + otherOrganism.getCollisionRadius();
        double distanceBetweenOrganisms = euclideanDistance(this.getCenterX(), this.getCenterY(), otherOrganism.getCenterX(), otherOrganism.getCenterY());
        return distanceBetweenOrganisms <= maxRadius;
    }

    /**
     * Increases the energy of the organism. Cannot go above max.
     *
     * @param energy Energy to increase by
     */
    public void increaseEnergy(int energy) {
        setEnergy(this.energy + energy);
    }

    /**
     * Decreases the energy of the organism. Cannot go below -.
     *
     * @param energy Energy to decrease by
     */
    public void decreaseEnergy(int energy) {
        setEnergy(this.energy - energy);
    }

    /**
     * Sets the energy level of the organism. Setting the energy above the max or
     * below 0 will adjust it to be the max or 0, accordingly.
     *
     * @param energy Energy level to set the organism to
     */
    private void setEnergy(int energy) {
        this.energy = energy;
        if (this.energy > maxEnergy) {
            this.energy = maxEnergy;
        } else if (this.energy < 0) {
            this.energy = 0;
        }
    }

    /**
     * Returns the energy of the organism
     *
     * @return The energy
     */
    public int getEnergy() {
        return this.energy;
    }

    /**
     * Returns the max energy of the organism
     *
     * @return The max energy
     */
    public int getMaxEnergy() {
        return this.maxEnergy;
    }

    /**
     * Returns the description (life's purpose) of the organism
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the organism to a new one.
     * @param description The new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Calculates the euclidean distance between two (x, y) coordinate sets.
     * The euclidean distance is a straight line from point 1 to point 2.
     *
     * @param x1 The first point's x
     * @param y1 The first point's y
     * @param x2 The second point's x
     * @param y2 The second point's y
     * @return The distance between the two points
     */
    public double euclideanDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    /**
     * Returns the Pane container of the organism
     * @return The organism's Pane container
     */
    public Pane getOrganismContainer() {
        return organismContainer;
    }

}   //end class Organism
