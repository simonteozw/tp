package trackitnus.testutil.builder;

import java.time.LocalDate;
import java.util.Optional;

import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.task.Task;
import trackitnus.testutil.TestUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Submit v1.4";
    public static final String DEFAULT_DATE = "09/11/2020";
    public static final String DEFAULT_CODE = "CS2103T";
    public static final String DEFAULT_REMARK = "Do a final stress test before submission";

    private Name name;
    private LocalDate date;
    private Code code;
    private String remark;

    /**
     * Creates a {@name TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = TestUtil.parseName(DEFAULT_NAME);
        date = TestUtil.parseDate(DEFAULT_DATE);
        code = TestUtil.parseCode(DEFAULT_CODE);
        remark = TestUtil.parseRemark(Optional.of(DEFAULT_REMARK));
    }

    /**
     * Initializes the TaskBuilder with the data of {@name taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        date = taskToCopy.getDate();
        code = taskToCopy.getCode().orElse(null);
    }

    /**
     * Sets the {@name Name} of the {@name Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = TestUtil.parseName(name);
        return this;
    }

    /**
     * Sets the {@name Date} of the {@name Task} that we are building.
     */
    public TaskBuilder withDate(String date) {
        this.date = TestUtil.parseDate(date);
        return this;
    }

    /**
     * Sets the {@name Code} of the {@name Task} that we are building.
     */
    public TaskBuilder withCode(Optional<String> code) {
        this.code = TestUtil.parseOptionalCode(code);
        return this;
    }

    /**
     * Sets the {@name Remark} of the {@name Task} that we are building.
     */
    public TaskBuilder withRemark(String remark) {
        this.remark = TestUtil.parseRemark(Optional.of(remark));
        return this;
    }

    public Task build() {
        return new Task(name, date, code, remark);
    }

}
