package com.pubmanager.expensetracker.services;

import com.pubmanager.expensetracker.domain.User;
import com.pubmanager.expensetracker.exceptions.EtAuthException;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;

}
