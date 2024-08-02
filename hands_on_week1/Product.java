public class Product {
    private int productId;
    private String productName;
    private String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

 class ECommerceSearch {
    public static Product linearSearch(Product[] products, String productName) {
        for (Product product : products) {
            if (product.getProductName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] products, String productName) {
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (products[mid].getProductName().equals(productName)) {
                return products[mid];
            } else if (products[mid].getProductName().compareTo(productName) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Product[] products = new Product[] {
                new Product(1, "Apple iPhone", "Electronics"),
                new Product(2, "Samsung TV", "Electronics"),
                new Product(3, "Nike Shoes", "Fashion"),
                new Product(4, "Adidas Jacket", "Fashion"),
                new Product(5, "Sony Headphones", "Electronics")
        };

        // Linear Search
        Product product = linearSearch(products, "Samsung TV");
        if (product != null) {
            System.out.println("Linear Search: Found product - " + product);
        } else {
            System.out.println("Linear Search: Product not found.");
        }

        // Binary Search
        Product[] sortedProducts = products.clone();
        java.util.Arrays.sort(sortedProducts, (p1, p2) -> p1.getProductName().compareTo(p2.getProductName()));

        product = binarySearch(sortedProducts, "Samsung TV");
        if (product != null) {
            System.out.println("Binary Search: Found product - " + product);
        } else {
            System.out.println("Binary Search: Product not found.");
        }
    }
}