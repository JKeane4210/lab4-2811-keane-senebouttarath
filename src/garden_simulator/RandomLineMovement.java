/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Lab 4: Bees
 * Author:     Jonathan Keane & Kyle Senebouttarath
 * Date:       1/6/22
 */
package garden_simulator;

/**
 * Bee movement pattern that has a bee fly towards a random coordinate in
 * the garden and then keep selecting random destinations forever.
 *
 * @author Jonathan Keane
 */
public class RandomLineMovement extends BeeMovementPattern {
    public RandomLineMovement(Bee bee) {
        super(bee);
    }

    /**
     * Checks to see if the bee has reached its destination coordinate.
     * @return True if within a movement "tick" range of the destination, false otherwise.
     */
    @Override
    public boolean targetAchieved() {
        return bee.euclideanDistance(bee.centerX, bee.centerY, bee.targetX, bee.targetY) <= bee.moveDistance;
    }

    /**
     * Retargets the bee to a random coordinate.
     */
    @Override
    public void retarget() {
        bee.targetX = (int)(Math.random() * (Garden.GARDEN_WIDTH));
        bee.targetY = (int)(Math.random() * (Garden.GARDEN_HEIGHT));
    }
}
