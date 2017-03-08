
package chatroomClient;

import chatroom.Message;
import chatroom.User;
import chatroomClient.UserSession;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Karol Zdebel
 * 
 * Class responsible for displaying private chat GUI to a particular user
 * and allowing for the typing and sending of messages.
 */

final public class PrivateChatFrame extends javax.swing.JFrame {

    private UserSession mySession;
    private User recipient;
    private static final long serialVersionUID = 0;

    /**
     * Creates new form PrivateChatFrame
     * @param session User Session associated with this private chat
     * @param recipient User receiving the messages
     */
    
    public PrivateChatFrame(UserSession session, User recipient) {
  
        mySession = session;
        this.recipient = recipient;
        mySession.createPrivateChatSession(recipient, this);
        
        //set title to who the private chat is between
        this.setTitle(mySession.getUser().getNickname()+": Private chat with "+recipient.getNickname());
        
        initComponents();
        
        //Let user send message by pressing enter
        getRootPane().setDefaultButton(sendMessageButton);
        
        //provide a label displaying who the chat is with
        this.jLabel1.setText("Private Chat with "+recipient.getNickname());
        
        //don't close app upon closing this frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //Display the message in private chat frame
    public void showMessage(Message message){
        
        if(chatLog.getText().isEmpty()){
            chatLog.append(message.getContent());
        }else{
            chatLog.append("\n"+message.getContent());
        }
    }
    
    //Display the notification in private chat frame
    public void showNotifcation(String notif){
        if(chatLog.getText().isEmpty()){
            chatLog.append(notif);
        }else{
            chatLog.append("\n"+notif);
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chatLog = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        sendMessageButton = new javax.swing.JButton();
        sendMessageTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        chatLog.setEditable(false);
        chatLog.setColumns(20);
        chatLog.setRows(5);
        jScrollPane1.setViewportView(chatLog);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Private Chat");

        sendMessageButton.setText("Send");
        sendMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sendMessageTextField)
                        .addGap(1, 1, 1)
                        .addComponent(sendMessageButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendMessageButton)
                    .addComponent(sendMessageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Behaviour executed when send button is pressed, contains message sending
    //functonality
    private void sendMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageButtonActionPerformed
        
        String userMessage = sendMessageTextField.getText();
        String err = "";
        
        //Check that the message isn't too long or empty
        if (userMessage.isEmpty()){
            err += "\nCannot send empty message.";
        }
        else if (userMessage.length() > 100){
            err += "\nYour message is "+userMessage.length()+" characters long, keep it less than 100 characters. ";
        }
        
        //If no errors exist send the message, prompt user to fix errors otherwise
        if (err.isEmpty()){
            sendMessageTextField.setText("");
            mySession.sendMessage(userMessage,recipient);
        }else{
            JOptionPane.showMessageDialog(this, err, "Message Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_sendMessageButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatLog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton sendMessageButton;
    private javax.swing.JTextField sendMessageTextField;
    // End of variables declaration//GEN-END:variables
}