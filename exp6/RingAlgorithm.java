import java.util.Scanner;

public class RingAlgorithm {
    static int[] ids;
    static boolean[] active;

    public static void startElection(int start, int n) {
        int maxId = -1;
        int curr = start;
        System.out.println("Election started by Process " + ids[curr]);

        do {
            System.out.println("Message passed to Process " + ids[curr]);
            if (active[curr] && ids[curr] > maxId) {
                maxId = ids[curr];
            }
            curr = (curr + 1) % n;
        } while (curr != start);

        System.out.println("New Coordinator is Process " + maxId);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        ids = new int[n];
        active = new boolean[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter ID for process " + (i + 1) + ": ");
            ids[i] = sc.nextInt();
            active[i] = true;
        }

        int choice;
        do {
            System.out.println("\n1. Start election");
            System.out.println("2. Quit");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter index (0 to " + (n - 1) + ") to start election: ");
                int start = sc.nextInt();
                startElection(start, n);
            }

        } while (choice != 2);
        sc.close();
    }
}
