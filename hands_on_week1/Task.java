public class Task {
    private int taskId;
    private String taskName;
    private String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

class Node {
    Task task;
    Node next;

    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

class TaskLinkedList {
    private Node head;

    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public Task searchTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId() == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    public void deleteTask(int taskId) {
        if (head == null) {
            return;
        }

        if (head.task.getTaskId() == taskId) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.task.getTaskId() == taskId) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }
}

 class Main {
    public static void main(String[] args) {
        TaskLinkedList taskLinkedList = new TaskLinkedList();

        Task task1 = new Task(1, "Task 1", "To-Do");
        Task task2 = new Task(2, "Task 2", "In Progress");
        Task task3 = new Task(3, "Task 3", "Done");

        taskLinkedList.addTask(task1);
        taskLinkedList.addTask(task2);
        taskLinkedList.addTask(task3);

        System.out.println("Traversing tasks:");
        taskLinkedList.traverseTasks();

        System.out.println("\nSearching for task with ID 2:");
        Task searchedTask = taskLinkedList.searchTask(2);
        if (searchedTask != null) {
            System.out.println(searchedTask);
        } else {
            System.out.println("Task not found");
        }

        System.out.println("\nDeleting task with ID 2:");
        taskLinkedList.deleteTask(2);

        System.out.println("\nTraversing tasks after deletion:");
        taskLinkedList.traverseTasks();
    }
}