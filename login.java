package com.rms;

import java.io.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.auth.login.Configuration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Statement;
import com.sun.xml.internal.fastinfoset.sax.Properties;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		HttpSession session=request.getSession(); 
		 String userId = request.getParameter("email");
		 //session.putValue("userId",userId);
		  String password = request.getParameter("password");
		 //session.putValue("password",password);
		  
		  PrintWriter out = response.getWriter();
		 
		 try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 java.sql.Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppdcom_seng624", "nisha", "ace456");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		Statement st = null;
		try {
			st = (Statement) con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = st.executeQuery("select * from ppdcom_seng624.users where users_email_address='"+userId+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
					try {
	
						if(rs.next())
						 {
							
						if(rs.getString(6).equals(password))
						{			 
					        session.setAttribute("userId",userId);	 
					        request.getRequestDispatcher("menu.html").include(request, response);
						}
						else
						{
							    out.println("<script type=\"text/javascript\">");
							    out.println("alert('Password is incorrect');");
							    out.println("location='order.html';");
							    out.println("</script>");	
						}
						 }
						
						else
						 {
							out.println("<script type=\"text/javascript\">");
						    out.println("alert('User does not exist in Database');");
						    out.println("location='order.html';");
						    out.println("</script>");	
						 }
						

					 }catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					
					
		
		
	}

}}

