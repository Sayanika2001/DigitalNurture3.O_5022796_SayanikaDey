public class ProxyPatternExample {

    // Step 2: Define Subject Interface
    interface Image {
        void display();
    }

    // Step 3: Implement Real Subject Class
    static class RealImage implements Image {
        private String imageUrl;

        public RealImage(String imageUrl) {
            this.imageUrl = imageUrl;
            loadImageFromServer();
        }

        private void loadImageFromServer() {
            System.out.println("Loading image from server: " + imageUrl);
        }

        @Override
        public void display() {
            System.out.println("Displaying image: " + imageUrl);
        }
    }

    // Step 4: Implement Proxy Class
    static class ProxyImage implements Image {
        private String imageUrl;
        private RealImage realImage;

        public ProxyImage(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(imageUrl);
            }
            realImage.display();
        }
    }

    // Step 5: Test the Proxy Implementation
    public static void main(String[] args) {
        Image image1 = new ProxyImage("http://example.com/image1.jpg");
        Image image2 = new ProxyImage("http://example.com/image2.jpg");

        // The images will be loaded from the server only when display() is called
        System.out.println("Image 1 display call:");
        image1.display(); // First time loading from server and then displaying
        System.out.println();

        System.out.println("Image 1 display call again:");
        image1.display(); // This time just displaying the already loaded image
        System.out.println();

        System.out.println("Image 2 display call:");
        image2.display(); // First time loading from server and then displaying
        System.out.println();
    }
}
