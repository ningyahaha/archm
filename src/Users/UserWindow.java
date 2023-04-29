package Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;

public class UserWindow extends JFrame {
    private String admin_name;

    public void initComponents(String name,int index){
        admin_name = name;


        //--------this--------
        setTitle("用户管理界面");
        setSize(380,300);
        setLocationRelativeTo(null);
        setResizable(false);


        //--------添加用户界面--------
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        JPanel panel_add=new JPanel();
        panel_add.setLayout(null);
        tabbedPane.addTab("添加用户", panel_add);
        JLabel L1=new JLabel("账号:");
        JTextField te1=new JTextField(25);
        JLabel L2=new JLabel("密码:");
        JPasswordField te2=new JPasswordField(25);
        JLabel L3=new JLabel("角色:");
        String role[]= {"administrator","operator","browser"};
        JComboBox<String> box=new JComboBox<String>(role);
        te2.setEchoChar('*');
        L1.setFont(new Font("黑体",Font.PLAIN,18));
        L1.setSize(50,30);
        L1.setBounds(5+30,30,50,30);
        te1.setFont(new Font("黑体",Font.PLAIN,18));
        te1.setSize(250,30);
        te1.setBounds(5+80,30,250,30);
        L2.setFont(new Font("黑体",Font.PLAIN,18));
        L2.setSize(50,30);
        L2.setBounds(5+30,80,50,30);
        te2.setFont(new Font("黑体",Font.PLAIN,18));
        te2.setSize(250,30);
        te2.setBounds(5+80,80,250,30);
        L3.setFont(new Font("黑体",Font.PLAIN,18));
        L3.setSize(50,30);
        L3.setBounds(5+30,130,50,30);
        box.setFont(new Font("黑体",Font.PLAIN,18));
        box.setSize(200,30);
        box.setBounds(5+80,130,200,30);
        panel_add.add(L1);
        panel_add.add(te1);
        panel_add.add(L2);
        panel_add.add(te2);
        panel_add.add(L3);
        panel_add.add(box);


        //---------button--------
        JButton B1=new JButton("添加");
        JButton B2=new JButton("退出");
        B1.setSize(80,40);
        B1.setBounds((380-80-5)/2-60,(300-40-30)/2+50,80,40);
        B2.setSize(80,40);
        B2.setBounds((380-80-5)/2+60,(300-40-30)/2+50,80,40);
        panel_add.add(B1);
        panel_add.add(B2);
        B1.addActionListener(new ButtonHandler(this,te1,te2,box));
        B2.addActionListener(new ButtonHandler(this));


        //-------首先先获取到所有成员---------
        String[] columnName={"用户名","密码","角色"};
        String[][] rowData=new String[50][3];
        Enumeration<User> e=null;
        try {
            e = DataProcessing.getAllUser();
        }
        catch (SQLException e1) {
            e1.printStackTrace();;
        }
        User user;
        String[] nameData=new String[50];
        int i=0;
        while(e.hasMoreElements()) {
            user=e.nextElement();
            nameData[i]=rowData[i][0]=user.getName();
            rowData[i][1]=user.getPassword();
            rowData[i][2]=user.getRole();
            i++;
        }



        //--------修改用户界面---------
        JPanel panel_mod=new JPanel();
        panel_mod.setLayout(null);
        tabbedPane.addTab("修改用户", panel_mod);
        JLabel L4=new JLabel("账号:");
        JComboBox<String> box_name=new JComboBox<String>(nameData);
        JLabel L5=new JLabel("密码:");
        JPasswordField te4=new JPasswordField(25);
        JLabel L6=new JLabel("角色:");
        JComboBox<String> box2=new JComboBox<String>(role);
        te4.setEchoChar('*');
        L4.setFont(new Font("黑体",Font.PLAIN,18));
        L4.setSize(50,30);
        L4.setBounds(5+30,30,50,30);
        box_name.setFont(new Font("黑体",Font.PLAIN,18));
        box_name.setSize(250,30);
        box_name.setBounds(5+80,30,250,30);
        L5.setFont(new Font("黑体",Font.PLAIN,18));
        L5.setSize(50,30);
        L5.setBounds(5+30,80,50,30);
        te4.setFont(new Font("黑体",Font.PLAIN,18));
        te4.setSize(250,30);
        te4.setBounds(5+80,80,250,30);
        L6.setFont(new Font("黑体",Font.PLAIN,18));
        L6.setSize(50,30);
        L6.setBounds(5+30,130,50,30);
        box2.setFont(new Font("黑体",Font.PLAIN,18));
        box2.setSize(200,30);
        box2.setBounds(5+80,130,200,30);
        panel_mod.add(L4);
        panel_mod.add(box_name);
        panel_mod.add(L5);
        panel_mod.add(te4);
        panel_mod.add(L6);
        panel_mod.add(box2);

        //---------button--------
        JButton B3=new JButton("修改");
        JButton B4=new JButton("退出");
        B3.setSize(80,40);
        B3.setBounds((380-80-5)/2-60,(300-40-30)/2+50,80,40);
        B4.setSize(80,40);
        B4.setBounds((380-80-5)/2+60,(300-40-30)/2+50,80,40);
        panel_mod.add(B3);
        panel_mod.add(B4);
        B3.addActionListener(new ButtonHandler(this,box_name,te4,box2));
        B4.addActionListener(new ButtonHandler(this));



        //--------删除用户界面---------
        JPanel panel_del=new JPanel();
        panel_del.setLayout(null);
        tabbedPane.addTab("删除用户", panel_del);
        //创建一个表格 显示所有用户信息
        @SuppressWarnings("serial")
        JTable table=new JTable(rowData,columnName) {//创建表格时指定表格行元素 还有表头名称
            public boolean isCellEditable(int rowIndex,int ColIndex) {
                return false;
            }
        };
        table.setFont(new Font("黑体",Font.PLAIN,18));
        //getSelectionModel()返回 ListSelectionModel用来维持行选择状态。
        //ListSelectionModel.SINGLE_SELECTION - 一次只能选择一个列表索引
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll=new JScrollPane(table);
        scroll.setVisible(true);
        scroll.setSize(380,160);
        scroll.setBounds(0,0,380,160);
        panel_del.add(scroll);

        //--------button--------
        JButton B5=new JButton("删除");
        JButton B6=new JButton("退出");
        B5.setSize(80,40);
        B5.setBounds((380-80-5)/2-60,(300-40-30)/2+50,80,40);
        B6.setSize(80,40);
        B6.setBounds((380-80-5)/2+60,(300-40-30)/2+50,80,40);
        panel_del.add(B5);
        panel_del.add(B6);
        B5.addActionListener(new ButtonHandler(this,table));
        B6.addActionListener(new ButtonHandler(this));

        //--------this-------
        tabbedPane.setVisible(true);
        tabbedPane.setSelectedIndex(index);
        add(tabbedPane);
        setVisible(true);

    }


