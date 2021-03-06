package org.jboss.reddeer.uiforms.lookup;

import org.eclipse.ui.forms.widgets.Hyperlink;
import org.hamcrest.Matcher;
import org.jboss.reddeer.swt.lookup.impl.WidgetLookup;

/**
 * UIFormHyperlink Lookup containing lookup routines for Eclipse Forms Hyperlink widget type
 * @author jjankovi
 * 
 */
public class UIFormHyperlinkLookup {

	private static UIFormHyperlinkLookup instance = null;

	private UIFormHyperlinkLookup() {
	}

	/**
	 * Creates and returns instance of UIFormHyperlink Lookup
	 * 
	 * @return UIFormHyperlinkLookup instance
	 */
	public static UIFormHyperlinkLookup getInstance() {
		if (instance == null)
			instance = new UIFormHyperlinkLookup();
		return instance;
	}
	
	/**
	 * Return Eclipse Forms Hyperlink instance
	 * 
	 * @param matcher
	 * @return Hyperlink Widget matching criteria
	 */
	@SuppressWarnings({ "rawtypes" })
	public Hyperlink getHyperlink(int index, Matcher... matchers) {
		return (Hyperlink)WidgetLookup.getInstance().activeWidget(Hyperlink.class, index, matchers);
	}
	
}
