package BudgetApp;

import java.util.Scanner;

// att ha tryCatchen i en egen klass gör det mycket lättare att återanvända kod-
// och gör det lättare att hålla main så ren som möjligt.
public class TryCatch {

    public int TryCatch1() {
        int answer1 = 0;
        while (answer1 <= 0 || answer1 > 3) {
            try {
                Scanner sc = new Scanner(System.in);
                answer1 = sc.nextInt();
                if (answer1 <= 0 || answer1 > 3) {
                    System.out.println("Invalid input!");
                }
            } catch (Exception e) {System.out.println("Invalid input!");
            } finally {
            }
        }
        return answer1;
    }
    public String TryCatch2() { // polymorthism - en metod till create account - username, password  och login - usernama, password
        String input = "";
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Input please");
                input = sc.next();
            } catch (Exception e) {System.out.println("Invalid input!");
            } finally {
            }
            return input;
    }






}
