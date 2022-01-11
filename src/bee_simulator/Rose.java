/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Lab 4: Bees
 * Author:     Jonathan Keane & Kyle Senebouttarath
 * Date:       1/6/22
 */
package bee_simulator;

/**
 * A type of flower that has the responsibility of taking away the energy from a bee when it collides with one.
 * The Rose will lose energy itself when it drains a bee of its energy. It can only drain energy when it has
 * enough itself. It will drain a set amount of energy only. It regenerates energy over time.
 *
 * @author Jonathan Keane
 */
public class Rose extends Flower {
    private static final int ROSE_INITIAL_HEALTH = 100;
    private static final int ROSE_MAX_HEALTH = 150;
    private static final int ROSE_COLLISION_RADIUS = 20;
    private static final int BEE_HEALTH_LOSS = 15;
    private static final int ROSE_HEALTH_LOSS = 60;
    private static final int ROSE_HEALTH_REGEN = 1;
    private static final String ROSE_DESCRIPTION = "Thorny flower. Damages bees that visit it.";

    public Rose(int centerX, int centerY, boolean shouldDrawEnergy) {
        super(centerX, centerY, ROSE_COLLISION_RADIUS, ROSE_MAX_HEALTH, ROSE_INITIAL_HEALTH, "Assets/rose.png", ROSE_DESCRIPTION, shouldDrawEnergy);
    }

    public Rose(int centerX, int centerY) {
        this(centerX, centerY, true);
    }

    @Override
    public void interactWithBee(Bee bee) {
        if (energy > 0) {
            bee.decreaseEnergy(BEE_HEALTH_LOSS);
            decreaseEnergy(ROSE_HEALTH_LOSS);
        }
    }

    @Override
    public void update() {
        increaseEnergy(ROSE_HEALTH_REGEN);
    }
}
