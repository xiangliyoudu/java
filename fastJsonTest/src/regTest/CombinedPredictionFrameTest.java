package regTest;

public class CombinedPredictionFrameTest {

	public static void main(String[] args) {
		String ast = "(assign combined-prediction-frame_Predict1022_2018_01_23_14_08_30.hex (cbind prediction-frame_Predict1022_2018_01_23_14_08_30.hex frame_Parse1022_2018_01_23_13_41_11.hex [age,job,marital]))";
		String astpre = ast.substring(0, ast.lastIndexOf("frame"));
		astpre += "(cols ";
		String astsib =ast.substring(ast.lastIndexOf("frame"));
		astsib += ")";
		astsib = astsib.replace("[", "[\"").replace("]", "\"]").replace(",", "\",\"");
		System.out.println(astpre + astsib);
	}
	
	public static void method1(){
		//[[age,job,marital]]
		String[] pas = {"[age,job,marital]"};
		String p1 = pas[0];
		p1 = p1.replace("[", "");
		p1 = p1.replace("]", "");
		String[] params = p1.split(",");
		String ast = " (assign combined-prediction-frame_Predict923_2018_01_17_08_48_33.hex (cbind prediction-frame_Predict923_2018_01_17_08_48_33.hex frame_Parse923_2018_01_17_08_47_22.hex))";
		StringBuffer astpre = new StringBuffer(ast.substring(0, ast.lastIndexOf("frame")));
		StringBuffer astsib = new StringBuffer(ast.substring(ast.lastIndexOf("frame")));
		astsib.insert(0, "(cols").insert(astsib.length()-2, "[");
		for (String str : params) {
			astsib.insert(astsib.lastIndexOf("))"),"\"" + str + "\",");
		}
		astsib.replace(astsib.lastIndexOf(","), astsib.lastIndexOf(",") + 1, "])");
		astpre.append(astsib);
		//System.out.println(astsib);
		System.out.println(astpre);
	}

}
