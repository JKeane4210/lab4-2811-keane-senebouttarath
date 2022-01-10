package bee_simulator;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Garden {
    public static final int GARDEN_WIDTH = 600;
    public static final int GARDEN_HEIGHT = 600;
    public static final int GARDEN_X_PADDING = 50;
    public static final int GARDEN_Y_PADDING = 50;
    private static final int INITIAL_FLOWER_COUNT = 20;
    private static final int INITIAL_BEE_COUNT = 10;

    List<Flower> flowers = new ArrayList<>();
    List<Bee> bees = new ArrayList<>();

    @FXML
    private Pane theGarden;
    @FXML
    private VBox natureBookVBox;

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
            int movementType = (int)(Math.random() * 2);
            Bee newBee = i % 2 == 0 ? new BabyBee(x, y) : new BigBee(x, y);
            BeeMovementPattern movementPattern = movementType % 2 == 0 ?
                                                    new FlowerOrientedMovement(newBee, this) :
                                                    new RandomLineMovement(newBee);
            newBee.setMovementPattern(movementPattern);
            bees.add(newBee);
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
        theGarden.setFocusTraversable(true); // ensure garden pane will receive keypresses

        initializeFlowers();
        initializeBees();
        NatureBook natureBook = new NatureBook(natureBookVBox);
        natureBook.draw();
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        update();
    }

    public void initializeBees() {
        for (Bee bee: bees) {
            bee.addToGarden(theGarden);
            bee.setTarget();
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
        bees.forEach(Bee::update);
//        bees.stream().filter(bee::isAlive).forEach(bee -> {
//            bees.stream().filter(otherBee -> bee != otherBee).filter(bee::isCollided).forEach(bee::collide);
//        });
        bees.stream().filter(Bee::isAlive).forEach(bee ->
                flowers.stream().filter(bee::isCollided).forEach(bee::collide));
        flowers.forEach(Flower::update);
        draw();
    }

    public void draw() {
        bees.forEach(Bee::draw);
        flowers.forEach(Flower::draw);
    }

    public List<Flower> getFlowers() {
        return flowers;
    }
}

