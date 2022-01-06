package bee_simulator;

public class Rose extends Flower {
    public Rose(int centerX, int centerY, int collisionRadius, int maxEnergy, int energy, String description) {
        super(centerX, centerY, collisionRadius, maxEnergy, energy, "Assets/rose.png", description);
    }

    @Override
    public void collide(Organism otherOrganism) {

    }
}
