package com.cortinovis.clients.repository;

import com.cortinovis.clients.exceptions.CsvReadException;
import com.cortinovis.clients.model.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public List<Client> SaveRepository() {
  private List<Client> clients = new ArrayList<>();

  try(BufferedReader reader = new BufferedReader(new FileReader("clients.csv"))){
    String line;

    reader.readLine();

    while((line = reader.line() != null)){
      String[] data = line.split(",");

      Client client = new Client(
              Long.parseLong(data[0]),
              data[1],
              data[2],
              data[3],
              data[4],
              Boolean.parseBoolean(data[5])
      );

      clients.add(client);
    }
  }catch(IDException e){
    throw new CsvReadException("Erro ao ler o arquivo clients.csv", e);
  }
  return clients;
}
