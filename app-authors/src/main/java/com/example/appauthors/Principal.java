package com.example.appauthors;

import jakarta.activation.DataSource;
import jakarta.persistence.Persistence;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Principal {

    public static void main(String[] args) throws NamingException {

        Context initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup("java:comp/env");
        DataSource dataSource = (DataSource) context.lookup("jdbc/myDataSource");

        var p = Persistence.createEntityManagerFactory("pu1");
        var em = p.createEntityManager();

        context.close();

    }
}
