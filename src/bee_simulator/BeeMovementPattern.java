package bee_simulator;

public abstract class BeeMovementPattern {
    protected final Bee bee;

    public BeeMovementPattern(Bee bee) {
        this.bee = bee;
    }

    public void moveBee() {
        int xOffset = bee.targetX - bee.centerX;
        int yOffset = bee.targetY - bee.centerY;

        double denom = Math.abs(xOffset) + Math.abs(yOffset);

        int xMoveDist = (int) ((xOffset / denom) * bee.moveDistance);
        int yMoveDist = (int) ((yOffset / denom) * bee.moveDistance);

        bee.centerX+=xMoveDist;
        bee.centerY+=yMoveDist;
    }

    public abstract boolean targetAchieved();

    public abstract void retarget();
}
