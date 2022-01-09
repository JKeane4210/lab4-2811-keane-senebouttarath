package bee_simulator;

public class Lily extends Flower {
    private static final int LILY_INITIAL_HEALTH = 80;
    private static final int LILY_MAX_HEALTH = 120;
    private static final int LILY_COLLISION_RADIUS = 12;
    private static final int BEE_HEALTH_GAIN = 50;
    private static final String LILY_DESCRIPTION = "Pleasant flower. Gives health to bees that visit it.";

    public Lily(int centerX, int centerY, boolean shouldDrawEnergy) {
        super(centerX, centerY, LILY_COLLISION_RADIUS, LILY_MAX_HEALTH, LILY_INITIAL_HEALTH, "Assets/lily.png", LILY_DESCRIPTION, shouldDrawEnergy);
    }

    public Lily(int centerX, int centerY) {
        this(centerX, centerY, true);
    }

    @Override
    public void interactWithBee(Bee bee) {
        bee.increaseEnergy(BEE_HEALTH_GAIN);
    }
}
