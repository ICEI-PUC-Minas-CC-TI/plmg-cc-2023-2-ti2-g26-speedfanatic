package model;
import java.time.LocalDate;

public class Resposta {
    private int id;
    private String conteudo;
    private LocalDate data;
    private String usuario;
    private int post;
    private int respostaPaiId;
    private String chain;
    private int categoria;

    public Resposta() {
        id = 1;
        conteudo = "";
        data = LocalDate.now();
        usuario = "";
        post = 1;
        respostaPaiId = 0;
        categoria = 0;
    }

    public Resposta(int id, String conteudo, int post, String usuario, int respostaPaiId, LocalDate data, int categoria) {
        setID(id);
        setConteudo(conteudo);
        setPost(post);
        setUsuario(usuario);
        setRespostaPaiId(respostaPaiId);
        setData(data);
        setCategoria(categoria);
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getRespostaPaiId() {
        return respostaPaiId;
    }

    public void setRespostaPaiId(int respostaPaiId) {
        this.respostaPaiId = respostaPaiId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        // Garante que a data não pode ser futura
        if (!data.isAfter(LocalDate.now())) {
            this.data = data;
        }
    }
    public int getCategoria()
    {
    	return categoria;
    }
    public void setCategoria(int categoria)
    {
    	this.categoria = categoria;
    }
    @Override
    public String toString() {
        return "Resposta: " + conteudo + "  Data: " + data + "  Usuário: " + usuario + "  Post: " + post + "  Resposta Pai ID: " + respostaPaiId;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getID() == ((Resposta) obj).getID());
    }
}
