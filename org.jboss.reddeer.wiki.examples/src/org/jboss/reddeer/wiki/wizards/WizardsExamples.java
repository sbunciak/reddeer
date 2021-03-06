package org.jboss.reddeer.wiki.wizards;

import org.jboss.reddeer.eclipse.jdt.ui.NewJavaClassWizardDialog;
import org.jboss.reddeer.eclipse.jdt.ui.NewJavaClassWizardPage;

public class WizardsExamples {

	public static void main(String[] args) {
		
	}
	
	public void javaWizard(){
		NewJavaClassWizardDialog javaClassWizardDialog = new NewJavaClassWizardDialog();
		javaClassWizardDialog.open();
		
		NewJavaClassWizardPage wizardPage = javaClassWizardDialog.getFirstPage();
		wizardPage.setName("MyClass");
		wizardPage.setPackage("org.reddeer.example");
		
		javaClassWizardDialog.finish();
	}
}
