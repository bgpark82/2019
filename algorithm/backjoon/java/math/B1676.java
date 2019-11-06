import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;

public class B1676 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float num = sc.nextFloat();
        // Optional result = IntStream.rangeClosed(1, num).reduce((a, b) -> a * b);
        // System.out.println((result.isPresent()) ? result.getAsInt() : 1);
        float result = factorial(num);
        System.out.println(result);
        // int i = 0;
        // for (; i <= Integer.toString(result).length(); i++) {
        // if (result % Math.pow(10, i) == 0)
        // continue;
        // else
        // break;
        // }
        // System.out.println(i - 1);
    }

    public static float factorial(float num) {
        return (num == 0 || num == 1) ? 1 : factorial(num - 1) * num;
    }
}