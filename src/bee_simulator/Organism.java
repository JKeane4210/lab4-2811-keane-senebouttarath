package bee_simulator;

public abstract class Organism {

    protected int centerX;
    protected int centerY;

    protected int collisionRadius;
    protected int maxHealth;

    protected String description;
    protected int energyLevel;

    public boolean isCollided(Organism otherOrganism) {
        return false;
    }

    public abstract void collide(Organism otherOrganism);

    public void drawHealthBar() {

    }

    public void increaseEnergy(int amt) {

    }

    public void decreaseEnergy(int amt) {

    }

    private void setEnergy(int amt) {

    }

    public String getDescription() {
        return "";
    }

    private double euclideanDistance(int x1, int y1, int x2, int y2) {
        return 0.0;
    }
}
