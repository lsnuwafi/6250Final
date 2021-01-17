/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.interceptor;




import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author 18572
 */
public class SqlInterceptor implements HandlerInterceptor{
    private static String[] invalidCharacter = new String[]{  
        "script","select","insert","document","window","function",  
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
    public boolean preHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o) throws Exception {
//        Logger log = Logger.getLogger(SqlInterceptor.class);
//        log.info("I'm starting"); 
        System.out.println("the inter cepter is working");
        String parameterName = null;  
        String parameterValue = null;  
        // 获取请求的参数
        
        
        Enumeration<String> allParameter = hsr.getParameterNames();          
        while(allParameter.hasMoreElements()){  
            parameterName = allParameter.nextElement();  
            parameterValue = hsr.getParameter(parameterName);  
            if(null != parameterValue){  
                for(String str : invalidCharacter){
                    //logger.info(parameterValue);
                    if (parameterValue.toLowerCase().contains(str.toLowerCase())){  
                        //logger.info("contain");
                        //response.sendRedirect("index.htm");
                        hsr1.sendError(HttpServletResponse.SC_BAD_REQUEST, parameterValue+": here is the interceptor not valid");
                        return false;
                    }  
                }  
            }  
        }  
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
    }
}
