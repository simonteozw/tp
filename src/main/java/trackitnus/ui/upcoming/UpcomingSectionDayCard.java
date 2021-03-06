package trackitnus.ui.upcoming;

import java.time.LocalDate;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import trackitnus.logic.Logic;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.module.Module;
import trackitnus.model.task.Task;
import trackitnus.ui.UiPart;

/**
 * A UI component that displays information of a {@code UpcomingSection} but in a calendar format
 */
public class UpcomingSectionDayCard extends UiPart<Region> {

    public static final int TASK_HEIGHT_OFFSET = 35;
    private static final String FXML = "Upcoming/DayCard.fxml";
    private static final int LESSON_ROW_HEIGHT = 27;
    private static final int TASK_ROW_HEIGHT = 45;
    public final UpcomingSection section;
    private final Logic logic;

    @FXML
    private ListView<Task> taskListView;
    @FXML
    private ListView<Lesson> lessonListView;
    @FXML
    private HBox cardPane;
    @FXML
    private Label date;

    /**
     * Constructor for UpcomingSectionDayCard
     *
     * @param section a section in the calendar
     */
    public UpcomingSectionDayCard(UpcomingSection section, ObservableList<Task> taskList,
                                  ObservableList<Lesson> lessonList, Logic logic) {
        super(FXML);
        this.section = section;
        this.logic = logic;

        if (section.getDate().equals(LocalDate.now())) {
            date.setText("Today - " + section.getTitle());
        } else {
            date.setText(section.getTitle());
        }

        if (lessonList.isEmpty()) {
            lessonListView.setStyle("-fx-background-color: transparent");
        }

        setUpLessonView(lessonList);
        setUpTaskView(taskList);
    }

    private void setUpLessonView(ObservableList<Lesson> lessonList) {
        if (lessonList.isEmpty()) {
            lessonListView.prefHeightProperty().setValue(0);
        } else {
            lessonListView.prefHeightProperty().bind(Bindings.size(lessonList).multiply(LESSON_ROW_HEIGHT).add(10));
        }
        lessonListView.setItems(lessonList);
        lessonListView.setCellFactory(listView -> new LessonListViewCell());
    }

    private void setUpTaskView(ObservableList<Task> taskList) {
        taskListView.prefHeightProperty()
            .bind(Bindings.size(taskList).multiply(TASK_ROW_HEIGHT).add(TASK_HEIGHT_OFFSET));
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpcomingSectionDayCard)) {
            return false;
        }

        // state check
        UpcomingSectionDayCard card = (UpcomingSectionDayCard) other;
        return date.getText().equals(card.date.getText());
    }

    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                try {
                    setGraphic(new UpcomingTaskCard(task, logic.getTaskIndex(task).getOneBased()).getRoot());
                } catch (CommandException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class LessonListViewCell extends ListCell<Lesson> {
        @Override
        protected void updateItem(Lesson lesson, boolean empty) {
            super.updateItem(lesson, empty);

            if (empty || lesson == null) {
                setGraphic(null);
                setText(null);
            } else {
                try {
                    int lessonIndex = logic.getLessonIndex(lesson).getOneBased();
                    int moduleIndex = logic.getModuleIndex(lesson.getCode()).getZeroBased();
                    Color lessonColor = Module.COLORS.get(moduleIndex);
                    setGraphic(new UpcomingLessonCard(lesson, lessonIndex, lessonColor).getRoot());
                } catch (CommandException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


