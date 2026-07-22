package com.cortinovis.clients.service;

import com.cortinovis.clients.exceptions.InvalidClientException;
import com.cortinovis.clients.model.Client;
import com.cortinovis.clients.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UpdateClientsServiceTest {

  private ClientRepository clientRepository;
  private UpdateClientService updateClientService;

  @BeforeEach
  void setUp(){
    clientRepository = mock(ClientRepository.class);
    updateClientService = new UpdateClientService(clientRepository);
  }

  public Client client(){
    Client client = new Client(
            1L,
            "Teste",
            "Teste@gmail.com",
            "(44) 98804-0921",
            "@test",
            true
    );

    return client;
  }

  public Client clientUpdated(){
    Client client = new Client(
            1L,
            "Maria",
            "maria@gmail.com",
            "(44)98888-8888",
            "@maria",
            false
    );
    return client;
  }

  @Test
  public void shouldUpdateClientSuccessfully() throws InvalidClientException {
    Client client = client();
    Client clientUpdated = clientUpdated();

    when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

    when(clientRepository.save(any(Client.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

    Client result = updateClientService.updateClient(1L, clientUpdated);

    assertEquals("Maria", result.getName());
    assertEquals("maria@gmail.com", result.getEmail());
    assertEquals("(44)98888-8888", result.getPhone());
    assertEquals("@maria", result.getSocialMedia());
    assertFalse(result.getActive());

    verify(clientRepository).save(any(Client.class));
  }

  @Test
  public void shouldThrowExceptionWhenClientDoesNotExist () throws InvalidClientException {
    Client client = client();
    when(clientRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(
            InvalidClientException.class,
            () -> updateClientService.updateClient(1L, client)
    );

    verify(clientRepository, never()).save(any(Client.class));

  }

  @Test
  void shouldThrowExceptionWhenPhoneIsInvalid() {

    Client current = client();
    Client updated = clientUpdated();

    updated.setPhone("123");

    when(clientRepository.findById(1L))
            .thenReturn(Optional.of(current));

    assertThrows(
            InvalidClientException.class,
            () -> updateClientService.updateClient(1L, updated)
    );

    verify(clientRepository, never()).save(any(Client.class));
  }

  @Test
  void shouldThrowExceptionWhenEmailIsInvalid() {

    Client current = client();
    Client updated = clientUpdated();

    updated.setEmail("test");

    when(clientRepository.findById(1L))
            .thenReturn(Optional.of(current));

    assertThrows(
            InvalidClientException.class,
            () -> updateClientService.updateClient(1L, updated)
    );

    verify(clientRepository, never()).save(any(Client.class));
  }

}
