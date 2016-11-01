package com.capgemini.chess.dataaccess.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.capgemini.chess.dataaccess.dao.UserDao;
import com.capgemini.chess.dataaccess.entities.UserEntity;
import com.capgemini.chess.service.mapper.UserProfileMapper;
import com.capgemini.chess.service.to.UserProfileTO;

/**
 * User data access object. It is implementation of UserDao. It is a stub for
 * testing services
 * 
 * @author AWOZNICA
 *
 */
@Repository
public class UserDaoImpl implements UserDao {
	private long nextUserId = 0;
	private List<UserEntity> users = new ArrayList<>();

	public UserDaoImpl() {
		this.addUserProfilesStub();
	}

	@Override
	public boolean addUser(UserProfileTO userProfile) {
		UserEntity userEntity = UserProfileMapper.map(userProfile);
		userEntity.setId(nextUserId++);
		boolean result = users.add(userEntity);
		return result;
	}

	@Override
	public boolean setUserLogin(long userId, String newLogin) {
		boolean result = false;
		int foundUserPosition = this.findUserEntityById(userId);
		if (foundUserPosition >= 0) {
			UserEntity userEntity = this.users.get(foundUserPosition);
			userEntity.setLogin(newLogin);
			result = true;
		}
		return result;
	}

	@Override
	public boolean setUserPassword(long userId, String newPassword) {
		boolean result = false;
		int foundUserPosition = this.findUserEntityById(userId);
		if (foundUserPosition >= 0) {
			UserEntity userEntity = this.users.get(foundUserPosition);
			userEntity.setPassword(newPassword);
			result = true;
		}
		return result;
	}

	@Override
	public boolean setUserName(long userId, String newUserName) {
		boolean result = false;
		int foundUserPosition = this.findUserEntityById(userId);
		if (foundUserPosition >= 0) {
			UserEntity userEntity = this.users.get(foundUserPosition);
			userEntity.setName(newUserName);
			result = true;
		}
		return result;
	}

	@Override
	public boolean setUserSurname(long userId, String newUserSurname) {
		boolean result = false;
		int foundUserPosition = this.findUserEntityById(userId);
		if (foundUserPosition >= 0) {
			UserEntity userEntity = this.users.get(foundUserPosition);
			userEntity.setSurname(newUserSurname);
			result = true;
		}
		return result;
	}

	@Override
	public boolean setUserEmail(long userId, String newUserEmail) {
		boolean result = false;
		int foundUserPosition = this.findUserEntityById(userId);
		if (foundUserPosition >= 0) {
			UserEntity userEntity = this.users.get(foundUserPosition);
			userEntity.setEmail(newUserEmail);
			result = true;
		}
		return result;
	}

	@Override
	public boolean setUserAboutMe(long userId, String newUserAboutMe) {
		boolean result = false;
		int foundUserPosition = this.findUserEntityById(userId);
		if (foundUserPosition >= 0) {
			UserEntity userEntity = this.users.get(foundUserPosition);
			userEntity.setAboutMe(newUserAboutMe);
			result = true;
		}
		return result;
	}

	@Override
	public boolean setUserLifeMotto(long userId, String newUserLifeMotto) {
		boolean result = false;
		int foundUserPosition = this.findUserEntityById(userId);
		if (foundUserPosition >= 0) {
			UserEntity userEntity = this.users.get(foundUserPosition);
			userEntity.setLifeMotto(newUserLifeMotto);
			result = true;
		}
		return result;
	}

	@Override
	public UserProfileTO findUserById(long userId) {
		Optional<UserEntity> foundUser = users.stream().filter(p -> p.getId() == userId).findAny();
		UserEntity userEntity = null;
		if (foundUser.isPresent()) {
			userEntity = foundUser.get();
		}
		UserProfileTO userProfileTO = null;
		if (userEntity != null) {
			userProfileTO = UserProfileMapper.map(userEntity);
		}
		return userProfileTO;
	}

	@Override
	public List<UserProfileTO> findUsersByLogin(String login) {
		List<UserEntity> collectedEntities = users.stream().filter(p -> login.equals(p.getLogin()))
				.collect(Collectors.toList());
		List<UserProfileTO> foundUsersTOs = UserProfileMapper.map2TOs(collectedEntities);
		return foundUsersTOs;
	}

	@Override
	public List<UserProfileTO> findUsersByName(String userName) {
		List<UserEntity> collectedEntities = users.stream().filter(p -> userName.equals(p.getName()))
				.collect(Collectors.toList());
		List<UserProfileTO> foundUsersTOs = UserProfileMapper.map2TOs(collectedEntities);
		return foundUsersTOs;
	}

	@Override
	public List<UserProfileTO> findUsersBySurname(String userSurname) {
		List<UserEntity> collectedEntities = users.stream().filter(p -> userSurname.equals(p.getSurname()))
				.collect(Collectors.toList());
		List<UserProfileTO> foundUsersTOs = UserProfileMapper.map2TOs(collectedEntities);
		return foundUsersTOs;
	}

	@Override
	public List<UserProfileTO> findUsers(String login, String userName, String surname) {
		List<UserEntity> collectedEntities = users.stream().filter(p -> login.equals(p.getLogin()))
				.filter(p -> userName.equals(p.getName())).filter(p -> surname.equals(p.getSurname()))
				.collect(Collectors.toList());
		List<UserProfileTO> foundTOs = UserProfileMapper.map2TOs(collectedEntities);
		return foundTOs;
	}

	@Override
	public List<UserProfileTO> getAllUsers() {
		List<UserProfileTO> mappedUserEntities = UserProfileMapper.map2TOs(this.users);
		return mappedUserEntities;
	}

	@Override
	public boolean deleteUser(long deletedUserId) {
		boolean result = false;
		int userEntityIdListPosition = this.findUserEntityById(deletedUserId);
		if (userEntityIdListPosition >= 0) {
			users.remove(userEntityIdListPosition);
			result = true;
		}
		return result;
	}

	private void addUserProfilesStub() {
		users.add(this.createUser("krzysiek89", "krzysztof", "suchar"));
		users.add(this.createUser("majka12", "ewelina", "tylda"));
		users.add(this.createUser("pietraszko666", "krzysztof", "nowak"));
		users.add(this.createUser("robb52", "robert", "niedziela"));
		users.add(this.createUser("awoznica", "arnold", "woznica"));
		users.add(this.createUser("anitkaW", "Anita", "paluch"));
		users.add(this.createUser("myszka123", "olga", "nowak"));
	}

	private UserEntity createUser(String login, String firstName, String surname) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(nextUserId++);
		userEntity.setLogin(login);
		userEntity.setName(firstName);
		userEntity.setSurname(surname);
		userEntity.setAboutMe("");
		userEntity.setEmail("");
		userEntity.setLifeMotto("");
		return userEntity;
	}

	private int findUserEntityById(long id) {
		for (int userIter = 0; userIter < users.size(); userIter++) {
			UserEntity userEntity = users.get(userIter);
			if (userEntity.getId() == id) {
				return userIter;
			}
		}
		return -1;
	}

}
