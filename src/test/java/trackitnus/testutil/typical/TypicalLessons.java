package trackitnus.testutil.typical;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import trackitnus.model.commons.Code;
import trackitnus.model.lesson.DayOfWeek;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.lesson.LessonDateTime;
import trackitnus.model.lesson.Type;
import trackitnus.model.util.SampleDataUtil;

public class TypicalLessons {

    public static final Lesson CS1101S_LEC =
        new Lesson(new Code("CS1101S"), Type.LEC, new LessonDateTime(DayOfWeek.Fri, LocalTime.NOON,
            LocalTime.NOON.plusHours(2)));
    public static final Lesson CS1101S_TUT =
        new Lesson(new Code("CS1101S"), Type.TUT, new LessonDateTime(DayOfWeek.Mon, LocalTime.NOON,
            LocalTime.NOON.plusHours(2)));
    public static final Lesson CS2100_LAB =
        new Lesson(new Code("CS2100"), Type.LAB, new LessonDateTime(DayOfWeek.Mon, LocalTime.MIDNIGHT.plusHours(9),
            LocalTime.MIDNIGHT.plusHours(10)));
    public static final Lesson MA1101R_LEC =
        new Lesson(new Code("MA1101R"), Type.LEC, new LessonDateTime(DayOfWeek.Thu, LocalTime.NOON.plusHours(4),
            LocalTime.NOON.plusHours(6)));
    public static final Lesson GER1000H_SEC =
        new Lesson(new Code("GER1000H"), Type.SEC, new LessonDateTime(DayOfWeek.Fri, LocalTime.NOON.plusHours(2),
            LocalTime.NOON.plusHours(4)));
    public static final Lesson CS2030S_REC =
        new Lesson(new Code("CS2030S"), Type.REC, new LessonDateTime(DayOfWeek.Wed, LocalTime.NOON,
            LocalTime.NOON.plusHours(1)));


    public static List<Lesson> get() {
        return Arrays.asList(SampleDataUtil.getSampleLessons());
    }

    public static Lesson get(int index) {
        return get().get(index);
    }
}
