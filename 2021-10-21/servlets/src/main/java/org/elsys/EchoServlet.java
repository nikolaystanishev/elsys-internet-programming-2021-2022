package org.elsys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EchoServlet", urlPatterns = "/echo/*")
public class EchoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = req.getParameter("msg");
        String pathInfo = req.getPathInfo(); // /{value}/test
        String[] pathParts = pathInfo.split("/");

        resp.setStatus(200);
        resp.getWriter().println("echo: " + msg + " " + String.join(" ", pathParts));
    }
}
