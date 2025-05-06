import java.util.*;

public class Berkeley {

    static class Client {
        String name;
        long localTime;
        long offset = 0;

        Client(String name, long localTime) {
            this.name = name;
            this.localTime = localTime;
        }

        void applyOffset(long offset) {
            this.offset = offset;
            this.localTime += offset;
        }

        long getLocalTime() {
            return this.localTime;
        }
    }

    public static void main(String[] args) {
        // STEP 1: Server time
        long serverTime = System.currentTimeMillis();
        System.out.println("Server Initial Time (ms): " + serverTime);
        System.out.println("Server Date Time: " + new Date(serverTime));

        // STEP 2: Clients
        Client[] clients = new Client[3];
        clients[0] = new Client("Client-1", serverTime + 1000);  // +1 sec
        clients[1] = new Client("Client-2", serverTime - 2000);  // -2 sec
        clients[2] = new Client("Client-3", serverTime + 3000);  // +3 sec

        // STEP 3: Original times
        System.out.println("\n--- Original Times ---");
        for (int i = 0; i < clients.length; i++) {
            System.out.println(clients[i].name + " Time: " + clients[i].getLocalTime() + " | " + new Date(clients[i].getLocalTime()));
        }

        // STEP 4: Time differences
        long totalDiff = 0;
        System.out.println("\n--- Time Differences from Server ---");
        for (int i = 0; i < clients.length; i++) {
            long diff = clients[i].getLocalTime() - serverTime;
            totalDiff += diff;
            System.out.println(clients[i].name + " Difference: " + diff + " ms");
        }

        // STEP 5: Average diff
        long avgDiff = totalDiff / (clients.length + 1); // server included
        System.out.println("\nAverage Time Difference: " + avgDiff + " ms");

        // STEP 6: Final server time (adjusted)
        long finalServerTime = serverTime + avgDiff;
        System.out.println("Final Server Time: " + finalServerTime + " | " + new Date(finalServerTime));

        // STEP 7: Adjust clients
        System.out.println("\n--- Adjusting Clients ---");
        for (int i = 0; i < clients.length; i++) {
            long offset = avgDiff - (clients[i].getLocalTime() - serverTime);
            clients[i].applyOffset(offset);
            System.out.println(clients[i].name + " offset applied: " + offset + " ms");
        }

        // STEP 8: Final adjusted times
        System.out.println("\n--- Final Adjusted Times ---");
        System.out.println("Server Time: " + finalServerTime + " | " + new Date(finalServerTime));
        for (int i = 0; i < clients.length; i++) {
            System.out.println(clients[i].name + " Final Time: " + clients[i].getLocalTime() + " | " + new Date(clients[i].getLocalTime()));
        }
    }
}
