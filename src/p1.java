import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class p1 {
    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t[]);
    }

    public static void main(String[] args) {
        Function valueConverter = (x) -> {
            String s = "";
            int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
            for (Object y:x) {
                int a = (Integer) y;
                if(a<min2){
                    if (a<min1){
                        min1 = a;
                    }
                    else {
                        min2 = a;
                    }
                }
            }
            return String.valueOf(""+min1+min2);
        };
        System.out.println(valueConverter.apply(new Integer[]{3,5,1,8,4,6,7,5,2}));
    }
}
