package org.jboss.reddeer.eclipse.jdt.ui.ide;

import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.jboss.reddeer.eclipse.jface.wizard.NewWizardDialog;
import org.jboss.reddeer.swt.condition.JobIsRunning;
import org.jboss.reddeer.swt.condition.ShellWithTextIsActive;
import org.jboss.reddeer.swt.impl.button.PushButton;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.jboss.reddeer.swt.wait.TimePeriod;
import org.jboss.reddeer.swt.wait.WaitUntil;
import org.jboss.reddeer.swt.wait.WaitWhile;

public class NewJavaProjectWizardDialog extends NewWizardDialog{
	
	public NewJavaProjectWizardDialog() {
		super("Java", "Java Project");
	}
	
	@Override
	public NewJavaProjectWizardPage getFirstPage() {
		return new NewJavaProjectWizardPage(this);
	}
	
	@Override
	public void finish(){
		finish(false);
	}
	
	public void finish(boolean openAssociatedPerspective) {
		log.debug("Finish wizard dialog");
		new PushButton("Finish").click();
		try {
			new WaitUntil(new ShellWithTextIsActive("Open Associated Perspective?"));
			DefaultShell shell = new DefaultShell();
			if (new DefaultShell().getText().equals("Open Associated Perspective?")) {
				if (openAssociatedPerspective) {
					new PushButton("Yes").click();
				} else {
					new PushButton("No").click();
				}
				new WaitWhile(new ShellWithTextIsActive(shell.getText()), TimePeriod.LONG);
			}
		} catch (TimeoutException te) {
			log.info("Shell 'Open Associated Perspective' wasn't shown");
		}
		new WaitWhile(new JobIsRunning(), TimePeriod.LONG);
	}

}
