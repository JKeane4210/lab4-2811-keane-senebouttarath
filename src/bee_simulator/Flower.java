/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Lab 4: Bees
 * Author:     Jonathan Keane & Kyle Senebouttarath
 * Date:       1/6/22
 */
package bee_simulator;

/**
 * A flower is responsible for representing a flower within the garden and interacting with bees that visit them.
 * Flowers are stationary organisms that attract bees. They either give bees’ health when
 * interacted with or drain bees’ energy if it’s a bad flower. Flowers only have a limited
 * amount of pollen (their "energy") that slowly recharges over time. Flowers cannot die.
 *
 * @author Jonathan Keane
 */
public abstract class Flower extends Organism {
    
    public Flower(int centerX, int centerY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String description, boolean shouldDrawEnergy) {
        super(centerX, centerY, collisionRadius, maxEnergy, energy, imgUrl, description, shouldDrawEnergy);
    }

    public Flower(int centerX, int centerY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String description) {
        this(centerX, centerY, collisionRadius, maxEnergy, energy, imgUrl, description, true);
    }

    @Override
    public void collide(Organism otherOrganism) {
        if (otherOrganism instanceof Bee) {
            interactWithBee((Bee)otherOrganism);
        }
    }

    public abstract void interactWithBee(Bee bee);
}
