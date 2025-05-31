import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static boolean login(Scanner sc){
        final String USERNAME = "admin";
        final String PASSWORd = "admin";

        System.out.println("Enter Username: ");
        String username = sc.nextLine();
        System.out.println("Enter Password: ");
        String password = sc.nextLine();

        if (username.equals(USERNAME) && password.equals(PASSWORd)){
            System.out.println("✅ Login Successfull!\n");
            return true;
        }else{
            System.out.println("❌ Invalid credentials. Access Denied.");
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if(!login(sc)){
            return;
        }
        while(true){
            System.out.println("===================\uD83D\uDC4B Welcome to student result management system=================");
            System.out.println("1. Add Student");
            System.out.println("2. Add Result");
            System.out.println("3. View Student Report by Roll Number");
            System.out.println("4. View all Students");
            System.out.println("5. View all Results");
            System.out.println("6. Update Student Marks");
            System.out.println("7. Delete Student and Results");
            System.out.println("8. Delete Specific Subject Result");
            System.out.println("9. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:{
                    System.out.println("Enter Roll Number : ");
                    int rollNo = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.println("Enter Department: ");
                    String dept = sc.nextLine();

                    Student student = new Student(rollNo, name, dept);
                    StudentDAO studentDAO = new StudentDAO();
                    studentDAO.addStudent(student);
                    break;
                }
                case 2:{
                    System.out.println("Enter Roll Number : ");
                    int rollNo = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Subject: ");
                    String subject = sc.nextLine();

                    System.out.println("Enter Marks: ");
                    int marks = sc.nextInt();
                    sc.nextLine();

                    Result result = new Result(rollNo, subject, marks);
                    ResultDAO.addResult(result);
                    break;
                }
                case 3:{
                    System.out.println("Enter Roll Number: ");
                    int searchRoll = sc.nextInt();
                    sc.nextLine();

                    Student s = StudentDAO.getStudentByRollNo(searchRoll);
                    if (s == null){
                        System.out.println("Student not found.");
                    }else{
                        System.out.println("\n------------📑Student Report-----------------");
                        System.out.println("Roll No : " + s.getRollNo());
                        System.out.println("Name : " + s.getName());
                        System.out.println("Department : " + s.getDepartment());
                        System.out.println(" ---------------------Subjects & Marks-------------------------");
                        List<Result> results = ResultDAO.getResultByRollNo(searchRoll);
                        if (results.isEmpty()){
                            System.out.println("No Results Found.");
                        }else {
                            int total = 0;
                            for (Result result : results) {
                                System.out.println("- " + result.getSubject() + " : " + result.getMarks());
                                total += result.getMarks();
                            }

                            //we need to calculate the average
                            int count = results.size();
                            double average = (double) total / count;
                            String grade;

                            if (average >= 90) grade = "A";
                            else if (average >= 80) grade = "B";
                            else if (average >= 70) grade = "C";
                            else if (average >= 60) grade = "D";
                            else grade = "F";

                            System.out.println("---------------------------------------------------------");
                            System.out.println("Total Marks: " + total);
                            System.out.println("Average Marks: "+ String.format("%.2f", average));
                            System.out.println("Grade: " + grade);
                        }

                    }
                    break;
                }
                case 4:
                    List<Student> studentList = StudentDAO.getAllStudents();
                    if (studentList.isEmpty()){
                        System.out.println("No Students found.");
                    }else{
                        System.out.println("All students");
                        System.out.printf("%-10s %-35s %-15s\n", "Roll No", "Student Name", "Department");
                        System.out.println("-----------------------------------------------------------");
                        for(Student student: studentList){
                            System.out.printf("%-10d %-35s %-15s\n",student.getRollNo(),student.getName(),student.getDepartment());
                        }
                    }
                    break;
                case 5:
                    List<Result> resultList = ResultDAO.getAllResults();
                    if (resultList.isEmpty()){
                        System.out.println("No Results Found.");
                    }else {
                        System.out.printf("%-10s %-20s %-10s\n", "Roll No","Subject", "Marks");
                        for( Result result: resultList){
                            System.out.printf("%-10d %-20s %-10d\n", result.getRollNo(),result.getSubject(),result.getMarks());
                        }
                        break;
                }
                case 6:
                    System.out.println("Enter Roll Number: ");
                    int rollNo = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Subject to Update: ");
                    String subject = sc.nextLine();
                    System.out.println("Enter New Marks: ");
                    int marks = sc.nextInt();

                    boolean updated = ResultDAO.updateMarks(rollNo,subject,marks);
                    if (updated){
                        System.out.println("Marks Updated Successfully.");
                    }else{
                        System.out.println("Failed to Update Marks.");
                    }
                    break;
                case 7:
                    //deleting students and results
                    System.out.println("Enter Roll Number to delete: ");
                    int rollToDelete = sc.nextInt();
                    boolean resultsDeleted = ResultDAO.deleteResultByRollNo(rollToDelete);
                    boolean studentDeleted = StudentDAO.deleteStudent(rollToDelete);
                    if (studentDeleted) System.out.println("Deleted the Student and Results");
                    else System.out.println("No Student found with Roll Number: "+ rollToDelete + "Or already deleted.");
                    break;
                case 8:
                    //deleting results
                    System.out.println("Enter Roll Number: ");
                    int rollForSubjectDelete = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Subject to Delete:");
                    String subjectToDelete = sc.nextLine();

                    boolean resultDeleted = ResultDAO.deleteResultByRollAndSubject(rollForSubjectDelete, subjectToDelete);
                    if (resultDeleted) System.out.println("Result for Subject: "+subjectToDelete+" of Roll Number: "+rollForSubjectDelete+" Deleted Successfully");
                    else System.out.println("No Result found with Roll Number: "+ rollForSubjectDelete + " and Subject Name: "+subjectToDelete);
                    break;

                case 9:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("❗Invalid Option. Try again.");
            }

        }

    }
}