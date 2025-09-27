package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.JLabel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TestWindowController {
    @FXML private TextField latexField;
    @FXML private Label outputLabel;
    @FXML private Label statusLabel;

    // Adjust as you like
    private static final float BASE_SIZE = 22f;
    private static final boolean ANTIALIAS = true;

    @FXML
    private void initialize() {
        latexField.setText("\\int_0^{\\pi} \\sin x\\, dx = 2");
        render();
    }

    @FXML
    private void render() {
        String src = latexField.getText();
        if (src == null || src.isBlank()) {
            outputLabel.setGraphic(null);
            outputLabel.setText("(nothing to render)");
            statusLabel.setText("");
            return;
        }

        try {
            // Parse the LaTeX and build an icon
            TeXFormula formula = new TeXFormula(src);
            TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, BASE_SIZE);
            icon.setInsets(new Insets(4, 4, 4, 4));

            // Prepare an ARGB image and paint the icon on it
            BufferedImage img = new BufferedImage(
                    icon.getIconWidth(),
                    icon.getIconHeight(),
                    BufferedImage.TYPE_INT_ARGB
            );
            Graphics2D g2 = img.createGraphics();
            try {
                if (ANTIALIAS) {
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                }
                g2.setColor(new Color(0, 0, 0, 0)); // transparent background
                g2.fillRect(0, 0, img.getWidth(), img.getHeight());
                icon.paintIcon(new JLabel(), g2, 0, 0);
            } finally {
                g2.dispose();
            }

            // Convert to JavaFX Image and show in the Label
            Image fxImage = SwingFXUtils.toFXImage(img, null);
            ImageView view = new ImageView(fxImage);
            view.setPreserveRatio(true);
            view.setSmooth(true);

            outputLabel.setText("");           // clear any previous text
            outputLabel.setGraphic(view);      // put the rendered image into the label
            statusLabel.setText("OK");
        } catch (Exception ex) {
            outputLabel.setGraphic(null);
            outputLabel.setText("Parse/render error.");
            statusLabel.setText(ex.getMessage() != null ? ex.getMessage() : "Error");
        }
    }
}
