package it.easbronz.fakeFlix.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.easbronz.fakeFlix.db.DatabaseManager;
import it.easbronz.fakeFlix.exceptions.InvalidParamException;
import it.easbronz.fakeFlix.model.UtenteFactory;
import it.easbronz.fakeFlix.utils.Utils;

@WebServlet(name = "Registrazione", urlPatterns = { "/registrazione" })
@MultipartConfig
public class Registrazione extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        // Probabilmente non ci servirà la doGet.
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            String username = request.getParameter("username");
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String citta = request.getParameter("citta");

            Part file = request.getPart("file");
            String foto = Utils.getPathImg(file, "users");

            final int MIN_LENGTH = 3;
            final int USERNAME_PASSWORDMAX_LENGTH = 20;
            final int OTHER_MAX_LENGTH = 50;

            Utils.checkString("Username", username, MIN_LENGTH, USERNAME_PASSWORDMAX_LENGTH);
            Utils.checkString("Nome", nome, MIN_LENGTH, OTHER_MAX_LENGTH);
            Utils.checkString("Cognome", cognome, MIN_LENGTH, OTHER_MAX_LENGTH);
            Utils.checkString("Email", email, MIN_LENGTH, OTHER_MAX_LENGTH);
            Utils.checkString("Password", password, MIN_LENGTH, OTHER_MAX_LENGTH);
            Utils.checkString("Città", citta, MIN_LENGTH, OTHER_MAX_LENGTH);
            Utils.checkString("Foto", foto, 0, 200);

            conn = DatabaseManager.getInstance().getDbConnection();
            String query = "INSERT INTO utente VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, nome);
            stmt.setString(4, cognome);
            stmt.setString(5, email);
            stmt.setString(6, citta);
            stmt.setString(7, foto);
            stmt.executeUpdate();

            response.sendRedirect("catalogo"); // RICORDARSI DI CAMBIARLO. SOLO PER DEBUG. REINDIRIZZARE A LOGIN O AD
                                               // INDEX

        } catch (InvalidParamException e) {
            response.sendRedirect("login");
        } catch (SQLException e) {
            Logger.getLogger(UtenteFactory.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            response.sendRedirect("login");
        } finally {
            try {
                stmt.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
