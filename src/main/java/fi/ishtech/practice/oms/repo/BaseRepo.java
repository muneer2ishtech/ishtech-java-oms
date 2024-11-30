package fi.ishtech.practice.oms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import fi.ishtech.practice.oms.entity.BaseEntity;

/**
 *
 * @author Muneer Ahmed Syed
 */
@NoRepositoryBean
public interface BaseRepo<T extends BaseEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}