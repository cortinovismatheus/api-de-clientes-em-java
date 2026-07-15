package com.cortinovis.clients.service;

import com.cortinovis.clients.exceptions.InvalidClientException;
import com.cortinovis.clients.model.Client;
import com.cortinovis.clients.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoveClientService {
  private final ClientRepository clientRepository;

  public RemoveClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public void removeClient(Long id) throws InvalidClientException {
    List<Client> clients = clientRepository.readClients();

    boolean removed = clients.removeIf(client ->
            client.getId().equals(id));

    if (!removed) {
      throw new InvalidClientException("Client not found");
    }

    clientRepository.saveClients(clients);
  }
}
