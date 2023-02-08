package ev.projects.productsuggester.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

public class BasicAuthTestHelper {

    private static final String username = "tester";
    private static final String password = "tester";

    public static HttpHeaders getBasicAuth() {
        String authStr = username + ":" + password;
        String base64Creds = new String(Base64.encodeBase64(authStr.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        return headers;
    }

}
