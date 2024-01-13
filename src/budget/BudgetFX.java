package budget;

import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class BudgetFX extends Application{
	
	private double monthlyIncome;
	private double needsTotal;
	private double wantsTotal;
	private double savingsTotal;
	
	//categories for needs section
	private double foodAmt;
	private double houseAmt;
	private double tranAmt;
	private double insureAmt;
	private double utilAmt;
	private double childAmt;
	
	DropShadow shadow = new DropShadow();
	Font openFont = new Font("Impact",22);
	Font txtFont = new Font("Times New Roman",12);
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage mainStage) throws Exception {
		//Setting the scene
		Pane p1 = new Pane();
		Scene t = new Scene(p1,500,400);
		t.setRoot(openScreen(t));
		mainStage.setTitle("Budget App");
		Image icon = new Image("https://cdn-icons-png.flaticon.com/512/781/781831.png");
		mainStage.getIcons().add(icon);
		mainStage.setScene(t);
		mainStage.show();	
	}
	
	public Pane openScreen(Scene t) {
		Text welcomeTxt = new Text("Welcome to 50/30/20 Budget Calculator");
		welcomeTxt.setTextAlignment(TextAlignment.CENTER);
		welcomeTxt.setX(65);
		welcomeTxt.setY(100);
		welcomeTxt.setFont(openFont);
		
		Text adviceTxt = new Text("Start making smart financial choices today!!!");
		adviceTxt.setFont(txtFont);
		adviceTxt.setTextAlignment(TextAlignment.CENTER);
		adviceTxt.setX(135);
		adviceTxt.setY(130);
		
		//Image of Mr.Krabs
		ImageView openImg = new ImageView("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYrKd0OAa5EUAaO_szXyjoq-p2_YpwOa4GFg&usqp=CAU");
		openImg.setX(95);
		openImg.setY(150);
		
		Button nxtBtn1 = new Button("Next");
		
		nxtBtn1.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				t.setRoot(income(t));
			}
		});
		nxtBtn1.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	            nxtBtn1.setEffect(shadow);
	          }
	        });
	    nxtBtn1.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	            nxtBtn1.setEffect(null);
	          }
	        });
	    
	    VBox vb1 = new VBox(20);
	    vb1.setAlignment(Pos.CENTER);
	    vb1.getChildren().addAll(welcomeTxt,adviceTxt,openImg,nxtBtn1);
	    return vb1;
	}
	
	public Pane income(Scene t) {
		//only numbers accepted in txt fields
		UnaryOperator<TextFormatter.Change> filter = change -> {
	        String text = change.getText();
	        // Check if the new text is a number or empty
	        if (text.matches("[0-9]*") || text.isEmpty()) {
	            return change;
	        }
	        // If not a number or empty, reject the change
	        return null;
	    };
	    TextFormatter<Integer> textFormatter1 = new TextFormatter<>(new IntegerStringConverter(), null, filter);
		
		t.getWindow().setHeight(145);
		t.getWindow().setWidth(360);
		
		Text instructTxt = new Text("Please input your monthly income after taxes");
		instructTxt.setFont(txtFont);
		instructTxt.setX(5);
		instructTxt.setY(15);
		
		Label monthlyIncLbl = new Label("Monthly Income:");
		monthlyIncLbl.setTranslateX(3);
		monthlyIncLbl.setTranslateY(20);
		monthlyIncLbl.setFont(openFont);
		
		Label moneyLbl = new Label("$");
		moneyLbl.setTranslateX(161);
		moneyLbl.setTranslateY(21);
		moneyLbl.setFont(openFont);
		
		TextField incTxtF = new TextField();
		incTxtF.setTranslateX(175);
		incTxtF.setTranslateY(24);
		incTxtF.setTextFormatter(textFormatter1);
		
		Text errorMsg = new Text("");
		errorMsg.setFill(Color.RED);
		errorMsg.setFont(txtFont);
		errorMsg.setX(5);
		errorMsg.setY(60);
		errorMsg.setVisible(false);
		
        Button nxtBtn2 = new Button("Next");
        nxtBtn2.setTranslateX(300);
        nxtBtn2.setTranslateY(78);
        
		//Next button actions
		nxtBtn2.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
			  if (incTxtF.getText().isBlank()) {
				errorMsg.setText("Please enter a valid number");
			  	errorMsg.setVisible(true);
			  }
			  else{
				monthlyIncome = Double.parseDouble(incTxtF.getText());
				t.setRoot(needs(t));
			  }
			}
		});
		
		nxtBtn2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	            nxtBtn2.setEffect(shadow);
	          }
	        });
	    nxtBtn2.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	            nxtBtn2.setEffect(null);
	          }
	        });
	    
	    Button resetBtn = new Button("Reset");
	    resetBtn.setTranslateX(2);
	    resetBtn.setTranslateY(78);
	    
	    resetBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
			  monthlyIncome = 0;
			  t.setRoot(openScreen(t));
			  t.getWindow().setWidth(500);
			  t.getWindow().setHeight(400);
			}
		});
	    resetBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	           resetBtn.setEffect(shadow);
	          }
	        });
	    resetBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	            resetBtn.setEffect(null);
	          }
	        });
	    
		Pane incomePane = new Pane();
		incomePane.getChildren().addAll(monthlyIncLbl,instructTxt,moneyLbl,incTxtF,errorMsg,nxtBtn2,resetBtn);
		return incomePane;
	}
	
	public Pane needs(Scene t) {
		t.getWindow().setHeight(350);
		
		//title
		Label needsTitle = new Label("Needs(50%)");
		needsTitle.setFont(openFont);
		needsTitle.setTranslateX(110);
		
		//Food
		Label foodLbl = new Label("Food:");
		foodLbl.setFont(openFont);
	    foodLbl.setTranslateX(2);
	    foodLbl.setTranslateY(33);
		
		Label moneyLbl = new Label("$");
		moneyLbl.setFont(openFont);
		moneyLbl.setTranslateX(50);
		moneyLbl.setTranslateY(33);
		
		TextField foodTxtF = new TextField();
		foodTxtF.setTranslateX(65);
		foodTxtF.setTranslateY(36);
		
		//Housing
		Label housingLbl = new Label("Housing:");
		housingLbl.setFont(openFont);
		housingLbl.setTranslateX(2);
		housingLbl.setTranslateY(66);
		
		Label moneyLbl2 = new Label("$");
		moneyLbl2.setFont(openFont);
		moneyLbl2.setTranslateX(82);
		moneyLbl2.setTranslateY(66);
		
		TextField housingTxtF = new TextField();
		housingTxtF.setTranslateX(97);
		housingTxtF.setTranslateY(69);

		//Transportation
		Label tranLbl = new Label("Transportation:");
		tranLbl.setFont(openFont);
		tranLbl.setTranslateX(2);
		tranLbl.setTranslateY(99);
		
		Label moneyLbl3 = new Label("$");
		moneyLbl3.setFont(openFont);
		moneyLbl3.setTranslateX(142);
		moneyLbl3.setTranslateY(99);
		
		TextField tranTxtF = new TextField();
		tranTxtF.setTranslateX(157);
		tranTxtF.setTranslateY(102);
		
		//Insurance
		Label insLbl = new Label("Insurance:");
		insLbl.setFont(openFont);
		insLbl.setTranslateX(2);
		insLbl.setTranslateY(132);
		
		Label moneyLbl4 = new Label("$");
		moneyLbl4.setFont(openFont);
		moneyLbl4.setTranslateX(100);
		moneyLbl4.setTranslateY(132);
		
		TextField insTxtF = new TextField();
		insTxtF.setTranslateX(115);
		insTxtF.setTranslateY(135);
		
		//Basic utilities
		Label utiLbl = new Label("Basic utilities:");
		utiLbl.setFont(openFont);
		utiLbl.setTranslateX(2);
		utiLbl.setTranslateY(165);
		
		Label moneyLbl5 = new Label("$");
		moneyLbl5.setFont(openFont);
		moneyLbl5.setTranslateX(132);
		moneyLbl5.setTranslateY(165);
		
		TextField utiTxtF = new TextField();
		utiTxtF.setTranslateX(147);
		utiTxtF.setTranslateY(168);
		
		//Child Care
		Label childLbl = new Label("Child Care:");
		childLbl.setFont(openFont);
		childLbl.setTranslateX(2);
		childLbl.setTranslateY(198);
		
		Label moneyLbl6 = new Label("$");
		moneyLbl6.setFont(openFont);
		moneyLbl6.setTranslateX(100);
		moneyLbl6.setTranslateY(198);
		
		TextField childTxtF = new TextField();
		childTxtF.setTranslateX(115);
		childTxtF.setTranslateY(201);
		
		//error msg
		Text errorMsg = new Text("Please enter valid numbers and leave nothing blank");
		errorMsg.setFill(Color.RED);
		errorMsg.setFont(txtFont);
		errorMsg.setX(5);
		errorMsg.setY(250);
		errorMsg.setVisible(false);
		
		//next button
		Button nxtBtn = new Button("Next");
		nxtBtn.setTranslateX(300);
		nxtBtn.setTranslateY(283);
		
		nxtBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	            nxtBtn.setEffect(shadow);
	          }
	        });
	    nxtBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	            nxtBtn.setEffect(null);
	          }
	        });
	    
	   nxtBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					//adding all txt field numbers 
					foodAmt = Double.parseDouble(foodTxtF.getText());
					houseAmt = Double.parseDouble(housingTxtF.getText());
					tranAmt = Double.parseDouble(tranTxtF.getText());
					insureAmt = Double.parseDouble(insTxtF.getText());
					utilAmt = Double.parseDouble(utiTxtF.getText());
					childAmt = Double.parseDouble(childTxtF.getText());
					needsTotal=foodAmt+houseAmt+tranAmt+insureAmt+utilAmt+childAmt;
					t.setRoot(wants(t));
				}catch(NumberFormatException e) {
					errorMsg.setVisible(true);
				}
			}
		});
	    
		//reset button
		Button resetBtn = new Button("Reset");
		resetBtn.setTranslateX(3);
		resetBtn.setTranslateY(283);
		
		resetBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				foodTxtF.setText("");
				housingTxtF.setText("");
				tranTxtF.setText("");
				insTxtF.setText("");
				utiTxtF.setText("");
				childTxtF.setText("");
			}
		});
		
		resetBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	           resetBtn.setEffect(shadow);
	          }
	        });
	    resetBtn.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent e) {
	            resetBtn.setEffect(null);
	          }
	        });
		
		Pane needsPane = new Pane();
		needsPane.getChildren().addAll(needsTitle,foodLbl,moneyLbl,foodTxtF,housingLbl,moneyLbl2,housingTxtF,
				tranLbl,moneyLbl3,tranTxtF,insLbl,moneyLbl4,insTxtF,utiLbl,moneyLbl5,utiTxtF,childLbl,moneyLbl6,childTxtF,
				errorMsg,nxtBtn,resetBtn);
		return needsPane;
	}
	
	public Pane wants(Scene t) {
		
		Pane wantsPane = new Pane();
		
		return wantsPane;
	}

}
