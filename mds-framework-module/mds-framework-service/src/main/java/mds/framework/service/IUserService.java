package mds.framework.service;

import mds.framework.entity.User;

public interface IUserService {

    User getCurrentUser();

    User findById(String id) ;
}