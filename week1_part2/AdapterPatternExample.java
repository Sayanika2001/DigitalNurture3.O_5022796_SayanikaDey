public class AdapterPatternExample {

    
    interface PaymentProcessor {
        void processPayment(double amount);
    }

    
    static class PayPal {
        public void makePayment(double amount) {
            System.out.println("Processing payment of $" + amount + " through PayPal.");
        }
    }

    static class Stripe {
        public void pay(double amount) {
            System.out.println("Processing payment of $" + amount + " through Stripe.");
        }
    }

    static class Square {
        public void executePayment(double amount) {
            System.out.println("Processing payment of $" + amount + " through Square.");
        }
    }

    
    static class PayPalAdapter implements PaymentProcessor {
        private PayPal payPal;

        public PayPalAdapter(PayPal payPal) {
            this.payPal = payPal;
        }

        @Override
        public void processPayment(double amount) {
            payPal.makePayment(amount);
        }
    }

    static class StripeAdapter implements PaymentProcessor {
        private Stripe stripe;

        public StripeAdapter(Stripe stripe) {
            this.stripe = stripe;
        }

        @Override
        public void processPayment(double amount) {
            stripe.pay(amount);
        }
    }

    static class SquareAdapter implements PaymentProcessor {
        private Square square;

        public SquareAdapter(Square square) {
            this.square = square;
        }

        @Override
        public void processPayment(double amount) {
            square.executePayment(amount);
        }
    }

    
    public static void main(String[] args) {
        
        PayPal payPal = new PayPal();
        Stripe stripe = new Stripe();
        Square square = new Square();

        
        PaymentProcessor payPalProcessor = new PayPalAdapter(payPal);
        PaymentProcessor stripeProcessor = new StripeAdapter(stripe);
        PaymentProcessor squareProcessor = new SquareAdapter(square);

        
        payPalProcessor.processPayment(100.0);
        stripeProcessor.processPayment(200.0);
        squareProcessor.processPayment(300.0);
    }
}
