package container.controller;

import container.model.Employee;

import container.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.*;

@SuppressWarnings("serial")
public class EmployeeController extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        super.init();
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees = employeeService.getAllEmployees();
        for(Employee x:employees) {
        	System.out.println(x.getName());
        }
        req.setAttribute("employees", employees);
        req.getRequestDispatcher("/WEB-INF/views/employee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addEmployee(req);
                    break;
                case "update":
                    updateEmployee(req);
                    break;
                case "delete":
                    deleteEmployee(req);
                    break;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/employees");
    }

    private void addEmployee(HttpServletRequest req) {
        String name = req.getParameter("name");
        String job = req.getParameter("job");
        String joining_date = req.getParameter("joining_date");
        Float salary = Float.parseFloat(req.getParameter("salary"));
        int deptno = 0; // Default value if deptno is null

        String deptnoParam = req.getParameter("deptno");
        if (deptnoParam != null) {
            deptno = Integer.parseInt(deptnoParam);
        }
        
        // Get other parameters as per your form inputs

        Employee employee = new Employee();
        employee.setName(name);
        employee.setJob(job);
        employee.setJoining_date(joining_date);
        employee.setSalary(salary);
        employee.setDeptno(deptno);
        
        // Set other fields as per your table schema

        employeeService.addEmployee(employee);
    }

    private void updateEmployee(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String job = req.getParameter("job");
        String joining_date = req.getParameter("joining_date");
        Float salary = Float.parseFloat(req.getParameter("salary"));
        int deptno = 0; // Default value if deptno is null

        String deptnoParam = req.getParameter("deptno");
        if (deptnoParam != null) {
            deptno = Integer.parseInt(deptnoParam);
        }
        // Get other parameters as per your form inputs

        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setJob(job);
        employee.setJoining_date(joining_date);
        employee.setSalary(salary);
        employee.setDeptno(deptno);
        
        // Set other fields as per your table schema

        employeeService.updateEmployee(employee);
    }

    private void deleteEmployee(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        employeeService.deleteEmployee(id);
    }
}

