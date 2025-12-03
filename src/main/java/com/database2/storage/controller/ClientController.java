package com.database2.storage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.database2.storage.dto.request.ClientPostRequest;
import com.database2.storage.dto.response.ClientListResponse;
import com.database2.storage.dto.response.ClientResponse;
import com.database2.storage.entity.Client;
import com.database2.storage.repository.ClientRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/storage")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    
    @PostMapping("/addClient")
    public ResponseEntity<ClientResponse> addClient(@Valid @RequestBody ClientPostRequest request) {

        Client client = new Client();
        client.setName(request.name());
        client.setCPF(request.CPF());

        clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClientResponse("Success", client));
    }

    @PutMapping("/updateClient/{id}")
    public ResponseEntity<ClientResponse> addClient(@Valid @RequestBody ClientPostRequest request, @PathVariable("id") int id) {

        Client client = new Client();
        client.setId(id);
        client.setName(request.name());
        client.setCPF(request.CPF());

        clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.OK).body(new ClientResponse("Success", client));
    }

    @DeleteMapping("/deleteClient/{id}")
    public ResponseEntity<ClientResponse> deleteClient(@PathVariable("id") int id) {
        Optional<Client> client = clientRepository.findById(id);
        if (!client.isEmpty() && client.isPresent()) {
            clientRepository.delete(client.get());
            return ResponseEntity.status(HttpStatus.OK).body(new ClientResponse("Success", client.get()));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ClientResponse("Nothing was deleted. Verify if the correct ID is inserted.", null));
        }
    }

    @GetMapping("/getClient/{id}")
    public ResponseEntity<ClientResponse> getClient(@PathVariable("id") int id) {
        Client client = clientRepository.findClientByid(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(new ClientResponse("Success", client));
    }

    @GetMapping("/getClients")
    public ResponseEntity<ClientListResponse> getClients() {
        List<Client> clients = clientRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ClientListResponse("Success", clients));
    }
}
