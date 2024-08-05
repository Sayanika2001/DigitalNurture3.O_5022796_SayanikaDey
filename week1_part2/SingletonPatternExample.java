public class SingletonPatternExample {

    
    public static class Logger {
        
        private static Logger instance;

        
        private Logger() {
            
        }

        
        public static Logger getInstance() {
            if (instance == null) {
                synchronized (Logger.class) {
                    if (instance == null) { 
                        instance = new Logger();
                    }
                }
            }
            return instance;
        }

        // Example method for logging
        public void log(String message) {
            System.out.println("Log: " + message);
        }
    }

    public static void main(String[] args) {
        
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        
        logger1.log("This is the first log message.");
        logger2.log("This is the second log message.");

        
        if (logger1 == logger2) {
            System.out.println("Both logger1 and logger2 reference the same instance.");
        } else {
            System.out.println("Different instances exist.");
        }
    }
}
