package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentCancelData(
        @NotNull
        Long id,

        @NotNull
        ReasonCancel reason
) {
}
