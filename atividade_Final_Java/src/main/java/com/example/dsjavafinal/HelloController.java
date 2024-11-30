package com.example.dsjavafinal;

import com.example.dsjavafinal.Model.Reserva;
import com.example.dsjavafinal.Model.Dao.ReservaDao;
import com.example.dsjavafinal.Model.Database.Database;
import com.example.dsjavafinal.Model.Database.DatabaseFactory;
import javafx.collections.FXCollections;
import java.sql.Connection;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class HelloController {
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final ReservaDao reservaDao = new ReservaDao();

    @FXML
    private TextField txtNumeroSala;
    @FXML
    private TextField txtCurso;
    @FXML
    private TextField txtDisciplina;
    @FXML
    private TextField txtProfessor;
    @FXML
    private TextField txtData;
    @FXML
    private TextField txtHrEntrada;
    @FXML
    private TextField txtHrSaida;
    @FXML
    private ToggleGroup grpTurno;
    @FXML
    private RadioButton rbManha;
    @FXML
    private RadioButton rbTarde;
    @FXML
    private RadioButton rbNoite;
    @FXML
    private CheckBox chkInformatica;
    @FXML
    private TextField txtCodigo;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnDeletar;

    private Reserva reserva;
    private ObservableList<Reserva> listReservas;

    @FXML
    private TableView<Reserva> tbvReservas;
    @FXML
    private TableColumn<Reserva, Integer> tbcId;
    @FXML
    private TableColumn<Reserva, String> tbcNumeroSala;
    @FXML
    private TableColumn<Reserva, String> tbcCurso;
    @FXML
    private TableColumn<Reserva, String> tbcDisciplina;
    @FXML
    private TableColumn<Reserva, String> tbcProfessor;
    @FXML
    private TableColumn<Reserva, String> tbcData;
    @FXML
    private TableColumn<Reserva, String> tbcHrEntrada;
    @FXML
    private TableColumn<Reserva, String> tbcHrSaida;
    @FXML
    private TableColumn<Reserva, String> tbcTurno;
    @FXML
    private TableColumn<Reserva, Boolean> tbcInformatica;

    @FXML
    public void initialize() {
        listReservas = FXCollections.observableArrayList();
        tbvReservas.setItems(listReservas);

        tbcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcNumeroSala.setCellValueFactory(new PropertyValueFactory<>("numeroSala"));
        tbcCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        tbcDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        tbcProfessor.setCellValueFactory(new PropertyValueFactory<>("professor"));
        tbcData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tbcHrEntrada.setCellValueFactory(new PropertyValueFactory<>("hrEntrada"));
        tbcHrSaida.setCellValueFactory(new PropertyValueFactory<>("hrSaida"));
        tbcTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));
        tbcInformatica.setCellValueFactory(new PropertyValueFactory<>("informatica"));

        carregarDados();
    }

    @FXML
    protected void onClickCadastrar() {
        if (txtNumeroSala.getText().trim().isEmpty()) {
            campoVazio("O campo 'Número da sala' está vazio!");
            txtNumeroSala.requestFocus();
            return;
        }
        if (txtCurso.getText().trim().isEmpty()) {
            campoVazio("O campo 'Curso' está vazio!");
            txtCurso.requestFocus();
            return;
        }
        if (txtDisciplina.getText().trim().isEmpty()) {
            campoVazio("O campo 'Disciplina' está vazio!");
            txtDisciplina.requestFocus();
            return;
        }
        if (txtProfessor.getText().trim().isEmpty()) {
            campoVazio("O campo 'Professor' está vazio!");
            txtProfessor.requestFocus();
            return;
        }
        if (txtData.getText().equals("")) {
            campoVazio("O campo 'Data' está vazio ou inválido! Use o formato YYYY-MM-DD.");
            txtData.requestFocus();
            return;
        }
        if (txtHrEntrada.getText().trim().isEmpty()) {
            campoVazio("O campo 'Hora de Entrada' está vazio!");
            txtHrEntrada.requestFocus();
            return;
        }
        if (txtHrSaida.getText().trim().isEmpty()) {
            campoVazio("O campo 'Hora de Saída' está vazio!");
            txtHrSaida.requestFocus();
            return;
        }

        String turno = rbManha.isSelected() ? "Manhã" : rbTarde.isSelected() ? "Tarde" : "Noite";
        int id = listReservas.size() + 1;

        reserva = new Reserva(id, txtNumeroSala.getText(), txtCurso.getText(), txtDisciplina.getText(),
                txtProfessor.getText(), txtData.getText(), txtHrEntrada.getText(), txtHrSaida.getText(),
                chkInformatica.isSelected(), turno);
        listReservas.add(reserva);

        reservaDao.setConnection(connection);
        if (reservaDao.inserir(reserva)) {
            aviso("Sucesso!", "Cadastro Realizado", "Reserva cadastrada com sucesso!");
        } else {
            aviso("Erro!", "Erro ao cadastrar a Reserva", "A Reserva não foi Cadastrada!");
        }

        limparCampos();
    }

    @FXML
    protected void onClickBuscar() {
        Integer idBuscar;
        try {
            idBuscar = Integer.parseInt(txtCodigo.getText());
        } catch (NumberFormatException erro) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERRO");
            alerta.setHeaderText("Erro de Conversão");
            alerta.setContentText("Verifique se o código esta correto.");
            alerta.show();
            return;
        }

        reservaDao.setConnection(connection);
        reserva = reservaDao.getReservaById(idBuscar);

        if (reserva != null) {
            populaCampos(reserva);
        } else {
            aviso("Erro", "Não Foi Encontrada a Reserva", "Não foi possível encontrar uma reserva com esse código.");
        }
    }

    @FXML
    protected void onClickDelete() {
        Integer idBuscar;
        try {
            idBuscar = Integer.parseInt(txtCodigo.getText());
        } catch (NumberFormatException erro) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERRO");
            alerta.setHeaderText("Erro de Conversão");
            alerta.setContentText("Verifique se foi informado o código corretamente.");
            alerta.show();
            return;
        }

        reservaDao.setConnection(connection);
        reserva = reservaDao.getReservaById(idBuscar);

        if (reserva != null) {
            if (reservaDao.delete(idBuscar)) {
                aviso("Deletar", "Deletar Tabela Reserva", "Reserva deletada com sucesso!");
                carregarDados(); // Atualiza a tabela após exclusão
            } else {
                aviso("Erro", "Deletar Reserva", "Erro ao deletar a Reserva.");
            }
        } else {
            aviso("Erro", "Reserva Não Encontrada", "Não foi possível encontrar uma reserva com esse código.");
        }

        limparCampos();
    }

    private void populaCampos(Reserva rsv) {
        txtNumeroSala.setText(rsv.getNumeroSala());
        txtCurso.setText(rsv.getCurso());
        txtDisciplina.setText(rsv.getDisciplina());
        txtProfessor.setText(rsv.getProfessor());
        txtData.setText(rsv.getData());
        txtHrEntrada.setText(rsv.getHrEntrada());
        txtHrSaida.setText(rsv.getHrSaida());

        if (rsv.getTurno().equals("Noite")) {
            rbNoite.setSelected(true);
            rbTarde.setSelected(false);
            rbManha.setSelected(false);
        } else if (rsv.getTurno().equals("Tarde")) {
            rbNoite.setSelected(false);
            rbTarde.setSelected(true);
            rbManha.setSelected(false);
        } else {
            rbNoite.setSelected(false);
            rbTarde.setSelected(false);
            rbManha.setSelected(true);
        }

        chkInformatica.setSelected(rsv.getInformatica());
    }

    private void limparCampos() {
        txtNumeroSala.setText("");
        txtCurso.setText("");
        txtDisciplina.setText("");
        txtProfessor.setText("");
        txtData.setText("");
        txtHrEntrada.setText("");
        txtHrSaida.setText("");
        rbManha.setSelected(false);
        rbTarde.setSelected(false);
        rbNoite.setSelected(false);
        chkInformatica.setSelected(false);
        txtNumeroSala.requestFocus();
    }

    private void campoVazio(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erro");
        alert.setHeaderText("Campo Vazio");
        alert.setContentText(msg);
        alert.show();
    }

    private void aviso(String tipo, String cabecalho, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(tipo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.show();
    }

    private void carregarDados() {
        reservaDao.setConnection(connection);
        listReservas.clear();
        listReservas.addAll(reservaDao.getReservas());
        tbvReservas.setItems(listReservas);
    }
}
