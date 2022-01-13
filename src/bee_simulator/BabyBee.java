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

    /**
     * Amount of energy lost when bees collide
     */
    private static final int BEE_COLLISION_ENERGY_LOSS = 10;
    private static final int COLLISION_RADIUS = 12;         //24 pixels thick
    private static final int MAX_ENERGY = 200;              //max energy. Once it, the bee grows up!
    private static final int INIT_ENERGY = 130;             //Starting energy of the baby bee
    private static final int MOVE_DISTANCE = 30;            //The baby bee moves faster!
    private static final String IMG = "./Assets/babybee.png";       //The img source of the baby bee
    private static final String DESC = "A bee with a small amount of fast, but is very fast. If it reaches full health, it becomes a big bee.";     //Description

    //---------------- ATTRIBUTES ----------------\\

    /**
     * Determines whether the baby bee is grown up
     */
    private boolean canGrowUp = false;

    //---------------- METHODS ----------------\\

    /**
     * Creates a new baby bee with a given starting position, but also indicates whether the health bar should be drawn
     *
     * @param startX The starting x-position of the big bee
     * @param startY The starting y-position of the big bee
     */
    public BabyBee(int startX, int startY) {
        super(startX, startY, COLLISION_RADIUS, MAX_ENERGY, INIT_ENERGY, IMG, DESC, MOVE_DISTANCE, BEE_COLLISION_ENERGY_LOSS);
    }

    /**
     * Creates a new baby bee with a given starting position, but also indicates whether the health bar should be drawn
     *
     * @param startX The starting x-position of the big bee
     * @param startY The starting y-position of the big bee
     * @param shouldDrawEnergy If the health bar should show
     */
    public BabyBee(int startX, int startY, boolean shouldDrawEnergy) {
        super(startX, startY, COLLISION_RADIUS, MAX_ENERGY, INIT_ENERGY, IMG, DESC, MOVE_DISTANCE, BEE_COLLISION_ENERGY_LOSS, shouldDrawEnergy);
    }

    /**
     * Grows up the baby bee into a newly created big bee and carries over the movement pattern
     * Returns the BigBee that can replace the BabyBee
     *
     * @return The BigBee object that is a "grown up" version of the baby bee
     */
    public BigBee growUp() {
        BigBee bigBee = new BigBee(this);
        movementPattern.setBee(bigBee);
        return bigBee;
    }

    /**
     * The update method for a baby bee. Updates the can grow up potential
     */
    public void update() {
        if (energy >= maxEnergy) {
            canGrowUp = true;
        }
        super.update();
    }

    /**
     * Returns whether the baby bee is grown up.
     * @return If the bee is grown up or not
     */
    public boolean canGrowUp() {
        return canGrowUp;
    }

}   //end class BabyBee
