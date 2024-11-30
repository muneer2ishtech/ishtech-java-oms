package fi.ishtech.practice.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fi.ishtech.practice.oms.entity.User;
import fi.ishtech.practice.oms.entity.UserProfile;
import fi.ishtech.practice.oms.mapper.UserProfileMapper;
import fi.ishtech.practice.oms.payload.in.SignupRequest;
import fi.ishtech.practice.oms.repo.UserProfileRepo;
import fi.ishtech.practice.oms.service.UserProfileService;
import fi.ishtech.practice.oms.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Muneer Ahmed Syed
 */
@Service
@Slf4j
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private UserProfileRepo userProfileRepo;

	@Autowired
	private UserProfileMapper userProfileMapper;

	@Autowired
	private UserService userService;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public UserProfileRepo getRepo() {
		return userProfileRepo;
	}

	@Override
	public UserProfileMapper getMapper() {
		return userProfileMapper;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public Long create(SignupRequest signupRequest) {
		User user = userService.create(signupRequest);

		UserProfile userProfile = userProfileMapper.toNewEntity(signupRequest);

		userProfile.setId(user.getId());
		userProfile.setUser(user);

		userProfile = userProfileRepo.saveAndFlush(userProfile);
		log.info("New UserProfile({}) created", userProfile.getId());

		return userProfile.getId();
	}

}