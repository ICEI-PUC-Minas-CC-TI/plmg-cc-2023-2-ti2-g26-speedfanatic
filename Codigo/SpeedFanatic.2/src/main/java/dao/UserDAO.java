

package dao;

import model.User;
import spark.Request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class UserDAO extends DAO {
    public UserDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    
    public boolean insert(User user) {
        boolean status = false;
        try {
            String sql = "INSERT INTO \"user\" (user_username, user_email, user_senha, user_data_criacao, user_piloto, user_equipe, user_nota) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, user.getUsername());
            st.setString(2, user.getEmail());
            st.setString(3, user.getSenha());
            st.setTimestamp(4, Timestamp.valueOf(user.getDataCriacao().atStartOfDay()));
            st.setInt(5, user.getPiloto());
            st.setInt(6, user.getEquipe());
            st.setInt(7, 0);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public User get(int id) {
        User user = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM \"user\" WHERE user_id=" + id;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_username"),
                        rs.getString("user_email"),
                        rs.getString("user_senha"),
                        rs.getDate("user_data_criacao").toLocalDate(),
                        rs.getInt("user_piloto"),
                        rs.getInt("user_equipe"),
                        rs.getInt("user_nota")
                );
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    public List<User> get() {
        return get("");
    }

    public List<User> getOrderByUsername() {
        return get("user_username");
    }

    public List<User> getOrderByDataCriacao() {
        return get("user_data_criacao");
    }

    public List<User> getOrderByEquipe() {
        return get("user_equipe");
    }

    private List<User> get(String orderBy) {
        List<User> users = new ArrayList<User>();
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM \"user\" " + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                User u = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_username"),
                        rs.getString("user_email"),
                        rs.getString("user_senha"),
                        rs.getDate("user_data_criacao").toLocalDate(),
                        rs.getInt("user_piloto"),
                        rs.getInt("user_equipe"),
                        rs.getInt("user_nota")
                );
                users.add(u);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return users;
    }

    public boolean update(User user) {
        boolean status = false;
        try {
            String sql = "UPDATE \"user\" SET user_username = ?, user_email = ?, user_senha = ?, " +
                    "user_data_criacao = ?, user_piloto = ?, user_equipe = ?, user_nota = ? WHERE user_id = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, user.getUsername());
            st.setString(2, user.getEmail());
            st.setString(3, user.getSenha());
            st.setTimestamp(4, Timestamp.valueOf(user.getDataCriacao().atStartOfDay()));
            st.setInt(5, user.getPiloto());
            st.setInt(6, user.getEquipe());
            st.setInt(7, user.getNota());
            st.setInt(8, user.getID());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public boolean delete(int id) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM user WHERE user_id = " + id);
            st.close();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }
    public User authenticate(String email, String senha, Request request) {
        User user = null;
        try {
            String sql = "SELECT * FROM \"user\" WHERE user_email = ? AND user_senha = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, senha);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User(
                    rs.getInt("user_id"),
                    rs.getString("user_username"),
                    rs.getString("user_email"),
                    rs.getString("user_senha"),
                    rs.getDate("user_data_criacao").toLocalDate(),
                    rs.getInt("user_piloto"),
                    rs.getInt("user_equipe"),
                    rs.getInt("user_nota")
                );
                System.out.println("NOTA NO LOGIN" +user.getNota());
                
                // Crie um token JWT e armazene-o na sessão
                String token = criarTokenJWT(user);
                System.out.println(token);
                request.session().attribute("usuarioAutenticado", token);
                request.session().attribute("user_id", user.getID());
                request.session().attribute("user_username", user.getUsername());
                request.session().attribute("user_nota", user.getNota());
            }
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    private String criarTokenJWT(User user) {
        // Defina as informações do token
        String subject = String.valueOf(user.getID()); // Use o ID do usuário como assunto do token

        // Gere uma chave HMAC-SHA256 segura
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // Gere o token JWT usando a chave HMAC-SHA256 segura
        String token = Jwts.builder()
            .setSubject(subject)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();

        return token;
    }


    public User authenticate(String username, String senha) {
        User user = null;
        try {
            String sql = "SELECT * FROM \"user\" WHERE user_username = ? AND user_senha = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, senha);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User(
                    rs.getInt("user_id"),
                    rs.getString("user_username"),
                    rs.getString("user_email"),
                    rs.getString("user_senha"),
                    rs.getDate("user_data_criacao").toLocalDate(),
                    rs.getInt("user_piloto"),
                    rs.getInt("user_equipe"),
                    rs.getInt("user_nota")
                );
            }
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    
    public User authenticate(String username) {
        User user = null;
        try {
            String sql = "SELECT * FROM \"user\" WHERE user_username = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User(
                    rs.getInt("user_id"),
                    rs.getString("user_username"),
                    rs.getString("user_email"),
                    rs.getString("user_senha"),
                    rs.getDate("user_data_criacao").toLocalDate(),
                    rs.getInt("user_piloto"),
                    rs.getInt("user_equipe"),
                    rs.getInt("user_nota")
                );
            }
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

}
