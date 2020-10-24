package trackitnus.ui.module;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import trackitnus.commons.core.LogsCenter;
import trackitnus.logic.Logic;
import trackitnus.model.module.Module;
import trackitnus.ui.UiPart;
import trackitnus.ui.contact.ContactListPanel;
import trackitnus.ui.lesson.LessonListPanel;
import trackitnus.ui.task.TaskListPanel;

/**
 * Panel containing the list of modules.
 */
public class ModulePanel extends UiPart<Region> {
    private static final String FXML = "/Module/ModulePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModulePanel.class);

    private LessonListPanel lessonListPanel;
    private TaskListPanel taskListPanel;
    private ContactListPanel contactListPanel;

    private final int rowHeight = 50;

    @FXML
    private HBox moduleHeader;
    @FXML
    private Label code;
    @FXML
    private Label name;
    @FXML
    private StackPane lessonListPanelPlaceholder;
    @FXML
    private StackPane taskListPanelPlaceholder;
    @FXML
    private StackPane contactListPanelPlaceholder;

    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}.
     */
    public ModulePanel(Module module, Logic logic) {
        super(FXML);
        code.setText(module.getCode().code);
        name.setWrapText(true);
        name.setMaxWidth(400);
        name.setText(module.getName().fullName);

        lessonListPanelPlaceholder.setMinHeight(logic.getModuleLessons(module.getCode()).size() * rowHeight);
        lessonListPanelPlaceholder.setMaxHeight(logic.getModuleLessons(module.getCode()).size() * rowHeight);
        lessonListPanel = new LessonListPanel(logic.getModuleLessons(module.getCode()));
        lessonListPanelPlaceholder.getChildren().add(lessonListPanel.getRoot());

        taskListPanelPlaceholder.setMinHeight(logic.getModuleTasks(module.getCode()).size() * rowHeight);
        taskListPanelPlaceholder.setMaxHeight(logic.getModuleTasks(module.getCode()).size() * rowHeight);
        taskListPanel = new TaskListPanel(logic.getModuleTasks(module.getCode()));
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

        contactListPanelPlaceholder.setMinHeight(logic.getModuleContacts(module.getCode()).size() * rowHeight);
        contactListPanelPlaceholder.setMaxHeight(logic.getModuleContacts(module.getCode()).size() * rowHeight);
        contactListPanel = new ContactListPanel(logic.getModuleContacts(module.getCode()));
        contactListPanelPlaceholder.getChildren().add(contactListPanel.getRoot());
    }

}
