package trackitnus.ui.module;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import trackitnus.commons.core.LogsCenter;
import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.module.Module;
import trackitnus.ui.UiPart;

/**
 * Panel containing the list of modules.
 */
public class ModulePanel extends UiPart<Region> {
    private static final String FXML = "/Module/ModulePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModulePanel.class);

    @FXML
    private HBox moduleHeader;

    @FXML
    private Label code;

    @FXML
    private Label name;

    /**
     * Creates a {@code ContactListPanel} with the given {@code ObservableList}.
     */
    public ModulePanel(Module module) {
        super(FXML);
        code.setText(module.getCode().code);
        name.setText(module.getName().fullName);
    }

}
