import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Parser {
	public Map<String, Object> parse(String jsonStr) {
		Type typeOfObjectsList = new TypeToken<Map<String, Object>>() {}.getType();
		Map<String, Object> out = new Gson().fromJson(jsonStr, typeOfObjectsList);
		return out;
	}
}
