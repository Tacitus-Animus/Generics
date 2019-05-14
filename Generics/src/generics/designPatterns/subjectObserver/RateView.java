package generics.designPatterns.subjectObserver;

import javafx.scene.control.TextField;

public class RateView extends TextField implements CView {

	private final Currency currency;
	
	public RateView(final CModel model, final Currency currency) {
		this.currency = currency;
		
		this.setOnAction(event -> {
			try {
				double rate = Double.parseDouble(getText());
				model.setRate(this.currency, rate);
				setText(String.format("%10.6f", rate));
			}catch(NumberFormatException e) {}
		});
		
		model.addObserver(this);
	}
	
	/**
	 * Conditional update only if the Currency equals its own.
	 * Getting the rate could be error prone otherwise.
	 * During the init process. 
	 */
	@Override
	public void update(CModel subject, Currency metaData) {
		if(this.currency == metaData) {
			double rate = subject.getRate(currency);
			setText(String.format("%10.6f", rate));			
		}
	}
	
}
