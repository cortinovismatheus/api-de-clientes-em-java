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

  public void removeClient(Long id) throws InvalidClientException{

    if(!clientRepository.existsById(id)){
      throw new InvalidClientException("Client with id " + id + " does not exist");
    }

    clientRepository.deleteById(id);
  }
}
