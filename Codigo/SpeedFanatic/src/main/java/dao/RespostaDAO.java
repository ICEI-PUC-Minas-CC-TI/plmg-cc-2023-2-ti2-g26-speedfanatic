package dao;
import model.Resposta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class RespostaDAO extends DAO {
    public RespostaDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    public boolean insert(Resposta resposta) {
        boolean status = false;
        try {
            String sql = "INSERT INTO resposta (resposta_conteudo, resposta_data, resposta_usuario, resposta_post, resposta_pai_id, resposta_categoria) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, resposta.getConteudo());
            st.setTimestamp(2, Timestamp.valueOf(resposta.getData().atStartOfDay()));
            st.setString(3, resposta.getUsuario());
            st.setInt(4, resposta.getPost());
            if(resposta.getRespostaPaiId()==0)
            {
            	st.setNull(5, resposta.getRespostaPaiId());
            }
            else
            st.setInt(5, resposta.getRespostaPaiId());
            st.setInt(6,  resposta.getCategoria());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Resposta get(int id) {
        Resposta resposta = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM resposta WHERE resposta_id=" + id;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                resposta = new Resposta(rs.getInt("resposta_id"), rs.getString("resposta_conteudo"), rs.getInt("resposta_post"),
                        rs.getString("resposta_usuario"), rs.getInt("resposta_pai_id"),
                        rs.getDate("resposta_data").toLocalDate(), rs.getInt("resposta_categoria"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resposta;
        
    }

    public List<Resposta> get() {
        return get("");
    }

    public List<Resposta> getOrderByID() {
        return get("resposta_id");
    }

    public List<Resposta> getOrderByData() {
        return get("resposta_data");
    }

    public List<Resposta> getOrderByUsuario() {
        return get("resposta_usuario");
    }

    private List<Resposta> get(String orderBy) {
        List<Resposta> respostas = new ArrayList<Resposta>();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM resposta" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy)) + " DESC";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Resposta r = new Resposta(rs.getInt("resposta_id"), rs.getString("resposta_conteudo"), rs.getInt("resposta_post"),
                        rs.getString("resposta_usuario"), rs.getInt("resposta_pai_id"),
                        rs.getDate("resposta_data").toLocalDate(), rs.getInt("resposta_categoria"));;
                respostas.add(r);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return respostas;
    }
    public List<Resposta> getChain(int post_id) {
        List<Resposta> respostas = new ArrayList<Resposta>();

        try {
            
            String sql = "WITH RECURSIVE resposta_chain AS (\n"
            		+ "    SELECT\n"
            		+ "        resposta_id,\n"
            		+ "        resposta_conteudo,\n"
            		+ "        resposta_data,\n"
            		+ "        resposta_post,\n"
            		+ "        resposta_usuario,\n"
            		+ "        resposta_pai_id,\n"
            		+ "        resposta_id::text AS chain,\n"
            		+ "        resposta_categoria -- Acrescentada a coluna resposta_categoria\n"
            		+ "    FROM\n"
            		+ "        public.resposta\n"
            		+ "    WHERE\n"
            		+ "        resposta_pai_id IS NULL\n"
            		+ "    AND\n"
            		+ "        resposta_post = ?\n"
            		+ "    UNION ALL\n"
            		+ "    SELECT\n"
            		+ "        r.resposta_id,\n"
            		+ "        r.resposta_conteudo,\n"
            		+ "        r.resposta_data,\n"
            		+ "        r.resposta_post,\n"
            		+ "        r.resposta_usuario,\n"
            		+ "        r.resposta_pai_id,\n"
            		+ "        rc.chain || ' -> ' || r.resposta_id,\n"
            		+ "        r.resposta_categoria -- Acrescentada a coluna resposta_categoria\n"
            		+ "    FROM\n"
            		+ "        public.resposta r\n"
            		+ "    INNER JOIN\n"
            		+ "        resposta_chain rc ON r.resposta_pai_id = rc.resposta_id\n"
            		+ "    WHERE\n"
            		+ "        r.resposta_post = ?\n"
            		+ ")\n"
            		+ "SELECT\n"
            		+ "    resposta_id,\n"
            		+ "    resposta_conteudo,\n"
            		+ "    resposta_data,\n"
            		+ "    resposta_post,\n"
            		+ "    resposta_usuario,\n"
            		+ "    resposta_pai_id,\n"
            		+ "    chain,\n"
            		+ "    resposta_categoria -- Acrescentada a coluna resposta_categoria\n"
            		+ "FROM\n"
            		+ "    resposta_chain\n"
            		+ "ORDER BY\n"
            		+ "    chain;\n";
              PreparedStatement st = conexao.prepareStatement(sql);
              st.setInt(1, post_id);
              st.setInt(2, post_id);
                ResultSet rs = st.executeQuery(); 
                        while (rs.next()) {
                        	 Resposta r = new Resposta(rs.getInt("resposta_id"), rs.getString("resposta_conteudo"), rs.getInt("resposta_post"),
                                     rs.getString("resposta_usuario"), rs.getInt("resposta_pai_id"),
                                     rs.getDate("resposta_data").toLocalDate(), rs.getInt("resposta_categoria"));;
                respostas.add(r);	
            }
            st.close();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
       
        return respostas;
    }

    public boolean update(Resposta resposta) {
        boolean status = false;
        try {
            String sql = "UPDATE resposta SET resposta_conteudo = '" + resposta.getConteudo() + "', " +
                    "resposta_post = " + resposta.getPost() + ", " +
                    "resposta_usuario = " + resposta.getUsuario() + "," +
                    "resposta_resposta_pai_id = " + resposta.getRespostaPaiId() + ", " +
                    "resposta_data = ? WHERE resposta_id = " + resposta.getID();
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, resposta.getConteudo());
            st.setTimestamp(2, Timestamp.valueOf(resposta.getData().atStartOfDay()));
            st.setString(3, resposta.getUsuario());
            st.setInt(4, resposta.getPost());
            st.setInt(5, resposta.getRespostaPaiId());
            st.setInt(6, resposta.getID());
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
			st.executeUpdate("DELETE FROM post WHERE resposta_id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
