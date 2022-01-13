/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Lab 4: Bees
 * Author:     Jonathan Keane & Kyle Senebouttarath
 * Date:       1/6/22
 */
package garden_simulator;

/**
 * Bee movement pattern that has a bee fly towards a random flower
 * until it hits it, then retargets to another flower.
 *
 * @author Jonathan Keane
 */
public class FlowerOrientedMovement extends BeeMovementPattern {
    private Flower destinationFlower;
    private final Garden garden;

    public FlowerOrientedMovement(Bee bee, Garden garden) {
        super(bee);
        this.garden = garden;
        this.destinationFlower = null;
    }

    /**
     * Sets the Bee's target to be a new flower
     * @param destinationFlower The new flower for the bee to target
     */
    public void setDestinationFlower(Flower destinationFlower) {
        this.destinationFlower = destinationFlower;
        bee.targetX = destinationFlower.centerX;
        bee.targetY = destinationFlower.centerY;
    }

    /**
     * Checks to see if bee has collided with destination flower
     * @return True if has collided with flower, false otherwise
     */
    @Override
    public boolean targetAchieved() {
        if (destinationFlower == null) {
            retarget();
            bee.turnTowardsTarget();
        }
        return destinationFlower.isCollided(bee);
    }

    /**
     * Changes bee's target to a random flower
     */
    @Override
    public void retarget() {
        setDestinationFlower(garden.getFlowers().get(
                (int) Math.floor(Math.random() * garden.getFlowers().size())));
    }
}
