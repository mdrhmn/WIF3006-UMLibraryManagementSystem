
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        // Pre-defined User initialisations
        Member member1 = new Member("Ajwad Alias", "ajwadalias", "ajwad123", "Member");
        Member member2 = new Member("Ashraf Alias", "ashrafalias", "ashraf123", "Member");
        Librarian librarian = new Librarian("Muhd Rahiman", "mdrhmn", "ray123", "Librarian");

        // Store Users in ArrayList
        ArrayList<User> users = new ArrayList<User>();
        users.add(member1);
        users.add(member2);
        users.add(librarian);

        // Menu variables declaration
        int choice;
        int menuAction;
        int bookYear;
        int bookQuantity;
        String bookTitle;
        String bookAuthor;
        boolean exitFlag = false;

        // Welcoming ASCII text banner
        String welcomeMsg
                = " __        __   _                            _____                                                 \n"
                + " \\ \\      / /__| | ___ ___  _ __ ___   ___  |_   _|__                                              \n"
                + "  \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\   | |/ _ \\                                             \n"
                + "   \\ V  V /  __/ | (_| (_) | | | | | |  __/   | | (_) |                                            \n"
                + "    \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|   |_|\\___/                                             \n"
                + "  _   _ __  __   _     _ _                                                                         \n"
                + " | | | |  \\/  | | |   (_) |__  _ __ __ _ _ __ _   _                                                \n"
                + " | | | | |\\/| | | |   | | '_ \\| '__/ _` | '__| | | |                                               \n"
                + " | |_| | |  | | | |___| | |_) | | | (_| | |  | |_| |                                               \n"
                + "  \\___/|_|  |_| |_____|_|_.__/|_|  \\__,_|_|   \\__, |                                               \n"
                + "  __  __                                      |___/        _     ____            _                 \n"
                + " |  \\/  | __ _ _ __   __ _  __ _  ___ _ __ ___   ___ _ __ | |_  / ___| _   _ ___| |_ ___ _ __ ___  \n"
                + " | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '_ ` _ \\ / _ \\ '_ \\| __| \\___ \\| | | / __| __/ _ \\ '_ ` _ \\ \n"
                + " | |  | | (_| | | | | (_| | (_| |  __/ | | | | |  __/ | | | |_   ___) | |_| \\__ \\ ||  __/ | | | | |\n"
                + " |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_| |_| |_|\\___|_| |_|\\__| |____/ \\__, |___/\\__\\___|_| |_| |_|\n"
                + "                           |___/                                       |___/                       ";

        System.out.println("#####################################################################################################");
        System.out.println(welcomeMsg);
        System.out.println("#####################################################################################################\n");

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nSelect an action: ");
            System.out.println("\n[1] Sign In");
            System.out.println("[-1] Exit");

            System.out.print("\nEnter action: ");
            menuAction = scanner.nextInt();

            switch (menuAction) {
                case 1: {
                    signIn(users);
                    break;
                }
                case -1: {
                    exitFlag = true;
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unexpected value");
            }

            if (exitFlag) {
                break;
            }

        } while (true);

    }

    private static void signIn(ArrayList<User> users) {
        int menuAction;
        boolean exitFlag = false;
        boolean loginFlag = false;
        User identifiedUser = null;
        Scanner scanner = new Scanner(System.in);

        do {

            // Set to false again for case where User exists from their own menu
            loginFlag = false;

            System.out.println("\nPlease enter your username (leave empty to exit to Main Menu):");
            String username = scanner.nextLine();

            if (username.isEmpty() || username.equals("") || username.equals(" ")) {
                exitFlag = true;
                break;
            }

            System.out.println("\nPlease enter your password: ");
            String password = scanner.nextLine();

            for (User user : users) {
                if (user.username.equals(username) && user.password.equals(password)) {
                    loginFlag = true;
                    identifiedUser = user;
                }
            }

            if (loginFlag == true) {
                switch (identifiedUser.role) {
                    case "Member": {
                        memberMenu((Member) identifiedUser);
                        break;
                    }
                    case "Librarian": {
                        librarianMenu((Librarian) identifiedUser);
                        break;
                    }
                    default:
                        throw new IllegalArgumentException("Unexpected value");
                }

            } else {
                System.out.println("\nIncorrect username and/or password!");
                System.out.println("Do you want to reattempt sign in?");
                System.out.println("[1] Yes");
                System.out.println("[-1] Exit\n");
                System.out.print("\nEnter action: ");
                menuAction = scanner.nextInt();

                scanner.nextLine();

                if (menuAction == -1) {
                    exitFlag = true;
                    break;
                }
            }
        } while (true);
    }

    private static void memberMenu(Member user) {
        Scanner scanner = new Scanner(System.in);
        boolean exitFlag = false;
        int menuAction;

        System.out.println("\n#####################################################################################################");
        System.out.println("\nWelcome, Member! (" + user.name + ")");
        do {

            System.out.println("\n#####################################################################################################");
            System.out.println("\nSelect an action: ");
            System.out.println("\n[1] Reserve Book");
            System.out.println("[2] Cancel All Reservations");
            System.out.println("[3] Cancel Reservation");
            System.out.println("[4] View Reservations");
            System.out.println("[5] View Issued Books");
            System.out.println("[6] Return Issued Books");
            System.out.println("[7] View All Books");
            System.out.println("[-1] Sign out");

            System.out.print("\nEnter action: ");
            menuAction = scanner.nextInt();

            switch (menuAction) {
                case 1: {
                    user.reserveBooks();
                    break;
                }
                case 2: {
                    user.cancelAllReservations();
                    break;
                }
                case 3: {
                    user.cancelReservations();
                    break;
                }
                case 4: {
                    user.viewReservations();
                    break;
                }
                case 5: {
                    user.viewIssuedBooks();
                    break;
                }
                case 6: {
                    user.returnIssuedBooks();
                    break;
                }
                case 7: {
                    user.viewBooks();
                    break;
                }
                case -1: {
                    System.out.println("You have signed out!");
                    exitFlag = true;
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unexpected value: " + menuAction);
            }

            if (exitFlag) {
                break;
            }

        } while (true);
    }

    private static void librarianMenu(Librarian user) {
        Scanner scanner = new Scanner(System.in);
        boolean exitFlag = false;
        int menuAction;

        System.out.println("\n#####################################################################################################");
        System.out.println("\nWelcome, Librarian! (" + user.name + ")");

        do {
            System.out.println("\n#####################################################################################################");

            System.out.println("\nSelect an action: ");
            System.out.println("[1] Issue Books");
            System.out.println("[2] View Issued Books");
            System.out.println("[3] Register New Book");
            System.out.println("[4] View All Books");
            System.out.println("[5] Update Books");
            System.out.println("[6] Delete Books");
            System.out.println("[-1] Sign out");

            System.out.print("\nEnter action: ");
            menuAction = scanner.nextInt();
            scanner.nextLine();

            switch (menuAction) {
                case 1: {
                    user.issueBooks();
                    break;
                }
                case 2: {
                    user.viewIssuedBooks();
                    break;
                }
                // TODO: Choose to update certain fields only
                case 3: {
                    System.out.println("\nEnter book title: ");
                    String bookName = scanner.nextLine();

                    System.out.println("\nEnter book author: ");
                    String bookAuthor = scanner.nextLine();

                    System.out.println("\nEnter book's publish year: ");
                    int bookYear = scanner.nextInt();

                    System.out.println("\nEnter book's quantity: ");
                    int bookQuantity = scanner.nextInt();

                    user.registerBook(bookName, bookAuthor, bookYear, bookQuantity);
                    break;
                }
                case 4: {
                    user.viewBooks();
                    break;
                }
                case 5: {
                    user.viewBooks();

                    System.out.println("\nChoose Book ID to update");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("\nEnter new title: ");
                    String bookTitle = scanner.nextLine();

                    System.out.println("\nEnter new author: ");
                    String bookAuthor = scanner.nextLine();

                    System.out.println("\nEnter new publish year: ");
                    int bookYear = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("\nEnter new quantity: ");
                    int bookQuantity = scanner.nextInt();
                    scanner.nextLine();

                    user.updateBook(choice, bookAuthor, bookTitle, bookYear, bookQuantity);
                    break;
                }
                case 6: {
                    user.viewBooks();

                    System.out.println("\nChoose Book ID to delete");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    user.deleteBook(choice);
                    break;
                }
                case -1: {
                    System.out.println("\nYou have signed out!");
                    exitFlag = true;
                    break;
                }
                default:
                    throw new IllegalArgumentException("\nUnexpected value: " + menuAction);
            }

            if (exitFlag) {
                break;
            }

        } while (true);
    }

}
