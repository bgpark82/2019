import java.util.Scanner;

public class B1934 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] a = new int[num];
        int[] b = new int[num];
        for (int i = 0; i < num; i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }
        for (int i = 0; i < num; i++) {
            System.out.println(a[i] * b[i] / gcd(a[i], b[i]));
        }
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
}