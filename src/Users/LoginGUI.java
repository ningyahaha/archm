/*
 * Created by JFormDesigner on Sun Nov 27 20:10:50 CST 2022
 */

package Users;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author 1
 */
public class LoginGUI extends JFrame {
    public LoginGUI() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        ResourceBundle bundle = ResourceBundle.getBundle("Archieves");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        passwordField1 = new JPasswordField();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("系统登录");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(null);

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- label1 ----
                label1.setText(bundle.getString("label1.text"));
                contentPanel.add(label1);
                label1.setBounds(50, 20, 60, label1.getPreferredSize().height);

                //---- label2 ----
                label2.setText(bundle.getString("label2.text"));
                contentPanel.add(label2);
                label2.setBounds(50, 65, 60, label2.getPreferredSize().height);
                contentPanel.add(textField1);
                textField1.setBounds(115, 20, 200, textField1.getPreferredSize().height);
                contentPanel.add(passwordField1);
                passwordField1.setBounds(115, 65, 200, passwordField1.getPreferredSize().height);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < contentPanel.getComponentCount(); i++) {
                        Rectangle bounds = contentPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = contentPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    contentPanel.setMinimumSize(preferredSize);
                    contentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPanel);
            contentPanel.setBounds(12, 12, 368, 103);

            //---- okButton ----
            //设置text
            okButton.setText("登录");
            okButton.addActionListener(new ButtonHandler(this,textField1,passwordField1));
            dialogPane.add(okButton);
            okButton.setBounds(95, 120, 80, okButton.getPreferredSize().height);

            //---- cancelButton ----
            cancelButton.setText("取消");
            cancelButton.addActionListener(new ButtonHandler(this));
            dialogPane.add(cancelButton);
            cancelButton.setBounds(220, 120, 80, cancelButton.getPreferredSize().height);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < dialogPane.getComponentCount(); i++) {
                    Rectangle bounds = dialogPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = dialogPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                dialogPane.setMinimumSize(preferredSize);
                dialogPane.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    //监听器 设置内部类
    public class ButtonHandler implements ActionListener{
        public JTextField te1=new JTextField();
        public JPasswordField te2=new JPasswordField();
        public JFrame frame=new JFrame();
        ButtonHandler(JFrame frame){
            this.frame=frame;
        }
        ButtonHandler(JFrame frame,JTextField te1,JPasswordField te2) {
            this.frame=frame;
            this.te1=te1;
            this.te2=te2;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()=="登录"){
                String name = te1.getText();
                String password = String.valueOf(te2.getPassword());
                try {
                    if(DataProcessing.searchUser(name)!=null){
                        if(DataProcessing.searchUser(name,password)!=null){
                            frame.dispose();//登录界面消除
                            //引出菜单界面
                            new MenuGUI(name);
//                            addWindowListener(new WindowAdapter() {
//                                @SuppressWarnings("unused")
//                                public void WindowClosing(WindowEvent e2) {
//                                    DataProcessing.disconnectFromDatabase();
//                                }
//                            });

                        }else {
                            JOptionPane.showMessageDialog(null, "密码错误", "温馨提示", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "账号不存在", "温馨提示", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                //若是取消 则退出界面
                frame.dispose();
            }
        }
    }
}
