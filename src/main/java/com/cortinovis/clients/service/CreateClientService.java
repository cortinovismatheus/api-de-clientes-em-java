package com.cortinovis.clients.service;

import com.cortinovis.clients.exceptions.InvalidClientException;
import com.cortinovis.clients.model.Client;
import com.cortinovis.clients.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateClientService {
  private final ClientRepository clientRepository;

  public CreateClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public Client createClient(Client client) throws InvalidClientException {
    validateClient(client);

    return clientRepository.save(client);
  }

  private void validateClient(Client client) throws InvalidClientException {


    if(client.getName() == null || client.getName().isBlank()) {
      throw new InvalidClientException("Client name must not be null");
    }

    if(client.getEmail() == null  || client.getEmail().isBlank()) {
      throw new InvalidClientException("Client email must not be null");
    }

    if(!client.getEmail().contains("@")){
      throw new InvalidClientException("Invalid email address");
    }

    if(client.getPhone() == null || client.getPhone().isBlank()) {
      throw new InvalidClientException("Client phone must not be null");
    }

    String phone = client.getPhone().replaceAll("\\D", "" );

    if (phone.length() != 10 && phone.length() != 11) {
      throw new InvalidClientException("Invalid phone");
    }

    if(client.getSocialMedia() == null || client.getSocialMedia().isBlank()) {
      throw new InvalidClientException("Client social media must not be null");
    }

    if(client.getActive() == null) {
      throw new InvalidClientException("Client active must not be null");
    }
  }
}
