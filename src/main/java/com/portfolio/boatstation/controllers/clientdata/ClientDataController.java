package com.portfolio.boatstation.controllers.clientdata;

import com.portfolio.boatstation.entities.ClientData;
import com.portfolio.boatstation.services.clientdata.ClientDataService;
import com.portfolio.boatstation.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientDataController {
    private final ClientDataService clientDataService;
    private final UserService userService;

    @Autowired
    public ClientDataController(ClientDataService clientDataService, UserService userService) {
        this.clientDataService = clientDataService;
        this.userService = userService;
    }

    @GetMapping("/data")
    public ResponseEntity<ClientData> getCurrentClientData() {
        return ResponseEntity.ok(clientDataService.getCurrentClientData());
    }

    @PostMapping("/delete")
    public void deleteCurrentUserAccount(){
        userService.deleteCurrentUserAccount();
    }

    @PostMapping("/update")
    public void updateCurrentUserClientData(@RequestBody ClientData updatedClientData) {
        clientDataService.updateCurrentUserClientData(updatedClientData);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<ClientData>> getAllClients() {
        return ResponseEntity.ok(clientDataService.getAllClients());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{clientDataId}")
    public ResponseEntity<ClientData> getClientDataById(@PathVariable("clientDataId") Long clientDataId) {
        return ResponseEntity.ok(clientDataService.getClientDataById(clientDataId));
    }
}
