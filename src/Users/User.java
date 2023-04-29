package Users;
import java.io.*;
import java.sql.SQLException;
import java.util.Enumeration;


public  class User {
    private String name;
    private String password;
    private String role;

    static String uploadpath = "d:\\homework\\file\\uploadfile\\"; //上传文件的文件目录
    static String downloadpath = "D:\\homework\\file\\downloadfile\\"; //下载文件目录
    User(String name,String password,String role){
        this.name=name;
        this.password=password;
        this.role=role;
    }

    public boolean changeSelfInfo(String password) throws SQLException{
        //写用户信息到存储
        if (DataProcessing.updateUser(name, password, role)){
            this.password=password;
            System.out.println("修改成功");
            return true;
        }else
            return false;
    }

    public boolean downloadFile(String ID) throws IOException,SQLException{
        //用bufferedinout流来读存文件 使用字节流
        byte[] buffer = new byte[1024];
        //1.根据档案号在Hashtable中查找得到文件信息
        //3.输入进来的时档案号 根据档案号来下载文件
        Doc doc = DataProcessing.searchDoc(ID);
        //若没有找到 直接返回false
        if(doc == null)
            return false;
        //2.将对应文件拷贝至指定目录中
        //被拷贝的对应文件
        File tempFile = new File(uploadpath+doc.getFilename());
        String filename = tempFile.getName();
        String targetfilename = downloadpath+doc.getFilename();

        //被拷贝的文件
        BufferedInputStream infile = new BufferedInputStream(new FileInputStream(tempFile));
        //写入的文件
        BufferedOutputStream targetfile = new BufferedOutputStream(new FileOutputStream(targetfilename));

        int readlen = 0;
        //将读取的内容存取在数组buffer中
        while((readlen = infile.read(buffer))!=-1){
            //边读边写
            targetfile.write(buffer,0,readlen);
        }

        //关闭文件
        infile.close();
        targetfile.close();

        return true;

    }

    public void showFileList() throws SQLException{
        Enumeration<Doc> e = DataProcessing.getAllDocs();
        Doc doc;
        while(e.hasMoreElements()){
            doc = e.nextElement();
            String ID = "ID:"+doc.getID();
            String Creator = "Creator:"+doc.getCreator();
            String Timestamp = "Timestamp:"+doc.getTimestamp();
            String Description = "Description:"+doc.getDescription();
            String Filename = "Filename:" + doc.getFilename();
            System.out.println(ID+"\t"+Creator+"\t"+Timestamp+"\t"+Filename);
            System.out.println(Description);
        }
    }

    public  void showMenu(String name){;}

    public void exitSystem(){
        System.out.println("系统退出, 谢谢使用 ! ");
        System.exit(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
