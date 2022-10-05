package one.digitalinnovation.estacionamento.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import one.digitalinnovation.estacionamento.dto.ParkingCreateDTO;
import one.digitalinnovation.estacionamento.dto.ParkingDTO;
import one.digitalinnovation.estacionamento.mapper.ParkingMapper;
import one.digitalinnovation.estacionamento.model.Parking;
import one.digitalinnovation.estacionamento.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@SecurityRequirement(name = "javainuse")
public class ParkingController {

    private final ParkingService service;
    private final ParkingMapper mapper;

    public ParkingController(ParkingService service, ParkingMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @GetMapping
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = service.findAll();
        return ResponseEntity.ok(mapper.toParkingDTOList(parkingList));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        Parking parking = service.findById(id);
        return ResponseEntity.ok(mapper.toParkingDTO(parking));
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO parkingCreateDTO) {
        var parkingCreate = mapper.toParkingCreate(parkingCreateDTO);
        var parking = service.create(parkingCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toParkingDTO(parking));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto) {
        var parkingCreate = mapper.toParkingCreate(dto);
        var parking = service.update(id, parkingCreate);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toParkingDTO(parking));
    }

    @PutMapping("exit/{id}")
    public ResponseEntity<ParkingDTO> exit(@PathVariable String id) {
        Parking parking = service.exit(id);
        return ResponseEntity.ok(mapper.toParkingDTO(parking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
