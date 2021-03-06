/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Lab 4: Bees
 * Author:     Jonathan Keane & Kyle Senebouttarath
 * Date:       1/6/22
 */
package garden_simulator;

/**
 * A Lily is a type of flower that is responsible for giving energy to a bee with it collides with one.
 * If the Lily has enough energy, it will give the bee energy and will lose energy in doing so.
 * It regenerates pollen (it's "energy") over time.
 *
 * @author Jonathan Keane
 */
public class Lily extends Flower {
    private static final int LILY_INITIAL_HEALTH = 80;
    private static final int LILY_MAX_HEALTH = 120;
    private static final int LILY_COLLISION_RADIUS = 18;
    public static final int BEE_HEALTH_GAIN = 40;
    public static final int LILY_HEALTH_LOSS = 50;
    private static final String LILY_DESCRIPTION = "Pleasant flower. Gives health to bees that visit it if it has enough energy to do so.";
    private static final int LILY_HEALTH_REGEN = 2;

    public Lily(int centerX, int centerY, boolean shouldDrawEnergy) {
        super(centerX, centerY, LILY_COLLISION_RADIUS, LILY_MAX_HEALTH, LILY_INITIAL_HEALTH, "assets/lily.png", LILY_DESCRIPTION, shouldDrawEnergy);
    }

    public Lily(int centerX, int centerY) {
        this(centerX, centerY, true);
    }

    /**
     * Increase the lily health by a "tick's" worth of energy
     */
    public void update() {
        increaseEnergy(LILY_HEALTH_REGEN);
    }

    /**
     * Increases the bee energy and decrease the lily's energy if enough energy to do so
     * @param bee The bee that collided with the flower
     */
    @Override
    public void interactWithBee(Bee bee) {
        if (energy >= LILY_HEALTH_LOSS) {
            bee.increaseEnergy(BEE_HEALTH_GAIN);
            decreaseEnergy(LILY_HEALTH_LOSS);
        }
    }
}
