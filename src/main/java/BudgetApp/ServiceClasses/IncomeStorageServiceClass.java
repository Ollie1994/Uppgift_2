// https://stackoverflow.com/questions/43117731/what-is-type-typetoken
// https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/reflect/TypeToken.html
// https://bito.ai/resources/java-localdatetime-to-string-java-explained/#5
// https://www.geeksforgeeks.org/hashmap-replacekey-value-method-in-java-with-examples/



package BudgetApp.ServiceClasses;

import BudgetApp.EnumClasses.IncomeCategory;
import BudgetApp.InputClasses.UserInputClass;
import BudgetApp.TemplateClasses.Income;
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

public class IncomeStorageServiceClass {


    UserInputClass userInputClass = new UserInputClass();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    LoggedInServiceClass loggedInServiceClass = new LoggedInServiceClass();

    HashMap<String, Income> incomes = new HashMap<String, Income>();
    HashMap<String, Income> incomesJson = new HashMap<String, Income>();
    HashMap<String, Income> allIncomes = new HashMap<String, Income>();
    HashMap<String, Income> allIncomesJson = new HashMap<String, Income>();








    //------------------ METHODS ---------------------------------------------------------------


    public void addIncomeToAllIncomesList(String userNameAndPassword, IncomeCategory category, String date, double amount) throws IOException {
        try {
            FileReader fr = new FileReader("src/main/userSpecificFiles/" + userNameAndPassword + "/" + "allIncomes.json");
            allIncomesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Income>>() {
            }.getType());
            if (allIncomesJson == null) {
                System.out.println("allIncomesJson = null"); // test ta bort sen
            } else {
                System.out.println("allIncomesJson = not empty"); // test ta bort sen
                allIncomes = allIncomesJson;
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("catch ?");
        }
        allIncomes.put(date, new Income(amount, category));
        System.out.println("You have successfully created a new income with category " + category + " and amount " + amount);
        FileWriter fw = new FileWriter("src/main/userSpecificFiles/" + userNameAndPassword + "/" + "allIncomes.json");
        gson.toJson(allIncomes, fw);
        fw.close();
    }

