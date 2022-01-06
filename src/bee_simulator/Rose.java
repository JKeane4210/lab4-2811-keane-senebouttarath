package bee_simulator;

public class Rose extends Flower {
    private static final int ROSE_INITIAL_HEALTH = 100;
    private static final int ROSE_MAX_HEALTH = 150;
    private static final int ROSE_COLLISION_RADIUS = 15;
    private static final int BEE_HEALTH_LOSS = 5;
    private static final int ROSE_HEALTH_LOSS = 5;

    public Rose(int centerX, int centerY) {
        super(centerX, centerY, ROSE_COLLISION_RADIUS, ROSE_MAX_HEALTH, ROSE_INITIAL_HEALTH, "Assets/rose.png", "DESCRIPTION");
    }

    @Override
    public void interactWithBee(Bee bee) {
        bee.decreaseEnergy(BEE_HEALTH_LOSS);
        decreaseEnergy(ROSE_HEALTH_LOSS);
    }
}
