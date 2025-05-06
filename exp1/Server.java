import java.rmi.*;

public class Server {

    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            ServerImpl serverImpl = new ServerImpl();
            Naming.rebind("Server", serverImpl);
            System.out.println("Server Started....");
        } catch (Exception e) {
            System.out.println("Exception Occurred At Server! " + e);
        }
    }
}
