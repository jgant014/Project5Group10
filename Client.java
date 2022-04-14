import java.io.*;
import java.net.*;
import javax.swing.*;

public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 9999);

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        do {
            String[] identifications = { "Student", "Teacher" };
            String identification = (String) JOptionPane.showInputDialog(
                    null,
                    "Are you a Student or Teacher?",
                    "Login",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    identifications,
                    identifications[0]);
            if (identification == null) {
                break;
            }

            String[] loginOptions = { "Login", "Create an Account" };
            String loginOption = (String) JOptionPane.showInputDialog(
                    null,
                    "Would you like to login or create an account?",
                    "Login",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    loginOptions,
                    loginOptions[0]);
            if (loginOption == null) {
                break;
            }

            writer.write(identification);
            writer.println();
            writer.flush();
            writer.write(loginOption);
            writer.println();
            writer.flush();

            String username = JOptionPane.showInputDialog(null, "Enter your username.", "Username",
                    JOptionPane.QUESTION_MESSAGE);
            if (username == null) {
                break;
            } else if (username.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid username.", "Error",
                    JOptionPane.ERROR_MESSAGE);
                break;
            }
            String password = JOptionPane.showInputDialog(null, "Enter your password.", "Password",
                    JOptionPane.QUESTION_MESSAGE);
            if (password == null) {
                break;
            } else if (password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid password.", "Error",
                    JOptionPane.ERROR_MESSAGE);
                break;
            }

            writer.write(username.trim());
            writer.println();
            writer.flush();
            writer.write(password.trim());
            writer.println();
            writer.flush();

            String loginSuccess = reader.readLine();

            if (loginSuccess.equals("false")) {
                if (loginOption.equals("Login")) {
                    JOptionPane.showMessageDialog(null, "Account does not exist.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                } else if (loginOption.equals("Create an Account")) {
                    JOptionPane.showMessageDialog(null, "Account already exists.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Successfully logged in.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                if (identification.equals("Teacher")) {
                    boolean continueLoop = true;
                    do {
                        String[] teacherOptions = { "Add Course", "Delete a Course", "Create a Quiz", "Edit a Quiz", "Delete a Quiz",
                                "Upload a Quiz", "View Scores", "Edit Account", "Log Out" };
                        String teacherOption = (String) JOptionPane.showInputDialog(
                                null, "What would you like to do?", "Actions",
                                JOptionPane.QUESTION_MESSAGE, null, teacherOptions, teacherOptions[0]);
                        if (teacherOption == null) {
                            break;
                        }
                        writer.write(teacherOption);
                        writer.println();
                        writer.flush();
                        switch (teacherOption) {
                            case "Add Course":
                                String courseName = JOptionPane.showInputDialog(null, "Enter Course Name.",
                                        "Add Course",
                                        JOptionPane.QUESTION_MESSAGE);
                                if (courseName == null) {
                                    courseName = "null";
                                }
                                writer.write(courseName);
                                writer.println();
                                writer.flush();
                                String addCourseSuccess = reader.readLine();
                                if (addCourseSuccess.equals("true")) {
                                    JOptionPane.showMessageDialog(null, "Successfully added course.", "Success",
                                            JOptionPane.INFORMATION_MESSAGE);
                                } else if (addCourseSuccess.equals("false")) {
                                    JOptionPane.showMessageDialog(null, "Course already exists.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    continueLoop = false;
                                }
                                break;
                            case "Delete a Course":
                                int coursesLength = Integer.parseInt(reader.readLine());
                                String[] courses = new String[coursesLength];
                                for (int i = 0; i < coursesLength; i++) {
                                    courses[i] = reader.readLine();
                                }
                                String courseDeletionOption = (String) JOptionPane.showInputDialog(
                                        null, "Which course would you like to delete?", "Delete Course",
                                        JOptionPane.QUESTION_MESSAGE, null, courses, courses[0]);
                                if (courseDeletionOption == null) {
                                    writer.write("null");
                                    writer.println();
                                    writer.flush();
                                    continueLoop = false;
                                } else {
                                    writer.write(courseDeletionOption);
                                    writer.println();
                                    writer.flush();
                                    JOptionPane.showMessageDialog(null, "Successfully deleted course.", "Success",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                                break;
                            case "Create a Quiz":
                                break;
                            case "Edit a Quiz":
                                break;
                            case "Delete a Quiz":
                                int courseListLength = Integer.parseInt(reader.readLine());
                                String[] courseList = new String[courseListLength];
                                for (int i = 0; i < courseList.length; i++) {
                                    courseList[i] = reader.readLine();
                                }
                                String courseChoice = (String) JOptionPane.showInputDialog(
                                    null, "Which course would you like to enter?", "Course Selection",
                                    JOptionPane.QUESTION_MESSAGE, null, courseList, courseList[0]);
                                if (courseChoice == null) {
                                    writer.write("null");
                                    writer.println();
                                    writer.flush();
                                    continueLoop = false;
                                } else {
                                    writer.write(courseChoice);
                                    writer.println();
                                    writer.flush();
                                    int quizzesLength = Integer.parseInt(reader.readLine());
                                    String[] quizzesList = new String[quizzesLength];
                                    for (int i = 0; i < quizzesList.length; i++) {
                                        quizzesList[i] = reader.readLine();
                                    }
                                    String quizChoice = (String) JOptionPane.showInputDialog(
                                        null, "Which quiz would you like to delete?", "Quiz Deletion",
                                        JOptionPane.QUESTION_MESSAGE, null, quizzesList, quizzesList[0]);
                                    if (quizChoice == null) {
                                        writer.write("null");
                                        writer.println();
                                        writer.flush();
                                        continueLoop = false; 
                                    } else {
                                        writer.write(quizChoice);
                                        writer.println();
                                        writer.flush();
                                    }
                                }
                                break;
                            case "Upload a Quiz":
                                int coursesOptionsLength = Integer.parseInt(reader.readLine());
                                String[] coursesOptions = new String[coursesOptionsLength];
                                for (int i = 0; i < coursesOptions.length; i++) {
                                    coursesOptions[i] = reader.readLine();
                                }
                                String coursesOption = (String) JOptionPane.showInputDialog(
                                    null, "Which course would you like to enter?", "Course Selection",
                                    JOptionPane.QUESTION_MESSAGE, null, coursesOptions, coursesOptions[0]);
                                if (coursesOption == null) {
                                    writer.write("null");
                                    writer.println();
                                    writer.flush();
                                    continueLoop = false;
                                } else {
                                    writer.write(coursesOption);
                                    writer.println();
                                    writer.flush();
                                    String inputFilename = JOptionPane.showInputDialog(null, "Enter a valid file path.", "File Upload",
                                        JOptionPane.QUESTION_MESSAGE);
                                    if (inputFilename == null) {
                                        writer.write("null");
                                        writer.println();
                                        writer.flush();
                                        continueLoop = false;
                                    } else {
                                        writer.write(inputFilename);
                                        writer.println();
                                        writer.flush();
                                        String result = reader.readLine();
                                        if (result.equals("success")) {
                                            JOptionPane.showMessageDialog(null, "Successfully uploaded quiz.", "Success",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "File does not exist or is in the wrong format.", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                }
                                break;
                            case "View Scores":
                                int studentListLength = Integer.parseInt(reader.readLine());
                                String[] studentList = new String[studentListLength];
                                for (int i = 0; i < studentListLength; i++) {
                                    studentList[i] = reader.readLine();
                                }
                                String studentOption = (String) JOptionPane.showInputDialog(
                                    null, "Which student's quizzes would you like to view?", "Student Selection",
                                    JOptionPane.QUESTION_MESSAGE, null, studentList, studentList[0]);
                                if (studentOption == null) {
                                    writer.write("null");
                                    writer.println();
                                    writer.flush();
                                    continueLoop = false;
                                } else {
                                    writer.write(studentOption);
                                    writer.println();
                                    writer.flush();
                                    int studentQuizzesLength = Integer.parseInt(reader.readLine());
                                    String[] studentQuizzes = new String[studentQuizzesLength];
                                    for (int i = 0; i < studentQuizzes.length; i++) {
                                        studentQuizzes[i] = reader.readLine();
                                    }
                                    String quizOption = (String) JOptionPane.showInputDialog(
                                        null, "Which quiz would you like to view?", "Quiz Selection",
                                        JOptionPane.QUESTION_MESSAGE, null, studentQuizzes, studentQuizzes[0]);
                                    if (quizOption == null) {
                                        writer.write("null");
                                        writer.println();
                                        writer.flush();
                                        continueLoop = false;
                                    } else {
                                        writer.write(quizOption);
                                        writer.println();
                                        writer.flush();
                                        String quizScore = reader.readLine();
                                        JOptionPane.showMessageDialog(null, quizScore, quizOption + " Score",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                                break;
                            case "Edit Account":
                                String[] yesNoOptions = {"No", "Yes"};
                                String yesNo = (String) JOptionPane.showInputDialog(
                                    null, "Would you like to change your password?", "Edit Account",
                                    JOptionPane.QUESTION_MESSAGE, null, yesNoOptions, yesNoOptions[0]);
                                if (yesNo == null) {
                                    writer.write("null");
                                    writer.println();
                                    writer.flush();
                                } else {
                                    writer.write(yesNo);
                                    writer.println();
                                    writer.flush();
                                    if (yesNo.equals("Yes")) {
                                        String newPassword = JOptionPane.showInputDialog(null, "Enter new password.", "Edit Account",
                                            JOptionPane.QUESTION_MESSAGE);
                                        if (newPassword == null) {
                                            writer.write("null");
                                            writer.println();
                                            writer.flush(); 
                                        } else if (newPassword.trim().isEmpty()){
                                            writer.write(" ");
                                            writer.println();
                                            writer.flush();
                                            JOptionPane.showMessageDialog(null, "Invalid password.", "Error",
                                                JOptionPane.ERROR_MESSAGE); 
                                        } else {
                                            writer.write(newPassword.trim());
                                            writer.println();
                                            writer.flush();
                                            JOptionPane.showMessageDialog(null, "Password has been changed.", "Success",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    }
                                }
                                break;
                            case "Log Out":
                                continueLoop = false;
                                break;
                        }
                    } while (continueLoop);
                } else if (identification.equals("Student")) {
                    boolean continueLoop = true;
                    do {
                        String[] studentOptions = {"Take a Quiz", "View Scores", "Edit Account", "Log Out"};
                        String studentOption = (String) JOptionPane.showInputDialog(
                                null, "What would you like to do?", "Actions",
                                JOptionPane.QUESTION_MESSAGE, null, studentOptions, studentOptions[0]);
                        if (studentOption == null) {
                            break;
                        }
                        writer.write(studentOption);
                        writer.println();
                        writer.flush();
                        switch (studentOption) {
                            case "Take a Quiz":
                                int coursesLength = Integer.parseInt(reader.readLine());
                                String[] courses = new String[coursesLength];
                                for (int i = 0; i < coursesLength; i++) {
                                    courses[i] = reader.readLine();
                                }
                                String courseOption = (String) JOptionPane.showInputDialog(
                                        null, "Which course would you like to enter?", "Enter Course",
                                        JOptionPane.QUESTION_MESSAGE, null, courses, courses[0]);
                                if (courseOption == null) {
                                    writer.write("null");
                                    writer.println();
                                    writer.flush();
                                    continueLoop = false;
                                } else {
                                    writer.write(courseOption);
                                    writer.println();
                                    writer.flush();
                                    int quizzesLength = Integer.parseInt(reader.readLine());
                                    String[] quizzes = new String[coursesLength];
                                    for (int i = 0; i < quizzesLength; i++) {
                                        quizzes[i] = reader.readLine();
                                    }
                                    String quizOption = (String) JOptionPane.showInputDialog(
                                            null, "Which quiz would you like to take?", "Take Quiz",
                                            JOptionPane.QUESTION_MESSAGE, null, quizzes, quizzes[0]);
                                    if (quizOption == null) {
                                        writer.write("null");
                                        writer.println();
                                        writer.flush();
                                        continueLoop = false;
                                    } else {
                                        writer.write(quizOption);
                                        writer.println();
                                        writer.flush();
                                        int quizListLength = Integer.parseInt(reader.readLine());
                                        String[] quizList = new String[quizListLength];
                                        for (int i = 0; i < quizListLength; i++) {
                                            quizList[i] = reader.readLine();
                                        }
                                        String quizName = quizList[0];
                                        for (int i = 1; i < quizListLength; i += 6) {
                                            String question = quizList[i];
                                            String[] options = { quizList[i + 1], quizList[i + 2],
                                                    quizList[i + 3], quizList[i + 4] };
                                            String correctAnswer = quizList[i + 5];
                                            String quizListOption = (String) JOptionPane.showInputDialog(
                                                    null, question, quizName,
                                                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                                            if (quizListOption == null) {
                                                writer.write("null");
                                                writer.println();
                                                writer.flush();
                                                writer.write("null");
                                                writer.println();
                                                writer.flush();
                                                writer.write("null");
                                                writer.println();
                                                writer.flush();
                                                continueLoop = false;
                                                break;
                                            } else {
                                                writer.write(quizListOption);
                                                writer.println();
                                                writer.flush();
                                                writer.write(correctAnswer);
                                                writer.println();
                                                writer.flush();
                                                writer.write(question);
                                                writer.println();
                                                writer.flush();
                                            }
                                        }
                                        JOptionPane.showMessageDialog(null, "You have completed the quiz.",
                                            "Congratulations",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                                break;
                            case "View Scores":
                                int quizAttemptsLength = Integer.parseInt(reader.readLine());
                                String[] quizAttempts = new String[quizAttemptsLength];
                                for (int i = 0; i < quizAttemptsLength; i++) {
                                    quizAttempts[i] = reader.readLine();
                                }
                                String quizAttemptOption = (String) JOptionPane.showInputDialog(
                                        null, "Which quiz grade would you like to see?", "Pick Quiz",
                                        JOptionPane.QUESTION_MESSAGE, null, quizAttempts, quizAttempts[0]);
                                if (quizAttemptOption == null) {
                                    writer.write("null");
                                    writer.println();
                                    writer.flush();
                                    continueLoop = false;
                                } else {
                                    writer.write(quizAttemptOption);
                                    writer.println();
                                    writer.flush();
                                    String score = reader.readLine();
                                    JOptionPane.showMessageDialog(null, score, quizAttemptOption + " Score",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                                break;
                            case "Edit Account":
                                String[] yesNoOptions = {"No", "Yes"};
                                String yesNo = (String) JOptionPane.showInputDialog(
                                    null, "Would you like to change your password?", "Edit Account",
                                    JOptionPane.QUESTION_MESSAGE, null, yesNoOptions, yesNoOptions[0]);
                                if (yesNo == null) {
                                    writer.write("null");
                                    writer.println();
                                    writer.flush();
                                } else {
                                    writer.write(yesNo);
                                    writer.println();
                                    writer.flush();
                                    if (yesNo.equals("Yes")) {
                                        String newPassword = JOptionPane.showInputDialog(null, "Enter new password.", "Edit Account",
                                            JOptionPane.QUESTION_MESSAGE);
                                        if (newPassword == null) {
                                            writer.write("null");
                                            writer.println();
                                            writer.flush(); 
                                        } else if (newPassword.trim().isEmpty()){
                                            writer.write(" ");
                                            writer.println();
                                            writer.flush();
                                            JOptionPane.showMessageDialog(null, "Invalid password.", "Error",
                                                JOptionPane.ERROR_MESSAGE); 
                                        } else {
                                            writer.write(newPassword.trim());
                                            writer.println();
                                            writer.flush();
                                            JOptionPane.showMessageDialog(null, "Password has been changed.", "Success",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    }
                                }
                                break;
                            case "Log Out":
                                continueLoop = false;
                                break;
                        }
                    } while (continueLoop);
                }
            }
        } while (false);

        writer.close();
        reader.close();
        socket.close();
    }
}