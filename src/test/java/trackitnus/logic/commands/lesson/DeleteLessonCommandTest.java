package trackitnus.logic.commands.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackitnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackitnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackitnus.testutil.typical.TypicalTrackIter.getTypicalTrackIter;

import org.junit.jupiter.api.Test;

import trackitnus.commons.core.Messages;
import trackitnus.commons.core.index.Index;
import trackitnus.model.Model;
import trackitnus.model.ModelManager;
import trackitnus.model.UserPrefs;
import trackitnus.model.lesson.Lesson;
import trackitnus.testutil.typical.TypicalIndexes;

class DeleteLessonCommandTest {

    private final Model model = new ModelManager(getTypicalTrackIter(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Lesson lessonToDelete = model.getFilteredLessonList().get(TypicalIndexes.INDEX_FIRST.getZeroBased());
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(TypicalIndexes.INDEX_FIRST);

        String expectedMessage = String.format(DeleteLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete);

        ModelManager expectedModel = new ModelManager(model.getTrackIter(), new UserPrefs());
        expectedModel.deleteLesson(lessonToDelete);

        assertCommandSuccess(deleteLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredLessonList().size() + 1);
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(outOfBoundIndex);

        assertCommandFailure(deleteLessonCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

//    @Test
//    public void execute_validIndexFilteredList_success() {
//        LessonCommandTestUtil.showLessonAtIndex(model, TypicalIndexes.INDEX_FIRST);
//
//        Lesson lessonToDelete = model.getFilteredLessonList().get(TypicalIndexes.INDEX_FIRST.getZeroBased());
//        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(TypicalIndexes.INDEX_FIRST);
//
//        String expectedMessage = String.format(DeleteLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete);
//
//        Model expectedModel = new ModelManager(model.getTrackIter(), new UserPrefs());
//        expectedModel.deleteLesson(lessonToDelete);
//        showNoLesson(expectedModel);
//
//        assertCommandSuccess(deleteLessonCommand, model, expectedMessage, expectedModel);
//    }

//    @Test
//    public void execute_invalidIndexFilteredList_throwsCommandException() {
//        LessonCommandTestUtil.showLessonAtIndex(model, TypicalIndexes.INDEX_FIRST);
//
//        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getTrackIter().getLessonList().size());
//
//        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(outOfBoundIndex);
//
//        assertCommandFailure(deleteLessonCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
//    }

    @Test
    public void equals() {
        DeleteLessonCommand deleteFirstCommand = new DeleteLessonCommand(TypicalIndexes.INDEX_FIRST);
        DeleteLessonCommand deleteSecondCommand = new DeleteLessonCommand(TypicalIndexes.INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteLessonCommand deleteFirstCommandCopy = new DeleteLessonCommand(TypicalIndexes.INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different lesson -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoLesson(Model model) {
        model.updateFilteredLessonList(p -> false);

        assertTrue(model.getFilteredLessonList().isEmpty());
    }
}
