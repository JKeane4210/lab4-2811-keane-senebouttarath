package bee_simulator;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * TODO: Delete this class before we submit
 */
public class GardenController {

    private Pane beeImageBox;               // box containing bee and it's label; NOT a good domain name!
    private double beeXLocation, beeYLocation;  // drawn location of bee; this should be in a domain class

    @FXML
    private Pane theGarden;                 // capture the pane we are drawing on from JavaFX

    @FXML
    public void initialize() {              // executed after scene is loaded but before any methods
        // for fun, set up a gradient background; could probably do in SceneBuilder as well
        // note the label has a Z index of 2 so it is drawn above the panel, otherwise it may be displayed "under" the panel and not be visible
        theGarden.setStyle("-fx-background-color: linear-gradient(to bottom right, derive(forestgreen, 20%), derive(forestgreen, -40%));");
        // load image from a file; the file needs to be in the top folder of the project

        /*
        ImageView beeImage = new ImageView(new Image("file:bee-1.jpg")); // draws bee
        beeImage.setPreserveRatio(true);    // ensure ratio preserved when scaling the bee
        beeImage.setFitWidth(50.0);         // scale bee to be a reasonable size
        Label beeLabel = new Label();       // you might make this an attribute of another class so you can update it
        beeLabel.setText("Some Bee");
        beeLabel.setStyle("-fx-text-fill: blue;");
        beeImageBox = new VBox();
        beeImageBox.getChildren().add(beeImage);
        beeImageBox.getChildren().add(beeLabel);
        beeXLocation = 100;                 // initial location of bee; for your solution,
        beeYLocation = 200;                 //     capture this in an object
        theGarden.getChildren().add(beeImageBox); // place bee on the panel
        displayBee();
         */

        theGarden.setFocusTraversable(true); // ensure garden pane will receive keypresses
    }

    // display the bee at the (beeXLocation, beeYLocation), ensuring the bee does not leave the garden
    private void displayBee() {
        if ( beeXLocation < 0 )
            beeXLocation = 0;
        else if (theGarden.getWidth() > 0 && beeXLocation > theGarden.getWidth() - 10)
            // note: getWidth() is 0 when first load the scene, so don't relocate the bee in that case
            beeXLocation = theGarden.getWidth() - 10;
        if (beeYLocation < 0)
            beeYLocation = 0;
        else if (theGarden.getHeight() > 0 && beeYLocation > theGarden.getHeight() - 10)
            beeYLocation = theGarden.getHeight() - 10;
        beeImageBox.setLayoutX(beeXLocation);
        beeImageBox.setLayoutY(beeYLocation);
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            beeXLocation += 10.0;
        } else if (keyEvent.getCode() == KeyCode.LEFT) {
            beeXLocation -= 10.0;
        }
        if (keyEvent.getCode() == KeyCode.DOWN) {
            beeYLocation += 10.0;
        } else if (keyEvent.getCode() == KeyCode.UP) {
            beeYLocation -= 10.0;
        }
        displayBee();
    }
}
