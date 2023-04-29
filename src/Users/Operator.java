package Users;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

//档案录入人员
public class Operator extends User{
    public Operator(String name, String password, String role) {
        super(name, password, role);
    }

    public void showMenu()
    {
        //展示档案浏览员菜单
        String omenu = "欢迎进入档案录入员菜单";
        String oinfos = "****" + omenu + "********\n\t     "
                + "1.上传文件\n    \t 2.下载文件\n    \t 3.文件列表\n    \t 4.修改密码\n    \t 5.退  出\n" +
                "******************************";
        System.out.println(oinfos);
    }

    public void uploadFile()
    {
        //是Operator独有的方法
        //上传文件
        /*
        1.首先用户输入源文件名 找到源文件 File 创建BufferedInputStream流
        2.一次输入档案号 和档案描述
        3.将上传的文件的Doc插入到Hashtable中
        4.若插入成功 则将文件拷贝到uploadFile目录中
         */
        System.out.println("上传文件");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入源文件名：");
        String filename = scanner.next();
        System.out.print("请输入档案号：");
        String fileid = scanner.next();
        System.out.print("请输入档案描述：");
        String description = scanner.next();
        System.out.println("上传文件... ... ...");


        //目标文件
        byte[] buf = new byte[1024];//来存取读取的内容 Bufferin按字节读取
        File tempfile = new File(filename);
        String name = tempfile.getName();
        String uploadfimename = uploadpath+name;
        //创建文件输入流 输入流
        BufferedInputStream infile = null;
        BufferedOutputStream targetfile = null;

        System.out.println("上传中... ... ...");
        try {
            //BufferedInputStream构造器中一定要传一个InputStream的子类对象
            //FileInputStream构造器中可以传文件path 或者File类型的对象 或者FileDescriptor对象
            //在这一步就判断了文件是否存在 若文件存在 就继续向下
            infile = new BufferedInputStream(new FileInputStream(tempfile));
            //Doc插入Hashtable中
            if(DataProcessing.insertDoc(fileid,this.getName(),new Timestamp(System.currentTimeMillis()),description,name)){
                //若插入成功则开始复制文件
                targetfile = new BufferedOutputStream(new FileOutputStream(uploadfimename));
                int readlen = 0;
                while((readlen = infile.read(buf))!=-1){
                    //边读边写
                    targetfile.write(buf,0,readlen);
                }
                //若复制成功 上传成功
                System.out.println("上传成功");
            } else {
                System.out.println("上传失败");
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally{
            try {
                infile.close();
                targetfile.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }



    }

    public void downloadFile()
    {
        System.out.println("下载文件");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入档案号：");
        String filenum = scanner.next();
        try {
            if(super.downloadFile(filenum))
            {
                System.out.println("下载成功！");
            } else {
                System.out.println("下载失败！");
            }
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
}
