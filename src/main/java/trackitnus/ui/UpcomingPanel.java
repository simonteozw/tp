package trackitnus.ui;

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

public class UpcomingPanel extends UiPart<Region> {

    private static final String FXML = "UpcomingPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(UpcomingPanel.class);

    private LocalDate today = LocalDate.now();
    private final ObservableList<Day> calendarDates = FXCollections.observableArrayList();

    @FXML
    private ListView<Day> calendarView;

    /**
     * Constructor for Upcoming Panel
     */
    public UpcomingPanel() {
        super(FXML);

        getDatesBetween(today, today.plusDays(7));
        calendarView.setItems(calendarDates);
        calendarView.setCellFactory(listView -> new DateListViewCell());
    }

    public void getDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> list = startDate.datesUntil(endDate).collect(Collectors.toList());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM EEEE");

        for (LocalDate date : list) {
            String datestring = date.format(formatter);
            System.out.println(datestring + ", ");
            calendarDates.add(new Day(datestring));
        }
    }

    class DateListViewCell extends ListCell<Day> {
        @Override
        protected void updateItem(Day day, boolean empty) {
            super.updateItem(day, empty);

            if (empty || day == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DayCard(day).getRoot());
            }
        }
    }
}
