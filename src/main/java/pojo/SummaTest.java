package pojo;

import java.util.ArrayList;
import java.util.List;

public class SummaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "{\"location\":{\"lat\":-38.383494,\"lng\":33.427362},\"accuracy\":50,\"name\":\"Frontline house\",\"phone_number\":\"(+91) 983 893 3937\",\"address\":\"29, side layout, cohen 09\",\"types\":[\"shoe park\",\"shop\"],\"website\":\"http://google.com\",\"language\":\"French-IN\"}";
		List<String> test = new ArrayList<String>();
		String[] x = a.split("[{:},a-zA-Z]");
		for(String b : x) {
			test.add(b);
		}
		test.forEach(System.out::println);
	}

}
