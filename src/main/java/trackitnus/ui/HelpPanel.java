package trackitnus.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import trackitnus.commons.core.LogsCenter;


public class HelpPanel extends UiPart<Region> {
    public static final String TYPE = "H";
    private static final String FXML = "/HelpPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(trackitnus.ui.HelpPanel.class);

    public static final String HELP_INTRO = "Welcome to TrackIT@NUS!";

    @FXML
    private Text intro;

    /**
     * Creates a {@code HelpPanel} with the given {@code ObservableList}.
     */
    public HelpPanel() {
        super(FXML);
        intro.setText(HelpPanel.HELP_INTRO);
    }
}
