package com.example.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@MappedSuperclass
public abstract class BaseEntity<ID> {

    @Column(name = "creation_time", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationTime;

    @Column(name = "modification_time", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modificationTime;
    
    /*@Transient
    private DateTimeFormatter dtf = DateTimeFormat.forPattern("yy/M/d hh:mm");;*/
    
	@Version
    private long version;

    public abstract ID getId();

    public DateTime getCreationTime() {
        return creationTime;
    }

    public String getModificationTime() {
        return modificationTime.toString("yy/M/d hh:mm");
    }

    public long getVersion() {
        return version;
    }

    @PrePersist
    public void prePersist() {
        DateTime now = DateTime.now();
        this.creationTime = now;
        this.modificationTime = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = DateTime.now();
    }
}