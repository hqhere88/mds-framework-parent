package mds.framework.service;

import mds.framework.entity.User;

public class UserService {
    private String id;

    public User getCurrentUser() {
        return new User();
    }

    public User findById(String id) {
        this.id = id;
        return new User();
    }
}