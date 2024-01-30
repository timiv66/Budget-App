package budget;
import java.lang.Math;
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
	private double wholeTotal;
	
	//categories for needs section
	private double foodAmt;
	private double houseAmt;
	private double tranAmt;
	private double insureAmt;
	private double utilAmt;
	private double childAmt;
	
	//categories for wants section
	private double travelAmt;
	private double eatoutAmt;
	private double subAmt;
	private double clothAmt;
	private double hobAmt;
	private double entAmt;
	
	//categories for savings section
	private double emeFundAmt;
	private double retireAmt;
	private double debtAmt;
	
	
	DropShadow shadow = new DropShadow();
	Font openFont = new Font("Impact",22);
	Font txtFont = new Font("Times New Roman",12);
	Font resultFont = new Font("Verdana",16);
	
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
		t.getWindow().setWidth(360);
		
		//needs title
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
		t.getWindow().setHeight(350);
		t.getWindow().setWidth(360);
		
		//wants title
		Label wantsTitle = new Label("Wants(30%)");
		wantsTitle.setFont(openFont);
		wantsTitle.setTranslateX(110);
		
		//travel
		Label travelLbl = new Label("Travel:");
		travelLbl.setFont(openFont);
	    travelLbl.setTranslateX(2);
	    travelLbl.setTranslateY(33);
		
		Label moneyLbl = new Label("$");
		moneyLbl.setFont(openFont);
		moneyLbl.setTranslateX(65);
		moneyLbl.setTranslateY(33);
		
		TextField travelTxtF = new TextField();
		travelTxtF.setTranslateX(80);
		travelTxtF.setTranslateY(36);
		
		//Eating Out
		Label eatoutLbl = new Label("Eating Out:");
		eatoutLbl.setFont(openFont);
		eatoutLbl.setTranslateX(2);
		eatoutLbl.setTranslateY(66);
		
		Label moneyLbl2 = new Label("$");
		moneyLbl2.setFont(openFont);
		moneyLbl2.setTranslateX(98);
		moneyLbl2.setTranslateY(66);
		
		TextField eatoutTxtF = new TextField();
		eatoutTxtF.setTranslateX(113);
		eatoutTxtF.setTranslateY(69);
		
		//Subscriptions
		Label subLbl = new Label("Subscriptions:");
		subLbl.setFont(openFont);
		subLbl.setTranslateX(2);
		subLbl.setTranslateY(99);
		
		Label moneyLbl3 = new Label("$");
		moneyLbl3.setFont(openFont);
		moneyLbl3.setTranslateX(135);
		moneyLbl3.setTranslateY(99);
		
		TextField subTxtF = new TextField();
		subTxtF.setTranslateX(150);
		subTxtF.setTranslateY(102);
		
		//Clothing
		Label clothLbl = new Label("Clothing:");
		clothLbl.setFont(openFont);
		clothLbl.setTranslateX(2);
		clothLbl.setTranslateY(132);
		
		Label moneyLbl4 = new Label("$");
		moneyLbl4.setFont(openFont);
		moneyLbl4.setTranslateX(85);
		moneyLbl4.setTranslateY(132);
		
		TextField clothTxtF = new TextField();
		clothTxtF.setTranslateX(100);
		clothTxtF.setTranslateY(135);
		
		//Hobbies
		Label hobLbl = new Label("Hobbies:");
		hobLbl.setFont(openFont);
		hobLbl.setTranslateX(2);
		hobLbl.setTranslateY(165);
		
		Label moneyLbl5 = new Label("$");
		moneyLbl5.setFont(openFont);
		moneyLbl5.setTranslateX(82);
		moneyLbl5.setTranslateY(165);
		
		TextField hobTxtF = new TextField();
		hobTxtF.setTranslateX(97);
		hobTxtF.setTranslateY(168);
		
		//Entertainment
		Label entLbl = new Label("Entertainment:");
		entLbl.setFont(openFont);
		entLbl.setTranslateX(2);
		entLbl.setTranslateY(198);
		
		Label moneyLbl6 = new Label("$");
		moneyLbl6.setFont(openFont);
		moneyLbl6.setTranslateX(136);
		moneyLbl6.setTranslateY(198);
		
		TextField entTxtF = new TextField();
		entTxtF.setTranslateX(151);
		entTxtF.setTranslateY(201);
		
		//error msg
		Text errorMsg = new Text("Please enter valid numbers and leave nothing blank");
		errorMsg.setFill(Color.RED);
		errorMsg.setFont(txtFont);
		errorMsg.setX(5);
		errorMsg.setY(250);
		errorMsg.setVisible(false);
		
		//Next Button
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
					travelAmt = Double.parseDouble(travelTxtF.getText());
				    eatoutAmt = Double.parseDouble(eatoutTxtF.getText());
				    subAmt = Double.parseDouble(subTxtF.getText());
					clothAmt = Double.parseDouble(clothTxtF.getText());
					hobAmt = Double.parseDouble(hobTxtF.getText());
					entAmt = Double.parseDouble(entTxtF.getText());
					wantsTotal = travelAmt+eatoutAmt+subAmt+clothAmt+hobAmt+entAmt;
					t.setRoot(savings(t));
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
	  				travelTxtF.setText("");
	  				eatoutTxtF.setText("");
	  				subTxtF.setText("");
	  				clothTxtF.setText("");
	  				hobTxtF.setText("");
	  				entTxtF.setText("");
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
	   
	   
		
		Pane wantsPane = new Pane();
		wantsPane.getChildren().addAll(wantsTitle,travelLbl,moneyLbl,travelTxtF,eatoutLbl,moneyLbl2,eatoutTxtF,subLbl,moneyLbl3,subTxtF,clothLbl,moneyLbl4,clothTxtF,
				hobLbl,moneyLbl5,hobTxtF,entLbl,moneyLbl6,entTxtF,errorMsg,nxtBtn,resetBtn);
		return wantsPane;
	}
	
	public Pane savings(Scene t) {
		t.getWindow().setHeight(240);
		t.getWindow().setWidth(360);
		
		//Savings Title
		Label savingsLbl = new Label("Savings(20%)");
		savingsLbl.setFont(openFont);
		savingsLbl.setTranslateX(110);
		
		//Emergency Fund
		Label emeFundLbl = new Label("Emergency Fund:");
		emeFundLbl.setFont(openFont);
	    emeFundLbl.setTranslateX(2);
	    emeFundLbl.setTranslateY(33);
	   
	    Label moneyLbl = new Label("$");
		moneyLbl.setFont(openFont);
		moneyLbl.setTranslateX(155);
		moneyLbl.setTranslateY(33);
		
		TextField emeFundTxtF = new TextField();
		emeFundTxtF.setTranslateX(170);
		emeFundTxtF.setTranslateY(36);
		
		//Retirement
		Label retireLbl = new Label("Retirement:");
		retireLbl.setFont(openFont);
		retireLbl.setTranslateX(2);
		retireLbl.setTranslateY(66);
		
		Label moneyLbl2 = new Label("$");
		moneyLbl2.setFont(openFont);
		moneyLbl2.setTranslateX(110);
		moneyLbl2.setTranslateY(66);
		
		TextField retireTxtF = new TextField();
		retireTxtF.setTranslateX(125);
		retireTxtF.setTranslateY(69);
		
		//Debt Payments
		Label debtLbl = new Label("Debt Payments:");
		debtLbl.setFont(openFont);
		debtLbl.setTranslateX(2);
		debtLbl.setTranslateY(99);
		
		Label moneyLbl3 = new Label("$");
		moneyLbl3.setFont(openFont);
		moneyLbl3.setTranslateX(143);
		moneyLbl3.setTranslateY(99);
		
		TextField debtTxtF = new TextField();
		debtTxtF.setTranslateX(158);
		debtTxtF.setTranslateY(102);
		
		//Error message
		Text errorMsg = new Text("Please enter valid numbers and leave nothing blank");
		errorMsg.setFill(Color.RED);
		errorMsg.setFont(txtFont);
		errorMsg.setX(5);
		errorMsg.setY(145);
		errorMsg.setVisible(false);
		
		//Next Button
		Button nxtBtn = new Button("Next");
		nxtBtn.setTranslateX(300);
		nxtBtn.setTranslateY(163);
		
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
					emeFundAmt = Double.parseDouble(emeFundTxtF.getText());
					retireAmt = Double.parseDouble(retireTxtF.getText());
					debtAmt = Double.parseDouble(debtTxtF.getText());
					savingsTotal = emeFundAmt+retireAmt+debtAmt;
					wholeTotal = needsTotal+wantsTotal+savingsTotal;
					t.setRoot(results(t));
				}catch(NumberFormatException e) {
					errorMsg.setVisible(true);
				}
			}
	    });
		
		//reset button
 		Button resetBtn = new Button("Reset");
 		resetBtn.setTranslateX(3);
 		resetBtn.setTranslateY(163);
		
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
	    resetBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				emeFundTxtF.setText("");
				retireTxtF.setText("");
				debtTxtF.setText("");
				errorMsg.setVisible(false);
			}
	    });
 		
		Pane savePane = new Pane();
		savePane.getChildren().addAll(savingsLbl,emeFundLbl,moneyLbl,emeFundTxtF,retireLbl,moneyLbl2,
				retireTxtF,debtLbl,moneyLbl3,debtTxtF,errorMsg,nxtBtn,resetBtn);
		return savePane;
	}
	
	public Pane results(Scene t) {
		t.getWindow().setHeight(240);
		t.getWindow().setWidth(530);
		
		//Title
		Label resultLbl = new Label("Results");
		resultLbl.setFont(openFont);
		resultLbl.setTranslateX(200);
		
		//Going over or under monthly income
		Text overAmtTxt = new Text("");
		overAmtTxt.setFont(resultFont);
		overAmtTxt.setY(50);
		overAmtTxt.setVisible(false);
		 
		Text underAmtTxt = new Text("");
		underAmtTxt.setFont(resultFont);
		underAmtTxt.setY(50);
		underAmtTxt.setVisible(false);
		
		if (monthlyIncome < wholeTotal) {
			double amtOver;
			amtOver = monthlyIncome - wholeTotal;
			overAmtTxt.setText("You spent $" + String.format("%.2f", Math.abs(amtOver)) + " over your monthly income this month");
			overAmtTxt.setVisible(true);
		}
		else if(monthlyIncome > wholeTotal) {
			double amtUnder;
			amtUnder = monthlyIncome - wholeTotal;
			underAmtTxt.setText("You did not use $" + String.format("%.2f", amtUnder) + " of your monthly income this month");
			underAmtTxt.setVisible(true);
		}
		
		//Needs Percentage
		double needsPercent = (needsTotal/wholeTotal) * 100.0;
		
		Text needPercText = new Text(String.format("%.2f", needsPercent) + "% of your monthly income was spent your needs");
		needPercText.setFont(resultFont);
		needPercText.setY(80);
		
		//Want Percentage
		double wantsPercent = (wantsTotal/wholeTotal) * 100.0;
		
		Text wantsPercText = new Text(String.format("%.2f", wantsPercent) + "% of your monthly income was spent your wants");
		wantsPercText.setFont(resultFont);
		wantsPercText.setY(110);
		
		//Savings Percentage
		double savingsPercent = (savingsTotal/wholeTotal) * 100.0;
		
		Text savingsPercText = new Text(String.format("%.2f", savingsPercent) + "% of your monthly income was spent your wants");
		savingsPercText.setFont(resultFont);
		savingsPercText.setY(140);
		
		Pane resultPane = new Pane();
		resultPane.getChildren().addAll(resultLbl,overAmtTxt,underAmtTxt,needPercText,wantsPercText,savingsPercText);
		return resultPane;
	}
	
	
}
