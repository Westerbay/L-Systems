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
    
    private GridBagConstraints gbc = new GridBagConstraints();
    private JSlider slide = new JSlider(0,6,0);
    private Font police = new Font("Serif",Font.PLAIN,30);
    private Font police2 = new Font("Serif",Font.PLAIN,25);
    private Font police3 = new Font("Serif",Font.PLAIN,20);
    private JSpinner spinnerN = new JSpinner(new SpinnerNumberModel(5,0,40,1));
    private JSpinner spinnerL = new JSpinner(new SpinnerNumberModel(2.0,0,800,1.0));
    private JSpinner spinnerδ = new JSpinner(new SpinnerNumberModel(90.0,-360,360,1.0));
    private JButton generateur = new JButton("Draw");
    private JTextField axiomEcrit = new JTextField(17);
    private JTextArea rulesEcrit = new JTextArea(11,17);
    private TurtlePanel turtlePanel; 

    public ConfigurationPanel(TurtlePanel turtlePanel){
		
		this.turtlePanel = turtlePanel;
        this.setLayout(null);
        texte1();
        slider1();
        texteN();
        insertion1();
        texteL();
        insertion2();
        textδ();
        insertion3();
        label_axiom();
        constructeur_axiom();
        label_rules();
        constructeur_rules();
        button_generateur();
        setFract();
        slide.setValue(Interface.INDEX);
    }

    /**
     * Fonction qui creé un label simple et le genere au Jpanel
	 * En plus d'initiliser les premieres valeurs du gribagConstraints
     */
    public void texte1(){
        
       
        gbc.insets = new Insets(20,0,0,0);
       
        
        gbc.gridx = 0; //sur un le layout cela permet de le decalé de droite à gauche  si = 0 alors au centre si = 1 alors à droite;
        gbc.gridy = 0; //sur un le layout cela permet de le decalé de haut en bas si =1 tout en haut si =2 au centre si = 3 tout en bas du layout;
        JLabel affichagePreset = new JLabel("Presets");
        affichagePreset.setFont(this.police);
        gbc.anchor = GridBagConstraints.PAGE_START;
	affichagePreset.setBounds(120, 30, 250, 50);
        this.add(affichagePreset,gbc);
    }

    /**
     * Fonction qui creé un le JSlider et le genere au Jpanel
	 * En plus d'initiliser les premieres valeurs du gribagConstraints
     */
    public void slider1(){

        gbc.insets = new Insets(70,0,0,0);//permet de creer l'espace entre les elements haut - bas
        slide.setMinimum(0);
        slide.setMaximum(6);
        slide.setValue(0);
        slide.setMajorTickSpacing(1);
        slide.setBounds(50, 120, 250,50);
        slide.setPaintTicks(true);
        slide.setPaintLabels(true);
        slide.addChangeListener(this);
        
        this.add(slide,gbc);
    }

    /**
     * Fonction qui creé un Jlabel simple et le genere au Jpanel
     */
    public void texteN(){
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(147,15,0,0);
        
        gbc.weightx=1;
        
        JLabel n = new JLabel("n:");
        n.setFont(this.police2);
	n.setBounds(30, 200, 50, 50);
        this.add(n,gbc);
    }

    /**
     * Fonction qui creé un Jspinner et le genere au Jpanel
     */
    public void insertion1(){
        gbc.insets = new Insets(150,45,0,0);
        gbc.weightx=0;
        
        spinnerN.setPreferredSize(new Dimension(60,25));//sans cette fonction le JSpinner ne marche pas dans le Layout;
        spinnerN.setBounds(60, 213, 50, 30);
        spinnerN.addChangeListener(this);

        this.add(spinnerN,gbc);
    }

    /**
     * Fonction qui creé un Jlabel simple et le genere au Jpanel
     */
    public void texteL(){
        gbc.gridx =1;
        gbc.gridy =0;
        gbc.insets = new Insets(147,-220,0,0);
        
        JLabel l = new JLabel("l:");
	l.setBounds(140, 200, 50, 50);
        l.setFont(this.police2);
        this.add(l,gbc);
    }

    /**
     * Fonction qui creé un Jspinner et le genere au Jpanel
     */
    public void insertion2(){
        gbc.insets = new Insets(150,-195,0,0);
        
        
        spinnerL.setPreferredSize(new Dimension(60,25));
        spinnerL.addChangeListener(this);
	spinnerL.setBounds(165, 213, 50, 30);
        this.add(spinnerL,gbc);
    }

    /**
     * Fonction qui creé un Jlabel simple et le genere au Jpanel
     */
    public void textδ(){
        gbc.insets = new Insets(147,-110,0,0);
        gbc.gridx = 2;
        gbc.gridy = 0;
        
        JLabel sigmaδ = new JLabel("δ:");
	sigmaδ.setBounds(240, 200, 50, 50);
        sigmaδ.setFont(this.police2);
        this.add(sigmaδ,gbc);
    }

    /**
     * Fonction qui creé un Jspinner et le genere au Jpanel
     */
    public void insertion3(){
        gbc.weighty = 0.1;//primordial car autrement tout les elements seront rassemble au centre de l'ecran
        gbc.insets = new Insets(150,-80,0,0);
        
        spinnerδ.setPreferredSize(new Dimension(60,25));
        spinnerδ.addChangeListener(this);
	spinnerδ.setBounds(270, 213, 50, 30);
        this.add(spinnerδ,gbc);
    }

    /**
     * Fonction qui creé un Jspinner et le genere au Jpanel
     */
    public void label_axiom(){
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(-135,18,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        
        JLabel axiom = new JLabel("Axiom:");
	axiom.setBounds(30, 280, 200, 50);
        axiom.setFont(this.police);
        this.add(axiom,gbc);
    }

    /**
     * Fonction qui creé un JtextArea et le genere au Jpanel
     * En plus de creer un JScrollPane qui permet l'implementation des barres de deplacement
     */
    public void constructeur_axiom(){
        gbc.insets = new Insets(-95,23,10,0);
        
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        
        axiomEcrit.setFont(this.police3);
        axiomEcrit.setPreferredSize(new Dimension(60,25));
        int verti = ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER;
        int hori = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
        JScrollPane extension = new JScrollPane(axiomEcrit,verti,hori);//Permet de mettre des bars de deplacement
	extension.setBounds(30, 340, 300, 40);
        this.add(extension,gbc);

        //La Scrollbar n'apparait pas c'est à regler
    }

    /**
     * Fonction qui creé un Jspinner et le genere au Jpanel
     */
    public void label_rules(){
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(-35,23,10,0);
        gbc.gridy=1;
        gbc.gridx=0;
        
        JLabel rules = new JLabel("Rules:");
        rules.setFont(this.police);
	rules.setBounds(30, 390, 200, 50);
        this.add(rules,gbc);
    }

    /**
     * Fonction qui creé un JtextArea et le genere au Jpanel
     * En plus de creer un JScrollPane qui permet l'implementation des barres de deplacement
     */
    public void constructeur_rules(){
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(5,23,10,0);
        //gbc.weightx=1;
        
        rulesEcrit.setFont(this.police3);
        rulesEcrit.setLineWrap(true);
        //rulesEcrit.setPreferredSize(new Dimension(1,1));
        int verti = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int hori = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
        JScrollPane extension = new JScrollPane(rulesEcrit,verti,hori);
	extension.setBounds(30, 450, 300, 210);
        this.add(extension,gbc);

    }

    /**
     * Fonction qui creé un JButton et le genere au Jpanel
     */
    public void button_generateur(){
        
        gbc.insets=new Insets(-130,100,0,0);
        gbc.gridy=2;
        gbc.gridx=0;
        gbc.weighty=0;
        gbc.weightx=0;
        //gbc.fill = GridBagConstraints.HORIZONTAL;//decommenter pour que le bouton fasse toute la page
        
        generateur.setPreferredSize(new Dimension(150,30));
        generateur.addActionListener(this);
	generateur.setBounds(125, 680, 100, 30);
        this.add(generateur,gbc);


    }

    /**
     * Fonction qui creer un SystemL afin de generer le texte des regles et des axioms dans l'interface Graphique
     */
    public void setFract(){
        SystemL creaFract = Fractals.systemPresets[Interface.INDEX];
        axiomEcrit.setText(creaFract.getGeneration(0));
        rulesEcrit.setText(creaFract.getRules());
    }

    /**
     * Override de la methode qui detecte si un changement est survenue sur un Jspinner Par exemple
     * Il permet au Jslider d'actualiser la fractal parmis une liste dans le fichier : Fractals.java
     * En plus D'actualiser les JtextesArea lorqu'une fractals et choisie
     * Enfin elle permet de modifier les valeurs des Jspinner lorsque les fleches sont cliqués
     */
    @Override
    public void stateChanged(ChangeEvent e){
        if(e.getSource()==slide){
            //faudra changer ca pour pouvoir changer avec les figures de lyndemeyer
            int index = slide.getValue();
            int[] options = Fractals.optionPresets[index];
            
            SystemL creaFract = Fractals.systemPresets[index];
            turtlePanel.interprete(creaFract,options);
            axiomEcrit.setText(creaFract.getGeneration(0));
            rulesEcrit.setText(creaFract.getRules());



            spinnerN.removeChangeListener(this);
            spinnerL.removeChangeListener(this);
            spinnerδ.removeChangeListener(this);

            spinnerN.setValue(options[0]);
            spinnerL.setValue((double)options[1]);
            spinnerδ.setValue(creaFract.getAngle());

            spinnerN.addChangeListener(this);
            spinnerL.addChangeListener(this);
            spinnerδ.addChangeListener(this);
            

        }
        else if(e.getSource()==spinnerN){
            int valeur =(int) spinnerN.getValue();
            turtlePanel.setGen(valeur);
        }
        else if(e.getSource()==spinnerL){
            Double valeur = (Double) spinnerL.getValue();
            turtlePanel.setLength(valeur);
        }
        else if(e.getSource()==spinnerδ){
            Double valeur = (Double) spinnerδ.getValue();
            turtlePanel.setAngle(valeur);
        }
        
    }
    /**
     * Override de la methode actionPerformed qui detecte quand le Jbutton generateur est activé
     * Elle permet de prendre en compte des modifications dans les axioms et les rules 
     * Et de creer un SystemL 2d , 3d , Stochastique en fonction de la synthaxe dans ces derniers
     * Si la synthaxe est mauvaise alors le try catch empeche l'erreur de s'activer mais en contrepartie , empeche la fractals d'etre crée 
     * Enfin la fractals est affiché à l'ecran grace à la methode Parse de TurtlePanel
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==generateur){
            String sendAxiom = axiomEcrit.getText();
            String sendRules = rulesEcrit.getText();
            String[] sendRuless = sendRules.split("\n");
            Double sendAngle = (Double) spinnerδ.getValue();
            int sendGen = (int) spinnerN.getValue();
            Double sendLength =(Double) spinnerL.getValue();
            ConstructSystem creation = new ConstructSystem();
            SystemL systemTemp = null;
            try{
                systemTemp = creation.createSystem(sendAngle,sendAxiom,sendRuless);
                if (systemTemp != null)
		        	turtlePanel.interprete(systemTemp,sendGen,sendLength);
		        else {
		        	JFrame frame = new JFrame();
		        	JOptionPane.showMessageDialog(frame, "Unrecognized system, Only Simple/Stochastic/ContextSensitive available ! ");
		        }
            }catch(Exception g){
            	JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Syntax Error ! ");
            }
            
        }

    }

    


    

}
