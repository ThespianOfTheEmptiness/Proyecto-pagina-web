/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import dbc.DataBaseConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author samue
 */
@WebServlet(name = "IncrementLikeServlet", urlPatterns = {"/incrementarLike", "/obtenerLikes"})
public class IncrementLikeServlet extends HttpServlet {

    // Método para incrementar likes (ya existente)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Código actual de doPost
    }

    // Nuevo método GET para obtener los likes actuales
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        try {
            // Obtener el ID de la URL (por ejemplo, "/obtenerLikes?id=1")
            String idStr = request.getParameter("id");
            if (idStr == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"ID es requerido\"}");
                return;
            }

            int peliculaId = Integer.parseInt(idStr);
            int likes = 0;

            try (Connection connection = DataBaseConnection.getConnection();
                 PreparedStatement ps = connection.prepareStatement("SELECT contadorLikes FROM peliculas WHERE id = ?")) {

                ps.setInt(1, peliculaId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        likes = rs.getInt("contadorLikes");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"error\":\"Error al obtener los likes\"}");
                return;
            }

            // Devolver la cuenta de likes en formato JSON
            response.getWriter().write("{\"likes\":" + likes + "}");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Datos incorrectos en la solicitud.\"}");
        }
    }
}
