package br.ufjf.dcc192;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(name = "PaisesServlet", urlPatterns = {"/index.html"})
public class PaisesServlet extends HttpServlet {

    Map<String, String> paises;

    public Map PaisesServlet() {
        Map<String, String> p = new HashMap<>();
        p.put("Brasil", "Amarelo");
        p.put("Russia", "Vermelho");
        p.put("Alemanha", "Vermelho");
        p.put("Argentina", "Azul");
        p.put("Holanda", "Laranja");
        p.put("México", "Verde");
        return p;
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, ArrayList<String>> cores = new HashMap<>();
        resp.setContentType("text/html;charset=UTF-8");
        String comando = req.getParameter("comando");
        try (PrintWriter out = resp.getWriter()) {
            paises = PaisesServlet();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Países</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Países </h1>");
            out.println("<dl>");
            if ("cor".equals(comando))
            {
                for (Map.Entry<String, String> pais : paises.entrySet()) {
                    if (!cores.containsKey(pais.getValue())) 
                    {
                        ArrayList<String> corPais = new ArrayList();
                        corPais.add(pais.getKey());
                        cores.put(pais.getValue(), corPais);
                    } 
                    else 
                    {
                        cores.get(pais.getValue()).add(pais.getKey());
                    }
                }
                for (Map.Entry<String, ArrayList<String>> cor : cores.entrySet())
                {
                     out.println("<dt>" + cor.getKey() + "</dt>");
                     for (String pais : cor.getValue()) 
                    out.println("<dd>" + pais + "</dd>");
                }
            }
            else
            {
                for (Map.Entry<String, String> pais : paises.entrySet())
                {
                    out.println("<dt> " + pais.getKey() + "</dt>");
                    out.println("<dd> " + pais.getValue() + "</dt>");
                }
            }
            out.println("</dl>");
            out.println("<p> <a href='?comando=cor'> Juntar por cores </a> </p>");
            out.println("</body>");
            out.println("</html>");
        }

    }

    
}
