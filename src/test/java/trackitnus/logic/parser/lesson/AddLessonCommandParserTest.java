package trackitnus.logic.parser.lesson;

import org.junit.jupiter.api.Test;

import trackitnus.logic.commands.lesson.AddLessonCommand;
import trackitnus.logic.parser.CommandParserTestUtil;
import trackitnus.testutil.typical.TypicalLessons;

public class AddLessonCommandParserTest {
    private final AddLessonCommandParser parser = new AddLessonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS1101S t/lecture d/Fri 12:00-14:00",
            new AddLessonCommand(TypicalLessons.CS1101S_LEC));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS1101S t/tutorial d/Mon 12:00-14:00",
            new AddLessonCommand(TypicalLessons.CS1101S_TUT));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS2100 t/laboratory d/Mon 09:00-10:00",
            new AddLessonCommand(TypicalLessons.CS2100_LAB));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS2030S t/recitation d/Wed 12:00-13:00",
            new AddLessonCommand(TypicalLessons.CS2030S_REC));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/GER1000H t/sectional d/Fri 14:00-16:00",
            new AddLessonCommand(TypicalLessons.GER1000H_SEC));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS1101S t/LEC d/Fri 12:00-14:00",
            new AddLessonCommand(TypicalLessons.CS1101S_LEC));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS1101S t/TUT d/Mon 12:00-14:00",
            new AddLessonCommand(TypicalLessons.CS1101S_TUT));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS2100 t/LAB d/Mon 09:00-10:00",
            new AddLessonCommand(TypicalLessons.CS2100_LAB));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS2030S t/REC d/Wed 12:00-13:00",
            new AddLessonCommand(TypicalLessons.CS2030S_REC));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/GER1000H t/SEC d/Fri 14:00-16:00",
            new AddLessonCommand(TypicalLessons.GER1000H_SEC));
    }

}
