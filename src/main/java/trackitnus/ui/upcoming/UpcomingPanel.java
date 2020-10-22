package trackitnus.ui.upcoming;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import trackitnus.commons.core.LogsCenter;
import trackitnus.logic.Logic;
import trackitnus.ui.UiPart;

public class UpcomingPanel extends UiPart<Region> {

    private static final String FXML = "/Upcoming/UpcomingPanel.fxml";

    private final Logic logic;
    private final Logger logger = LogsCenter.getLogger(UpcomingPanel.class);

    private LocalDate today = LocalDate.now();
    private final ObservableList<Day> calendarDates = FXCollections.observableArrayList();

    @FXML
    private ListView<Day> calendarView;

    /**
     * Constructor for Upcoming Panel
     */
    public UpcomingPanel(Logic logic) {
        super(FXML);
        this.logic = logic;

        getDatesBetween(today, today.plusDays(7));
        calendarView.setItems(calendarDates);
        calendarView.setCellFactory(listView -> new DateListViewCell());
    }

    public void getDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> list = startDate.datesUntil(endDate).collect(Collectors.toList());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM EEEE");

        calendarDates.add(new Day("Overdue")); //TODO: Add implementation for overdue tasks

        for (LocalDate date : list) {
            String datestring = date.format(formatter);
            calendarDates.add(new Day(datestring));
        }

        calendarDates.add(new Day("Future")); //TODO: Add implementation for tasks after the current week
    }

    class DateListViewCell extends ListCell<Day> {
        @Override
        protected void updateItem(Day day, boolean empty) {
            super.updateItem(day, empty);

            if (empty || day == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DayCard(day, logic.getFilteredTaskList()).getRoot());
            }
        }
    }
}
