import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

//creating a class
public class FileExample {
    // Write a program that allows the user to enter in a student name and that student's grade
    // and then stores that data in a file.

    /*  User Scenario

    Bob starts the program and is presented with a prompt asking if he wants to enter student information.
    If bob selects `yes`, then the program prompts him for a student name and a student grade.
    After bob has entered the information, the program asks if he wishes to enter additional information.
    Bob will continue to enter information until he tells the program that he is done.
    When bob finishes entering information, the program writes all of the data to a file.

     */

    //declaring scanner object
    private static Scanner input = new Scanner(System.in);

    //declaring studentlist array, with students objects
    private StudentList students;
    // Next we will create a more general GradeList and use it to let our program manage both
    // StudentList and TeachList objects via polymorphism.

    //constructor for initiating studnetlist object
    public FileExample() {
        students = new StudentList();
    }

    //Getting and Storing student data
    private void processStudentGrades() {
        while(shouldContinue(askShouldContinue())) {
            StudentData data = getStudentData();

            storeStudentData(data);
        }

        writeStudentData();
    }

    //checking valid input
    private boolean shouldContinue(String userInput) {

        return userInput.toLowerCase().startsWith("y");
    }

    //writing output text file
    private void writeStudentData() {
        students.write("output.txt");
    }

    //storing the data in a studentlist
    private void storeStudentData(StudentData data) {
        students.add(data);
    }

    //exception handling
    private StudentData getStudentData() {
        StudentData data = null;
        while(true) {
            try {
                String name = getStudentName();
                String grade = getStudentGrade();

                data = new StudentData(name, grade);
                break;
            } catch (InvalidNameException ex) {
                System.out.println("Warning: your name was invalid, please double check it and try again");
            } catch (InvalidGradeException ex) {
                System.out.println("Warning: your grade was invalid, please double check it and try again");
            }
        }
        return data;
    }

    //getting input data
    private String getStudentName() {
        System.out.println("Enter the student's name:");
        return input.nextLine();
    }

    private String getStudentGrade() {
        System.out.println("Enter the student's grades:");
        return input.nextLine();
    }

    private static String askShouldContinue() {
        System.out.println("Do you wish to enter student information? (y/n):");
        return input.nextLine();
    }

    // Write a program that allows the user to read in a file of student names and grades and
    // displays that information to the console.
    //main function
    public static void main(String[] args) {
        FileExample m = new FileExample();

        m.processStudentGrades();
    }
}

