/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Lab 4: Bees
 * Author:     Jonathan Keane & Kyle Senebouttarath
 * Date:       1/6/22
 */
package bee_simulator;

import javafx.scene.effect.ColorAdjust;

/**
 * A Bee is responsible for representing bees within the garden. Bees are moving organisms that follow a movement
 * pattern to move around the garden. When bees hit a flower, they will attempt to take energy from the flower
 * for themselves. If a bee hits another bee, they will crash, and both lose some energy. When bees run out of
 * energy, they die. Bees lose energy as they move.
 *
 * @author Kyle Senebouttarath
 */
public abstract class Bee extends Organism {

    //---------------- CONSTANTS ----------------\\


    //---------------- ATTRIBUTES ----------------\\

    protected int moveDistance;     //The "speed" of the bee. This is how much they move per tick
    protected int beeCollisionEnergyLoss;   //Amount of energy lost on bee collision with another bee

    protected int targetX = 0;      //The x goal point in the garden to move to
    protected int targetY = 0;      //The y goal point in the garden to move to

    protected BeeMovementPattern movementPattern;       //Movement pattern for bee
    private Flower lastVisitedFlower;       //Tracks last visited flower to change flower targets


    //---------------- METHODS ----------------\\

    //-------- CONSTRUCTORS

    /**
     * The primary constructor for a bee. Creates a bee with given stats.
     *
     * @param startX The starting x-coordinate of the bee (in middle of bee)
     * @param startY The starting y-coordinate of the bee (in middle of bee)
     * @param collisionRadius The circle radius which are the bounds of collision for the bee
     * @param maxEnergy The maximum amount of energy the bee can have
     * @param energy The starting energy the bee will have
     * @param imgUrl The image that represents the bee on the GUI
     * @param desc The description of the bee
     * @param moveDistance The amount of pixels the bee moves per tick
     * @param beeCollisionEnergyLoss The amount of energy the bee loses when it hits another bee
     * @param shouldDrawEnergy Indicates whether the energy bar should be shown or not
     */
    public Bee(int startX, int startY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String desc, int moveDistance, int beeCollisionEnergyLoss, boolean shouldDrawEnergy) {
        super(startX, startY, collisionRadius, maxEnergy, energy, imgUrl, desc, shouldDrawEnergy);
        this.moveDistance = moveDistance;
        this.beeCollisionEnergyLoss = beeCollisionEnergyLoss;
    }

    /**
     * The secondary constructor for a bee, which is primarily used for bees in the garden.
     *
     * @param startX The starting x-coordinate of the bee (in middle of bee)
     * @param startY The starting y-coordinate of the bee (in middle of bee)
     * @param collisionRadius The circle radius which are the bounds of collision for the bee
     * @param maxEnergy The maximum amount of energy the bee can have
     * @param energy The starting energy the bee will have
     * @param imgUrl The image that represents the bee on the GUI
     * @param desc The description of the bee
     * @param moveDistance The amount of pixels the bee moves per tick
     * @param beeCollisionEnergyLoss The amount of energy the bee loses when it hits another bee
     */
    public Bee(int startX, int startY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String desc, int moveDistance, int beeCollisionEnergyLoss) {
        this(startX, startY, collisionRadius, maxEnergy, energy, imgUrl, desc, moveDistance, beeCollisionEnergyLoss, true);
    }

    /**
     * The secondary constructor for a bee, which is primarily used for bees in the garden. Maintained for backwards compatibility
     *
     * @param startX The starting x-coordinate of the bee (in middle of bee)
     * @param startY The starting y-coordinate of the bee (in middle of bee)
     * @param collisionRadius The circle radius which are the bounds of collision for the bee
     * @param maxEnergy The maximum amount of energy the bee can have
     * @param energy The starting energy the bee will have
     * @param imgUrl The image that represents the bee on the GUI
     * @param desc The description of the bee
     * @param moveDistance The amount of pixels the bee moves per tick
     */
    public Bee(int startX, int startY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String desc, int moveDistance) {
        this(startX, startY, collisionRadius, maxEnergy, energy, imgUrl, desc, moveDistance, 0, true);
    }

