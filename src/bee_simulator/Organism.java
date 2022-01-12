/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Lab 4: Bees
 * Author:     Jonathan Keane & Kyle Senebouttarath
 * Date:       1/6/22
 */
package bee_simulator;

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

    protected static final int ENERGY_BAR_HEIGHT = 10;

    //-------- ATTRIBUTES --------\\

    protected int centerX;
    protected int centerY;

    protected int collisionRadius;

    protected int maxEnergy;
    protected int energy;

    protected String description;

    protected Pane organismContainer;
    protected ImageView organismImage;
    protected Rectangle energyBackgroundBar;
    protected Rectangle energyBar;
    private Text energyBarText;

    //---------------- METHODS ----------------\\

    //-------- CONSTRUCTORS

    public Organism(int centerX, int centerY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String description) {
        this(centerX, centerY, collisionRadius, maxEnergy, energy, imgUrl, description, true);
    }

    public Organism(int centerX, int centerY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String description, boolean shouldDrawEnergy) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.collisionRadius = collisionRadius;
        this.maxEnergy = maxEnergy;
        this.energy = energy;
        this.description = description;

        organismContainer = new Pane();
        organismContainer.setMaxWidth(collisionRadius * 2);
        organismContainer.setMaxHeight(shouldDrawEnergy ? collisionRadius * 2 + ENERGY_BAR_HEIGHT : collisionRadius * 2);

        if (shouldDrawEnergy) {
            energyBackgroundBar = new Rectangle(collisionRadius * 2, ENERGY_BAR_HEIGHT, Color.GRAY);
            energyBackgroundBar.setX(0);
            energyBackgroundBar.setY(0);
            energyBar = new Rectangle(collisionRadius * 2, ENERGY_BAR_HEIGHT, Color.LIGHTYELLOW);
            energyBar.setX(0);
            energyBar.setY(0);
            drawEnergyBackground();
            drawEnergy();
            organismContainer.getChildren().addAll(energyBackgroundBar, energyBar);
        }

        organismImage = new ImageView(new Image("file:" + imgUrl));
        organismImage.setPreserveRatio(true);
        organismImage.setFitWidth(collisionRadius * 2);
        if (shouldDrawEnergy) {
            organismImage.setY(ENERGY_BAR_HEIGHT);
        }

        organismContainer.getChildren().addAll(organismImage);
    }

    public void addToGarden(Pane garden) {
        garden.getChildren().add(organismContainer);
    }

    public void removeFromGarden(Pane garden)  {
        garden.getChildren().remove(organismContainer);
    }
    //-------- ABSTRACT METHODS

    public abstract void collide(Organism otherOrganism);

    public abstract void update();

    //-------- DRAWING METHODS

    public void draw() {
        if (organismContainer.getLayoutX() != Garden.GARDEN_X_PADDING + centerX - collisionRadius ||
                organismContainer.getLayoutY() != Garden.GARDEN_Y_PADDING + centerY - collisionRadius - (ENERGY_BAR_HEIGHT)) {
            organismContainer.setLayoutX(Garden.GARDEN_X_PADDING + centerX - collisionRadius);
            organismContainer.setLayoutY(Garden.GARDEN_Y_PADDING + centerY - collisionRadius - (ENERGY_BAR_HEIGHT));
        }
        drawEnergy();
    }

    public void drawEnergy() {
        energyBar.setWidth((int) (collisionRadius * 2 * (energy / (double) maxEnergy)));
        energyBar.toFront();
    }

    public void drawEnergyBackground() {
        energyBackgroundBar.setWidth(collisionRadius * 2);
        energyBackgroundBar.toBack();
    }


    //-------- CONCRETE METHODS

    public boolean isAlive() {
        return energy > 0;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setPosition(int centerX, int centerY) {
        setCenterX(centerX);
        setCenterY(centerY);
    }

    public int getCollisionRadius() {
        return this.collisionRadius;
    }

    public void setCollisionRadius(int collisionRadius) {
        this.collisionRadius = collisionRadius;
    }

    public boolean isCollided(Organism otherOrganism) {
        int maxRadius = this.getCollisionRadius() + otherOrganism.getCollisionRadius();
        double distanceBetweenOrganisms = euclideanDistance(this.getCenterX(), this.getCenterY(), otherOrganism.getCenterX(), otherOrganism.getCenterY());
        return distanceBetweenOrganisms <= maxRadius;
    }

    public void increaseEnergy(int energy) {
        setEnergy(this.energy + energy);
    }

    public void decreaseEnergy(int energy) {
        setEnergy(this.energy - energy);
    }

    private void setEnergy(int energy) {
        this.energy = energy;
        if (this.energy > maxEnergy) {
            this.energy = maxEnergy;
        } else if (this.energy < 0) {
            this.energy = 0;
        }
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getMaxEnergy() {
        return this.maxEnergy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double euclideanDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private String formattedEnergyText() {
        return this.getEnergy() + " / " + this.getMaxEnergy();
    }

    public Pane getOrganismContainer() {
        return organismContainer;
    }
}   //end class Organism
