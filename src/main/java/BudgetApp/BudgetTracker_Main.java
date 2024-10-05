package BudgetApp;

public class BudgetTracker_Main {
    public static void main(String[] args) {
        TryCatch_Mc tryCatch_Mc = new TryCatch_Mc();
        User_Sc user_Sc = new User_Sc();
        ExpenseStorage_Sc expenseStorage_Sc = new ExpenseStorage_Sc();


        boolean loggedIn = false;
        while (loggedIn == false) {
            System.out.println("Start Menu\n1. Create a new account\n2. Login\n3. Exit");
            int startMenu = tryCatch_Mc.TryCatch1();
            switch (startMenu) {
                case 1:
                    user_Sc.createAccount();
                    break;
                case 2:
                    loggedIn = user_Sc.login();
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
            int mainMenu = tryCatch_Mc.TryCatch1(); // skicka in något här som ändra trycatchen till att tilåta flera val
            switch (mainMenu) {
                case 1 -> user_Sc.displayAccounts(); // bara för test
                case 2 -> loggedIn = false; // istället för EXIT så loopar vi runt till början igen genom att logga ut?
                case 3 -> expenseStorage_Sc.createExpense();


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
