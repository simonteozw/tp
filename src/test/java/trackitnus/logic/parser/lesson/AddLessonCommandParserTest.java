package trackitnus.logic.parser.lesson;

import org.junit.jupiter.api.Test;

import trackitnus.logic.commands.lesson.AddLessonCommand;
import trackitnus.logic.parser.CommandParserTestUtil;
import trackitnus.testutil.typical.TypicalLessons;

public class AddLessonCommandParserTest {
    private final AddLessonCommandParser parser = new AddLessonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS1101S n/lecture d/Fri 12:00-14:00",
            new AddLessonCommand(TypicalLessons.CS1101S_LEC));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS1101S n/tutorial d/Mon 12:00-14:00",
            new AddLessonCommand(TypicalLessons.CS1101S_TUT));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS2100 n/laboratory d/Mon 09:00-10:00",
            new AddLessonCommand(TypicalLessons.CS2100_LAB));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS2030S n/recitation d/Wed 12:00-13:00",
            new AddLessonCommand(TypicalLessons.CS2030S_REC));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/GER1000H n/sectional d/Fri 14:00-16:00",
            new AddLessonCommand(TypicalLessons.GER1000H_SEC));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS1101S n/LEC d/Fri 12:00-14:00",
            new AddLessonCommand(TypicalLessons.CS1101S_LEC));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS1101S n/TUT d/Mon 12:00-14:00",
            new AddLessonCommand(TypicalLessons.CS1101S_TUT));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS2100 n/LAB d/Mon 09:00-10:00",
            new AddLessonCommand(TypicalLessons.CS2100_LAB));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/CS2030S n/REC d/Wed 12:00-13:00",
            new AddLessonCommand(TypicalLessons.CS2030S_REC));
        CommandParserTestUtil.assertParseSuccess(parser, "L add m/GER1000H n/SEC d/Fri 14:00-16:00",
            new AddLessonCommand(TypicalLessons.GER1000H_SEC));
    }

}
