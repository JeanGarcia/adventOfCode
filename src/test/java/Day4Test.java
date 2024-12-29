import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Day4Test
 *
 * @author Jean
 * @since 29/12/2024
 */
public class Day4Test {

    @Test
    void shouldCountWordFromFileWithoutXmasActive() {
        int result = Day4.countWordFromFile("XMAS", "src/test/resources/day4/day4_input_subset.txt", false);
        assertEquals(18, result);

        int result2 = Day4.countWordFromFile("XMAS", "src/test/resources/day4/day4_input.txt", false);
        assertEquals(2507, result2);
    }

    @Test
    void shouldCountWordFromFileWithXmasActive() {
        int result = Day4.countWordFromFile("XMAS", "src/test/resources/day4/day4_input_subset.txt", true);
        assertEquals(9, result);

        int result2 = Day4.countWordFromFile("XMAS", "src/test/resources/day4/day4_input.txt", true);
        assertEquals(1969, result2);
    }
}
