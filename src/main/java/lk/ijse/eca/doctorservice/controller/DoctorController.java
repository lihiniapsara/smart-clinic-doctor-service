package lk.ijse.eca.doctorservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lk.ijse.eca.doctorservice.dto.DoctorSlotDto;
import lk.ijse.eca.doctorservice.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
@Slf4j
@Validated
public class DoctorController {

    private final DoctorService doctorService;

    private static final String DOCTOR_SLOT_ID_REGEXP = "^[A-Z0-9-]+$";

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DoctorSlotDto> createDoctorSlot(
            @Validated({Default.class, DoctorSlotDto.OnCreate.class})
            @RequestBody DoctorSlotDto dto) {
        log.info("POST doctor-slot - doctorSlotId: {}", dto.getDoctorSlotId());
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctorSlot(dto));
    }

    @GetMapping(value = "/{doctorSlotId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorSlotDto> getDoctorSlot(
            @PathVariable
            @Pattern(regexp = DOCTOR_SLOT_ID_REGEXP, message = "Doctor Slot ID must contain uppercase letters, numbers, or '-' only")
            String doctorSlotId) {
        log.info("GET doctor-slot/{}", doctorSlotId);
        return ResponseEntity.ok(doctorService.getDoctorSlot(doctorSlotId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DoctorSlotDto>> getAllDoctorSlots() {
        log.info("GET doctor-slots - retrieving all doctor slots");
        return ResponseEntity.ok(doctorService.getAllDoctorSlots());
    }

    @PutMapping(
            value = "/{doctorSlotId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DoctorSlotDto> updateDoctorSlot(
            @PathVariable
            @Pattern(regexp = DOCTOR_SLOT_ID_REGEXP, message = "Doctor Slot ID must contain uppercase letters, numbers, or '-' only")
            String doctorSlotId,
            @Valid @RequestBody DoctorSlotDto dto) {
        log.info("PUT doctor-slot/{}", doctorSlotId);
        return ResponseEntity.ok(doctorService.updateDoctorSlot(doctorSlotId, dto));
    }

    @DeleteMapping("/{doctorSlotId}")
    public ResponseEntity<Void> deleteDoctorSlot(
            @PathVariable
            @Pattern(regexp = DOCTOR_SLOT_ID_REGEXP, message = "Doctor Slot ID must contain uppercase letters, numbers, or '-' only")
            String doctorSlotId) {
        log.info("DELETE doctor-slot/{}", doctorSlotId);
        doctorService.deleteDoctorSlot(doctorSlotId);
        return ResponseEntity.noContent().build();
    }
}


