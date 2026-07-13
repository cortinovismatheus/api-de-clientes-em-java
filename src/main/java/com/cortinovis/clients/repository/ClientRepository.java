package com.cortinovis.clients.repository;

import com.cortinovis.clients.exceptions.CsvReadException;
import com.cortinovis.clients.exceptions.CsvWriteException;
import com.cortinovis.clients.model.Client;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepository {

  private static final String FILE_PATH = "src/main/resources/clients.csv";

  public List<Client> readClients() {
    List<Client> clients = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(
            new FileReader(FILE_PATH)
    )) {

      String line;

      reader.readLine();

      while ((line = reader.readLine()) != null) {

        if (line.isBlank()) {
          continue;
        }

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

    } catch (IOException e) {
      throw new CsvReadException(
              "Erro ao ler o arquivo clients.csv",
              e
      );
    }

    return clients;
  }

  public void saveClient(Client client) {

    File file = new File(FILE_PATH);
    System.out.println("Salvando em: " + file.getAbsolutePath());

    try (BufferedWriter writer = new BufferedWriter(
            new FileWriter(FILE_PATH, true)
    )) {

      writer.write(
              client.getId() + "," +
                      client.getName() + "," +
                      client.getEmail() + "," +
                      client.getPhone() + "," +
                      client.getSocialMedia() + "," +
                      client.getActive()
      );

      writer.newLine();

    } catch (IOException e) {
      throw new CsvWriteException(
              "Erro ao salvar cliente no arquivo clients.csv",
              e
      );
    }
  }

  public void saveClients(List<Client> clients) {

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {

      writer.write("id,name,email,phone,socialMedia,active");
      writer.newLine();

      for (Client client : clients) {

        writer.write(
                client.getId() + "," +
                        client.getName() + "," +
                        client.getEmail() + "," +
                        client.getPhone() + "," +
                        client.getSocialMedia() + "," +
                        client.getActive()
        );

        writer.newLine();
      }

    } catch (IOException e) {
      throw new CsvWriteException("Error writing clients CSV", e);
    }
  }
}