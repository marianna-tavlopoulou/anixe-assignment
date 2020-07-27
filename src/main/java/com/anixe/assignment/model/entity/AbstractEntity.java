package com.anixe.assignment.model.entity;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;



@MappedSuperclass
@Data
public abstract class AbstractEntity {

    public static final String SYSTEM = "system";

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "INSERT_TS")
    private LocalDateTime insertedAt;

    @Column(name = "INSERTED_BY")
    private String insertedBy;

    @Column(name = "UPDATE_TS")
    private LocalDateTime updatedAt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @PrePersist
    public void prePersist() {
        this.id = StringUtils.isEmpty(id) ? uniqueId() : id;
        this.setInsertedAt(LocalDateTime.now());
        this.setInsertedBy(SYSTEM);
        this.setUpdatedAt(LocalDateTime.now());
        this.setUpdatedBy(SYSTEM);
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
        this.setUpdatedBy(SYSTEM);
    }

    public static String uniqueId() {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }
}
