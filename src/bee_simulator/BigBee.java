package bee_simulator;

public class BigBee extends Bee {

    //---------------- CONSTANTS ----------------\\

    private static final int COLLISION_RADIUS = 20;
    private static final int MAX_ENERGY = 300;
    private static final int INIT_ENERGY = 200;
    private static final int MOVE_DISTANCE = 10;
    private static final String IMG = "./Assets/bee.png";
    private static final String DESC = "A grown up bee with more health, but it is slower than the baby bee.";

    //---------------- ATTRIBUTES ----------------\\


    //---------------- METHODS ----------------\\

    public BigBee(int startX, int startY) {
        super(startX, startY, COLLISION_RADIUS, MAX_ENERGY, INIT_ENERGY, IMG, DESC, MOVE_DISTANCE);
    }

    public BigBee(int startX, int startY, boolean shouldDrawEnergy) {
        super(startX, startY, COLLISION_RADIUS, MAX_ENERGY, INIT_ENERGY, IMG, DESC, MOVE_DISTANCE, shouldDrawEnergy);
    }

    public void draw() {
        super.draw();
    }

    public void collide(Organism otherOrganism) {
        super.collide(otherOrganism);
    }
}
