package com.lecture.finalproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModelFriends {
    private static Logger logger = LoggerFactory.getLogger(ModelFriends.class);
    
    private String friend_id   ;
    private String friendName  ;
    public ModelFriends(String friend_id, String friendName) {
        super();
        this.friend_id = friend_id;
        this.friendName = friendName;
    }
    public ModelFriends() {
        super();
    }
    public static Logger getLogger() {
        return logger;
    }
    public static void setLogger(Logger logger) {
        ModelFriends.logger = logger;
    }
    public String getFriend_id() {
        return friend_id;
    }
    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }
    public String getFriendName() {
        return friendName;
    }
    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
    @Override
    public String toString() {
        return "ModelFriends [friend_id=" + friend_id + ", friendName="
                + friendName + "]";
    }
    
 
}
