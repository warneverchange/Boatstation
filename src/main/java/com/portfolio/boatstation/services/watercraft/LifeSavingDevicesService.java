package com.portfolio.boatstation.services.watercraft;

import com.portfolio.boatstation.entities.LifeSavingDeviceType;
import com.portfolio.boatstation.entities.views.AllLifeSavingDevicesView;
import com.portfolio.boatstation.entities.views.FreeLifeSavingDevicesView;
import com.portfolio.boatstation.entities.views.LifeSavingDevicesView;

import java.util.List;

public interface LifeSavingDevicesService {
    List<LifeSavingDevicesView>  getLifeSavingDevicesViews();

    List<FreeLifeSavingDevicesView> getFreeLifeSavingDevices();

    void deleteLifeSavingDeviceLog(Long lifeSavingDeviceLogId);

    void createLifeSavingDeviceLog(Long lifeSavingDeviceId, Long watercraftLogId);

    List<AllLifeSavingDevicesView> getAllLifeSavingDevices();

    List<LifeSavingDeviceType> getLsdTypes();

    void createLifeSavingDevice(Long lsdTypeId);
}
