package model;

import java.time.LocalDate;

public class User {
    private int id;
    private String username;
    private String email;
    private String senha;
    private LocalDate dataCriacao;
    private int piloto;
    private int equipe;
    private int nota;

    public User() {
        id = 1;
        username = "";
        email = "";
        senha = "";
        dataCriacao = LocalDate.now();
        piloto = 1;
        equipe = 1;
        nota = 0;
    }

    public User(int id, String username, String email, String senha, LocalDate dataCriacao, int piloto, int equipe, int nota) {
        setID(id);
        setUsername(username);
        setEmail(email);
        setSenha(senha);
        setDataCriacao(dataCriacao);
        setPiloto(piloto);
        setEquipe(equipe);
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        // Garante que a data de criação não pode ser futura
        if (!dataCriacao.isAfter(LocalDate.now())) {
            this.dataCriacao = dataCriacao;
        }
    }

    public int getPiloto() {
        return piloto;
    }

    public void setPiloto(int piloto) {
        this.piloto = piloto;
    }

    public int getEquipe() {
        return equipe;
    }

    public void setEquipe(int equipe) {
        this.equipe = equipe;
    }
    public int getNota()
    {
    	return nota;
    }
    public void setNota(int nota)
    {
       this.nota = nota;
    }

    @Override
    public String toString() {
        return "User: " + username + "  Email: " + email + "  Data de Criação: " + dataCriacao + "  Piloto: " + piloto + "  Equipe: " + equipe;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getID() == ((User) obj).getID());
    }
}
