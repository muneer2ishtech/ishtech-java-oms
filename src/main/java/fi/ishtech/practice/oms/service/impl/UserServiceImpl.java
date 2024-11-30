package fi.ishtech.practice.oms.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import fi.ishtech.practice.oms.entity.User;
import fi.ishtech.practice.oms.mapper.UserMapper;
import fi.ishtech.practice.oms.payload.in.SignupRequest;
import fi.ishtech.practice.oms.repo.UserRepo;
import fi.ishtech.practice.oms.service.UserRoleService;
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
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Lazy
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private EntityManager entityManager;

	@Override
	public UserRepo getRepo() {
		return userRepo;
	}

	@Override
	public UserMapper getMapper() {
		return userMapper;
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Optional<User> findOneByEmail(String email) {
		return userRepo.findOneByEmail(email);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	@Override
	public User create(SignupRequest signupRequest) {
		Assert.isTrue(!userRepo.existsByEmail(signupRequest.getEmail()), "Email already exists");

		User user = userMapper.toNewEntity(signupRequest);

		user.setUsername(user.getEmail());
		user.setPasswordHash(passwordEncoder.encode(signupRequest.getPassword()));

		user = userRepo.saveAndFlush(user);
		log.info("New User({}) created for email:{}", user.getId(), user.getEmail());

		// create default role
		userRoleService.createForSignup(user);

		return user;
	}

}