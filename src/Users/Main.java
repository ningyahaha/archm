package Users;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //欢迎进入档案系统 人员登录系统
        String tip_system = "档案系统";
        String tip_menu = "请选择菜单：";
        String tip_exit = "系统退出，谢谢使用！";
        String infos = "****欢迎进入" + tip_system + "****\n\t     "+
                "1.登录\n    \t 2.退出\n"+
                "******************************";
        System.out.println(infos);
        System.out.print(tip_menu);
        Scanner scanner = new Scanner(System.in);
        //输入菜单 1.登录 2.退出
        int n = scanner.nextInt();
        if(n == 1)
        {
            DataProcessing dataProcessing = new DataProcessing();
            System.out.print("请输入用户名：");
            String uname = scanner.next();
            System.out.print("请输入口令：");
            String upassword = scanner.next();
            User user1 = null;
            try {
                //这里的user1相当于一个指针 若利用user1改变什么的话，存储中也会相应地改变
                user1 = dataProcessing.searchUser(uname,upassword);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            //若user1的运行类型是Browsers
            if(user1 instanceof Browser)
            {
                //向下转型 才能调用子类的特有属性
                Browser user = (Browser)user1;
                int choose = 5;
                do {
                    user.showMenu();
                    System.out.print("请选择菜单：");
                    choose = scanner.nextInt();
                    switch (choose)
                    {
                        case 1:user.downloadFile();break;
                        case 2:user.showFileList();break;
                        case 3:user.changeSelfInfo();break;
                        case 4:user.exitSystem();
                        default:
                            System.out.println("请重新选择菜单！");
                            break;
                    }

                }while(choose!=4);

            }

            //若user1的运行类型是Operator
            else if(user1 instanceof Operator)
            {
                Operator user = (Operator)user1;
                int choose = 10;
                do{
                    user.showMenu();
                    System.out.print("请选择菜单：");
                    choose = scanner.nextInt();
                    switch(choose)
                    {
                        case 1:user.uploadFile();break;
                        case 2:user.downloadFile();break;
                        case 3:user.showFileList();break;
                        case 4:user.changeSelfInfo();break;
                        case 5:user.exitSystem();break;
                        default:
                            System.out.println("请重新选择菜单！");
                            break;

                    }
                }while(choose!=5);
            }

            //若user1的运行类型是Administrator
            else if(user1 instanceof Administrator)
            {
                Administrator user = (Administrator)user1;
                int choose = 10;
                do{
                    user.showMenu();
                    System.out.print("请选择菜单：");
                    choose = scanner.nextInt();
                    switch(choose)
                    {
                        case 1:user.changeUserInfo();break;
                        case 2:user.delUser();break;
                        case 3:user.addUser();break;
                        case 4:user.listUser();break;
                        case 5:user.downloadFile();break;
                        case 6:user.showFileList();break;
                        case 7:user.changeSelfInfo();break;
                        case 8:user.exitSystem();break;
                        default:
                            System.out.println("请重新选择菜单！");
                            break;
                    }
                }while(choose!=8);
            }

        }
    }
}
