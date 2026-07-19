package com.cortinovis.clients.service;

import com.cortinovis.clients.exceptions.InvalidClientException;
import com.cortinovis.clients.model.Client;
import com.cortinovis.clients.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetClientServiceTest {

  private ClientRepository clientRepository;
  private GetClientsService getClientsService;

  @BeforeEach
  void setUp() {
    clientRepository = mock(ClientRepository.class);
    getClientsService = new GetClientsService(clientRepository);
  }

  private List<Client> createClients (){
    Client client1 = new Client();

    client1.setName("Test");
    client1.setEmail("test@gmail.com");
    client1.setPhone("(44) 98804-0921");
    client1.setSocialMedia("@test");
    client1.setActive(true);

    Client client2 = new Client();

    client2.setName("Test");
    client2.setEmail("test@gmail.com");
    client2.setPhone("(44) 98804-0921");
    client2.setSocialMedia("@test");
    client2.setActive(true);

    return List.of(client1, client2);
  }

  @Test
  void ShouldReturnAllClients (){
    List<Client> clients = createClients();

    when(clientRepository.findAll()).thenReturn(clients);

    List<Client> result = getClientsService.getClients();

    assertEquals(clients, result);

    verify(clientRepository).findAll();
  }
}
