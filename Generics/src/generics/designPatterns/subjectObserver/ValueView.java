package generics.designPatterns.subjectObserver;

import javafx.scene.control.TextField;

public class ValueView extends TextField implements CView {

	private final Currency currency;
	
	public ValueView(final CModel model, final Currency currency) {
		this.currency = currency;
		setOnAction(event -> {
			try {
				long value = Math.round(100.0 * Double.parseDouble(getText()));
				model.setValue(currency, value);
				setText(String.format("%15d.%02d", value/100, value%100));
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
		if(metaData == null || metaData == this.currency) {
		long value = subject.getValue(this.currency);
			setText(String.format("%15d.%02d", value/100, value%100));
		}
	}

}
