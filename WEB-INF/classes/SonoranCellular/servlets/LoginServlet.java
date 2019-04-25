package SonoranCellular.servlets;
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import SonoranCellular.servlets.*;
import SonoranCellular.utils.*;
import java.sql.*;

public class LoginServlet extends HttpServlet
{
    public LoginServlet()
    {
        super();
        System.out.println("HI\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    OracleConnect oc = new OracleConnect();

    public void drawHeader(HttpServletRequest req, PrintWriter out)
    {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>SonoranCellular logged in</title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<p>");
        out.println("<center>");
        out.println("<font size=7 face=\"Arial, Helvetica, sans-serif\" color=\"#000066\">");
        out.println("<center>\n<strong>SonoranCellular</strong></br>");
        out.println("</center>\n<hr color=\"#000066\">");
        out.println("<br><br>");

    }

    public void drawFooter(HttpServletRequest req, PrintWriter out)
    {
        out.println("</center>");
        out.println("</p>");
        out.println("</body>");
        out.println("</html>");
    }


    private void drawActiveOptions(HttpServletRequest req, PrintWriter out)
    {

        out.println("<br>");

	out.println("<form name=\"AddPlan\" action=AddPlan method=get>");
        out.println("<input type=submit name=\"AddPlan\" value=\"Add a Plan\">");
        out.println("</form>");

	out.println("<br>");

        out.println("<form name=\"FindBill\" action=FindBill method=get>");
        out.println("<input type=submit name=\"FindBill\" value=\"Print Bill for a billing period\">");
        out.println("</form>");

        out.println("<br>");

        out.println("<form name=\"PlanShare\" action=./JSP/SharedAssignment.jsp>");
        out.println("<input type=submit name=\"SharedAssignment\" value=\"Who is assigned to the same plan?\">");
        out.println("</form>");

        out.println("<br>");

        out.println("<form name=\"logout\" action=index.html>");
        out.println("<input type=submit name=\"logoutSonoranCellular\" value=\"Log out\">");
        out.println("</form>");
    }

    private void drawFailOptions(HttpServletRequest req, PrintWriter out)
    {
        out.println("<font size=5 face=\"Arial,Helvetica\">");
        out.println("<b>Error: e-mail does not exist.</b></br>");

        out.println("<hr");
        out.println("<br><br>");

        out.println("<form name=\"logout\" action=index.html>");
        out.println("<input type=submit name=\"home\" value=\"Return to Main Menu\">");
        out.println("</form>");

        out.println("<br>");
    }

    public void drawLoginSuccess(HttpServletRequest req, PrintWriter out)
    {
        System.out.println("HI2\n\n\n\n\n\n\n\n\n\n\n\n\n");
        drawHeader(req,out);
        drawActiveOptions(req,out);
        drawFooter(req,out);
    }

    public void drawLoginFail(HttpServletRequest req, PrintWriter out)
    {
        drawHeader(req,out);
        drawFailOptions(req,out);
        drawFooter(req,out);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        //System.out.println("Res " + res);
        System.out.println("Req " + req.getQueryString());
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        //add if statement here.
        //if login success, call the following function
        drawLoginSuccess(req,out);

        //if fail, call the following function
        //drawLoginFail(req,out);
    }
}