    /**
     * The secondary constructor for a bee, which is primarily used for bees in the garden. Maintained for backwards compatibility
     *
     * @param startX The starting x-coordinate of the bee (in middle of bee)
     * @param startY The starting y-coordinate of the bee (in middle of bee)
     * @param collisionRadius The circle radius which are the bounds of collision for the bee
     * @param maxEnergy The maximum amount of energy the bee can have
     * @param energy The starting energy the bee will have
     * @param imgUrl The image that represents the bee on the GUI
     * @param desc The description of the bee
     * @param moveDistance The amount of pixels the bee moves per tick
     * @param shouldDrawEnergy Indicates whether the energy bar should be shown or not
     */
    public Bee(int startX, int startY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String desc, int moveDistance, boolean shouldDrawEnergy) {
        this(startX, startY, collisionRadius, maxEnergy, energy, imgUrl, desc, moveDistance, 0, shouldDrawEnergy);
    }

    //-------- CONSTRUCTORS

    /**
     * Updates the position of the bee based on the movement pattern
     */
    public void move() {
        movementPattern.moveBee();
    }

    /**
     * Draws the bee based on the currently set position
     */
    public void draw() {
        super.draw();
    }

    /**
     * Updates the bee's movement, energy, and alive state for 1 tick
     */
    public void update() {
        decreaseEnergy(1);
        if (energy > 0) {
            move();
        } else {
            die();
        }
        if (movementPattern.targetAchieved()) {
            movementPattern.retarget();
            turnTowardsTarget();
        }
    }   //end method update

    /**
     * Handles the collision interaction from this bee to another bee
     * When another bee hits this bee, decrease energy and reset the new target
     */
    public void hitByBee() {
        decreaseEnergy(beeCollisionEnergyLoss);     //The other bee should decrease its energy as well
        setTarget();
    }

    /**
     * Rotates the image of the bee container to face the target
     */
    public void turnTowardsTarget() {
        organismImage.setRotate(Math.toDegrees(Math.atan2(targetY - centerY, targetX - centerX)) + 90);
    }

    /**
     * Change the display of the bee on death
     */
    public void die() {
        organismContainer.getChildren().removeAll(energyBar, energyBackgroundBar, energyBarText);
        ColorAdjust adj = new ColorAdjust();
        adj.setSaturation(-1.0);
        adj.setBrightness(-0.5);
        organismImage.setEffect(adj);
    }

    /**
     * Updates the target in the bee's movement pattern
     */
    public void setTarget() {
        movementPattern.retarget();
        turnTowardsTarget();
    }

    /**
     * Handles the collision actions when a bee collides with another organism in the garden
     *
     * @param otherOrganism The organism the bee is colliding with
     */
    public void collide(Organism otherOrganism) {
        if (otherOrganism instanceof Flower && lastVisitedFlower != otherOrganism) {
            ((Flower)otherOrganism).interactWithBee(this);
            lastVisitedFlower = (Flower)otherOrganism;
        } else if (otherOrganism instanceof Bee) {
            hitByBee();
        }
    }

    /**
     * Sets up a new reference to a movement pattern object for a bee.
     * @param movementPattern The movement pattern of the bee
     */
   public void setMovementPattern(BeeMovementPattern movementPattern) {
        this.movementPattern = movementPattern;
   }

    /**
     * Returns the currently set movement pattern of the bee
     *
     * @return The bee's movement pattern
     */
   public BeeMovementPattern getMovementPattern() {
       return movementPattern;
   }

    /**
     * Getter for the bee's move distance per update
     * @return The bee's speed
     */
   public int getMoveDistance() {
        return moveDistance;
   }

    /**
     * Returns the amount of energy lost when this bee hits another bee
     * @return Bee collision energy loss value
     */
   public int getBeeCollisionEnergyLoss() {
       return beeCollisionEnergyLoss;
   }

    /**
     * Sets the amount of energy lost when this bee hits another bee
     * @param energyLoss New bee collision energy loss value
     */
   public void setBeeCollisionEnergyLoss(int energyLoss) {
       this.beeCollisionEnergyLoss = energyLoss;
   }
}   //end abstract class bee
