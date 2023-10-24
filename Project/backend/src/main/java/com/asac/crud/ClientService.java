package com.asac.crud;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> findClientById(String id) {
        return clientRepository.findClientById(id);
    }

    public Client getClient(Client client) throws NoSuchElementException {
        return findClientById(client.getId()).orElseThrow();
    }

    public void addClient(Client client) {
        try {
            getClient(client);
            throw new IllegalStateException("id already exist");
        } catch (NoSuchElementException e) {
            clientRepository.save(client);
        }
    }

    public boolean authClient(Client client) {
        try {
            byte[] a = getClient(client).getPrivateCode();
            byte[] b = client.getPrivateCode();
            System.err.println(Arrays.equals(a, b));
            return Arrays.equals(a, b);
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public void deleteClient(Client client) {
        if (authClient(client))
            deleteClientByIndex(
                    findClientById(client.getId()).orElseThrow().getIndex()
            );
    }


    public void deleteClientByIndex(Long index) {
        clientRepository.deleteById(index);
    }

    @Transactional
    public void updateClient(Client client){
        if (authClient(client)){
            getClient(client).setNickName(client.getNickName());
        }
    }

}
