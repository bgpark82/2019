import java.util.Scanner;

public class BottomUp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int dp[] = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1; // 무조건 1을 뺀 경우의 수에서 + 1을 더한 값을 저장
            if (i % 2 == 0)
                dp[i] = Math.min(dp[i], dp[i / 2] + 1); // 2로 나누어 지는 경우 1을 뺀 것과 2로 나눈 후 1을 더한 값 중 더 작은 값 선택
            if (i % 3 == 0)
                dp[i] = Math.min(dp[i], dp[i / 3] + 1); // 3으로도 나누어 지는 경우 3으로 나누고 1을 더한 것과 비교해서 더 작은 것을 선택
        }
        System.out.println(dp[n]);
        sc.close();
    }
}