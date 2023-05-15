package com.portfolio.boatstation.services.watercraft;

import com.portfolio.boatstation.entities.LifeSavingDeviceType;
import com.portfolio.boatstation.entities.views.AllLifeSavingDevicesView;
import com.portfolio.boatstation.entities.views.FreeLifeSavingDevicesView;
import com.portfolio.boatstation.entities.views.LifeSavingDevicesView;
import com.portfolio.boatstation.repositories.lsdevices.LifeSavingDeviceLogRepository;
import com.portfolio.boatstation.repositories.lsdevices.LifeSavingDeviceRepository;
import com.portfolio.boatstation.repositories.lsdevices.LifeSavingDeviceTypeRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LifeSavingDevicesServiceImpl implements LifeSavingDevicesService{
    private final EntityManager entityManager;
    private final LifeSavingDeviceLogRepository lifeSavingDeviceLogRepository;

    private final LifeSavingDeviceTypeRepository lifeSavingDeviceTypeRepository;
    private final LifeSavingDeviceRepository lifeSavingDeviceRepository;

    @Autowired
    public LifeSavingDevicesServiceImpl(EntityManager entityManager, LifeSavingDeviceLogRepository lifeSavingDeviceLogRepository, LifeSavingDeviceTypeRepository lifeSavingDeviceTypeRepository, LifeSavingDeviceRepository lifeSavingDeviceRepository) {
        this.entityManager = entityManager;
        this.lifeSavingDeviceLogRepository = lifeSavingDeviceLogRepository;
        this.lifeSavingDeviceTypeRepository = lifeSavingDeviceTypeRepository;
        this.lifeSavingDeviceRepository = lifeSavingDeviceRepository;
    }


    @Override
    public List<LifeSavingDevicesView> getLifeSavingDevicesViews() {
        return entityManager.createNativeQuery("select * from _life_saving_devices", LifeSavingDevicesView.class).getResultList();
    }

    @Override
    public List<FreeLifeSavingDevicesView> getFreeLifeSavingDevices() {
        return entityManager.createNativeQuery("select* from _free_life_saving_devices", FreeLifeSavingDevicesView.class).getResultList();
    }

    @Override
    public void deleteLifeSavingDeviceLog(Long lifeSavingDeviceLogId) {
        lifeSavingDeviceLogRepository.deleteLifeSavingDeviceLog(lifeSavingDeviceLogId);
    }

    @Override
    public void createLifeSavingDeviceLog(Long lifeSavingDeviceId, Long watercraftLogId) {
        lifeSavingDeviceLogRepository.createLog(lifeSavingDeviceId, watercraftLogId);
    }

    @Override
    public List<AllLifeSavingDevicesView> getAllLifeSavingDevices() {
        return entityManager.createNativeQuery("select * from all_life_saving_devices", AllLifeSavingDevicesView.class).getResultList();
    }

    @Override
    public List<LifeSavingDeviceType> getLsdTypes() {
        return lifeSavingDeviceTypeRepository.getLsdTypes().orElseThrow();
    }

    @Override
    public void createLifeSavingDevice(Long lsdTypeId) {
        lifeSavingDeviceRepository.createLifeSavingDevice(lsdTypeId);
    }
}
