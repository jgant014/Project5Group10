import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;

public class Client {

    static JTextField tf_question;
    static JTextField tf_correctOption;
    static JTextField tf_option2;
    static JTextField tf_option3;
    static JTextField tf_option4;
    static JLabel question;
    static JLabel title;
    static JLabel correctOption;
    static JLabel options2;
    static JLabel options3;
    static JLabel options4;
    static JButton btn;
    static JFrame frame;
    static String questionSub;
    static String correctAnswerSub;
    static String option2Sub;
    static String option3Sub;
    static String option4Sub;
    static CountDownLatch latch;
    static String[] qG;

    public static String[] shuffleAnswerChoice(String[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            String temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public static int fileUploadLength(String filename) {
        ArrayList<String> fileLength = new ArrayList<>();
        try {
            File file = new File(filename);
            file.createNewFile();
            FileReader fr = new FileReader(filename);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();

            while (line != null) {
                fileLength.add(line);
                line = bfr.readLine();
            }
            bfr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileLength.size();
    }

    public static ArrayList<String> getFileAnswers(String filename) {
        ArrayList<String> fileLength = new ArrayList<>();
        try {
            File file = new File(filename);
            file.createNewFile();
            FileReader fr = new FileReader(filename);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();

            while (line != null) {
                fileLength.add(line);
                line = bfr.readLine();
            }
            bfr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileLength;
    }

    public static void quizGui() {
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        frame.setSize(800, 600);
        frame.setTitle("Add Question");
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        title = new JLabel("Multiple Choice Question");
        title.setBounds(200, 50, 360, 50);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBackground(Color.BLUE);
        title.setOpaque(true);
        title.setForeground(Color.WHITE);
        title.setBorder(new EmptyBorder(0, 10, 0, 0));

        question = new JLabel("Question: ");
        question.setBounds(186, 130, 500, 50);
        question.setFont(new Font("Arial", Font.BOLD, 18));
        question.setBorder(new EmptyBorder(0, 10, 0, 0));

        btn = new JButton("Submit");
        btn.setBounds(250, 420, 250, 50);
        btn.setFocusable(false);
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        btn.setBackground(Color.RED);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn) {
                    try {
                        qG[0] = tf_question.getText();
                        qG[1] = tf_correctOption.getText();
                        qG[2] = tf_option2.getText();
                        qG[3] = tf_option3.getText();
                        qG[4] = tf_option4.getText();
                        latch.countDown();
                        frame.dispose();
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        correctOption = new JLabel("Correct Answer Choice:");
        correctOption.setBounds(62, 180, 300, 50);
        correctOption.setFont(new Font("Arial", Font.BOLD, 18));
        correctOption.setBorder(new EmptyBorder(0, 10, 0, 0));

        options2 = new JLabel("Incorrect Answer Choice:");
        options2.setBounds(50, 230, 300, 50);
        options2.setFont(new Font("Arial", Font.BOLD, 18));
        options2.setBorder(new EmptyBorder(0, 10, 0, 0));

        options3 = new JLabel("Incorrect Answer Choice:");
        options3.setBounds(50, 280, 300, 50);
        options3.setFont(new Font("Arial", Font.BOLD, 18));
        options3.setBorder(new EmptyBorder(0, 10, 0, 0));

        options4 = new JLabel("Incorrect Answer Choice:");
        options4.setBounds(50, 330, 300, 50);
        options4.setFont(new Font("Arial", Font.BOLD, 18));
        options4.setBorder(new EmptyBorder(0, 10, 0, 0));

        tf_question = new JTextField(40);
        tf_question.setFont(new Font("Arial", Font.BOLD, 16));
        tf_question.setBounds(300, 130, 300, 50);

        tf_correctOption = new JTextField(40);
        tf_correctOption.setFont(new Font("Arial", Font.BOLD, 16));
        tf_correctOption.setBounds(300, 180, 300, 50);

        tf_option2 = new JTextField(40);
        tf_option2.setFont(new Font("Arial", Font.BOLD, 16));
        tf_option2.setBounds(300, 230, 300, 50);

        tf_option3 = new JTextField(40);
        tf_option3.setFont(new Font("Arial", Font.BOLD, 16));
        tf_option3.setBounds(300, 280, 300, 50);

        tf_option4 = new JTextField(40);
        tf_option4.setFont(new Font("Arial", Font.BOLD, 16));
        tf_option4.setBounds(300, 330, 300, 50);

        frame.add(title);
        frame.add(question);
        frame.add(tf_question);
        frame.add(tf_correctOption);
        frame.add(tf_option2);
        frame.add(tf_option3);
        frame.add(tf_option4);
        frame.add(btn);
        frame.add(correctOption);
        frame.add(options2);
        frame.add(options3);
        frame.add(options4);
        frame.setVisible(true);
        // SwingUtilities.invokeLater((Runnable) new MyFrame());
    }

    public static void main(String[] args)
            throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        qG = new String[5];
        Socket socket = new Socket("localhost", 9999);

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        String identification;
        String loginOption;
        String username;
        String password;

        do {
            String[] identifications = { "Student", "Teacher" };
            identification = (String) JOptionPane.showInputDialog(
                    null,
                    "Are you a Student or Teacher?",
                    "Login",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    identifications,
                    identifications[0]);
            if (identification == null) {
                writer.write("null");
                writer.println();
                writer.flush();
                break;
            } else {
                writer.write(identification);
                writer.println();
                writer.flush();
                String[] loginOptions = { "Login", "Create an Account" };
                loginOption = (String) JOptionPane.showInputDialog(
                        null,
                        "Would you like to login or create an account?",
                        "Login",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        loginOptions,
                        loginOptions[0]);
                if (loginOption == null) {
                    writer.write("null");
                    writer.println();
                    writer.flush();
                    break;
                } else {
                    writer.write(loginOption);
                    writer.println();
                    writer.flush();
                    username = JOptionPane.showInputDialog(null, "Enter your username.", "Username",
                            JOptionPane.QUESTION_MESSAGE);
                    if (username == null) {
                        writer.write("null");
                        writer.println();
                        writer.flush();
                        break;
                    } else if (username.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Invalid username.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        writer.write("null");
                        writer.println();
                        writer.flush();
                        break;
                    } else {
                        writer.write(username.trim());
                        writer.println();
                        writer.flush();
                        password = JOptionPane.showInputDialog(null, "Enter your password.", "Password",
                                JOptionPane.QUESTION_MESSAGE);
                        if (password == null) {
                            writer.write("null");
                            writer.println();
                            writer.flush();
                            break;
                        } else if (password.trim().isEmpty()) {
                            writer.write("null");
                            writer.println();
                            writer.flush();
                            JOptionPane.showMessageDialog(null, "Invalid password.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            break;
                        } else {
                            writer.write(password.trim());
                            writer.println();
                            writer.flush();
                        }
                    }
                }
            }

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
                        String[] teacherOptions = { "Add Course", "Delete a Course", "Create a Quiz", "Edit a Quiz",
                                "Delete a Quiz",
                                "Upload a Quiz", "View Scores", "Edit Account", "Delete Account", "Log Out" };
                        String teacherOption = (String) JOptionPane.showInputDialog(
                                null, "What would you like to do?", "Actions",
                                JOptionPane.QUESTION_MESSAGE, null, teacherOptions, teacherOptions[0]);
                        if (teacherOption == null) {
                            writer.write("null");
                            writer.println();
                            writer.flush();
                            continueLoop = false;
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
                                String duplicated = reader.readLine();
                                if (duplicated.equals("duplicate")) {
                                    JOptionPane.showMessageDialog(null, "Course already exists.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
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
                                if (coursesLength == 0) {
                                    JOptionPane.showMessageDialog(null, "No available courses.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
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
                                int coursesInt = Integer.parseInt(reader.readLine());
                                if (coursesInt == 0) {
                                    JOptionPane.showMessageDialog(null, "No available courses.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                                String[] courseChoices = new String[coursesInt];
                                for (int i = 0; i < coursesInt; i++) {
                                    courseChoices[i] = reader.readLine();
                                }
                                String courseOption = (String) JOptionPane.showInputDialog(
                                        null, "In which course would you like to create this quiz?", "Course Choice",
                                        JOptionPane.QUESTION_MESSAGE, null, courseChoices, courseChoices[0]);
                                if (courseOption == null) {
                                    writer.write("null");
                                    writer.println();
                                    writer.flush();
                                    continueLoop = false;
                                } else {
                                    writer.write(courseOption);
                                    writer.println();
                                    writer.flush();
                                    String quizTitle = JOptionPane.showInputDialog(null, "Enter the quiz title.",
                                            "Quiz Creation",
                                            JOptionPane.QUESTION_MESSAGE);
                                    if (quizTitle == null) {
                                        writer.write("null");
                                        writer.println();
                                        writer.flush();
                                        continueLoop = false;
                                    } else {
                                        writer.write(quizTitle);
                                        writer.println();
                                        writer.flush();
                                        String duplicate = reader.readLine();
                                        if (duplicate.equals("duplicate")) {
                                            JOptionPane.showMessageDialog(null, "Quiz already exists.", "Error",
                                                    JOptionPane.ERROR_MESSAGE);
                                            break;
                                        }
                                        latch = new CountDownLatch(1);
                                        quizGui();
                                        latch.await();
                                        String question = qG[0];
                                        String correctAns = qG[1];
                                        String ans2 = qG[2];
                                        String ans3 = qG[3];
                                        String ans4 = qG[4];
                                        if (question.trim().length() == 0 ||
                                                correctAns.trim().length() == 0 ||
                                                ans2.trim().length() == 0 ||
                                                ans3.trim().length() == 0 ||
                                                ans4.trim().length() == 0) {
                                            writer.println("null");
                                            writer.println("null");
                                            writer.println("null");
                                            writer.println("null");
                                            writer.println("null");
                                            writer.flush();
                                            JOptionPane.showMessageDialog(null, "Invalid entry.", "Error",
                                                    JOptionPane.ERROR_MESSAGE);
                                        } else {
                                            writer.println(question);
                                            writer.println(correctAns);
                                            writer.println(ans2);
                                            writer.println(ans3);
                                            writer.println(ans4);
                                            writer.flush();
                                            String[] yesNoOption = { "No", "Yes" };
                                            String yN = (String) JOptionPane.showInputDialog(
                                                    null, "Would you like to make quiz questions randomized?",
                                                    "Randomize Option",
                                                    JOptionPane.QUESTION_MESSAGE, null, yesNoOption, yesNoOption[0]);
                                            if (yN == null) {
                                                writer.write("null");
                                                writer.println();
                                                writer.flush();
                                                continueLoop = false;
                                            } else {
                                                writer.write(yN);
                                                writer.println();
                                                writer.flush();
                                                JOptionPane.showMessageDialog(null, "Successfully created quiz.",
                                                        "Success",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                                String yesOrNo = (String) JOptionPane.showInputDialog(
                                                        null, "Would you like to add another question?", "Add Question",
                                                        JOptionPane.QUESTION_MESSAGE, null, yesNoOption,
                                                        yesNoOption[0]);
                                                if (yesOrNo == null) {
                                                    writer.write("null");
                                                    writer.println();
                                                    writer.flush();
                                                    continueLoop = false;
                                                } else {
                                                    writer.write(yesOrNo);
                                                    writer.println();
                                                    writer.flush();
                                                    while (yesOrNo.equals("Yes")) {
                                                        latch = new CountDownLatch(1);
                                                        quizGui();
                                                        latch.await();
                                                        question = qG[0];
                                                        correctAns = qG[1];
                                                        ans2 = qG[2];
                                                        ans3 = qG[3];
                                                        ans4 = qG[4];
                                                        if (question.trim().length() == 0 ||
                                                                correctAns.trim().length() == 0 ||
                                                                ans2.trim().length() == 0 ||
                                                                ans3.trim().length() == 0 ||
                                                                ans4.trim().length() == 0) {
                                                            writer.println("null");
                                                            writer.println("null");
                                                            writer.println("null");
                                                            writer.println("null");
                                                            writer.println("null");
                                                            writer.flush();
                                                            JOptionPane.showMessageDialog(null, "Invalid entry.",
                                                                    "Error",
                                                                    JOptionPane.ERROR_MESSAGE);
                                                            yesOrNo = "No";
                                                        } else {
                                                            writer.println(question);
                                                            writer.println(correctAns);
                                                            writer.println(ans2);
                                                            writer.println(ans3);
                                                            writer.println(ans4);
                                                            writer.flush();
                                                            yesOrNo = (String) JOptionPane.showInputDialog(
                                                                    null, "Would you like to add another question?",
                                                                    "Add Question",
                                                                    JOptionPane.QUESTION_MESSAGE, null, yesNoOption,
                                                                    yesNoOption[0]);
                                                            if (yesOrNo == null) {
                                                                writer.write("null");
                                                                writer.println();
                                                                writer.flush();
                                                                continueLoop = false;
                                                            } else {
                                                                writer.write(yesOrNo);
                                                                writer.println();
                                                                writer.flush();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            case "Edit a Quiz":
                                int courseLLength = Integer.parseInt(reader.readLine());
                                if (courseLLength == 0) {
                                    JOptionPane.showMessageDialog(null, "No available courses.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                                String[] courseL = new String[courseLLength];
                                for (int i = 0; i < courseL.length; i++) {
                                    courseL[i] = reader.readLine();
                                }
                                String courseC = (String) JOptionPane.showInputDialog(
                                        null, "Which course would you like to enter?", "Course Selection",
                                        JOptionPane.QUESTION_MESSAGE, null, courseL, courseL[0]);
                                if (courseC == null) {
                                    writer.write("null");
                                    writer.println();
                                    writer.flush();
                                    continueLoop = false;
                                } else {
                                    writer.write(courseC);
                                    writer.println();
                                    writer.flush();
                                    int quizzesLength = Integer.parseInt(reader.readLine());
                                    if (quizzesLength == 0) {
                                        JOptionPane.showMessageDialog(null, "No available quizzes.", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                        break;
                                    }
                                    String[] quizzesList = new String[quizzesLength];
                                    for (int i = 0; i < quizzesList.length; i++) {
                                        quizzesList[i] = reader.readLine();
                                    }
                                    String quizChoice = (String) JOptionPane.showInputDialog(
                                            null, "Which quiz would you like to edit?", "Quiz Edit",
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
                                        String[] editOptions = { "Add Question", "Delete Question" };
                                        String editOption = (String) JOptionPane.showInputDialog(
                                                null, "Would you like to add or delete a question?", "Quiz Edit",
                                                JOptionPane.QUESTION_MESSAGE, null, editOptions, editOptions[0]);
                                        if (editOption == null) {
                                            writer.write("null");
                                            writer.println();
                                            writer.flush();
                                            continueLoop = false;
                                        } else {
                                            writer.write(editOption);
                                            writer.println();
                                            writer.flush();
                                            if (editOption.equals("Delete Question")) {
                                                int questionsLength = Integer.parseInt(reader.readLine());
                                                if (questionsLength == 0) {
                                                    JOptionPane.showMessageDialog(null, "No available questions.",
                                                            "Error",
                                                            JOptionPane.ERROR_MESSAGE);
                                                    break;
                                                }
                                                String[] questionsList = new String[questionsLength];
                                                for (int i = 0; i < questionsLength; i++) {
                                                    questionsList[i] = reader.readLine();
                                                }
                                                String questionChoice = (String) JOptionPane.showInputDialog(
                                                        null, "Which question would you like to delete?", "Quiz Edit",
                                                        JOptionPane.QUESTION_MESSAGE, null, questionsList,
                                                        questionsList[0]);
                                                if (questionChoice == null) {
                                                    writer.write("null");
                                                    writer.println();
                                                    writer.flush();
                                                    continueLoop = false;
                                                } else {
                                                    writer.write(questionChoice);
                                                    writer.println();
                                                    writer.flush();
                                                    JOptionPane.showMessageDialog(null,
                                                            "Successfully deleted question.", "Success",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                }
                                            } else {
                                                latch = new CountDownLatch(1);
                                                quizGui();
                                                latch.await();
                                                if (qG.length == 0) {
                                                    continueLoop = false;
                                                    break;
                                                }
                                                String question = qG[0];
                                                String correctAns = qG[1];
                                                String ans2 = qG[2];
                                                String ans3 = qG[3];
                                                String ans4 = qG[4];
                                                if (question.trim().length() == 0 ||
                                                        correctAns.trim().length() == 0 ||
                                                        ans2.trim().length() == 0 ||
                                                        ans3.trim().length() == 0 ||
                                                        ans4.trim().length() == 0) {
                                                    writer.println("null");
                                                    writer.println("null");
                                                    writer.println("null");
                                                    writer.println("null");
                                                    writer.println("null");
                                                    writer.flush();
                                                    JOptionPane.showMessageDialog(null, "Invalid entry.", "Error",
                                                            JOptionPane.ERROR_MESSAGE);
                                                } else {
                                                    writer.println(question);
                                                    writer.println(correctAns);
                                                    writer.println(ans2);
                                                    writer.println(ans3);
                                                    writer.println(ans4);
                                                    writer.flush();
                                                    JOptionPane.showMessageDialog(null, "Successfully added question.",
                                                            "Success",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            case "Delete a Quiz":
                                int courseListLength = Integer.parseInt(reader.readLine());
                                if (courseListLength == 0) {
                                    JOptionPane.showMessageDialog(null, "No available courses.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
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
                                    if (quizzesLength == 0) {
                                        JOptionPane.showMessageDialog(null, "No available quizzes.", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                        break;
                                    }
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
                                        JOptionPane.showMessageDialog(null, "Successfully deleted quiz.", "Success",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                                break;
                            case "Upload a Quiz":
                                int coursesOptionsLength = Integer.parseInt(reader.readLine());
                                if (coursesOptionsLength == 0) {
                                    JOptionPane.showMessageDialog(null, "No available courses.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
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
                                    String inputFilename = JOptionPane.showInputDialog(null, "Enter a valid file path.",
                                            "File Upload",
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
                                        String[] yesNoOptions = { "No", "Yes" };
                                        String yesNo = (String) JOptionPane.showInputDialog(
                                                null, "Would you like to make quiz questions randomized?",
                                                "Randomize Option",
                                                JOptionPane.QUESTION_MESSAGE, null, yesNoOptions, yesNoOptions[0]);
                                        if (yesNo == null) {
                                            writer.write("null");
                                            writer.println();
                                            writer.flush();
                                        } else {
                                            writer.write(yesNo);
                                            writer.println();
                                            writer.flush();
                                            String result = reader.readLine();
                                            if (result.equals("success")) {
                                                JOptionPane.showMessageDialog(null, "Successfully uploaded quiz.",
                                                        "Success",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null,
                                                        "File does not exist or is in the wrong format.", "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                }
                                break;
                            case "View Scores":
                                int studentListLength = Integer.parseInt(reader.readLine());
                                if (studentListLength == 0) {
                                    JOptionPane.showMessageDialog(null, "No available students.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
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
                                    if (studentQuizzesLength == 0) {
                                        JOptionPane.showMessageDialog(null, "No available quiz attempts.", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                        break;
                                    }
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
                                        String timestamp = reader.readLine();
                                        JOptionPane.showMessageDialog(null, quizScore + "\nSubmitted " + timestamp,
                                                quizOption + " Score",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        String[] yNChoice = { "Yes", "No" };
                                        String yN = (String) JOptionPane.showInputDialog(
                                                null, "Would you like to view individual questions?",
                                                "Individual Questions",
                                                JOptionPane.QUESTION_MESSAGE, null, yNChoice, yNChoice[0]);
                                        if (yN == null) {
                                            writer.write("null");
                                            writer.println();
                                            writer.flush();
                                            continueLoop = false;
                                        } else if (yN.equals("No")) {
                                            writer.write("No");
                                            writer.println();
                                            writer.flush();
                                        } else {
                                            writer.write("Yes");
                                            writer.println();
                                            writer.flush();
                                            int quizQuestionsLength = Integer.parseInt(reader.readLine());
                                            String[] quizQuestions = new String[quizQuestionsLength];
                                            for (int i = 0; i < quizQuestionsLength; i++) {
                                                quizQuestions[i] = reader.readLine();
                                            }
                                            String questionChoice = (String) JOptionPane.showInputDialog(
                                                    null, "Which question would you like to view?",
                                                    "Individual Questions",
                                                    JOptionPane.QUESTION_MESSAGE, null, quizQuestions,
                                                    quizQuestions[0]);
                                            if (questionChoice != null) {
                                                JOptionPane.showMessageDialog(null, questionChoice,
                                                        "Individual Questions",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                            }
                                        }
                                    }
                                }
                                break;
                            case "Edit Account":
                                String[] yesNoOptions = { "No", "Yes" };
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
                                        String newPassword = JOptionPane.showInputDialog(null, "Enter new password.",
                                                "Edit Account",
                                                JOptionPane.QUESTION_MESSAGE);
                                        if (newPassword == null) {
                                            writer.write("null");
                                            writer.println();
                                            writer.flush();
                                        } else if (newPassword.trim().isEmpty()) {
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
                            case "Delete Account":
                                String[] yesNoList = { "No", "Yes" };
                                String yesNoChoice = (String) JOptionPane.showInputDialog(
                                        null, "Are you sure you would like to terminate this account?", "WARNING",
                                        JOptionPane.QUESTION_MESSAGE, null, yesNoList, yesNoList[0]);
                                if (yesNoChoice == null) {
                                    writer.write("null");
                                    writer.println();
                                    writer.flush();
                                } else {
                                    writer.write(yesNoChoice);
                                    writer.println();
                                    writer.flush();
                                    if (yesNoChoice.equals("Yes")) {
                                        JOptionPane.showMessageDialog(null, "Account Deleted.\nLogging out.", "Success",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        continueLoop = false;
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
                        String[] studentOptions = { "Take a Quiz", "View Scores", "Edit Account", "Delete Account",
                                "Log Out" };
                        String studentOption = (String) JOptionPane.showInputDialog(
                                null, "What would you like to do?", "Actions",
                                JOptionPane.QUESTION_MESSAGE, null, studentOptions, studentOptions[0]);
                        if (studentOption == null) {
                            writer.write("null");
                            writer.println();
                            writer.flush();
                            continueLoop = false;
                            break;
                        }
                        writer.write(studentOption);
                        writer.println();
                        writer.flush();
                        switch (studentOption) {
                            case "Take a Quiz":
                                int coursesLength = Integer.parseInt(reader.readLine());
                                if (coursesLength == 0) {
                                    JOptionPane.showMessageDialog(null, "No available courses.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
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
                                    if (quizzesLength == 0) {
                                        JOptionPane.showMessageDialog(null, "No available quizzes.", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                        break;
                                    }
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
                                        boolean finished = false;
                                        String[] upload = { "Upload a File", "Manually" };
                                        String uploadOrManually = (String) JOptionPane.showInputDialog(
                                                null, "How would you like to take the quiz?", "Quiz Choice",
                                                JOptionPane.QUESTION_MESSAGE, null, upload, upload[0]);
                                        if (uploadOrManually == null) {
                                            writer.write("null");
                                            writer.println();
                                            writer.flush();
                                            continueLoop = false;
                                        } else if (uploadOrManually.equals("Upload a File")) {
                                            ArrayList<String> allStudentAnswers = new ArrayList<String>();
                                            ArrayList<String> allCorrectAnswers = new ArrayList<String>();
                                            ArrayList<String> allQuestions = new ArrayList<String>();
                                            ArrayList<String> answerA = new ArrayList<String>();
                                            ArrayList<String> answerB = new ArrayList<String>();
                                            ArrayList<String> answerC = new ArrayList<String>();
                                            ArrayList<String> answerD = new ArrayList<String>();
                                            for (int i = 1; i < quizListLength; i += 6) {
                                                allQuestions.add(quizList[i]);
                                                String[] options = { quizList[i + 1], quizList[i + 2],
                                                    quizList[i + 3], quizList[i + 4] };
                                                    options = shuffleAnswerChoice(options);
                                                answerA.add(options[0]);
                                                answerB.add(options[1]);
                                                answerC.add(options[2]);
                                                answerD.add(options[3]);
                                                allCorrectAnswers.add(quizList[i + 5]);
                                            }
                                            JOptionPane.showMessageDialog(null,
                                                    "Each question of the quiz will appear on its own pop up.\nWrite each answer on a separate line of a file and then import the file via its path.",
                                                    "Instructions",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            for (int i = 0; i < allQuestions.size(); i++) {
                                                String title = String.format("%s: Question %d", quizName, i + 1);
                                                JOptionPane.showMessageDialog(null, allQuestions.get(i) + 
                                                "\nA: " + answerA.get(i) + 
                                                "\nB: " + answerB.get(i) + 
                                                "\nC: " + answerC.get(i) + 
                                                "\nD: " + answerD.get(i), title,
                                                        JOptionPane.INFORMATION_MESSAGE);
                                            }
                                            String filename = JOptionPane.showInputDialog(null,
                                                    "Enter file path of answer sheet.",
                                                    "Add Course",
                                                    JOptionPane.QUESTION_MESSAGE);
                                            int fileLength = fileUploadLength(filename);
                                            if (filename == null || filename.trim().isEmpty()
                                                    || fileLength != allQuestions.size()) {
                                                JOptionPane.showMessageDialog(null,
                                                        "There was a problem reading the file.\nEither the filepath does not exist or\nthe file is in the wrong format.",
                                                        "Error",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                                writer.write("null");
                                                writer.println();
                                                writer.flush();
                                                writer.write("null");
                                                writer.println();
                                                writer.flush();
                                                writer.write("null");
                                                writer.println();
                                                writer.flush();
                                                finished = false;
                                                break;
                                            } else {
                                                ArrayList<String> uploadedAnswers = new ArrayList<String>();
                                                uploadedAnswers = getFileAnswers(filename);
                                                for (int i = 0; i < uploadedAnswers.size(); i++) {
                                                    allStudentAnswers.add(uploadedAnswers.get(i));
                                                }
                                                for (int i = 0; i < allStudentAnswers.size(); i++) {
                                                    writer.write(allStudentAnswers.get(i));
                                                    writer.println();
                                                    writer.flush();
                                                    writer.write(allCorrectAnswers.get(i));
                                                    writer.println();
                                                    writer.flush();
                                                    writer.write(allQuestions.get(i));
                                                    writer.println();
                                                    writer.flush();
                                                }
                                            }
                                            finished = true;
                                        } else {
                                            for (int i = 1; i < quizListLength; i += 6) {
                                                String question = quizList[i];
                                                String[] options = { quizList[i + 1], quizList[i + 2],
                                                        quizList[i + 3], quizList[i + 4] };
                                                options = shuffleAnswerChoice(options);
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
                                                    finished = false;
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
                                                    finished = true;
                                                }
                                            }
                                        }
                                        if (finished) {
                                            JOptionPane.showMessageDialog(null, "You have completed the quiz.",
                                                    "Congratulations",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    }
                                }
                                break;
                            case "View Scores":
                                int quizAttemptsLength = Integer.parseInt(reader.readLine());
                                if (quizAttemptsLength == 0) {
                                    JOptionPane.showMessageDialog(null, "No available quiz attempts.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
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
                                    String timestamp = reader.readLine();
                                    JOptionPane.showMessageDialog(null, score + "\nSubmitted " + timestamp,
                                            quizAttemptOption + " Score",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    String[] yNChoice = { "Yes", "No" };
                                    String yN = (String) JOptionPane.showInputDialog(
                                            null, "Would you like to view individual questions?",
                                            "Individual Questions",
                                            JOptionPane.QUESTION_MESSAGE, null, yNChoice, yNChoice[0]);
                                    if (yN == null) {
                                        writer.write("null");
                                        writer.println();
                                        writer.flush();
                                        continueLoop = false;
                                    } else if (yN.equals("No")) {
                                        writer.write("No");
                                        writer.println();
                                        writer.flush();
                                    } else {
                                        writer.write("Yes");
                                        writer.println();
                                        writer.flush();
                                        int quizQuestionsLength = Integer.parseInt(reader.readLine());
                                        String[] quizQuestions = new String[quizQuestionsLength];
                                        for (int i = 0; i < quizQuestionsLength; i++) {
                                            quizQuestions[i] = reader.readLine();
                                        }
                                        String questionChoice = (String) JOptionPane.showInputDialog(
                                                null, "Which question would you like to view?", "Individual Questions",
                                                JOptionPane.QUESTION_MESSAGE, null, quizQuestions, quizQuestions[0]);
                                        if (questionChoice != null) {
                                            JOptionPane.showMessageDialog(null, questionChoice, "Individual Questions",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    }
                                }
                                break;
                            case "Edit Account":
                                String[] yesNoOptions = { "No", "Yes" };
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
                                        String newPassword = JOptionPane.showInputDialog(null, "Enter new password.",
                                                "Edit Account",
                                                JOptionPane.QUESTION_MESSAGE);
                                        if (newPassword == null) {
                                            writer.write("null");
                                            writer.println();
                                            writer.flush();
                                        } else if (newPassword.trim().isEmpty()) {
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
                            case "Delete Account":
                                String[] yesNoList = { "No", "Yes" };
                                String yesNoChoice = (String) JOptionPane.showInputDialog(
                                        null, "Are you sure you would like to terminate this account?", "WARNING",
                                        JOptionPane.QUESTION_MESSAGE, null, yesNoList, yesNoList[0]);
                                if (yesNoChoice == null) {
                                    writer.write("null");
                                    writer.println();
                                    writer.flush();
                                } else {
                                    writer.write(yesNoChoice);
                                    writer.println();
                                    writer.flush();
                                    if (yesNoChoice.equals("Yes")) {
                                        JOptionPane.showMessageDialog(null, "Account Deleted.\nLogging out.", "Success",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        continueLoop = false;
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