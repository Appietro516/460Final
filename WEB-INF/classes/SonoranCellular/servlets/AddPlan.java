package SonoranCellular.servlets;
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import SonoranCellular.servlets.*;


public class AddPlan extends HttpServlet
{
   public AddPlan()
   {
      super();
   }


   public void drawUpdateMessage(HttpServletRequest req, PrintWriter out)
   {
      String plan_name  = "CS460";
      int numb = 1234567;
      int imei = 460;
      String model = "iPhone";

     
      out.println("<p><b>Plan Name:</b>  " + plan_name + "</p>");
      out.println("<p><b>Mobile Number:</b>  " + numb + "</p>");
      out.println("<p><b>IMEI:</b>  " + imei + "</p>");
      out.println("<p><b>Model:</b>  " + model + "</p>");

      out.println("<br>");

      out.println("<form name=\"MainMenu\" action=LoginServlet>");
      out.println("<input type=submit name=\"MainMenu\" value=\"MainMenu\">");
      out.println("</form>");

      out.println("<br>");

      out.println("<form name=\"logout\" action=index.html>");
      out.println("<input type=submit name=\"logoutSonoranCellular\" value=\"Logout\">");
      out.println("</form>");
   }


   public void drawHeader(HttpServletRequest req, PrintWriter out) {
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Plan Addition</title>");
      out.println("</head>");

      out.println("<body>");
      out.println("<p>");
      out.println("<center>");
      out.println("<font face=\"Arial, Helvetica, sans-serif\" >");
      out.println("<font color=\"#000066\">");
      out.println("<center>\n<font size=7><strong>SonoranCellular</strong></font></br>");
      out.println("</center>\n<font size=4><hr color=\"#000066\">");
      out.println("Add new plan </b><br></font>");
      out.println("</font>");

      out.println("<hr>");
   }


   public void drawFooter(HttpServletRequest req, PrintWriter out)
   {
      out.println("</center>");
      out.println("</p>");
      out.println("</body>");
      out.println("</html>");
   }


   public void drawAddPlanInformationMenu(HttpServletRequest req, PrintWriter out)
   {
      out.println("<form name=\"AddPlan\" action=AddPlan method=get>");
      out.println("<font size=3 face=\"Arial, Helvetica, sans-serif\" color=\"#000066\">");
      out.println("<p>");
      out.println("<b>Plan Name:</b>");
      out.println("<input type=text name=\"planname\">");
      out.println("<br>");
      out.println("</p>");

      out.println("<p>");
      out.println("<b>IMEI: </b>");
      out.println("<input type=text name=\"imei\">");
      out.println("<br>");
      out.println("</p>");

      out.println("<p>");
      out.println("<b>Mobile Number: </b>");
      out.println("<input type=text name=\"mobilenumber\">");
      out.println("<br>");
      out.println("</p>");

      out.println("<p>");
      out.println("<b>Model: </b>");
      out.println("<input type=text name=\"model\">");
      out.println("<br>");
      out.println("</p>");

      out.println("<table>");
      out.println("<tr>");
      out.println("<td>");
      out.println("<input type=submit name=\"Submit\" value=\"Insert\">&nbsp&nbsp");
      out.println("</td>");
      out.println("</tr>");

      out.println("</form>");

      out.println("<tr>");
      out.println("<td>");
      out.println("<form name=\"Cancel\" action=AddPlan method=get>");
      out.println("<input type=submit name=\"Cancel\" value=\"Cancel\">&nbsp&nbsp");
      out.println("</form>");
      out.println("</td>");
      out.println("</tr>");

      out.println("<tr>");
      out.println("<td>");
      out.println("<form name=\"MainMenu\" action=LoginServlet>");
      out.println("<input type=submit name=\"MainMenu\" value=\"Return to Main Menu\">");
      out.println("</form>");
      out.println("</td>");
      out.println("</tr>");

      out.println("<tr>");
      out.println("<td>");
      out.println("<form name=\"logout\" action=index.html>");
      out.println("<input type=submit name=\"logoutSonoranCellular\" value=\"Logout\">");
      out.println("</form>");
      out.println("</p>");
      out.println("</td>");
      out.println("</tr>");

      out.println("</table>");
      out.println("<br><br><br>");
   }


   public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
   {
      res.setContentType("text/html");
      PrintWriter out = res.getWriter();

      drawHeader(req,out);

      if(req.getParameter("Submit") == null)
      {
         drawAddPlanInformationMenu(req,out);
      }
      else
      {
         drawUpdateMessage(req,out);
      }

      drawFooter(req,out);
   }
}