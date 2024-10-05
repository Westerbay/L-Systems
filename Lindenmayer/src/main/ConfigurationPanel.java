package main;

import system.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

/**
 * Configuration Panel for L-Systems
 * @author Wester
 */
public class ConfigurationPanel extends JPanel implements ChangeListener, ActionListener{
    
    private final JSlider _slider = new JSlider(0,6,0);
    
    private final JSpinner _spinnerNumber = new JSpinner(new SpinnerNumberModel(5,0,40,1));
    private final JSpinner _spinnerLength = new JSpinner(new SpinnerNumberModel(2.0,0,800,1.0));
    private final JSpinner _spinnerAngle = new JSpinner(new SpinnerNumberModel(90.0,-360,360,1.0));
    
    private final JTextField _axiomField = new JTextField(17);
    private final JTextArea _rulesField = new JTextArea(11,17);    
    private final JButton _generateButton = new JButton("Draw"); 
       
    private final TurtlePanel _turtlePanel; 
    private JScrollPane _scrollPaneAxiom;
    private JScrollPane _scrollPaneRules;

    public ConfigurationPanel(TurtlePanel turtlePanel){
		
		_turtlePanel = turtlePanel;
        setLayout(null);
        setLabel();
        setSlider();
        setSpinner();
        setConstructor();
        setFract();
        
    }
    
    
    public void add(JComponent... components) {
    	for (JComponent component: components) {
    		add(component);
    	}
    }
    
    
    public void setSlider(){
        _slider.setMinimum(0);
        _slider.setMaximum(6);
        _slider.setMajorTickSpacing(1);
        _slider.setBounds(50, 120, 250,50);
        _slider.setPaintTicks(true);
        _slider.setPaintLabels(true);
        _slider.addChangeListener(this);        
        _slider.setValue(Interface.INDEX);
        add(_slider);
    }

    public void setLabel(){
    
    	Font font1 = new Font("Serif", Font.PLAIN, 30);
    	Font font2 = new Font("Serif", Font.PLAIN, 25);
    
        JLabel presetLabel = new JLabel("Presets");
        presetLabel.setFont(font1);
        presetLabel.setBounds(120, 30, 250, 50);
        
        JLabel numberLabel = new JLabel("n:");
        numberLabel.setFont(font2);
        numberLabel.setBounds(30, 200, 50, 50);
        
        JLabel lenghtLabel = new JLabel("l:");
		lenghtLabel.setBounds(140, 200, 50, 50);
        lenghtLabel.setFont(font2);
        
        JLabel angleLabel = new JLabel("δ:");
		angleLabel.setBounds(240, 200, 50, 50);
        angleLabel.setFont(font2);        
        
        JLabel axiomLabel = new JLabel("Axiom:");
		axiomLabel.setBounds(30, 280, 200, 50);
        axiomLabel.setFont(font1);
        
        JLabel rulesLabel = new JLabel("Rules:");
        rulesLabel.setFont(font1);
		rulesLabel.setBounds(30, 390, 200, 50);
        
        add(presetLabel, numberLabel, lenghtLabel, angleLabel,
        	axiomLabel, rulesLabel);
        	
    }
    
    public void setSpinner() {
    
        _spinnerNumber.setBounds(60, 213, 50, 30);
        _spinnerNumber.addChangeListener(this);
        
        _spinnerLength.setBounds(165, 213, 50, 30);
        _spinnerLength.addChangeListener(this);
        
        _spinnerAngle.setBounds(270, 213, 50, 30);
        _spinnerAngle.addChangeListener(this);
	

        add(_spinnerNumber, _spinnerLength, _spinnerAngle);
        
    }
    
    public void setConstructor() {
 
    	Font font = new Font("Serif", Font.PLAIN, 20);
    	
        _axiomField.setFont(font);
        _axiomField.setPreferredSize(new Dimension(60,25));
        _scrollPaneAxiom = new JScrollPane(
        	_axiomField, 
        	ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, 
        	ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
		_scrollPaneAxiom.setBounds(30, 340, 300, 40);
        
        _rulesField.setFont(font);
        _rulesField.setLineWrap(true);
        _scrollPaneRules = new JScrollPane(
        	_rulesField,
        	ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        	ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
		_scrollPaneRules.setBounds(30, 450, 300, 210);
        
        _generateButton.setPreferredSize(new Dimension(150,30));
        _generateButton.addActionListener(this);
		_generateButton.setBounds(125, 680, 100, 30);
		
        add(_scrollPaneAxiom, _scrollPaneRules, _generateButton);
        
    }

 
    public void setFract(){
        SystemL creaFract = Fractals.systemPresets[Interface.INDEX];
        _axiomField.setText(creaFract.getGeneration(0));
        _rulesField.setText(creaFract.getRules());
    }

  
    @Override
    public void stateChanged(ChangeEvent e){
    
		if (e.getSource() == _slider) {
      
		    int index = _slider.getValue();
		    int[] options = Fractals.optionPresets[index];
		      
		    SystemL creaFract = Fractals.systemPresets[index];
		    _turtlePanel.interprete(creaFract, options);
		    _axiomField.setText(creaFract.getGeneration(0));
		    _rulesField.setText(creaFract.getRules());

		    _spinnerNumber.removeChangeListener(this);
		    _spinnerLength.removeChangeListener(this);
		    _spinnerAngle.removeChangeListener(this);

		    _spinnerNumber.setValue(options[0]);
		    _spinnerLength.setValue((double) options[1]);
		    _spinnerAngle.setValue(creaFract.getAngle());

		    _spinnerNumber.addChangeListener(this);
		    _spinnerLength.addChangeListener(this);
		    _spinnerAngle.addChangeListener(this);
		    
		    SwingUtilities.invokeLater(() -> {
                _scrollPaneRules.getVerticalScrollBar().setValue(0);
            });
		    
		    
		}
		        
		if (e.getSource() == _spinnerNumber) {
		    int value = (int) _spinnerNumber.getValue();
		    _turtlePanel.setGen(value);
		}
		    
		if (e.getSource() == _spinnerLength) {
		    double value = (double) _spinnerLength.getValue();
		    _turtlePanel.setLength(value);
		}
		    
		if (e.getSource() == _spinnerAngle) {
		    double value = (double) _spinnerAngle.getValue();
		    _turtlePanel.setAngle(value);
		}	   
          
    }
   
   
    @Override
    public void actionPerformed(ActionEvent e){
    
        if (e.getSource() == _generateButton) {
        
            String sendAxiom = _axiomField.getText();
            String sendRules = _rulesField.getText();
            String[] sendRulesFormat = sendRules.split("\n");
            
            double sendAngle = (double) _spinnerAngle.getValue();
            int sendGen = (int) _spinnerNumber.getValue();
            double sendLength = (double) _spinnerLength.getValue();
            ConstructSystem creator = new ConstructSystem();
            SystemL system = null;
            
            try {
                system = creator.createSystem(
                	sendAngle, sendAxiom, sendRulesFormat
                );
                if (system != null) {
                	_turtlePanel.interprete(
                		system, sendGen, sendLength
                	);
                }
		        else {
		        	JFrame frame = new JFrame();
		        	JOptionPane.showMessageDialog(frame, "Unrecognized system, Only Simple/Stochastic/ContextSensitive available ! ");
		        }
		        
            } catch(Exception ignored){
            	JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Syntax Error ! ");
            }
                        
        }

    }

    


    

}
