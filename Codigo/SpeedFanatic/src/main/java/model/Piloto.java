package model;
public class Piloto {
    private int id;
    private int numero;
    private String abreviacao;
    private String nome;
    private String sobrenome;
    private String pais;

    public Piloto() {
        id = -1;
        numero = 0;
        abreviacao = "";
        nome = "";
        sobrenome = "";
        pais = "";
    }

    public Piloto(int id, int numero, String abreviacao, String nome, String sobrenome, String pais) {
        setId(id);
        setNumero(numero);
        setAbreviacao(abreviacao);
        setNome(nome);
        setSobrenome(sobrenome);
        setPais(pais);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Piloto [ID: " + id + ", Número: " + numero + ", Abreviação: " + abreviacao + ", Nome: " + nome + ", Sobrenome: " + sobrenome
                + ", País: " + pais + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Piloto) {
            Piloto other = (Piloto) obj;
            return this.getId() == other.getId();
        }
        return false;
    }
}
