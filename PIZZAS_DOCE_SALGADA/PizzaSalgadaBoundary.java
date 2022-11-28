package edu.curso;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.beans.binding.Bindings;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class PizzaSalgadaBoundary extends Application{

	    
		private Label lblTitulo = new Label("Pizza Salgada");
		
		private Label lbId = new Label("ID:");
		private TextField TextId = new TextField(); 
		
		private Label lbSabor = new Label("Sabor:");
		private TextField TextSabor = new TextField(); 
		
		private Label lbBorda = new Label("Borda:");
		private TextField TextBorda = new TextField();
		
		private Label lbPreco = new Label("Preço:");
		private TextField TextPreco = new TextField(); 
		
		private TableView<PizzaSalgada> tablePizzaSal = new TableView<>();
		
		private Button btnPesquisar = new Button("Pesquisar");
		

		private Button btnSalvar = new Button("Salvar");

		
		
		
		
		private PizzaSalgadaControl controle = new PizzaSalgadaControl();
		
		
		@Override
		public void start(Stage stage) throws Exception {
			
			Pane painel = new Pane();
			
			
			
			lblTitulo.relocate(180, 18);
			lblTitulo.setStyle("-fx-text-fill: 'white';");
			painel.getChildren().add(lblTitulo);
			
			
			lbSabor.relocate(30, 80);
			lbSabor.setStyle("-fx-text-fill: 'white';");
			painel.getChildren().add(lbSabor);
			
	        TextSabor.relocate(100, 80);
	        TextSabor.setPrefSize(240, 0);
			painel.getChildren().add(TextSabor);			
			
			lbId.relocate(30, 140);
			lbId.setStyle("-fx-text-fill: 'white';");
			painel.getChildren().add(lbId);
			
	        TextId.relocate(100, 140);
	        TextId.setPrefSize(240, 0);
			painel.getChildren().add(TextId);
			

					

			lbBorda.relocate(30, 200);
			lbBorda.setStyle("-fx-text-fill: 'white';");
			painel.getChildren().add(lbBorda);
			
	        TextBorda.relocate(100, 200);
	        TextBorda.setPrefSize(240, 0);
			painel.getChildren().add(TextBorda);
			
			
			lbPreco.relocate(30, 260);
			lbPreco.setStyle("-fx-text-fill: 'white';");
			painel.getChildren().add(lbPreco);
			
	        TextPreco.relocate(100, 260);
	        TextPreco.setPrefSize(240, 0);
			painel.getChildren().add(TextPreco);
			
			
			
			btnPesquisar.relocate(350, 80);
			painel.getChildren().addAll(btnPesquisar);
			
			
			btnSalvar.relocate(440, 80);
			painel.getChildren().addAll(btnSalvar);	   



			prepararTable();
			
			tablePizzaSal.relocate(20, 350);
			tablePizzaSal.setPrefSize(480, 200);
			painel.getChildren().addAll(tablePizzaSal);
			

			
		//Botao Inserir
		btnSalvar.setOnAction(e ->{
					
			
		//	String cmd = e.getSource().toString();
		//	System.out.println(cmd)	;
			
			
			if(((TextId.getText().isEmpty()
			  || TextSabor.getText().isEmpty()
			  || TextBorda.getText().isEmpty()
			  || TextPreco.getText().isEmpty()))) {
						
						
	   JOptionPane.showMessageDialog(null, "Não deixei campos em branco", "ERRO", JOptionPane.ERROR_MESSAGE);
							   
					}
			
			controle.salvar();
			controle.limpar();
			controle.pesquisar();
			
			
		});
		
	  	

		//Botao Pesquisar		
		btnPesquisar.setOnAction(e ->{	
			
			
		//	String cmd = e.getSource().toString();
		//	System.out.println(cmd);
			
			
			controle.pesquisar();


					
			
		});
		
		//Metodo que vincula os TextFields com os atributos da pizza salgada
		vincular();

		

			Scene cena = new Scene(painel, 520, 560);
			painel.setStyle("-fx-background-color: green;");
			
			stage.setScene(cena);
			stage.setFullScreen(false);
			stage.setTitle("Tela Pizza Salgada");
			stage.show();
		}
		
		//Adição e preparacao da tabela
		
		private void prepararTable() {
			//Insercao das colunas
			
			TableColumn<PizzaSalgada, Integer > col1 = new TableColumn<>("ID_PIZZA_SAL");
			col1.setCellValueFactory(
					new PropertyValueFactory<PizzaSalgada, Integer>("id"));
			
			
			TableColumn<PizzaSalgada, String  > col2 = new TableColumn<>("SABOR");
			col2.setCellValueFactory(
					new PropertyValueFactory<PizzaSalgada, String>("sabor"));
			
			TableColumn<PizzaSalgada, String  > col3 = new TableColumn<>("BORDA");
			col3.setCellValueFactory(
					new PropertyValueFactory<PizzaSalgada, String>("borda"));
			
			TableColumn<PizzaSalgada, Double  > col4 = new TableColumn<>("PREÇO");
			col4.setCellValueFactory(
					new PropertyValueFactory<PizzaSalgada, Double>("preço"));
			
			TableColumn<PizzaSalgada, String  > col5 = new TableColumn<>("Opções");

			

			
			
	        Callback<TableColumn<PizzaSalgada, String>, TableCell<PizzaSalgada, String>> cellFactory
            = //
            new Callback<TableColumn<PizzaSalgada, String>, TableCell<PizzaSalgada, String>>() {
        @Override
        public TableCell call(final TableColumn<PizzaSalgada, String> param) {
            final TableCell<PizzaSalgada, String> cell = new TableCell<PizzaSalgada, String>() {

            	
                final Button btnApagar = new Button("Apagar");
                final Button btnEditar = new Button("Editar");
                

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                    	btnApagar.setOnAction(e -> {
                    		
                		//	String cmd = e.getSource().toString();
                		//	System.out.println(cmd);
                			
                        	PizzaSalgada ps = getTableView().getItems().get(getIndex());
                        	
                            controle.apagar(ps);
                            controle.limpar();
                            controle.pesquisar();
                        });
                    	
                    	btnEditar.setOnAction(e -> {
                		//	String cmd = e.getSource().toString();
                		//	System.out.println(cmd);
                			
                        	PizzaSalgada ps = getTableView().getItems().get(getIndex());
                            controle.setEntity(ps);
                        	controle.editar();
                        });
                    	
                    	//Campo FlowPane que exibe os botoes de Apagar e Editar
                    	FlowPane fpanel = new FlowPane();
                    	fpanel.getChildren().addAll(btnApagar, btnEditar);
                        setGraphic(fpanel);
                        setText(null);
                    }
                }
            };
            return cell;
        }
    };

    col5.setCellFactory(cellFactory);
			
			
			tablePizzaSal.getColumns().clear();
			tablePizzaSal.getColumns().addAll(col1, col2, col3, col4, col5);
			
			tablePizzaSal.setItems(controle.getListaPizzaSalgada());
			
			
			// Envio de informacoes pros TextFields
//			tablePizzaSal.getSelectionModel()
//			.selectedItemProperty()
//			.addListener( (prop, antiga, novo) -> {
//			
//				
//				controle.setEntity(novo);
//			});
			
			
			
		}
		
		//metodo de vinculacao de TextFields e os atributos da pizza salgada

		public void vincular() {
			
			StringConverter<? extends Number> converterInteiro =
					new IntegerStringConverter();
			
			Bindings.bindBidirectional(TextId.textProperty(), controle.idProperty(),
					                  (StringConverter)converterInteiro);
			
			
			Bindings.bindBidirectional(controle.saborProperty(),
					   TextSabor.textProperty());
			
			Bindings.bindBidirectional(controle.bordaProperty(),
					   TextBorda.textProperty());
			
			
			StringConverter<? extends Number> converterDouble =
					new DoubleStringConverter();
			
			Bindings.bindBidirectional(TextPreco.textProperty(), controle.preçoProperty(),
					  (StringConverter)converterDouble);
		}
		
		

		
		public static void main(String[] args) {
			
			Application.launch(PizzaSalgadaBoundary.class, args);
		}
	}


