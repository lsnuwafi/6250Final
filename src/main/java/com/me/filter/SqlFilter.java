/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.filter;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 18572
 */
public class SqlFilter extends CharacterEncodingFilter{
    private static String[] invalidCharacter = new String[]{  
        "aa", "script","select","insert","document","window","function",  
        "delete","update","prompt","alert","create","alter",  
        "drop","iframe","link","where","replace","function","onabort",  
        "onactivate","onafterprint","onafterupdate","onbeforeactivate",  
        "onbeforecopy","onbeforecut","onbeforedeactivateonfocus",  
        "onkeydown","onkeypress","onkeyup","onload",  
        "expression","applet","layer","ilayeditfocus","onbeforepaste",  
        "onbeforeprint","onbeforeunload","onbeforeupdate",  
        "onblur","onbounce","oncellchange","oncontextmenu",  
        "oncontrolselect","oncopy","oncut","ondataavailable",  
        "ondatasetchanged","ondatasetcomplete","ondeactivate",  
        "ondrag","ondrop","onerror","onfilterchange","onfinish","onhelp",  
        "onlayoutcomplete","onlosecapture","onmouse","ote",  
        "onpropertychange","onreadystatechange","onreset","onresize",  
        "onresizeend","onresizestart","onrow","onscroll",  
        "onselect","onstaronsubmit","onunload","IMgsrc","infarction"  
    };  
    
   
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)  
            throws ServletException, IOException{     
        String parameterName = null;  
        String parameterValue = null;  
        // 获取请求的参数
        
        logger.info("it is filtering");
        Enumeration<String> allParameter = request.getParameterNames();  
        while(allParameter.hasMoreElements()){  
            parameterName = allParameter.nextElement();  
            parameterValue = request.getParameter(parameterName);  
            if(null != parameterValue){  
                for(String str : invalidCharacter){
                    logger.info(parameterValue);
                    if (parameterValue.toLowerCase().contains(str.toLowerCase())){  
                        logger.info("contain");
                        //response.sendRedirect("index.htm");
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, str+": is not valid");
                        return;
                    }  
                }  
            }  
        }  
        super.doFilterInternal(request, response, filterChain);  
    }  
    
    
    
}
