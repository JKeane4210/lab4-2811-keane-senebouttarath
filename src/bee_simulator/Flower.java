package bee_simulator;

public abstract class Flower extends Organism {
    
    public Flower(int centerX, int centerY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String description, boolean shouldDrawEnergy) {
        super(centerX, centerY, collisionRadius, maxEnergy, energy, imgUrl, description, shouldDrawEnergy);
    }

    public Flower(int centerX, int centerY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String description) {
        this(centerX, centerY, collisionRadius, maxEnergy, energy, imgUrl, description, true);
    }

    public void draw() {
        super.draw();
        //I commented this out for now so the flowers would draw in the center
        //Some boundary check stuff like this should be implemented in the organism class!
        /*
        if ( centerX < 0 )
            centerX = 0;
        else if (Garden.GARDEN_WIDTH > 0 && centerX > Garden.GARDEN_WIDTH - 10)
            // note: getWidth() is 0 when first load the scene, so don't relocate the bee in that case
            centerX = Garden.GARDEN_WIDTH - 10;
        if (centerY < 0)
            centerY = 0;
        else if (Garden.GARDEN_HEIGHT > 0 && centerY > Garden.GARDEN_HEIGHT - 10)
            centerY = Garden.GARDEN_HEIGHT - 10;
        organismContainer.setLayoutX(centerX);
        organismContainer.setLayoutY(centerY);

         */
    }

    public void update() {

    }

    @Override
    public void collide(Organism otherOrganism) {
        if (otherOrganism instanceof Bee) {
            interactWithBee((Bee)otherOrganism);
        }
    }

    public abstract void interactWithBee(Bee bee);
}
