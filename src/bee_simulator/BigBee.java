package bee_simulator;

public class BigBee extends Bee {

    //---------------- CONSTANTS ----------------\\

    private static final int COLLISION_RADIUS = 30;
    private static final int MAX_ENERGY = 20;
    private static final int INIT_ENERGY = 20;
    private static final int MOVE_DISTANCE = 10;
    private static final String IMG = "./Assets/bee-1.jpg";
    private static final String DESC = "BIG BOY";

    //---------------- METHODS ----------------\\

    public BigBee(int startX, int startY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String desc, int moveDistance) {
        super(startX, startY, COLLISION_RADIUS, MAX_ENERGY, INIT_ENERGY, IMG, DESC, MOVE_DISTANCE);
    }

    public void drawBee() {
        organismContainer.setLayoutX(centerX + COLLISION_RADIUS);
        organismContainer.setLayoutY(centerY + COLLISION_RADIUS);
    }

    public void collide(Organism otherOrganism) {

    }
}
