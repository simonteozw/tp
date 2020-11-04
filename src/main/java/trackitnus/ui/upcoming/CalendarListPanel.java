package trackitnus.ui.upcoming;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import trackitnus.logic.Logic;
import trackitnus.model.task.Task;
import trackitnus.ui.UiPart;
import trackitnus.ui.task.TaskCard;

public class CalendarListPanel extends UiPart<Region> {
    private static final String FXML = "Upcoming/CalendarListPanel.fxml";
    private final Logic logic;

    @FXML
    private ListView<Day> dayListView;

    public CalendarListPanel(ObservableList<Day> dayList, Logic logic) {
        super(FXML);
        this.logic = logic;
        dayListView.setItems(dayList);
        dayListView.setCellFactory(listView -> new CalendarListPanel.CalendarListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class CalendarListViewCell extends ListCell<Day> {
        @Override
        protected void updateItem(Day day, boolean empty) {
            super.updateItem(day, empty);

            if (empty || day == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DayCard(day, logic.getDayUpcomingTasks(day.getDate()),
                    logic.getDayUpcomingLessons(day.getDate()), logic).getRoot());
            }
        }
    }
}
