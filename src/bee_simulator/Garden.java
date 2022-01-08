package bee_simulator;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Garden {
    public static final int GARDEN_WIDTH = 700;
    public static final int GARDEN_HEIGHT = 700;
    private static final int INITIAL_FLOWER_COUNT = 20;
    private static final int INITIAL_BEE_COUNT = 20;

    private Pane beeImageBox;               // box containing bee and it's label; NOT a good domain name!
    private double beeXLocation, beeYLocation;  // drawn location of bee; this should be in a domain class
    List<Flower> flowers = new ArrayList<>();
    List<Bee> bees = new ArrayList<>();

    @FXML
    private Pane theGarden;                 // capture the pane we are drawing on from JavaFX
    @FXML
    private Pane natureBookPane;

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
                bees.add(new BabyBee(x, y));
            } else {
                bees.add(new BigBee(x, y));
            }
        }
    }

    @FXML
    public void initialize() {              // executed after scene is loaded but before any methods
        // this is already set in FXML
//        theGarden.setPrefWidth(GARDEN_WIDTH);
//        theGarden.setPrefHeight(GARDEN_HEIGHT);
//        theGarden.setMaxWidth(GARDEN_WIDTH);
//        theGarden.setMaxHeight(GARDEN_HEIGHT);
//        theGarden.setMinWidth(GARDEN_WIDTH);
//        theGarden.setMinHeight(GARDEN_HEIGHT);
        // for fun, set up a gradient background; could probably do in SceneBuilder as well
        // note the label has a Z index of 2 so it is drawn above the panel, otherwise it may be displayed "under" the panel and not be visible
        theGarden.setStyle("-fx-background-color: linear-gradient(to bottom right, derive(forestgreen, 20%), derive(forestgreen, -40%));");
        // load image from a file; the file needs to be in the top folder of the project

        /*
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
         */

        theGarden.setFocusTraversable(true); // ensure garden pane will receive keypresses

        initializeFlowers();
        initializeBees();
    }

    // display the bee at the (beeXLocation, beeYLocation), ensuring the bee does not leave the garden
    private void displayBee() {
        /*
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

         */
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        update();
    }

    public void initializeBees() {
        for (Bee bee: bees) {
            bee.addToGarden(theGarden);

            bee.visitFlower(flowers.get((int) Math.floor(Math.random() * flowers.size())));

            bee.update();
            bee.draw();
        }
    }

    public void initializeFlowers() {
        for (Flower flower: flowers) {
            flower.addToGarden(theGarden);
            flower.update();
            flower.draw();
        }
    }

    public void update() {
        for (Bee bee: bees) {
            bee.update();

            if (!bee.isAlive()) {
                //Somehow remove bee from garden
            } else {
                for (Bee otherBee: bees) {
                    if (bee != otherBee) {
                        if (bee.isCollided(otherBee)) {
                            bee.collide(otherBee);
                        }
                    }
                }
                for (Flower flower: flowers) {
                    if (bee.isCollided(flower)) {
                        bee.collide(flower);

                        //TODO: Select a new random flower!
                    }
                }
            }
        }

        for (Flower flower: flowers) {
            flower.update();
        }


        draw();
    }

    public void draw() {
        for (Bee bee: bees) {
            bee.draw();
        }
        for (Flower flower: flowers) {
            flower.draw();
        }
    }
}

