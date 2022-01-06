package bee_simulator;

public class BabyBee extends Bee {

    //---------------- CONSTANTS ----------------\\

    private static final int COLLISION_RADIUS = 10;
    private static final int MAX_ENERGY = 10;
    private static final int INIT_ENERGY = 10;
    private static final int MOVE_DISTANCE = 30;
    private static final String IMG = "./Assets/bee-1.jpg";
    private static final String DESC = "EPIC!";

    //---------------- METHODS ----------------\\

    public BabyBee(int startX, int startY) {
        super(startX, startY, COLLISION_RADIUS, MAX_ENERGY, INIT_ENERGY, IMG, DESC, MOVE_DISTANCE);
    }

    public void drawBee() {
        organismContainer.setLayoutX(centerX + COLLISION_RADIUS);
        organismContainer.setLayoutY(centerY + COLLISION_RADIUS);
    }

    public void collide(Organism otherOrganism) {

    }

    public void growUp() {

    }
}
