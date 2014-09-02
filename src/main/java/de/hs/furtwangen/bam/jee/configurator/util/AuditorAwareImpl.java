package de.hs.furtwangen.bam.jee.configurator.util;

import java.time.LocalDateTime;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<LocalDateTime> {

	@Override
	public LocalDateTime getCurrentAuditor() {
		return LocalDateTime.now();
	}

}
