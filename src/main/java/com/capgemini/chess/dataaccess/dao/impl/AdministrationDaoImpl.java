package com.capgemini.chess.dataaccess.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.capgemini.chess.dataaccess.dao.AdministrationDao;
import com.capgemini.chess.dataaccess.entities.UserEntity;
import com.capgemini.chess.service.mapper.UserProfileMapper;
import com.capgemini.chess.service.to.UserProfileTO;

@Repository
public class AdministrationDaoImpl implements AdministrationDao {
	private long nextUserLogin = 0;
	private List<UserEntity> users = new ArrayList<>();

	public AdministrationDaoImpl() {
		this.addUserProfilesStub();
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
	public List<UserProfileTO> findUsersByLogin(String userLogin) {
		List<UserEntity> collectedEntities = users.stream().filter(p -> userLogin.equals(p.getLogin()))
				.collect(Collectors.toList());
		List<UserProfileTO> foundUsersTOs = UserProfileMapper.map2TOs(collectedEntities);
		return foundUsersTOs;
	}

	@Override
	public List<UserProfileTO> findUsersByFirstName(String firstName) {
		List<UserEntity> collectedEntities = users.stream().filter(p -> firstName.equals(p.getName()))
				.collect(Collectors.toList());
		List<UserProfileTO> foundUsersTOs = UserProfileMapper.map2TOs(collectedEntities);
		return foundUsersTOs;
	}

	@Override
	public List<UserProfileTO> findUsersByLastName(String lastName) {
		List<UserEntity> collectedEntities = users.stream().filter(p -> lastName.equals(p.getSurname()))
				.collect(Collectors.toList());
		List<UserProfileTO> foundUsersTOs = UserProfileMapper.map2TOs(collectedEntities);
		return foundUsersTOs;
	}

	@Override
	public List<UserProfileTO> findUsers(String userId, String firstName, String lastName) {
		List<UserEntity> collectedEntities = users.stream().filter(p -> userId.equals(p.getLogin()))
				.filter(p -> firstName.equals(p.getName())).filter(p -> lastName.equals(p.getSurname()))
				.collect(Collectors.toList());
		List<UserProfileTO> foundTOs = UserProfileMapper.map2TOs(collectedEntities);
		return foundTOs;
	}

	@Override
	public boolean setUserFirstName(long userId, String newFirstName) {
		boolean result = true;
		int userEntityIdListPosition = this.findUserEntityById(userId);
		if (userEntityIdListPosition < 0) {
			result = false;
		} else {
			users.get(userEntityIdListPosition).setName(newFirstName);
		}
		return result;
	}

	@Override
	public boolean setUserLastName(long userId, String newLastName) {
		boolean result = true;
		int userEntityIdListPosition = this.findUserEntityById(userId);
		if (userEntityIdListPosition < 0) {
			result = false;
		} else {
			users.get(userEntityIdListPosition).setSurname(newLastName);
		}
		return result;
	}

	@Override
	public boolean setUserEmailAddress(long userId, String newEmailAddress) {
		boolean result = true;
		int userEntityIdListPosition = this.findUserEntityById(userId);
		if (userEntityIdListPosition < 0) {
			result = false;
		} else {
			users.get(userEntityIdListPosition).setEmail(newEmailAddress);
		}
		return result;
	}

	@Override
	public boolean setUserAboutMeNote(long userId, String newAboutMeNote) {
		boolean result = true;
		int userEntityIdListPosition = this.findUserEntityById(userId);
		if (userEntityIdListPosition < 0) {
			result = false;
		} else {
			users.get(userEntityIdListPosition).setAboutMe(newAboutMeNote);
		}
		return result;
	}

	@Override
	public boolean setUserLifeMotto(long userId, String newLifeMotto) {
		boolean result = true;
		int userEntityIdListPosition = this.findUserEntityById(userId);
		if (userEntityIdListPosition < 0) {
			result = false;
		} else {
			users.get(userEntityIdListPosition).setLifeMotto(newLifeMotto);
		}
		return result;
	}

	@Override
	public boolean setUserProfile(long userId, String newFirstName, String newLastName, String newEmailAddress,
			String newAboutMeNote, String newLifeMotto) {
		boolean result = true;
		int userEntityIdListPosition = this.findUserEntityById(userId);
		if (userEntityIdListPosition < 0) {
			result = false;
		} else {
			UserEntity userEntity = users.get(userEntityIdListPosition);
			userEntity.setName(newFirstName);
			userEntity.setSurname(newLastName);
			userEntity.setEmail(newEmailAddress);
			userEntity.setAboutMe(newAboutMeNote);
			userEntity.setLifeMotto(newLifeMotto);
		}
		return result;
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

	private UserEntity createUser(String loginId, String firstName, String surname) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(nextUserLogin++);
		userEntity.setLogin(loginId);
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
