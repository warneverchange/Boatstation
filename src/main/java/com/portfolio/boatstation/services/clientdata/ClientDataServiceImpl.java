package com.portfolio.boatstation.services.clientdata;

import com.portfolio.boatstation.entities.ClientData;
import com.portfolio.boatstation.entities.security.User;
import com.portfolio.boatstation.repositories.client.ClientDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientDataServiceImpl implements ClientDataService {
    private final ClientDataRepository clientDataRepository;

    @Autowired
    public ClientDataServiceImpl(ClientDataRepository clientDataRepository) {
        this.clientDataRepository = clientDataRepository;
    }

    @Override
    public ClientData getCurrentClientData() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return clientDataRepository.getClientDataById(user.getClientDataId());
    }

    @Override
    public void updateCurrentUserClientData(ClientData updatedClientData) {
        clientDataRepository.updateClientData(updatedClientData);
    }

    @Override
    public ClientData getClientDataById(Long clientDataId) {
        return clientDataRepository.getClientDataById(clientDataId);
    }

    @Override
    public List<ClientData> getAllClients() {
        return clientDataRepository.getAllClients().orElseThrow();
    }
}
