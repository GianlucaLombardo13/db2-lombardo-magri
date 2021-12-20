package it.polimi.db2.controllers;

import com.google.gson.Gson;
import it.polimi.db2.entities.Customer;
import it.polimi.db2.exception.CredentialsException;
import it.polimi.db2.exception.NotUniqueResultException;
import it.polimi.db2.services.CustomerService;

import javax.ejb.EJB;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

@WebServlet(
        name = "CheckLogin",
        value = "/CheckLogin"
)
@MultipartConfig
public class CheckLogin extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @EJB(
            name = "it.polimi.db2.services/CustomerService"
    )

    private CustomerService customerService;

    public CheckLogin(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username;
        String password;

        username=request.getParameter("username");
        password=request.getParameter("password");

        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Incorrect credentials!");
            return;
        }

        Customer customer;
        try {
            customer = this.customerService.checkCredentials(username,password);
        } catch (CredentialsException | NotUniqueResultException e) {
            e.printStackTrace();
            return;
        }

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
