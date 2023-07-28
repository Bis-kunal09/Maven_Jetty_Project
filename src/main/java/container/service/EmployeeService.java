package container.service;
import container.model.Employee;
import container.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private static final String TABLE_NAME = "employee";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_JOB = "job";
    private static final String COLUMN_JOINING_DATE = "joining_date";
    private static final String COLUMN_SALARY = "salary";
    private static final String COLUMN_DEPT = "deptno";
    
    // Add other column names as per your table schema

    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM " + TABLE_NAME;
    
    private static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=?";
    private static final String INSERT_EMPLOYEE = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME + ", " + COLUMN_JOB +", " + COLUMN_JOINING_DATE +", " + COLUMN_SALARY +", " + COLUMN_DEPT + ") VALUES (?, ?,?,?,?)";
    private static final String UPDATE_EMPLOYEE = "UPDATE " + TABLE_NAME + " SET " + COLUMN_NAME + "=?, " + COLUMN_JOB + "=?, " + COLUMN_JOINING_DATE+ "=? ," + COLUMN_SALARY+ "=? ," + COLUMN_DEPT+ "=? WHERE " + COLUMN_ID + "=?";
    private static final String DELETE_EMPLOYEE = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=?";

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_EMPLOYEES)) {
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt(COLUMN_ID));
                employee.setName(resultSet.getString(COLUMN_NAME));
                employee.setJob(resultSet.getString(COLUMN_JOB));
                employee.setJoining_date(resultSet.getString(COLUMN_JOINING_DATE));
                employee.setSalary(resultSet.getFloat(COLUMN_SALARY));
                employee.setDeptno(resultSet.getInt(COLUMN_DEPT));
                // Set other fields as per your table schema
                
                employees.add(employee);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Employee getEmployeeById(int id) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                	Employee employee = new Employee();
                    employee.setId(resultSet.getInt(COLUMN_ID));
                    employee.setName(resultSet.getString(COLUMN_NAME));
                    employee.setJob(resultSet.getString(COLUMN_JOB));
                    employee.setJoining_date(resultSet.getString(COLUMN_JOINING_DATE));
                    employee.setSalary(resultSet.getFloat(COLUMN_SALARY));
                    employee.setDeptno(resultSet.getInt(COLUMN_DEPT));
                    // Set other fields as per your table schema

                    return employee;
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addEmployee(Employee employee) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_EMPLOYEE)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getJob());
            statement.setString(3, employee.getJoining_date());
            statement.setFloat(4, employee.getSalary());
            statement.setInt(5, employee.getDeptno());
            // Set other fields as per your table schema

            statement.executeUpdate();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE)) {
        	statement.setString(1, employee.getName());
            statement.setString(2, employee.getJob());
            statement.setString(3, employee.getJoining_date());
            statement.setFloat(4, employee.getSalary());
            statement.setInt(5, employee.getDeptno());
            // Set other fields as per your table schema
            statement.setInt(6, employee.getId());

            statement.executeUpdate();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
