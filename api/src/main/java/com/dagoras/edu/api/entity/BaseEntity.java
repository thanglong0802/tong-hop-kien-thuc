package com.dagoras.edu.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "create_date")
    @JsonIgnore
    private LocalDateTime createDate;
    @Column(name = "last_update")
    @JsonIgnore
    private LocalDateTime lastUpdate;
    @Column(name = "is_delete")
    @JsonIgnore
    private Boolean isDelete;
}
