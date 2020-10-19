package trackitnus.ui;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import trackitnus.commons.core.GuiSettings;
import trackitnus.commons.core.LogsCenter;
import trackitnus.logic.Logic;
import trackitnus.logic.commands.CommandResult;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.logic.parser.exceptions.ParseException;
import trackitnus.model.contact.Contact;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.module.Module;
import trackitnus.model.task.Task;
import trackitnus.ui.contact.ContactListPanel;
import trackitnus.ui.lesson.LessonListPanel;
import trackitnus.ui.module.ModuleListPanel;
import trackitnus.ui.task.TaskListPanel;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;
//    private final HelpWindow helpWindow;

    // Independent Ui parts residing in this Ui container

//    private LessonListPanel lessonListPanel;
    private TaskListPanel taskListPanel;
    private ModuleListPanel moduleListPanel;
    private ContactListPanel contactListPanel;

    private ResultDisplay resultDisplay;

    @FXML
    private StackPane commandBoxPlaceholder;

//    @FXML
//    private MenuItem helpMenuItem;

//    @FXML
//    private StackPane lessonListPanelPlaceholder;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private StackPane moduleListPanelPlaceholder;

    @FXML
    private StackPane contactListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private BorderPane borderPane;

    @FXML
    private StackPane sidePanelPlaceholder;

    @FXML
    private StackPane tabPanelPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

//        setAccelerators();

//        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

//    private void setAccelerators() {
//        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
//    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {

//        lessonListPanel = new LessonListPanel(logic.getFilteredLessonList());
//        lessonListPanelPlaceholder.getChildren().add(lessonListPanel.getRoot());

        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());

        moduleListPanel = new ModuleListPanel(logic.getFilteredModuleList());

        contactListPanel = new ContactListPanel(logic.getFilteredContactList());

        switchTab("C");
        sidePanelPlaceholder.getChildren().add(new SidePanel(this::switchTab).getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getTrackIterFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    private void switchTab(String tabName) {
        logger.info("Switching tab to: " + tabName);
        tabPanelPlaceholder.getChildren().clear();

        if (tabName.equals("M")) {
            tabPanelPlaceholder.getChildren().add(moduleListPanel.getRoot());
        }
        if (tabName.equals("C")) {
            tabPanelPlaceholder.getChildren().add(contactListPanel.getRoot());
        }
        if (tabName.equals("T")) {
            tabPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
        }
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

//    /**
//     * Opens the help window or focuses on it if it's already opened.
//     */
//    @FXML
//    public void handleHelp() {
//        if (!helpWindow.isShowing()) {
//            helpWindow.show();
//        } else {
//            helpWindow.focus();
//        }
//    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
            (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
//        helpWindow.hide();
        primaryStage.hide();
    }


//    private void changeTabOnCommandEntered(String commandText) {
//        String type = String.valueOf(commandText.charAt(0));
//        switch (type) {
//        case Task.TYPE: //Go to Task tab
//        case Lesson.TYPE: //Go to Lessons tab
//            tabPane.getSelectionModel().select(0);
//            break;
//        case Module.TYPE: //Go to Modules tab
//            tabPane.getSelectionModel().select(1);
//            break;
//        case Contact.TYPE: //Go to Contacts tab
//            tabPane.getSelectionModel().select(2);
//            break;
//        default:
//            break;
//        }
//    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
//            changeTabOnCommandEntered(commandText);
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

//            if (commandResult.isShowHelp()) {
//                handleHelp();
//            }
//
//            if (commandResult.isExit()) {
//                handleExit();
//            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
