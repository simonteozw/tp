package trackitnus.ui.module;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import trackitnus.commons.core.LogsCenter;
import trackitnus.logic.Logic;
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
    private static final String FXML = "/Module/ModulePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModulePanel.class);

    private LessonListPanel lessonListPanel;
    private TaskListPanel taskListPanel;
    private ContactListPanel contactListPanel;

    private final int rowHeight = 45;
    private final int contactRowHeight = 50;

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

        ObservableList<Lesson> lessons = logic.getModuleLessons(module.getCode());
        ObservableList<Task> tasks = logic.getModuleTasks(module.getCode());
        ObservableList<Contact> contacts = logic.getModuleContacts(module.getCode());

        lessonListPanelPlaceholder.setPrefHeight(lessons.size() * rowHeight + rowHeight);
        lessonListPanel = new LessonListPanel(lessons);
        lessonListPanelPlaceholder.getChildren().add(lessonListPanel.getRoot());

        taskListPanelPlaceholder.setPrefHeight(tasks.size() * rowHeight + rowHeight);
        taskListPanel = new TaskListPanel(tasks);
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

        contactListPanelPlaceholder.setPrefHeight(contacts.size() * contactRowHeight + contactRowHeight);
        contactListPanel = new ContactListPanel(contacts);
        contactListPanelPlaceholder.getChildren().add(contactListPanel.getRoot());
    }

}
