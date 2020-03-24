/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traveladvisor;

import java.util.ArrayList;

/**
 *
 * @author ibehf
 */
public interface DataStorage {
     
    void createUserAccount(String user_id, String name, String pswd, String tag1, String tag2);
    String login(String id, String password);
    String AdminLogin(String id, String password);
  String searchCity(String i);
  ArrayList<String>searchCityAttraction(String i);
   ArrayList<String>searchAttraction(String i);
   String ViewAttractionByName(String i);
   ArrayList<String> MyFavoriteDestination(String i);
    void updateAccount(String accountNumber, double balance, String statement);
   void postReview(String att_name, String user_id, float score, String comments, String date);
   void addFavorite(String user_id, String Attr_name);
    void adminApprove(String name);
    String adminChoose(String name) ;
    void adminDeny(String name);
    ArrayList<String> YouMayLike(String i);
    void updateAttraction(String user_id, String name, String city, String state, String description, String tag);
    void updateAttractionAvgScore(String name);
    void CreateAttraction(String user_id, String name, String city, String state, String description, String tag);
}