    public void createIncome() throws IOException {

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        System.out.println("Pick a category");
        IncomeCategory category = IncomeCategory.valueOf(userInputClass.inputEnumCategoryChoice()); // byt till annan tC
        System.out.println("Please enter an amount: ");
        double amount = userInputClass.inputAmountChoice();
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldtmn = ldt.minusNanos(100);
        String str = "";
        String path = "";

        System.out.println("Would you like to create a custom date for the income?(yes/no).");
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
            System.out.println(yearSYes);

            StringBuilder monthSb = new StringBuilder(monthSYes);
            monthSb.setLength(7);
            monthSb.deleteCharAt(0);
            monthSb.deleteCharAt(0);
            monthSb.deleteCharAt(0);
            monthSb.deleteCharAt(0);
            monthSb.deleteCharAt(0);
            monthSYes = monthSb.toString();
            System.out.println(monthSYes);

            System.out.println("You have created this new date: " + createdDate + ", year: " + yearSYes + ", month: " + monthSYes);

            path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSYes + "/" + monthSYes + "/" + "Incomes.json";
            File test = new File("src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSYes + "/" + monthSYes);
            test.mkdirs();

        } else {

            str = ldtmn.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String pathStr = ldt.format(DateTimeFormatter.ofPattern("yyyyMM"));

            String yearSNo = ldt.format(DateTimeFormatter.ofPattern("yyyy"));
            String monthSNo = ldt.format(DateTimeFormatter.ofPattern("MM"));


            path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSNo + "/" + monthSNo + "/" + "Incomes.json";
            File test = new File("src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSNo + "/" + monthSNo);
            test.mkdirs();
        }

        try {
            FileReader fr = new FileReader(path);
            incomesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Income>>() {
            }.getType());
            if (incomesJson == null) {
                System.out.println("incomesJson = null"); // test ta bort sen
            } else {
                System.out.println("incomesJson = not empty"); // test ta bort sen
                incomes = incomesJson;
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("catch ?");
        }


        incomes.put(str, new Income(amount, category));
        System.out.println("You have successfully created a new income with category " + category + " and amount " + amount);
        FileWriter fw = new FileWriter(path);
        gson.toJson(incomes, fw);
        fw.close();
        addIncomeToAllIncomesList(userNameAndPassword, category, str, amount); // lägger till income till en ALL income fil.
    }


    // ------------------------------------------------------------------------------------------------------------------------------


    public boolean displayIncomesByDate(String date) throws IOException { // bara för testing
        /*
        System.out.println("Enter your username");
        String username = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter your password");
        String password = userInputClass.inputUsernamePasswordDateChoice();
        System.out.println("Enter the year and month of the incomes you would like to checkout, (yyyyMM/199408)");
        String date = userInputClass.inputUsernamePasswordDateChoice();
        */
        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        String yearSYes = date;
        String monthSYes = date;
        StringBuilder yearSb = new StringBuilder(yearSYes);
        yearSb.setLength(4);
        yearSYes = yearSb.toString();
        System.out.println(yearSYes);

        StringBuilder monthSb = new StringBuilder(monthSYes);
        monthSb.setLength(6);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSYes = monthSb.toString();
        System.out.println(monthSYes);

        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSYes + "/" + monthSYes + "/" + "Incomes.json";

        boolean incomesFound = true;
        try {
            FileReader fr = new FileReader(path);
            incomesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Income>>() {
            }.getType());
            if (incomesJson == null) {
                System.out.println("incomesJson = null"); // test ta bort sen
                incomesFound = false;
            } else {
                System.out.println("incomesJson = not empty"); // test ta bort sen
                incomes = incomesJson;
            }
            for (String i : incomes.keySet()) {
                System.out.println("_______________________\nKey - " + i + "\n" + incomes.get(i));
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("No incomes found");
            incomesFound = false;
        }
        return incomesFound; // ska returna till remove incomes och ändra incomes
    }

    public boolean displayAllIncomes(boolean choice) throws IOException { // bara för testing



        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" +  "allIncomes.json";

        boolean incomesFound = true;
        try {
            FileReader fr = new FileReader(path);
            allIncomesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Income>>() {
            }.getType());
            if (allIncomesJson == null) {
                System.out.println("allIncomesJson = null"); // test ta bort sen
                incomesFound = false;
            } else {
                System.out.println("allIncomesJson = not empty"); // test ta bort sen
                allIncomes = allIncomesJson;
            }
            if (choice) {
                for (String i : allIncomes.keySet()) {
                    System.out.println("_______________________\nKey - " + i + "\n" + allIncomes.get(i));
                }
            }
            else {
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("No incomes found");
            incomesFound = false;
        }
        return incomesFound; // ska returna till remove incomes och ändra incomes
    }


    // ------------------------------------------------------------------------------------------------------------------------------


    public void deleteAnIncomeFromAllIncomesList(String date) throws IOException {

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + "allIncomes.json";

        boolean incomesFound = displayAllIncomes(false);

        if (incomesFound) {
            allIncomes.remove(date);
            FileWriter fw = new FileWriter(path);
            gson.toJson(allIncomes, fw);
            fw.close();
        } else {
            System.out.println("No incomes found");
        }
    }

    public void deleteAnIncomeByDate() throws IOException {

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();


        System.out.println("Enter the year and month of the income you would like to remove, (yyyyMM/199408)");
        String date = userInputClass.inputUsernamePasswordDateChoice();

        String yearSYes = date;
        String monthSYes = date;
        StringBuilder yearSb = new StringBuilder(yearSYes);
        yearSb.setLength(4);
        yearSYes = yearSb.toString();
        System.out.println(yearSYes);

        StringBuilder monthSb = new StringBuilder(monthSYes);
        monthSb.setLength(6);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSYes = monthSb.toString();
        System.out.println(monthSYes);

        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSYes + "/" + monthSYes + "/" + "Incomes.json";

        boolean incomesFound = displayIncomesByDate(date);

        if (incomesFound) {
            System.out.println("Type in yyyy-MM-dd HH:mm:ss of income you want to remove");
            String incomeToBeRemoved = userInputClass.inputUsernamePasswordDateChoice();
            System.out.println(incomes.get(incomeToBeRemoved) + "\n // Has been removed!");
            incomes.remove(incomeToBeRemoved);
            FileWriter fw = new FileWriter(path);
            gson.toJson(incomes, fw);
            fw.close();
            deleteAnIncomeFromAllIncomesList(incomeToBeRemoved);
        } else {
            System.out.println("No incomes found");
        }
    }


    // ------------------------------------------------------------------------------------------------------------------------------


    public void updateAnIncomeFromAllIncomesList(IncomeCategory category, double amount, String newDate, String oldDate) throws IOException {

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + "allIncomes.json";

        boolean incomesFound = displayAllIncomes(false);

        if (incomesFound) {
            allIncomes.remove(oldDate);
            allIncomes.put(newDate, new Income(amount, category));
            FileWriter fw = new FileWriter(path);
            gson.toJson(allIncomes, fw);
            fw.close();
        } else {
            System.out.println("No incomes found");
        }
    }

    public void updateAnIncomeByDate() throws IOException {

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        System.out.println("Enter the year and month of the income you would like to update, (yyyyMM/199408)");
        String date = userInputClass.inputUsernamePasswordDateChoice();

        String yearSYes = date;
        String monthSYes = date;
        StringBuilder yearSb = new StringBuilder(yearSYes);
        yearSb.setLength(4);
        yearSYes = yearSb.toString();
        System.out.println(yearSYes);

        StringBuilder monthSb = new StringBuilder(monthSYes);
        monthSb.setLength(6);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSYes = monthSb.toString();
        System.out.println(monthSYes);

        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSYes + "/" + monthSYes + "/" + "Incomes.json";

        boolean incomesFound = displayIncomesByDate(date);
        String oldDate = "";
        String newDate = "";

        if (incomesFound) {
            System.out.println("Type in yyyy-MM-dd HH:mm:ss of income you want to update");
            String incomeToBeUpdated = userInputClass.inputUsernamePasswordDateChoice();
            oldDate = incomeToBeUpdated;
            newDate = oldDate;
            System.out.println("Pick a category");
            IncomeCategory category = IncomeCategory.valueOf(userInputClass.inputEnumCategoryChoice()); // byt till annan tC
            System.out.println("Please enter an amount: ");
            double amount = userInputClass.inputAmountChoice();

            System.out.println("Would you also like to change the date of the income yes/no? (You can only change it within the month originally set, if you wanna change year or month (delete the income and create a new custom one)");
            String answer = userInputClass.inputUsernamePasswordDateChoice();
            if (answer.equals("yes")) {
                System.out.println("Type in the new date - (dd HH:mm:ss)");
                String newDateMinusYearMonth = userInputClass.inputUsernamePasswordDateChoice();
                String completeDate = incomeToBeUpdated;
                StringBuilder strBTest = new StringBuilder(completeDate);
                strBTest.setLength(8);
                String yearMonthDate = strBTest.toString();
                String fullDate = yearMonthDate + newDateMinusYearMonth;
                newDate = fullDate;

                System.out.println(incomes.get(incomeToBeUpdated) + ", Date: " + incomeToBeUpdated + "\n // Has been replace with ->");
                incomes.remove(incomeToBeUpdated);
                incomes.put(fullDate, new Income(amount, category));
                System.out.println("Updated " + incomes.get(fullDate) + ", New date: " + fullDate);
            } else {
                System.out.println("The original date is kept");
                System.out.println(incomes.get(incomeToBeUpdated) + "\n // Has been replace with ->");
                incomes.replace(incomeToBeUpdated, new Income(amount, category));
                System.out.println("Updated " + incomes.get(incomeToBeUpdated));
            }

            FileWriter fw = new FileWriter(path);
            gson.toJson(incomes, fw);
            fw.close();

            updateAnIncomeFromAllIncomesList(category, amount, newDate, oldDate);
        }
        else {
            System.out.println("No incomes found");
        }
    }


    //------------------------------------------------------------------------------------------------------------


    public void searchForSpecificIncomeByCategoryAndAmount() throws IOException {

        System.out.println("whats the category of the income you want to search for");
        IncomeCategory category = IncomeCategory.valueOf(userInputClass.inputEnumCategoryChoice());
        System.out.println("whats the amount of the income you want to search for");
        double amount = userInputClass.inputAmountChoice();

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" +  "allIncomes.json";
        boolean matched = false;
        try {
            FileReader fr = new FileReader(path);
            allIncomesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Income>>() {
            }.getType());
            if (allIncomesJson == null) {
                System.out.println("allIncomesJson = null"); // test ta bort sen
            } else {
                System.out.println("allIncomesJson = not empty"); // test ta bort sen
                allIncomes = allIncomesJson;
            }
            for (Income i : allIncomes.values()) {
                if (category.equals(i.getCategory()) && amount == i.getAmount()) {
                    for (String y : allIncomes.keySet()) {
                        if (allIncomes.get(y).getCategory().equals(i.getCategory()) && allIncomes.get(y).getAmount() == i.getAmount()) {
                            System.out.println("This is the income you searched for ");
                            System.out.println("Date: " + y + "\n" + allIncomes.get(y));
                            matched = true;
                        }
                    }
                }
            }
            if (matched == false) {
                System.out.println("The income you searched for does not exist");
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("No incomes found");

        }
    }


    //----------------------------------------------------------------------------------------------------------------


    public double calculateTotalForAllIncomes() throws IOException { // bara för testing

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + "allIncomes.json";
        double total = 0;
        try {
            FileReader fr = new FileReader(path);
            allIncomesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Income>>() {
            }.getType());
            if (allIncomesJson == null) {
                System.out.println("allIncomesJson = null"); // test ta bort sen
            }
            else {
                System.out.println("allIncomesJson = not empty"); // test ta bort sen
                allIncomes = allIncomesJson;
            }
            for (Income i : allIncomes.values()) {
                total += i.getAmount();
                System.out.println("Total: " + total); // test purpose
            }
            System.out.println("The total for all the incomes are: " + total);

            fr.close();
        } catch (Exception e) {
            System.out.println("No incomes found");
        }
        return total;
    }

