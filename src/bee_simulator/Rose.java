package bee_simulator;

public class Rose extends Flower {
    private static final int ROSE_INITIAL_HEALTH = 100;
    private static final int ROSE_MAX_HEALTH = 150;
    private static final int ROSE_COLLISION_RADIUS = 15;
    private static final int BEE_HEALTH_LOSS = 30;
    private static final int ROSE_HEALTH_LOSS = 5;
    private static final String ROSE_DESCRIPTION = "Thorny flower. Damages bees that visit it.";

    public Rose(int centerX, int centerY, boolean shouldDrawEnergy) {
        super(centerX, centerY, ROSE_COLLISION_RADIUS, ROSE_MAX_HEALTH, ROSE_INITIAL_HEALTH, "Assets/rose.png", ROSE_DESCRIPTION, shouldDrawEnergy);
    }

    public Rose(int centerX, int centerY) {
        this(centerX, centerY, true);
    }

    @Override
    public void interactWithBee(Bee bee) {
        bee.decreaseEnergy(BEE_HEALTH_LOSS);
        decreaseEnergy(ROSE_HEALTH_LOSS);
    }
}
