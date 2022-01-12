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
    private static final int BEE_HEALTH_LOSS = 5;

    //---------------- ATTRIBUTES ----------------\\

    protected int moveDistance;
    protected int targetX = 0;
    protected int targetY = 0;
    protected BeeMovementPattern movementPattern;
    private Flower lastVisitedFlower;

    //---------------- METHODS ----------------\\

    //-------- CONSTRUCTORS

    public Bee(int startX, int startY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String desc, int moveDistance, boolean shouldDrawEnergy) {
        super(startX, startY, collisionRadius, maxEnergy, energy, imgUrl, desc, shouldDrawEnergy);
        this.moveDistance = moveDistance;
    }

    public Bee(int startX, int startY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String desc, int moveDistance) {
        this(startX, startY, collisionRadius, maxEnergy, energy, imgUrl, desc, moveDistance, true);
    }

    public void move() {
        movementPattern.moveBee();
    }

    public void draw() {
        super.draw();
    }

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
    }

    public void interactWithBee(Bee bee) {
        decreaseEnergy(BEE_HEALTH_LOSS);
        bee.decreaseEnergy(BEE_HEALTH_LOSS);
    }

    public void turnTowardsTarget() {
        organismImage.setRotate(Math.toDegrees(Math.atan2(targetY - centerY, targetX - centerX)) + 90);
    }

    public void die() {
        organismContainer.getChildren().removeAll(energyBar, energyBackgroundBar);
        ColorAdjust adj = new ColorAdjust();
        adj.setSaturation(-1.0);
        adj.setBrightness(-0.5);
        organismImage.setEffect(adj);
    }

    public void setTarget() {
        movementPattern.retarget();
        turnTowardsTarget();
    }

    public void collide(Organism otherOrganism) {
        if (otherOrganism instanceof Flower && lastVisitedFlower != otherOrganism) {
            ((Flower)otherOrganism).interactWithBee(this);
            lastVisitedFlower = (Flower)otherOrganism;
        } else if (otherOrganism instanceof Bee) {
            ((Bee)otherOrganism).interactWithBee(this);
        }
    }

   public void setMovementPattern(BeeMovementPattern movementPattern) {
        this.movementPattern = movementPattern;
   }

   public BeeMovementPattern getMovementPattern() {
        return movementPattern;
   }

   public int getMoveDistance() {
        return moveDistance;
   }
}
