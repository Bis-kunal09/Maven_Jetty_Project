<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee CRUD</title>
</head>
<body>
    <h1>Employee List</h1>
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Job</th>
            <th>Salary</th>
            <th>Joining Date</th>
            <th>Dept number</th>
            <!-- Add other columns as per your table schema -->
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.name}</td>
                <td>${employee.job}</td>
                <td>${employee.salary}</td>
                
				<td>${employee.joining_date}</td>
                
                <td>${employee.deptno}</td>
                <!-- Add other columns as per your table schema -->
            </tr>
        </c:forEach>
    </table>

    <h2>Add Employee</h2>
    <form action="/employees" method="post">
        <input type="hidden" name="action" value="add">
        Name: <input type="text" name="name" required><br>
        Job: <input type="text" name="job" required><br>
        Salary: <input type="number" name="salary" required><br>
        Date of Joining: <input type="date" name="joining_date" required><br>
        Dept Number: <input type="NUMBER" name="deptno" required><br>
        <!-- Add other input fields as per your table schema -->
        <input type="submit" value="Add Employee">
    </form>
    <br>
    <h2>Delete Employee</h2>
    <form action="/employees" method="post">
        <input type="hidden" name="action" value="delete">
        ID:<input type="number" name="id" required><br>
       
        <!-- Add other input fields as per your table schema -->
        <input type="submit" value="Delete Employee">
    </form>
    <br>
     <h2>Update Employee</h2>
    <form action="/employees" method="post">
        <input type="hidden" name="action" value="update">
        ID:<input type="number" name="id" required><br>
		New Name: <input type="text" name="name" required><br>
        New Job: <input type="text" name="job" required><br>
        New Salary: <input type="number" name="salary" required><br>
        New Date of Joining: <input type="Date" name="joining_date" required><br>
        New Dept Number: <input type="number" name="deptno" required><br>       
        <!-- Add other input fields as per your table schema -->
        <input type="submit" value="Update Employee">
    </form>
</body>
</html>