import java.util.Scanner;

// Student class
class Student {
    private final String name;
    private final int rollNumber;
    private final String className;

    Student(String name, int rollNumber, String className) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.className = className;
    }

    public String getName() {
        return this.name;
    }

    public int getRollNumber() {
        return this.rollNumber;
    }

    public String getClassName() {
        return this.className;
    }
}

// Subject class
class Subject {
    private final String subject;
    private final int marks;

    Subject(String subject, int marks) {
        this.subject = subject;
        this.marks = marks;
    }

    public String getSubject() {
        return this.subject;
    }

    public int getMarks() {
        return this.marks;
    }
}

// ReportCard class
class ReportCard {
    private final Student student;
    private final Subject[] subjects;

    ReportCard(Student student, Subject[] subjects) {
        this.student = student;
        this.subjects = subjects;
    }

    // Display the formatted report card
    public void displayReportCard() {
        int totalMarks = 0;

        System.out.println("\n========= REPORT CARD =========");
        System.out.printf("%-12s : %d%n", "Roll No", student.getRollNumber());
        System.out.printf("%-12s : %s%n", "Name", student.getName());
        System.out.printf("%-12s : %s%n", "Class", student.getClassName());
        System.out.println("\nSubjects:");

        for (Subject sub : subjects) {
            System.out.printf("%-12s : %d%n", sub.getSubject(), sub.getMarks());
            totalMarks += sub.getMarks();
        }

        double percentage = (double) totalMarks / subjects.length;
        String grade = calculateGrade(percentage);

        System.out.printf("%n%-12s : %d%n", "Total Marks", totalMarks);
        System.out.printf("%-12s : %.2f%%%n", "Percentage", percentage);
        System.out.printf("%-12s : %s%n", "Grade", grade);
        System.out.println("===============================\n");

    }

    private String calculateGrade(double percentage) {
        if (percentage >= 90) return "O";
        else if (percentage >= 80) return "E";
        else if (percentage >= 70) return "A";
        else if (percentage >= 60) return "B";
        else if (percentage >= 50) return "C";
        else if (percentage >= 40) return "D";
        else if (percentage <= 39) return "RE-TEST";
        else return "Fail";
    }
}

// Main driver class
class ReportCardSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int numStudents = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= numStudents; i++) {
            System.out.println("\n--- Enter details for Student " + i + " ---");

            System.out.print("Enter Roll No: ");
            int rollNo = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Class: ");
            String className = sc.nextLine();

            Student student = new Student(name, rollNo, className);

            System.out.print("Enter number of subjects: ");
            int numSubjects = sc.nextInt();
            sc.nextLine();

            Subject[] subjects = new Subject[numSubjects];

            for (int j = 0; j < numSubjects; j++) {
                System.out.print("Enter Subject " + (j + 1) + " name: ");
                String subName = sc.nextLine();

                System.out.print("Enter marks for " + subName + ": ");
                int marks = sc.nextInt();
                sc.nextLine();

                subjects[j] = new Subject(subName, marks);
            }

            // Create and display report card
            ReportCard rc = new ReportCard(student, subjects);
            rc.displayReportCard();
        }

        sc.close();
    }
}
