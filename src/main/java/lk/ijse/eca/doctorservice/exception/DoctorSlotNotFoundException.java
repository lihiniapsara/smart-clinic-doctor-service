package lk.ijse.eca.doctorservice.exception;

public class DoctorSlotNotFoundException extends RuntimeException {

    public DoctorSlotNotFoundException(String doctorSlotId) {
        super("DoctorSlot not found with ID: " + doctorSlotId);
    }
}


