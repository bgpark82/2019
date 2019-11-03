import java.util.Scanner;

public class B2609 {

    /**
     * 최대 공약수 (GCD : Greatest Common Divisor) - 유클리드 호제법 사용 - a와 b를 나눈 나머지를 다시 b로
     * 나누었을 때 0 보다 클 때까지 반복
     * 
     * 최소 공배수 (LCM : Least Common Multiple) -
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(gcd(a, b));
        System.out.println(lcm(a, b));
        sc.close();
    }

    public static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}