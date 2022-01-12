/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Lab 4: Bees
 * Author:     Jonathan Keane & Kyle Senebouttarath
 * Date:       1/6/22
 */
package bee_simulator;

/**
 * The BigBee is a type of bee that is a large bee. Because of its size, it will be more likely to run
 * into other organisms. However, it has more energy. It has the same responsibilities of a normal bee,
 * such as handling movement and dying, but it is has set properties to make it larger and slower.
 *
 * @author Kyle Senebouttarath
 */
public class BigBee extends Bee {

    //---------------- CONSTANTS ----------------\\

    private static final int COLLISION_RADIUS = 25;
    private static final int MAX_ENERGY = 300;
    private static final int INIT_ENERGY = 200;
    private static final int MOVE_DISTANCE = 15;
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

    public BigBee(BabyBee babyBee) {
        super(babyBee.getCenterX(), babyBee.getCenterY(), COLLISION_RADIUS, MAX_ENERGY, INIT_ENERGY, IMG, DESC, MOVE_DISTANCE);
        this.targetX = babyBee.targetX;
        this.targetY = babyBee.targetY;
        this.movementPattern = babyBee.getMovementPattern();
    }

    public void draw() {
        super.draw();
    }

    public void collide(Organism otherOrganism) {
        super.collide(otherOrganism);
    }
}
