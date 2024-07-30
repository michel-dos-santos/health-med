package br.com.healthmed.rest.mappers.inputs.dtos.schedule;

import br.com.healthmed.domain.schedule.Status;
import br.com.healthmed.rest.utils.annotations.EnumNamePattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusScheduleInputDTO {

    @EnumNamePattern(regexp = "ACEITA|RECUSADA", message = "Status da agenda inválido, deve ser ACEITA ou RECUSADA")
    private Status status;

}
