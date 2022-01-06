package bee_simulator;

public class Lily extends Flower {
    public Lily(int centerX, int centerY, int collisionRadius, int maxEnergy, int energy, String description) {
        super(centerX, centerY, collisionRadius, maxEnergy, energy, "Assets/lily.png", description);
    }

    @Override
    public void collide(Organism otherOrganism) {

    }
}
