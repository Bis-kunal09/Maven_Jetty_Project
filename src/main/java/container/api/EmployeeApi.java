package container.api;


import container.model.Employee;
import container.service.EmployeeService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EmployeeApi extends HttpServlet {

    private EmployeeService employeeService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Employee> employees = employeeService.getAllEmployees();

        // Set the response content type to application/json
        resp.setContentType("application/json");

        // Write the JSON response to the client
        PrintWriter out = resp.getWriter();
        out.println("[");
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            out.println("{");
            out.println("\"id\": " + employee.getId() + ",");
            out.println("\"name\": \"" + employee.getName() + "\",");
            out.println("\"job\": \"" + employee.getJob() + "\",");
            out.println("\"salary\": " + employee.getSalary() + ",");
            out.println("\"joining_date\": \"" + employee.getJoining_date() + "\",");
            out.println("\"deptno\": " + employee.getDeptno());
            // Add other fields as per your table schema

            out.println("}");

            if (i < employees.size() - 1) {
                out.println(",");
            }
        }
        out.println("]");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Handle POST request for creating a new employee
        String name = req.getParameter("name");
        String job = req.getParameter("job");
        String joining_date = req.getParameter("joining_date");
        float salary = Float.parseFloat(req.getParameter("salary"));
        int deptno = Integer.parseInt(req.getParameter("deptno"));

        // Create a new Employee object
        Employee employee = new Employee();
        employee.setName(name);
        employee.setJob(job);
        employee.setJoining_date(joining_date);
        employee.setSalary(salary);
        employee.setDeptno(deptno);
        // Set other fields as per your table schema

        // Call the service method to add the employee
        employeeService.addEmployee(employee);

        // Set the response content type to application/json
        resp.setContentType("application/json");

        // Write the success message to the client
        PrintWriter out = resp.getWriter();
        out.println("{");
        out.println("\"message\": \"Employee added successfully\"");
        out.println("}");
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Handle PUT request for updating an existing employee
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String job = req.getParameter("job");
        String joining_date = req.getParameter("joining_date");
        float salary = Float.parseFloat(req.getParameter("salary"));
        int deptno = Integer.parseInt(req.getParameter("deptno"));

        // Create a new Employee object
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setJob(job);
        employee.setJoining_date(joining_date);
        employee.setSalary(salary);
        employee.setDeptno(deptno);
        // Set other fields as per your table schema

        // Call the service method to update the employee
        employeeService.updateEmployee(employee);

        // Set the response content type to application/json
        resp.setContentType("application/json");

        // Write the success message to the client
        PrintWriter out = resp.getWriter();
        out.println("{");
        out.println("\"message\": \"Employee updated successfully\"");
        out.println("}");
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Handle DELETE request for deleting an employee
        int id = Integer.parseInt(req.getParameter("id"));

        // Call the service method to delete the employee
        employeeService.deleteEmployee(id);

        // Set the response content type to application/json
        resp.setContentType("application/json");

        // Write the success message to the client
        PrintWriter out = resp.getWriter();
        out.println("{");
        out.println("\"message\": \"Employee deleted successfully\"");
        out.println("}");
        out.flush();
    }
}
