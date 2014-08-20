package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;

import de.hs.furtwangen.bam.jee.configurator.model.Role;
import de.hs.furtwangen.bam.jee.configurator.queries.RolePredicates;

public class RoleRepositoryImpl implements RoleRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	private QueryDslJpaRepository<Role, Long> roleRepository;

	@Override
	public List<Role> findAllRoleForUser(Long userId) {
		return roleRepository.findAll(RolePredicates.roleByUserId(userId));

	}

	@PostConstruct
	public void init() {
		JpaEntityInformation<Role, Long> roleEntityInfo = new JpaMetamodelEntityInformation<Role, Long>(
				Role.class, entityManager.getMetamodel());
		roleRepository = new QueryDslJpaRepository<Role, Long>(roleEntityInfo,
				entityManager);
	}

}
