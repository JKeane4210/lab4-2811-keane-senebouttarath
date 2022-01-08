package bee_simulator;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Organism {

    //-------- CONSTANTS --------\\

    private static final int ENERGY_BAR_HEIGHT = 10;

    //-------- ATTRIBUTES --------\\

    protected int centerX;
    protected int centerY;

    protected int collisionRadius;

    protected int maxEnergy;
    protected int energy;

    protected String description;

    protected VBox organismContainer;
    private Rectangle energyBackgroundBar;
    private Rectangle energyBar;
    private Label energyBarText;

    //---------------- METHODS ----------------\\

    //-------- CONSTRUCTORS

    public Organism() {
        this(0, 0, 10, 10, 10, "", "");
    }

    public Organism(int centerX, int centerY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String description) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.collisionRadius = collisionRadius;
        this.maxEnergy = maxEnergy;
        this.energy = energy;
        this.description = description;

        organismContainer = new VBox();

        energyBackgroundBar = new Rectangle();
        energyBar = new Rectangle();
        energyBarText = new Label();

        ImageView organismImage = new ImageView(new Image("file:" + imgUrl));
        organismImage.setPreserveRatio(true);
        organismImage.setFitWidth(collisionRadius * 2);

        organismContainer.getChildren().add(energyBackgroundBar);
        organismContainer.getChildren().add(energyBar);
        organismContainer.getChildren().add(energyBarText);
        organismContainer.getChildren().add(organismImage);
    }

    public void addToGarden(Pane garden) {
        garden.getChildren().add(organismContainer);
    }

    //-------- ABSTRACT METHODS

    public abstract void collide(Organism otherOrganism);

    public abstract void update();

    public abstract void draw();

    //-------- CONCRETE METHODS

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

    public void drawHealthBar() {
        int barPosX = this.getCenterX() - this.getCollisionRadius();
        int barPosY = this.getCenterY() - this.getCollisionRadius() - ENERGY_BAR_HEIGHT;
        int width = this.getCollisionRadius() * 2;

        energyBackgroundBar.setHeight(ENERGY_BAR_HEIGHT);
        energyBackgroundBar.setWidth(width);
        energyBackgroundBar.setX(barPosX);
        energyBackgroundBar.setY(barPosY);

        energyBar.setHeight(ENERGY_BAR_HEIGHT);
        energyBar.setWidth(energy / maxEnergy);
        energyBar.setX(barPosX);
        energyBar.setY(barPosY);

        energyBarText.setText(formattedEnergyText());
        energyBarText.setMaxHeight(ENERGY_BAR_HEIGHT);
        energyBarText.setMaxWidth(width);
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

    private double euclideanDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private String formattedEnergyText() {
        return this.getEnergy() + " / " + this.getMaxEnergy();
    }
}   //end class Organism
