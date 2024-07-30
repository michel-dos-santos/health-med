package br.com.healthmed.rest.mappers.outputs.dtos.schedule;

import br.com.healthmed.domain.schedule.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SchedulingOutputDTO {

    private UUID id;
    private LocalDateTime dateTime;
    private String link;
    private Status status;
    private PatientDataOutputDTO patientData;
    private DoctorDataOutputDTO doctorData;

}
