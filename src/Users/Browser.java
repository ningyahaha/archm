package Users;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

//档案浏览员
public class Browser extends User{
    public Browser(String name, String password, String role) {
        super(name, password, role);
    }

    //下载文件
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

    //只是修改本人密码
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

    public void showMenu()
    {
        //展示档案浏览员菜单
        String bmenu = "欢迎进入档案录入员菜单";
        String binfos = "****" + bmenu + "********\n\t     "
                + "1.下载文件\n    \t 2.文件列表\n    \t 3.修改密码\n    \t 4.退  出\n" +
                "******************************";
        System.out.println(binfos);
    }
}
