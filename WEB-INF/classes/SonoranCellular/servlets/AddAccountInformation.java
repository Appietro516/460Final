package SonoranCellular.servlets;
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import SonoranCellular.servlets.*;
import SonoranCellular.utils.*;


public class AddAccountInformation extends HttpServlet
{
   private Connection m_conn;
   public AddAccountInformation()
   {
      super();
      m_conn = connectDB();
   }


   public void drawUpdateMessage(HttpServletRequest req, PrintWriter out, String email, String accountname, int accountnum)
   {
      boolean isMember = true;

      out.println("<p><b>Email:</b>  " + email + "</p>");
      out.println("<p><b>Account Name:</b>  " + accountname + "</p>");
      out.println("<p><b>Account Number:</b>  " + accountnum + "</p>");

      out.println("<br>");

      out.println("<form name=\"MainMenu\" action=LoginServlet>");
      out.println("<input type=submit name=\"MainMenu\" value=\"MainMenu\">");
      out.println("</form>");

      out.println("<br>");

      out.println("<form name=\"logout\" action=index.html>");
      out.println("<input type=submit name=\"logoutSonoranCellular\" value=\"Logout\">");
      out.println("</form>");
   }


   public void drawHeader(HttpServletRequest req, PrintWriter out, String msg) {
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Account Addition</title>");
      out.println("</head>");

      out.println("<body>");
      out.println("<p>");
      out.println("<center>");
      out.println("<font size=7 face=\"Arial, Helvetica, sans-serif\" color=\"#000066\">");
      out.println("<center>\n<strong>SonoranCellular</strong></br></font>");
      out.println("</center>\n<hr color=\"#000066\">");
      out.println(String.format("%s</b><br></font>", msg));

      out.println("<hr>");
   }


   public void drawFooter(HttpServletRequest req, PrintWriter out)
   {
      out.println("</center>");
      out.println("</p>");
      out.println("</body>");
      out.println("</html>");
   }


   public void drawAddAccountInformationMenu(HttpServletRequest req, PrintWriter out)
   {
      out.println("<form name=\"AddAccountInformation\" action=AddAccountInformation method=get>");
      out.println("<font size=3 face=\"Arial, Helvetica, sans-serif\" color=\"#000066\">");
      out.println("<p>");
      out.println("<b>Email Address:</b>");
      out.println("<input type=text name=\"email\">");
      out.println("<br>");
      out.println("</p>");

      out.println("<p>");
      out.println("<b>Account Name: </b>");
      out.println("<input type=text name=\"accountname\">");
      out.println("<br>");
      out.println("</p>");

      out.println("<p>");
      out.println("<b>Account Number: </b>");
      out.println("<input type=text name=\"accountnum\">");
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
      out.println("<form name=\"Cancel\" action=index.html method=get>");
      out.println("<input type=submit name=\"Cancel\" value=\"Cancel\">&nbsp&nbsp");
      out.println("</form>");
      out.println("</td>");
      out.println("</tr>");

      out.println("</table>");
      out.println("<br><br><br>");
   }


   public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
   {
      res.setContentType("text/html");
      PrintWriter out = res.getWriter();

      if(req.getParameter("Submit") == null)
      {
         drawHeader(req,out,"Add a new account");
         drawAddAccountInformationMenu(req,out);
      }
      else
      {

          //parse login
          String loginInfo = req.getQueryString();
          String[] loginParams = loginInfo.split("&");
          String email, user;
          int num;
          try{
              email = loginParams[0].split("=")[1];
              user = loginParams[1].split("=")[1];
              num = Integer.parseInt(loginParams[2].split("=")[1]);
          } catch(ArrayIndexOutOfBoundsException e){
              drawHeader(req,out,"Fields must not be empty");
              drawAddAccountInformationMenu(req,out); //redraw page if empty
              return;
          } catch(NumberFormatException e){
              drawHeader(req,out,"Account Number must be number");
              drawAddAccountInformationMenu(req,out); //redraw page if empty
              return;
          }

          ResultSet rs0 = runQuery(String.format("SELECT * FROM Users WHERE Email = '%s'", email));
          ResultSet rs1 = runQuery(String.format("SELECT * FROM Users WHERE User = '%s'", user));
          try{
              Statement s = m_conn.createStatement(
      			ResultSet.TYPE_SCROLL_INSENSITIVE,
      			ResultSet.CONCUR_READ_ONLY
  			  );
              if(countResults(rs0) == 0 && countResults(rs1) == 0){
                s.executeUpdate(String.format("INSERT INTO Users VALUES (%s, '%s', '%s')",num,email,user));
                drawUpdateMessage(req,out, email, user, num);
              } else{
                drawHeader(req,out,"Login already exist!");
                drawAddAccountInformationMenu(req,out); //redraw page if empty
              }
          } catch(SQLException e) {e.printStackTrace();}
      }

      drawFooter(req,out);
   }

   public ResultSet runQuery(String query){
       ResultSet rs0 = null;
       try{
         Statement s = m_conn.createStatement(
             ResultSet.TYPE_SCROLL_INSENSITIVE,
             ResultSet.CONCUR_READ_ONLY
         );
         rs0 = s.executeQuery(query);
         return rs0;
     } catch(Exception e) {e.printStackTrace();}
     return null;
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

   //connect to databases
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
