package bee_simulator;

public class RandomLineMovement extends BeeMovementPattern {
    public RandomLineMovement(Bee bee) {
        super(bee);
    }

    @Override
    public boolean targetAchieved() {
        return bee.euclideanDistance(bee.centerX, bee.centerY, bee.targetX, bee.targetY) <= bee.moveDistance;
    }

    @Override
    public void retarget() {
        bee.targetX = (int)(Math.random() * (Garden.GARDEN_WIDTH));
        bee.targetY = (int)(Math.random() * (Garden.GARDEN_HEIGHT));
    }
}
