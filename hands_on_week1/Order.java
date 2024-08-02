
public class Order {
    private int  orderId;
    private String customerName;
    private double totalPrice;

    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

class SortingCustomerOrders {
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() < orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        Order pivot = orders[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() > pivot.getTotalPrice()) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    public static void printOrders(Order[] orders) {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public static void main(String[] args) {
        Order[] orders = new Order[] {
                new Order(1, "John Doe", 500.0),
                new Order(2, "Jane Smith", 200.0),
                new Order(3, "Bob Johnson", 800.0),
                new Order(4, "Alice Brown", 300.0),
                new Order(5, "Mike Davis", 400.0)
        };

        System.out.println("Original Orders:");
        printOrders(orders);

        System.out.println("\nSorting using Bubble Sort:");
        bubbleSort(orders);
        printOrders(orders);

        Order[] orders2 = new Order[] {
                new Order(1, "John Doe", 500.0),
                new Order(2, "Jane Smith", 200.0),
                new Order(3, "Bob Johnson", 800.0),
                new Order(4, "Alice Brown", 300.0),
                new Order(5, "Mike Davis", 400.0)
        };

        System.out.println("\nSorting using Quick Sort:");
        quickSort(orders2, 0, orders2.length - 1);
        printOrders(orders2);
    }
}