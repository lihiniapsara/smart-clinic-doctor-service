package lk.ijse.eca.doctorservice.service;

import lk.ijse.eca.doctorservice.dto.DoctorSlotDto;

import java.util.List;

public interface DoctorService {

    DoctorSlotDto createDoctorSlot(DoctorSlotDto dto);

    DoctorSlotDto getDoctorSlot(String doctorSlotId);

    List<DoctorSlotDto> getAllDoctorSlots();

    DoctorSlotDto updateDoctorSlot(String doctorSlotId, DoctorSlotDto dto);

    void deleteDoctorSlot(String doctorSlotId);
}


