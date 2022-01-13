/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Lab 4: Bees
 * Author:     Jonathan Keane & Kyle Senebouttarath
 * Date:       1/6/22
 */
package bee_simulator;

/**
 * A movement pattern where the bee will move in a circular pattern that has its radius
 * increase to the maximum radius that the garden can hold and then decrease back down
 * to a radius of 0 continuously.
 *
 * @author Jonathan Keane
 */
public class CircularMovement extends BeeMovementPattern {
    private static final int DELTA_RADIUS = 5;
    private int targetRadius;
    private double targetAngle;
    private boolean isIncreasing;

    public CircularMovement(Bee bee) {
        super(bee);
        targetRadius = Math.abs(bee.getCenterX() - Garden.GARDEN_WIDTH / 2);
        targetAngle = Math.atan2(bee.getCenterY() - (double)Garden.GARDEN_HEIGHT / 2,
                bee.getCenterX() - (double)Garden.GARDEN_WIDTH / 2);
        isIncreasing = true;
    }

    /**
     * Returns whether the bee has achieved the destination it's seeking
     * @return True if target achieved, false otherwise
     */
    @Override
    public boolean targetAchieved() {
        return bee.euclideanDistance(bee.centerX, bee.centerY, bee.targetX, bee.targetY) <= (double)bee.moveDistance;
    }

    /**
     * Retargets a bee to the next target radius and angle that it should seek
     */
    @Override
    public void retarget() {
        if (isIncreasing) {
            targetRadius += DELTA_RADIUS;
            if (targetRadius > Garden.GARDEN_HEIGHT / 2) {
                targetRadius -= DELTA_RADIUS;
                isIncreasing = false;
            }
        } else {
            targetRadius -= DELTA_RADIUS;
            if (targetRadius < 1) {
                targetRadius += DELTA_RADIUS;
                isIncreasing = true;
            }
        }
        targetAngle = (targetAngle + 20) % 360;
        bee.targetX = Garden.GARDEN_WIDTH / 2 + (int)(targetRadius * Math.cos(Math.toRadians(targetAngle)));
        bee.targetY = Garden.GARDEN_HEIGHT / 2 + (int)(targetRadius * Math.sin(Math.toRadians(targetAngle)));
    }
}
