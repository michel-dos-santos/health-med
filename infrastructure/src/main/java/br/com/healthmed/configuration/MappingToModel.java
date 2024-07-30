package br.com.healthmed.configuration;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class MappingToModel {

    public static final Converter<String, LocalDateTime> toStringDate = new AbstractConverter<String, LocalDateTime>() {
        @Override
        protected LocalDateTime convert(String source) {
        if (Objects.nonNull(source) && !source.trim().isEmpty()) {
            DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            return LocalDateTime.parse(source, format);
        }
        return null;
        }
    };

    //public static final PropertyMap<DE, PARA> mapDE_PARA = new PropertyMap<DE, PARA>() {
    //    protected void configure() {
    //
    //    }
    //};

}
