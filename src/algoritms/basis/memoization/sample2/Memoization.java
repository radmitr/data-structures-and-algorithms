package algoritms.basis.memoization.sample2;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

public class Memoization {

    public static <T, R> Function<T, R> getMemoizedFunction(Function<T, R> fun) {
        class MemCache implements Function<T, R> {
            private ConcurrentMap<T, R> cache = new ConcurrentHashMap<>();

            @Override
            public R apply(T t) {
                R result = cache.get(t);
                if (result != null) {
                    System.out.println("Cache: {" + t + "=" + result + "}");
                    return result;
                }
                result = fun.apply(t);
                cache.put(t, result);
                return result;
            }
        }
        return new MemCache();
    }
}
