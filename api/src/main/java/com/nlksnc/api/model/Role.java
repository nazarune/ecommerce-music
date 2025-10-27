package com.nlksnc.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "roles")
@Getter
@Setter
@With
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Role extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "roleName")
    private ERole name;
}
