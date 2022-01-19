package helpers;

import static drivers.BrowserstackMobileDriver.mobileConf;
import static io.restassured.RestAssured.given;

public class Browserstack {

    public static String videoUrl(String sessionId) {

        String user = mobileConf.user();
        String key = mobileConf.key();
        String videoURL = mobileConf.videoURL();

        return given()
                .auth().basic(user, key)
                .when()
                .get(videoURL + sessionId + ".json")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .path("automation_session.video_url");
    }
}
