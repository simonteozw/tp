package trackitnus.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import trackitnus.commons.core.LogsCenter;
import trackitnus.logic.Logic;
import trackitnus.model.module.Module;

public class SidePanel extends UiPart<Region> {
    private static final String FXML = "SidePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SidePanel.class);
    private Consumer<ArrayList<Object>> tabConsumer;
    private Logic logic;
    private final HelpWindow helpWindow;

    /**
     * Constructor for SidePanel
     *
     * @param tabConsumer
     */
    public SidePanel(Consumer<ArrayList<Object>> tabConsumer, Logic logic) {
        super(FXML);
        this.tabConsumer = tabConsumer;
        this.logic = logic;
        helpWindow = new HelpWindow();
        this.initialize();
    }

    @FXML
    private VBox tabContainer;
    private List<Button> tabButtons;

    /**
     * Initialises/reloads the tab buttons in the side panel.
     */
    public void initialize() {
        if (logic != null) {
            tabButtons = new ArrayList<>();

            // Get upcoming button.
            Button upcomingButton = getUpcomingButton();
            tabButtons.add(upcomingButton);
            tabContainer.getChildren().add(upcomingButton);

            // Get modules buttons.
            ObservableList<Module> modules = logic.getFilteredModuleList();
            for (int i = 0; i < modules.size(); i++) {
                Button moduleButton = getModuleButton(modules.get(i));
                tabButtons.add(moduleButton);
                tabContainer.getChildren().add(moduleButton);
            }

            // Get help button.
            Button helpButton = getHelpButton();
            tabButtons.add(helpButton);
            tabContainer.getChildren().add(helpButton);
        }
    }

    public Button getUpcomingButton() {
        Button button = new Button("Upcoming");
        ArrayList<Object> upcomingValues = new ArrayList<>(Arrays.asList((Object) "U"));
        button.setOnAction(actionEvent -> {
            tabConsumer.accept(upcomingValues);
        });
        return button;
    }

    public Button getModuleButton(Module module) {
        Button button = new Button(module.getCode().code);
        ArrayList<Object> moduleValues = new ArrayList<>(Arrays.asList((Object) "M", (Object) module));
        button.setOnAction(actionEvent -> {
            tabConsumer.accept(moduleValues);
        });
        return button;
    }

    /**
     * Create a button that when clicked,
     * the help window or focuses on it if it's already opened.
     * @return button Help button.
     */
    public Button getHelpButton() {
        Button button = new Button("Help");
        button.setOnAction(actionEvent -> {
            if (!helpWindow.isShowing()) {
                helpWindow.show();
            } else {
                helpWindow.focus();
            }
        });
        return button;
    }
}
