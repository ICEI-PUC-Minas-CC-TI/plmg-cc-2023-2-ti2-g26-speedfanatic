package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Post {
	private int id;
	private String conteudo;
	private LocalDate data;
	private String usuario;
	private int categoria;
	
	public Post() {
		id = 1;
		conteudo = "";
		data = LocalDate.now();
		usuario = "";
		categoria = 0;
	}

	public Post(int id, String conteudo, int categoria, String usuario, LocalDate data) {
		setId(id);
		setConteudo(conteudo);
		setCategoria(categoria);
		setUsuario(usuario);
		setData(data);
	
	}		
	
	public int getID() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		// Pega a Data Atual
		LocalDate agora = LocalDate.now();
		// Garante que a data de fabricação não pode ser futura
		if (agora.compareTo(data) >= 0)
			this.data = data;
	}

	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Post: " + conteudo + "  Categoria: " + categoria + "   Usuario: " + usuario + "   Data: "
				+ data;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getID() == ((Post) obj).getID());
	}	
}