package com.bridgelabz.streams.objectstream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Class to store details about Employee
class Employee implements Serializable {
    // Attributes
    private static final long serialVersionUID = 1L;
    private final int id;
    private final String name;
    private final String department;
    private final double salary;

    // Constructor
    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // method to display employee details
    public void displayDetails() {
        System.out.println("Employee{" + "id=" + id + ", name='" + name + '\'' + ", department='" + department + '\'' + ", salary=" + salary + '}');
    }
}

// class to store employee object in a file then read from the file
public class SerializeDeserializeEmployee {
    private static final String fileName = "src/main/java/com/bridgelabz/streams/objectstream/employees.dat";

    public static void main(String[] args) {
        // Create a list to store employees
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1, "Amit", "Finance", 45000));
        employees.add(new Employee(2, "Bhanu", "Cyber", 70000));
        employees.add(new Employee(3, "Chirag", "HR", 60000));

        serializeEmployees(employees);
        List<Employee> deserializedEmployees = deserializeEmployees();

        // Display the result
        System.out.println("Deserialized Employees:");
        deserializedEmployees.forEach(Employee::displayDetails);
    }

    // method to serialize employee objects
    private static void serializeEmployees(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(employees);
            System.out.println("Employees serialized successfully.");
        } catch (IOException e) {
            System.out.println("Error serializing employees: " + e.getMessage());
        }
    }

    // method to deserialize objects
    private static List<Employee> deserializeEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing employees: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
// Sample Output ->
// Employees serialized successfully.
// Deserialized Employees:
// Employee{id=1, name='Amit', department='Finance', salary=45000.0}
// Employee{id=2, name='Bhanu', department='Cyber', salary=70000.0}
// Employee{id=3, name='Chirag', department='HR', salary=60000.0}