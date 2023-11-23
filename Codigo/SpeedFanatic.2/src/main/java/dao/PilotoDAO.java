package dao;

import model.Equipe;
import model.Piloto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PilotoDAO extends DAO {
    public PilotoDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    public boolean insert(Piloto piloto) {
        boolean status = false;
        try {
            String sql = "INSERT INTO piloto (piloto_numero, piloto_abreviacao, piloto_nome, piloto_sobrenome, piloto_pais) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, piloto.getNumero());
            st.setString(2, piloto.getAbreviacao());
            st.setString(3, piloto.getNome());
            st.setString(4, piloto.getSobrenome());
            st.setString(5, piloto.getPais());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Piloto get(int id) {
        Piloto piloto = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM piloto WHERE piloto_id=" + id;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                piloto = new Piloto(rs.getInt("piloto_id"), rs.getInt("piloto_numero"),
                        rs.getString("piloto_abreviacao"), rs.getString("piloto_nome"),
                        rs.getString("piloto_sobrenome"), rs.getString("piloto_pais"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return piloto;
    }

    public List<Piloto> getAll() {
        return getAll("");
    }

    private List<Piloto> getAll(String orderBy) {
        List<Piloto> pilotos = new ArrayList<Piloto>();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM piloto" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Piloto p = new Piloto(rs.getInt("piloto_id"), rs.getInt("piloto_numero"),
                        rs.getString("piloto_abreviacao"), rs.getString("piloto_nome"),
                        rs.getString("piloto_sobrenome"), rs.getString("piloto_pais"));
                pilotos.add(p);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return pilotos;
    }

    public List<Piloto> getAllOrderedById() {
        return getAll("piloto_id");
    }

    public List<Piloto> getAllOrderedByAbreviacao() {
        return getAll("piloto_abreviacao");
    }

    public List<Piloto> getAllOrderedByNome() {
        return getAll("piloto_nome");
    }

    public List<Piloto> getAllOrderedBySobrenome() {
        return getAll("piloto_sobrenome");
    }

    public List<Piloto> getAllOrderedByPais() {
        return getAll("piloto_pais");
    }
    public List<Piloto> getList(int pagina) {
        List<Piloto> pilotos = new ArrayList<>();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
        if (pagina > 86) {
               pagina = 86;
          } else if (pagina < 1) {
                pagina = 1;
            }

            String sql = "SELECT *\n"
                       + "FROM piloto\n"
                       + "WHERE piloto_id > " + (pagina - 1) * 10 + "\n"  // Paginação
                       + "ORDER BY piloto_id\n"
                       + "LIMIT 5;\n";

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Piloto piloto = new Piloto(
                    rs.getInt("piloto_id"),
                    rs.getInt("piloto_numero"),
                    rs.getString("piloto_abreviacao"),
                    rs.getString("piloto_nome"),
                    rs.getString("piloto_sobrenome"),
                    rs.getString("piloto_pais")
                );
                pilotos.add(piloto);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return pilotos;
    }


    public boolean update(Piloto piloto) {
        boolean status = false;
        try {
            String sql = "UPDATE piloto SET piloto_numero = ?, piloto_abreviacao = ?, piloto_nome = ?, piloto_sobrenome = ?, piloto_pais = ? WHERE piloto_id = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, piloto.getNumero());
            st.setString(2, piloto.getAbreviacao());
            st.setString(3, piloto.getNome());
            st.setString(4, piloto.getSobrenome());
            st.setString(5, piloto.getPais());
            st.setInt(6, piloto.getId());
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
            st.executeUpdate("DELETE FROM piloto WHERE piloto_id = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}
