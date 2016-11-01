package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.service.to.UserProfileTO;

public interface UserService {
	UserProfileTO findUserById(long userId);

	List<UserProfileTO> findUsersByLogin(String login);

	List<UserProfileTO> findUsersByName(String userName);

	List<UserProfileTO> findUsersBySurname(String userSurname);

	List<UserProfileTO> findUsers(String login, String userName, String surname);

	UserProfileTO readUser(Long id);
}
