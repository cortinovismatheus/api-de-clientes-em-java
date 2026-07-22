package com.cortinovis.clients.service;

import com.cortinovis.clients.exceptions.InvalidClientException;
import com.cortinovis.clients.model.Client;
import com.cortinovis.clients.repository.ClientRepository;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;

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

    Validations(client);


    return clientRepository.save(currentClient);
  }

  private void Validations(Client client) throws InvalidClientException {
    if (client.getEmail() != null && !client.getEmail().contains("@")) {
      throw new InvalidClientException("Invalid email");
    }

    if (client.getPhone() != null &&
            client.getPhone().replaceAll("\\D", "").length() != 11) {
      throw new InvalidClientException("Invalid phone");
    }
  }
}