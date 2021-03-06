package org.jboss.reddeer.swt.matcher;

import org.eclipse.swt.widgets.Widget;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

@SuppressWarnings("rawtypes")
public class ClassMatcher extends BaseMatcher {

	private Class<? extends Widget> c;
	
	public ClassMatcher(Class<? extends Widget> c) {
		this.c = c;
	}
	
	@Override
	public boolean matches(Object item) {
		return  item.getClass() == c;
	}

	@Override
	public void describeTo(Description description) {
		// TODO Auto-generated method stub
		
	}
	
	
}
