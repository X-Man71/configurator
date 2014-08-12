/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.hs.furtwangen.bam.jee.configurator.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Version;

/**
 * Simple JavaBean domain object with an id property. Used as a base class for objects needing this property.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
@MappedSuperclass
public class BaseEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    
    @Version
    @Column(name = "version")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    public LocalDateTime version;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

	public LocalDateTime getVersion() {
		return version;
	}

	public void setVersion(LocalDateTime version) {
		this.version = version;
	}

	public boolean isNew() {
        return (this.id == null);
    }

}
