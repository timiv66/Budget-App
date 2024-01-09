package budget;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class BudgetFX extends Application{
	
	private double monthlyIncome;
	private double wants;
	private double needs;
	private double savings;
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage mainStage) throws Exception {
		Pane p1 = new Pane();
		Scene t = new Scene(p1,500,400);
		
		mainStage.setTitle("Budget App");
		
		Image icon = new Image("https://cdn-icons-png.flaticon.com/512/781/781831.png");
		mainStage.getIcons().add(icon);
		
		Font openFont = new Font("Impact",22);
		Font txtFont = new Font("Times New Roman",12);
		
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
		
		ImageView openImg = new ImageView("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYrKd0OAa5EUAaO_szXyjoq-p2_YpwOa4GFg&usqp=CAU");
		openImg.setX(95);
		openImg.setY(150);
		
		Button nxtBtn = new Button("Next");
		nxtBtn.setTranslateX(220);
		nxtBtn.setTranslateY(340);
		
		nxtBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				t.setRoot(income(t));
			}
		});

		p1.getChildren().addAll(welcomeTxt,adviceTxt,openImg,nxtBtn);
		
		
		mainStage.setScene(t);
		mainStage.show();
	}
	
	public Pane income(Scene t) {
		
		
		Pane incomePane = new Pane();
		return incomePane;
	}

}
