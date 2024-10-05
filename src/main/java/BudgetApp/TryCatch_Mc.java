package BudgetApp;

import java.util.Scanner;

// att ha tryCatchen i en egen klass gör det mycket lättare att återanvända kod-
// och gör det lättare att hålla main så ren som möjligt.
public class TryCatch_Mc {

    public int TryCatch1() {
        int input = 0;
        while (input <= 0 || input > 3) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                if (input <= 0 || input > 3) {
                    System.out.println("Invalid input!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            } finally {
            }
        }
        return input;
    }

    public String TryCatch2() { // polymorthism - en metod till create account - username, password  och login - usernama, password
        String input = "";
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Input please");
                input = sc.next();
            } catch (Exception e) {
                System.out.println("Invalid input!");
            } finally {
            }
            return input;
    }

    public double TryCatch3() {
        double input = 0;
        while (input <= 0 || input >= Double.MAX_VALUE) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextDouble();
                if (input <= 0 || input >= Double.MAX_VALUE) {
                    System.out.println("Invalid input!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            } finally {
            }
        }
        return input;
    }

    public String TryCatch4() { // pollyshofräs eftesom den används till både expense och income
        Scanner sc = new Scanner(System.in);
        ExpenseCategory_Ec category_exCa;
        IncomeCategory_Ec category_inCa;
        String finalCategory = null;
        while (finalCategory == null) {
            try {
                System.out.println("Would you like to add an expense or income?");
                String choice = sc.next();
                switch (choice) {
                    case "expense":
                        for (ExpenseCategory_Ec category : ExpenseCategory_Ec.values()) {
                            System.out.println(category);
                        }
                        System.out.println("Please enter the expense category");
                        category_exCa = ExpenseCategory_Ec.valueOf(sc.next());
                        finalCategory = String.valueOf(category_exCa); // föreslog wrap så vi testar det
                        break;
                    case "income":
                        for (IncomeCategory_Ec category : IncomeCategory_Ec.values()) {
                            System.out.println(category);
                        }
                        System.out.println("Please enter the income category");
                        category_inCa = IncomeCategory_Ec.valueOf(sc.next());
                        finalCategory = String.valueOf(category_inCa);
                        break;
                    default:
                        System.out.println("Invalid input!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            } finally {

            }

        }
        return finalCategory;
    }






}
