package com.cortinovis.clients.controller;

import com.cortinovis.clients.exceptions.InvalidClientException;
import com.cortinovis.clients.model.Client;
import com.cortinovis.clients.service.CreateClientService;
import com.cortinovis.clients.service.GetClientsService;
import com.cortinovis.clients.service.RemoveClientService;
import com.cortinovis.clients.service.UpdateClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
  private final GetClientsService getClientsService;
  private final CreateClientService createClientService;
  private final RemoveClientService removeClientService;
  private final UpdateClientService updateClientService;

  public ClientController(GetClientsService getClientsService,
                          CreateClientService createClientService,
                          RemoveClientService removeClientService,
                          UpdateClientService updateClientService) {

    this.getClientsService = getClientsService;
    this.createClientService = createClientService;
    this.removeClientService = removeClientService;
    this.updateClientService = updateClientService;
  }

  @GetMapping
  public ResponseEntity<List<Client>> getAllClients() {
    List<Client> clients = getClientsService.getClients();
    return ResponseEntity.ok(clients);
  }

  @PostMapping
  public ResponseEntity<Client> save(@RequestBody Client client) throws InvalidClientException {
    Client createdClient = createClientService.createClient(client);
    return ResponseEntity.status(201).body(createdClient);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Client> delete(@PathVariable Long id) throws InvalidClientException {
    removeClientService.removeClient(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) throws InvalidClientException {
    Client updatedClient = updateClientService.updateClient(id, client);
    return ResponseEntity.ok(updatedClient);
  }
}
