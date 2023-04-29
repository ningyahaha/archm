/*
 * Created by JFormDesigner on Sun Nov 27 20:29:39 CST 2022
 */

package Users;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

/**
 * @author 1
 */
public class MenuGUI extends JFrame {

   public MenuGUI(String name){
       initComponents(name);
   }

    private void initComponents(String name) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        ResourceBundle bundle = ResourceBundle.getBundle("Users.guitest");
        menuBar1 = new JMenuBar();
        userMenu = new JMenu();
        changeuse = new JMenuItem();
        deleteuse = new JMenuItem();
        adduse = new JMenuItem();
        fileMenu = new JMenu();
        upfile = new JMenuItem();
        downfile = new JMenuItem();
        personMessageMenu = new JMenu();
        infochange = new JMenuItem();
        panel1 = new JPanel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== userMenu ========
            {
                userMenu.setText("用户管理");

                //---- changeuse ----
                changeuse.setText("修改用户");
                userMenu.add(changeuse);

                //---- deleteuse ----
                deleteuse.setText("删除用户");
                userMenu.add(deleteuse);

                //---- adduse ----
                adduse.setText("添加用户");
                userMenu.add(adduse);
            }
            menuBar1.add(userMenu);

            //======== fileMenu ========
            {
                fileMenu.setText("档案管理");

                //---- upfile ----
                upfile.setText("上传文件");
                fileMenu.add(upfile);

                //---- downfile ----
                downfile.setText("下载文件");
                fileMenu.add(downfile);
            }
            menuBar1.add(fileMenu);

            //======== personMessageMenu ========
            {
                personMessageMenu.setText("个人信息管理");

                //---- infochange ----
                infochange.setText("修改密码");
                personMessageMenu.add(infochange);
            }
            menuBar1.add(personMessageMenu);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setLayout(null);
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 650, 531);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }

        //根据人员权限设置菜单是否可见
        String role = null;
        try {
            role = DataProcessing.searchUser(name).getRole();

            if(role.equals("administrator")){
                this.setTitle("系统管理员界面");
                fileMenu.getItem(0).setEnabled(false);
            } else if(role.equals("browser")){
                this.setTitle("档案浏览员界面");
                userMenu.setEnabled(false);
                fileMenu.getItem(0).setEnabled(false);
            } else if(role.equals("operator")){
                this.setTitle("档案录入人员界面");
                userMenu.setEnabled(false);
            }
        } catch (SQLException e) {
            e.getMessage();
        }


        //---------添加监视器---------
        changeuse.addActionListener(new MenuAction(name));
        deleteuse.addActionListener(new MenuAction(name));
        adduse.addActionListener(new MenuAction(name));
        upfile.addActionListener(new MenuAction(name));
        downfile.addActionListener(new MenuAction(name));
        infochange.addActionListener(new MenuAction(name));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar1;
    private JMenu userMenu;
    private JMenuItem changeuse;
    private JMenuItem deleteuse;
    private JMenuItem adduse;
    private JMenu fileMenu;
    private JMenuItem upfile;
    private JMenuItem downfile;
    private JMenu personMessageMenu;
    private JMenuItem infochange;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    class MenuAction implements ActionListener{
        String name;
        MenuAction(String name){
            this.name=name;
        }
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand()=="添加用户") {
                UserWindow userWindow=new UserWindow();
                userWindow.initComponents(name,0);
            }
            else if(e.getActionCommand()=="修改用户") {
                UserWindow userWindow=new UserWindow();
                userWindow.initComponents(name,1);
            }
            else if(e.getActionCommand()=="删除用户") {
                UserWindow userWindow=new UserWindow();
                userWindow.initComponents(name,2);
            }
            else if(e.getActionCommand()=="上传文件") {
                UpDownloadWindow updownloadWindow=new UpDownloadWindow();
                updownloadWindow.showMenu(name,1);
            }
            else if(e.getActionCommand()=="下载文件") {
                UpDownloadWindow updownloadWindow=new UpDownloadWindow();
                updownloadWindow.showMenu(name,0);
            }
            else if(e.getActionCommand()=="修改密码") {
                PasswordWindow passwordWindow=new PasswordWindow();
                passwordWindow.showMenu(name);
            }
        }
    }


}


