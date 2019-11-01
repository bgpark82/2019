import java.util.Scanner;

public class B10430 {

    // (A+B)%C는 (A%C + B%C)%C 와 같을까?
    // (A×B)%C는 (A%C × B%C)%C 와 같을까?

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        System.out.println((a + b) % c);
        System.out.println((a % c + b % c) % c);
        System.out.println((a * b) % c);
        System.out.println((a % c * b % c) % c);
        sc.close();
    }
}