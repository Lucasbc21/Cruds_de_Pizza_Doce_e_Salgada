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


//perdao se esta meio sem nexo professor tivemos contratempos e acabamos demorando para conseguir a conexao com banco de dados

public class PizzaDoceBoundary extends Application{

	    
		private Label lblTitulo = new Label("Pizza Doce");
		
		private Label lbId = new Label("ID:");
		private TextField TextId = new TextField(); 
		
		private Label lbCobertura = new Label("Cobertura:");
		private TextField TextCobertura = new TextField(); 
		
		private Label lbBorda = new Label("Borda:");
		private TextField TextBorda = new TextField();
		
		private Label lbPreco = new Label("Preço:");
		private TextField TextPreco = new TextField(); 
		
		private TableView<PizzaDoce> tablePizzaDoce = new TableView<>();
		
		private Button btnPesquisar = new Button("Pesquisar");
		

		private Button btnSalvar = new Button("Salvar");

		
		private PizzaDoceControl controle = new PizzaDoceControl();		
		

		
		
		@Override
		public void start(Stage stage) throws Exception {
			
			Pane painel = new Pane();
			
			
			
			lblTitulo.relocate(180, 18);
			lblTitulo.setStyle("-fx-text-fill: 'white';");
			painel.getChildren().add(lblTitulo);
			
			
			lbCobertura.relocate(30, 80);
			lbCobertura.setStyle("-fx-text-fill: 'white';");
			painel.getChildren().add(lbCobertura);
			
	        TextCobertura.relocate(100, 80);
	        TextCobertura.setPrefSize(240, 0);
			painel.getChildren().add(TextCobertura);			
			
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


            PrepararTable();
		
			
			tablePizzaDoce.relocate(20, 350);
			tablePizzaDoce.setPrefSize(480, 200);
			painel.getChildren().addAll(tablePizzaDoce);
			

			
		//Botao Salvar
		btnSalvar.setOnAction(e ->{
					
			
		//	String cmd = e.getSource().toString();
		//	System.out.println(cmd)	;
			

			
			
			if(((TextId.getText().isEmpty()
			  || TextCobertura.getText().isEmpty()
			  || TextBorda.getText().isEmpty()
			  || TextPreco.getText().isEmpty()))) {
						
						
	   JOptionPane.showMessageDialog(null, "Não deixei campos em branco", "ERRO",
			   JOptionPane.ERROR_MESSAGE);
							   
					}else {
			controle.salvar();
			controle.limpar();
			controle.pesquisar();
			
					}
		});
		
	  	

		//Botao Pesquisar		
		btnPesquisar.setOnAction(e ->{	
			
			
			//String cmd = e.getSource().toString();
			//System.out.println(cmd);
			
			controle.pesquisar();
	


					
			
		});
		vincular();
		
		//Metodo que vincula os TextFields com os atributos da pizza doce
		
		
		

		

			Scene cena = new Scene(painel, 520, 560);
			painel.setStyle("-fx-background-color: blue;");
			
			stage.setScene(cena);
			stage.setFullScreen(false);
			stage.setTitle("Tela Pizza Doce");
			stage.show();
		}
		




			
		//Adição e preparacao da tabela
		
		private void PrepararTable() {
			//Insercao das colunas
			
			TableColumn<PizzaDoce, Integer > col1 = new TableColumn<>("ID_PIZZA_DOCE");
			col1.setCellValueFactory(
					new PropertyValueFactory<PizzaDoce, Integer>("id"));
			
			TableColumn<PizzaDoce, String  > col2 = new TableColumn<>("COBERTURA");
			col2.setCellValueFactory(
					new PropertyValueFactory<PizzaDoce, String>("cobertura"));
			
			
			TableColumn<PizzaDoce, String  > col3 = new TableColumn<>("BORDA");
			col3.setCellValueFactory(
					new PropertyValueFactory<PizzaDoce, String>("borda"));
			
			TableColumn<PizzaDoce, Double  > col4 = new TableColumn<>("PREÇO");
			col4.setCellValueFactory(
					new PropertyValueFactory<PizzaDoce, Double>("preço"));
			
			TableColumn<PizzaDoce, String  > col5 = new TableColumn<>("Opções");
			
			
			
			
			
	        Callback<TableColumn<PizzaDoce, String>, TableCell<PizzaDoce, String>> cellFactory
            = //
            new Callback<TableColumn<PizzaDoce, String>, TableCell<PizzaDoce, String>>() {
        @Override
        public TableCell call(final TableColumn<PizzaDoce, String> param) {
            final TableCell<PizzaDoce, String> cell = new TableCell<PizzaDoce, String>() {

            	
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
                    		
                	//		String cmd = e.getSource().toString();
                	//		System.out.println(cmd);
                			
                        	PizzaDoce pd = getTableView().getItems().get(getIndex());
                        	
                            controle.apagar(pd);
                            controle.limpar();
                            controle.pesquisar();
                        });
                    	
                    	btnEditar.setOnAction(e -> {
            //    			String cmd = e.getSource().toString();
              //  			System.out.println(cmd);
                			
                        	PizzaDoce pd = getTableView().getItems().get(getIndex());
                            controle.setEntity(pd);
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
			
			
			
			tablePizzaDoce.getColumns().clear();
			tablePizzaDoce.getColumns().addAll(col1, col2, col3, col4, col5);
			
			tablePizzaDoce.setItems(controle.getListaPizzaDoce());
			
		}
		
		
		
			//metodo de vinculacao de TextFields e os atributos da pizza doce
		
		private void vincular() {
			
			//Metodo de conversao que transforma String em valor inteiro
			
			StringConverter<? extends Number> converterInteiro =
					new IntegerStringConverter();
			
			Bindings.bindBidirectional(TextId.textProperty(), controle.idProperty(),
					                  (StringConverter)converterInteiro);
			
			Bindings.bindBidirectional(controle.coberturaProperty(),
					                   TextCobertura.textProperty());
			
			Bindings.bindBidirectional(controle.bordaProperty(),
					                   TextBorda.textProperty());
			
			StringConverter<? extends Number> converterDouble =
					new DoubleStringConverter();
			
			Bindings.bindBidirectional(TextPreco.textProperty(), controle.preçoProperty(),
					                  (StringConverter)converterDouble);
			
			
			
		}

		
		public static void main(String[] args) {
			
			Application.launch(PizzaDoceBoundary.class, args);
		}
	}