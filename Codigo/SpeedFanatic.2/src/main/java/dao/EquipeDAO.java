package dao;

import model.Equipe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class EquipeDAO extends DAO {	
	public EquipeDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Equipe equipes) {
    boolean status = false;
    try {
        String sql = "INSERT INTO equipe (equipe_nome, equipe_nacionalidade) "
                + "VALUES (?, ?)";
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setString(1, equipes.getNome());
        st.setString(2, equipes.getNacionalidade());

        st.executeUpdate();
        st.close();
        status = true;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return status;
}

	public Equipe get(int id) {
	    Equipe equipes = null;

	    try {
	        String sql = "SELECT * FROM equipe WHERE equipe_id=?";
	        PreparedStatement st = conexao.prepareStatement(sql);
	        st.setInt(1, id);

	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            equipes = new Equipe(
	                rs.getInt("equipe_id"),
	                rs.getString("equipe_nome"),
	                rs.getString("equipe_nacionalidade")
	            );
	        }

	        st.close();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }

	    return equipes;
	}

	
	
	public List<Equipe> get() {
		return get("");
	}

	
	public List<Equipe> getOrderByID() {
		return get("equipe_id");		
	}
	
	
	public List<Equipe> getOrderByDescricao() {
		return get("equipe_nacionalidade");		
	}
	
	
	public List<Equipe> getOrderByNome() {
		return get("equipe_nome");		
	}
	
	public List<Equipe> getList(int pagina) {
        List<Equipe> equipes = new ArrayList<>();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if(pagina > 21)
            {
          	pagina = 21;
            }
            else if(pagina <1)
           {
            pagina = 1;
            }
            String sql = "SELECT *\n"
            		+ "FROM equipe\n"
            		+ "WHERE equipe_id >"+pagina+"*10\n"
            		+ "ORDER BY equipe_id\n"
            		+ "LIMIT 5;\n"
            		+ "";

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Equipe c = new Equipe(
                        rs.getInt("equipe_id"),
                        rs.getString("equipe_nome"),
                        rs.getString("equipe_nacionalidade")
                );
                equipes.add(c);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return equipes;
    }

	private List<Equipe> get(String orderBy) {
		List<Equipe> equipes = new ArrayList<Equipe>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM equipe" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
			while (rs.next()) {
			    Equipe p = new Equipe(
			        rs.getInt("equipe_id"),
			        rs.getString("equipe_nome"),
			        rs.getString("equipe_nacionalidade")
			    );
			    equipes.add(p);
			}

	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return equipes;
	}
	
	
	public boolean update(Equipe equipe) {
	    boolean status = false;
	    try {
	        String sql = "UPDATE equipe SET equipe_nome = ?, "
	                + "equipe_nacionalidade = ?, "
	                + " WHERE equipe_id = ?";
	        PreparedStatement st = conexao.prepareStatement(sql);
	        st.setString(1, equipe.getNome());
	        st.setString(2, equipe.getNacionalidade());
	        st.setInt(3, equipe.getID());

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
			st.executeUpdate("DELETE FROM produto WHERE equipe_id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}