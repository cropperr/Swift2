<%-- 
    Document   : newSosialInsuranceRecord
    Created on : Jun 21, 2017, 2:38:24 PM
    Author     : Cropper
--%>

<%@page import="DataObjects.personaldetails.Citizen"%>
<%@page import="DataAccessLayer.interfaces.*"%>
<%@page import="DataAccessLayer.sql.*"%>
<%@page import="DataObjects.education.*"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    static final String dbmsConnString = "jdbc:mysql://localhost:3306/citizen_registrations";
    static final String userName = "root";
    static final String password = "kokolino123";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавяне на образование</title>
    </head>
    <body>
        <style>
            body {
                background-color: GhostWhite;
            }
            table {
                border-collapse: collapse;
                width: auto;
            }
            th, td {
                text-align: left;
                padding: 8px;
            }
            tr:nth-child(even){background-color: #f2f2f2}
        </style>

    </body>
</html>     
<%
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    int personId = Integer.parseInt(session.getAttribute("personId").toString());
    String institutionName = request.getParameter("institutionName");
    String degree = request.getParameter("educationDegree");
    String enrollmentDate = request.getParameter("enrollmentDate");
    String graduationDate = request.getParameter("graduationDate");
    String finalGrade = request.getParameter("finalGrade");
    Education education = null;
    EducationStorage addEducation = new MySqlEducationStorage(dbmsConnString, userName, password);
%>

<%
    Class.forName("com.mysql.jdbc.Driver");
    PersonStorage getPerson = new MySqlPersonStorage(dbmsConnString, userName, password);
    Citizen person = getPerson.getPresonById(personId);
%>
<%
    Class.forName("com.mysql.jdbc.Driver");
    if (degree != null && LocalDate.parse(graduationDate, formatter).isBefore(LocalDate.parse(enrollmentDate, formatter))) {%>
<h1><font color = red>Въведените дати са некоректни, датата на завършване, трябва да е след датата на започване!</font></h1>
    <%
        } else if (degree != null && LocalDate.parse(graduationDate, formatter).isAfter(LocalDate.parse(enrollmentDate, formatter))) {
            switch (degree) {
                case "Primary":
                    PrimaryEducation pEducation = new PrimaryEducation(institutionName, LocalDate.parse(enrollmentDate, formatter), LocalDate.parse(graduationDate, formatter));
                    education = pEducation;
                    person.addEducation(education);
                    break;
                case "Secondary":
                    SecondaryEducation sEducation = new SecondaryEducation(institutionName, LocalDate.parse(enrollmentDate, formatter), LocalDate.parse(graduationDate, formatter));
                    if (LocalDate.parse(graduationDate, formatter).isBefore(LocalDate.now())) {
                        ((GradedEducation) sEducation).gotGraduated(Float.parseFloat(finalGrade));
                    }
                    education = sEducation;
                    person.addEducation(education);
                    break;
                case "Bachelor":
                case "Master":
                case "Doctorate":
                    EducationDegree educationDegree = null;
                    if (degree.equals("Bachelor")) {
                        educationDegree = EducationDegree.Bachelor;
                    } else if (degree.equals("Master")) {
                        educationDegree = EducationDegree.Master;
                    } else {
                        educationDegree = EducationDegree.Doctorate;
                    }
                    HigherEducation hEducation = new HigherEducation(institutionName, LocalDate.parse(enrollmentDate, formatter), LocalDate.parse(graduationDate, formatter), educationDegree);
                    if (LocalDate.parse(graduationDate, formatter).isBefore(LocalDate.now())) {
                        ((GradedEducation) hEducation).gotGraduated(Float.parseFloat(finalGrade));
                    }
                    education = hEducation;
                    person.addEducation(education);
                    break;
            }
        }
        for (Education personEducation : person.getEducations()) {
            if (personEducation.equals(education)) {
                addEducation.insertEducationWebPage(personEducation, personId);
            }
        }
    %>

<a href="userInfo.jsp">Назад</a>  
<br></br>
<form action="newEducation.jsp" method="POST">
    <label>Учебно заведение:</label>  <input type="text" name="institutionName" value="" />           
    <label>Начална дата:</label> <input type="text" name="enrollmentDate" value="" />         
    <label>Крайна дата:</label> <input type="text" name="graduationDate" value="" />

    <label>Среден успех:</label> <input type="text" name="finalGrade" value="" />
    <label>Образоваелна степен:</label> <select name="educationDegree">
        <option>None</option>
        <option>Primary</option>
        <option>Secondary</option>
        <option>Bachelor</option>
        <option>Master</option>
        <option>Doctorate</option>
    </select>
    <br></br>
    <input type="submit" value="Добави образование" name="addEducation" />
</form>
<br></br>
<table border="2">
    <tbody>
        <tr>
            <td>Име</td>
            <td><%=person.getFirstName() + " " + person.getMiddleName() + " " + person.getLastName()%></td>
        </tr>                
        <tr>
            <td>Височина</td>
            <td><%=person.getHeight()%></td>
        </tr>
        <tr>
            <td>Пол</td>
            <td><%=person.getGender()%></td>
        </tr>
        <tr>
            <td>Дата на раждане</td>
            <td><%=person.getDateOfBirth()%></td>
        </tr>
        <tr>
            <td>Адрес</td>
            <td><%=person.getAddress().toString()%></td>
        </tr>
    </tbody>
</table> 
<br></br>
<form name="checkEducation" action="newEducation.jsp">
    <input type="submit" value="Преглед на образование" name="checkEducation" />
</form>
<br></br>
<% if (request.getParameter("checkEducation") != null) {%>
<table border="2">                   
    <tbody>
        <tr>            
            <%for (Education personEducation : person.getEducations()) {%>
            <td>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Вид образование</th>
                            <th><%=personEducation.getDegree().toString()%></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Учебно заведение</td>
                            <td><%=personEducation.getInstitutionName()%></td>
                        </tr>
                        <tr>
                            <td>Начална дата</td>
                            <td><%=personEducation.getEnrollmentDate()%></td>
                        </tr>
                        <tr>
                            <td>Дата на завършване</td>
                            <td><%=personEducation.getGraduationDate()%></td>
                        </tr>
                        <tr>
                            <td>Статус</td>
                            <td><%=personEducation.isGraduated()%></td>
                        </tr>

                        <%if (personEducation instanceof GradedEducation && personEducation.getGraduationDate().isBefore(LocalDate.now())) {%>
                    <td>Среден успех</td>
                    <td><%=((GradedEducation) personEducation).getFinalGrade()%></td>
                    <%} else {%>
                    <td>Среден успех</td>
                    <td>N/A</td>
                    <%}%>

    </tbody>
</table>

</td>                  

<% } %>
</tr>
</tbody>
</table>
<%}%>
<br></br>
