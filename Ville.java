package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.MaConnection;

public class Ville {
    private int id;
    private String nom;

    public Ville() {}

    public Ville(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public static int getIdByNom(String nom) throws Exception {
    String sql = "SELECT id FROM ville WHERE nom = ?";
    try (Connection conn = MaConnection.connect();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, nom);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) return rs.getInt("id");
    }
    throw new Exception("Ville introuvable : " + nom);
}

}
