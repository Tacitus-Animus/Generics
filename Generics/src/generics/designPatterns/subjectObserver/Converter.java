 package generics.designPatterns.subjectObserver;

import java.util.stream.IntStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Converter extends Application {
	
	private final static Currency[] currency = Currency.values();
	private final static CModel model = new CModel();
	private final static GridPane view = initView();
	private static Scene scene = new Scene(view);
	
	@Override
	public void init() throws Exception {			
		model.init(1D, 0.83D, 0.56D, 0.71D);	
	}

	private static GridPane initView() {
		final GridPane view = new GridPane();
		view.setHgap(10);
		view.setVgap(10);
		view.addRow(0, new Text("Currency"), new Text("Rate"), new Text("Value"));
		IntStream.range(0, currency.length).forEach(i -> {
			view.addRow(i + 1, new Text(currency[i].name()), new RateView(model, currency[i]), new ValueView(model, currency[i]));
		});
		view.getChildren().filtered(node -> node instanceof CView)
							.forEach(node -> node.setEffect(new InnerShadow()));
		return view;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(scene);
		primaryStage.setTitle("Currency Converter");
		primaryStage.show();
	}
	
	public static void main(String[] args) throws Exception {
		launch(args);
	}
	
}
 