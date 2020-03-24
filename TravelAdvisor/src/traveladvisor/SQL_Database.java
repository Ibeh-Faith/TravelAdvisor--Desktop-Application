/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traveladvisor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



/**
 *
 * @author ibehf
 */
public class SQL_Database implements DataStorage{
    final String DATABASE_URL = 
                "jdbc:mysql://mis-sql.uhcl.edu/edafetanurei?useSSL=false";
    final String db_id = "edafetanurei";
    final String db_psw = "1852320";
        
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    @Override
     public void createUserAccount(String user_id, String pswd, String name, String tag1, String tag2)
    {
        try
        {
            
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
             statement = connection.createStatement();
             
              resultSet = statement.executeQuery("Select * from user "
                    + "where user_id = '" + user_id + "'");
              
            if(resultSet.next())
            {
                //either the ssn is used or the id is used
                System.out.println("Account creation failed, user id already exists");
                System.out.println();
            }
            else
            {
                //insert a record into onlineAccount
            int r = statement.executeUpdate("insert into user values ('" + user_id + "', '" + name + "', '" + pswd + "', '"
                        + tag1 +"', '" + tag2 + "')");
                System.out.println("Account creation successful!");
                System.out.println();
            }
            connection.commit();
            connection.setAutoCommit(true);
            
        }
        catch (SQLException e)
        {
            System.out.println("Something wrong during the creation process!");
            e.printStackTrace();
        }
        finally
        {
             //close the database
             try
             {
                 resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }
        
    }
    
    @Override
    public String login(String user_id, String password)
    {
         
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_psw);
        statement = connection.createStatement();
            //search the accountID in the onlineAccount table
            resultSet = statement.executeQuery("Select * from user "
                    + "where user_id = '" + user_id + "'");
            
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_psw);
            
