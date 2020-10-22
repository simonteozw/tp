package trackitnus.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import trackitnus.commons.core.LogsCenter;
import trackitnus.logic.Logic;
import trackitnus.model.module.Module;

public class SidePanel extends UiPart<Region> {
    private static final String FXML = "SidePanel.fxml";

    private final Logger logger = LogsCenter.getLogger(SidePanel.class);

    private Consumer<String> consumer;
    private Logic logic;
    private final HelpWindow helpWindow;

    /**
     * Constructor for SidePanel
     *
     * @param consumer
     */
    public SidePanel(Consumer<String> consumer, Logic logic) {
        super(FXML);

        this.consumer = consumer;
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

            // Get upcoming tab button.
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

            //TODO: next time not so many tabs already, so don't worry long method.
            Button contactButton = getContactButton();
            tabButtons.add(contactButton);
            tabContainer.getChildren().add(contactButton);

            Button taskButton = getTaskButton();
            tabButtons.add(taskButton);
            tabContainer.getChildren().add(taskButton);

            Button helpButton = getHelpButton();
            tabButtons.add(helpButton);
            tabContainer.getChildren().add(helpButton);
        }
    }

    public Button getUpcomingButton() {
        Button button = new Button("Upcoming");
        button.setOnAction(actionEvent ->  {
            consumer.accept("U");
        });
        return button;
    }
    public Button getContactButton() {
        Button button = new Button("Contact");
        button.setOnAction(actionEvent ->  {
            consumer.accept("C");
        });
        return button;
    }
    public Button getModuleButton(Module module) {
        Button button = new Button(module.getCode().code);
        button.setOnAction(actionEvent ->  {
            consumer.accept("M");
        });
        return button;
    }
    public Button getTaskButton() {
        Button button = new Button("Task");
        button.setOnAction(actionEvent ->  {
            consumer.accept("T");
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
        button.setOnAction(actionEvent ->  {
            if (!helpWindow.isShowing()) {
                helpWindow.show();
            } else {
                helpWindow.focus();
            }
        });
        return button;
    }
}
