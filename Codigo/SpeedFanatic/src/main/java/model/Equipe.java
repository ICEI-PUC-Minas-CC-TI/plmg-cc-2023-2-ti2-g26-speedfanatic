package model;


public class Equipe {
	private int id;
	private String nome;
	private String nacionalidade;
	
	public Equipe() {
		id = -1;
		nome = "";
		nacionalidade = "";
	}

	public Equipe(int id, String nome, String nacionalidade) {
		setId(id);
		setNome(nome);
		setNacionalidade(nacionalidade);
		
		
	}		
	
	public int getID() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}




	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Equipe equipe = (Equipe) obj;

        return id == equipe.id;
    }

	public Equipe get() {
	    return this;
	}

	
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }



	
}