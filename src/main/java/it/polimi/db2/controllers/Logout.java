package it.polimi.db2.controllers;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serial;

@WebServlet(name = "Logout", value = "/Logout")
@MultipartConfig
public class Logout extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public Logout(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request,response);
    }

}
