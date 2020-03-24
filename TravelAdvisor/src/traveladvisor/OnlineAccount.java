/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traveladvisor;

import java.util.Scanner;

/**
 *
 * @author ibehf
 */
public class OnlineAccount {
    
    private String user_id;
    private String psw;
    private DataStorage data;
    
   
    //constructor
    public OnlineAccount(String i, String p)
    {
        user_id = i;
        psw = p;
    }

     public void welcome(){
        System.out.println();
        System.out.println("Hello welcome to your online account");
        
        
        Scanner input = new Scanner(System.in);
        String selection = "";
        
        while(!selection.equals("x"))
        {
            //welcome msg
            System.out.println("\n***Welcome to your online account***");
            //options
            System.out.println("Please select your options");
            System.out.println("c: Create an attraction");
            System.out.println("st: Search attractions based on tags ");
            System.out.println("sc: Search attractions based on city");
            System.out.println("p: Post a Review of an attraction");
            System.out.println("mf: Go to My Favorite Destination");
            System.out.println("f: Save some places as Favorite Destinations");
            System.out.println("x: sign out\n");
            
            //after display the menu, we ask the user to input selection
            selection = input.nextLine();
            
            if(selection.equals("c"))
            {
                UserAccount.createAttraction();
            }
            else if(selection.equals("st"))
            {
                UserAccount.searchTagAttraction();
            }
            else if(selection.equals("sc"))
            {
                UserAccount.searchCityAttraction();
            }
            else if(selection.equals("p"))
            {
                UserAccount.postReview();
            }
             else if(selection.equals("mf"))
            {
                UserAccount.MyFavoriteDestination();
            }
            else if(selection.equals("f"))
            {
                UserAccount.Favorite();
            }
            
             
            }
        }
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public DataStorage getData() {
        return data;
    }

    public void setData(DataStorage data) {
        this.data = data;
    }
    
}
