package dao;

import model.Post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class PostDAO extends DAO {	
	public PostDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Post post) {
		boolean status = false;
		try {
	   String sql = "INSERT INTO post (post_conteudo, post_data, post_usuario, post_categoria) " +
                    "VALUES (?, ?, ?, ?)";
       PreparedStatement st = conexao.prepareStatement(sql);
       st.setString(1, post.getConteudo());
       st.setTimestamp(2, Timestamp.valueOf(post.getData().atStartOfDay()));
       st.setString(3, post.getUsuario());
       st.setInt(4, post.getCategoria());
       st.executeUpdate();
       st.close();
       status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Post get(int id) {
		Post post = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM post WHERE post_id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 post = new Post(rs.getInt("post_id"), rs.getString("post_conteudo"), rs.getInt("post_categoria"), 
	                				   rs.getString("post_usuario"),
	        			               rs.getDate("post_data").toLocalDate());
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return post;
	}
	
	
	public List<Post> get() {
		return get("");
	}

	
	public List<Post> getOrderByID() {
		return get("post_id");		
	}
	
	public List<Post> getOrderByData() {
		return get("post_data");		
	}
	
	
	public List<Post> getOrderByCategoria() {
		return get("post_categoria");		
	}
	
	
	private List<Post> get(String orderBy) {
		List<Post> posts = new ArrayList<Post>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM post" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy)) +" DESC";
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Post p = new Post(rs.getInt("post_id"), rs.getString("post_conteudo"), rs.getInt("post_categoria"), 
     				   rs.getString("post_usuario"),
			               rs.getDate("post_data").toLocalDate());
	            posts.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return posts;
	}

	public List<Post> getArray(int categoria) {
		List<Post> posts = new ArrayList<Post>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM post WHERE post_categoria ="+categoria+"ORDER BY post_data";
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Post p = new Post(rs.getInt("post_id"), rs.getString("post_conteudo"), rs.getInt("post_categoria"), 
     				   rs.getString("post_usuario"),
			               rs.getDate("post_data").toLocalDate());
	            posts.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return posts;
	}
	
	public int getInicial(int categoria) {
		int contagem = -1;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT COUNT(*) FROM post WHERE post_categoria = " + categoria;
			
			int tmp;
			ResultSet rs = st.executeQuery(sql);	           
	    
	        
	        if (rs.next()) {
                contagem = rs.getInt(1); // Obtém o valor da primeira coluna
            }
	        st.close();
	        
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return contagem;
	}
	public Post getLastPost(int categoria) {
		Post p = new Post();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT *\n"
					+ "	FROM post\n"
					+ "	WHERE post_categoria = "+categoria+"\n"
					+ "	ORDER BY post_data DESC\n"
					+ "	LIMIT 1;";
			
			ResultSet rs = st.executeQuery(sql);	           
	    
	        if(p != null)
	        {
	        if (rs.next()) {
	         p = new Post(rs.getInt("post_id"), rs.getString("post_conteudo"), rs.getInt("post_categoria"), 
	     				   rs.getString("post_usuario"),
				               rs.getDate("post_data").toLocalDate()); // Obtém o valor da primeira coluna
                System.out.println(p.getUsuario());
            }
	        }
	        st.close();
	        
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return p;
	}
	

	
	
	public boolean update(Post post) {
		boolean status = false;
		try {  
			String sql = "UPDATE post SET post_conteudo = '" + post.getConteudo() + "', "
					   + "post_categoria = " + post.getCategoria() + ", " 
					   + "post_quantidade = " + post.getUsuario() + ","
					   + "post_data = ? WHERE post_id = " + post.getID();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setString(1, post.getConteudo());
	        st.setTimestamp(2, Timestamp.valueOf(post.getData().atStartOfDay()));
	        st.setString(3, post.getUsuario());
	        st.setInt(4, post.getCategoria());
	        st.setInt(5, post.getID());
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
			st.executeUpdate("DELETE FROM post WHERE post_id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}