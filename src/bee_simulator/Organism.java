package bee_simulator;

import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
    private Rectangle energyBackgroundBar;
    private Rectangle energyBar;
    private Text energyBarText;

    //---------------- METHODS ----------------\\

    //-------- CONSTRUCTORS

    public Organism() {
        this(0, 0, 10, 10, 10, "", "");
    }

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
        organismContainer.setMaxHeight(collisionRadius * 2 + ENERGY_BAR_HEIGHT);

        if (shouldDrawEnergy) {
            energyBackgroundBar = new Rectangle(collisionRadius * 2, ENERGY_BAR_HEIGHT, Color.GRAY);
            energyBackgroundBar.setX(0);
            energyBackgroundBar.setY(0);
            energyBar = new Rectangle(collisionRadius * 2, ENERGY_BAR_HEIGHT, Color.LIGHTYELLOW);
            energyBar.setX(0);
            energyBar.setY(0);
            drawEnergy();
            organismContainer.getChildren().addAll(energyBackgroundBar, energyBar);
        }

        ImageView organismImage = new ImageView(new Image("file:" + imgUrl));
        organismImage.setPreserveRatio(true);
        organismImage.setFitWidth(collisionRadius * 2);
        organismImage.setY(ENERGY_BAR_HEIGHT);

        organismContainer.getChildren().addAll(organismImage);
    }

    public void addToGarden(Pane garden) {
        garden.getChildren().add(organismContainer);
    }

    //-------- ABSTRACT METHODS

    public abstract void collide(Organism otherOrganism);

    public abstract void update();

    //-------- DRAWING METHODS

    public void draw() {
        organismContainer.setLayoutX(centerX - collisionRadius);
        organismContainer.setLayoutY(centerY - collisionRadius - (ENERGY_BAR_HEIGHT));
        organismContainer.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        drawEnergy();
    }

    public void drawEnergy() {
        energyBackgroundBar.setWidth(collisionRadius * 2);
        energyBackgroundBar.toBack();
        energyBar.setWidth((int) (collisionRadius * 2 * (energy / (double) maxEnergy)));
        energyBar.toFront();
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
