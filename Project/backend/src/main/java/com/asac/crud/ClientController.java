package com.asac.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = "{clientId}")
    public Optional<Client> getClient(@PathVariable("clientId") String id) {
        return clientService.findClientById(id);
    }


    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @PostMapping("/register")
    public void registerNewClient(@RequestBody Client client) {
        clientService.addClient(client);
    }

    @DeleteMapping(path = "{id}")
    public void deleteClient(
            @PathVariable("id") String id,
            @RequestParam String privateCode
    ) {
        clientService.deleteClient(new Client(id, null, privateCode));
    }

    @PutMapping(path = "{id}")
    public void updateClient(
            @PathVariable("id") String id,
            @RequestParam String privateCode,
            @RequestParam(required = false) String nickName
    ) {
        clientService.updateClient(new Client(id, nickName, privateCode));
    }

}
