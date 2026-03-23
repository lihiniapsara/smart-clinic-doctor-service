package lk.ijse.eca.doctorservice.service.impl;

import lk.ijse.eca.doctorservice.dto.DoctorSlotDto;
import lk.ijse.eca.doctorservice.entity.DoctorSlot;
import lk.ijse.eca.doctorservice.exception.DuplicateDoctorSlotException;
import lk.ijse.eca.doctorservice.exception.DoctorSlotNotFoundException;
import lk.ijse.eca.doctorservice.mapper.DoctorSlotMapper;
import lk.ijse.eca.doctorservice.repository.DoctorSlotRepository;
import lk.ijse.eca.doctorservice.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorSlotRepository doctorSlotRepository;
    private final DoctorSlotMapper doctorSlotMapper;

    @Override
    @Transactional
    public DoctorSlotDto createDoctorSlot(DoctorSlotDto dto) {
        log.debug("Creating doctorSlot with ID: {}", dto.getDoctorSlotId());

        if (doctorSlotRepository.existsById(dto.getDoctorSlotId())) {
            log.warn("Duplicate doctorSlot ID detected: {}", dto.getDoctorSlotId());
            throw new DuplicateDoctorSlotException(dto.getDoctorSlotId());
        }

        DoctorSlot saved = doctorSlotRepository.save(doctorSlotMapper.toEntity(dto));
        log.info("DoctorSlot created successfully: {}", saved.getDoctorSlotId());
        return doctorSlotMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DoctorSlotDto getDoctorSlot(String doctorSlotId) {
        log.debug("Fetching doctorSlot with ID: {}", doctorSlotId);
        return doctorSlotRepository.findById(doctorSlotId)
                .map(doctorSlotMapper::toDto)
                .orElseThrow(() -> {
                    log.warn("DoctorSlot not found: {}", doctorSlotId);
                    return new DoctorSlotNotFoundException(doctorSlotId);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoctorSlotDto> getAllDoctorSlots() {
        log.debug("Fetching all doctor slots");
        List<DoctorSlotDto> doctorSlots = doctorSlotRepository.findAll()
                .stream()
                .map(doctorSlotMapper::toDto)
                .toList();
        log.debug("Fetched {} doctorSlot(s)", doctorSlots.size());
        return doctorSlots;
    }

    @Override
    @Transactional
    public DoctorSlotDto updateDoctorSlot(String doctorSlotId, DoctorSlotDto dto) {
        log.debug("Updating doctorSlot with ID: {}", doctorSlotId);

        DoctorSlot doctorSlot = doctorSlotRepository.findById(doctorSlotId)
                .orElseThrow(() -> {
                    log.warn("DoctorSlot not found for update: {}", doctorSlotId);
                    return new DoctorSlotNotFoundException(doctorSlotId);
                });

        doctorSlotMapper.updateEntity(dto, doctorSlot);
        DoctorSlot updated = doctorSlotRepository.save(doctorSlot);
        log.info("DoctorSlot updated successfully: {}", updated.getDoctorSlotId());
        return doctorSlotMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteDoctorSlot(String doctorSlotId) {
        log.debug("Deleting doctorSlot with ID: {}", doctorSlotId);

        DoctorSlot doctorSlot = doctorSlotRepository.findById(doctorSlotId)
                .orElseThrow(() -> {
                    log.warn("DoctorSlot not found for deletion: {}", doctorSlotId);
                    return new DoctorSlotNotFoundException(doctorSlotId);
                });

        doctorSlotRepository.delete(doctorSlot);
        log.info("DoctorSlot deleted successfully: {}", doctorSlotId);
    }
}


