package com.rms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class cart
 */
@WebServlet("/cart")
public class cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cart() {
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
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(true);
		String uId = session.getAttribute("userId").toString(); 
		
		Calendar calendar = Calendar.getInstance();
	    java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());
		String OrderType =request.getParameter("OrderType");				
		String gTotal= request.getParameter("gTotal");

		if(gTotal.equals("0.0")){
			out.println("<script type=\"text/javascript\">");
		    out.println("alert('You cannot place an empty order');");
		    out.println("location='menu.html';");
		    out.println("</script>");	
		}
		else{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("found driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.sql.Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppdcom_seng624", "nisha","ace456");
			System.out.println("connection established with db");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		          String sql = "INSERT INTO orders (orders_activity_date, orders_email, orders_type, orders_total ) VALUES (?, ?, ?, ?)";
		          
		          PreparedStatement statement = null;
				try {
					statement = con.prepareStatement(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					statement.setDate(1, date);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		          try {
					statement.setString(2, uId);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		          try {
					statement.setString(3, OrderType);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		          try {
						statement.setString(4, gTotal);;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		          		           
		          int rowsInserted = 0;
				try {
					rowsInserted = statement.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		          if (rowsInserted > 0) {
		              System.out.println("order placed successfully!");
		              request.getRequestDispatcher("OrderSuccess.html").include(request, response); 
		              
		          }
		          
				
		        
		          try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		 
		

		
		 
		 
		 
		 
		
		
	}

}
