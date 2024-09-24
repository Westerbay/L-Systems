package system;



/**
 * Classe représentant les ensembles de fractales par défauts
 * @author dubuiss212
 */
public class Fractals {


	/** Un exemple de L-System simple */
	public static final SystemL Simple = ConstructSystem.createSystem(
		90,
		"F-F-F-F",
		"F: FF-F-F-F-FF"
	);
	
	
	/** Un exemple de L-System simple avec Edge rewriting (Avec notion de droite et de gauche) */
	public static final SystemL FASS = ConstructSystem.createSystem(
		90,
		"Fl",
		"Fl: Fl+Fr+",
		"Fr: -Fl-Fr"
	);

	
	/** Un exemple de Bracketed L-System (les plantes) */
	public static final SystemL BracketedSimple = ConstructSystem.createSystem(
		22.5,
		"F",
		"F: FF-[-F+F+F]+[+F-F-F]"
	);
	
	
	/** Un exemple de L-System simple en 3D */
	public static final SystemL Simple3D = ConstructSystem.createSystem(
			90,
			"A",
			"A: B-F+CFC+F-D&F∧D-F+&&CFC+F+B//",
			"B: A&F∧CFB∧F∧D∧∧-F-D∧|F∧B|FC∧F∧A//",
			"C: |D∧|F∧B-F+C∧F∧A&&FA&F∧C+F+B∧F∧D//",
			"D: |CFB-F+B|FA&F∧A&&FB-F+B|FC//"
	);

	
	/** Un exemple de Bracketed L-System simple en 3D (une plante 3D) */
	public static final SystemL Plant3D = ConstructSystem.createSystem(
			22.5,
			"P",
			"P: I + [ P + r] − − // [ − − l ] I [++ l ] − [ P r ]++ P r",
			"I: F s [// & & l ] [// ∧ ∧ l ] F s",
			"s: s F s",
			"l: [’ { +f−ff−f+ | +f−ff−f } ]",
			"r: [&&& p ‘ / w //// w //// w //// w //// w ] ",
			"p: FF",
			"w: [‘ ∧ F][ { &&&& −f+f | −f+f } ]"
	);

	
	/** Un exemple de L-System Stochastic */
	public static final SystemL Stochastic = ConstructSystem.createSystem(
			25.7,
			"F",
			"F: 0.33: F[+F]F[-F]F",
			"F: 0.33: F[+F]F",
			"F: 0.34: F[-F]F"
	);
	
	
	/** Un exemple de L-System ContextSensitive */
	public static final SystemL Context = ConstructSystem.createSystem(
			22.5,
			"F1F1F1",
			"#ignore: +-F",
			"0 < 0 > 0: 0",
			"0 < 0 > 1: 1[-F1F1]",
			"0 < 1 > 0: 1",
			"0 < 1 > 1: 1",
			"1 < 0 > 0: 0",
			"1 < 0 > 1: 1F1",
			"1 < 1 > 0: 1",
			"1 < 1 > 1: 0",
			"* < + > *: -",
			"* < - > *: +"
	);

	
	/** Tableau stockant toutes les fractales par défauts */
	public final static SystemL[] systemPresets = new SystemL[] {
		Simple, FASS, BracketedSimple, Simple3D, Plant3D, Stochastic, Context
	};
	
	
	/** Tableau stockant toutes les options de configurations des fractales par défauts */
	public final static int[][] optionPresets = new int[][] {
			new int[] {5, 2},
			new int[] {18, 1},
			new int[] {4, 10},
			new int[] {3, 40},
			new int[] {5, 10},
			new int[] {5, 7},
			new int[] {26, 10}
	};

}

