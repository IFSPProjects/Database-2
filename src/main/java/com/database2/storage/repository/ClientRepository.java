package com.database2.storage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database2.storage.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{
    Optional<Client> findClientByid(Integer id);
    Optional<Client> findClientByCPF(String CPF);
}
