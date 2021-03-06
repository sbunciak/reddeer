package org.jboss.reddeer.eclipse.ui.perspectives;

import org.apache.log4j.Logger;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.PlatformUI;
import org.jboss.reddeer.eclipse.exception.EclipseLayerException;
import org.jboss.reddeer.swt.api.Menu;
import org.jboss.reddeer.swt.impl.button.PushButton;
import org.jboss.reddeer.swt.impl.menu.ShellMenu;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.jboss.reddeer.swt.impl.table.DefaultTable;
import org.jboss.reddeer.swt.matcher.RegexMatchers;
import org.jboss.reddeer.swt.util.Bot;

/**
 * Abstract parent for each Perspective implementation
 * 
 * @author vlado pakan
 * 
 */
public abstract class AbstractPerspective {
  
  protected final Logger log = Logger.getLogger(this.getClass());
  
  private String perspectiveLabel;

  public AbstractPerspective(String perspectiveLabel) {
    super();
    this.perspectiveLabel = perspectiveLabel;
    if (!isPerspectiveAvailable()){
    	throw new EclipseLayerException("Perspective "+perspectiveLabel+" isn't available");
    }
  }

  public void open() {
    log.info("Open perspective: " + getPerspectiveLabel());
    if (Bot.get().activePerspective().getLabel().equals(getPerspectiveLabel())){
      log.debug("Perspective " + getPerspectiveLabel() + " is already opened.");
    }
    else{
      log.debug("Trying to open perspective: " + getPerspectiveLabel());
      RegexMatchers m = new RegexMatchers("Window.*", "Open Perspective.*",
          "Other...*");
      Menu menu = new ShellMenu(m.getMatchers());
      menu.select();
      new DefaultShell("Open Perspective");
      DefaultTable table = new DefaultTable();
      try{
        // Try to select perspective label within available perspectives
        table.select(getPerspectiveLabel());
      } catch (IllegalArgumentException iae){
        // Try to select perspective label within available perspectives with "(default)" suffix
        table.select(getPerspectiveLabel() + " (default)");
      }
      new PushButton("OK").click();
    }
  }

  public String getPerspectiveLabel() {
    return perspectiveLabel;
  }
	
	private boolean isPerspectiveAvailable(){
		IPerspectiveDescriptor perspective = PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithLabel(perspectiveLabel);
		if (perspective == null){
			return false;
		}
		return true;
	}
}
