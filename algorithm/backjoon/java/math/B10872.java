import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;

public class B10872 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        // OptionalInt result = IntStream.rangeClosed(1, num).reduce((a, b) -> a * b);
        // System.out.println((result.isPresent()) ? result.getAsInt() : 1);
        System.out.println(factorial(num));
        sc.close();
    }

    public static int factorial(int num) {
        return (num == 0 || num == 1) ? 1 : factorial(num - 1) * num;
    }
}