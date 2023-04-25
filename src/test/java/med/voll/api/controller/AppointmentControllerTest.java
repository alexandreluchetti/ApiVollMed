package med.voll.api.controller;

import med.voll.api.domain.appointment.AppointmentDetailData;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import med.voll.api.domain.appointment.AppointmentSchedule;
import med.voll.api.domain.doctor.Speciality;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppointmentRegistrationData> dataJson;

    @Autowired
    private JacksonTester<AppointmentDetailData> detailJson;

    @MockBean
    private AppointmentSchedule appointmentSchedule;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void setAppointment1() throws Exception {
        var response = mvc.perform(post("/appointments")).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void setAppointment2() throws Exception {
        var dateTime = LocalDateTime.now().plusHours(1);
        var appointmentObject = new AppointmentDetailData(null, 1L, 1L, dateTime);

        when(appointmentSchedule.setAppointment(any())).thenReturn(appointmentObject);

        var response = mvc.perform(
                post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson.write(
                                new AppointmentRegistrationData(1L, 1L, dateTime, Speciality.GINECOLOGIA)
                        ).getJson()
                )
        ).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJson = detailJson.write(appointmentObject).getJson();
        Assertions.assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

}