/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.tagHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author 18572
 */
public class TagHandler extends SimpleTagSupport{
    private String csvName;

    public void setCsvName(String csvName) {
        this.csvName = csvName;
    }
    
    /**
     *
     * @throws JspException
     * @throws IOException
     */
    @Override
    public void doTag() throws JspException, IOException {
       
        try {
            
            JspWriter out = getJspContext().getOut();
            out.println(csvName);
            out.println("asdasdsad");
            
            try {
                Class.forName("org.relique.jdbc.csv.CsvDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TagHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:relique:csv:D:\\6250\\As2");
            } catch (SQLException ex) {
                Logger.getLogger(TagHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement st = conn.createStatement();
            //ResultSet res = st.executeQuery("SELECT * from SalesOrder");
            ResultSet res = null;
            try {
                res = st.executeQuery("SELECT * from " + csvName);
            } catch(Exception e) {
                out.print("file not found");
            }
            if (res != null){
                String url = "jdbc";
                ResultSetMetaData rsmd = res.getMetaData();
                int columnsNumber = 0;
                try {
                    columnsNumber = rsmd.getColumnCount();
                } catch (SQLException ex) {
                    Logger.getLogger(TagHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                while (res.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        try {
                            if (i > 1) out.print(",  ");
                            String columnValue = res.getString(i);
                            out.print(columnValue + " " + rsmd.getColumnName(i));
                        } catch (SQLException ex) {
                            Logger.getLogger(TagHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    out.println("<br>");
                }
            }
            
        } catch (SQLException ex) {       
            Logger.getLogger(TagHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
