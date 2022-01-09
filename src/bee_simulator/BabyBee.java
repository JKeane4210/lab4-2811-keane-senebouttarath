package bee_simulator;

public class BabyBee extends Bee {

    //---------------- CONSTANTS ----------------\\

    private static final int COLLISION_RADIUS = 10;
    private static final int MAX_ENERGY = 200;
    private static final int INIT_ENERGY = 130;
    private static final int MOVE_DISTANCE = 30;
    private static final String IMG = "./Assets/bee.png";
    private static final String DESC = "A bee with a small amount of fast, but is very fast. If it reaches full health, it becomes a big bee.";

    //---------------- METHODS ----------------\\

    public BabyBee(int startX, int startY) {
        super(startX, startY, COLLISION_RADIUS, MAX_ENERGY, INIT_ENERGY, IMG, DESC, MOVE_DISTANCE);
    }

    public BabyBee(int startX, int startY, boolean shouldDrawEnergy) {
        super(startX, startY, COLLISION_RADIUS, MAX_ENERGY, INIT_ENERGY, IMG, DESC, MOVE_DISTANCE, shouldDrawEnergy);
    }

    public void draw() {
        super.draw();
    }

    public void update() {
        super.update();

        if (energy >= maxEnergy) {
            growUp();
        }
    }

    public void collide(Organism otherOrganism) {
        super.collide(otherOrganism);
    }

    public void growUp() {
        //TODO: Make the baby bee grow up (it needs to delete itself and create a new big bee in the garden)
    }
}
