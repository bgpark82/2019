import java.util.Arrays;
import java.util.Scanner;

public class TopDown {

    private static int[] list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        list = new int[n + 1];
        Arrays.fill(list, -1);
        list[0] = 1;
        list[1] = 1;
        list[2] = 2;
        System.out.println(solution(n));
        sc.close();
    }

    public static int solution(int n) {
        if (list[n] != -1)
            return list[n];
        else {
            list[n] = solution(n - 1) + solution(n - 2);
            return list[n] % 10007;
        }
    }
}