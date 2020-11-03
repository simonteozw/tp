package trackitnus.logic.commands.lesson;

import static trackitnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackitnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackitnus.testutil.typical.TypicalTrackIter.getTypicalTrackIter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackitnus.commons.core.Messages;
import trackitnus.model.Model;
import trackitnus.model.ModelManager;
import trackitnus.model.UserPrefs;
import trackitnus.model.lesson.Lesson;
import trackitnus.testutil.builder.LessonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddLessonCommand}.
 */
public class AddLessonCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTrackIter(), new UserPrefs());
    }

    @Test
    public void execute_newLesson_success() {
        Lesson validLesson = new LessonBuilder().build();

        Model expectedModel = new ModelManager(model.getTrackIter(), new UserPrefs());
        expectedModel.addLesson(validLesson);

        assertCommandSuccess(new AddLessonCommand(validLesson), model,
            String.format(Messages.MESSAGE_ADD_LESSON_SUCCESS, validLesson), expectedModel);
    }

    @Test
    public void execute_duplicateLesson_throwsCommandException() {
        Lesson lessonInList = model.getTrackIter().getLessonList().get(0);
        assertCommandFailure(new AddLessonCommand(lessonInList), model, Messages.MESSAGE_DUPLICATE_LESSON);
    }

}
