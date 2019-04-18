package com.merchantesolutions.tests;

import com.github.javafaker.Faker;
import com.merchantesolutions.pojo.Comment;
import com.merchantesolutions.pojo.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class SampleApiTests {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://ec2-54-84-52-184.compute-1.amazonaws.com:3000/";
    }

    @Test
    public void sampleGetTestForPosts(){
        //verify response status code 200, Content-Type json and body contains title:'qui est esse'
        //check header content type
        Response response = RestAssured.get("/posts");
        response.then().log()
                .headers().header("Content-Type","application/json; charset=utf-8")
                .assertThat().body("[1].title", is("ea molestias quasi exercitationem repellat qui ipsa sit aut")).statusCode(200);
    }

    @Test
    public void samplePostTestForComments(){
        //verify response status code 201,
        Comment comment = new Comment();
        int postId = new Faker().number().numberBetween(0,1000);
        int id = new Faker().number().numberBetween(0,100000);
        String name = new Faker().name().fullName();
        String email = new Faker().internet().emailAddress();
        String body = new Faker().book().title();
        comment.setPostId(postId);
        comment.setId(id);
        comment.setName(name);
        comment.setEmail(email);
        comment.setBody(body);
        Response postResponse = given().contentType(ContentType.JSON).
                body(comment).
                when().post("/comments");
            postResponse.then().log().all().statusCode(201);
    }

    @Test
    public void samplePutTestForUsers(){
        RestAssured.given().accept(ContentType.JSON).
                when().body("{\n" +
                "\"name\": \"Kaiser Soze\",\n" +
                "\"email\": \"abc123123@gmail.com\",\n" +
                "\"username\": \"kaplan\"\n" +
                "}")
                .log().all()
                .pathParam("id","2")
                .contentType(ContentType.JSON)
                .put("/users/{id}")
                .then().log().all().statusCode(200);
    }



}
