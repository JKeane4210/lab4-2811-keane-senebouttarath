package bee_simulator;

public abstract class Flower extends Organism {
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
//        beeImageBox.setLayoutX(centerX);
//        beeImageBox.setLayoutY(centerX);
    }

    public void interactWithBee(Bee bee) {

    }
}
