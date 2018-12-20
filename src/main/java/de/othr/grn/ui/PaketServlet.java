package de.othr.grn.ui;

import de.othr.grn.entity.*;
import de.othr.grn.service.WarenWirtschaftService;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaketServlet",urlPatterns = "/Servlet")
public class PaketServlet extends HttpServlet {

    @Inject
    private WarenWirtschaftService ps;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();

        out.println("<html>");

        out.println("<head>");
        out.println("</head>");

        out.println("<body>");
        out.println("<h1>Paket-Servlet-Test</h1>");

        Adresse adresse = new Adresse("Weggasse 9","54231","Dorfstadt");
        Lieferung p = new Paket("Tontauben", adresse,312, Versandart.standart);
        p=ps.aufgeben(p,1);
        printObject(out, p);
        p=ps.empfangen(p);
        printObject(out, p);

        Lieferung p2 = new Bestellung(new Lagergut("Tontauben", 213), adresse, 7, Versandart.express);
        p2=ps.aufgeben(p2,1);
        printObject(out, p2);
        p2=ps.empfangen(p2);
        printObject(out, p2);

        List<Lieferung> lieferungen = ps.lieferungenAnzeigen(adresse);
        for(Lieferung l: lieferungen){
            printObject(out, l);
        }

        out.println("</body>");
        out.println("</html>");
    }

    private void printObject(ServletOutputStream out, Object o) throws IOException {
        out.println("<p>" + o.toString().replace("\'","<br>") + "</p>");
    }
}
