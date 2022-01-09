package bee_simulator;

public class BabyBee extends Bee {

    //---------------- CONSTANTS ----------------\\

    private static final int COLLISION_RADIUS = 10;
    private static final int MAX_ENERGY = 200;
    private static final int INIT_ENERGY = 130;
    private static final int MOVE_DISTANCE = 30;
    private static final String IMG = "./Assets/bee.png";
    private static final String DESC = "EPIC!";

    //---------------- METHODS ----------------\\

    public BabyBee(int startX, int startY) {
        super(startX, startY, COLLISION_RADIUS, MAX_ENERGY, INIT_ENERGY, IMG, DESC, MOVE_DISTANCE);
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
