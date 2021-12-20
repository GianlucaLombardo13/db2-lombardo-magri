package it.polimi.db2.controllers;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serial;

@WebServlet(name = "GetValidityPeriods",value = "/GetValidityPeriods")
@MultipartConfig
public class GetValidityPeriods extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public GetValidityPeriods(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }

}
