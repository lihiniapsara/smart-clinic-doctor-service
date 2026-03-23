package lk.ijse.eca.doctorservice.exception;

public class DuplicateDoctorSlotException extends RuntimeException {

    public DuplicateDoctorSlotException(String doctorSlotId) {
        super("DoctorSlot already exists with ID: " + doctorSlotId);
    }
}


