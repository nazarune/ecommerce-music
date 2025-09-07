package com.nlksnc.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created_at")
    private ZonedDateTime createdDate;

    @LastModifiedDate
    @Column(name = "modified_at")
    private ZonedDateTime modifiedDate;

    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
