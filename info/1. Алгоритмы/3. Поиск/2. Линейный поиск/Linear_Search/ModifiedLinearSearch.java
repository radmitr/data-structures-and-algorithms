
import java.util.ArrayList;
import java.util.List;

public class ModifiedLinearSearch {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] s = new int[] { -2, 0, 3, 5, 7, 9, 11, 15, 18, 21 };
        List<Integer> sequince = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            sequince.add(s[i]);
        }
        int element = 5;

        System.out.println(modifiedLinearSearch(sequince, element));

        System.out.println(sequince);
    }

    public static int modifiedLinearSearch(List<Integer> sequince, int element) {
        sequince.add(element);
        int lastIndex = sequince.size() - 1;
        int i = 0;
        for (; sequince.get(i) != element;) {
            i++;
        }
        sequince.remove(lastIndex);
        if (i != lastIndex) {
            return i;
        }
        return -1;
    }

}
