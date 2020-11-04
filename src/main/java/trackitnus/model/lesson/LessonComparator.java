package trackitnus.model.lesson;

import java.util.Comparator;

public class LessonComparator implements Comparator<Lesson> {
    /**
     * Compares 2 lessons in chronological order to facilitate sorting.
     *
     * @param firstLesson  First lesson to compare.
     * @param secondLesson Second lesson to compare.
     * @return an int < 0 if firstLesson is "less than" secondLesson.
     */
    @Override
    public int compare(Lesson firstLesson, Lesson secondLesson) {
        int res = firstLesson.getTime().compareTo(secondLesson.getTime());
        if (res != 0) {
            return res;
        }
        return firstLesson.toString().compareTo(secondLesson.toString());
    }
}