    public double calculateTotalForAllIncomesByMonth(String date) throws IOException {

        String userNameAndPassword = loggedInServiceClass.userCurrentlyLoggedIn();

        String yearSYes = date;
        String monthSYes = date;
        StringBuilder yearSb = new StringBuilder(yearSYes);
        yearSb.setLength(4);
        yearSYes = yearSb.toString();
        System.out.println(yearSYes);

        StringBuilder monthSb = new StringBuilder(monthSYes);
        monthSb.setLength(6);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSb.deleteCharAt(0);
        monthSYes = monthSb.toString();
        System.out.println(monthSYes);

        String path = "src/main/userSpecificFiles/" + userNameAndPassword + "/" + yearSYes + "/" + monthSYes + "/" + "Incomes.json";

        double total = 0;
        try {
            FileReader fr = new FileReader(path);
            incomesJson = new Gson().fromJson(fr, new TypeToken<HashMap<String, Income>>() {
            }.getType());
            if (incomesJson == null) {
                System.out.println("incomesJson = null"); // test ta bort sen
            } else {
                System.out.println("incomesJson = not empty"); // test ta bort sen
                incomes = incomesJson;
            }
            for (Income i : incomes.values()) {
                total += i.getAmount();
                System.out.println("Total: " + total); // test purpose
            }
            System.out.println("The total for all the incomes are: " + total);
            fr.close();
        } catch (Exception e) {
            System.out.println("No incomes found");
        }
        return total;
    }


    //---------------------------------------------------------------------------------------------------------------











}
