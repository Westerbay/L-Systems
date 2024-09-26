package system;



/**
 * Default fractals to present
 * @author Wester
 */
public class Fractals {

	public static final SystemL Simple = ConstructSystem.createSystem(
		90,
		"F-F-F-F",
		"F: FF-F-F-F-FF"
	);
	
	public static final SystemL FASS = ConstructSystem.createSystem(
		90,
		"Fl",
		"Fl: Fl+Fr+",
		"Fr: -Fl-Fr"
	);

	public static final SystemL BracketedSimple = ConstructSystem.createSystem(
		22.5,
		"F",
		"F: FF-[-F+F+F]+[+F-F-F]"
	);
	
	public static final SystemL Simple3D = ConstructSystem.createSystem(
			90,
			"A",
			"A: B-F+CFC+F-D&F∧D-F+&&CFC+F+B//",
			"B: A&F∧CFB∧F∧D∧∧-F-D∧|F∧B|FC∧F∧A//",
			"C: |D∧|F∧B-F+C∧F∧A&&FA&F∧C+F+B∧F∧D//",
			"D: |CFB-F+B|FA&F∧A&&FB-F+B|FC//"
	);

	
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

	
	public static final SystemL Stochastic = ConstructSystem.createSystem(
			25.7,
			"F",
			"F: 0.33: F[+F]F[-F]F",
			"F: 0.33: F[+F]F",
			"F: 0.34: F[-F]F"
	);
	
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

	public final static SystemL[] systemPresets = new SystemL[] {
		Simple, FASS, BracketedSimple, Simple3D, Plant3D, Stochastic, Context
	};
	
	
	/** Default configurations for fractals above */
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

