import java.util.Scanner;

/**
 * B11726 2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오. 아래 그림은 2×5 크기의
 * 직사각형을 채운 한 가지 방법의 예이다.
 */
public class B11726 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(dp(n));                             
    }

    public static int dp(int n){
        if(n == 1) return 1;
        if(n == 2) return 2;
        return dp(n-1) + 1;
        
    }
}