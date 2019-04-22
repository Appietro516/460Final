<%@ page language="java" contentType="text/html" %>

<html>

    <head><title>SonoranCellular</title></head>
    <body link=#f0f0ff alink vlink=#f0f0ff>
        <p>
            <center>
            <font size=7 face="Arial, Helvetica, sans-serif" color="#000066">
            <b>SonoranCellular</b><br>
            </font>
            <hr>
            <br>

            <b>Accounts with the same plan:</b>
            <br>
        <%
                String name1 = "Jean-Pierre";
                String name2 = "Rick Snodgrass";
		String plan1 = "normal";
                String name3 = "Bill Gates";
                String name4 = "Steve Jobs";
		String plan2 = "unlimited";
        %>

	    <table border="1">
	      <tr> 
	      <td> <b> Card Task Name </b> </td>
	      <td> <b> User Name </b> </td>
        <%
	      // Card 1
	      out.println("<tr>");
	      out.println("<td> " + plan1 + "</td>");
	      out.println("<td> " + name1 + " </td>");

	      out.println("<tr>");
	      out.println("<td> </td>");
	      out.println("<td> " + name2 + " </td>");

	      // Card 2
	      out.println("<tr>");
	      out.println("<td> " + plan2 + "</td>");
	      out.println("<td> " + name3 + " </td>");

	      out.println("<tr>");
	      out.println("<td> </td>");
	      out.println("<td> " + name4 + " </td>");
        %>
	      </table>	

                <hr>
                <br><br>

                <table>
                <tr>
                <td>
                <form name="mainmenu" action=../LoginServlet method=get>
                <input type=submit name="MainMenu" value="Main Menu">
                </form>
                </td>
                </tr>
                <tr>
                <td>
                <form name="logout" action=../index.html>
                <input type=submit name="logoutSonoranCellular" value="Logout">
                </form>
                </td>
                </tr>
                </table>


            </center>
        </p>
    </body>
</html>


