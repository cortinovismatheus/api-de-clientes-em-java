package com.cortinovis.clients.service;

import com.cortinovis.clients.exceptions.InvalidClientException;
import com.cortinovis.clients.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RemoveClientServiceTest {

  private ClientRepository clientRepository;
  private RemoveClientService removeClientService;

  @BeforeEach
  void setUp() {
    clientRepository = mock(ClientRepository.class);
    removeClientService = new RemoveClientService(clientRepository);
  }

  @Test
  void ShouldDeleteUserWithAValidID () throws InvalidClientException {

    Long id = 1L;

    when(clientRepository.existsById(id)).thenReturn(true);

    removeClientService.removeClient(id);

    verify(clientRepository).deleteById(id);
  }

  @Test
  void ShouldThrowExceptionWhenIDDoesNotExist () {
      Long id  = 1L;
      when(clientRepository.existsById(id)).thenReturn(false);

      assertThrows(
        InvalidClientException.class,
        () -> removeClientService.removeClient(id)
      );

      verify(clientRepository, never()).deleteById(id);
  }
}
