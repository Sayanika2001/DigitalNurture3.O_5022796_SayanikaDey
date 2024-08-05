public class DecoratorPatternExample {

    
    interface Notifier {
        void send(String message);
    }

    
    static class EmailNotifier implements Notifier {
        @Override
        public void send(String message) {
            System.out.println("Sending Email: " + message);
        }
    }

    
    static abstract class NotifierDecorator implements Notifier {
        protected Notifier wrappedNotifier;

        public NotifierDecorator(Notifier notifier) {
            this.wrappedNotifier = notifier;
        }

        @Override
        public void send(String message) {
            wrappedNotifier.send(message);
        }
    }

    static class SMSNotifierDecorator extends NotifierDecorator {
        public SMSNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            super.send(message);
            sendSMS(message);
        }

        private void sendSMS(String message) {
            System.out.println("Sending SMS: " + message);
        }
    }

    static class SlackNotifierDecorator extends NotifierDecorator {
        public SlackNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            super.send(message);
            sendSlackMessage(message);
        }

        private void sendSlackMessage(String message) {
            System.out.println("Sending Slack message: " + message);
        }
    }

    
    public static void main(String[] args) {
        
        Notifier emailNotifier = new EmailNotifier();

        
        Notifier emailAndSMSNotifier = new SMSNotifierDecorator(emailNotifier);

        
        Notifier emailAndSMSAndSlackNotifier = new SlackNotifierDecorator(emailAndSMSNotifier);

        
        emailAndSMSAndSlackNotifier.send("This is a test notification.");
    }
}
