package bee_simulator;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Garden {

    public static final int GARDEN_WIDTH = 900;
    public static final int GARDEN_HEIGHT = 700;
    private static final int INITIAL_FLOWER_COUNT = 20;
    private static final int INITIAL_BEE_COUNT = 20;

    private Pane beeImageBox;               // box containing bee and it's label; NOT a good domain name!
    private double beeXLocation, beeYLocation;  // drawn location of bee; this should be in a domain class
    List<Flower> flowers = new ArrayList<>();
    List<Bee> bees = new ArrayList<>();

    public Garden() {
        for (int i = 0; i < INITIAL_FLOWER_COUNT; ++i) {
            int x = (int)(Math.random() * GARDEN_WIDTH);
            int y = (int)(Math.random() * GARDEN_HEIGHT);
            if (i % 2 == 0) {
                flowers.add(new Rose(x, y));
            } else {
                flowers.add(new Lily(x, y));
            }
        }
        for (int i = 0; i < INITIAL_BEE_COUNT; ++i) {
            int x = (int)(Math.random() * GARDEN_WIDTH);
            int y = (int)(Math.random() * GARDEN_HEIGHT);
            if (i % 2 == 0) {
                bees.add(new BabyBee(x, y, 10, 10, 10, "Assets/bee-1.png", "", 0));
            } else {
                bees.add(new BigBee(x, y, 15, 10, 10, "Assets/bee-1.png", "", 0));
            }
        }
    }

    @FXML
    private Pane theGarden;                 // capture the pane we are drawing on from JavaFX

    @FXML
    public void initialize() {              // executed after scene is loaded but before any methods
        theGarden.setPrefWidth(GARDEN_WIDTH);
        theGarden.setPrefHeight(GARDEN_HEIGHT);
        theGarden.setMaxWidth(GARDEN_WIDTH);
        theGarden.setMaxHeight(GARDEN_HEIGHT);
        theGarden.setMinWidth(GARDEN_WIDTH);
        theGarden.setMinHeight(GARDEN_HEIGHT);
        // for fun, set up a gradient background; could probably do in SceneBuilder as well
        // note the label has a Z index of 2 so it is drawn above the panel, otherwise it may be displayed "under" the panel and not be visible
        theGarden.setStyle("-fx-background-color: linear-gradient(to bottom right, derive(forestgreen, 20%), derive(forestgreen, -40%));");
        // load image from a file; the file needs to be in the top folder of the project
        ImageView beeImage = new ImageView(new Image("file:Assets/bee-1.jpg")); // draws bee
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
        theGarden.setFocusTraversable(true); // ensure garden pane will receive keypresses
        initializeBees();
        initializeFlowers();
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

    public void initializeBees() {
        for (Bee bee: bees) {
            bee.addToGarden(theGarden);
            bee.drawBee();
        }
    }

    public void initializeFlowers() {
        for (Flower flower: flowers) {
            flower.addToGarden(theGarden);
            flower.draw();
        }
    }

    public void update() {

    }

    public void draw() {

    }
}

