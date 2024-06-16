package evs;

import java.text.SimpleDateFormat;
import java.util.*;

public class VotingSystem {
    private static List<User> users;
    private static List<Voting> votings;

    public static void main(String[] args) {
        users = DataStorage.loadUsers();
        votings = DataStorage.loadVotings();

        users = users != null ? users : new ArrayList<>();
        votings = votings != null ? votings : new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Voting System!");
        System.out.print("1. Login\n2. Register\nSelect an option: ");
        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1:
                login(scanner);
                break;
            case 2:
                register(scanner);
                break;
            default:
                System.out.println("Invalid selection.");
        }

        scanner.close();
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter your login: ");
        String login = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User currentUser = authenticate(login, password);

        if (currentUser != null) {
            System.out.println("Login successful. Your role: " + currentUser.getRole());
            switch (currentUser.getRole()) {
                case "Admin":
                    adminMenu(scanner);
                    break;
                case "CIC":
                    cicMenu(scanner);
                    break;
                case "Candidate":
                    candidateMenu(scanner);
                    break;
                case "User":
                    userMenu(scanner);
                    break;
                default:
                    System.out.println("Unknown role: " + currentUser.getRole());
            }
        } else {
            System.out.println("Invalid login or password.");
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter your login: ");
        String login = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter your birth date (yyyy-MM-dd): ");
        String birthDate = scanner.nextLine();

        User newUser = new User(login, password, "User", fullName, birthDate);
        users.add(newUser);
        DataStorage.saveUsers(users);
        System.out.println("Registration completed. Now you can login.");
    }

    private static User authenticate(String login, String password) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    private static void adminMenu(Scanner scanner) {
        System.out.println("Admin menu:");
        System.out.println("1. View users");
        System.out.println("2. Delete user");
        System.out.println("3. View CICs");
        System.out.println("4. Delete CIC");
        System.out.println("5. Create CIC");
        System.out.println("6. View candidates");
        System.out.println("7. Delete candidate");
        System.out.print("Select an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                viewUsers();
                break;
            case 2:
                deleteUser(scanner);
                break;
            case 3:
                viewCICs();
                break;
            case 4:
                deleteCIC(scanner);
                break;
            case 5:
                createCIC(scanner);
                break;
            case 6:
                viewCandidates();
                break;
            case 7:
                deleteCandidate(scanner);
                break;
            default:
                System.out.println("Invalid selection.");
        }
    }

    private static void viewUsers() {
        users.forEach(user -> System.out.println(user.getLogin() + " - " + user.getRole()));
    }

    private static void deleteUser(Scanner scanner) {
        System.out.print("Enter the login of the user to delete: ");
        String login = scanner.nextLine();
        users.removeIf(user -> user.getLogin().equals(login));
        DataStorage.saveUsers(users);
        System.out.println("User deleted successfully.");
    }

    private static void viewCICs() {
        users.stream()
                .filter(user -> user.getRole().equals("CIC"))
                .forEach(user -> System.out.println(user.getLogin()));
    }

    private static void deleteCIC(Scanner scanner) {
        System.out.print("Enter the login of the CIC to delete: ");
        String login = scanner.nextLine();
        users.removeIf(user -> user.getLogin().equals(login) && user.getRole().equals("CIC"));
        DataStorage.saveUsers(users);
        System.out.println("CIC deleted successfully.");
    }

    private static void createCIC(Scanner scanner) {
        System.out.print("Enter the login of the new CIC: ");
        String login = scanner.nextLine();
        System.out.print("Enter the password of the new CIC: ");
        String password = scanner.nextLine();
        System.out.print("Enter the full name of the new CIC: ");
        String fullName = scanner.nextLine();

        User newCIC = new User(login, password, "CIC", fullName, "");
        users.add(newCIC);
        DataStorage.saveUsers(users);
        System.out.println("CIC created successfully.");
    }

    private static void viewCandidates() {
        users.stream()
                .filter(user -> user.getRole().equals("Candidate"))
                .forEach(user -> System.out.println(user.getLogin() + " - " + user.getFullName()));
    }

    private static void deleteCandidate(Scanner scanner) {
        System.out.print("Enter the login of the candidate to delete: ");
        String login = scanner.nextLine();
        users.removeIf(user -> user.getLogin().equals(login) && user.getRole().equals("Candidate"));
        DataStorage.saveUsers(users);
        System.out.println("Candidate deleted successfully.");
    }

    private static void cicMenu(Scanner scanner) {
        System.out.println("CIC menu:");
        System.out.println("1. View candidates");
        System.out.println("2. Vote for a candidate");
        System.out.print("Select an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                viewCandidates();
                break;
            case 2:
                voteForCandidate(scanner);
                break;
            default:
                System.out.println("Invalid selection.");
        }
    }

    private static void voteForCandidate(Scanner scanner) {
        System.out.print("Enter the login of the candidate you want to vote for: ");
        String login = scanner.nextLine();
        User candidate = users.stream()
                .filter(user -> user.getLogin().equals(login) && user.getRole().equals("Candidate"))
                .findFirst()
                .orElse(null);

        if (candidate != null) {
            Voting newVote = new Voting(candidate, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            votings.add(newVote);
            DataStorage.saveVotings(votings);
            System.out.println("Vote submitted successfully.");
        }        else {
        System.out.println("Invalid candidate login.");
    }
}

private static void candidateMenu(Scanner scanner) {
    System.out.println("Candidate menu:");
    System.out.println("1. View votes");
    System.out.println("2. View vote count");
    System.out.print("Select an option: ");
    int choice = Integer.parseInt(scanner.nextLine());

    switch (choice) {
        case 1:
            viewVotes();
            break;
        case 2:
            viewVoteCount();
            break;
        default:
            System.out.println("Invalid selection.");
    }
}

private static void viewVotes() {
    System.out.println("Votes:");
    VotingSystem.votings.forEach(vote -> System.out.println(vote.getCandidate().getFullName() + " - " + vote.getDate()));
}

private static void viewVoteCount() {
    Map<User, Integer> voteCount = new HashMap<>();
    votings.forEach(vote -> voteCount.put(vote.getCandidate(), voteCount.getOrDefault(vote.getCandidate(), 0) + 1));

    System.out.println("Vote count:");
    voteCount.forEach((candidate, count) -> System.out.println(candidate.getFullName() + " - " + count));
}

private static void userMenu(Scanner scanner) {
    System.out.println("User menu:");
    System.out.println("1. View candidates");
    System.out.print("Select an option: ");
    int choice = Integer.parseInt(scanner.nextLine());

    switch (choice) {
        case 1:
            viewCandidates();
            break;
        default:
            System.out.println("Invalid selection.");
    }
}

public void main() {
}

