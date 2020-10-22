package trackitnus.ui.upcoming;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import trackitnus.model.task.Task;
import trackitnus.ui.UiPart;
import trackitnus.ui.task.TaskCard;

public class DayCard extends UiPart<Region> {

    private static final String FXML = "/Upcoming/DayCard.fxml";

    public final Day day;

    @FXML
    private ListView<Task> taskListView;
    @FXML
    private HBox cardPane;
    @FXML
    private Label date;

    /**
     * Constructor for DayCard
     *
     * @param day
     */
    public DayCard(Day day, ObservableList<Task> taskList) {
        super(FXML);
        this.day = day;
        date.setText(day.getDate());

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
        if (!(other instanceof DayCard)) {
            return false;
        }

        // state check
        DayCard card = (DayCard) other;
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
                setGraphic(new TaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }
}


