package bee_simulator;

public class BabyBee extends Bee {

    //---------------- CONSTANTS ----------------\\

    private static final int EGG = 10;

    //---------------- METHODS ----------------\\

    public BabyBee(int startX, int startY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String desc, int moveDistance) {
        super(startX, startY, collisionRadius, maxEnergy, energy, imgUrl, desc, moveDistance);
    }

    public void drawBee() {

    }

    public void collide(Organism otherOrgansim) {

    }

    public void growUp() {

    }
}
