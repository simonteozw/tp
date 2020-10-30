package trackitnus.testutil.typical;

import java.util.Arrays;
import java.util.List;

import trackitnus.model.lesson.Lesson;
import trackitnus.model.util.SampleDataUtil;

public class TypicalLessons {
    public static List<Lesson> get() {
        return Arrays.asList(SampleDataUtil.getSampleLessons());
    }

    public static Lesson get(int index) {
        return get().get(index);
    }
}
