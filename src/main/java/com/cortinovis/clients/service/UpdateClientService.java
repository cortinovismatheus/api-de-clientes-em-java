package com.cortinovis.clients.service;

import com.cortinovis.clients.exceptions.InvalidClientException;
import com.cortinovis.clients.model.Client;
import com.cortinovis.clients.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateClientService {

  private final ClientRepository clientRepository;

  public UpdateClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public Client updateClient(Long id, Client client) throws InvalidClientException {

    Client currentClient = clientRepository.findById(id)
            .orElseThrow(() -> new InvalidClientException("Client not found"));


    if(client.getName() != null){
      currentClient.setName(client.getName());
    }

    if(client.getEmail() != null){
      currentClient.setEmail(client.getEmail());
    }

    if(client.getPhone() != null){
      currentClient.setPhone(client.getPhone());
    }

    if(client.getActive() != null){
      currentClient.setActive(client.getActive());
    }

    if(client.getSocialMedia() != null){
      currentClient.setSocialMedia(client.getSocialMedia());
    }


    return clientRepository.save(currentClient);
  }
}