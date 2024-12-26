/**
 * ######## DAY TWO #########
 *
 * @author Jean
 * @since 06/12/2024
 */
public class DayTwo {

    public static int safeReportsCount(int[][] reports, int tolerance) {
        int counter = 0;

        for (int[] report : reports) {
            if (isASafeReport(report, tolerance)) {
                counter += 1;
            }
        }
        return counter;
    }

    private static boolean isASafeReport(int[] report, int tolerance) {
        boolean isValid = true;
        String orientation = report[0] - report[1] > 0 ? "DESC" : "ASC";
        for (int i = 1; i < report.length; i++) {
            if (hasChangedOrientation(report[i - 1], report[i], orientation) || hasAnInvalidJump(report[i - 1], report[i])) {
                if (tolerance == 0) {
                    isValid = false;
                    break;
                }
                if (i == report.length - 1) {
                    break;
                }

                isValid = isASafeReport(removeAtIndex(report, i - 1), tolerance - 1)
                        || isASafeReport(removeAtIndex(report, i), tolerance - 1)
                        || (i > 1 && isASafeReport(removeAtIndex(report, i - 2), tolerance - 1));
                break;
            }
        }
        return isValid;
    }

    public static int[] removeAtIndex(int[] array, int index) {
        int[] result = new int[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i == index) {
                continue;
            }
            result[j] = array[i];
            j++;
        }
        return result;
    }

    private static boolean hasAnInvalidJump(int levelOne, int levelTwo) {
        return Math.abs(levelOne - levelTwo) > 3 || Math.abs(levelOne - levelTwo) < 1;
    }

    private static boolean hasChangedOrientation(int levelOne, int levelTwo, String currentOrientation) {
        String orientation = levelOne - levelTwo > 0 ? "DESC" : "ASC";
        return !currentOrientation.equals(orientation);
    }
}
