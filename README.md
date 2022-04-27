# Project5Group10

Jaden Gant - Submitted Report on Brightspace. Jaden Gant - Submitted Vocareum workspace.

Note: When using any text fields, never use the word "Random", "null", or the symbol "@".
      Also, any time client.java closes or ends, server.java will continue running. 

This project has two classes, Server and Client. In order to run the program, first run Server.java. That will open the server. Then, run Client.java in
a different terminal, this will open the client side. A pop up window for login will appear and the user can begin using program. 
Multiple users can use this program at the same time. To do this, open up another terminal and run Client.java. Now multiple users can user this program
concurrently.

The client class handles all GUI. This program utilizes both simple GUI and complex GUI, but it uses JOptionPane much more. Users enter information into the client class' GUI, and the client sends over that information to the server using network IO. Then the server, sends back the necessary information to the client side and the client creates GUI's with this information. This is how the client class and server class interact with eachother on a basic level.

The server class handles storing and retrieving all information. This is done with file IO and all file readings and writings are done in methods in the server class. Additionally, the server class handles all multithreading. 

Once the client and server are connected, the program begins by prompting the user to enter if they are a student or teacher using a drop down menu and then clicking ok. Then they are prompted to enter if they want to login or create an account using a drop down menu. If the user chooses create an account, they will be prompted to enter a username and password using two JOptionPane input dialogs. If the username and password are valid, meaning that the username is not already taken the account will be created and the user will be logged in. However, if the user leaves a text field empty or if the username is taken, they are prompted with the appropriate error message and client.java ends. If the user chooses login, they will be prompted to enter a username and password using two JOptionPane input dialogs. If the username and password are valid, meaning that an account with the same username and password has already been created, the user will be logged on. However, if the user leaves a text field empty or an account with the same username and password has not yet been created, they are prompted with the appropriate error message and client.java ends. If at any point the user selects the exit button on the top right or cancel, client.java will end. 

Once a user is logged on, they are given options via a drop down menu on what they can do based on if they are a student or teacher account. 
The teacher account can add course, delete a course, create a quiz, edit a quiz, delete a quiz, upload a quiz, view scores, edit account, delete account, and log out. All courses, quizzes, scores, etc are viewable/editable (when applicable) by all teachers.
The student account can take a quiz, view scores, edit account, delete account, and log out.
These drop down menus are considered the main menu, and the student or teacher will be returned to their respective main menu each time they complete thier a task. Students can take any quiz in any course, however they can only view their own scores.

I will now explain the progression of what happens when each task is selected.
Note: Any time the user clicks the exit button or cancel, client.java will close.

Teacher:

add course - the teacher is prompted to add a course name using an input dialog. If the course name is already in use, the user will be prompted and returned to the main menu. If the course name is available, the course will be created under that name. 

delete course - the teacher is prompted to choose a course to delete via a drop down menu of all current courses. The course the teacher selects will be deleted as a course. Therefore, the course and all of its quizzes will not be viewable/editable/takeable by any teachers or students.

create a quiz - the teacher is prompted to choose a course to enter via a drop down menu. If no course is created yet, the appropriate error message will appear and the teacher will returned to the main menu. Once the teacher chooses a course, they are prompted to enter a title. If there is already a quiz under the same name, the appropriate error message will appear and the teacher will be returned to the main menu. Once the title is entered, a complex gui will appear, where the teacher will enter a quiz question, the correct answer to the question, and three other incorrect asnwers to the question. The teacher will submit this with the "submit" button. If any fields are left blank, the appropriate error message will appear and the teacher will be returned to the main menu. Once a teacher submits a question, they are given the option for if they would like to make this quiz have randomized question orders via a drop
down menu. After choosing this, it is at this point that the quiz is created. The teacher will then enter a loop that prompts them to choose whether or not to add another question via a drop down menu. If they choose yes, they will add a question the same as before and after they submit they will be prompted again to choose to add another question. Once they choose no, the teacher is returned to the main menu.

edit a quiz - the teacher is prompted to choose a course to enter via a drop down menu. If no course is created yet, the appropriate error message will appear and the teacher will returned to the main menu. Once the teacher chooses a course, they are prompted to choose a quiz to enter via a drop down menu.
If no quiz created yet, the appropriate error message will appear and the teacher will returned to the main menu. Once the teacher chooses a quiz, they are prompted to choose to either add or delete a question. If the teacher chooses to add a question, a complex gui will appear, where the teacher will enter a quiz question, the correct answer to the question, and three other incorrect asnwers to the question. The teacher will submit this with the "submit" button. If any fields are left blank, the appropriate error message will appear and the teacher will be returned to the main menu. Once a teacher submits a question,
they are returned to the main menu. If the teacher chooses to delete a question, they are prompted to choose a question to delete via a drop down menu of all the questions in that specific quiz. Once a question is chosen, that specific question is deleted from the chosen quiz and the teacher is returned to the main menu.

delete a quiz - the teacher is prompted to choose a course to enter via a drop down menu. If no course is created yet, the appropriate error message will appear and the teacher will returned to the main menu. Once the teacher chooses a course, they are prompted to choose a quiz to enter via a drop down menu.
If no quiz created yet, the appropriate error message will appear and the teacher will returned to the main menu. Once the teacher chooses a quiz, that quiz will be deleted from the course, and the teacher will be returned to the main menu.

upload a quiz - the teacher is prompted to choose a course to enter via a drop down menu. If no course is created yet, the appropriate error message will appear and the teacher will returned to the main menu. Once the teacher chooses a course, they will be prompted to enter the file path of the quiz to upload. IMPORTANT: the format of the quiz to upload must be this: the first line of the file will be the quiz title, the second line will be a question statement, the third, fourth, fifth, and sixth lines will be the different answer choices(don't label them with a, b, c, d), and the seventh line will be correct answer. The string of the correct answer must match the string of the answer choice in one of the previous 4 lines. Then, the next line (line 8) is the next question statement, and so on. Once the file path is entered, if the file is in the incorrect format, the appropriate error message will appear and the 
teacher will returned to the main menu. If the file is in the correct format the appropriate success message will appear and the teacher will be returned to the main menu.


