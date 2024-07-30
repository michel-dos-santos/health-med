package br.com.healthmed;

import br.com.healthmed.model.LogCode;
import br.com.healthmed.port.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Log4jRepository implements LogRepository {

    @Override
    public void debug(Class<?> logClass, LogCode.LogCodeDebug logCode) {
        Logger logger = LoggerFactory.getLogger(logClass);
        String msg = String.format("TAG: %s - MESSAGE: %s", logCode.name(), logCode.getDescription());
        logger.debug(msg);
        System.out.print(msg);
    }

    @Override
    public void info(Class<?> logClass, LogCode.LogCodeInfo logCode) {
        Logger logger = LoggerFactory.getLogger(logClass);
        String msg = String.format("TAG: %s - MESSAGE: %s", logCode.name(), logCode.getDescription());
        logger.info(msg);
        System.out.print(msg);
    }

    @Override
    public void warn(Class<?> logClass, LogCode.LogCodeWarn logCode) {
        Logger logger = LoggerFactory.getLogger(logClass);
        logger.warn(String.format("TAG: %s - MESSAGE: %s", logCode.name(), logCode.getDescription()));
    }

    @Override
    public void error(Class<?> logClass, LogCode.LogCodeError logCode) {
        Logger logger = LoggerFactory.getLogger(logClass);
        logger.error(String.format("TAG: %s - MESSAGE: %s", logCode.name(), logCode.getDescription()));
    }
}