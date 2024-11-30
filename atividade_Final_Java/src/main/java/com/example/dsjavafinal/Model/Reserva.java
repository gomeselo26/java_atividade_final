package com.example.dsjavafinal.Model;

public class Reserva {
    private Integer id;
    private String numeroSala;
    private String curso;
    private String disciplina;
    private String professor;
    private String data;
    private String hrEntrada;
    private String hrSaida;
    private Boolean informatica;
    private String turno;

    public Reserva(Integer id, String numeroSala, String curso, String disciplina, String professor,
                   String data, String hrEntrada, String hrSaida, Boolean informatica, String turno) {
        this.id = id;
        this.numeroSala = numeroSala;
        this.curso = curso;
        this.disciplina = disciplina;
        this.professor = professor;
        this.data = data;
        this.hrEntrada = hrEntrada;
        this.hrSaida = hrSaida;
        this.informatica = informatica;
        this.turno = turno;
    }

    public Reserva(String numeroSala, String curso, String disciplina, String professor,
                   String data, String hrEntrada, String hrSaida, Boolean informatica, String turno) {
        this.numeroSala = numeroSala;
        this.curso = curso;
        this.disciplina = disciplina;
        this.professor = professor;
        this.data = data;
        this.hrEntrada = hrEntrada;
        this.hrSaida = hrSaida;
        this.informatica = informatica;
        this.turno = turno;
    }

    public Reserva() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(String numeroSala) {
        this.numeroSala = numeroSala;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {

        if (data.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.data = data;
        } else {
            throw new IllegalArgumentException("Data inválida. Use o formato YYYY-MM-DD.");
        }
    }

    public String getHrEntrada() {
        return hrEntrada;
    }

    public void setHrEntrada(String hrEntrada) {
        this.hrEntrada = hrEntrada;
    }

    public String getHrSaida() {
        return hrSaida;
    }

    public void setHrSaida(String hrSaida) {
        this.hrSaida = hrSaida;
    }

    public Boolean getInformatica() {
        return informatica;
    }

    public void setInformatica(Boolean informatica) {
        this.informatica = informatica;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", numeroSala='" + numeroSala + '\'' +
                ", curso='" + curso + '\'' +
                ", disciplina='" + disciplina + '\'' +
                ", professor='" + professor + '\'' +
                ", data='" + data + '\'' +
                ", hrEntrada='" + hrEntrada + '\'' +
                ", hrSaida='" + hrSaida + '\'' +
                ", informatica=" + informatica +
                ", turno='" + turno + '\'' +
                '}';
    }
}
