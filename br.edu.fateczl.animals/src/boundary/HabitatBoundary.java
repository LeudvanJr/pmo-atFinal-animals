package boundary;

import control.HabitatControl;
import exception.AnimalsException;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.control.Alert.AlertType;

public class HabitatBoundary implements BoundaryScreen{
	private Label labelId = new Label();
	private TextField tfNome = new TextField();
	private TextField tfTipo = new TextField();
	private TextField tfDescricao = new TextField();
	private TextField tfClima = new TextField();
	private TextField tfAltitude = new TextField();
	private TableView<Object> tabela;
	
	private HabitatControl control;
	
	public Pane render(){
		
		try {
			control = new HabitatControl();
		} catch (AnimalsException e) {
			e.printStackTrace();
			new Alert(AlertType.ERROR,
					"Falha ao iniciar o Sistema!",
					ButtonType.OK).showAndWait();
		}
		
		tabela = new TableView<>();
		GridPane gridPane = new GridPane();
		BorderPane  borderPane = new BorderPane();
		FlowPane buttonsPane = new FlowPane();
		
		Button btnGravar = new Button("Gravar");
		btnGravar.setOnAction(op -> {
			try {
				control.insert();
			} catch (AnimalsException e) {
				e.printStackTrace();
				new Alert(AlertType.ERROR,
						"Não foi possível Gravar",
						ButtonType.OK).showAndWait();
			}
		});
		
		Button btnPesquisar = new Button("Pesquisar");
		btnPesquisar.setOnAction(op -> {
			try {
				control.search();
			} catch (AnimalsException e) {
				e.printStackTrace();
				new Alert(AlertType.ERROR,
						"Não foi possível Pesquisar",
						ButtonType.OK).showAndWait();
			}
		});
		
		Button btnExcluir = new Button("Excluir");
		btnExcluir.setOnAction(op -> {
			try {
				control.delete();
			} catch (AnimalsException e) {
				e.printStackTrace();
				new Alert(AlertType.ERROR,
						"Não foi possível Gravar",
						ButtonType.OK).showAndWait();
			}
		});
		
		Button btnAtualizar = new Button("Atualizar");
		btnAtualizar.setOnAction(op -> {
			try {
				control.update();
			} catch (AnimalsException e) {
				e.printStackTrace();
				new Alert(AlertType.ERROR,
						"Não foi possível Atualizar",
						ButtonType.OK).showAndWait();
			}
		});
		
		Button btnListar = new Button("Listar");
		btnListar.setOnAction(op -> {
			try {
				control.listAll();
			} catch (AnimalsException e) {
				e.printStackTrace();
				new Alert(AlertType.ERROR,
						"Não foi possível Listar",
						ButtonType.OK).showAndWait();
			}
		});
		
		Button btnLimpar = new Button("Limpar");
		btnLimpar.setOnAction(op -> {
			control.clearBoundary();
		});
		
		gridPane.add(new Label("ID"), 0, 0);
		gridPane.add(labelId, 1, 0);
		gridPane.add(new Label("Nome"), 0, 1);
		gridPane.add(tfNome, 1, 1);
		gridPane.add(new Label("Tipo"), 0, 2);
		gridPane.add(tfTipo, 1, 2);
		gridPane.add(new Label("Descrição"), 0, 3);
		gridPane.add(tfDescricao, 1, 3);
		gridPane.add(new Label("clima"), 0, 4);
		gridPane.add(tfClima, 1, 4);
		gridPane.add(new Label("Altitude"), 0, 5);
		gridPane.add(tfAltitude, 1, 5);
		
		buttonsPane.getChildren().add(btnGravar);
		buttonsPane.getChildren().add(btnAtualizar);
		buttonsPane.getChildren().add(btnExcluir);
		buttonsPane.getChildren().add(btnPesquisar);
		buttonsPane.getChildren().add(btnListar);
		buttonsPane.getChildren().add(btnLimpar);
		buttonsPane.setHgap(5);
		gridPane.add(buttonsPane, 1, 6);
		
		bindings();
		preencherTabela();
		
		gridPane.setHgap(20);
		gridPane.setVgap(5);
		gridPane.setPadding(new Insets(5));
		
		borderPane.setTop(gridPane);
		borderPane.setCenter(tabela);
		return borderPane;
	}

	private void preencherTabela() {
		TableColumn<Object, Integer> col1 = new TableColumn<>("ID");
		col1.setCellValueFactory(new PropertyValueFactory<Object, Integer>("id"));
		
		TableColumn<Object, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<Object, String>("nome"));
		
		TableColumn<Object, String> col3 = new TableColumn<>("Tipo");
		col3.setCellValueFactory(new PropertyValueFactory<Object, String>("tipo"));
		
		TableColumn<Object, String> col4 = new TableColumn<>("Descrição");
		col4.setCellValueFactory(new PropertyValueFactory<Object, String>("descricao"));
		
		TableColumn<Object, String> col5 = new TableColumn<>("Clima");
		col5.setCellValueFactory(new PropertyValueFactory<Object, String>("clima"));
		
		TableColumn<Object, Float> col6 = new TableColumn<>("Altitude");
		col6.setCellValueFactory(new PropertyValueFactory<Object, Float>("altitude"));
		
		tabela.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
			if(novo!=null)
				control.toBoundary(novo);
		});
		
		tabela.getColumns().addAll(col1, col2, col3, col4, col5, col6);
		tabela.setItems(control.getListaHabitat());
	}

	private void bindings() {
		Bindings.bindBidirectional(labelId.textProperty(), control.getId(), new NumberStringConverter());
		Bindings.bindBidirectional(tfNome.textProperty(), control.getNome());
		Bindings.bindBidirectional(tfTipo.textProperty(), control.getTipo());
		Bindings.bindBidirectional(tfDescricao.textProperty(), control.getDescricao());
		Bindings.bindBidirectional(tfClima.textProperty(), control.getClima());
		Bindings.bindBidirectional(tfAltitude.textProperty(), control.getAltitude(), new NumberStringConverter());
	}
}
