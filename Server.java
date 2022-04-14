import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

    // Reads users file of all usernames and passwords
    // If username and password is not there returns true and adds them
    // If username and password is there returns false
    public static String addUser(String username, String password) {
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
    public static void deleteCourse(String courseName) {
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
    public static String[] getQuiz(String quizFileName) {
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

        // Passing 6 Variables Per Question (Question Statement, 4 Options, and Correct
        // Option) + Quiz Name

        ArrayList<String> tempQuestionsList = new ArrayList<>();

        tempQuestionsList.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
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
    public static void addAttempt(String username, String courseName, String quizName, int attemptNumber) {
        String quizFileName = username + "@" + courseName + "@" + quizName + "@" + String.valueOf(attemptNumber)
                + ".txt";
        try {
            FileOutputStream fos = new FileOutputStream("quizAttempts.txt", true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(quizFileName);
            pw.close();

        } catch (FileNotFoundException e) {
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
    public static void writeToProgressFile(String progressFile, String quizName, String question, boolean correct) {
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

    //Writes the score of the quiz at the end
    public static void gradeQuiz(String progressFile) {
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

    //Returns string array of completed quizzes for a specific student user
    public static String[] getCompletedQuizzesList(String username) {
        ArrayList<String> list = new ArrayList<>();
        try {
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

    //Returns quiz score for specific quiz
    public static String getQuizScore(String progressFile) {
        ArrayList<String> list = new ArrayList<>();
        try {
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

    //Returns string list of all student usernames
    public static String[] getStudentUsernames() {
        ArrayList<String> list = new ArrayList<>();
        try {
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

    //Returns true if file is in correct format
    //Returns false if file is in incorrect format
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

    //Writes quiz to quiz file and returns quiz title
    public static String uploadQuiz(String course, String filename) {
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

    //adds quiz name to course file that keeps track of its quizzes
    public static void addQuizToCourse(String quizName, String course) {
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

    public static void deleteQuiz(String courseName, String quizName) {
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
            }
        }
    }

    public static void changeTeacherPassword(String username, String password, String newPassword) {
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

    public static void changeStudentPassword(String username, String password, String newPassword) {
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
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

        while (true) {
            ServerSocket serverSocket = new ServerSocket(9999);
            Socket socket = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            String identification = reader.readLine();
            String loginOption = reader.readLine();
            String username = reader.readLine();
            String password = reader.readLine();

            if (identification != null && loginOption != null && username != null && password != null) {
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
                            if (teacherOption != null) {
                                switch (teacherOption) {
                                    case "Add Course":
                                        String courseName = reader.readLine();
                                        String addCourseSuccess;
                                        if (courseName != "null") {
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
                                        if (courseDeletionOption != "null") {
                                            deleteCourse(courseDeletionOption);
                                        } else {
                                            continueLoop = false;
                                        }
                                        break;
                                    case "Create a Quiz":
                                        break;
                                    case "Edit a Quiz":
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
                                        if (courseChoice != "null") {
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
                                            if (quizChoice != "null") {
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
                                        if (courseOption != "null") {
                                            String inputFileName = reader.readLine();
                                            if (inputFileName != "null") {
                                                boolean valid = checkFileValidity(inputFileName);
                                                if (valid) {
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
                            if (studentOption != null) {
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
                                                    String[] quizList = getQuizInProgress(
                                                            (courseOption + "@" + quizOption + ".txt"),
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
                                                    gradeQuiz(quizProgressFilename);
                                                } else {
                                                    String quizProgressFilename = username + "@" + courseOption +
                                                            "@" + quizOption + "@" +
                                                            getAttempt(username, courseOption, quizOption) + ".txt";
                                                    String[] quizList = getQuiz(
                                                            courseOption + "@" + quizOption + ".txt");
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
                                                    gradeQuiz(quizProgressFilename);
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
                                            writer.write(score);
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
            serverSocket.close();
            socket.close();
        }
    }
}
