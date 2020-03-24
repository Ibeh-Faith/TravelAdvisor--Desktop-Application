/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traveladvisor;

import java.sql.ResultSet;

/**
 *
 * @author ibehf
 */
public class Reviews {
    
    private String attr_name;
    private String user_id;
    private float scores;
    private String comment;
    private String date_and_time;
    
    public Reviews(String an, String ui, float sc, String c, String dt){
       
        attr_name=an;
        user_id=ui;
        scores=sc;
        comment=c;
        date_and_time=dt;
        
    }

    public String getAttr_name() {
        return attr_name;
    }

    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public float getScores() {
        return scores;
    }

    public void setScores(float scores) {
        this.scores = scores;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(String date_and_time) {
        this.date_and_time = date_and_time;
    }

   
    
    
}
