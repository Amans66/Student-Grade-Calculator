import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Student Grade Calculator!");
        System.out.println("Please follow the instructions to input your details and marks.\n");

        System.out.print("Enter your Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your Class: ");
        String studentClass = scanner.nextLine();
        System.out.print("Enter your Registration Number: ");
        String registrationNumber = scanner.nextLine();

        System.out.print("Enter the number of subjects: ");
        int numSubjects = getValidNumber(scanner);

        String[] subjects = new String[numSubjects];
        double[] marks = new double[numSubjects];

        inputSubjectNames(scanner, subjects);
        inputMarks(scanner, subjects, marks);

        double totalMarks = calculateTotalMarks(marks);
        double averagePercentage = calculateAveragePercentage(totalMarks, numSubjects);

        char grade = calculateGrade(averagePercentage);

        displayResults(name, studentClass, registrationNumber, subjects, marks, totalMarks, averagePercentage, grade);

        scanner.close();
    }

    private static int getValidNumber(Scanner scanner) {
        int number;
        while (true) {
            try {
                number = scanner.nextInt();
                if (number > 0) {
                    break;
                } else {
                    System.out.print("Number should be greater than 0. Please try again: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.next();
            }
        }
        return number;
    }

    private static void inputSubjectNames(Scanner scanner, String[] subjects) {
        scanner.nextLine();
        for (int i = 0; i < subjects.length; i++) {
            System.out.print("Enter the name of subject " + (i + 1) + ": ");
            subjects[i] = scanner.nextLine();
        }
    }

    private static void inputMarks(Scanner scanner, String[] subjects, double[] marks) {
        for (int i = 0; i < subjects.length; i++) {
            marks[i] = getValidMark(scanner, subjects[i]);
        }
    }

    private static double getValidMark(Scanner scanner, String subject) {
        double mark;
        while (true) {
            try {
                System.out.print("Enter marks obtained in " + subject + " (out of 100): ");
                mark = scanner.nextDouble();
                if (mark >= 0 && mark <= 100) {
                    break;
                } else {
                    System.out.println("Marks should be between 0 and 100. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 0 and 100.");
                scanner.next();
            }
        }
        return mark;
    }

    private static double calculateTotalMarks(double[] marks) {
        double total = 0;
        for (double mark : marks) {
            total += mark;
        }
        return total;
    }

    private static double calculateAveragePercentage(double totalMarks, int numberOfSubjects) {
        return totalMarks / numberOfSubjects;
    }

    private static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    private static void displayResults(String name, String studentClass, String registrationNumber, String[] subjects, double[] marks, double totalMarks, double averagePercentage, char grade) {
        System.out.println("\n--- Student Details ---");
        System.out.println("Name: " + name);
        System.out.println("Class: " + studentClass);
        System.out.println("Registration Number: " + registrationNumber);

        System.out.println("\n--- Marks Details ---");
        for (int i = 0; i < subjects.length; i++) {
            System.out.printf("%s: %.2f\n", subjects[i], marks[i]);
        }
        System.out.printf("\nTotal Marks: %.2f\n", totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);
    }
}
