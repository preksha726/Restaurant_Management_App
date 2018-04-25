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

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
		String firstname = request.getParameter("fname");
		String lastname= request.getParameter("lname");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		String address= request.getParameter("address");
		String role= request.getParameter("role");
		String payment= request.getParameter("payment");
		String users_active = "1";
		String users_weekly_hrs = "40";
		String users_wage="10";
		Calendar calendar = Calendar.getInstance();
	    java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());

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
		PrintWriter out = response.getWriter();

		
		          String sql = "INSERT INTO users (users_first_name, users_last_name, users_email_address, users_address, users_password, users_active, users_pay_method, users_weekly_hrs, users_wage, users_activity_date ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		          
		          PreparedStatement statement = null;
				try {
					statement = con.prepareStatement(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		          try {
					statement.setString(1, firstname);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		          try {
					statement.setString(2, lastname);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		          try {
					statement.setString(3, email);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		          try {
						statement.setString(4, address);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		          try {
					statement.setString(5, password);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		          try {
						statement.setString(6, users_active);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		          try {
						statement.setString(7, payment);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		          try {
						statement.setString(8, users_weekly_hrs);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		          try {
						statement.setString(9, users_wage);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		          try {
						statement.setDate(10, date);
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
		              System.out.println("A new user was inserted successfully!");
		              RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("success.html");
		              RequetsDispatcherObj.forward(request, response);
		          }
		          
				
		        
		          try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          
 

		
	}

}