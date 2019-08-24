package fastJsonTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

public class FastJosnMethodTest {
	
	private static SerializeConfig mapping = new SerializeConfig();
	static {
		mapping.put(Date.class, new SimpleDateFormatSerializer(
				"yyyy-MM-dd HH:mm:ss"));
	}

	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) {
//		method3();

		/*Date d = new Date();
		// Date -> string
		String dString = JSON.toJSONString(d, mapping);
		System.out.println(dString);
		// string -> Date
		d = JSON.parseObject(dString, Date.class);
		System.out.println(df.format(d));*/

        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 3, 30);
        System.out.println(df.format(calendar.getTime()));
	}

	public static void method1() {
		System.out.println("javabean转换开始！");
		Person p = new Person();
		p.setId("1");
		p.setAge(1);
		p.setName("fastjson1ᠠᠰᠳᠹᠣᠡᠹᠠᠰᠥᠦᠵᠴᠥᠣᠡᠵᠷᠦᠶᠥᠢᠦᠴᠥᠣᠡᠮᠨᠵᠯᠠᠰᠳᠵᠹᠥᠦᠴᠣᠡ  ᠵᠢᠥᠴᠨᠣᠳᠯᠹᠵ ᠦᠥᠴᠦᠵᠯᠠᠰᠵᠳᠹ ");

		// javabean -> JSONString
		String pString = JSON.toJSONString(p);
		System.out.println(pString);
		// JSONString -> javabean
		p = JSON.parseObject(pString, Person.class);
		System.out.println(p.toString());
		System.out.println("javabean转换结束！");
	}

	public static void method2() {
		System.out.println("list<javabean>转换开始！");
		List<Person> ps = new ArrayList<Person>();
		
		Person p1 = new Person();
		p1.setId("1");
		p1.setName("p1");
		p1.setAge(1);

		Person p11 = new Person();
		p11.setId("11");
		p11.setName("p11");
		p11.setAge(11);

		ps.add(p1);
		ps.add(p11);

		String psString = JSON.toJSONString(ps);
		System.out.println("json string" + psString);

		// parse jsonString
		List<Person> ps2 = JSON.parseArray(psString, Person.class);
		for (Person person : ps2) {
			System.out.println("person: " + person.toString());
		}
		System.out.println("list<javabean>转换完成！");

	}

	public static void method3() {
		System.out.println("map<string,javabean>转换开始！");
		Map<String, Object> pMap = new HashMap<String, Object>();
		
		Person p2 = new Person();
		p2.setId("2");
		p2.setName("p2");
		p2.setAge(2);

		Person p22 = new Person();
		p22.setId("22");
		p22.setName("p22");
		p22.setAge(22);

		pMap.put("p2", p2);
		pMap.put("p22", p22);

		String psString = JSON.toJSONString(pMap);
		System.out.println("json string" + psString);

		// parse jsonString
		pMap = JSON.parseObject(psString);
		for (Object p : pMap.keySet()) {
			System.out.println(pMap.get(p));
		}
		System.out.println("list<javabean>转换完成！");

	}
}
