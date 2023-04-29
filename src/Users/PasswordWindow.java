package Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PasswordWindow {
    String user_name;
    String user_role;

    public void showMenu(String name) {
        //--------获取姓名 以及角色
        user_name = name;
        try {
            user_role = DataProcessing.searchUser(user_name).getRole();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        JFrame frame = new JFrame();
        frame.setTitle("密码修改界面");
        frame.setSize(380, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        JLabel L1 = new JLabel("账号:");
        JTextField te1 = new JTextField(25);
        JLabel L2 = new JLabel("旧密码:");
        JPasswordField te2 = new JPasswordField(25);
        te2.setEchoChar('*');
        JLabel L3 = new JLabel("新密码:");
        JPasswordField te3 = new JPasswordField(25);
        te3.setEchoChar('*');
        JLabel L4 = new JLabel("重复密码:");
        JPasswordField te4 = new JPasswordField(25);
        te4.setEchoChar('*');
        JLabel L5 = new JLabel("角色:");
        JTextField te5 = new JTextField(25);
        L1.setFont(new Font("黑体", Font.PLAIN, 18));
        L1.setSize(100, 30);
        L1.setBounds(5, 30, 100, 30);
        te1.setFont(new Font("黑体", Font.PLAIN, 18));
        te1.setSize(250, 30);
        te1.setBounds(5 + 100, 30, 250, 30);
        L2.setFont(new Font("黑体", Font.PLAIN, 18));
        L2.setSize(100, 30);
        L2.setBounds(5, 80, 100, 30);
        te2.setFont(new Font("黑体", Font.PLAIN, 18));
        te2.setSize(250, 30);
        te2.setBounds(5 + 100, 80, 250, 30);
        L3.setFont(new Font("黑体", Font.PLAIN, 18));
        L3.setSize(100, 30);
        L3.setBounds(5, 130, 100, 30);
        te3.setFont(new Font("黑体", Font.PLAIN, 18));
        te3.setSize(250, 30);
        te3.setBounds(5 + 100, 130, 250, 30);
        L4.setFont(new Font("黑体", Font.PLAIN, 18));
        L4.setSize(100, 30);
        L4.setBounds(5, 180, 100, 30);
        te4.setFont(new Font("黑体", Font.PLAIN, 18));
        te4.setSize(250, 30);
        te4.setBounds(5 + 100, 180, 250, 30);
        L5.setFont(new Font("黑体", Font.PLAIN, 18));
        L5.setSize(100, 30);
        L5.setBounds(5, 230, 100, 30);
        te5.setFont(new Font("黑体", Font.PLAIN, 18));
        te5.setSize(250, 30);
        te5.setBounds(5 + 100, 230, 250, 30);
        frame.add(L1);
        frame.add(L2);
        frame.add(L3);
        frame.add(L4);
        frame.add(L5);
        frame.add(te1);
        frame.add(te2);
        frame.add(te3);
        frame.add(te4);
        frame.add(te5);
        te1.setText(user_name);
        te1.setEnabled(false);
        te5.setText(user_role);
        te5.setEnabled(false);
        JButton B1 = new JButton("确认");
        JButton B2 = new JButton("退出");
        B1.setSize(80, 40);
        B1.setBounds((380 - 80 - 5) / 2 - 60, 300, 80, 40);
        B2.setSize(80, 40);
        B2.setBounds((380 - 80 - 5) / 2 + 60, 300, 80, 40);
        B1.addActionListener(new ButtonHandler(frame,te2,te3,te4));
        B2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(B1);
        frame.add(B2);
        frame.setVisible(true);
    }

    public class ButtonHandler implements ActionListener{
        JFrame frame;
        JTextField te2;
        JTextField te3;
        JTextField te4;
        ButtonHandler(JFrame frame,JTextField te2,JTextField te3,JTextField te4){
            this.frame=frame;
            this.te2=te2;
            this.te3=te3;
            this.te4=te4;
        }
        public void actionPerformed(ActionEvent e) {
            String old_password=te2.getText();
            String new_password=te3.getText();
            String new_password2=te4.getText();
            if(e.getActionCommand()=="确认") {
                try {
                    if(DataProcessing.searchUser(user_name, old_password)!=null) {
                        if(new_password.equals(new_password2)) {
                            DataProcessing.updateUser(user_name, new_password, user_role);
                            frame.dispose();
                            JOptionPane.showMessageDialog(null, "修改成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "两次输入的新密码不一致", "温馨提示", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "密码错误", "温馨提示", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            else if(e.getActionCommand()=="退出"){
                frame.dispose();
            }
        }
    }


}

