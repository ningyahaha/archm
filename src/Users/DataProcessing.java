package Users;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

public class DataProcessing{
    private static Connection connection;
    private static Statement statement;
    @SuppressWarnings("unused")
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private static boolean connectedToDatabase=false;

    public static void connectToDatabase(String driveName,String url,String user,String password) throws ClassNotFoundException, SQLException {
        Class.forName(driveName);
        connection=DriverManager.getConnection(url, user, password);
        connectedToDatabase=true;
    }

    public static void disconnectFromDatabase() {
        if(connectedToDatabase) {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            }catch(SQLException sqlException) {
                sqlException.printStackTrace();
            }finally {
                connectedToDatabase=false;
            }
        }
    }

    public static Doc searchDoc(String DocID) throws SQLException{
        Doc temp=null;
        if(!connectedToDatabase) throw new SQLException("Not Connected to Database.");

        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String sql="select * from doc_info where Id='"+DocID+"'";
        resultSet=statement.executeQuery(sql);

        if(resultSet.next()) {
            String ID=resultSet.getString("ID");
            String creator=resultSet.getString("creator");
            Timestamp timestamp=resultSet.getTimestamp("timestamp");
            String description=resultSet.getString("description");
            String filename=resultSet.getString("filename");
            temp=new Doc(ID,creator,timestamp,description,filename);
        }
        return temp;
    }

    public static Enumeration<Doc> getAllDocs() throws SQLException{
        Hashtable<String,Doc> docs=new Hashtable<String,Doc>();
        Doc temp=null;
        Enumeration<Doc> e;
        if(!connectedToDatabase) throw new SQLException("Not Connected to Database.");

        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String sql="select * from doc_info";
        resultSet=statement.executeQuery(sql);

        while(resultSet.next()) {
            String ID=resultSet.getString("ID");
            String creator=resultSet.getString("creator");
            Timestamp timestamp=resultSet.getTimestamp("timestamp");
            String description=resultSet.getString("description");
            String filename=resultSet.getString("filename");
            temp=new Doc(ID,creator,timestamp,description,filename);
            docs.put(ID, temp);
        }
        e=docs.elements();
        return e;
    }

    public static boolean insertDoc(String ID,String creator,Timestamp timestamp,String description,String filename)throws SQLException{
        if(!connectedToDatabase) throw new SQLException("Not Connected to Database.");

        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        String sql="select * from doc_info where Id='"+ID+"'";
        resultSet=statement.executeQuery(sql);

        if(resultSet.next()) return false;
        sql="insert into doc_info(Id,creator,timestamp,description,filename) values "+"('"+ID+"','"+creator+"','"+timestamp+"','"+description+"','"+filename+"')";

        int i=0;
        i = statement.executeUpdate(sql);
        if(i>0)
            return true;
        else return false;
    }

    public static User searchUser(String name) throws SQLException{
        User temp=null;

        if(!connectedToDatabase) throw new SQLException("Not Connected to Database.");

        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String sql="select * from user_info where username ='"+name+"'";
        resultSet=statement.executeQuery(sql);

        if(resultSet.next()) {
            String Name=resultSet.getString("username");
            String password=resultSet.getString("password");
            String role=resultSet.getString("role");
            temp=new User(Name,password,role);
        }
        return temp;
    }

    public static User searchUser(String name,String password) throws SQLException{
        User temp=null;

        if(!connectedToDatabase) throw new SQLException("Not Connected to Database.");

        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String sql="select * from user_info where username='"+name+"'and password='"+password+"'";
        resultSet=statement.executeQuery(sql);

        if(resultSet.next()) {
            String Name=resultSet.getString("username");
            String Password=resultSet.getString("password");
            String role=resultSet.getString("role");
            temp=new User(Name,Password,role);
        }
        return temp;
    }

    public static Enumeration<User> getAllUser() throws SQLException{
        Hashtable<String,User> users=new Hashtable<String,User>();
        User temp = null;
        Enumeration<User> e;
        if(!connectedToDatabase) throw new SQLException("Not Connected to Database.");

        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String sql="select * from user_info";
        resultSet=statement.executeQuery(sql);

        while(resultSet.next()) {
            String name=resultSet.getString("username");
            String password=resultSet.getString("password");
            String role=resultSet.getString("role");
            temp=new User(name,password,role);
            users.put(name, temp);
        }
        e=users.elements();
        return e;
    }

    public static boolean updateUser(String name,String password,String role) throws SQLException{
        if(!connectedToDatabase) throw new SQLException("Not Connected to Database.");

        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        String sql="select * from user_info where username='"+name+"'";
        resultSet=statement.executeQuery(sql);

        if(!resultSet.next()) return false;
        sql="update user_info set password='"+password+"',role='"+role+"' where username='"+name+"'";
        if(statement.executeUpdate(sql)>0) return true;
        else return false;
    }

    public static boolean insertUser(String name,String password,String role) throws SQLException{
        if(!connectedToDatabase) throw new SQLException("Not Connected to Database.");

        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        String sql="select * from user_info where username='"+name+"'";
        resultSet=statement.executeQuery(sql);

        if(resultSet.next()) return false;
        sql="insert into user_info(username,password,role) values "+"('"+name+"','"+password+"','"+role+"')";
        if(statement.executeUpdate(sql)>0) return true;
        else return false;
    }

    public static boolean deleteUser(String name) throws SQLException{
        if(!connectedToDatabase) throw new SQLException("Not Connected to Database.");

        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        String sql="select * from user_info where username='"+name+"'";
        resultSet=statement.executeQuery(sql);

        if(!resultSet.next()) return false;
        sql="delete from user_info where username='"+name+"'";
        if(statement.executeUpdate(sql)>0) return true;
        else return false;
    }
}

