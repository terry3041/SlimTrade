package main.java.com.slimtrade.gui.options;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import main.java.com.slimtrade.core.References;
import main.java.com.slimtrade.gui.panels.ContainerPanel;

public class InformationPanel extends ContainerPanel {

	private static final long serialVersionUID = 1L;

	private final int buffer = 10;
	
	public InformationPanel(){
		this.setVisible(false);
		//TODO : Might need to change background color of text areas
//		super(false);
		
		JLabel versionLabel = new JLabel(References.APP_NAME + " v" + References.APP_VERSION);
		
		JLabel gitLabel = new JLabel("GitHub");
		JTextField gitText = new JTextField("https://github.com/zmilla93/SlimTrade");
		Dimension gitSize = gitText.getPreferredSize();
		gitSize.width++;
		gitText.setPreferredSize(gitSize);
		gitText.setBorder(null);
		gitText.setEditable(false);
		
		JLabel emailTitle = new JLabel("E-Mail");
		JTextField emailText = new JTextField("slimtradepoe@gmail.com");
		Dimension emailSize = emailText.getPreferredSize();
		emailSize.width++;
		emailText.setPreferredSize(emailSize);
		emailText.setBorder(null);
		emailText.setEditable(false);
		
		JLabel info1 = new JLabel("Bugs can be reported on github or via e-mail");
		JLabel info2 = new JLabel("Design/feature feedback can be sent via e-mail.");
		JLabel info3 = new JLabel("Please read the github post before sending feedback to avoid redundancy.");
		
		container.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.insets.bottom = buffer;
		
		gc.gridwidth = 2;
		container.add(versionLabel, gc);
		gc.gridwidth = 1;
		gc.gridx = 0;
		gc.gridy++;
		
		container.add(gitLabel, gc);
		gc.gridx = 1;
		container.add(gitText, gc);
		gc.gridx = 0;
		gc.gridy++;
		
		gc.insets.bottom = 0;
		container.add(emailTitle, gc);
		gc.gridx = 1;
		container.add(emailText, gc);
		gc.gridx = 0;
		gc.gridy++;

//		this.setBuffer(40, -1);
//		this.autoResize();
		
	}
	
	
}
