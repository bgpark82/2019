import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class B1978 {

    // https://www.deadcoderising.com/2015-05-19-java-8-replace-traditional-for-loops-with-intstreams/

    static int total = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] arr = IntStream.range(0, num).map(i -> sc.nextInt()).toArray();

        Arrays.stream(arr).forEach(i -> {
            if (isPrime(i))
                total++;

        });
        System.out.println(total);
        sc.close();
    }

    public static boolean isPrime(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0)
                count++;
        }
        return (count == 2) ? true : false;
    }
}