/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Lab 4: Bees
 * Author:     Jonathan Keane & Kyle Senebouttarath
 * Date:       1/6/22
 */
package bee_simulator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;


/**
 * The NatureBook class has descriptions and pictures of all the natural things that can be found within
 * the garden! This includes all flowers and bees and their descriptive information.
 *
 * @author Jonathan Keane
 */
public class NatureBook {
    private final VBox mainVBox;

    public NatureBook(VBox mainVBox) {
        this.mainVBox = mainVBox;
    }

    private void addOrganismInfo(Organism organism, String name, String ... extraFields) {
        VBox organismVBox = new VBox();
        organismVBox.setAlignment(Pos.TOP_CENTER);
        // PICTURE & NAME BOX
        HBox nameAndPictureHBox = new HBox();
        nameAndPictureHBox.setAlignment(Pos.CENTER);
        nameAndPictureHBox.setSpacing(20);
        nameAndPictureHBox.setPadding(new Insets(0, 0, 5, 0));
        // NAME
        Label nameLabel = new Label(name);
        nameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        // PICTURE
        VBox imageBox = new VBox();
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setStyle("-fx-background-color: green; -fx-border-color: black; -fx-border-width: 2");
        imageBox.setPadding(new Insets(2, 5, 5, 5));
        imageBox.getChildren().add(organism.getOrganismContainer());
        nameAndPictureHBox.getChildren().addAll(imageBox, nameLabel);
        organismVBox.getChildren().add(nameAndPictureHBox);
        // DESCRIPTION & OTHER INFO
        Label descriptionLabel = new Label(organism.getDescription());
        descriptionLabel.setWrapText(true);
        descriptionLabel.setTextAlignment(TextAlignment.CENTER);
        organismVBox.getChildren().add(descriptionLabel);
        organismVBox.getChildren().add(new Label("Max Health: " + organism.getMaxEnergy()));
        organismVBox.getChildren().add(new Label("Size: " + organism.getCollisionRadius() * 2));
        for (String extraField: extraFields) {
            organismVBox.getChildren().add(new Label(extraField));
        }
        mainVBox.getChildren().add(organismVBox);
    }

    public void draw() {
        Bee natureBookBigBee = new BigBee(0, 0, false);
        addOrganismInfo(natureBookBigBee, "Big Bee", "Speed: " + natureBookBigBee.getMoveDistance());

        Bee natureBookBabyBee = new BabyBee(0, 0, false);
        addOrganismInfo(natureBookBabyBee, "Baby Bee",
                "Speed: " + natureBookBabyBee.getMoveDistance());

        Organism natureBookRose = new Rose(0, 0, false);
        addOrganismInfo(natureBookRose, "Rose",
                "Damage to Bees: " + Rose.BEE_HEALTH_LOSS,
                "Rose Damage from Bees: "  + Rose.ROSE_HEALTH_LOSS);

        Organism natureBookLily = new Lily(0, 0, false);
        addOrganismInfo(natureBookLily, "Lily",
                "Health Given to Bees: " + Lily.BEE_HEALTH_GAIN,
                "Lily Damage from Bees: " + Lily.LILY_HEALTH_LOSS);
    }
}
