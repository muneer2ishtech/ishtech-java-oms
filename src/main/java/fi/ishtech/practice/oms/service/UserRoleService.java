package fi.ishtech.practice.oms.service;

import java.util.List;

import fi.ishtech.practice.oms.entity.User;
import fi.ishtech.practice.oms.entity.UserRole;
import fi.ishtech.practice.oms.enums.UserRoleEnum;
import fi.ishtech.practice.oms.payload.UserRoleVo;

/**
 *
 * @author Muneer Ahmed Syed
 */
public interface UserRoleService extends BaseEntityService<UserRole, UserRoleVo> {

	UserRole createForSignup(User user);

	List<UserRoleEnum> findAllActiveEnumsByUserId(Long userId);

}