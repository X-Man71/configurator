package de.hs.furtwangen.bam.jee.configurator.queries;

import com.mysema.query.types.Predicate;

import de.hs.furtwangen.bam.jee.configurator.model.QPermission;

public class PermissionPredicates {
	
	public static Predicate permissionByRoleId(final Long roleId)
	{
		QPermission permission = QPermission.permission;
		return permission.permRoles.any().id.eq(roleId);
	}

}
