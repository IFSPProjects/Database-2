package com.database2.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "State")
public class State {

    @Id
    @Column(name = "state_id", nullable = false)
    private String state;

    @Column(name = "description", nullable = false)
    private String description;
}
