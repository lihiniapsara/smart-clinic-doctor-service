package lk.ijse.eca.doctorservice.mapper;

import lk.ijse.eca.doctorservice.dto.DoctorSlotDto;
import lk.ijse.eca.doctorservice.entity.DoctorSlot;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DoctorSlotMapper {

    DoctorSlotDto toDto(DoctorSlot doctorSlot);

    DoctorSlot toEntity(DoctorSlotDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "doctorSlotId", ignore = true)
    void updateEntity(DoctorSlotDto dto, @MappingTarget DoctorSlot doctorSlot);
}


