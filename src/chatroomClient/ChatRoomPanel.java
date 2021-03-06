package chatroomClient;




import chatroom.Message;
import javax.swing.JOptionPane;

/**
 * @author Karol Zdebel

 * Class responsible for storing all Chat Room GUI elements, and responding to
 * user interactions with the GUI.
 */
public class ChatRoomPanel extends javax.swing.JPanel {

    private ChatRoomFrame myFrame;
    private static final long serialVersionUID = 4;

    
    /**
     * Creates new form ChatRoomFrame
     * @param myFrame used to initialize the frame this Panel is stored inside.
     */
    public ChatRoomPanel(ChatRoomFrame myFrame) {
        
        initComponents();
        this.myFrame = myFrame;
        
        //Let user send message by pressing enter
        myFrame.getRootPane().setDefaultButton(sendMessageButton);
    }

    //Show message in the chat text area
    public void showMessage(Message message){
        if(messageLogTextArea.getText().isEmpty()){
            messageLogTextArea.append(message.getContent());
        }else{
            messageLogTextArea.append("\n"+message.getContent());
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

        jPanel1 = new javax.swing.JPanel();
        sendMessageButton = new javax.swing.JButton();
        sendMessageField = new javax.swing.JTextField();
        messageLogScrollPane = new javax.swing.JScrollPane();
        messageLogTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(400, 360));

        sendMessageButton.setText("Send");
        sendMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessageButtonActionPerformed(evt);
            }
        });

        sendMessageField.setToolTipText("Type your message here");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(sendMessageField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendMessageButton))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendMessageButton)
                    .addComponent(sendMessageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        messageLogScrollPane.setHorizontalScrollBar(null);

        messageLogTextArea.setEditable(false);
        messageLogTextArea.setColumns(20);
        messageLogTextArea.setLineWrap(true);
        messageLogTextArea.setRows(5);
        messageLogScrollPane.setViewportView(messageLogTextArea);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Chat Room");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(messageLogScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageLogScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void showNotifcation(String notif){
        if(messageLogTextArea.getText().isEmpty()){
            messageLogTextArea.append(notif);
        }else{
            messageLogTextArea.append("\n"+notif);
        }
    }
    
    /*
    *Method is called when the user presses the send message button.
    */
    private void sendMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageButtonActionPerformed

        String userMessage = sendMessageField.getText();
        String err = "";

        //Check that the message isn't too long or empty
        if (userMessage.isEmpty()){
            err += "\nCannot send empty message.";
        }
        else if (userMessage.length() > 100){
            err += "\nYour message is "+userMessage.length()+" characters long, keep it less than 100 characters. ";
        }

        //If no erros exist then send the message
        if (err.isEmpty()){
            //send message
            sendMessageField.setText("");
            myFrame.getUserSession().sendMessage(userMessage);
        }
        
        //If errors exist, prompt user to correct input 
        else{
            JOptionPane.showMessageDialog(myFrame, err, "Message Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_sendMessageButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane messageLogScrollPane;
    javax.swing.JTextArea messageLogTextArea;
    private javax.swing.JButton sendMessageButton;
    javax.swing.JTextField sendMessageField;
    // End of variables declaration//GEN-END:variables
}
