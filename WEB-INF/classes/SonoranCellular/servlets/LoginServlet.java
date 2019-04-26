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
    private Connection m_conn;

    public LoginServlet()
    {
        super();
        m_conn = connectDB();
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

        //parse login
        String loginInfo = req.getQueryString();
        String[] loginParams = loginInfo.split("&");
        String email, user;
        try{
            email = loginParams[0].split("=")[1];
            user = loginParams[1].split("=")[1];
        } catch(ArrayIndexOutOfBoundsException e){
            email = "";
            user = "";
        }

        //query db
        ResultSet rs0 = null;
        try{
			Statement s = m_conn.createStatement(
    			ResultSet.TYPE_SCROLL_INSENSITIVE,
    			ResultSet.CONCUR_READ_ONLY
			);
		    rs0 = s.executeQuery(String.format("SELECT * FROM Users WHERE user=%s and email=%s", user, email));
        } catch(Exception e) {e.printStackTrace();}

        if (countResults(rs0) == 1){
            drawLoginSuccess(req,out);
        } else {drawLoginFail(req,out);}

    }

    private static int countResults(ResultSet rs){
		try{
			int count = 0;
			while (rs.next()) {count++;}
			rs.beforeFirst(); //rewind result set
			return count;
		} catch(Exception e) {e.printStackTrace();}
		return -1;
	}

    //connect to database
	public static Connection connectDB(){
		try{
			Class.forName("oracle.jdbc.OracleDriver");  // Registers drivers
	        Connection m_conn = DriverManager.getConnection(OracleConnect.connect_string,OracleConnect.user_name,OracleConnect.password); //get a connection
	        if (m_conn == null) throw new Exception("getConnection failed");
			m_conn.setAutoCommit(true);//optional, but it sets auto commit to true
            return m_conn;
		} catch(Exception e){e.printStackTrace();}
        return null;
	}
}
