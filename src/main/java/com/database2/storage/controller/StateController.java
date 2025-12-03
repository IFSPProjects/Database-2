package com.database2.storage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.database2.storage.dto.response.StateListResponse;
import com.database2.storage.dto.response.StateResponse;
import com.database2.storage.entity.State;
import com.database2.storage.repository.StateRepository;

@RestController
@RequestMapping("/storage")
public class StateController {

    private final StateRepository stateRepository;

    public StateController(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @GetMapping("/getState/{id}")
    public ResponseEntity<StateResponse> getState(@PathVariable("id") String id) {
        State state = stateRepository.findStateBystate(id.toUpperCase()).get();
        return ResponseEntity.status(HttpStatus.OK).body(new StateResponse("Success", state));
    }

    @GetMapping("/getStates")
    public ResponseEntity<StateListResponse> getStates() {
        List<State> states = stateRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new StateListResponse("Success", states));
    }
    
}
