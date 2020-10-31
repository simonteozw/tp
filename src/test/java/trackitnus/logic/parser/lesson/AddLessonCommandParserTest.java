package trackitnus.logic.parser.lesson;

import org.junit.jupiter.api.Test;

import trackitnus.logic.commands.lesson.AddLessonCommand;
import trackitnus.logic.parser.CommandParserTestUtil;
import trackitnus.testutil.typical.TypicalLessons;

public class AddLessonCommandParserTest {
    private final AddLessonCommandParser parser = new AddLessonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // TODO: make it less confusing
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS1101S n/lecture d/Fri 12:00-14:00",
            new AddLessonCommand(TypicalLessons.get(0)));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS2100 n/laboratory d/Mon 09:00-10:00",
            new AddLessonCommand(TypicalLessons.get(3)));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS2030S n/recitation d/Wed 12:00-13:00",
            new AddLessonCommand(TypicalLessons.get(11)));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS1101S n/Lec d/Fri 12:00-14:00",
            new AddLessonCommand(TypicalLessons.get(0)));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS2100 n/Lab d/Mon 09:00-10:00",
            new AddLessonCommand(TypicalLessons.get(3)));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS2030S n/Rec d/Wed 12:00-13:00",
            new AddLessonCommand(TypicalLessons.get(11)));
    }

}
