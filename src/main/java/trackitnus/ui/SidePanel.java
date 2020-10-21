package trackitnus.ui;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import trackitnus.commons.core.LogsCenter;

public class SidePanel extends UiPart<Region> {
    private static final String FXML = "SidePanel.fxml";

    private final Logger logger = LogsCenter.getLogger(SidePanel.class);

    private Consumer<String> consumer;
    private final HelpWindow helpWindow;

    /**
     * Constructor for SidePanel
     *
     * @param consumer
     */
    public SidePanel(Consumer<String> consumer) {
        super(FXML);

        this.consumer = consumer;
        helpWindow = new HelpWindow();
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    @FXML
    private void handleSwitchToUpcoming() {
        consumer.accept("U");
    }

    @FXML
    private void handleSwitchToContact() {
        consumer.accept("C");
    }

    @FXML
    private void handleSwitchToModule() {
        consumer.accept("M");
    }

    @FXML
    private void handleSwitchToTask() {
        consumer.accept("T");
    }
}
