package trackitnus.logic.parser.lesson;

import org.junit.jupiter.api.Test;

import trackitnus.logic.commands.lesson.AddLessonCommand;
import trackitnus.logic.parser.CommandParserTestUtil;
import trackitnus.testutil.typical.TypicalLessons;

public class AddLessonCommandParserTest {
    private final AddLessonCommandParser parser = new AddLessonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        CommandParserTestUtil.assertParseSuccess(parser, " m/CS1101S t/lecture d/Wed 12:00-14:00 a/TP-SR2",
            new AddLessonCommand(TypicalLessons.CS1101S_LEC));
        CommandParserTestUtil.assertParseSuccess(parser, " m/CS1101S t/tutorial d/Mon 14:00-16:00 a/COM1-0208",
            new AddLessonCommand(TypicalLessons.CS1101S_TUT));
        CommandParserTestUtil.assertParseSuccess(parser, " m/CS2100 t/laboratory d/Mon 09:00-10:00 a/E-learning",
            new AddLessonCommand(TypicalLessons.CS2100_LAB));
        CommandParserTestUtil.assertParseSuccess(parser, " m/CS2030S t/recitation d/Wed 12:00-13:00 a/LT19",
            new AddLessonCommand(TypicalLessons.CS2030S_REC));
        CommandParserTestUtil.assertParseSuccess(parser, " m/GER1000H t/sectional d/Fri 14:00-16:00 a/PGPH-FR4",
            new AddLessonCommand(TypicalLessons.GER1000H_SEC));
        CommandParserTestUtil.assertParseSuccess(parser, " m/CS1101S t/LEC d/Wed 12:00-14:00 a/TP-SR2",
            new AddLessonCommand(TypicalLessons.CS1101S_LEC));
        CommandParserTestUtil.assertParseSuccess(parser, " m/CS1101S t/TUT d/Mon 14:00-16:00 a/COM1-0208",
            new AddLessonCommand(TypicalLessons.CS1101S_TUT));
        CommandParserTestUtil.assertParseSuccess(parser, " m/CS2100 t/LAB d/Mon 09:00-10:00 a/E-learning",
            new AddLessonCommand(TypicalLessons.CS2100_LAB));
        CommandParserTestUtil.assertParseSuccess(parser, " m/CS2030S t/REC d/Wed 12:00-13:00 a/LT19",
            new AddLessonCommand(TypicalLessons.CS2030S_REC));
        CommandParserTestUtil.assertParseSuccess(parser, " m/GER1000H t/SEC d/Fri 14:00-16:00 a/PGPH-FR4",
            new AddLessonCommand(TypicalLessons.GER1000H_SEC));
    }

}
