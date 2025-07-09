package framework.RestAssured.utils;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class PayloadManager {
	public static JSONObject payload;

	public static String getCreateUserPayload(String name, String job) {
		payload = new JSONObject();
		payload.put("name", name);
		payload.put("job", job);
		return payload.toString();
	}

	// @Test
	public void payloadTest() {
		System.out.println(getCreateUserPayload("jet", "nam"));
	}

}
