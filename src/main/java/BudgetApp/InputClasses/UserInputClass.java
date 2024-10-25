package BudgetApp.InputClasses;

import BudgetApp.EnumClasses.ExpenseCategory;
import BudgetApp.EnumClasses.IncomeCategory;

import java.util.Scanner;

// att ha inputten i en egen klass gör det mycket lättare att återanvända kod-
// och gör det lättare att hålla main så ren som möjligt.
public class UserInputClass {



    public int inputStartMenuChoice() { // (userinput)start meny valet felhantering
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

    public int inputMainMenuChoice() { //(userinput)huvudmeny felhantering
        int input = 0;
        while (input <= 0 || input > 19) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                if (input <= 0 || input > 19) {
                    System.out.println("Invalid input!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            } finally {
            }
        }
        return input;
    }

    public String inputUsernamePasswordDateChoice() { // (userinput)väldigt använd för det mesta jag beöver en username pass, date  , elelr strign som svar.
        String input = "";
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Input please");
                input = sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input!");
            } finally {
            }
            return input;
    }

    public double inputAmountChoice() { //amount double user input + felhantering
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

    public String inputEnumCategoryChoice(String choice) { // (userinput)enum kategori val och felhantering
        Scanner sc = new Scanner(System.in);
        ExpenseCategory category_exCa;
        IncomeCategory category_inCa;
        String finalCategory = null;
        while (finalCategory == null) {
            try {
                // koden under skapade ett problem om man valde tex expense när man försökte skapa en inc.
                // nu istället så tar metoden emot en (String choice) istället som säger om det ska vara en exp eller inc
               // System.out.println("please type in the type of category you want (expense/income)?");
               // String choice = sc.next();
                switch (choice) {
                    case "expense":
                        for (ExpenseCategory category : ExpenseCategory.values()) {
                            System.out.println(category);
                        }
                        System.out.println("Please enter the expense category (ALLCAPITALLETTERS)");
                        category_exCa = ExpenseCategory.valueOf(sc.next());
                        finalCategory = String.valueOf(category_exCa); // föreslog wrap så vi testar det
                        break;
                    case "income":
                        for (IncomeCategory category : IncomeCategory.values()) {
                            System.out.println(category);
                        }
                        System.out.println("Please enter the income category(ALLCAPITALLETTERS)");
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
