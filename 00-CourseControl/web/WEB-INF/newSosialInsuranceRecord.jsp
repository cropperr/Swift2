<%-- 
    Document   : newSosialInsuranceRecord
    Created on : Jun 21, 2017, 2:38:24 PM
    Author     : Cropper
--%>

<%@page import="java.util.Calendar"%>
<%@page import="DataAccessLayer.interfaces.*"%>
<%@page import="DataObjects.insurance.SocialInsuranceRecord"%>
<%@page import="DataObjects.personaldetails.*"%>
<%@page import="DataObjects.address.*"%>
<%@page import="DataAccessLayer.sql.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    static final String dbmsConnString = "jdbc:mysql://localhost:3306/citizen_registrations";
    static final String userName = "root";
    static final String password = "kokolino123";
%>
<%int personId = Integer.parseInt(session.getAttribute("personId").toString());%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавяне на социална осигуровка</title>
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
    Class.forName("com.mysql.jdbc.Driver");
    PersonStorage getPerson = new MySqlPersonStorage(dbmsConnString, userName, password);
    Citizen person = getPerson.getPresonById(personId);
%>
<% if (request.getParameter("addSocialInsurance") != null && request.getParameter("year") != null && request.getParameter("month") != null && request.getParameter("amount") != null) {
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        if (year < Calendar.getInstance().get(Calendar.YEAR) - 100) {%>
<br>
<h1>Въведената година не е коректна!</h1>
</br>
<%} else if (1 > month || month > 12) {%>
<br>
<h1>Въведения месец не е коректен!</h1>
</br>
<%} else if (amount <= 0) {%>
<br>
<h1>Въведента сума е невалидна!</h1>
</br>
<%} else {
            SocialInsuranceRecordStorage addSocialInsurance = new MySqlSocialInsuranceRecordStorage(dbmsConnString, userName, password);
            SocialInsuranceRecord insurance = new SocialInsuranceRecord(year, month, amount);
            person.addSocialInsuranceRecord(insurance);
            for (SocialInsuranceRecord personSocialInsurance : person.getSocialInsuranceRecords()) {
                if (personSocialInsurance.equals(insurance)) {
                    addSocialInsurance.insertSocialInsuranceFromWebPage(personSocialInsurance, personId);
                }
            }
        }
    }
%>
<a href="userInfo.jsp">Назад</a>  
<br></br>
<form name="addSocialInsurance" action="newSosialInsuranceRecord.jsp" method="POST">
    <label>Година:</label><input type="text" name="year" value="" /> <label>Месец:</label><input type="text" name="month" value="" /> <label>Внесена сума:</label><input type="text" name="amount" value="" />
    <input type="submit" value="Добави социална осигуровка" name="addSocialInsurance" />            
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
<br>
<form name="checkSocialInsurance" action="newSosialInsuranceRecord.jsp">
    <input type="submit" value="Преглед на внесени социални осигуровки" name="checkSocialInsurance" />
</form>
<br></br>
<% if (request.getParameter("checkSocialInsurance") != null) {%>
<table border="2">
    <thead>
        <tr>
            <th>година</th>
            <th>месец</th>
            <th>сума</th>
        </tr>
    </thead>
    <tbody>
        <%for (SocialInsuranceRecord socialInsurance : person.getSocialInsuranceRecords()) {%>
        <tr>                   
            <td><%=socialInsurance.getYear()%> </td>
            <td><%=socialInsurance.getMonth()%></td>
            <td><%=socialInsurance.getAmount()%></td>              
        </tr>
        <%}%>
    </tbody>
</table>
<%}%>
</br>