package helper;

import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.utils.properties.PropertiesLoader;
import com.epam.ta.reportportal.ws.model.launch.Mode;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.apache.maven.shared.utils.io.FileUtils;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class ReportPortalUtil {

    private final static  String RP_PROPERTIES = "reportportal.properties";
    private ListenerParameters listenerParameters;
    private static String endpoint;
    private static String token;
    private static String project;
    private static String launchName;
    private static boolean isEnable;
    private static boolean isConvertImage;
    private static boolean isSkippedIssue;
    private static Mode mode;
    private static String keyStore;
    private static String keyStorePassword;
    private static String projects;

    private static Map<String, Object> map = new HashMap<String, Object>();

    public ReportPortalUtil() {
        PropertiesLoader properties = PropertiesLoader.load(RP_PROPERTIES);
        listenerParameters = new ListenerParameters(properties);
        token = listenerParameters.getApiKey();
        endpoint = listenerParameters.getBaseUrl();
        project = listenerParameters.getProjectName();
        launchName = listenerParameters.getLaunchName();
        isEnable = listenerParameters.getEnable();
        isSkippedIssue = listenerParameters.getSkippedAnIssue();
        isConvertImage = listenerParameters.isConvertImage();
        keyStore = listenerParameters.getKeystore();
        keyStorePassword = listenerParameters.getKeystorePassword();
        mode = listenerParameters.getLaunchRunningMode();

        projects = properties.getProperty("rp.projects");
    }

    private void setBody() {

        if(!isEnable) {
            map.put("enable", Boolean.toString(isEnable));
        }
        if(!isConvertImage) {
            map.put("convertimage", Boolean.toString(isEnable));
        }
        if(!isSkippedIssue) {
            map.put("skipped.issue", Boolean.toString(isSkippedIssue));
        }
        if(mode != null) {
            map.put("mode", mode);
        }

    }

    private String getLaunchName() {

        //final String resultXML = System.getProperty("user.dir") + File.separator + "target\\cucumber.xml";
    	final String resultXML = System.getProperty("user.dir") + File.separator + "target/cucumber.xml";

        File sourceFile = new File(resultXML);
        sourceFile = sourceFile.getAbsoluteFile();
        String path = sourceFile.getParent();

        launchName = path + File.separator + launchName + ".xml";
        File targetFile = new File(launchName);

        if(targetFile.exists()) {
            System.out.println("Target File already exists");
            targetFile.delete();
        }

        try {
            FileUtils.copyFile(sourceFile, targetFile);
        }catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        return launchName;
    }

    private String getLaunchUUID(String respondMessage) {
        String SWAGGER_BASE_UUID_REGEX = "\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}";
        Pattern pairRegex = Pattern.compile(SWAGGER_BASE_UUID_REGEX);
        Matcher matcher = pairRegex.matcher( respondMessage );

        String launchUUID = "";
        while (matcher.find()) {
            launchUUID = matcher.group(0);
        }

        return launchUUID;
    }

    private  String getLaunchID(String projectName, String uuid) {
        RestAssured.basePath = "/api/v1/" + projectName + "/launch/" + uuid;

        Response response =
           given().
              header("Context-type", "application/json").
              header("Authorization", "Bearer " + token ).
           when().
              get().
           then().
              statusCode(200).
              contentType(ContentType.JSON).
           extract().
              response();

        return response.jsonPath().getString("id");

    }


    private String updateLaunch(String projectName, String launchId) {
        RestAssured.basePath = "/v1/" + projectName + "/launch/" + launchId + "update";
        Response response =
           given().
              header("Context-type", "application/json").
              header("Authorization", "Bearer " + token ).
              body(map).
           when().
              put();

        Assert.assertEquals(response.statusCode(), 302);

        return response.asString();
    }


    public void postReportPortal()  {
        String[] projectNames = projects.split(";");
        String launch = getLaunchName();
        RestAssured.config().sslConfig(SSLConfig.sslConfig().trustStore(keyStore, keyStorePassword));
        RestAssured.baseURI = endpoint;

        for(String projectName : projectNames ) {
        	if ( !projectName.equals(project) ) {
	            RestAssured.basePath = "/api/v1/" + projectName.trim() + "/launch/import";
	            RestAssured.useRelaxedHTTPSValidation();
	            Response response =
	                given().
	                    header("Context-type", "multipart/form-data").
	                    header("Authorization", "Bearer " + token).
	                    multiPart("file", new File( launch ) ).
	                when().
	                    post(); /*.
	                then().
	                    statusCode(200).
	                    contentType(ContentType.JSON).
	                extract().
	                    response();
                        */
        	}
        }
    }
}
