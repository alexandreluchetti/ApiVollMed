package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.AppointmentCancelData;

public interface AppointmentCancelValidator {
    void validate(AppointmentCancelData data);
}
