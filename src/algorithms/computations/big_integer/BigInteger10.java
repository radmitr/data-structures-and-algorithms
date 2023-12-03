package algorithms.computations.big_integer;

import java.io.PrintWriter;
import java.util.Scanner;

public class BigInteger10 {
    
    public static final int BASE = 10;

    void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] d = new int[301];
        d[0] = 1;
        for (int p = 0; p < n; p++) {
            int carry = 0;
            for (int i = 0; i < d.length - 1; i++) {
                carry = carry + d[i] * 2;
                d[i] = carry % BASE;
                carry /= BASE;
            }
            if (carry != 0) {
                throw new Error();
            }
        }
        int last = d.length - 1;
        while (last > 0 && d[last] == 0) {
            last--;
        }
        for (int i = last; i >= 0; i--) {
//            out.print(d[i]);
            System.out.print(d[i]);
        }
    }

    void run() {
        try {
            Scanner in = new Scanner(System.in);
            PrintWriter out = new PrintWriter(System.out);
            solve(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BigInteger10().run();
    }
}
