/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Lab 4: Bees
 * Author:     Jonathan Keane & Kyle Senebouttarath
 * Date:       1/6/22
 */
package bee_simulator;

/**
 * The BabyBee is a type of bee that is a small bee. It has a small amount of energy, but it’s faster and has a
 * smaller collision area. It has the same responsibilities as a bee, but it has defined properties and the
 * additional responsibility of handling the bee's ability to grow up if it is reaches maximum health.
 *
 * @author Kyle Senebouttarath
 */
public class BabyBee extends Bee {

    //---------------- CONSTANTS ----------------\\

    private static final int COLLISION_RADIUS = 12;
    private static final int MAX_ENERGY = 200;
    private static final int INIT_ENERGY = 130;
    private static final int MOVE_DISTANCE = 30;
    private static final String IMG = "./Assets/babybee.png";
    private static final String DESC = "A bee with a small amount of fast, but is very fast. If it reaches full health, it becomes a big bee.";

    //---------------- ATTRIBUTES ----------------\\

    private boolean isGrownUp = false;

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
        if (energy >= maxEnergy) {
            isGrownUp = true;
        }
        super.update();
    }

    public void collide(Organism otherOrganism) {
        super.collide(otherOrganism);
    }

    public BigBee growUp() {
        BigBee bigBee = new BigBee(this);
        movementPattern.setBee(bigBee);
        return bigBee;
    }

    public boolean isGrownUp() {
        return isGrownUp;
    }
}
