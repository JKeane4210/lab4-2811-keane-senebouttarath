/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Lab 4: Bees
 * Author:     Jonathan Keane & Kyle Senebouttarath
 * Date:       1/6/22
 */
package garden_simulator;

/**
 * An abstract class representing the movement pattern for a bee to follow
 *
 * @author Jonathan Keane (Kyle Senebouttarath created moveBee method)
 */
public abstract class BeeMovementPattern {
    protected Bee bee;

    public BeeMovementPattern(Bee bee) {
        this.bee = bee;
    }

    /**
     * Moves a bee in the direction of it's target at its current speed
     */
    public void moveBee() {
        int xOffset = bee.targetX - bee.centerX;
        int yOffset = bee.targetY - bee.centerY;

        double denom = Math.abs(xOffset) + Math.abs(yOffset);

        int xMoveDist = (int) ((xOffset / denom) * bee.moveDistance);
        int yMoveDist = (int) ((yOffset / denom) * bee.moveDistance);

        bee.centerX += xMoveDist;
        bee.centerY += yMoveDist;
    }

    /**
     * Returns whether the bee has achieved the destination it's seeking
     * @return True if target achieved, false otherwise
     */
    public abstract boolean targetAchieved();

    /**
     * Updates the target of the Bee to a new target
     */
    public abstract void retarget();

    public void setBee(Bee bee) {
        this.bee = bee;
    }
}
