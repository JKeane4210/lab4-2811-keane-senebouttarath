package bee_simulator;

public class Lily extends Flower {
    private static final int LILY_INITIAL_HEALTH = 80;
    private static final int LILY_MAX_HEALTH = 120;
    private static final int LILY_COLLISION_RADIUS = 12;
    private static final int BEE_HEALTH_GAIN = 10;

    public Lily(int centerX, int centerY) {
        super(centerX, centerY, LILY_COLLISION_RADIUS, LILY_MAX_HEALTH, LILY_INITIAL_HEALTH, "Assets/lily.png", "DESCRIPTION");
    }

    @Override
    public void interactWithBee(Bee bee) {
        bee.increaseEnergy(BEE_HEALTH_GAIN);
    }
}
