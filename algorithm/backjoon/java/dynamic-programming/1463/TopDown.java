import java.util.Arrays;
import java.util.Scanner;

public class TopDown {

    public static int N, dp[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        dp = new int[N + 1];
        Arrays.fill(dp, -1);
        dp[0] = dp[1] = 0;
        System.out.println(solution(N));
        sc.close();
    }

    public static int solution(int n) {
        System.out.println(n);
        if (dp[n] != -1)
            return dp[n];
        int min = 0x7fffffff; // int의 max 숫자를 16진수로
        if (n % 3 == 0)
            min = Math.min(min, solution(n / 3));
        if (n % 2 == 0)
            min = Math.min(min, solution(n / 2));
        min = Math.min(min, solution(n - 1));

        return dp[n] = min + 1;
    }
}