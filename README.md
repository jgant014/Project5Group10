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
add course - the teacher is prompted to add a course name using 
