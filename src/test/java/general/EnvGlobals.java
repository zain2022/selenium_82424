package general;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EnvGlobals {

    public static StringBuilder Differnce = new StringBuilder() ;
    public static RequestSpecification requestSpecification;
    public static Response response;
    public static String requestBody = "";

    public static String firstName = "";
    public static String lastName = "";
    public static String email = "";
    public static String password = "";
    public static String company = "";
    public static String ProjectName = "";
}