            if(resultSet.next())
            {
                //the id is found, check the password
                if(password.equals(resultSet.getString(2)))
                {
                    //password is good
                    return "success";
                    //go to the welcome page 
                }
                else
                {
                    //password is not correct
                    return "The password is not correct!";
                     
                }
            }
            else
            {
                return "The login ID is not found!";
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "Internal error";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }
    @Override
     public ArrayList<String> searchAttraction(String i)
    {
        try
        {
            //connect to the database
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
             statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from attraction "
                    + "where tag = '" + i + "' and status = 'Approved' order by avg_score desc");
           
            
            connection.commit();
            connection.setAutoCommit(true);
            
  
                 
           ArrayList<String> aList = new ArrayList<String>();
            
            while(resultSet.next())
            {
               
              String anAccount = resultSet.getString(2)+" | "+resultSet.getString(6)+"\n";
                 aList.add(anAccount);
            }
            return aList;
        }
        catch (SQLException e)
        {
            //handle the exception
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
}
     @Override
public ArrayList<String> searchCityAttraction(String i)
    {
        try
        {
            //connect to the database
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            
            resultSet= statement.executeQuery("select * from attraction "
                    + "where city = '" + i + "' and status = 'Approved' order by avg_score desc");
            
            connection.commit();
            connection.setAutoCommit(true);
                 ArrayList<String> aList = new ArrayList<String>();
           while(resultSet.next())
            {
               
                 String anAccount = resultSet.getString(2)+" | "+resultSet.getString(3)+"\n";
                 aList.add(anAccount);
            }
            return aList;
        }
        catch (SQLException e)
        {
            //handle the exception
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }
@Override
public String searchCity(String i)
    {
        try
        {
            //connect to the database
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            
            resultSet= statement.executeQuery("select * from attraction "
                    + "where city = '" + i + "' and status = 'Approved' order by avg_score desc");
            
            connection.commit();
            connection.setAutoCommit(true);
            
            if(resultSet.next())
            {
                return resultSet.getString(3);
            }
            else
            {
                return null;
            }
        }
        catch (SQLException e)
        {
            //handle the exception
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }
@Override
 public String ViewAttractionByName(String i){
     {
        try
        {
            //connect to the database
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            
            resultSet= statement.executeQuery("select * from attraction "
                    + "where name = '" + i + "'");
            
            connection.commit();
            connection.setAutoCommit(true);
            
            if(resultSet.next())
            {
                return resultSet.getString(2)+" | "+resultSet.getString(3)+" | "+
                         resultSet.getString(4)+" | "+resultSet.getString(5)+" | "+resultSet.getString(6)+" | "+resultSet.getFloat(7)+" | "+resultSet.getString(8);
            }
            else
            {
                return null;
            }
        }
        catch (SQLException e)
        {
            //handle the exception
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }}      
        
 }
@Override
public void postReview(String att_name, String user_id, float score, String comments, String date)
    {
        try
        {
            //connect to the database
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            
            //update the review
            resultSet = statement.executeQuery("Select * from reviews ");
             
             int id = 0;
             while(resultSet.next())
             {
                 id= resultSet.getInt(1) + 1;
                 
             }
             
             //rolled back to here if anything wrong
             connection.setAutoCommit(false);
             
             //insert a record into reviews Table
             int r = statement.executeUpdate("insert into reviews values "
                     + "('" + id + "', '" + att_name + "', '" + user_id + "', '" 
                     + score + "', '" + comments + "', '"+date+"')");
             
           
            //update the activity
            connection.commit();
            connection.setAutoCommit(true);


        }
        catch (SQLException e)
        {
            //handle the exception
            e.printStackTrace();
        }
        finally
        { 
            //close the database
            try
            {
                resultSet.close();
                statement.close();
                connection.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
   
@Override
    public void addFavorite(String user_id, String Attr_name) {
       
       
        try
        {
            //connect to the database
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            
            //insert record into favorite table
        int r = statement.executeUpdate("insert into favorite values "
                     + "('" + user_id + "', '" + Attr_name + "')");
             
                System.out.println("Favorite Added");
               
            //update the activity
            connection.commit();
            connection.setAutoCommit(true);


        }
        catch (SQLException e)
        {
            //handle the exception
            e.printStackTrace();
        }
        finally
        { 
            //close the database
            try
            {
                
                statement.close();
                connection.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }}
    }
    @Override
 public ArrayList<String> MyFavoriteDestination(String i){
     {
        try
        {
            //connect to the database
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            
            resultSet= statement.executeQuery("select * from favorite "
                    + "where user_id = '" + i + "'");
            
            connection.commit();
            connection.setAutoCommit(true);
            ArrayList<String> aList = new ArrayList<String>();
           while(resultSet.next())
            {
               
                 String anAccount = resultSet.getString(2)+"\n";
                 aList.add(anAccount);
            }
            return aList;
        }
        catch (SQLException e)
        {
            //handle the exception
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }}      
        
 }
 @Override
    public void updateAttraction(String user_id, String name, String city, String state, String description, String tag) {
        try
        {
            //connect to the database
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            
            //update the review
            int r = statement.executeUpdate("Update attraction set "
                    + "user_id = '" + user_id + "' "
                    + "name = '" + name + "' "    
                    + "city = '" + city + "' "
                    + "state = '" + state + "' "
                    + "description = '" + description + "' "
                    + "tag = '" + tag + "' " );
            //update the activity
            connection.commit();
            connection.setAutoCommit(true);


        }
        catch (SQLException e)
        {
            //handle the exception
            e.printStackTrace();
        }
        finally
        { 
            //close the database
            try
            {
                resultSet.close();
                statement.close();
                connection.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void CreateAttraction(String user_id, String name, String city, String state, String description, String tag){
        final String DATABASE_URL = 
                "jdbc:mysql://mis-sql.uhcl.edu/edafetanurei?useSSL=false";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try
        {
            
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
             statement = connection.createStatement();
             
              resultSet = statement.executeQuery("Select * from attraction "
                    + "where name = '" + name + "'");
              
            if(resultSet.next())
            {
                //either the ssn is used or the id is used
                System.out.println("Attraction creation failed, name already exists");
                System.out.println();
            }
            else
            {
                //insert a record into attraction
            int r = statement.executeUpdate("insert into attraction values('" + user_id + "', '" + name + "', '" + city + "', '"
                        + state +"', '" + description + "', '" + tag + "', 0, 'Pending')");
             
                System.out.println("Attraction creation pending Approval!");
                System.out.println();
            
            }
            connection.commit();
            connection.setAutoCommit(true);
            
        }
        catch (SQLException e)
        {
            System.out.println("Something wrong during the creation process!");
            e.printStackTrace();
        }
        finally
        {
             //close the database
             try
             {
                 resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }
        
    }
    @Override
    public void updateAttractionAvgScore(String name) {
        final String DATABASE_URL = 
                "jdbc:mysql://mis-sql.uhcl.edu/edafetanurei?useSSL=false";
        
        Connection connection = null;
        Statement statement = null;
        Statement statement1=null;
        ResultSet resultSet = null;
        
        try
        {
            
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
             statement = connection.createStatement();
             statement1=connection.createStatement();
             
              resultSet = statement.executeQuery("Select avg(scores) from reviews where attr_name = '"+ name + "'");
              
              while(resultSet.next())
              {
              float a=resultSet.getFloat(1);
              int r=statement1.executeUpdate("update attraction set avg_score = "+ a +" where name = '"+name+"'");
              
            //update the activity
            connection.commit();
            connection.setAutoCommit(true);
//update attraction set avg_score=(select avg(scores) from reviews where attraction.name=reviews.attr_name");
              }
           
        }
        catch (SQLException e)
        {
            //handle the exception
            e.printStackTrace();
        }
        finally
        { 
            //close the database
            try
            {

                statement.close();
                statement1.close();
                connection.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
@Override
    public ArrayList<String> YouMayLike(String i) {
        final String DATABASE_URL = 
                "jdbc:mysql://mis-sql.uhcl.edu/edafetanurei?useSSL=false";
        
        Connection connection = null;
        Statement statement = null;
        Statement statement1=null;
        Statement statement2=null;
        ResultSet resultSet = null;
        ResultSet rs=null;
        ResultSet rss=null;
        
        try
        {
            
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
             statement = connection.createStatement();
             statement1=connection.createStatement();
             statement2=connection.createStatement();
             
              resultSet = statement.executeQuery("Select tag1 from user where user_id = '"+ i + "'");
              rss = statement1.executeQuery("Select tag2 from user where user_id = '"+ i + "'");
              
            
            ArrayList<String> aList = new ArrayList<String>();

              while(resultSet.next() && rss.next())
              {
                    String a=resultSet.getString(1);
               String b=rss.getString(1);
              rs=statement2.executeQuery("select * from attraction where tag = '"+ a +"' or tag = '"+ b +"' and status = 'Approved' order by avg_score desc limit 3");
             while(rs.next()){
              String anAccount = rs.getString(2) + " | "+ rs.getString(6) + " | "+rs.getString(7) + "\n";
                 aList.add(anAccount);
             }
            //update the activity
            connection.commit();
            connection.setAutoCommit(true);
              }
            return aList;
             
        }
        catch (SQLException e)
        {
            //handle the exception
            e.printStackTrace();
        return  null;
        }
        finally
        { 
            //close the database
            try
            {

                statement.close();
                statement1.close();
                statement2.close();
                connection.close();
                resultSet.close();
                rss.close();
                rs.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void adminApprove(String name) {
         try
        {
          //connect to the database
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            
             int r = statement.executeUpdate("Update attraction set "
                    + "status = 'Approved' where name = '"+name+"'");
        
            connection.commit();
            connection.setAutoCommit(true);
            
           
        }
        catch (SQLException e)
        {
            //handle the exception
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
         
    }
    @Override
    public String adminChoose(String name) {
         try
        {
          //connect to the database
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            
            resultSet= statement.executeQuery("select * from attraction "
                    + "where status = 'Pending' and name='"+name+"'");
            
            connection.commit();
            connection.setAutoCommit(true);
            
            if(resultSet.next())
            {
                return resultSet.getString("status");
            }
            else
            {
                return null;
            }
        }
        catch (SQLException e)
        {
            //handle the exception
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
         
    }
 @Override
    public void adminDeny(String name) {
         final String DATABASE_URL = 
                "jdbc:mysql://mis-sql.uhcl.edu/edafetanurei?useSSL=false";
        
        Connection connection = null;
        Statement statement = null;
        Statement statement1=null;
         try
        {
          //connect to the database
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement1 = connection.createStatement();
            
             int r = statement.executeUpdate("Update attraction set "
                    + "status = 'Denied' where name = '"+name+"'");
             int d = statement1.executeUpdate("delete from attraction where status = 'Denied'");
        
            connection.commit();
            connection.setAutoCommit(true);
           
        }
        catch (SQLException e)
        {
            //handle the exception
            e.printStackTrace();
            
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                statement1.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }}
   @Override
   public String AdminLogin(String user_id, String password)
    {
         
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_psw);
        statement = connection.createStatement();
            //search the accountID in the onlineAccount table
            resultSet = statement.executeQuery("Select * from user "
                    + "where user_id = 'admin' and password = 'admin'");
            
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                  db_id, db_psw);
            
            if(resultSet.next())
            {
                //the id is found, check the password
                if(password == "admin1." && user_id =="Admin")
                {
                    //password is good
                    return "success";
                    //go to the welcome page 
                }
                else
                {
                    //password is not correct
                    return "The user id or password is not an admin!";
                     
                }
            }
            else
            {
                return "The login ID is not found!";
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "Internal error";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }
   
    @Override
    public void updateAccount(String accountNumber, double balance, String statement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
