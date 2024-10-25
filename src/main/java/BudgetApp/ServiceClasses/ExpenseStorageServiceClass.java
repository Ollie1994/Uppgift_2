// https://stackoverflow.com/questions/43117731/what-is-type-typetoken
// https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/reflect/TypeToken.html
// https://bito.ai/resources/java-localdatetime-to-string-java-explained/#5
// https://www.geeksforgeeks.org/hashmap-replacekey-value-method-in-java-with-examples/

package BudgetApp.ServiceClasses;


import BudgetApp.EnumClasses.ExpenseCategory;
import BudgetApp.InputClasses.UserInputClass;
import BudgetApp.TemplateClasses.Expense;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ExpenseStorageServiceClass {

    UserInputClass userInputClass = new UserInputClass();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    LoggedInServiceClass loggedInServiceClass = new LoggedInServiceClass();

    // inte riktigt 100 men jag tror ifall jag hade gjort som "raden under" så hade det också varit polly
    // Hashmap<String, Transaction> expenses = new Hashmap<String, Expense>();

    HashMap<String, Expense> expenses = new HashMap<String, Expense>();
    HashMap<String, Expense> expensesJson = new HashMap<String, Expense>();
    HashMap<String, Expense> allExpenses = new HashMap<String, Expense>();
    HashMap<String, Expense> allExpensesJson = new HashMap<String, Expense>();

    // jsonmapsen existerar för att ifall json filen är tom så blir hashmappen oickså null, vilket jag inte visste hur jag skulle lösa




    // typ varje gång jag gör något med en "expense" så använder jag saker från trans.
    // en exp är en trans

    //------------------ METHODS ---------------------------------------------------------------

    // jag tror att varje gång jag skapar ett nytt object så använder jag variabeln "amount" från trans vilket ajg tror är en form av polllyformism
    public void addExpenseToAllExpensesList(String userNameAndPassword, ExpenseCategory category, String date, double amount) throws IOException {
        try {
            FileReader fr = new FileReader("src/main/userSpecificFiles/" + userNameAndPassword + "/" + "allExpenses.json");
            allExpensesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Expense>>() { //hashmappenblirnullfelhanteringifalljsonfil=tom
            }.getType());
            // if/else under = felhantering ifall json filen är tom
            if (allExpensesJson == null) {
                //System.out.println("allExpensesJson = null"); // test ta bort sen
            } else {
                //System.out.println("allExpensesJson = not empty"); // test ta bort sen
                allExpenses = allExpensesJson; // ifall inte null
            }
            fr.close();
        } catch (Exception e) {
           //System.out.println("catch ?");
        }
        allExpenses.put(date, new Expense(amount, category));
        //System.out.println("You have successfully created a new expense with category " + category + " and amount " + amount);
        FileWriter fw = new FileWriter("src/main/userSpecificFiles/" + userNameAndPassword + "/" + "allExpenses.json");
        gson.toJson(allExpenses, fw);
        fw.close();
    }

    public void createExpense() throws IOException {

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();
        String expenseC = "expense";
        System.out.println("Pick a category");
        ExpenseCategory category = ExpenseCategory.valueOf(userInputClass.inputEnumCategoryChoice(expenseC));
        System.out.println("Please enter an amount: ");
        double amount = userInputClass.inputAmountChoice();
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldtmn = ldt.minusNanos(100);
        String str = "";
        String path = "";

        System.out.println("Would you like to create a custom date for the expense?(yes/no).");
        String yesNoChoice = userInputClass.inputUsernamePasswordDateChoice();

        if (yesNoChoice.equals("yes")) {
            System.out.println("Type in the custom date (yyyy-MM-dd HH:mm:ss)");
            String createdDate = userInputClass.inputUsernamePasswordDateChoice();
            str = createdDate;

            String yearSYes = str;
            String monthSYes = str;
            StringBuilder yearSb = new StringBuilder(yearSYes);
            yearSb.setLength(4);
            yearSYes = yearSb.toString();
            //System.out.println(yearSYes);

            StringBuilder monthSb = new StringBuilder(monthSYes);
            monthSb.setLength(7);
            monthSb.deleteCharAt(0);
            monthSb.deleteCharAt(0);
            monthSb.deleteCharAt(0);
            monthSb.deleteCharAt(0);
            monthSb.deleteCharAt(0);
            monthSYes = monthSb.toString();
            //System.out.println(monthSYes);
            // allt detta över är för att översätta input till "usable code" tyyyp,,,, lättare att använda kod


            System.out.println("You have created this new date: " + createdDate + ", year: " + yearSYes + ", month: " + monthSYes);

            path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSYes + "/" + monthSYes + "/" + "Expenses.json";
            File test = new File("src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSYes + "/" + monthSYes);
            test.mkdirs();

        } else {

            str = ldtmn.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String pathStr = ldt.format(DateTimeFormatter.ofPattern("yyyyMM"));

            String yearSNo = ldt.format(DateTimeFormatter.ofPattern("yyyy"));
            String monthSNo = ldt.format(DateTimeFormatter.ofPattern("MM"));


            path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSNo + "/" + monthSNo + "/" + "Expenses.json";
            File test = new File("src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSNo + "/" + monthSNo);
            test.mkdirs();
        }

        try {
            FileReader fr = new FileReader(path);
            expensesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Expense>>() {
            }.getType());
            // if/else under = felhantering ifall json filen är tom
            if (expensesJson == null) {
                //System.out.println("expensesJson = null"); // test ta bort sen
            } else {
                //System.out.println("expensesJson = not empty"); // test ta bort sen
                expenses = expensesJson;
            }
            fr.close();
        } catch (Exception e) {
            //System.out.println("catch ?");
        }


        expenses.put(str, new Expense(amount, category));
        System.out.println("You have successfully created a new expense with category " + category + " and amount " + amount);
        FileWriter fw = new FileWriter(path);
        gson.toJson(expenses, fw);
        fw.close();
        addExpenseToAllExpensesList(userNameAndPassword, category, str, amount); // lägger till expenses till en ALL expense fil.
    }


    // ------------------------------------------------------------------------------------------------------------------------------


    public boolean displayExpensesByDate(String date) throws IOException {
        /*
        System.out.println("Enter your username");
        String username = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter your password");
        String password = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter the year and month of the expenses you would like to checkout, (yyyyMM/199408)");
        String date = userInputClass.inputUsernamePasswordDateChoice();
        */

        // detta över var det som man var tvungen att göra överallt för att linka "user" till exp/incs, men nu fixas det enkelt med metoden under.
        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        String yearSYes = date;
        String monthSYes = date;
        StringBuilder yearSb = new StringBuilder(yearSYes);
        yearSb.setLength(4);
        yearSYes = yearSb.toString();
        //System.out.println(yearSYes);

        StringBuilder monthSb = new StringBuilder(monthSYes);
        monthSb.setLength(6);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSYes = monthSb.toString();
        //System.out.println(monthSYes);
        // allt detta över är för att översätta input till "usable code" tyyyp,,,, lättare att använda kod

        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSYes + "/" + monthSYes + "/" + "Expenses.json";

        boolean expensesFound = true;
        try {
            FileReader fr = new FileReader(path);
            expensesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Expense>>() {
            }.getType());
            // if/else under = felhantering ifall json filen är tom
            if (expensesJson == null) {
                //System.out.println("expensesJson = null"); // test ta bort sen
                expensesFound = false;
            } else {
                //System.out.println("expensesJson = not empty"); // test ta bort sen
                expenses = expensesJson;
            }
            for (String i : expenses.keySet()) {
                System.out.println("_______________________\nKey - " + i + "\n" + expenses.get(i));
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("No expenses found");
            expensesFound = false;
        }
        return expensesFound; // ska returna till remove expenses och ändra expenses, ifall det inte finns några  att ändra.
    }

    public boolean displayAllExpenses(boolean choice) throws IOException {

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" +  "allExpenses.json";

        boolean expensesFound = true;
        try {
            FileReader fr = new FileReader(path);
            allExpensesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Expense>>() {
            }.getType());
            // if/else under = felhantering ifall json filen är tom
            if (allExpensesJson == null) {
                //System.out.println("allExpensesJson = null"); // test ta bort sen
                expensesFound = false;
            } else {
                //System.out.println("allExpensesJson = not empty"); // test ta bort sen
                allExpenses = allExpensesJson;
            }
            if (choice) {
                for (String i : allExpenses.keySet()) {
                    System.out.println("_______________________\nKey - " + i + "\n" + allExpenses.get(i));
                }
            }
            else {
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("No expenses found");
            expensesFound = false;
        }
        return expensesFound; // ska returna till remove expenses och ändra expenses ifall det inte finns några exp
    }


    // ------------------------------------------------------------------------------------------------------------------------------


    public void deleteAnExpenseFromAllExpensesList(String date) throws IOException {

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + "allExpenses.json";

        boolean expensesFound = displayAllExpenses(false); // denna är falsk för vi vill inte visa upp lista, vi vill bara updatera listan ifall det finns att ta bort.

        if (expensesFound) {
            allExpenses.remove(date);
            FileWriter fw = new FileWriter(path);
            gson.toJson(allExpenses, fw);
            fw.close();
        } else {
            System.out.println("No expenses found");
        }
    }

    public void deleteAnExpenseByDate() throws IOException {

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();


        System.out.println("Enter the year and month of the expense you would like to remove, (yyyyMM/199408)");
        String date = userInputClass.inputUsernamePasswordDateChoice();

        String yearSYes = date;
        String monthSYes = date;
        StringBuilder yearSb = new StringBuilder(yearSYes);
        yearSb.setLength(4);
        yearSYes = yearSb.toString();
        //System.out.println(yearSYes);

        StringBuilder monthSb = new StringBuilder(monthSYes);
        monthSb.setLength(6);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSYes = monthSb.toString();
        //System.out.println(monthSYes);
        // allt detta över är för att översätta input till "usable code" tyyyp,,,, lättare att använda kod




        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSYes + "/" + monthSYes + "/" + "Expenses.json";

        boolean expensesFound = displayExpensesByDate(date); // felhantering för ifall det inte finns några expenses

        if (expensesFound) {
            System.out.println("Type in yyyy-MM-dd HH:mm:ss of expense you want to remove");
            String expenseToBeRemoved = userInputClass.inputUsernamePasswordDateChoice();
            System.out.println(expenses.get(expenseToBeRemoved) + "\n // Has been removed!");
            expenses.remove(expenseToBeRemoved);
            FileWriter fw = new FileWriter(path);
            gson.toJson(expenses, fw);
            fw.close();
            deleteAnExpenseFromAllExpensesList(expenseToBeRemoved);
        } else {
            System.out.println("No expenses found");
        }
    }


    // ------------------------------------------------------------------------------------------------------------------------------


    public void updateAnExpenseFromAllExpensesList(ExpenseCategory category, double amount, String newDate, String oldDate) throws IOException {

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + "allExpenses.json";

        boolean expensesFound = displayAllExpenses(false); // "false" bcuz - vi vill bara updatera listan inte visa något

        if (expensesFound) {
            allExpenses.remove(oldDate);
            allExpenses.put(newDate, new Expense(amount, category));
            FileWriter fw = new FileWriter(path);
            gson.toJson(allExpenses, fw);
            fw.close();
        } else {
            System.out.println("No expenses found");
        }
    }

    public void updateAnExpenseByDate() throws IOException {

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();
        String expenseC = "expense";
        System.out.println("Enter the year and month of the expense you would like to update, (yyyyMM/199408)");
        String date = userInputClass.inputUsernamePasswordDateChoice();

        String yearSYes = date;
        String monthSYes = date;
        StringBuilder yearSb = new StringBuilder(yearSYes);
        yearSb.setLength(4);
        yearSYes = yearSb.toString();
        //System.out.println(yearSYes);

        StringBuilder monthSb = new StringBuilder(monthSYes);
        monthSb.setLength(6);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSYes = monthSb.toString();
        //System.out.println(monthSYes);
        // allt detta över är för att översätta input till "usable code" tyyyp,,,, lättare att använda kod


        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSYes + "/" + monthSYes + "/" + "Expenses.json";

        boolean expensesFound = displayExpensesByDate(date);
        String oldDate = "";
        String newDate = "";

        if (expensesFound) {
            System.out.println("Type in yyyy-MM-dd HH:mm:ss of expense you want to update");
            String expenseToBeUpdated = userInputClass.inputUsernamePasswordDateChoice();
            oldDate = expenseToBeUpdated;
            newDate = oldDate;
            System.out.println("Pick a category");
            ExpenseCategory category = ExpenseCategory.valueOf(userInputClass.inputEnumCategoryChoice(expenseC)); // byt till annan tC
            System.out.println("Please enter an amount: ");
            double amount = userInputClass.inputAmountChoice();


            // allt under är bara ifall man vill ändra datumet på expensen också.

            System.out.println("Would you also like to change the date of the expense yes/no? (You can only change it within the month originally set, if you wanna change year or month (delete the expense and create a new custom one)");
            String answer = userInputClass.inputUsernamePasswordDateChoice();
            if (answer.equals("yes")) {
                System.out.println("Type in the new date - (dd HH:mm:ss)");
                String newDateMinusYearMonth = userInputClass.inputUsernamePasswordDateChoice();
                String completeDate = expenseToBeUpdated;
                StringBuilder strBTest = new StringBuilder(completeDate);
                strBTest.setLength(8);
                String yearMonthDate = strBTest.toString();
                String fullDate = yearMonthDate + newDateMinusYearMonth;
                newDate = fullDate;

                System.out.println(expenses.get(expenseToBeUpdated) + ", Date: " + expenseToBeUpdated + "\n // Has been replace with ->");
                expenses.remove(expenseToBeUpdated);
                expenses.put(fullDate, new Expense(amount, category));
                System.out.println("Updated " + expenses.get(fullDate) + ", New date: " + fullDate);
            } else {
                System.out.println("The original date is kept");
                System.out.println(expenses.get(expenseToBeUpdated) + "\n // Has been replace with ->");
                expenses.replace(expenseToBeUpdated, new Expense(amount, category));
                System.out.println("Updated " + expenses.get(expenseToBeUpdated));
            }

            FileWriter fw = new FileWriter(path);
            gson.toJson(expenses, fw);
            fw.close();

            updateAnExpenseFromAllExpensesList(category, amount, newDate, oldDate);
        }
        else {
            System.out.println("No expenses found");
        }
    }


    //------------------------------------------------------------------------------------------------------------


    public void searchForSpecificExpenseByCategoryAndAmount() throws IOException {
        String expenseC = "expense";
        System.out.println("whats the category of the expense you want to search for");
        ExpenseCategory category = ExpenseCategory.valueOf(userInputClass.inputEnumCategoryChoice(expenseC));
        System.out.println("whats the amount of the expense you want to search for");
        double amount = userInputClass.inputAmountChoice();

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" +  "allExpenses.json";
        boolean matched = false;
        try {
            FileReader fr = new FileReader(path);
            allExpensesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Expense>>() {
            }.getType());
            // if/else under = felhantering ifall json filen är tom
            if (allExpensesJson == null) {
                //System.out.println("allExpensesJson = null"); // test ta bort sen
            } else {
                //System.out.println("allExpensesJson = not empty"); // test ta bort sen
                allExpenses = allExpensesJson;
            }
            for (Expense i : allExpenses.values()) {
                if (category.equals(i.getCategory()) && amount == i.getAmount()) {
                    for (String y : allExpenses.keySet()) {
                        if (allExpenses.get(y).getCategory().equals(i.getCategory()) && allExpenses.get(y).getAmount() == i.getAmount()) {
                            System.out.println("This is the expense you searched for ");
                            System.out.println("Date: " + y + "\n" + allExpenses.get(y));
                            matched = true;
                        }
                    }
                }
            }
            if (matched == false) {
                System.out.println("The expense you searched for does not exist");
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("No expenses found");

        }
    }


    //------------------------------------------------------------------------------------------------------------


    public double calculateTotalForAllExpenses() throws IOException {

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + "allExpenses.json";
        double total = 0;

        try {
            FileReader fr = new FileReader(path);
            allExpensesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Expense>>() {
            }.getType());
            // if/else under = felhantering ifall json filen är tom
            if (allExpensesJson == null) {
                //System.out.println("allExpensesJson = null"); // test ta bort sen
            }
            else {
                //System.out.println("allExpensesJson = not empty"); // test ta bort sen
                allExpenses = allExpensesJson;
            }
            for (Expense i : allExpenses.values()) {
                total += i.getAmount();
                //System.out.println("Total: " + total); // test purpose
            }
            System.out.println("The total for all the expenses are: " + total);

            fr.close();
        } catch (Exception e) {
            System.out.println("No expenses found");
        }
        return total;
    }

    public double calculateTotalForAllExpensesByMonth(String date) throws IOException {

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        String yearSYes = date;
        String monthSYes = date;
        StringBuilder yearSb = new StringBuilder(yearSYes);
        yearSb.setLength(4);
        yearSYes = yearSb.toString();
        //System.out.println(yearSYes);

        StringBuilder monthSb = new StringBuilder(monthSYes);
        monthSb.setLength(6);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSYes = monthSb.toString();
        //System.out.println(monthSYes);
        // allt detta över är för att översätta input till "usable code" tyyyp,,,, lättare att använda kod


        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSYes + "/" + monthSYes + "/" + "Expenses.json";

        double total = 0;
        try {
            FileReader fr = new FileReader(path);
            expensesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Expense>>() {
            }.getType());
            // if/else under = felhantering ifall json filen är tom
            if (expensesJson == null) {
                //System.out.println("expensesJson = null"); // test ta bort sen

            } else {
                //System.out.println("expensesJson = not empty"); // test ta bort sen
                expenses = expensesJson;
            }
            for (Expense i : expenses.values()) {
                total += i.getAmount();
                //System.out.println("Total: " + total); // test purpose
            }
            System.out.println("The total for all the expenses are: " + total);
            fr.close();
        } catch (Exception e) {
            System.out.println("No expenses found");

        }
        return total;
    }


    //----------------------------------------------------------------------------------------------------------



}
