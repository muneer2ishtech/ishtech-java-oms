package fi.ishtech.practice.oms.repo;

import java.util.Optional;

import fi.ishtech.practice.oms.entity.User;

/**
 *
 * @author Muneer Ahmed Syed
 */
public interface UserRepo extends BaseRepo<User> {

	boolean existsByEmail(String email);

	boolean existsByIdAndEmail(Long id, String email);

	Optional<User> findOneByEmail(String email);

}