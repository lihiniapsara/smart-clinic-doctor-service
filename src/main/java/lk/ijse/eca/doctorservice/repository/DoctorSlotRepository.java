package lk.ijse.eca.doctorservice.repository;

import lk.ijse.eca.doctorservice.entity.DoctorSlot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorSlotRepository extends MongoRepository<DoctorSlot, String> {
}


