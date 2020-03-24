/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traveladvisor;

import java.util.Scanner;
import static traveladvisor.UserAccount.data;

/**
 *
 * @author ibehf
 */
public class OnlineSystem {
    
     private OnlineAccount theLoginAccount;
    private DataStorage data;

    
    public OnlineSystem(DataStorage d)
    {
        //at the biginning, there is no login account
        theLoginAccount = null;
        data = d;
    }
    public void showMainPage()
    {
        //declare varaibles
        Scanner input = new Scanner(System.in);
        String selection = "";
        
        while(!selection.equals("x"))
        {
            //display the menu
            System.out.println("Welcome to TravelAdvisor");
            System.out.println("Please make your selection");
            System.out.println("1: Create a new account");
            System.out.println("2: Login your account");
            System.out.println("x: Leave the system");
            
            //get the selection from the user
            selection = input.nextLine();
            System.out.println();
            
            if(selection.equals("1"))
            {
                //register
                register();
                 
            }
            else if(selection.equals("2"))
            {
                //login
                login();
            }
        }
    }
    
    //register method
    public void register()
    {
        //declare varaibles
        String user_id, password, name, tag1, tag2, tag11 = "", tag22 = "";
        
        Scanner input = new Scanner(System.in);
        
        //prompts and input
        System.out.println("Please enter a user_id");
        user_id = input.nextLine();
        
        System.out.println("Please enter your name");
        name = input.nextLine();
        
        System.out.println("Please enter your password");
        password = input.nextLine();
        
        System.out.println("Please choose a tag1");
        System.out.println("1. History Buff \n 2. Shopping Fanatic \n 3. Beach Goer \n 4. Urban Explorer \n  5. Nature Lover \n 6. Family Vacationer ");
        tag1 = input.nextLine();
       
            switch (tag1) {
             case "1":
                 tag11="History Buff";
                 break;
             case "2":
                 tag11="Shopping Fanatic";
                 break;
             case "3":
                 tag11="Beach Goer";
                 break;
             case "4":
                 tag11="Urban Explorer";
                 break;
             case "5":
                 tag11="Nature Lover";
                 break;
             case "6":
                 tag11="Family Vacationer";
                 break;
             default:
                 break;
         }
           
        
        System.out.println("Please choose a tag2");
        System.out.println("1. History Buff \n 2. Shopping Fanatic \n 3. Beach Goer \n 4. Urban Explorer \n  5. Nature Lover \n 6. Family Vacationer ");
        tag2= input.nextLine();
             switch (tag2) {
                 case "1":
                     tag22="History Buff";
                     break;
                 case "2":
                     tag22="Shopping Fanatic";
                     break;
                 case "3":
                     tag22="Beach Goer";
                     break;
                 case "4":
                     tag22="Urban Explorer";
                     break;
                 case "5":
                     tag22="Nature Lover";
                     break;
                 case "6":
                     tag22="Family Vacationer";
                     break;
                 default:
                     break;
             }
        
        
         if((password.length() >=3 && password.length() <= 10) && (password.matches("^(?:.*[a-z].*)(?:.*[0-9].*)$")) && (password!=user_id)){
                     data.createUserAccount(user_id, name, password, tag11, tag22); 
         }else{
            System.out.println("Password must be between 3 and 10 characters and "
                    + "must contain at least one number or letter and must not be the same with user id ");
         }
                 
             }
      

    
    
    public void login()
    {
        //we need id and password
        Scanner input = new Scanner(System.in);
        String user_id="";
        String password="";
         
        
        //get the login info.
        System.out.println("Please enter your user ID");
        user_id = input.nextLine();
        System.out.println("Please enter your password");
        password = input.nextLine();
        
        String res = data.login(user_id, password);
        if(res.equals("success"))
        {
            String ans;
            theLoginAccount = new OnlineAccount(user_id, password);
            theLoginAccount.setData(data);
            
            System.out.println("You May Like");
            System.out.println(data.YouMayLike(user_id));
            theLoginAccount.welcome();
        }
        else
        {
            System.out.println("The login failed");
            System.out.println(res);
            System.out.println();
        }}
    
        public void AdminLogin()
    {
        //we need id and password
        Scanner input = new Scanner(System.in);
        String user_id="";
        String password="";
         
        
        //get the login info.
        System.out.println("Please enter your user ID");
        user_id = input.next();
        System.out.println("Please enter your password");
        password = input.next();
        
        String res = data.login(user_id, password);
        if(res.equals("success"))
        {
            theLoginAccount = new OnlineAccount(user_id, password);
            theLoginAccount.setData(data);
           AdminAccount.ApproveAttraction();
        }
        else
        {
            System.out.println("The login failed");
            System.out.println(res);
            System.out.println();
        }
        
    }
   
        
    
     
}
