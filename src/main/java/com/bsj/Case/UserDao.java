package com.bsj.Case;

public class UserDao {
    public User save(String name, String phone, String repId) {
        User user = new User();
        user.setRepId(repId);
        return user;
    }
}
