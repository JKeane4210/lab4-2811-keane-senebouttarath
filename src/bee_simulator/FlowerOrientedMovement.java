package bee_simulator;

public class FlowerOrientedMovement extends BeeMovementPattern {
    private Flower destinationFlower;
    private final Garden garden;

    public FlowerOrientedMovement(Bee bee, Garden garden) {
        super(bee);
        this.garden = garden;
        this.destinationFlower = null;
    }

    public void setDestinationFlower(Flower destinationFlower) {
        this.destinationFlower = destinationFlower;
        bee.targetX = destinationFlower.centerX;
        bee.targetY = destinationFlower.centerY;
    }

    @Override
    public boolean targetAchieved() {
        return destinationFlower.isCollided(bee);
    }

    @Override
    public void retarget() {
        setDestinationFlower(garden.getFlowers().get(
                (int) Math.floor(Math.random() * garden.getFlowers().size())));
    }
}
