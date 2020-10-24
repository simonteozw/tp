package trackitnus.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import trackitnus.commons.core.LogsCenter;
import trackitnus.logic.Logic;
import trackitnus.model.module.Module;
import trackitnus.ui.upcoming.UpcomingPanel;

public class SidePanel extends UiPart<Region> {
    private static final String FXML = "SidePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SidePanel.class);
    private Consumer<ArrayList<Object>> tabConsumer;
    private Logic logic;
    private final HelpWindow helpWindow;
    private final int moduleRowHeight = 32;

    @FXML
    private Button upcomingButton;

    @FXML
    private ListView<Module> moduleListView;

    @FXML
    private Button helpButton;


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



    /**
     * Initialises/reloads the tab buttons in the side panel.
     */
    public void initialize() {
        if (logic != null) {
            // Get modules buttons.
            ObservableList<Module> modules = logic.getFilteredModuleList();
            moduleListView.setPrefHeight(modules.size() * moduleRowHeight);
            moduleListView.setItems(modules);
            moduleListView.setCellFactory(listView -> new ModuleListViewCell());
        }
    }

    /**
     * Sets the view of the module tab in the side panel.
     */
    class ModuleListViewCell extends ListCell<Module> {
        @Override
        protected void updateItem(Module module, boolean empty) {
            super.updateItem(module, empty);

            if (empty || module == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(getModuleButton(module));
            }
        }
    }

    /**
     * Relays message to MainWindow to get Upcoming panel information in TabPanel.
     */
    public void toggleUpcomingTab() {
        ArrayList<Object> upcomingValues = new ArrayList<>(Arrays.asList((Object) UpcomingPanel.TYPE));
        tabConsumer.accept(upcomingValues);
    }

    /**
     * Relays message to MainWindow to get Help window information in TabPanel.
     */
    public void toggleHelpTab() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Configure the upcoming button tab in the side panel.
     * @return upcomingButton The upcoming button.
     */
    public Button getUpcomingButton() {
        Button button = new Button("Upcoming");
        ArrayList<Object> upcomingValues = new ArrayList<>(Arrays.asList((Object) UpcomingPanel.TYPE));
        button.setOnAction(actionEvent -> {
            tabConsumer.accept(upcomingValues);
        });
        return button;
    }

    /**
     * Configure the module button tab in the side panel.
     * @return moduleButton The module button.
     */
    public Button getModuleButton(Module module) {
        Button button = new Button(module.getCode().code);
        ArrayList<Object> moduleValues = new ArrayList<>(Arrays.asList(Module.TYPE, module));
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
