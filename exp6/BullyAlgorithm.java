import java.util.Scanner;

public class BullyAlgorithm {
    static boolean[] isAlive = new boolean[5];  
    static int coordinator = 5;  

    public static void bringUp(int id) {
        if (isAlive[id - 1]) {
            System.out.println("Process " + id + " is already up.");
        } else {
            isAlive[id - 1] = true;
            System.out.println("Process " + id + " is brought up.");
            if (id > coordinator) {
                startElection(id);
            }
        }
    }

    public static void bringDown(int id) {
        if (!isAlive[id - 1]) {
            System.out.println("Process " + id + " is already down.");
        } else {
            isAlive[id - 1] = false;
            System.out.println("Process " + id + " is brought down.");
            if (id == coordinator) {
                System.out.println("Coordinator " + id + " is down. Starting election...");
                for (int i = isAlive.length - 1; i >= 0; i--) {
                    if (isAlive[i]) {
                        startElection(i + 1);
                        break;
                    }
                }
            }
        }
    }

    public static void startElection(int id) {
        System.out.println("Election started by Process " + id);
        boolean foundHigher = false;
        for (int i = id + 1; i <= isAlive.length; i++) {
            if (isAlive[i - 1]) {
                System.out.println("Process " + id + " sends election message to Process " + i);
                foundHigher = true;
            }
        }
        if (!foundHigher) {
            coordinator = id;
            System.out.println("Process " + id + " becomes the new Coordinator.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; i++) isAlive[i] = true;

        int choice;
        do {
            System.out.println("\n1. Bring up a process");
            System.out.println("2. Bring down a process");
            System.out.println("3. Exit");
            System.out.println("Current Coordinator: Process " + coordinator);
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            if (choice == 1 || choice == 2) {
                System.out.print("Enter process number (1-5): ");
                int process = sc.nextInt();
                if (process < 1 || process > 5) {
                    System.out.println("Invalid process number.");
                    continue;
                }
                if (choice == 1) bringUp(process);
                else bringDown(process);
            }
        } while (choice != 3);

        sc.close();
        System.out.println("Program exited.");
    }
}
