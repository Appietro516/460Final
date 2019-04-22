package SonoranCellular.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import SonoranCellular.servlets.*;
import java.sql.*;

public class FindBill extends HttpServlet
{

   public FindBill()
   {
      super();
   }

   public void drawHeader(HttpServletRequest req, PrintWriter out)
   {
      out.println("<html>");
      out.println("<head><title>Find Bill</title></head>");

      out.println("<body>");
      out.println("<p>");
      out.println("<center>");
      out.println("<font face=\"Arial, Helvetica, sans-serif\">");
      out.println("<font color=\"#000066\">");
      out.println("<center>\n<font size=7><strong>SonoranCellular</strong></font></br>");
      out.println("</center>\n<font size=4><hr color=\"#000066\">");
      out.println("<b>Find Bill</b><br><br/></font>");
      out.println("</font>");
   }

   public void drawFooter(HttpServletRequest req, PrintWriter out)
   {
      out.println("<br>");
      out.println("<hr>");
      out.println("<br>");

      out.println("<p>");
      out.println("<form name=\"MainMenu\" action=LoginServlet method=get>");
      out.println("<input type=submit name=\"MainMenu\" value=\"Main Menu\">");
      out.println("</form>");
      out.println("</p>");

      out.println("<p>");
      out.println("<form name=\"logout\" action=index.html>");
      out.println("<input type=submit name=\"logoutSonoranCellular\" value=\"Logout\">");
      out.println("</form>");
      out.println("</p>");

      out.println("<br><br>");
      out.println("</center>");
      out.println("</p>");
      out.println("</body>");
      out.println("</html>");
   }


   public void drawGetBill(HttpServletRequest req, PrintWriter out)
   {

      System.out.println("CSC460: in drawGetBill___");

      out.println("<form name=\"billSearch\" action=FindBill method=get>");
      out.println("Enter billing period: ");
      out.println("<input type=text size=30 name=\"billperiod\">");
      out.println("<input type=submit name=\"findBill\" value=\"Find\" >");
      out.println("</form>");

   }



   public void drawShowInfo(HttpServletRequest req, PrintWriter out)
   {
      String billperiod = req.getParameter("billperiod");

      out.println("<p><b>Bill for billing period " + billperiod + ":</b>");
      /* TODO: Execute the query and print out the results rather than hard coding the results */
      out.println("<p> CS460 TODOs </p>");
      out.println("<p> CS473 TODOs </p>");

   }


   public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
   {
      res.setContentType("text/html");
      PrintWriter out = res.getWriter();

      drawHeader(req,out);

      if(req.getParameter("findBill") == null)
      {
         drawGetBill(req,out);
      }
      else
      {
         drawShowInfo(req,out);
         System.out.println("CSC460: inside doGet FindBill____" + req.getParameter("billperiod") +"___");
      }

      drawFooter(req,out);
   }
}



