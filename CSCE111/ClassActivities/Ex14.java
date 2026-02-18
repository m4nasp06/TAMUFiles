import java.util.Scanner;
public class Ex14 {
    public static void main(String[] args) {
        String sound = "";
        String color = "";

        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter a pet and type : "); 
        
        String pet = scnr.next();
        String type = scnr.next();


        if ( (pet.equals("cat")) && (type.equals("Siamese")) ) {
            sound = "meow";
            color = "brown";
        }
        else if ( (pet.equals("cat")) && (type.equals("Shorthair")) ) {
            sound = "meow";
            color = "grey";
        }
        else if (pet.equals("dog")){
            sound = "woof";
            if (type.equals("Bulldog")){
                color = "white";
            }
            else if (type.equals("Husky")){
                color = "grey";
            }
        }
        else if (pet.equals("bird")) {
            sound = "caw";
            if (type.equals("Cockatiel")) {
                color = "yellow";
            }
        }
        scnr.close();

        System.err.printf("My %s is %s and says %s\n", pet, color, sound);
    }   
}