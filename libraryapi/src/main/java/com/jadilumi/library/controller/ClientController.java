package com.jadilumi.library.controller;

import com.jadilumi.library.domain.entities.client.Client;
import com.jadilumi.library.domain.entities.client.dto.ClientDTO;
import com.jadilumi.library.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody @Valid ClientDTO receivedData) {
        return new ResponseEntity<>(clientService.saveClient(receivedData), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{clientId}")
    public ResponseEntity<Client> editClient(@RequestBody @Valid ClientDTO receivedData, @PathVariable UUID clientId) {
        return new ResponseEntity<>(clientService.editClient(receivedData, clientId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Page<Client>> listAllClients(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(clientService.getAllClients(page, size), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/document/{document}")
    public ResponseEntity<Client> listClientByDocument(
            @PathVariable String document
    ) {
        return new ResponseEntity<>(clientService.getClientByDocument(document), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/id/{clientId}")
    public ResponseEntity<Client> listClientById(@PathVariable UUID clientId) {
        return new ResponseEntity<>(clientService.getClientById(clientId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/del/{clientId}")
    public ResponseEntity<String> deleteClientById(@PathVariable UUID clientId) {
        clientService.deleteClientById(clientId);
        return new ResponseEntity<>("Client deleted successfully", HttpStatus.GONE);
    }
}
