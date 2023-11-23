package dao;

import model.Circuito;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CircuitoDAO extends DAO {
    public CircuitoDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    public boolean insert(Circuito circuito) {
        boolean status = false;
        try {
            String sql = "INSERT INTO circuito (circuito_nome, circuito_cidade, circuito_pais, circuito_latitude, circuito_longitude, circuito_altitude) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, circuito.getNome());
            st.setString(2, circuito.getCidade());
            st.setString(3, circuito.getPais());
            st.setDouble(4, circuito.getLatitude());
            st.setDouble(5, circuito.getLongitude());
            st.setDouble(6, circuito.getAltitude());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Circuito get(int id) {
        Circuito circuito = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM circuito WHERE circuito_id=" + id;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                circuito = new Circuito(
                        rs.getInt("circuito_id"),
                        rs.getString("circuito_nome"),
                        rs.getString("circuito_cidade"),
                        rs.getString("circuito_pais"),
                        rs.getDouble("circuito_latitude"),
                        rs.getDouble("circuito_longitude"),
                        rs.getDouble("circuito_altitude")
                );
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return circuito;
    }


    public List<Circuito> getOrderByID(int pagina) {
        return getList(pagina);
    }

    public List<Circuito> getOrderByNome(int x) {
        return getList(x);
    }

    private List<Circuito> getList(int pagina) {
        List<Circuito> circuitos = new ArrayList<>();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if(pagina > 7)
            {
            	pagina = 7;
            }
            else if(pagina <1)
            {
            	pagina = 1;
            }
            String sql = "SELECT *\n"
            		+ "FROM circuito\n"
            		+ "WHERE circuito_id >"+pagina+"*10\n"
            		+ "ORDER BY circuito_id\n"
            		+ "LIMIT 5;\n"
            		+ "";

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Circuito c = new Circuito(
                        rs.getInt("circuito_id"),
                        rs.getString("circuito_nome"),
                        rs.getString("circuito_cidade"),
                        rs.getString("circuito_pais"),
                        rs.getDouble("circuito_latitude"),
                        rs.getDouble("circuito_longitude"),
                        rs.getDouble("circuito_altitude")
                );
                circuitos.add(c);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return circuitos;
    }

    public boolean update(Circuito circuito) {
        boolean status = false;
        try {
            String sql = "UPDATE circuito SET circuito_nome = ?, " +
                    "circuito_cidade = ?, " +
                    "circuito_pais = ?, " +
                    "circuito_latitude = ?, " +
                    "circuito_longitude = ?, " +
                    "circuito_altitude = ? " +
                    "WHERE circuito_id = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, circuito.getNome());
            st.setString(2, circuito.getCidade());
            st.setString(3, circuito.getPais());
            st.setDouble(4, circuito.getLatitude());
            st.setDouble(5, circuito.getLongitude());
            st.setDouble(6, circuito.getAltitude());
            st.setInt(7, circuito.getId());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean delete(int id) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM circuito WHERE circuito_id = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}
