package one.digitalinnovation.estacionamento.service;

import one.digitalinnovation.estacionamento.exception.ParkingNotFoundException;
import one.digitalinnovation.estacionamento.model.Parking;
import one.digitalinnovation.estacionamento.repository.ParkingRepository;
import one.digitalinnovation.estacionamento.util.Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService {

    private final ParkingRepository repository;

    public ParkingService(ParkingRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll() {
        return repository.findAll();
    }


    @Transactional(readOnly = true)
    public Parking findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
    }

    @Transactional
    public Parking create(Parking parkingCreate) {
        parkingCreate.setId(Util.getUUID());
        parkingCreate.setEntryDate(LocalDateTime.now());
        repository.save(parkingCreate);
        return parkingCreate;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    @Transactional
    public Parking update(String id, Parking parkingCreate) {
        //TODO - ver quais estão nulos ou vazios

        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parking.setState(parkingCreate.getState());
        parking.setLicense(parkingCreate.getLicense());
        parking.setModel(parkingCreate.getModel());
        repository.save(parking);
        return parking;
    }

    @Transactional
    public Parking exit(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckout.getBill(parking));
        repository.save(parking);
        return parking;
    }
}
