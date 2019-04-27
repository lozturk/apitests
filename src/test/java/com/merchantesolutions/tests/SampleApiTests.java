package com.merchantesolutions.tests;

import com.github.javafaker.Faker;
import com.merchantesolutions.pojo.Comment;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SampleApiTests {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://ec2-54-84-52-184.compute-1.amazonaws.com:3000/";
    }

    @Test
    public void sampleGetTestForPosts(){
        //verify response status code 200, Content-Type json and body contains title:'ea molestias quasi exercitationem repellat qui ipsa sit aut'
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
            postResponse.then().log().all()
                    .header("Content-Type","application/json; charset=utf-8").statusCode(201);
            Map<Object,Object> responseMap = postResponse.body().as(Map.class);
            System.out.println(responseMap);
            assertEquals(postId,responseMap.get("postId"));
            assertEquals(id,responseMap.get("id"));
            assertEquals(name,responseMap.get("name"));
            assertEquals(email,responseMap.get("email"));
            assertEquals(body,responseMap.get("body"));
    }

    @Test
    public void samplePutTestForUsers(){
        String name = new Faker().name().fullName();
        String email = new Faker().internet().emailAddress();
        String username = new Faker().book().title();
        RestAssured.given().accept(ContentType.JSON).
                when().body("{\n" +
                "\"name\": \""+ name +"\",\n" +
                "\"email\": \""+ email + "\",\n" +
                "\"username\": \"" + username + "\"\n" +
                "}")
                .log().all()
                .pathParam("id","2")
                .contentType(ContentType.JSON)
                .put("/users/{id}")
                .then().log().all()
                .header("Content-Type","application/json; charset=utf-8")
                .body(containsString(name))
                .assertThat().statusCode(200);
    }
}
