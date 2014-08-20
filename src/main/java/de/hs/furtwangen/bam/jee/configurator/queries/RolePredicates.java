package de.hs.furtwangen.bam.jee.configurator.queries;

import com.mysema.query.types.Predicate;

import de.hs.furtwangen.bam.jee.configurator.model.QRole;

public class RolePredicates {

		public static Predicate roleByUserId(final Long userId){
			QRole role = QRole.role;
			return role.userRoles.any().id.eq(userId);
		}
}
