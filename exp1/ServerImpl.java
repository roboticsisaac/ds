import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf {

    public ServerImpl() throws RemoteException {
        super();
    }

    public double Addition(double num1, double num2) throws RemoteException {
        return num1 + num2;
    }

    public double Subtraction(double num1, double num2) throws RemoteException {
        return num1 - num2;
    }

    public double Multiplication(double num1, double num2) throws RemoteException {
        return num1 * num2;
    }

    public double Division(double num1, double num2) throws RemoteException {
        if (num2 == 0) throw new RemoteException("Cannot divide by zero");
        return num1 / num2;
    }
}
