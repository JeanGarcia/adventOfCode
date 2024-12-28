import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ######## DAY THREE #########
 *
 * @author Jean
 * @since 28/12/2024
 */
public class Day3 {

    public static int findMulResultFromStringWithConditionals(String corruptedMemoryWithConditionals) {
        Matcher corruptedMemoryDosMatcher = getCorruptedMemoryDosMatcher(corruptedMemoryWithConditionals);
        int result = 0;
        while (corruptedMemoryDosMatcher.find()) {
            result += findMulResultFromString(corruptedMemoryDosMatcher.group());
        }
        return result;
    }

    public static Matcher getCorruptedMemoryDosMatcher(String corruptedMemoryWithConditionals) {
        String regex = "(?:^.*?(?=don't\\(\\))|do\\(\\).*?(?=(?:don't\\(\\)|$)))";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        return pattern.matcher(corruptedMemoryWithConditionals);
    }

    public static int findMulResultFromString(String corruptedMemory) {
        if (corruptedMemory.isEmpty()) {
            return 0;
        }

        Matcher nonCorruptedMatcher = getNonCorruptedMulPairsMatcher(corruptedMemory);
        int result = 0;
        while (nonCorruptedMatcher.find()) {
            result += Integer.parseInt(nonCorruptedMatcher.group(1))
                    * Integer.parseInt(nonCorruptedMatcher.group(2));
        }
        return result;
    }

    private static Matcher getNonCorruptedMulPairsMatcher(String corruptedMemory) {
        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(corruptedMemory);
    }


}
