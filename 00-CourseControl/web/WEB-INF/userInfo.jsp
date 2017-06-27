<%-- 
    Document   : userInfo
    Created on : Jun 21, 2017, 2:40:29 PM
    Author     : Cropper
--%>

<%@page import="DataObjects.insurance.SocialInsuranceRecord"%>
<%@page import="insuranceCheck.SocialInsuranceAccessCheck"%>
<%@page import="java.time.LocalDate"%>
<%@page import="DataObjects.education.*"%>
<%@page import="DataObjects.personaldetails.*"%>
<%@page import="DataObjects.address.*"%>
<%@page import="DataAccessLayer.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    static final String dbmsConnString = "jdbc:mysql://localhost:3306/citizen_registrations";
    static final String userName = "root";
    static final String password = "kokolino123";
    int personId = 0;
%>


<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Начало</title>
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

<form action="userInfo.jsp">
    <label>Персонален номер: </label><input type="text" name="personId" value="" />
</form>
<%
    if (request.getParameter("personId") != null && !request.getParameter("personId").equals("") && Integer.parseInt(request.getParameter("personId")) > 0) {
        personId = Integer.parseInt(request.getParameter("personId"));
    } else if (session.getAttribute("personId") != null) {
        personId = Integer.parseInt(session.getAttribute("personId").toString());
    }
%>

<%
    Class.forName("com.mysql.jdbc.Driver");
    if (personId > 0) {
        session.setAttribute("personId", personId);
        session.setMaxInactiveInterval(120);
        MySqlPersonStorage getPerson = new MySqlPersonStorage(dbmsConnString, userName, password);
        Citizen person = getPerson.getPresonById(personId);
        SocialInsuranceAccessCheck socialInsuranceAccess = new SocialInsuranceAccessCheck();
%>
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
<table border="2">                   
    <tbody>
        <tr>            
            <%for (Education education : person.getEducations()) {%>
            <td>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Вид образование</th>
                            <th><%=education.getDegree().toString()%></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Учебно заведение</td>
                            <td><%=education.getInstitutionName()%></td>
                        </tr>
                        <tr>
                            <td>Начална дата</td>
                            <td><%=education.getEnrollmentDate()%></td>
                        </tr>
                        <tr>
                            <td>Дата на завършване</td>
                            <td><%=education.getGraduationDate()%></td>
                        </tr>
                        <tr>
                            <td>Статус</td>
                            <td><%=education.isGraduated()%></td>
                        </tr>

                        <%if (education instanceof GradedEducation && education.getGraduationDate().isBefore(LocalDate.now())) {%>
                    <td>Среден успех</td>
                    <td><%=((GradedEducation) education).getFinalGrade()%></td>
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
</br>


<table border="0">                   
    <tbody>
        <tr>                        
            <td><form name="addEducation" action="newEducation.jsp">
                    <input type="submit" value="Добави образование" name="addEducationButton" /> 
                </form>
            </td>                  
            <td><form name="addSocialInsurance" action="newSosialInsuranceRecord.jsp">
                    <input type="submit" value="Добави социална осигуровка" name="addSocialInsurance" />
                </form>
            </td> 

            <td>
            </td> 

        </tr>
    </tbody>
</table>
<%if (request.getParameter("checkSocialInsuranceAccess") != null) {%>
<%if (socialInsuranceAccess.checkSocialInsuranceInstallments(person) && socialInsuranceAccess.checkEducation(person)) {%>
<font color="blue"><h1><%=String.format("Има право на социално подпомагане на стойност %.2f лева", socialInsuranceAccess.getSocialInsuranceInstallmentSum(person))%></h1></font>
<table border="2">
    <thead>
        <tr>
            <th>година</th>
            <th>месец</th>
            <th>сума</th>
        </tr>
    </thead>
    <tbody>
        <%for (SocialInsuranceRecord socialInsurance : socialInsuranceAccess.getLastTwoYearInsurances(person)) {%>
        <tr>                   
            <td><%=socialInsurance.getYear()%> </td>
            <td><%=socialInsurance.getMonth()%></td>
            <td><%=socialInsurance.getAmount()%></td>              
        </tr>
        <%}%>
    </tbody>
</table>
<%} else {%>
<bold><h1><font color="red">Без право на социално подпомагане</font></h1></bold>
<table border="2">
    <thead>
        <tr>
            <th>година</th>
            <th>месец</th>
            <th>сума</th>
        </tr>
    </thead>
    <tbody>
        <%for (SocialInsuranceRecord socialInsurance : socialInsuranceAccess.getLastTwoYearInsurances(person)) {%>
        <tr>                   
            <td><%=socialInsurance.getYear()%> </td>
            <td><%=socialInsurance.getMonth()%></td>
            <td><%=socialInsurance.getAmount()%></td>              
        </tr>
        <%}%>
    </tbody>
</table>
<%}
        } else {%>
<form name="checkSocialInsuranceAccess" action="userInfo.jsp" method="POST">
    <input type="submit" value="Проверка за социално подпомагане" name="checkSocialInsuranceAccess" />
</form>
<%}%>
<%} else if ((request.getParameter("personId") == null) || (request.getParameter("personId").equals(""))) {
%>
<h1>Въведете Персонален номер</h1>        
<%
} else if (!request.getParameter("personId").equals("") && Integer.parseInt(request.getParameter("personId")) <= 0) {
%>
<h1><font color = red>Невалидно Персонален номер, опитайте отново</font></h1>
    <%}%>
