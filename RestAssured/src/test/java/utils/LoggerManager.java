package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerManager {
	
	private static final Logger logger = LogManager.getLogger("FrameworkLogger");

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logRequest(String message) {
        logger.info("🟦 REQUEST: " + message);
    }

    public static void logResponse(String message) {
        logger.info("🟩 RESPONSE: " + message);
    }

    public static void logDebug(String message) {
        logger.debug(message);
    }

    public static void logError(String message, Throwable t) {
        logger.error("❌ ERROR: " + message, t);
    }
    
    public static void logWarn(String message) {
        logger.info(message);
    }

}
