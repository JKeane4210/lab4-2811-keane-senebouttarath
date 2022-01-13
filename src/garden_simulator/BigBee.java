/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Lab 4: Bees
 * Author:     Jonathan Keane & Kyle Senebouttarath
 * Date:       1/6/22
 */
package garden_simulator;

import javafx.scene.effect.ColorAdjust;

/**
 * The BigBee is a type of bee that is a large bee. Because of its size, it will be more likely to run
 * into other organisms. However, it has more energy. It has the same responsibilities of a normal bee,
 * such as handling movement and dying, but it is has set properties to make it larger and slower. Big
 * bees grow older and slower over time.
 *
 * @author Kyle Senebouttarath
 */
public class BigBee extends Bee {

    //---------------- CONSTANTS ----------------\\

    /**
     * Amount of energy lost when bees collide
     */
    private static final int BEE_COLLISION_ENERGY_LOSS = 15;
    private static final int LIFE_PERIOD_LENGTH = 50;
    private static final int MIN_MOVE_DISTANCE = 10;

    private static final int COLLISION_RADIUS = 25;     //50 pixels thick circle
    private static final int MAX_ENERGY = 300;          //max energy
    private static final int INIT_ENERGY = 200;         //initial energy
    private static final int MOVE_DISTANCE = 20;        //15 pixels per update
    private static final String IMG = "./assets/bee.png";     //Img representation of the bee
    private static final String DESC = "A grown up bee with more health, but it is slower than the baby bee. " +
            "As they grow old, they start to lose speed.";      //Bee desc

    private int lifeTime;
    private int currentLifePeriod;

    //---------------- ATTRIBUTES ----------------\\


    //---------------- METHODS ----------------\\

    /**
     * Creates a new big bee with a given starting position
     *
     * @param startX The starting x-position of the big bee
     * @param startY The starting y-position of the big bee
     */
    public BigBee(int startX, int startY) {
        super(startX, startY, COLLISION_RADIUS, MAX_ENERGY, INIT_ENERGY, IMG, DESC, MOVE_DISTANCE, BEE_COLLISION_ENERGY_LOSS);
    }

    /**
     * Creates a new big bee with a given starting position, but also indicates whether the health bar should be drawn
     *
     * @param startX The starting x-position of the big bee
     * @param startY The starting y-position of the big bee
     * @param shouldDrawEnergy If the health bar should show
     */
    public BigBee(int startX, int startY, boolean shouldDrawEnergy) {
        super(startX, startY, COLLISION_RADIUS, MAX_ENERGY, INIT_ENERGY, IMG, DESC, MOVE_DISTANCE, BEE_COLLISION_ENERGY_LOSS, shouldDrawEnergy);
    }

    /**
     * Creates a new big bee based on a passed in baby bee. This is primarily used for the growing up process.
     * Sets the big bee's position to the baby bee's, and passes the same movement as well
     *
     * @param babyBee The baby bee to copy data from
     */
    public BigBee(BabyBee babyBee) {
        super(babyBee.getCenterX(), babyBee.getCenterY(), COLLISION_RADIUS, MAX_ENERGY, INIT_ENERGY, IMG, DESC, MOVE_DISTANCE);
        this.targetX = babyBee.targetX;
        this.targetY = babyBee.targetY;
        this.movementPattern = babyBee.getMovementPattern();
        this.lifeTime = 0;
        this.currentLifePeriod = 0;
    }

    /**
     * Decreases bee movement speed because it is getting older (capped at a minimum speed)
     */
    public void growOlder() {
        ++currentLifePeriod;
        moveDistance = Math.max(moveDistance - 1, MIN_MOVE_DISTANCE);
        ColorAdjust adj = new ColorAdjust();
        adj.setBrightness(Math.min(currentLifePeriod, 10) * -0.05);
        organismImage.setEffect(adj);
    }

    /**
     * Updates the bee as usual, but if the bee has completed a life period, it grows older
     */
    @Override
    public void update() {
        super.update();
        ++lifeTime;
        if (lifeTime % LIFE_PERIOD_LENGTH == 0) {
            growOlder();
        }
    }
}   //end class BigBee
