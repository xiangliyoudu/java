package frameRename;

public class FrameNewName {

	public static void main(String[] args) {
//		String[] source_frames = new String[]{"hdfs://bdpha/MaximAIData/bank-full.csv"};
		String[] source_frames = new String[]{"[\"hdfs://bdpha/MaximAIData/bank-full.csv\",\"hdfs://bdpha/MaximAIData/bank-full1234.csv\"]"};
		
		String firstFrameName = source_frames[0];
		firstFrameName = firstFrameName.substring(0, firstFrameName.indexOf(","));
		firstFrameName = firstFrameName.substring(firstFrameName.lastIndexOf("/") + 1, 
				firstFrameName.lastIndexOf("."));

		System.out.println(firstFrameName + "_Parse");
	}

}
