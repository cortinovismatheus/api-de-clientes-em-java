package com.cortinovis.clients.service;

import com.cortinovis.clients.exceptions.InvalidClientException;
import com.cortinovis.clients.model.Client;
import com.cortinovis.clients.repository.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateClientServiceTest {

  private ClientRepository clientRepository;
  private CreateClientService createClientService;

  @BeforeEach
  void setUp() {
    clientRepository = mock(ClientRepository.class);
    createClientService = new CreateClientService(clientRepository);
  }

  private Client createValidClient() {

    Client client = new Client();

    client.setName("Test");
    client.setEmail("test@gmail.com");
    client.setPhone("(44) 98804-0921");
    client.setSocialMedia("@test");
    client.setActive(true);

    return client;
  }

  @Test
  void shouldCreateClientSuccessfully() throws InvalidClientException {
    Client client = createValidClient();

    when(clientRepository.save(client)).thenReturn(client);


    Client result = createClientService.createClient(client);

    assertEquals(client, result);
    verify(clientRepository).save(client);
  }

  @Test
  void shouldThrowWhenNameIsNull() {
    Client client = createValidClient();

    client.setName(null);

    assertThrows(
            InvalidClientException.class,
            () -> createClientService.createClient(client)
    );
  }

  @Test
  void shouldThrowWhenEmailIsNull() {
    Client client = createValidClient();

    client.setEmail(null);

    assertThrows(
            InvalidClientException.class,
            () -> createClientService.createClient(client)
    );
  }

  @Test
  void shouldThrowWhenPhoneIsNull() {
    Client client = createValidClient();

    client.setPhone(null);

    assertThrows(
            InvalidClientException.class,
            () -> createClientService.createClient(client)
    );
  }

  @Test
  void shouldThrowWhenSocialMediaIsNull() {
    Client client = createValidClient();

    client.setSocialMedia(null);

    assertThrows(
            InvalidClientException.class,
            () -> createClientService.createClient(client)
    );
  }

  @Test
  void shouldThrowWhenActiveIsNull() {
    Client client = createValidClient();

    client.setActive(null);

    assertThrows(
            InvalidClientException.class,
            () -> createClientService.createClient(client)
    );
  }

  @Test
  void shouldThrowWhenEmailIsInvalid() {
    Client client = createValidClient();

    client.setEmail("testgmail.com");

    assertThrows(
            InvalidClientException.class,
            () -> createClientService.createClient(client)
    );
  }

  @Test
  void shouldThrowWhenPhoneIsInvalid() {
    Client client = createValidClient();

    client.setPhone("(44) 988-0921");

    assertThrows(
            InvalidClientException.class,
            () -> createClientService.createClient(client)
    );
  }
}