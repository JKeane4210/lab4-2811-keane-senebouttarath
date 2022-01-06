package bee_simulator;

public abstract class Flower extends Organism {
    public Flower(int centerX, int centerY, int collisionRadius, int maxEnergy, int energy, String imgUrl, String description) {
        super(centerX, centerY, collisionRadius, maxEnergy, energy, imgUrl, description);
    }

    public void draw() {
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
    }

    public void interactWithBee(Bee bee) {

    }
}
