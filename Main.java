import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
//import javax.swing.*
//import java.awt.*

class NetworkDevice {
    String deviceName;
    String ipAddress; //some kind of datatype or object
    boolean isOnline;

    // Blank default constructor so the child class can build itself
    NetworkDevice() {
    }

    // Custom Constructor for standard devices
    NetworkDevice(String name, String ip, boolean status) {
        deviceName = name;
        ipAddress = ip;
        isOnline = status;
    }

    void ping() {
        System.out.println(deviceName + " pinged. Status: " + isOnline);
    }

    void access(String f){
        //f = file
        System.out.println("File " + "\"" + f + "\" accessed by device \"" + deviceName + "\".")
    }

    void displayInfo() {
        System.out.println("Device Name: " + deviceName + "\nIP Address: " + ipAddress + "\nOnline: " + isOnline);
    }
}

    class Firewall extends NetworkDevice {
        //@Override
        //void ping(){
        //    System.out.println("Blocked");
        Firewall(String name, String ip, boolean status) {
            // Directly assigning the inherited variables!
            deviceName = name;
            ipAddress = ip;
            isOnline = status;
        }

        // Polymorphism / Overriding!
        @Override
        void ping() {
            System.out.println(deviceName + " pinged. Result: BLOCKED BY FIREWALL.");
        }

        @Override
        void access(String f){
            System.out.println(f + " access revoked.");
            System.out.println();
        }
    }


//Hello I'm here.
//AGSKJYKUDHILJWYAUSHIOIDPO:WKAGSDIYOWLA

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        NetworkDevice[] myNetwork = new NetworkDevice[5];
        int deviceCount = 0; // Keeps track of how many we've added
        // Pre-loading a few devices for our presentation
        myNetwork[deviceCount] = new NetworkDevice("Jonathan's Phone", "10.0.0.5", true);
        deviceCount++;
        myNetwork[deviceCount] = new Firewall("The Dark Lord's Server", "10.0.0.99", true);
        deviceCount++;

        int choice;

        do {
            System.out.println("\n--- SOC EVENT LOGGER ---");
            System.out.println("1. View All Devices");
            System.out.println("2. Ping Network");
            System.out.println("3. Export Logs to File");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            choice = input.nextInt();

            if (choice == 1) {
                // Loop through the array and call .displayInfo()
                for (int i = 0; i < deviceCount; i++) {
                    myNetwork[i].displayInfo();
                }
            } else if (choice == 2) {
                // Loop through the array and call .ping()
                System.out.println("\nExecuting Ping Sweep...");
                for (int i = 0; i < deviceCount; i++) {
                    myNetwork[i].ping();
                }
            } else if (choice == 3) {
                // 3. File I/O with Try-Catch (Lectures 15 & 17)
                try {
                    FileWriter writer = new FileWriter("securitylog.txt");
                    writer.write("--- OFFICIAL SOC NETWORK LOG ---\n");

                    for (int i = 0; i < deviceCount; i++) {
                        writer.write("Device: " + myNetwork[i].deviceName + " | IP: " + myNetwork[i].ipAddress + "\n");
                    }

                    writer.close();
                    System.out.println("Success: Logs exported to securitylog.txt");
                } catch (Exception e) {
                    System.out.println("Error: Could not write to file.");
                }
            }

        } while (choice != 4);

        System.out.println("Shutting down SOC terminal...");
    }
}
//        }
//        File log = new File("securitylog.txt");
//        FileWriter logWriter = new FileWriter(log);

//        NetworkDevice test = new NetworkDevice();
//        test.ipAddress;
//        test.deviceName;
//        test.ping();
//        test.displayInfo();
//    }
