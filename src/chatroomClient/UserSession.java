package chatroomClient;

import chatroom.Message;
import chatroom.User;
import chatroom.UserActivity;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author Karol Zdebel
 * 
 * Class responsible for storing information and functionality related
 * to the user chat session, including private chats, and public chats.
 */
public final class UserSession {
    
    private User user;  //User associated with this user session
    private ChatRoomFrame chatRoomFrame;    //Chat room GUI 
    private UserRegisterFrame userRegisterFrame;    //Registration GUI
    private final Hashtable<User,PrivateChatFrame> privateChats;    //Private chats GUI
    private boolean joined; //Whether the user session is already in chat or registering
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;
    private ArrayList<User> chatUsers;
    private ArrayList<User> permissionList;
    
    public static void main(String[] args){
        UserSession userSession = new UserSession();
    }
    
    //Create User Session instance
    public UserSession(){
        privateChats = new Hashtable<>();
        chatUsers = new ArrayList<>(); 
        permissionList = new ArrayList<>();
        this.joined = false;
        startRegisterSession();
    }    
    
    public void anotherUserJoined(User user){
        chatUsers.add(user);
    }
    
    public void anotherUserLeft(User user){
        chatUsers.remove(user);
    }
    
    public ObjectInputStream getInStream(){
        return inStream;
    }
    
    //Create private chat session
    public void createPrivateChatSession(User recipient, PrivateChatFrame frame){
        privateChats.put(recipient, frame);
    }
    
    public final void connectToHost(){
        try{
            Socket host = new Socket("159.203.32.136",49153);
            inStream = new ObjectInputStream(host.getInputStream());
            outStream = new ObjectOutputStream(host.getOutputStream());
            
            System.out.println("Connected to host and established streams successfully.");
            
        }catch(Exception e){
            System.err.print("Error connecting to host!:"+e.getMessage());
        }
    }
    
    public void sendActivity(UserActivity activity) throws IOException{
        System.out.println("Sending activity:"+activity.toString());
        outStream.writeObject(activity);
    }
    
    public void startRegisterSession(){

        //Begin listening to server side messages now that we're in chat
        System.out.println("Attempting to connect to host!");
        connectToHost();
        
        //Begin listening to server side communication
        Thread listen = new Thread(new ServerActivityListen(this));
        listen.start();
        
        //Create register GUI Frame
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserSession.this.userRegisterFrame = new UserRegisterFrame(UserSession.this);
                UserSession.this.userRegisterFrame.setVisible(true);
            }
        });
    }
    
    //Get user associated with this session
    public User getUser(){
        return user;
    }
    
    //Start public chat session
    void startChatSession(){
        
        joined = true;
        
        //Create Chat GUI Frame
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserSession.this.chatRoomFrame = new ChatRoomFrame(UserSession.this,UserSession.this.user);
                UserSession.this.chatRoomFrame.setVisible(true);
            }
        });
    }
    
    public void showNotification(String notif){
        chatRoomFrame.showChatNotification(notif);
    }
    
    public boolean hasJoined(){
        return joined;
    }
    
    public void hideUser(User user){
        chatRoomFrame.hideUser(user);
    }
    
    public void showMessage(Message message){
        chatRoomFrame.getChatRoomPanel().showMessage(message);
    }
    
    public void showMessage(Message message,User sender){
        
        //if this user hasn't started the private chat session then start one
        if (privateChats.get(sender) == null){
            
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    
                     PrivateChatFrame newPrivateChat = new PrivateChatFrame(UserSession.this,sender);
                     UserSession.this.privateChats.put(sender,newPrivateChat);
                     newPrivateChat.setVisible(true);
                     
                     //show message on recipients screen
                     UserSession.this.privateChats.get(sender).showMessage(message);

                }
            });
        }       
        //User already has a private chat GUI open, simply display message
        else{
            UserSession.this.privateChats.get(sender).showMessage(message);
        }
                
        
    }
    
    //Notify server user leaves the chat room
    public void leave(){
        UserActivity a = new UserActivity(getUser(),UserActivity.ACT_USER_LEAVE);
        try{
            sendActivity(a);
        }catch(Exception e){
            System.err.print("Could not send activity to host: "+e.getMessage());
        }
        
    }
    
    //Add user to chat room
    public void addUser(String nickname, int age, char gender){
        this.user = new User(nickname,gender,age);
        UserActivity a = new UserActivity(getUser(),UserActivity.ACT_USER_JOIN);
        try{
            sendActivity(a);
        }catch(Exception e){
            System.err.print("Could not send activity to host: "+e.getMessage());
        }
    }
    
    public void gotPermission(User from){
        permissionList.add(from);
        chatRoomFrame.givePermission(from);
    }
    
    public ArrayList<User> getPermissionList(){
        return this.permissionList;
    }
    
    
    //Give permission to a specific user
    public void givePermission(User to){
        UserActivity a = new UserActivity(this.getUser(),to);
        try{
            sendActivity(a);
        }catch(Exception e){
            System.err.print("Could not send activity to host: "+e.getMessage());
        }
    }
    
    //Send message in public chat room
    public void sendMessage(String input){
        Message message = new Message(input);
        UserActivity a = new UserActivity(message);
        try{
            sendActivity(a);
        }catch(Exception e){
            System.err.print("Could not send activity to host: "+e.getMessage());
        }
    }
    
    //Send message in private chat room
    public void sendMessage(String input, User recipient){
        Message message = new Message(input,user,recipient);
        UserActivity a = new UserActivity(message);
        
        try{
            sendActivity(a);
        }catch(Exception e){
            System.err.print("Could not send activity to host: "+e.getMessage());
        }
    }
    
    public ArrayList<User> getAllChatUsers(){
        return chatUsers;
    }
    
    //Check whetehr nickname is unique
    public boolean chatHasNickname(String nickname){
        for (User u: chatUsers){
            if (u.getNickname().equals(nickname)){
                return true;
            }
        }
        return false;
    }
    
    //Display user in user log
    public void showUser(User user){
        chatRoomFrame.showUser(user);
    }
    

    
}
