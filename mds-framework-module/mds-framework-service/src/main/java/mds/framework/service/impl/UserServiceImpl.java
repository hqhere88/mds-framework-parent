package mds.framework.service.impl;

import mds.framework.entity.User;
import mds.framework.service.IUserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {
    private String id;

    @Override
    public User getCurrentUser() {
        return new User();
    }

    @Override
    public User findById(String id) {
        this.id = id;
        return new User();
    }
}