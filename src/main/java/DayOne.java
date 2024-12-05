import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayOne {

    public static Integer findAddedDistanceBetweenTwoLists(List<Integer> leftList, List<Integer> rightList) {
        leftList.sort(Integer::compareTo);
        rightList.sort(Integer::compareTo);
        int sum = 0;
        for (int i = 0; i < leftList.size(); i++) {
            sum += Math.abs(leftList.get(i) - rightList.get(i));
        }
        return sum;
    }

    public static Integer findSimilarityScore(List<Integer> leftList, List<Integer> rightList) {
        Map<Integer, Integer> counter = new HashMap<>();
        int sum = 0;
        for (Integer rightItem : rightList) {
            counter.put(rightItem, counter.getOrDefault(rightItem, 0) + 1);
        }
        for (Integer leftItem : leftList) {
            sum += counter.getOrDefault(leftItem, 0) * leftItem;
        }
        return sum;
    }
}
