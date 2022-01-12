/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Lab 4: Bees
 * Author:     Jonathan Keane & Kyle Senebouttarath
 * Date:       1/6/22
 */
package bee_simulator;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * The Garden is responsible for holding a collection of different organisms that exist inside it. These
 * organisms will be either bees or flowers. The size of the garden should not overlap with the NatureBook.
 * When the Garden is initially created, it will have a set number of bees and flowers in it.
 *
 * @author Jonathan Keane & Kyle Senebouttarath
 */
public class Garden {
    public static final int GARDEN_WIDTH = 600;
    public static final int GARDEN_HEIGHT = 550;
    public static final int GARDEN_X_PADDING = 50;
    public static final int GARDEN_Y_PADDING = 50;
    private static final int DEFAULT_BABY_BEES = 6;
    private static final int DEFAULT_BIG_BEES = 6;
    private static final int DEFAULT_ROSES = 4;
    private static final int DEFAULT_LILIES = 8;

    List<Flower> flowers = new ArrayList<>();
    List<Bee> bees = new ArrayList<>();

    @FXML
    private Pane theGarden;
    @FXML
    private VBox natureBookVBox;
    @FXML
    private TextField roseCountField;
    @FXML
    private TextField lilyCountField;
    @FXML
    private TextField babyBeeCountField;
    @FXML
    private TextField bigBeeCountField;

    @FXML
    public void initialize() {              // executed after scene is loaded but before any methods
        // for fun, set up a gradient background; could probably do in SceneBuilder as well
        // note the label has a Z index of 2 so it is drawn above the panel, otherwise it may be displayed "under" the panel and not be visible
        theGarden.setStyle("-fx-background-color: linear-gradient(to bottom right, derive(forestgreen, 20%), derive(forestgreen, -40%));");
        theGarden.setFocusTraversable(true); // ensure garden pane will receive keypresses

        createGarden();
        NatureBook natureBook = new NatureBook(natureBookVBox);
        natureBook.draw();
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        update();
    }

    @FXML
    public void setDefaults() {
        roseCountField.setText("" + DEFAULT_ROSES);
        lilyCountField.setText("" + DEFAULT_LILIES);
        babyBeeCountField.setText("" + DEFAULT_BABY_BEES);
        bigBeeCountField.setText("" + DEFAULT_BIG_BEES);
        createGarden();
    }

    @FXML
    public void createGarden() {
        theGarden.getChildren().clear();
        flowers.clear();
        bees.clear();
        try {
            // ROSES
            for (int i = 0; i < Integer.parseInt(roseCountField.getText()); ++i) {
                int x = (int) (Math.random() * GARDEN_WIDTH);
                int y = (int) (Math.random() * GARDEN_HEIGHT);
                flowers.add(new Rose(x, y));
            }
            // LILIES
            for (int i = 0; i < Integer.parseInt(lilyCountField.getText()); ++i) {
                int x = (int) (Math.random() * GARDEN_WIDTH);
                int y = (int) (Math.random() * GARDEN_HEIGHT);
                flowers.add(new Lily(x, y));
            }
            // BABY BEES
            for (int i = 0; i < Integer.parseInt(babyBeeCountField.getText()); ++i) {
                int x = (int) (Math.random() * GARDEN_WIDTH);
                int y = (int) (Math.random() * GARDEN_HEIGHT);
                int movementType = (int) (Math.random() * 2);
                BabyBee newBee = new BabyBee(x, y);
                BeeMovementPattern movementPattern = movementType % 2 == 0 && flowers.size() > 0 ?
                        new FlowerOrientedMovement(newBee, this) :
                        new RandomLineMovement(newBee);
                newBee.setMovementPattern(movementPattern);
                bees.add(newBee);
            }
            // BIG BEES
            for (int i = 0; i < Integer.parseInt(bigBeeCountField.getText()); ++i) {
                int x = (int) (Math.random() * GARDEN_WIDTH);
                int y = (int) (Math.random() * GARDEN_HEIGHT);
                int movementType = (int) (Math.random() * 2);
                BigBee newBee = new BigBee(x, y);
                BeeMovementPattern movementPattern = movementType % 2 == 0 && flowers.size() > 0 ?
                        new FlowerOrientedMovement(newBee, this) :
                        new RandomLineMovement(newBee);
                newBee.setMovementPattern(movementPattern);
                bees.add(newBee);
            }
            initializeFlowers();
            initializeBees();
        } catch (NumberFormatException e) {
            Alert numberFormatAlert = new Alert(Alert.AlertType.ERROR);
            numberFormatAlert.setContentText("One of the garden creation fields is not an integer. Please try again.");
            numberFormatAlert.showAndWait();
        }
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

        List<Bee> beesToRemove = new ArrayList<Bee>();
        List<Bee> beesToAdd = new ArrayList<Bee>();
        bees.forEach((b) -> {
            if (b instanceof BabyBee && ((BabyBee) b).canGrowUp()) {
                BigBee newBigBee = new BigBee((BabyBee) b);
                b.removeFromGarden(theGarden);
                newBigBee.addToGarden(theGarden);
                beesToAdd.add(newBigBee);
                beesToRemove.add(b);
            }
        });
        bees.removeAll(beesToRemove);
        bees.addAll(beesToAdd);

        bees.forEach(Bee::update);
        bees.removeIf(bee -> !bee.isAlive());
        IntStream.range(0, bees.size()).forEach(index -> {
            Bee bee = bees.get(index);
            bees.stream().skip(index + 1).filter(bee::isCollided).forEach(bee::collide);
        });
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

