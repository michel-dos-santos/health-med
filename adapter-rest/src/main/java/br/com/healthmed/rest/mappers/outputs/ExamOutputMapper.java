package br.com.healthmed.rest.mappers.outputs;

import br.com.healthmed.model.medicalrecord.Exam;
import br.com.healthmed.rest.mappers.outputs.dtos.medicalrecord.ExamOutputDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamOutputMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ExamOutputDTO mapExamOutputDTOFromExam(Exam exam) {
        return modelMapper.map(exam, ExamOutputDTO.class);
    }

    public List<ExamOutputDTO> mapListExamOutputDTOFromListExam(List<Exam> exams) {
        return modelMapper.map(exams, new TypeToken<List<ExamOutputDTO>>() {}.getType());
    }
}
