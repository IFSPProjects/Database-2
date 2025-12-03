package com.database2.storage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database2.storage.entity.State;

public interface StateRepository extends JpaRepository<State, Integer> {
    Optional<State> findStateBystate(String state);
}
