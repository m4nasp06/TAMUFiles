import java.util.Scanner;

public class HW2{
    // Use this scanner in all functions. Do not redefine scanner.
    public static Scanner scnr = new Scanner(System.in);

    public static void main(String[] args) {
        // This is a main program. You don't need to modify it.
        // But you can get inspiration for this for the rest of the program.
        double hws = get_homework_grade();
        double labs = get_labs_grade();
        double readings = get_readings_grade();
        double discussions = get_discussions_grade();
        double class_activities = get_activities_grade();
        double exams = get_exam_grade();
        
        System.out.println("\nYour homework score is: " + round2dp(hws));
        System.out.println("Your labs score is: " + round2dp(labs));
        System.out.println("Your readings score is: " + round2dp(readings));
        System.out.println("Your discussions score is: " + round2dp(discussions));
        System.out.println("Your class activities score is: " + round2dp(class_activities));
        System.out.println("Your exams score is: " + round2dp(exams));
        
        double total = hws + labs + discussions + exams + class_activities + readings;
        System.out.println("Your total score is: " + round2dp(total));
    }

    // Sample main function to run unit tests 
    // public static void main(String[] args) {
    //     // System.out.println(round2dp(85.456));
    //     // System.out.println(round2dp(85.454));

    //     System.out.println(getDiscussion());
    // }

    public static double round2dp(double total) {
        // TODO:    Complete the function to return the value rounded to 2 decimal places.
        //          This can be done with by multiplying by 100.0
        //          Use Math.round to round to the nearest number.
        //          Divide it by 100.0 to convert back to double.
        total *= 100;
        total = Math.round(total);
        total /= 100;
        return total;  // Replace with proper return value
    }


    public static double get_activities_grade() {
        double activity = getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity = activity + getActivity();
        activity += 3;
        activity = Math.min(27, activity);
        double percent = activity / 27;
        double scaled = percent * 5;


        
        // TODO:    Complete the function to get score for 27 activities in total.
        //          Add 3 points to the total activities score.
        //          Calculate the minimum of 27 and activities score. Use Math.min function.
        //          Scale the score to 5.00 points.

        return scaled;   // Replace with proper return value 
    }

    public static double getActivity() {
        System.out.print("Enter Activity Score: ");
        return scnr.nextDouble();
    }

    public static double get_discussions_grade() {
        // TODO:    Complete the function by using get_activities_grade as a sample.
        //          Use getDiscussion() function to read a single discussion socre.
        //          You will get 6 Discussion scores in total. That's a total of 24 points.
        //          Scale the score to 5.00 points.
        double discussion = getDiscussion();
        discussion += getDiscussion();
        discussion += getDiscussion();
        discussion += getDiscussion();
        discussion += getDiscussion();
        discussion += getDiscussion();
        double percent = discussion / 24;
        double scaled = percent * 5;
        return scaled;   // Replace with proper return value 
    }

    public static double getDiscussion() {
        // TODO:    Use getActivity function as a sample to complete this function
        //          The prompt should be "Enter Dicussion Score: " 
        System.out.print("Enter Discussion Score: ");

        return scnr.nextDouble();   // Replace with proper return value 
    }

    public static double get_labs_grade() {
        // TODO:    Complete the function to using get_activities_grade as a sample.
        //          Use getLab() function to read a single discussion socre.
        //          You will get 13 Lab scores in total. That's a total of 65 points.
        //          Scale the score to 15.00 points.
        double labs = getLab();
        labs += getLab();
        labs += getLab();
        labs += getLab();
        labs += getLab();
        labs += getLab();
        labs += getLab();
        labs += getLab();
        labs += getLab();
        labs += getLab();
        labs += getLab();
        labs += getLab();
        labs += getLab();
        double percent = labs / 65;
        double scaled = percent * 15;

        return scaled;   // Replace with proper return value 
    }

    // TODO:    Define getLab function similar to getActivity function.
    //          The prompt should "Enter Lab Score: "
    public static double getLab() {
        System.out.print("Enter Lab Score: ");
        return scnr.nextDouble();
    }


    public static double get_homework_grade() {
        // TODO:    Complete the function to using get_activities_grade as a sample.
        //          Use getHomework() function to read a single homework socre.
        //          You will get 12 Homeworks in total. That's a total of 60 points.
        //          Scale the score to 10.00 points.
        double homeworks = getHomework();
        homeworks += getHomework();
        homeworks += getHomework();
        homeworks += getHomework();
        homeworks += getHomework();
        homeworks += getHomework();
        homeworks += getHomework();
        homeworks += getHomework();
        homeworks += getHomework();
        homeworks += getHomework();
        homeworks += getHomework();
        homeworks += getHomework();
        double percent = homeworks / 60;
        double scaled = percent * 10;


        return scaled;   // Replace with proper return value 
    }

    // TODO:    Define getHomework function similar to getActivity function.
    //          The prompt should "Enter Homework Score: "
    public static double getHomework() {
        System.out.print("Enter Homework Score: ");
        return scnr.nextDouble();
    }


    public static double get_readings_grade() {
        // TODO:    Complete the function to using get_activities_grade as a sample.
        //          Use getReading() function to read a single reading socre.
        //          You will get 12 Readings in total. That's a total of 60 points.
        //          Scale the score to 5.00 points.
        double readings = getReading();
        readings += getReading();
        readings += getReading();
        readings += getReading();
        readings += getReading();
        readings += getReading();
        readings += getReading();
        readings += getReading();
        readings += getReading();
        readings += getReading();
        readings += getReading();
        readings += getReading();
        double percent = readings / 60;
        double scaled = percent * 5;

        return scaled;   // Replace with proper return value 
    }

    // TODO:    Define getReading function similar to getActivity function.
    //          The prompt should "Enter Reading Score: "
    public static double getReading() {
        System.out.print("Enter Reading Score: ");
        return scnr.nextDouble();
    }

    public static double get_exam_grade() {
        // TODO:    Prompt user for each exam score separately.
        //          The exam scores will be entered as a %age.
        //          Calculate the average score of all three exams.
        //          Replace exam1 and exam2 score with maximum of exam score and average.
        //          Use Math.max function for replacement.
        //          Scale the score to each exam to weights given in syllabus (15, 20, 25).
        //          Add them together and return.
        System.out.print("Enter Exam 1 Score: ");
        double exam1 = scnr.nextDouble();
        System.out.print("Enter Exam 2 Score: ");
        double exam2 = scnr.nextDouble();
        System.out.print("Enter Final Score: ");
        double finalExam = scnr.nextDouble();
        double avg = (exam1 + exam2 + finalExam) / 3.0;
        exam1 = Math.max(exam1, avg);
        exam2 = Math.max(exam2, avg);
        double weightedSum = (exam1 * .15) + (exam2 * .20) + (finalExam * .25);
        return weightedSum;   // Replace with proper return value 

    }


}
