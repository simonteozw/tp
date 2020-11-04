package trackitnus.ui.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import trackitnus.model.task.Task;
import trackitnus.ui.UiPart;

public class OverdueTaskCard extends UiPart<Region> {
    private static final String FXML = "Task/OverdueTaskListCard.fxml";

    public final Task task;
    private final LocalDate today = LocalDate.now();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM");

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label code;
    @FXML
    private Label name;
    @FXML
    private Label date;
    @FXML
    private Label remark;

    /**
     * Creates a {@code TaskCard} with the given {@code Task} and index to display.
     */
    public OverdueTaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText("[" + displayedIndex + "] ");
        name.setText(task.getName().toString());
        date.setText(task.getDate().format(formatter));

        if (task.getDate().isAfter(today.plusDays(7))) {
            date.setStyle("-fx-text-fill: #68C2E8");
        } else {
            date.setStyle("-fx-text-fill: #d53636");
        }
        remark.setText(task.getRemark());
        code.setText(task.getCode().isPresent() ? task.getCode().get().code + " " : "");
    }

}
