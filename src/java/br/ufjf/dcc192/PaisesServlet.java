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
    String cores [];
    int i = 0;

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
            for (Map.Entry<String, String> pais : paises.entrySet())
            {
                out.println("<dt> " + pais.getKey() + "</dt>");
                out.println("<dd> " + pais.getValue() + "</dt>");
            }
            for (Map.Entry<String, String> pais : paises.entrySet())
            {
                Boolean colocar = false;
                cores = new String [paises.size()]; 
                for (i = 0; i < paises.size(); i++)
                {
                    if (cores[i].equals(pais.getValue()))
                    {
                        colocar = true;
                    }
                }
                if (colocar)
                {
                    colocar = false;
                }
                else
                {
                    for (i = 0; i < paises.size(); i++)
                    {
                        if (cores[i] == null)
                            cores[i] = pais.getValue();
                    }
                }
            }
            for (i = 0; i < paises.size(); i++)
            {
                for (Map.Entry<String, String> pais : paises.entrySet())
                {
                    out.println("<dt> " + cores[i] + "</dt>");
                    if (cores[i].equals(pais.getValue()))
                    {
                        out.println("<dd> " + pais.getKey() + "</dd>");
                    }
                }
            }
            out.println("</dl>");
            out.println("</body>");
            out.println("</html>");
        }

    }

    
}
