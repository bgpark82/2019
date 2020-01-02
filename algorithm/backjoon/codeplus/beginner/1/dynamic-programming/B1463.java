import java.util.Scanner;

/**
 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
X가 3으로 나누어 떨어지면, 3으로 나눈다.
X가 2로 나누어 떨어지면, 2로 나눈다.
1을 뺀다.
정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.
 */
public class B1463 {

    static int[] d;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        d = new int[num+1];
        System.out.println(dp(num));
        sc.close();
    };

    public static int dp(int n){
        // 가장 작은 경우가 필요
        // 연산이 없이 구할 수 있는 값
        if(n == 1) return 0;
        // memoization
        if(d[n] > 0) return d[n];
        d[n] = dp(n-1) + 1;
        if(n%2==0){
            int temp = dp(n/2) + 1;
            if(d[n] > temp) d[n] = temp; 
        }
        if(n%3==0) {
            int temp = dp(n/3) + 1;
            if(d[n] > temp) d[n] = temp;
        }
        return d[n];   
    };
}