Student Name: Karol Zdebel
Student Number: 0892519

Design/Strategy: 
	The software is designed and perform in a way that simulates a chat room 
hosted on a server. A user joining the server is simulated by the ChatRoomManager object
which spawns a user session. Every user session is stored inside the ChatRoom object
which simulates the server. Any communication between any two user sessions takes place
using the ChatRoom object's methods and local data. Upon starting a session, users are 
prompted to input some basic information including a nickname, gender and age. When 
messaging in the public chat room, all users can see each other messages however,
the usernames are anonymous. Users may however view each specific users profile in the 
chatroom, but they will not be able to tell which message is associated with which profile.
Private messaging sessions may be started between two specific users, in which case anonymity
is removed and both users can see who is sending the message. This still does not compromise 
the anonymity in the public chat.

Program Compilation:
	In the assignment directory are included all the folders associated with the NetBeans
project of this application. The source files located in the "src" folder can be compiled 
using "javac *.java" inside the src/chatroom folder, and then can be ran using "java chatroom.ChatRoom"
inside the src folder. The jre file can be located inside the dist folder. 

Program Features:
	After launching the program, you may begin lauching user sessions by pressing the
"Add User" button inside the ChatRoom Manager window. After filling out the Nickname,
Age, and Gender you can press Join to enter the ChatRoom with a particular session.
Messages can be sent in the public chat by typing in the textfield at the bottom of the
chatroom window, after which either Enter or the "Send" button can be pressed to send.
To identify which session is which, the username appears in the title of the window.
If you'd like to private message a specific user then simply select their username
in the right-hand window and press the "Message" button. If you'd like to view
the details of their profile then press the "View Profile" button instead. By closing
a particular User Session window, every other user session will be notified that a user
has left. Same thing happens when a new user session starts. 