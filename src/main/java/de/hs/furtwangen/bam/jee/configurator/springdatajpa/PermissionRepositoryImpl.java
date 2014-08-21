package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;

import de.hs.furtwangen.bam.jee.configurator.model.Permission;
import de.hs.furtwangen.bam.jee.configurator.queries.PermissionPredicates;

public class PermissionRepositoryImpl implements PermissionRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	private QueryDslJpaRepository<Permission, Long> permissionRepository;
	
	@Override
	public List<Permission> findAllPermissionForRole(Long roleId){
		return permissionRepository.findAll(PermissionPredicates.permissionByRoleId(roleId));
	}
	
	
	
	@PostConstruct
	public void init() {
		JpaEntityInformation<Permission, Long> permissionEntityInfo = new JpaMetamodelEntityInformation<Permission, Long>(
				Permission.class, entityManager.getMetamodel());
		permissionRepository = new QueryDslJpaRepository<Permission, Long>(permissionEntityInfo,
				entityManager);
	}

}
