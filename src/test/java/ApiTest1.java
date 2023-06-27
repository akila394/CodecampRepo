import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;


public class ApiTest1 {

    @Test
    public void getPostcode()
    {
        RestAssured.given().header("auth-key","b48f2b17-70a2-42d9-9d07-11687f9279ca").
                and().param("q", "Melbourne").and().param("state","VIC").
                when().get("https://digitalapi.auspost.com.au/postcode/search.json").
                then().assertThat().statusCode(is(200));

        var response = RestAssured.given().header("auth-key","b48f2b17-70a2-42d9-9d07-11687f9279ca").
                and().param("q", "Melbourne").and().param("state","VIC").
                when().get("https://digitalapi.auspost.com.au/postcode/search.json");

        response.then().assertThat().statusCode(is(200));
        response.then().log().body();
    }

    @Test
    public void getPostcodeFromBody(){
        var response = RestAssured.given().header("auth-key","b48f2b17-70a2-42d9-9d07-11687f9279ca").
                and().param("q", "Melbourne").and().param("state","VIC").
                when().get("https://digitalapi.auspost.com.au/postcode/search.json");

        response.then().assertThat().statusCode(is(200));
        response.then().log().body();
        response.then().assertThat().body("localities.locality.postcode[1]",equalTo(8002));
    }
}
