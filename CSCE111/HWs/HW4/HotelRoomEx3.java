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
        getInfo(scnr);
        rosterDisplay();

        scnr.close();
    }

    public static void getInfo(Scanner scnr) {
        System.out.print("Enter the starting floor: ");
        startingFloor = scnr.nextInt();
        if (startingFloor < 3 || startingFloor > 6) {
            System.out.println("Starting floor must be between 3 and 6.");
            return;
        }
        System.out.print("Enter the total number of floors:");
        floors = scnr.nextInt();

        topFloor = startingFloor + floors;
        if (topFloor < 13) {
            floors -= 1;
        }

        if (topFloor > 21) {
            System.out.println("Top floor must be between 3 and 21");
            return;
        }

        System.out.print("Enter the number of rooms per floor: ");
        roomsPerFloor = scnr.nextInt();
        if (roomsPerFloor < 1 || roomsPerFloor > 999) {
            System.out.println("Rooms per floor should be between 1 and 999");
            return;
        }
    }

    public static void rosterDisplay() {
        System.out.println("Hotel Room Cleaning Roster");
        System.out.printf("%-20s", "Room Number");
        System.out.printf("%-10s%n", "Cleaned");

        for (int i = startingFloor; i <= topFloor; i++) {
            if (i == 13) {
                continue;
            }
            for (int j = 1; j <= roomsPerFloor; j++) {
                int room;
                if (roomsPerFloor < 100) {
                    room = i * 100 + j;
                } else {
                    room = i * 1000 + j;
                }
                if (i == topFloor && j == roomsPerFloor) {
                    System.out.printf("%-20d%-10s", room, "__________");
                } else {
                    System.out.printf("%-20d%-10s%n", room, "__________");
                }
            }
            System.out.println();
        }
    }
}
