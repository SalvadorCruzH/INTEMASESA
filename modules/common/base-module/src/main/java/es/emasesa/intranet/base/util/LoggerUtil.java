package es.emasesa.intranet.base.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;


public class LoggerUtil {

    private LoggerUtil(){}

    public static Log getLog(Class<?> c){
        return LogFactoryUtil.getLog(c);
    };

    // DEBUG
    public static void debug(final Log _log, final String message) {
        if (_log.isDebugEnabled()) {
            _log.debug(message);
        }
    }

    public static void debug(final Log _log, final Throwable t) {
        if (_log.isDebugEnabled()) {
            _log.debug(t);
        }
    }

    public static void debug(final Log _log, final String message, final Throwable t) {
        if (_log.isDebugEnabled()) {
            _log.debug(message, t);
        }
    }

    // WARNING
    public static void warn(final Log _log, final String message) {
        if (_log.isWarnEnabled()) {
            _log.warn(message);
        }
    }

    public static void warn(final Log _log, final Throwable t) {
        if (_log.isWarnEnabled()) {
            _log.warn(t);
        }
    }

    public static void warn(final Log _log, final String message, final Throwable t) {
        if (_log.isWarnEnabled()) {
            _log.warn(message, t);
        }
    }

    // ERROR
    public static void error(final Log _log, final String message) {
        if (_log.isErrorEnabled()) {
            _log.error(message);
        }
    }

    public static void error(final Log _log, final Throwable t) {
        if (_log.isErrorEnabled()) {
            _log.error(t);
        }
    }

    public static void error(final Log _log, final String message, final Throwable t) {
        if (_log.isErrorEnabled()) {
            _log.error(message, t);
        }
    }

    // WARNING
    public static void info(final Log _log, final String message) {
        if (_log.isInfoEnabled()) {
            _log.info(message);
        }
    }

    public static void info(final Log _log, final Throwable t) {
        if (_log.isInfoEnabled()) {
            _log.info(t);
        }
    }

    public static void info(final Log _log, final String message, final Throwable t) {
        if (_log.isInfoEnabled()) {
            _log.info(message, t);
        }
    }

}
