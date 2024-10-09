package BudgetApp;

public class BudgetTrackerMain {
    public static void main(String[] args) {
        UserInputClass userInputClass = new UserInputClass();
        UserServiceClass userServiceClass = new UserServiceClass();
        ExpenseStorageServiceClass expenseStorageServiceClass = new ExpenseStorageServiceClass();


        boolean loggedIn = false;
        while (loggedIn == false) {
            System.out.println("Start Menu\n1. Create a new account\n2. Login\n3. Exit");
            int startMenu = userInputClass.inputStartMenuChoice();
            switch (startMenu) {
                case 1:
                    userServiceClass.createAccount();
                    break;
                case 2:
                    loggedIn = userServiceClass.login();
                    System.out.println(loggedIn); // test ta bort sen
                    break;

                //case 3 ->
            }
        }
        //while loop så länge man är inloggad

        while (loggedIn == true) {
        //MENY 2
            System.out.println("YOU ARE LOGGED IN!\nMain Menu\n1. Display all accounts (test val)\n2. EXIT!" +
            "\n3. Add an expense\n4. Add an income\n5. ");
            int mainMenu = userInputClass.inputStartMenuChoice(); // skicka in något här som ändra trycatchen till att tilåta flera val
            switch (mainMenu) {
                case 1 -> userServiceClass.displayAccounts(); // bara för test
                case 2 -> loggedIn = false; // istället för EXIT så loopar vi runt till början igen genom att logga ut?
                case 3 -> expenseStorageServiceClass.createExpense();


            }

        }
        /* I programmet ska du kunna:
         - Lägga till en eller flera utgifter och lägga till en eller flera inkomster
         - Dina inkomster och utgifter ska sparas till en fil i formatet JSON.
         - Dessa filer ska läsas in när du startar upp ditt program så att du kan använda
            din budget app över tid.
         - Du ska även kunna se alla dina inkomster respektive utgifter.
         - Du ska också kunna se hur din budget ser ut med inkomster kontra utgifter,
            alltså inkomster - utgifter.
         - Du ska även kunna ändra en utgift eller inkomst samt kunna ta bort en inkomst
            eller utgift.
        */


    }
}