    public class ButtonHandler implements ActionListener {
        public JTextField te1=new JTextField();
        public JPasswordField te2=new JPasswordField();
        public JFrame frame=new JFrame();
        //组合按钮或可编辑字段和下拉列表的组件。
        // 用户可以根据用户的请求从下拉列表中选择一个值。
        // 如果使组合框可编辑，则组合框包含用户可以键入值的可编辑字段。
        public JComboBox<String> box=new JComboBox<String>();//角色
        public JComboBox<String> box_name=new JComboBox<String>();//姓名
        public JTable table;
        public String role;
        ButtonHandler(JFrame frame){
            this.frame=frame;
        }//取消
        ButtonHandler(JFrame frame,JTextField te1,JPasswordField te2,JComboBox<String> box){//添加
            this.frame=frame;
            this.te1=te1;
            this.te2=te2;
            this.box=box;
        }
        ButtonHandler(JFrame frame,JComboBox<String> box_name,JPasswordField te2,JComboBox<String> box) {//修改
            this.frame=frame;
            this.box_name=box_name;
            this.te2=te2;
            this.box=box;
        }
        ButtonHandler(JFrame frame,JTable table) {//删除
            this.frame=frame;
            this.table=table;
        }
        public void actionPerformed(ActionEvent e) {
            //首先得到名字和密码
            String name = te1.getText();
            String password = String.valueOf(te2.getPassword());
            //若按钮的文本为修改
            if(e.getActionCommand().equals("修改")){
                //返回当前所选项目
                name = (String)box_name.getSelectedItem();
                try {
                    if(DataProcessing.searchUser(name)!=null){
                        role = (String)box.getSelectedItem();
                        //更新用户
                        DataProcessing.updateUser(name,password,role);
                        JOptionPane.showMessageDialog(null, "修改成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
                        //修改用户的界面关闭
                        frame.dispose();
                        //再次打开该用户的用户管理界面
                        UserWindow userWindow = new UserWindow();
                        userWindow.initComponents(admin_name,1);


                    } else {
                        JOptionPane.showMessageDialog(null, "账号不存在", "温馨提示", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }


            else if(e.getActionCommand().equals("添加")){
                role = (String)box.getSelectedItem();
                try {
                    DataProcessing.insertUser(name,password,role);
                    JOptionPane.showMessageDialog(null, "添加成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                    UserWindow userWindow=new UserWindow();
                    userWindow.initComponents(admin_name,0);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            }

            else if(e.getActionCommand().equals("删除")){
                //返回第一个选定行的索引，如果没有选择行，则返回-1。
                //获取选中的第一行 table.getSelectedRow();
                if(table.getSelectedRow()<0) ;
                else {
                    String name_del = (String) table.getValueAt(table.getSelectedRow(),0);
                    //若删除的名字与正在使用的名字相同
                    if(name_del.equals(admin_name)){
                        JOptionPane.showMessageDialog(null, "无法删除", "温馨提示", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        try {
                            if(DataProcessing.deleteUser(name_del)){
                                JOptionPane.showMessageDialog(null, "删除成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(null, "账号不存在", "温馨提示", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                        frame.dispose();
                        UserWindow userWindow=new UserWindow();
                        userWindow.initComponents(admin_name,2);
                    }
                }
            }

            else if(e.getActionCommand().equals("退出")){
                frame.dispose();
            }
        }



    }


}
