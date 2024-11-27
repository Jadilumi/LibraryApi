package com.jadilumi.library.service;


import com.jadilumi.library.domain.entities.client.Client;
import com.jadilumi.library.domain.entities.client.ClientMapper;
import com.jadilumi.library.domain.entities.client.dto.ClientDTO;
import com.jadilumi.library.domain.repository.ClientRepository;
import com.jadilumi.library.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;


    public Client saveClient(ClientDTO receivedData) {
        Client createdClient = new Client();
        clientMapper.mapDTOToEntity(receivedData, createdClient);
        return clientRepository.save(createdClient);
    }

    public Client editClient(ClientDTO receivedData, UUID clientId) {
        Client receivedClient = clientRepository.findById(clientId).orElseThrow(() -> new CustomException("Client not found!", HttpStatus.NOT_FOUND));
        clientMapper.mapDTOToEntity(receivedData, receivedClient);
        return clientRepository.save(receivedClient);
    }

    public Page<Client> getAllClients(int page, int size) {
        return clientRepository.findAll(PageRequest.of(page, size));
    }

    public Client getClientById(UUID clientId) {
        System.out.println(clientRepository.findById(clientId).orElseThrow(() -> new CustomException("Client not found!", HttpStatus.NOT_FOUND)).getLoans().get(0).getLoanDays());
        return clientRepository.findById(clientId).orElseThrow(() -> new CustomException("Client not found!", HttpStatus.NOT_FOUND));
    }

    public Client getClientByDocument(String document) {
        return clientRepository.findClientByDocument(document).orElseThrow(() -> new CustomException("Client not found!", HttpStatus.NOT_FOUND));
    }

    public void deleteClientById(UUID clientId) {
        clientRepository.findById(clientId).orElseThrow(() -> new CustomException("Client not found!", HttpStatus.NOT_FOUND));
        clientRepository.deleteById(clientId);
    }
}
