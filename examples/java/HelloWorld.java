
import java.util.Scanner;


public class HelloWorld {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine(); //We don't handle exception or line check ;)
        System.out.println("Hello " + name + "!");
    }
}
