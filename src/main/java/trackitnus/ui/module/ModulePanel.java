package trackitnus.ui.module;

import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import trackitnus.commons.core.LogsCenter;
import trackitnus.logic.Logic;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.contact.Contact;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.module.Module;
import trackitnus.model.task.Task;
import trackitnus.ui.UiPart;
import trackitnus.ui.contact.ContactListPanel;
import trackitnus.ui.lesson.LessonListPanel;
import trackitnus.ui.task.TaskListPanel;

/**
 * Panel containing the list of modules.
 */
public class ModulePanel extends UiPart<Region> {
    private static final String FXML = "Module/ModulePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModulePanel.class);
    private final int defaultRowHeight = 50;
    private final int lessonRowHeight = 40;
    private final int paddingHeight = 10;
    private final LessonListPanel lessonListPanel;
    private final TaskListPanel taskListPanel;
    private final ContactListPanel contactListPanel;
    @FXML
    private HBox moduleHeader;
    @FXML
    private Circle moduleCircle;
    @FXML
    private Label moduleName;
    @FXML
    private StackPane lessonListPanelPlaceholder;
    @FXML
    private Label taskHeader;
    @FXML
    private StackPane taskListPanelPlaceholder;
    @FXML
    private Label contactHeader;
    @FXML
    private StackPane contactListPanelPlaceholder;


    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}.
     */
    public ModulePanel(Module module, Logic logic) throws CommandException {
        super(FXML);
        moduleHeader.setAlignment(Pos.CENTER_LEFT);
        moduleName.setWrapText(true);
        moduleName.setMaxWidth(500);
        moduleName.setText(module.getCode().code + " " + module.getName().value);

        int moduleIndex = logic.getModuleIndex(module).getZeroBased();
        Color moduleColor = Module.COLORS.get(moduleIndex % 10);
        moduleCircle.setFill(moduleColor);
        taskHeader.setStyle("-fx-text-fill: " + getColorHex(moduleColor) + ";");
        contactHeader.setStyle("-fx-text-fill: " + getColorHex(moduleColor) + ";");

        ObservableList<Lesson> lessons = logic.getModuleLessons(module.getCode());
        ObservableList<Task> tasks = logic.getModuleTasks(module.getCode());
        ObservableList<Contact> contacts = logic.getModuleContacts(module.getCode());

        // Allow height of lists to update automatically
        lessonListPanelPlaceholder.prefHeightProperty().bind(Bindings.size(lessons)
            .multiply(lessonRowHeight).add(paddingHeight));
        taskListPanelPlaceholder.prefHeightProperty().bind(Bindings.size(tasks)
            .multiply(defaultRowHeight).add(paddingHeight));
        contactListPanelPlaceholder.prefHeightProperty().bind(Bindings.size(contacts)
            .multiply(defaultRowHeight).add(paddingHeight));

        lessonListPanel = new LessonListPanel(lessons);
        lessonListPanelPlaceholder.getChildren().add(lessonListPanel.getRoot());

        taskListPanel = new TaskListPanel(tasks);
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

        contactListPanel = new ContactListPanel(contacts);
        contactListPanelPlaceholder.getChildren().add(contactListPanel.getRoot());
    }

    public String getColorHex(Color color) {
        java.awt.Color c = new java.awt.Color((float) color.getRed(),
            (float) color.getGreen(),
            (float) color.getBlue(),
            (float) color.getOpacity());
        String hex = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
        return hex;
    }

}
