package com.cortinovis.clients.service;

import com.cortinovis.clients.model.Client;
import com.cortinovis.clients.repository.ClientRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetClientsService {
  private final ClientRepository clientRepository;

  public GetClientsService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public List<Client> getClients() {
    return clientRepository.findAll();
  }
}
