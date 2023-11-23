package model;

public class Circuito {
    private int id;
    private String nome;
    private String cidade;
    private String pais;
    private double latitude;
    private double longitude;
    private double altitude;

    public Circuito() {
        id = 1;
        nome = "";
        cidade = "";
        pais = "";
        latitude = 0.0;
        longitude = 0.0;
        altitude = 0.0;
    }

    public Circuito(int id, String nome, String cidade, String pais, double latitude, double longitude, double altitude) {
        setId(id);
        setNome(nome);
        setCidade(cidade);
        setPais(pais);
        setLatitude(latitude);
        setLongitude(longitude);
        setAltitude(altitude);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        return "Circuito: " + nome + "  Cidade: " + cidade + "   País: " + pais + "   Latitude: " + latitude + "   Longitude: " + longitude + "   Altitude: " + altitude;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getId() == ((Circuito) obj).getId());
    }
}
