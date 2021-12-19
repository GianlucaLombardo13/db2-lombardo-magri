package it.polimi.db2.controllers;

import it.polimi.db2.connection.ConnectionManager;
import it.polimi.db2.entities.Customer;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "CheckLogin", value = "/CheckLogin")
@MultipartConfig
public class CheckLogin extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private Connection connection = null;

    public CheckLogin(){
        super();
    }

    public void init() throws ServletException{
        try{
            connection= ConnectionManager.tryConnection(getServletContext());
        }catch(ClassNotFoundException e){
            throw new UnavailableException("Can't load DB driver!");
        }catch (SQLException e){
            throw new UnavailableException("Couldn't connect!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username;
        String password;

        username=request.getParameter("username");
        password=request.getParameter("password");

        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Incorrect credentials!");
            return;
        }

        Customer customer = null;

        // Check credentials' customer;

        if(customer==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("User not found!");
        }else{

            request.getSession().setAttribute("customer",customer);
            String json = new Gson().toJson(customer);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }


}
