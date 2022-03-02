package csulb.cecs323.app;

import csulb.cecs323.model.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class Menu {
    private Scanner scanner;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    private static final String[] allModels = {
        "TinyHome",         // 0
        "RecoveryProgram",  // 1
        "Person",           // 2
        "Community",        // 3
        "Funder",           // 4
        "Expense",          // 5
        "Employment",       // 6
        "Contribution",     // 7
        "BillType",         // 8
        "AfterStay",        // 9
        "ProgramEnrollment" // 10
    };

    /**
     * Constructor for the Main Menu program
     * @param em a reference to the already created EntityManager
     * @param tx a reference to the already created EntityTransaction
     */
    public Menu(EntityManager em, EntityTransaction tx) {
        scanner = new Scanner(System.in);
        entityManager = em;
        entityTransaction = tx;
    }

    /**
     * Displays the main menu of the program and asks the user to select
     * the actions they want to run.
     */
    public void mainMenu() {
        System.out.println("\nThis database application tracks TinyHomes, their residents, as well as the occupations they have.");
        System.out.println("Add, view data, or run queries through the menu.");

        while (true) {
            System.out.println("\nSelect option:\n" +
                    "0. Quit\n" +
                    "1. Add\n" +
                    "2. View\n" +
                    "3. Run query\n" +
                    "4. Remove RecoveryProgram\n");
            int selectedOption = scanner.nextInt();

            switch (selectedOption) {
                case 0:  // Quit
                    scanner.close();
                    return;

                case 1: // Add data
                    addDataMenu();
                    break;

                case 2: // View data
                    viewDataMenu();
                    break;

                case 3:
                    runQueriesMenu();
                    break;

                case 4:
                    removeRecoveryProgramFromUser();
                    break;
            }
        }
    }

    /**
     * Displays the menu for the user to choose which table
     * they want to add data to. Will also ask the user for input
     * in order to add the data.
     */
    private void addDataMenu() {
        int selectedOption = getSelectedModel();
        scanner.nextLine(); // Removes the new line character

        switch (selectedOption) {
            case 0:
                addTinyHomeFromUser();
                break;
            case 1:
                addRecoveryProgramFromUser();
                break;
            case 2:
                addPersonFromUser();
                break;
            case 3:
                addCommunityFromUser();
                break;
            case 4:
                addFunderFromUser();
                break;
            case 5:
                addExpenseFromUser();
                break;
            case 6:
                addEmploymentFromUser();
                break;
            case 7:
                addContributionFromUser();
                break;
            case 8:
                addBillTypeFromUser();
                break;
            case 9:
                addAfterStayFromUser();
                break;
            case 10:
                addProgramEnrollmentFromUser();
                break;
        }
    }

    /**
     * Displays the menu for the user to choose what
     * data they want to view.
     */
    private void viewDataMenu() {
        int selectedOption = getSelectedModel();

        String queryString = String.format("select s from %s s", allModels[selectedOption]);
        Query query = entityManager.createQuery(queryString);
        List resultsList;

        switch (selectedOption) {
            case 0:
                resultsList = (List<TinyHome>) query.getResultList();
                break;
            case 1:
                resultsList = (List<RecoveryProgram>) query.getResultList();
                break;
            case 2:
                resultsList = (List<Person>) query.getResultList();
                break;
            case 3:
                resultsList = (List<Community>) query.getResultList();
                break;
            case 4:
                resultsList = (List<Funder>) query.getResultList();
                break;
            case 5:
                resultsList = (List<Expense>) query.getResultList();
                break;
            case 6:
                resultsList = (List<Employment>) query.getResultList();
                break;
            case 7:
                resultsList = (List<Contribution>) query.getResultList();
                break;
            case 8:
                resultsList = (List<BillType>) query.getResultList();
                break;
            case 9:
                resultsList = (List<AfterStay>) query.getResultList();
                break;
            case 10:
                resultsList = (List<ProgramEnrollment>) query.getResultList();
                break;
            default:
                resultsList = new ArrayList();
        }

        // Print out all the results
        System.out.println();
        for (Object e: resultsList) {
            System.out.println(e.toString());
        }
        System.out.println();
    }

    /**
     * Displays the menu for the user to choose what
     * queries they want to run.
     */
    private void runQueriesMenu() {
        System.out.println("1. Gets the total expenses for each house\n" +
                "2. Provides the name(s) of funders that have financially supported communities with 50% or greater occupied homes\n" +
                "3. Provides the name(s) of the funder who donated to the largest sized community\n" +
                "4. Returns the size of the a recoveryProgram's local community\n" +
                "5. Returns the number of goals met by each community\n" +
                "6. Returns communities who's expenses are greater than their incoming contributions\n");
        int selectedChoice = scanner.nextInt();

        String queryString = "";
        String[][] dataLabels = {{"House Number", "Expenses"},
                                {"Funder Name"},
                                {"Funder Name"},
                                {"Community State", "Community City", "Program Name", "Local Program Size"},
                                {"Community State", "Community City", "Goals Met"},
                                {"Community State", "Community City", "Total Contributions", "Total Expenses"}};
        if(selectedChoice == 1) {
            queryString = String.format("SELECT t.houseNum, SUM(e.amount) FROM\n" +
                    "    (\n" +
                    "        SELECT t1 FROM TinyHome t1\n" +
                    "            LEFT JOIN Community c ON t1.COMMUNITY_Community_ID = c.Community_ID\n" +
                    "        UNION\n" +
                    "            SELECT t2 FROM TinyHome t2\n" +
                    "                RIGHT JOIN Community c ON t2.COMMUNITY_Community_ID = c.Community_ID\n" +
                    "    ) t\n" +
                    "    INNER JOIN Expense e GROUP BY houseNum");
        }
        else if(selectedChoice == 2) {
            queryString = "SELECT funder_name FROM FUNDER EXCEPT SELECT funder_name FROM FUNDER JOIN CONTRIBUTION C on FUNDER.PHONE_NUMBER = C.FUNDER_PHONE_NUMBER INNER JOIN COMMUNITY C2 on C2.COMMUNITY_ID = C.COMMUNITY_COMMUNITY_ID WHERE c2.HOMES_FILLED / c2.TOTAL_CAPACITY < 0.5";
        }
        else if(selectedChoice == 3) {
            queryString = String.format("SELECT DISTINCT a.funderName FROM\n" +
                    "    (SELECT f.funderName, C2.totalCapacity FROM Funder f\n" +
                    "        INNER JOIN Contribution C on Funder.phoneNumber = C.FUNDER_PHONE_NUMBER\n" +
                    "        INNER JOIN Community C2 on C.COMMUNITY_COMMUNITY_ID = C2.communityID) a\n" +
                    "\n" +
                    "WHERE C2.totalCapacity =\n" +
                    "      (SELECT MAX(c.Total_Capacity) FROM Community c)");
        }
        else if(selectedChoice == 4) {
            queryString = String.format("SELECT COMMUNITY_STATE, CITY, PE.PROGRAM_PROGRAM_NAME as \"Program_Name\", COUNT(PERSON_APPLICANT_ID) as \"Local_Program_Size\"\n" +
                    "FROM    TINYHOME TH INNER JOIN COMMUNITY C on TH.COMMUNITY_COMMUNITY_ID = C.COMMUNITY_ID\n" +
                    "        INNER JOIN PROGRAMENROLLMENT PE on TH.RESIDENT_APPLICANT_ID = PE.PERSON_APPLICANT_ID\n" +
                    "GROUP BY COMMUNITY_STATE, CITY, PROGRAM_PROGRAM_NAME");
        }
        else if(selectedChoice == 5) {
            queryString = String.format("SELECT COMMUNITY_STATE, CITY, COUNT(A.GOAL_MET) AS \"GOALS_MET\"\n" +
                    "FROM    TINYHOME TC INNER JOIN COMMUNITY C on TC.COMMUNITY_COMMUNITY_ID = C.COMMUNITY_ID\n" +
                    "        LEFT OUTER JOIN AFTERSTAY A ON TC.RESIDENT_APPLICANT_ID = A.PERSON_APPLICANT_ID\n" +
                    "GROUP BY COMMUNITY_STATE, CITY");
        }
        else if(selectedChoice == 6) {
            queryString = String.format("SELECT COMMUNITY_STATE, CITY, SUM(AMOUNT_FUNDED) as \"Total_Contributions\", Total_Expenses\n" +
                    "FROM    CONTRIBUTION C1 INNER JOIN COMMUNITY C2 on C1.COMMUNITY_COMMUNITY_ID = C2.COMMUNITY_ID\n" +
                    "        INNER JOIN (\n" +
                    "            SELECT COMMUNITY_COMMUNITY_ID, SUM(AMOUNT) as Total_Expenses\n" +
                    "            FROM EXPENSE\n" +
                    "            GROUP BY COMMUNITY_COMMUNITY_ID) TE\n" +
                    "        ON C2.COMMUNITY_ID = TE.COMMUNITY_COMMUNITY_ID\n" +
                    "GROUP BY COMMUNITY_STATE, CITY, Total_Expenses\n" +
                    "HAVING Total_Expenses > SUM(AMOUNT_FUNDED)\n");
        }
        else {
            System.out.println("Please select a query from 1 - 6");
        }

        List<Object[]> resultList = entityManager.createNativeQuery(queryString).getResultList();
        for (String l: dataLabels[selectedChoice-1]) {
            System.out.print(l + "\t");
        }
        System.out.println();
        for (Object[] e: resultList) {
            for (Object i: e) {
                System.out.print(i.toString() + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Displays the possible models the user can select from.
     * @return the integer of the model the user chose.
     */
    private int getSelectedModel() {
        System.out.println("\nSelect model: ");
        for (int i = 0; i < allModels.length; i++) {
            System.out.printf("%d. %s\n", i, allModels[i]);
        }
        System.out.println("");
        return scanner.nextInt();
    }

    // ================================
    // ADDING DATA MENUS
    // ================================

    /**
     * This method will add an after stay from the user.
     * <p>
     * Our main menu allows the user to insert information
     * into our database therefore this method will allow
     * that user to do that
     * <p>
     */
    private void addAfterStayFromUser() {
        String[] attributes = { "Person ID", "Date of departure (epoch time)", "Days of occupancy",
                "Goal met (true or false)", "Housing secured (true or false)" };

        for (int i = 0; i < attributes.length; i++) {
            System.out.println(attributes[i]);
            attributes[i] = scanner.nextLine();
        }

        AfterStay af = new AfterStay(
            // Person ID
            Long.parseLong(attributes[0]),
            // Date of departure
            new Timestamp(Long.parseLong(attributes[1])).toLocalDateTime().toLocalDate(),
            // Days of occupancy
            Integer.parseInt(attributes[2]),
            // Goal met
            Boolean.parseBoolean(attributes[3]),
            // Housing secured
            Boolean.parseBoolean(attributes[4]));

        entityTransaction.begin();
        entityManager.persist(af);
        entityTransaction.commit();
    }

    /**
     * This method will add a bill type from the user.
     *
     * <p>
     * Our main menu allows the user to insert information
     * into our database therefore this method will allow
     * that user to do that
     * <p>
     */
    private void addBillTypeFromUser() {
        System.out.println("Bill type");
        String bill = scanner.nextLine();

        BillType bt  = new BillType(bill);

        entityTransaction.begin();
        entityManager.persist(bt);
        entityTransaction.commit();
    }

    /**
     * This method will add a contributor from the user.
     *
     * <p>
     * Our main menu allows the user to insert information
     * into our database therefore this method will allow
     * that user to do that
     * <p>
     */
    private void addContributionFromUser() {
        String[] attributes = {
            "Funder phone number",
            "Community ID",
            "Date funded (epoch time)",
            "Amount funded"
        };

        for (int i = 0; i < attributes.length; i++) {
            System.out.println(attributes[i]);
            attributes[i] = scanner.nextLine();
        }

        Contribution c = new Contribution(
            // Funder phone number
            attributes[0],
            // Community ID
            Long.parseLong(attributes[1]),
            // Date funded
            new Timestamp(Long.parseLong(attributes[2])).toLocalDateTime().toLocalDate(),
            // Amount funded
            Double.parseDouble(attributes[3])
        );

        entityTransaction.begin();
        entityManager.persist(c);
        entityTransaction.commit();
    }

    /**
     * This method will add an employment from the user.
     *
     * <p>
     * Our main menu allows the user to insert information
     * into our database therefore this method will allow
     * that user to do that
     * <p>
     */
    private void addEmploymentFromUser() {
        String[] attributes = {
            "Person ID",
            "Type of work",
            "Hourly wage",
            "Start date (epoch time)",
            "Company"
        };

        for (int i = 0; i < attributes.length; i++) {
            System.out.println(attributes[i]);
            attributes[i] = scanner.nextLine();
        }

        Employment e = new Employment(
            // Person ID
            Long.parseLong(attributes[0]),
            // Type of work
            attributes[1],
            // Hourly wage
            Double.parseDouble(attributes[3]),
            // Start date (epoch time)
            new Timestamp(Long.parseLong(attributes[3])).toLocalDateTime().toLocalDate(),
            // Company
            attributes[4]
        );

        entityTransaction.begin();
        entityManager.persist(e);
        entityTransaction.commit();
    }

    /**
     * This method will add an expense from the user.
     *
     * <p>
     * Our main menu allows the user to insert information
     * into our database therefore this method will allow
     * that user to do that
     * <p>
     */
    private void addExpenseFromUser() {
        String[] attributes = {
            "Community ID",
            "Month (int)",
            "Billed year",
            "Type of bill",
            "Amount"
        };

        for (int i = 0; i < attributes.length; i++) {
            System.out.println(attributes[i]);
            attributes[i] = scanner.nextLine();
        }

        Expense e = new Expense(
            // Community ID
            Long.parseLong(attributes[0]),
            // Month
            Integer.parseInt(attributes[1]),
            // Billed year
            Integer.parseInt(attributes[2]),
            // Type of bill
            attributes[3],
            // Amount
            Double.parseDouble(attributes[4])
        );

        entityTransaction.begin();
        entityManager.persist(e);
        entityTransaction.commit();
    }

    /**
     * This method will add a funder from the user.
     *
     * <p>
     * Our main menu allows the user to insert information
     * into our database therefore this method will allow
     * that user to do that
     * <p>
     */
    private void addFunderFromUser() {
        String[] attributes = {
            "Phone Number",
            "Funder Name",
            "Affiliation",
            "Street Address",
            "Zip Code",
            "State"
        };

        for (int i = 0; i < attributes.length; i++) {
            System.out.println(attributes[i]);
            attributes[i] = scanner.nextLine();
        }

        Funder f = new Funder(
            // Phone Number
            attributes[0],
            // Funder Name
            attributes[1],
            // Affiliation
            attributes[2],
            // Street Address
            attributes[3],
            // Zip Code
            attributes[4],
            // State
            attributes[5]
        );

        entityTransaction.begin();
        entityManager.persist(f);
        entityTransaction.commit();
    }

    /**
     * This method will add a community from the user.
     *
     * <p>
     * Our main menu allows the user to insert information
     * into our database therefore this method will allow
     * that user to do that
     * <p>
     */
    private void addCommunityFromUser() {
        String[] attributes = {
            "Community ID",
            "State",
            "City",
            "Street Address",
            "Service Years",
            "Total Capacity",
            "Homes Filled",
            "Vacant homes",
            "Capacity Met Per Year"
        };

        for (int i = 0; i < attributes.length; i++) {
            System.out.println(attributes[i]);
            attributes[i] = scanner.nextLine();
        }

        Community l = new Community(
            // Community ID
            Long.parseLong(attributes[0]),
            // State
            attributes[1],
            // City
            attributes[2],
            // Street Address
            attributes[3],
            // Service Years
            Integer.parseInt(attributes[4]),
            // Total Capacity
            Integer.parseInt(attributes[5]),
            // Homes filled
            Integer.parseInt(attributes[6]),
            // Vacant homes
            Integer.parseInt(attributes[7]),
            // Capacity Met Per Year
            Integer.parseInt(attributes[8])
        );

        entityTransaction.begin();
        entityManager.persist(l);
        entityTransaction.commit();
    }

    /**
     * This method will add a person from the user.
     *
     * <p>
     * Our main menu allows the user to insert information
     * into our database therefore this method will allow
     * that user to do that
     * <p>
     */
    private void addPersonFromUser() {
        String[] attributes = {
            "Applicant ID",
            "First Name",
            "Last Name",
            "Date of birth (epoch time)",
            "Gender",
            "Days Without Permemnant Housing",
            "Current Housing Situation",
            "Housing Offered (true or false)",
            "Tiny House Number"
        };

        for (int i = 0; i < attributes.length; i++) {
            System.out.println(attributes[i]);
            attributes[i] = scanner.nextLine();
        }

        Person person = new Person(
            // Applicant ID
            Long.parseLong(attributes[0]),
            // First Name
            attributes[1],
            // Last Name
            attributes[2],
            // Date of birth
            new Timestamp(Long.parseLong(attributes[3])).toLocalDateTime().toLocalDate(),
            // Gender
            attributes[4],
            // Days Without Permemnant Housing
            Integer.parseInt(attributes[5]),
            // Current Housing Situation
            attributes[6],
            // Housing Offered
            Boolean.parseBoolean(attributes[7]),
            // Capacity Met Per Year
            Integer.parseInt(attributes[8])
        );

        entityTransaction.begin();
        entityManager.persist(person);
        entityTransaction.commit();
    }

    /**
     * This method will add a Program Enrollment from the user.
     *
     * <p>
     * Our main menu allows the user to insert information
     * into our database therefore this method will allow
     * that user to do that
     * <p>
     */
    private void addProgramEnrollmentFromUser() {
        String[] attributes = {
                "Applicant ID",
                "Program Name",
                "Enrollment Date"
        };

        for (int i = 0; i < attributes.length; i++) {
            System.out.println(attributes[i]);
            attributes[i] = scanner.nextLine();
        }

        ProgramEnrollment programEnrollment = new ProgramEnrollment(
                // Applicant ID
                Long.parseLong(attributes[0]),
                // Program Name
                attributes[1],
                // EnrollmentDate
                new Timestamp(Long.parseLong(attributes[2])).toLocalDateTime().toLocalDate()
        );

        entityTransaction.begin();
        entityManager.persist(programEnrollment);
        entityTransaction.commit();

    }

    /**
     * This method will add a Recovery Program from the user.
     *
     * <p>
     * Our main menu allows the user to insert information
     * into our database therefore this method will allow
     * that user to do that
     * <p>
     */
    private void addRecoveryProgramFromUser() {
        String[] attributes = {
                "Program Name"
        };

        for (int i = 0; i < attributes.length; i++) {
            System.out.println(attributes[i]);
            attributes[i] = scanner.nextLine();
        }

       RecoveryProgram recoveryProgram = new RecoveryProgram(
                // Program Name
                attributes[1]
        );

        entityTransaction.begin();
        entityManager.persist(recoveryProgram);
        entityTransaction.commit();

    }

    /**
     * This method will add a tiny home from the user.
     *
     * <p>
     * Our main menu allows the user to insert information
     * into our database therefore this method will allow
     * that user to do that
     * <p>
     */
    private void addTinyHomeFromUser() {
        String[] attributes = {
                "House Number",
                "Available",
                "Applicant ID",
                "Community"
        };

        for (int i = 0; i < attributes.length; i++) {
            System.out.println(attributes[i]);
            attributes[i] = scanner.nextLine();
        }

        TinyHome tinyHome = new TinyHome(
            // House Num
            Integer.parseInt(attributes[0]),
            // Available
            Boolean.parseBoolean(attributes[1]),
            // Applicant ID
            Long.parseLong(attributes[2]),
            // Community
            Long.parseLong(attributes[3])
        );

        entityTransaction.begin();
        entityManager.persist(tinyHome);
        entityTransaction.commit();
    }

    /**
     * This method will remove a recovery program that is specified from the user.
     */
    private void removeRecoveryProgramFromUser() {
        entityTransaction.begin();

        //retrieves the list of RecoveryPrograms from the database and saves the initial number of people enrolled
        List<RecoveryProgram> programList = (List<RecoveryProgram>) entityManager.createQuery("select rp from RecoveryProgram rp").getResultList();
        int initialEnrollment = entityManager.createQuery("select e from ProgramEnrollment e").getResultList().size();

        //exits the method if there are no RecoveryPrograms to remove
        if(programList.size() == 0) {
            System.out.println("There are currently no RecoveryPrograms in the database to remove.");
            return;
        }

        //displays a list of the current recovery programs and retrieves a program to remove from the user
        System.out.println("Select the number of the recovery program you wish to remove: ");
        for(int i=0;i<programList.size();i++) {
            System.out.println(i + ") " + programList.get(i).getProgramName());
        }
        RecoveryProgram choice = programList.get(scanner.nextInt());

        //removes the specified program from the database and removes all enrolled Persons
        entityManager.remove(choice);
        //records the final number of people enrolled in programs
        int finalEnrollment = entityManager.createQuery("select e from ProgramEnrollment e").getResultList().size();
        entityTransaction.commit();

        //outputs the results of the changes to the user
        System.out.println("As a result of the removal, one RecoveryProgram was removed and " + (initialEnrollment - finalEnrollment) + " People were unenrolled from the program.");
    }
}
