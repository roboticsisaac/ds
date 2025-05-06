import mpi.*; // Import MPJ Express package

public class DistributedSum {
    public static void main(String[] args) throws MPIException {

        // Step 1: Initialize MPI environment
        MPI.Init(args);

        // Step 2: Get rank (ID) of current process
        int rank = MPI.COMM_WORLD.Rank();

        // Step 3: Define tag and destination
        int tag = 100;
        int dest;

        if (rank == 1) {
            // This is the sending process
            dest = 0;
            int[] numberToSend = new int[1];
            numberToSend[0] = 99;

            System.out.println("This is process-" + rank);
            System.out.println("Sending number = " + numberToSend[0]);

            // Send data to process 0
            MPI.COMM_WORLD.Send(numberToSend, 0, 1, MPI.INT, dest, tag);

        } else {
            // This is the receiving process (rank 0)
            dest = 1;
            int[] numberToRecv = new int[1];

            // Receive data from process 1
            MPI.COMM_WORLD.Recv(numberToRecv, 0, 1, MPI.INT, dest, tag);

            System.out.println("This is process-" + rank);
            System.out.println("Number received from process " + dest + " is = " + numberToRecv[0]);
        }

        // Finalize the MPI environment
        MPI.Finalize();
    }
}
//for setup
//sudo apt update
//sudo apt install default-jdk
//java -version
//wget https://sourceforge.net/projects/mpjexpress/files/latest/download -O mpjexpress.zip
//unzip mpjexpress.zip -d ~/mpj

 //. Set Environment Variables
//nano ~/.bashrc
//export MPJ_HOME=$HOME/mpj
//export PATH=$MPJ_HOME/bin:$PATH
//source ~/.bashrc
//version check
//mpjrun.sh -version


//to compile
//javac -cp .:$MPJ_HOME/lib/mpj.jar DistributedSum.java

//to run commands
//mpjrun.sh -np 4 DistributedSum
