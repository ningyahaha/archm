package Users;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

//系统管理员
public class Administrator extends User{
    public Administrator(String name, String password, String role) {
        super(name, password, role);
    }

    public void showMenu()
    {
        String amenu = "欢迎进入系统管理员菜单";
        String ainfos = "****" + amenu + "********\n\t     "
                + "1.修改用户\n    \t 2.删除用户\n    \t 3.新增用户\n    \t 4.列出用户\n    \t 5.下载文件\n    \t 6.文件列表\n    \t 7.修改(本人)密码\n    \t 8.退  出\n" +
                "******************************";
        System.out.println(ainfos);
    }



    public void delUser()
    {
        System.out.println("删除用户");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String name = scanner.next();
        DataProcessing dataProcessing = new DataProcessing();
        try {
            if(dataProcessing.deleteUser(name))
            {
                System.out.println("删除成功!");
            } else {
                System.out.println("删除失败！");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public void addUser()
    {
        System.out.println("新增用户");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String name = scanner.next();
        System.out.print("请输入口令：");
        String password = scanner.next();
        System.out.print("请输入角色：");
        String role = scanner.next();
        DataProcessing dataProcessing = new DataProcessing();
        try {
            if(dataProcessing.insertUser(name,password,role))
            {
                System.out.println("添加成功！");
            }else{
                System.out.println("添加失败！");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    //列出用户
    public void listUser()
    {
        //就是列出哈希表中的各位
        System.out.println("列出用户");
        DataProcessing dataProcessing = new DataProcessing();
        try {
            //如果捕获到了错误 后面的代码将不再执行
            Enumeration<User> e = dataProcessing.getAllUser();
            //用e输出
            while(e.hasMoreElements())
            {
                //next element()获取e中的下一个元素 连续调用nextElement方法返回系列的连续元素
                //从外部访问
                User use = e.nextElement();
                String name = "Name:"+use.getName();
                String password = "Password:"+use.getPassword();
                String role = "Role:"+use.getRole();
                System.out.println(name+"\t"+password+"\t"+role);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    //修改用户密码
    public void changeUserInfo()
    {
        System.out.println("修改用户");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String uname = scanner.next();
        System.out.print("请输入口令：");
        String upassword = scanner.next();
        System.out.print("请输入角色：");
        String urole = scanner.next();
        DataProcessing dataProcessing = new DataProcessing();
        //写用户信息到存储
        //uname要存在原先的存储中
        try {
            if(dataProcessing.updateUser(uname,upassword,urole))
            {
                System.out.println("修改成功");
            } else {
                System.out.println("修改失败");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void changeSelfInfo()
    {

        System.out.println("修改本人密码");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入新口令：");
        String newpassword = scanner.next();
        //直接调用user的改变密码函数将user本人的密码更改 并将用户信息写到存储
        try {
            if(super.changeSelfInfo(newpassword))
            {

            } else {
                System.out.println("修改失败！");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void downloadFile()
    {
        System.out.println("下载文件");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入档案号：");
        String filenum = scanner.next();
        //处理异常
        try {
            if(super.downloadFile(filenum))
                System.out.println("下载成功！");
            else
                System.out.println("下载失败！");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void showFileList()
    {
        System.out.println("文件列表");
        try {
            super.showFileList();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
