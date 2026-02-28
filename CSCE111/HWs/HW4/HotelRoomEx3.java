// Manas Paramathmuni
// 635002312
// Section 503

import java.util.Scanner;

public class HotelRoomEx3 {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        getInfo(scnr);
        
        scnr.close();
    }

    public static void getInfo(Scanner scnr) {
        System.out.print("Enter the starting floor: ");
        int startingFloor = scnr.nextInt();
        if (startingFloor < 3 || startingFloor > 6) {
            System.out.println("Starting floor must be between 3 and 6.");
            return;
        }
        System.out.print("Enter the total number of floors:");
        int floors = scnr.nextInt();

        int topFloor = startingFloor + floors;
        if (topFloor < 13) {
            floors -= 1;
        }

        if (topFloor > 21) {
            System.out.println("Top floor must be between 3 and 21");
            return;
        }

        System.out.print("Enter the number of rooms per floor: ");
        int roomsPerFloor = scnr.nextInt();
        if (roomsPerFloor < 1 || roomsPerFloor > 999) {
            System.out.println("Rooms per floor should be between 1 and 999");
            return;
        }
    }
    
    
}
