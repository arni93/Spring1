package com.capgemini.chess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.dao.UserDao;
import com.capgemini.chess.service.UserService;
import com.capgemini.chess.service.to.UserProfileTO;

/**
 * Implementation of UserService
 * 
 * @author AWOZNICA
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public UserProfileTO findUserById(long userId) {
		UserProfileTO foundUser = this.userDao.findUserById(userId);
		return foundUser;
	}

	@Override
	public List<UserProfileTO> findUsersByLogin(String login) {
		List<UserProfileTO> foundUsers = userDao.findUsersByLogin(login);
		return foundUsers;
	}

	@Override
	public List<UserProfileTO> findUsersByName(String userName) {
		List<UserProfileTO> users = this.userDao.findUsersByName(userName);
		return users;
	}

	@Override
	public List<UserProfileTO> findUsersBySurname(String userSurname) {
		List<UserProfileTO> foundUsers = this.userDao.findUsersBySurname(userSurname);
		return foundUsers;
	}

	@Override
	public List<UserProfileTO> findUsers(String login, String userName, String surname) {
		List<UserProfileTO> foundUsers = this.userDao.findUsers(login, userName, surname);
		return foundUsers;
	}

	@Override
	public UserProfileTO readUser(Long id) {
		UserProfileTO fountUser = this.userDao.findUserById(id);
		return fountUser;
	}

}
