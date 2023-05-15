package com.portfolio.boatstation.services.clientdata;


import com.portfolio.boatstation.entities.ClientData;

import java.util.List;

public interface ClientDataService {
    ClientData getCurrentClientData();

    void updateCurrentUserClientData(ClientData updatedClientData);

    ClientData getClientDataById(Long clientDataId);

    List<ClientData> getAllClients();
}
