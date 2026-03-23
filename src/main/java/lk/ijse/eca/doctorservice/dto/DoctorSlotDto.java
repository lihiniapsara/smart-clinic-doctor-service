package lk.ijse.eca.doctorservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorSlotDto {

    public interface OnCreate {}

    @NotBlank(groups = OnCreate.class, message = "Doctor Slot ID is required")
    @Pattern(groups = OnCreate.class, regexp = "^[A-Z0-9-]+$", message = "Doctor Slot ID must contain uppercase letters, numbers, or '-' only")
    private String doctorSlotId;

    @NotBlank(message = "Doctor details cannot be blank")
    private String description;
}


