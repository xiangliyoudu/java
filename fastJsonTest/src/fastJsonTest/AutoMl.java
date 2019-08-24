package fastJsonTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class AutoMl {

	public static void main(String[] args) {
		String reqString = "{\"input_spec\":{\"training_frame\":\"Key_Frame__bank.hex\","
				+ "							\"response_column\":\"y\","
				+ "							\"ignored_columns\":[\"age\",\"job\"]},"
				+ "		  \"build_models\":{\"exclude_algos\":[\"GLM\",\"DeepLearning\"]},"
				+ "		 \"build_control\":{\"nfolds\":2,"
				+ "	 \"stopping_criteria\":{\"seed\":-1,"
				+ "							\"max_models\":0,"
				+ "							\"max_runtime_secs\":10,"
				+ "							\"stopping_rounds\":3,"
				+ "							\"stopping_tolerance\":-1}}}";
		//runAutoML {"training_frame":"frame_bank-full_Parse1474_2018_04_02_16_10_14.hex","response_column":"y","seed":-1,"max_models":0,"max_runtime_secs":3600,"stopping_metric":"AUTO","stopping_rounds":3,"stopping_tolerance":-1,"nfolds":5,"ignored_columns":["age","job"],"exclude_algos":["GLM","DeepLearning"]}
		JSONObject jObject = JSONObject.parseObject(reqString);
		Map<String, String[]> params = new HashMap<String, String[]>();
		
		getAutoMlMap(params, jObject);
		handleStringArray(params);
		
		printMap(params);
		
	}
	
	public static Map<String, String[]> getAutoMlMap(Map<String, String[]> params, JSONObject jObject) {
		
		Set<String> keys = jObject.keySet();
		for (String key : keys) {
			if (jObject.get(key) instanceof JSONObject) {
				JSONObject jO = jObject.getJSONObject(key);
				getAutoMlMap(params, jO);
			} else {
//				System.out.println( key + "\t" + jObject.get(key));
				String[] values = new String[]{jObject.get(key).toString()};
				params.put(key, values);
			}
		}
		
		return params;
	}
	
	public static void handleStringArray(Map<String, String[]> map) {
		String[] keys = new String[]{"exclude_algos", "ignored_columns"};
		
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value.startsWith("[\"")) {
				value = value.replace("[", "").replace("\"", "").replace("]", "");
				String[] algos = null;
				if (value.contains(",")) {
					algos = value.split(",");
				} else {
					algos = new String[] {value};
				}
				map.put(key, algos);
			} else {
				map.put(key, new String[]{});
			}
		}
	}
	
	public static void printMap(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		
		for (String key : keys) {
			String[] array = map.get(key);
			System.out.print(key);
			for (String str : array) {
				System.out.print("\t" + str);
			}
			System.out.println();
		}
	}

}
