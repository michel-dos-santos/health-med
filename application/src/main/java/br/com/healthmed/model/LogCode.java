package br.com.healthmed.model;

import java.util.Arrays;

public abstract class LogCode {
    public static LogCodeInfo INFO;
    public static LogCodeDebug DEBUG;
    public static LogCodeWarn WARN;
    public static LogCodeError ERROR;

    public enum LogCodeInfo {
        _0001("Iniciando o processo de criação do paciente no sistema"),
        _0002("Validando a consistência dos dados do paciente"),
        _0003("Paciente criado com sucesso"),
        _0004("Identificando o paciente com base no CPF"),
        _0005("Identificando o paciente com base no ID"),
        _0006("Paciente identificado com sucesso"),
        _0007("Paciente identificado com sucesso"),
        _0008("Paciente identificado com sucesso"),
        _0009("Paciente identificado com sucesso"),
        _0010("Paciente identificado com sucesso"),
        _0011("Paciente identificado com sucesso"),
        _0012("Paciente identificado com sucesso"),
        _0013("Paciente identificado com sucesso"),
        _0014("Paciente identificado com sucesso"),
        _0015("Paciente identificado com sucesso"),
        _0016("Paciente identificado com sucesso"),
        _0017("Paciente identificado com sucesso"),
        _0018("Paciente identificado com sucesso"),
        _0019("Paciente identificado com sucesso"),
        _0020("Paciente identificado com sucesso"),
        _0021("Paciente identificado com sucesso"),
        _0022("Paciente identificado com sucesso"),
        _0023("Paciente identificado com sucesso"),
        _0024("Paciente identificado com sucesso"),
        _0025("Paciente identificado com sucesso"),
        ;

        private String description;

        LogCodeInfo(String description) {
            this.description = description;
        }

        public static LogCodeInfo get(String type) {
            return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
        }

        public String getDescription() {
            return this.description;
        }

    }

    public enum LogCodeDebug {
        _0001("Template");

        private String description;

        LogCodeDebug(String description) {
            this.description = description;
        }

        public static LogCodeDebug get(String type) {
            return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
        }

        public String getDescription() {
            return this.description;
        }

    }

    public enum LogCodeWarn {
        _0001("Template");

        private String description;

        LogCodeWarn(String description) {
            this.description = description;
        }

        public static LogCodeWarn get(String type) {
            return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
        }

        public String getDescription() {
            return this.description;
        }

    }

    public enum LogCodeError {
        _0001("Template");

        private String description;

        LogCodeError(String description) {
            this.description = description;
        }

        public static LogCodeError get(String type) {
            return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
        }

        public String getDescription() {
            return this.description;
        }

    }
}
