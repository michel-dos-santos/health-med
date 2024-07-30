package br.com.healthmed.port.repository;

import br.com.healthmed.model.LogCode;

public interface LogRepository {

    void error(Class<?> logClass, LogCode.LogCodeError logCode);
    void warn(Class<?> logClass, LogCode.LogCodeWarn logCode);
    void info(Class<?> logClass, LogCode.LogCodeInfo logCode);
    void debug(Class<?> logClass, LogCode.LogCodeDebug logCode);

}
