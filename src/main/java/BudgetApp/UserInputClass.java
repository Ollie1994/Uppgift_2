package BudgetApp;

import BudgetApp.EnumClasses.ExpenseCategory;
import BudgetApp.EnumClasses.IncomeCategory;

import java.util.Scanner;

// att ha tryCatchen i en egen klass gör det mycket lättare att återanvända kod-
// och gör det lättare att hålla main så ren som möjligt.
public class UserInputClass {

    public int inputStartMenuChoice() {
        int input = 0;
        while (input <= 0 || input > 4) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                if (input <= 0 || input > 4) {
                    System.out.println("Invalid input!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            } finally {
            }
        }
        return input;
    }

    public String inputUsernamePasswordChoice() {
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

    public double inputAmountChoice() {
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

    public String inputEnumCategoryChoice() {
        Scanner sc = new Scanner(System.in);
        ExpenseCategory category_exCa;
        IncomeCategory category_inCa;
        String finalCategory = null;
        while (finalCategory == null) {
            try {
                System.out.println("Would you like to add an expense or income?");
                String choice = sc.next();
                switch (choice) {
                    case "expense":
                        for (ExpenseCategory category : ExpenseCategory.values()) {
                            System.out.println(category);
                        }
                        System.out.println("Please enter the expense category");
                        category_exCa = ExpenseCategory.valueOf(sc.next());
                        finalCategory = String.valueOf(category_exCa); // föreslog wrap så vi testar det
                        break;
                    case "income":
                        for (IncomeCategory category : IncomeCategory.values()) {
                            System.out.println(category);
                        }
                        System.out.println("Please enter the income category");
                        category_inCa = IncomeCategory.valueOf(sc.next());
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
