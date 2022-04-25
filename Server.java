import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Server extends Thread {

    // Reads users file of all usernames and passwords
    // If username and password is not there returns true and adds them
    // If username and password is there returns false
    public synchronized static String addUser(String username, String password) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File("users.txt");
            file.createNewFile();

            FileReader fr = new FileReader("users.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i += 2) {
            String tempUsername = list.get(i);
            String tempPassword = list.get(i + 1);
            if (tempUsername.equals(username) && tempPassword.equals(password)) {
                return "false";
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream("users.txt", true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(username);
            pw.println(password);
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "true";
    }

    // Reads users file of all usernames and passwords
    // If username and password is there returns true
    // If username and password is not there returns false
    public static String checkUser(String username, String password) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File("users.txt");
            file.createNewFile();

            FileReader fr = new FileReader("users.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i += 2) {
            String tempUsername = list.get(i);
            String tempPassword = list.get(i + 1);
            if (tempUsername.equals(username) && tempPassword.equals(password)) {
                return "true";
            }
        }
        return "false";
    }

    // Returns string array of course names
    public static String[] getCourses() {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File("courses.txt");
            file.createNewFile();

            FileReader fr = new FileReader("courses.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int arrayLength = list.size();
        String[] courseList = new String[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            courseList[i] = list.get(i);
        }
        return courseList;
    }

    // Returns true if course is not already in course list and adds it
    // Returns false if course is already in course list
    public static String addCourse(String courseName) {
        {
            ArrayList<String> list = new ArrayList<>();
            try {
                File file = new File("courses.txt");
                file.createNewFile();

                FileReader fr = new FileReader("courses.txt");
                BufferedReader bfr = new BufferedReader(fr);
                String line = bfr.readLine();
                while (line != null) {
                    list.add(line);
                    line = bfr.readLine();
                }
                bfr.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < list.size(); i++) {
                String tempCourse = list.get(i);
                if (tempCourse.equals(courseName)) {
                    return "false";
                }
            }

            try {
                FileOutputStream fos = new FileOutputStream("courses.txt", true);
                PrintWriter pw = new PrintWriter(fos);
                pw.println(courseName);
                pw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return "true";
        }
    }

    // Deletes chosen course from course list
    public synchronized static void deleteCourse(String courseName) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File("courses.txt");
            file.createNewFile();

            FileReader fr = new FileReader("courses.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int lineNumber = -1;
        for (int i = 0; i < list.size(); i++) {
            String tempCourseName = list.get(i);
            if (tempCourseName.equals(courseName)) {
                lineNumber = i;
            }
        }

        if (lineNumber >= 0) {
            try {
                FileOutputStream fos = new FileOutputStream("courses.txt", false);
                PrintWriter pw = new PrintWriter(fos);
                for (int i = 0; i < lineNumber; i++) {
                    pw.println(list.get(i));
                }
                for (int i = (lineNumber + 1); i < list.size(); i++) {
                    pw.println(list.get(i));
                }
                pw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // First checks if the quiz is in progress by checking the length of the
    // progress file
    // If progress file is less than or equal to 1 returns false, else returns true
    public static String isQuizInProgress(String quizProgressFileName) {
        String inProgress = "";
        try {
            File quizProgress = new File(quizProgressFileName);
            quizProgress.createNewFile();
            FileReader fr = new FileReader(quizProgressFileName);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            ArrayList<String> progressFileLength = new ArrayList<>();
            while (line != null) {
                progressFileLength.add(line);
                line = bfr.readLine();
            }
            bfr.close();
            if (progressFileLength.size() <= 1) {
                inProgress = "false";
            } else {
                inProgress = "true";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inProgress;
    }

    // Returns a String array with the quiz name followed by the remaining questions
    // and answer choices on the quiz
    public static String[] getQuizInProgress(String quizFileName, String quizProgressFileName) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> progressFileLength = new ArrayList<>();
        try {
            File quizFile = new File(quizFileName);
            quizFile.createNewFile();
            FileReader fr = new FileReader(quizFileName);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

            File quizProgressFile = new File(quizProgressFileName);
            quizProgressFile.createNewFile();
            fr = new FileReader(quizProgressFileName);
            bfr = new BufferedReader(fr);

            String progressFileLine = bfr.readLine();
            while (progressFileLine != null) {
                progressFileLength.add(line);
                progressFileLine = bfr.readLine();
            }
            bfr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int quizLength = list.size();
        int progressFileLengthInt = progressFileLength.size();
        int remainingQuestions = quizLength - progressFileLengthInt;
        // Passing 6 Variables Per Question (Question Statement, 4 Options, and Correct
        // Option) + Quiz Name
        String[] questionsList = new String[(remainingQuestions * 6) + 1];
        ArrayList<String> tempQuestionsList = new ArrayList<>();

        tempQuestionsList.add(list.get(0));
        for (int i = progressFileLengthInt; i < list.size(); i++) {
            // Gets the substring of the question and adds it to tempList
            tempQuestionsList.add(list.get(i).substring(4, list.get(i).indexOf("@", 4)));

            // Gets the whole question line and stores it in String l
            String l = list.get(i);

            // Finds the correct answer and stores it in correctAnswer by looking for the
            // key @AR: @BR: @CR: or @DR: R stands for right, then deletes the R from the
            // substring
            String correctAnswer;
            if (l.indexOf("@AR:") != -1) {
                correctAnswer = "A";
                l = l.substring(0, l.indexOf("@AR:") + 2) + l.substring(l.indexOf("@AR:") + 3);
            } else if (l.indexOf("@BR:") != -1) {
                correctAnswer = "B";
                l = l.substring(0, l.indexOf("@BR:") + 2) + l.substring(l.indexOf("@BR:") + 3);
            } else if (l.indexOf("@CR:") != -1) {
                correctAnswer = "C";
                l = l.substring(0, l.indexOf("@CR:") + 2) + l.substring(l.indexOf("@CR:") + 3);
            } else {
                correctAnswer = "D";
                l = l.substring(0, l.indexOf("@DR:") + 2) + l.substring(l.indexOf("@DR:") + 3);
            }

            // Stores the substrings of each answer inside of their respective answerLetter
            // variable
            tempQuestionsList.add(l.substring(l.indexOf("@A:") + 3, l.indexOf("@B:")));
            tempQuestionsList.add(l.substring(l.indexOf("@B:") + 3, l.indexOf("@C:")));
            tempQuestionsList.add(l.substring(l.indexOf("@C:") + 3, l.indexOf("@D:")));
            tempQuestionsList.add(l.substring(l.indexOf("@D:") + 3));
            String answerA = (l.substring(l.indexOf("@A:") + 3, l.indexOf("@B:")));
            String answerB = (l.substring(l.indexOf("@B:") + 3, l.indexOf("@C:")));
            String answerC = (l.substring(l.indexOf("@C:") + 3, l.indexOf("@D:")));
            String answerD = (l.substring(l.indexOf("@D:") + 3));

            // Finds the String of the correct answer for writing later
            String answerCorrect;
            if (correctAnswer.equals("A")) {
                answerCorrect = answerA;
            } else if (correctAnswer.equals("B")) {
                answerCorrect = answerB;
            } else if (correctAnswer.equals("C")) {
                answerCorrect = answerC;
            } else {
                answerCorrect = answerD;
            }
            tempQuestionsList.add(answerCorrect);
        }
        for (int i = 0; i < tempQuestionsList.size(); i++) {
            questionsList[i] = tempQuestionsList.get(i);
        }
        return questionsList;

    }

    // Returns list with quiz name and all quiz questions followed by their answers
    // and correct answer
    // List format [quiz name, question 1, a, b, c, d, correct answer, question 2,
    // etc...]
    public static String[] getQuiz(String quizFileName, Boolean randomized) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File quizFile = new File(quizFileName);
            quizFile.createNewFile();
            FileReader fr = new FileReader(quizFileName);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        if (randomized) {
            array = shuffleQuestions(array);
        }

        // Passing 6 Variables Per Question (Question Statement, 4 Options, and Correct
        // Option) + Quiz Name

        ArrayList<String> tempQuestionsList = new ArrayList<>();

        tempQuestionsList.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            String l = array[i];
            // Gets the substring of the question and adds it to tempList
            tempQuestionsList.add(l.substring(4, l.indexOf("@", 4)));

            // Gets the whole question line and stores it in String l

            // Finds the correct answer and stores it in correctAnswer by looking for the
            // key @AR: @BR: @CR: or @DR: R stands for right, then deletes the R from the
            // substring
            String correctAnswer;
            if (l.indexOf("@AR:") != -1) {
                correctAnswer = "A";
                l = l.substring(0, l.indexOf("@AR:") + 2) + l.substring(l.indexOf("@AR:") + 3);
            } else if (l.indexOf("@BR:") != -1) {
                correctAnswer = "B";
                l = l.substring(0, l.indexOf("@BR:") + 2) + l.substring(l.indexOf("@BR:") + 3);
            } else if (l.indexOf("@CR:") != -1) {
                correctAnswer = "C";
                l = l.substring(0, l.indexOf("@CR:") + 2) + l.substring(l.indexOf("@CR:") + 3);
            } else {
                correctAnswer = "D";
                l = l.substring(0, l.indexOf("@DR:") + 2) + l.substring(l.indexOf("@DR:") + 3);
            }

            // Stores the substrings of each answer inside of their respective answerLetter
            // variable
            tempQuestionsList.add(l.substring(l.indexOf("@A:") + 3, l.indexOf("@B:")));
            tempQuestionsList.add(l.substring(l.indexOf("@B:") + 3, l.indexOf("@C:")));
            tempQuestionsList.add(l.substring(l.indexOf("@C:") + 3, l.indexOf("@D:")));
            tempQuestionsList.add(l.substring(l.indexOf("@D:") + 3));
            String answerA = (l.substring(l.indexOf("@A:") + 3, l.indexOf("@B:")));
            String answerB = (l.substring(l.indexOf("@B:") + 3, l.indexOf("@C:")));
            String answerC = (l.substring(l.indexOf("@C:") + 3, l.indexOf("@D:")));
            String answerD = (l.substring(l.indexOf("@D:") + 3));

            // Finds the String of the correct answer for writing later
            String answerCorrect;
            if (correctAnswer.equals("A")) {
                answerCorrect = answerA;
            } else if (correctAnswer.equals("B")) {
                answerCorrect = answerB;
            } else if (correctAnswer.equals("C")) {
                answerCorrect = answerC;
            } else {
                answerCorrect = answerD;
            }
            tempQuestionsList.add(answerCorrect);
        }
        String[] questionsList = new String[tempQuestionsList.size()];
        for (int i = 0; i < tempQuestionsList.size(); i++) {
            questionsList[i] = tempQuestionsList.get(i);
        }
        return questionsList;
    }

    // Returns string array of all quizzes in chosen course
    public static String[] getQuizList(String courseName) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File quizFile = new File((courseName + "@quizzes.txt"));
            quizFile.createNewFile();
            FileReader fr = new FileReader((courseName + "@quizzes.txt"));
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] quizList = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            quizList[i] = list.get(i);
        }
        return quizList;
    }

    // Adds attempt for a quiz into quiz attempts once done
    public synchronized static void addAttempt(String username, String courseName, String quizName, int attemptNumber) {
        String quizFileName = username + "@" + courseName + "@" + quizName + "@" + String.valueOf(attemptNumber)
                + ".txt";
        try {
            File file = new File("quizAttempts.txt");
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream("quizAttempts.txt", true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(quizFileName);
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Returns number of attempts a specific user has on a specific quiz + 1
    public static int getAttempt(String username, String courseName, String quizName) {
        String quizFileName = username + "@" + courseName + "@" + quizName;
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File("quizAttempts.txt");
            file.createNewFile();

            FileReader fr = new FileReader("quizAttempts.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).indexOf(quizFileName) != -1) {
                count++;
            }
        }
        return (count + 1);
    }

    // Writes quiz name, questions, and if they got it correct to progress file
    public synchronized static void writeToProgressFile(String progressFile, String quizName, String question,
            boolean correct) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File(progressFile);
            file.createNewFile();

            FileReader fr = new FileReader(progressFile);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (list.size() < 1) {
            try {
                FileOutputStream fos = new FileOutputStream(progressFile, true);
                PrintWriter pw = new PrintWriter(fos);
                pw.println(quizName);
                if (correct) {
                    pw.println("Question 1: " + question + "@Correct");
                } else {
                    pw.println("Question 1: " + question + "@Incorrect");
                }
                pw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (list.size() == 1) {
            try {
                FileOutputStream fos = new FileOutputStream(progressFile, true);
                PrintWriter pw = new PrintWriter(fos);
                if (correct) {
                    pw.println("Question 1: " + question + "@Correct");
                } else {
                    pw.println("Question 1: " + question + "@Incorrect");
                }
                pw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {

                FileOutputStream fos = new FileOutputStream(progressFile, true);
                PrintWriter pw = new PrintWriter(fos);
                if (correct) {
                    pw.println("Question " + list.size() + ": " + question + "@Correct");
                } else {
                    pw.println("Question " + list.size() + ": " + question + "@Incorrect");
                }
                pw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void createPersonalQuizVersion(String[] quiz, String filename) {
        String quizName = quiz[0];
        try {
            File file = new File(filename);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file, false);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(String.format(quizName));
            for (int i = 1; i < quiz.length; i += 6) {
                String question = quiz[i];
                String answerA = quiz[i + 1];
                String answerB = quiz[i + 2];
                String answerC = quiz[i + 3];
                String answerD = quiz[i + 4];
                String correctAnswer = quiz[i + 5];
                String questionLine = "";
                if (correctAnswer.equals(answerA)) {
                    questionLine = String.format("MCQ@" + question + "@AR:" + answerA + "@B:" + answerB +
                            "@C:" + answerC + "@D:" + answerD);
                } else if (correctAnswer.equals(answerB)) {
                    questionLine = String.format("MCQ@" + question + "@A:" + answerA + "@BR:" + answerB +
                            "@C:" + answerC + "@D:" + answerD);
                } else if (correctAnswer.equals(answerC)) {
                    questionLine = String.format("MCQ@" + question + "@A:" + answerA + "@B:" + answerB +
                            "@CR:" + answerC + "@D:" + answerD);
                } else {
                    questionLine = String.format("MCQ@" + question + "@A:" + answerA + "@B:" + answerB +
                            "@C:" + answerC + "@DR:" + answerD);
                }
                pw.println(questionLine);
            }
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Writes the score of the quiz at the end
    public synchronized static void gradeQuiz(String progressFile) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File(progressFile);
            file.createNewFile();

            FileReader fr = new FileReader(progressFile);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int correctCount = 0;
        int incorrectCount = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).indexOf("@Correct") != -1) {
                correctCount++;
            }
            if (list.get(i).indexOf("@Incorrect") != -1) {
                incorrectCount++;
            }
        }
        int total = correctCount + incorrectCount;
        int score = total - incorrectCount;
        try {

            FileOutputStream fos = new FileOutputStream(progressFile, true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(String.format("@Score: %d/%d", score, total));
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Returns string array of completed quizzes for a specific student user
    public static String[] getCompletedQuizzesList(String username) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File("quizAttempts.txt");
            file.createNewFile();
            FileReader fr = new FileReader("quizAttempts.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).substring(0, list.get(i).indexOf("@")).equals(username)) {
                count++;
            }
        }
        String[] quizAttemptList = new String[count];
        int iteration = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).substring(0, list.get(i).indexOf("@")).equals(username)) {
                quizAttemptList[iteration] = list.get(i);
                iteration++;
            }
        }
        return quizAttemptList;
    }

    // Returns quiz score for specific quiz
    public static String getQuizScore(String progressFile) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File(progressFile);
            file.createNewFile();
            FileReader fr = new FileReader(progressFile);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (list.get(list.size() - 1).substring(1));
    }

    // returns the timestamp for a specific quiz
    public static String getTimestamp(String progressFile) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File(progressFile);
            file.createNewFile();
            FileReader fr = new FileReader(progressFile);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (list.get(list.size() - 2));
    }

    // Returns string list of all student usernames
    public static String[] getStudentUsernames() {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File("users.txt");
            file.createNewFile();
            FileReader fr = new FileReader("users.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).indexOf("S@") != -1) {
                count++;
            }
        }
        String[] usernamesList = new String[count];
        int iteration = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).indexOf("S@") != -1) {
                usernamesList[iteration] = list.get(i).substring(2);
                iteration++;
            }
        }
        return usernamesList;
    }

    // Returns true if file is in correct format
    // Returns false if file is in incorrect format
    public static boolean checkFileValidity(String filename) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File(filename);
            file.createNewFile();
            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int length = list.size();
        if ((length - 1) % 6 == 0 && length >= 7) {
            return true;
        } else {
            return false;
        }
    }

    // Writes quiz to quiz file and returns quiz title
    public synchronized static String uploadQuiz(String course, String filename) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File(filename);
            file.createNewFile();
            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String quizName = list.get(0);
        String outputFilename = course + "@" + quizName + ".txt";
        try {
            File file = new File(outputFilename);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file, false);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(String.format(quizName));
            for (int i = 1; i < list.size(); i += 6) {
                String question = list.get(i);
                String answerA = list.get(i + 1);
                String answerB = list.get(i + 2);
                String answerC = list.get(i + 3);
                String answerD = list.get(i + 4);
                String correctAnswer = list.get(i + 5);
                String questionLine = "";
                if (correctAnswer.equals(answerA)) {
                    questionLine = String.format("MCQ@" + question + "@AR:" + answerA + "@B:" + answerB +
                            "@C:" + answerC + "@D:" + answerD);
                } else if (correctAnswer.equals(answerB)) {
                    questionLine = String.format("MCQ@" + question + "@A:" + answerA + "@BR:" + answerB +
                            "@C:" + answerC + "@D:" + answerD);
                } else if (correctAnswer.equals(answerC)) {
                    questionLine = String.format("MCQ@" + question + "@A:" + answerA + "@B:" + answerB +
                            "@CR:" + answerC + "@D:" + answerD);
                } else {
                    questionLine = String.format("MCQ@" + question + "@A:" + answerA + "@B:" + answerB +
                            "@C:" + answerC + "@DR:" + answerD);
                }
                pw.println(questionLine);
            }
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quizName;
    }

    public static String[] getQuizQuestions(String quizfile) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File(quizfile);
            file.createNewFile();
            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] questions = new String[list.size() - 1];
        for (int i = 1; i < list.size(); i++) {
            questions[i - 1] = i + ". " + list.get(i).substring(4, list.get(i).indexOf("@", 4));
        }
        return questions;
    }

    public static void deleteQuestion(String quizfile, int questionNum) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File(quizfile);
            file.createNewFile();

            FileReader fr = new FileReader(quizfile);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream(quizfile, false);
            PrintWriter pw = new PrintWriter(fos);
            for (int i = 0; i < questionNum; i++) {
                pw.println(list.get(i));
            }
            for (int i = (questionNum + 1); i < list.size(); i++) {
                pw.println(list.get(i));
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Creates new quiz with one question
    public synchronized static String createQuiz(String course, String quizName, String question, String answerA,
            String answerB, String answerC, String answerD) {
        String outputFilename = course + "@" + quizName + ".txt";
        try {
            File file = new File(outputFilename);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file, false);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(quizName);
            String questionLine = String.format("MCQ@" + question + "@AR:" + answerA + "@B:" + answerB +
                    "@C:" + answerC + "@D:" + answerD);
            pw.println(questionLine);
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFilename;
    }

    // Adds one question to a created quiz
    public synchronized static void addQuestion(String course, String quizName, String question, String answerA,
            String answerB, String answerC, String answerD) {
        String outputFilename = course + "@" + quizName + ".txt";
        try {
            File file = new File(outputFilename);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file, true);
            PrintWriter pw = new PrintWriter(fos);
            String questionLine = String.format("MCQ@" + question + "@AR:" + answerA + "@B:" + answerB +
                    "@C:" + answerC + "@D:" + answerD);
            pw.println(questionLine);
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // adds quiz name to course file that keeps track of its quizzes
    public synchronized static void addQuizToCourse(String quizName, String course) {
        try {
            File file = new File(course + "@quizzes.txt");
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file, true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(quizName);
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void deleteQuiz(String courseName, String quizName) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File(courseName + "@quizzes.txt");
            file.createNewFile();

            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int lineNumber = -1;
        for (int i = 0; i < list.size(); i++) {
            String tempQuizName = list.get(i);
            if (tempQuizName.equals(quizName)) {
                lineNumber = i;
            }
        }

        if (lineNumber >= 0) {
            try {
                File file = new File(courseName + "@quizzes.txt");
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(courseName + "@quizzes.txt", false);
                PrintWriter pw = new PrintWriter(fos);
                for (int i = 0; i < lineNumber; i++) {
                    pw.println(list.get(i));
                }
                for (int i = (lineNumber + 1); i < list.size(); i++) {
                    pw.println(list.get(i));
                }
                pw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Returns true if quiz is a random quiz
    // Returns false if quiz is not a random quiz
    public static boolean isRandom(String quizOption) {
        if (quizOption.indexOf("Random") != -1) {
            return true;
        } else {
            return false;
        }
    }

    public synchronized static void changeTeacherPassword(String username, String password, String newPassword) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File("users.txt");
            file.createNewFile();

            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int lineNumber = -1;
        for (int i = 0; i < list.size(); i += 2) {
            if (list.get(i).equals("T@" + username) && list.get(i + 1).equals(password)) {
                lineNumber = i + 1;
            }
        }
        if (lineNumber >= 0) {
            try {
                FileOutputStream fos = new FileOutputStream("users.txt", false);
                PrintWriter pw = new PrintWriter(fos);
                for (int i = 0; i < lineNumber; i++) {
                    pw.println(list.get(i));
                }
                pw.println(newPassword);
                for (int i = (lineNumber + 1); i < list.size(); i++) {
                    pw.println(list.get(i));
                }
                pw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void changeStudentPassword(String username, String password, String newPassword) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File("users.txt");
            file.createNewFile();

            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int lineNumber = -1;
        for (int i = 0; i < list.size(); i += 2) {
            if (list.get(i).equals("S@" + username) && list.get(i + 1).equals(password)) {
                lineNumber = i + 1;
            }
        }
        if (lineNumber >= 0) {
            try {
                FileOutputStream fos = new FileOutputStream("users.txt", false);
                PrintWriter pw = new PrintWriter(fos);
                for (int i = 0; i < lineNumber; i++) {
                    pw.println(list.get(i));
                }
                pw.println(newPassword);
                for (int i = (lineNumber + 1); i < list.size(); i++) {
                    pw.println(list.get(i));
                }
                pw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static String[] shuffleQuestions(String[] arr) {
        Random rand = new Random();
        String[] array = new String[arr.length - 1];
        for (int i = 0; i < arr.length; i++) {
            if (!(i == 0)) {
                array[i - 1] = arr[i];
            }
        }
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            String temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
        String[] returnArray = new String[arr.length];
        for (int i = 1; i < arr.length; i++) {
            returnArray[i] = array[i - 1];
        }
        return returnArray;
    }

    // Timestamps progress quiz file
    public synchronized static void timeStampQuiz(String progressFile) throws IOException {
        File f = new File(progressFile);
        BufferedReader read = new BufferedReader(new FileReader(f));
        String str = read.readLine();
        String append = "";
        while (str != null) {
            append += str + "\n";
            str = read.readLine();
        }
        PrintWriter pw = new PrintWriter(new FileWriter(f), true);
        pw.print(append);
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pw.println(formatter.format(ts));
        pw.close();
        read.close();
    }

    public synchronized static void addRandom(String inputFileName) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File(inputFileName);
            file.createNewFile();

            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String quizname = list.get(0) + " Random";
        try {
            FileOutputStream fos = new FileOutputStream(inputFileName, false);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(quizname);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fos = new FileOutputStream(inputFileName, true);
            PrintWriter pw = new PrintWriter(fos);
            for (int i = 1; i < list.size(); i++) {
                pw.println(list.get(i));
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void deleteAccount(String username, String password, String identification) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File("users.txt");
            file.createNewFile();

            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int usernameLine = -1;
        int passwordLine = -1;
        if (identification.equals("Student")) {
            for (int i = 0; i < list.size(); i += 2) {
                if (list.get(i).equals("S@" + username) && list.get(i + 1).equals(password)) {
                    usernameLine = i;
                    passwordLine = i + 1;
                }
            }
        } else if (identification.equals("Teacher")) {
            for (int i = 0; i < list.size(); i += 2) {
                if (list.get(i).equals("T@" + username) && list.get(i + 1).equals(password)) {
                    usernameLine = i;
                    passwordLine = i + 1;
                }
            }
        }

        if (usernameLine >= 0 && passwordLine >= 1) {
            try {
                FileOutputStream fos = new FileOutputStream("users.txt", false);
                PrintWriter pw = new PrintWriter(fos);
                for (int i = 0; i < usernameLine; i++) {
                    pw.println(list.get(i));
                }
                for (int i = (passwordLine + 1); i < list.size(); i++) {
                    pw.println(list.get(i));
                }
                pw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static Socket socket;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(9999);
            while (true) {
                socket = serverSocket.accept();
                Server server = new Server();
                Thread clientThread = new Thread(server);
                clientThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            String identification = "";
            String loginOption = "";
            String username = "";
            String password = "";

            identification = reader.readLine();
            if (identification.equals("null")) {
            } else {
                loginOption = reader.readLine();
                if (loginOption.equals("null")) {
                } else {
                    username = reader.readLine();
                    if (username.equals("null")) {
                    } else {
                        password = reader.readLine();
                        if (password.equals("null")) {
                        }
                    }
                }
            }

            if (!identification.equals("null") && !loginOption.equals("null") && !username.equals("null")
                    && !password.equals("null")) {
                String loginSuccess = "";
                if (loginOption.equals("Login")) {
                    if (identification.equals("Teacher")) {
                        loginSuccess = checkUser(("T@" + username), password);
                    } else if (identification.equals("Student")) {
                        loginSuccess = checkUser(("S@" + username), password);
                    }
                } else if (loginOption.equals("Create an Account")) {
                    if (identification.equals("Teacher")) {
                        loginSuccess = addUser(("T@" + username), password);
                    } else if (identification.equals("Student")) {
                        loginSuccess = addUser(("S@" + username), password);
                    }
                }
                writer.write(loginSuccess);
                writer.println();
                writer.flush();

                if (loginSuccess.equals("true")) {
                    if (identification.equals("Teacher")) {
                        boolean continueLoop = true;
                        do {
                            String teacherOption = reader.readLine();
                            if (!teacherOption.equals("null")) {
                                switch (teacherOption) {
                                    case "Add Course":
                                        String courseName = reader.readLine();
                                        String addCourseSuccess;
                                        if (!courseName.equals("null")) {
                                            addCourseSuccess = addCourse(courseName);
                                        } else {
                                            addCourseSuccess = "null";
                                            continueLoop = false;
                                        }
                                        writer.write(addCourseSuccess);
                                        writer.println();
                                        writer.flush();
                                        break;
                                    case "Delete a Course":
                                        String[] courses = getCourses();
                                        String coursesLength = String.valueOf(courses.length);
                                        writer.write(coursesLength);
                                        writer.println();
                                        writer.flush();
                                        for (int i = 0; i < courses.length; i++) {
                                            writer.write(courses[i]);
                                            writer.println();
                                            writer.flush();
                                        }
                                        String courseDeletionOption = reader.readLine();
                                        if (!courseDeletionOption.equals("null")) {
                                            deleteCourse(courseDeletionOption);
                                        } else {
                                            continueLoop = false;
                                        }
                                        break;
                                    case "Create a Quiz":
                                        String[] coursesList = getCourses();
                                        String coursesListLength = String.valueOf(coursesList.length);
                                        writer.write(coursesListLength);
                                        writer.println();
                                        writer.flush();
                                        for (int i = 0; i < coursesList.length; i++) {
                                            writer.write(coursesList[i]);
                                            writer.println();
                                            writer.flush();
                                        }
                                        String coursesChoice = reader.readLine();
                                        if (!coursesChoice.equals("null")) {
                                            String quizTitle = reader.readLine();
                                            if (!quizTitle.equals("null")) {
                                                String question = reader.readLine();
                                                String correctAns = reader.readLine();
                                                String ans2 = reader.readLine();
                                                String ans3 = reader.readLine();
                                                String ans4 = reader.readLine();
                                                if (!question.equals("null")) {
                                                    String randomized = reader.readLine();
                                                    if (!randomized.equals("null")) {
                                                        String inputFileName;
                                                        if (randomized.equals("Yes")) {
                                                            inputFileName = createQuiz(coursesChoice, quizTitle + " Random",
                                                                    question, correctAns, ans2, ans3, ans4);
                                                            addQuizToCourse(quizTitle + " Random", coursesChoice);
                                                        } else {
                                                            inputFileName = createQuiz(coursesChoice, quizTitle,
                                                                    question, correctAns, ans2, ans3, ans4);
                                                            addQuizToCourse(quizTitle, coursesChoice);
                                                        }
                                                        String keepAdding = reader.readLine();
                                                        if (keepAdding.equals("null")) {
                                                            continueLoop = false;
                                                        }
                                                        while (keepAdding.equals("Yes")) {
                                                            question = reader.readLine();
                                                            correctAns = reader.readLine();
                                                            ans2 = reader.readLine();
                                                            ans3 = reader.readLine();
                                                            ans4 = reader.readLine();
                                                            if (!question.equals("null")) {
                                                                if (randomized.equals("Yes")) {
                                                                    addQuestion(coursesChoice, quizTitle + " Random", question,
                                                                        correctAns, ans2, ans3, ans4);
                                                                } else if (randomized.equals("No")) {
                                                                    addQuestion(coursesChoice, quizTitle, question,
                                                                        correctAns, ans2, ans3, ans4);
                                                                }        
                                                                keepAdding = reader.readLine();
                                                                if (keepAdding.equals("null")) {
                                                                    continueLoop = false;
                                                                }
                                                            } else {
                                                                keepAdding = "No";
                                                                continueLoop = false;
                                                            }
                                                        }
                                                    } else {
                                                        continueLoop = false;
                                                    }
                                                } else {
                                                    continueLoop = false;
                                                }
                                            } else {
                                                continueLoop = false;
                                            }
                                        } else {
                                            continueLoop = false;
                                        }
                                        break;
                                    case "Edit a Quiz":
                                        String[] coursesL = getCourses();
                                        String coursesLen = String.valueOf(coursesL.length);
                                        writer.write(coursesLen);
                                        writer.println();
                                        writer.flush();
                                        for (int i = 0; i < coursesL.length; i++) {
                                            writer.write(coursesL[i]);
                                            writer.println();
                                            writer.flush();
                                        }
                                        String courChoice = reader.readLine();
                                        if (!courChoice.equals("null")) {
                                            String[] quizzes = getQuizList(courChoice);
                                            String quizzesLength = String.valueOf(quizzes.length);
                                            writer.write(quizzesLength);
                                            writer.println();
                                            writer.flush();
                                            for (int i = 0; i < quizzes.length; i++) {
                                                writer.write(quizzes[i]);
                                                writer.println();
                                                writer.flush();
                                            }
                                            String quizChoice = reader.readLine();
                                            if (!quizChoice.equals("null")) {
                                                String editOption = reader.readLine();
                                                if (editOption.equals("Add Question")) {
                                                    String question = reader.readLine();
                                                    String correctAns = reader.readLine();
                                                    String ans2 = reader.readLine();
                                                    String ans3 = reader.readLine();
                                                    String ans4 = reader.readLine();
                                                    if (!question.equals("null")) {
                                                        addQuestion(courChoice, quizChoice, question, correctAns, ans2, ans3, ans4);
                                                    } else {
                                                        continueLoop = false;
                                                    }
                                                } else if (editOption.equals("Delete Question")) {
                                                    String[] questions = getQuizQuestions(courChoice + "@" + quizChoice + ".txt");
                                                    String questionsLength = String.valueOf(questions.length);
                                                    writer.write(questionsLength);
                                                    writer.println();
                                                    writer.flush();
                                                    for (int i = 0; i < questions.length; i++) {
                                                        writer.write(questions[i]);
                                                        writer.println();
                                                        writer.flush();
                                                    }
                                                    String questionChoice = reader.readLine();
                                                    if (!questionChoice.equals("null")) {
                                                        int questionNum = Integer.parseInt(questionChoice.substring(0, questionChoice.indexOf(".")));
                                                        deleteQuestion(courChoice + "@" + quizChoice + ".txt", questionNum);
                                                    } else {
                                                        continueLoop = false;
                                                    }
                                                } else {
                                                    continueLoop = false;
                                                } 
                                            } else {
                                                continueLoop = false;
                                            }
                                        } else {
                                            continueLoop = false;
                                        }
                                        break;
                                    case "Delete a Quiz":
                                        String[] courseList = getCourses();
                                        String courseListLength = String.valueOf(courseList.length);
                                        writer.write(courseListLength);
                                        writer.println();
                                        writer.flush();
                                        for (int i = 0; i < courseList.length; i++) {
                                            writer.write(courseList[i]);
                                            writer.println();
                                            writer.flush();
                                        }
                                        String courseChoice = reader.readLine();
                                        if (!courseChoice.equals("null")) {
                                            String[] quizzes = getQuizList(courseChoice);
                                            String quizzesLength = String.valueOf(quizzes.length);
                                            writer.write(quizzesLength);
                                            writer.println();
                                            writer.flush();
                                            for (int i = 0; i < quizzes.length; i++) {
                                                writer.write(quizzes[i]);
                                                writer.println();
                                                writer.flush();
                                            }
                                            String quizChoice = reader.readLine();
                                            if (!quizChoice.equals("null")) {
                                                deleteQuiz(courseChoice, quizChoice);
                                            } else {
                                                continueLoop = false;
                                            }
                                        } else {
                                            continueLoop = false;
                                        }
                                        break;
                                    case "Upload a Quiz":
                                        String[] coursesOptions = getCourses();
                                        String coursesOptionsLength = String.valueOf(coursesOptions.length);
                                        writer.write(coursesOptionsLength);
                                        writer.println();
                                        writer.flush();
                                        for (int i = 0; i < coursesOptions.length; i++) {
                                            writer.write(coursesOptions[i]);
                                            writer.println();
                                            writer.flush();
                                        }
                                        String courseOption = reader.readLine();
                                        if (!courseOption.equals("null")) {
                                            String inputFileName = reader.readLine();
                                            if (!inputFileName.equals("null")) {
                                                String randomized = reader.readLine();
                                                if (!randomized.equals("null")) {
                                                    boolean valid = checkFileValidity(inputFileName);
                                                    if (valid) {
                                                        if (randomized.equals("Yes")) {
                                                            addRandom(inputFileName);
                                                        }
                                                        String quizName = uploadQuiz(courseOption, inputFileName);
                                                        addQuizToCourse(quizName, courseOption);
                                                        writer.write("success");
                                                        writer.println();
                                                        writer.flush();
                                                    } else {
                                                        writer.write("fail");
                                                        writer.println();
                                                        writer.flush();
                                                    }
                                                } else {
                                                    continueLoop = false;
                                                }
                                            } else {
                                                continueLoop = false;
                                            }
                                        } else {
                                            continueLoop = false;
                                        }
                                        break;
                                    case "View Scores":
                                        String[] studentList = getStudentUsernames();
                                        String studentListLength = String.valueOf(studentList.length);
                                        writer.write(studentListLength);
                                        writer.println();
                                        writer.flush();
                                        for (int i = 0; i < studentList.length; i++) {
                                            writer.write(studentList[i]);
                                            writer.println();
                                            writer.flush();
                                        }
                                        String studentOption = reader.readLine();
                                        if (!studentOption.equals("null")) {
                                            String[] studentQuizzes = getCompletedQuizzesList(studentOption);
                                            String studentQuizzesLength = String.valueOf(studentQuizzes.length);
                                            writer.write(studentQuizzesLength);
                                            writer.println();
                                            writer.flush();
                                            for (int i = 0; i < studentQuizzes.length; i++) {
                                                writer.write(studentQuizzes[i]);
                                                writer.println();
                                                writer.flush();
                                            }
                                            String quizOption = reader.readLine();
                                            if (!quizOption.equals("null")) {
                                                String quizScore = getQuizScore(quizOption);
                                                writer.write(quizScore);
                                                writer.println();
                                                writer.flush();
                                                String timestamp = getTimestamp(quizOption);
                                                writer.write(timestamp);
                                                writer.println();
                                                writer.flush();
                                            } else {
                                                continueLoop = false;
                                            }
                                        } else {
                                            continueLoop = false;
                                        }
                                        break;
                                    case "Edit Account":
                                        String yesNo = reader.readLine();
                                        if (yesNo.equals("null")) {
                                            continueLoop = false;
                                        } else if (yesNo.equals("Yes")) {
                                            String newPassword = reader.readLine();
                                            if (newPassword.equals(" ")) {
                                            } else if (!newPassword.equals("null")) {
                                                changeTeacherPassword(username, password, newPassword);
                                            } else {
                                                continueLoop = false;
                                            }
                                        }
                                        break;
                                    case "Delete Account":
                                        String yesNoChoice = reader.readLine();
                                        if (yesNoChoice.equals(null)) {
                                            continueLoop = false;
                                        } else if (yesNoChoice.equals("Yes")) {
                                            continueLoop = false;
                                            deleteAccount(username, password, identification);
                                        }
                                        break;
                                    case "Log Out":
                                        continueLoop = false;
                                        break;
                                }
                            } else {
                                continueLoop = false;
                            }
                        } while (continueLoop);
                    } else if (identification.equals("Student")) {
                        boolean continueLoop = true;
                        do {
                            String studentOption = reader.readLine();
                            if (!studentOption.equals("null")) {
                                switch (studentOption) {
                                    case "Take a Quiz":
                                        String[] courses = getCourses();
                                        String coursesLength = String.valueOf(courses.length);
                                        writer.write(coursesLength);
                                        writer.println();
                                        writer.flush();
                                        for (int i = 0; i < courses.length; i++) {
                                            writer.write(courses[i]);
                                            writer.println();
                                            writer.flush();
                                        }
                                        String courseOption = reader.readLine();
                                        if (!courseOption.equals("null")) {
                                            String[] quizzes = getQuizList(courseOption);
                                            String quizzesLength = String.valueOf(quizzes.length);
                                            writer.write(quizzesLength);
                                            writer.println();
                                            writer.flush();
                                            for (int i = 0; i < quizzes.length; i++) {
                                                writer.write(quizzes[i]);
                                                writer.println();
                                                writer.flush();
                                            }
                                            String quizOption = reader.readLine();
                                            if (!quizOption.equals("null")) {
                                                String inProgress = isQuizInProgress(username + "@" +
                                                        courseOption + "@" + quizOption + "@" +
                                                        getAttempt(username, courseOption, quizOption) + ".txt");
                                                if (inProgress.equals("true")) {
                                                    String quizProgressFilename = username + "@" + courseOption +
                                                            "@" + quizOption + "@" +
                                                            getAttempt(username, courseOption, quizOption) + ".txt";
                                                    String quizVersionFilename = username + "@" + courseOption +
                                                            "@" + quizOption + "@" +
                                                            getAttempt(username, courseOption, quizOption) + "@Ver.txt";
                                                    String[] quizList = getQuizInProgress(quizVersionFilename,
                                                            quizProgressFilename);

                                                    String quizListLength = String.valueOf(quizList.length);
                                                    writer.write(quizListLength);
                                                    writer.println();
                                                    writer.flush();
                                                    for (int i = 0; i < quizList.length; i++) {
                                                        writer.write(quizList[i]);
                                                        writer.println();
                                                        writer.flush();
                                                    }
                                                    for (int i = 0; i < ((quizList.length - 1) / 6); i++) {
                                                        String quizName = quizList[0];
                                                        String answerChoice = reader.readLine();
                                                        String correctAnswer = reader.readLine();
                                                        String questionStatement = reader.readLine();
                                                        if (!inProgress.equals("null") && !correctAnswer.equals("null")
                                                                && !questionStatement.equals("null")) {
                                                            boolean correct;
                                                            if (answerChoice.equals(correctAnswer)) {
                                                                correct = true;
                                                            } else {
                                                                correct = false;
                                                            }
                                                            writeToProgressFile(quizProgressFilename, quizName,
                                                                    questionStatement, correct);
                                                        }
                                                    }
                                                    addAttempt(username, courseOption, quizOption,
                                                            getAttempt(username, courseOption, quizOption));
                                                    timeStampQuiz(quizProgressFilename);
                                                    gradeQuiz(quizProgressFilename);

                                                } else {
                                                    String quizProgressFilename = username + "@" + courseOption +
                                                            "@" + quizOption + "@" +
                                                            getAttempt(username, courseOption, quizOption) + ".txt";
                                                    String[] quizList = getQuiz(
                                                            courseOption + "@" + quizOption + ".txt",
                                                            isRandom(quizOption));
                                                    String quizVersionFilename = username + "@" + courseOption +
                                                            "@" + quizOption + "@" +
                                                            getAttempt(username, courseOption, quizOption) + "@Ver.txt";
                                                    createPersonalQuizVersion(quizList, quizVersionFilename);
                                                    String quizListLength = String.valueOf(quizList.length);
                                                    writer.write(quizListLength);
                                                    writer.println();
                                                    writer.flush();
                                                    for (int i = 0; i < quizList.length; i++) {
                                                        writer.write(quizList[i]);
                                                        writer.println();
                                                        writer.flush();
                                                    }
                                                    boolean finished = false;
                                                    for (int i = 0; i < ((quizList.length - 1) / 6); i++) {
                                                        String quizName = quizList[0];
                                                        String answerChoice = reader.readLine();
                                                        String correctAnswer = reader.readLine();
                                                        String questionStatement = reader.readLine();
                                                        if (!inProgress.equals("null") && !correctAnswer.equals("null")
                                                                && !questionStatement.equals("null")) {
                                                            boolean correct;
                                                            if (answerChoice.equals(correctAnswer)) {
                                                                correct = true;
                                                            } else {
                                                                correct = false;
                                                            }
                                                            writeToProgressFile(quizProgressFilename, quizName,
                                                                    questionStatement, correct);
                                                            finished = true;
                                                        } else {
                                                            finished = false;
                                                            break;
                                                        }
                                                    }
                                                    if (finished) {
                                                        addAttempt(username, courseOption, quizOption,
                                                                getAttempt(username, courseOption, quizOption));
                                                        timeStampQuiz(quizProgressFilename);
                                                        gradeQuiz(quizProgressFilename);
                                                    }
                                                }
                                            } else {
                                                continueLoop = false;
                                            }
                                        } else {
                                            continueLoop = false;
                                        }
                                        break;
                                    case "View Scores":
                                        String[] quizAttempts = getCompletedQuizzesList(username);
                                        String quizAttemptsLength = String.valueOf(quizAttempts.length);
                                        writer.write(quizAttemptsLength);
                                        writer.println();
                                        writer.flush();
                                        for (int i = 0; i < quizAttempts.length; i++) {
                                            writer.write(quizAttempts[i]);
                                            writer.println();
                                            writer.flush();
                                        }
                                        String quizAttemptChoice = reader.readLine();
                                        if (!quizAttemptChoice.equals("null")) {
                                            String score = getQuizScore(quizAttemptChoice);
                                            String timestamp = getTimestamp(quizAttemptChoice);
                                            writer.write(score);
                                            writer.println();
                                            writer.flush();
                                            writer.write(timestamp);
                                            writer.println();
                                            writer.flush();
                                        } else {
                                            continueLoop = false;
                                        }
                                        break;
                                    case "Edit Account":
                                        String yesNo = reader.readLine();
                                        if (yesNo.equals("null")) {
                                            continueLoop = false;
                                        } else if (yesNo.equals("Yes")) {
                                            String newPassword = reader.readLine();
                                            if (newPassword.equals(" ")) {
                                            } else if (!newPassword.equals("null")) {
                                                changeStudentPassword(username, password, newPassword);
                                            } else {
                                                continueLoop = false;
                                            }
                                        }
                                        break;
                                    case "Delete Account":
                                        String yesNoChoice = reader.readLine();
                                        if (yesNoChoice.equals(null)) {
                                            continueLoop = false;
                                        } else if (yesNoChoice.equals("Yes")) {
                                            continueLoop = false;
                                            deleteAccount(username, password, identification);
                                        }
                                        break;
                                    case "Log Out":
                                        continueLoop = false;
                                        break;
                                }
                            } else {
                                continueLoop = false;
                            }
                        } while (continueLoop);
                    }
                }
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
