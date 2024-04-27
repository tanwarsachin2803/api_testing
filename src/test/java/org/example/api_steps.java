package org.example;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.junit.runner.Request;
import org.openqa.selenium.remote.server.JsonParametersAware;
import org.testng.Assert;

import java.lang.reflect.Array;
import java.util.List;

import static io.restassured.RestAssured.given;

public class api_steps {
    Response response;
    RequestSpecification httpClient;
    JSONObject postParameters=new JSONObject();

    @Given("Access end point url")
    public void access_end_point_url() {
        RestAssured.baseURI="https://fakestoreapi.com/";
    }
    @When("Access products")
    public void access_products() {

        RequestSpecification httpClient= given();
        this.httpClient=httpClient;
        Response response=httpClient.get("products");
        this.response=response;
    }


    @When("Access databases {}")
    public void accessDatabasesDatabase(String database) {
        System.out.println(database);
        RequestSpecification httpClient= given();
        Response response=httpClient.get(database);
        this.response=response;
    }

    @Then("Verify database {} is present")
    public void verifyDatabaseIsPresent(String database) {
        if(response.getStatusCode()==200)
        {
            System.out.println(database+" is not present");

        }
        else
        {
            System.out.println(database+" is not present");
        }
    }

    @Given("print {}")
    public void printExamples(String examples) {
        System.out.println("Example is "+examples);
    }

    @Then("Check the product {int}")
    public void checkTheProduct(int arg0) {
        ResponseBody body=response.body();
        //
        String responseBody= body.asString();

        //COnverting the value in json
        JsonPath jsPath=response.jsonPath();
        // Assuming the JSON response is an array, e.g., [{"id":1}, {"id":2}]
        List<Object> allObjects = jsPath.getList("rating.rate"); // You can specify a path if needed, like "data"
        int count = allObjects.size();

        System.out.println("Total number of objects: " + count);

    }

    @Then("Verify status code {int}")
    public void verifyTheStatusCode(int arg1) {
        System.out.println("Status code: "+response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),arg1);
    }

    @Then("Pass the parameters")
    public void passTheParameters() {
        JSONObject postParameters=new JSONObject();
        postParameters.put("title","Luffy");
        postParameters.put("price",200);


    }

    @When("Pass the post method")
    public void passThePostMethod() {
        String resp=  given().log().all().accept(ContentType.JSON)
                .contentType("application/json")
                .and()
                .body(postParameters.toString())
                .post("products")   //hit the post end point
                .thenReturn().asString();

        System.out.println(resp);

    }
}
