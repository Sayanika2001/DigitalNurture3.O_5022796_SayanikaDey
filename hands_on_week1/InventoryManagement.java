import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InventoryManagement {
    private List<Product> inventory;

    public InventoryManagement() {
        inventory = new ArrayList<>();
    }

    public void addProduct(Product product) {
        inventory.add(product);
    }

    public void updateProduct(Product product) {
        int index = inventory.indexOf(product);
        if (index != -1) {
            inventory.set(index, product);
        }
    }

    public void deleteProduct(Product product) {
        inventory.remove(product);
    }

    public Product findProductById(int productId) {
        for (Product product : inventory) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    public void printInventory() {
        for (Product product : inventory) {
            System.out.println(product);
        }
    }

    public static class Product {
        private int productId;
        private String productName;
        private int quantity;
        private double price;

        public Product(int productId, String productName, int quantity, double price) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        public int getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "productId=" + productId +
                    ", productName='" + productName + '\'' +
                    ", quantity=" + quantity +
                    ", price=" + price +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Product)) return false;
            Product product = (Product) o;
            return productId == product.productId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(productId);
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    public static void main(String[] args) {
        InventoryManagement inventory = new InventoryManagement();

        inventory.addProduct(new Product(1, "Apple iPhone", 10, 999.99));
        inventory.addProduct(new Product(2, "Samsung TV", 5, 799.99));
        inventory.addProduct(new Product(3, "Nike Shoes", 20, 129.99));

        inventory.printInventory();

        Product productToUpdate = inventory.findProductById(1);
        productToUpdate.setQuantity(15);
        inventory.updateProduct(productToUpdate);

        inventory.printInventory();

        Product productToDelete = inventory.findProductById(2);
        inventory.deleteProduct(productToDelete);

        inventory.printInventory();
    }
}