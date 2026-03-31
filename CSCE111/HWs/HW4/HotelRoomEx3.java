// Manas Paramathmuni
// 635002312
// Section 503

import java.util.Scanner;

public class HotelRoomEx3 {

    public static Scanner scnr = new Scanner(System.in);
    public static int startingFloor = 0;
    public static int floors = 0;
    public static int topFloor = 0;
    public static int roomsPerFloor = 0;

    public static void main(String[] args) {
        boolean validInput = getInfo(scnr);
        // Only run roster display if input valid
        if (validInput) {
            rosterDisplay();
        }

        scnr.close();
    }

    public static boolean getInfo(Scanner scnr) {
        System.out.print("Enter the starting floor: ");
        startingFloor = scnr.nextInt();

        System.out.print("Enter the total number of floors: ");
        floors = scnr.nextInt();

        System.out.print("Enter the number of rooms per floor: ");
        roomsPerFloor = scnr.nextInt();

        if (startingFloor < 3 || startingFloor > 6) {
            System.out.println("Starting floor must be between 3 and 6.");
            return false;
        }

        // top Floor calculation
        int floorsCounted = 0;
        topFloor = startingFloor - 1;
        while (floorsCounted < floors) {
            topFloor++;
            if (topFloor == 13) {
                continue;
            }
            floorsCounted++;
        }

        if (topFloor > 21) {
            System.out.println("Top floor must be between 3 and 21.");
            return false;
        }

        if (roomsPerFloor < 1 || roomsPerFloor > 999) {
            System.out.println("Rooms per floor should be between 1 and 999.");
            return false;
        }

        return true;
    }

    public static void rosterDisplay() {
        System.out.println();
        System.out.println();
        System.out.println("Hotel Room Cleaning Roster");
        System.out.printf("%-20s%-10s%n", "Room Number", "Cleaned");

        int floorsPrinted = 0;
        int currentFloor = startingFloor;
        // only return as long as the num of floors is less than the top floor
        while (floorsPrinted < floors) {
            if (currentFloor == 13) {
                currentFloor++;
                continue;
            }

            for (int j = 1; j <= roomsPerFloor; j++) {
                int room;
                if (roomsPerFloor < 100) {
                    room = currentFloor * 100 + j;
                } else {
                    room = currentFloor * 1000 + j;
                }

                System.out.printf("%-20d%-10s%n", room, "__________");
            }

            System.out.println();
            floorsPrinted++;
            currentFloor++;
        }
    }
}
