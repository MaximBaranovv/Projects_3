import com.project.ppp.configuration.RequestBodyConst;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

public class UsersRestControllerTest {

    @Test(groups = {"restful"})
    public void testTheNeedOfAuthorization() {
        RestAssured.expect().statusCode(HttpStatus.UNAUTHORIZED.value()).when()
                .get("http://localhost:8088/");
    }

    @Test(groups = {"restful"})
    public void testAuthorization() {
        RestAssured.given().auth().preemptive()
                .basic("loginMaks", "password1")
                .when()
                .get("http://localhost:8088/")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test(groups = {"restful"})
    public void testGetAllUsers() {
        RestAssured.given().auth().preemptive()
                .basic("loginMaks", "password1").when()
                .get("http://localhost:8088/")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", CoreMatchers.hasItems(1, 3));
    }

    @Test(groups = {"restful"})
    public void testGetUser() {
        RestAssured.given().auth().preemptive()
                .basic("loginMaks", "password1").when()
                .get("http://localhost:8088/users/1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("firstName", CoreMatchers.equalTo("Maks"));
        RestAssured.given().auth().preemptive()
                .basic("loginMaks", "password1").when()
                .get("http://localhost:8088/users/100500")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test(groups = {"restful"})
    public void testAddUser() {
        for (int i = 0; i < 3; i++) {
            if (i == 2) {
                RestAssured.given().auth().preemptive()
                        .basic("loginMaks", "password1")
                        .contentType(ContentType.JSON)
                        .body(RequestBodyConst.REQUEST_BODY_1)
                        .when()
                        .post("http://localhost:8088/users/add")
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .body("firstName", CoreMatchers.equalTo("testAdding"));
                break;
            }
            RestAssured.given().auth().preemptive()
                    .basic("loginMaks", "password1")
                    .contentType(ContentType.JSON)
                    .body(RequestBodyConst.REQUEST_BODY_1)
                    .when()
                    .post("http://localhost:8088/users/add")
                    .then()
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        }

    }

    @Test(groups = {"restful"})
    public void testUpdateUser() {
        RestAssured.given().auth().preemptive()
                .basic("loginMaks", "password1")
                .contentType(ContentType.JSON)
                .body(RequestBodyConst.REQUEST_BODY_2)
                .when()
                .put("http://localhost:8088/users/3")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("firstName", CoreMatchers.equalTo("testUpdating"));

    }

    @Test(groups = {"restful"})
    public void testDeleteUser() {
        RestAssured.given().auth().preemptive()
                .basic("loginMaks", "password1")
                .when()
                .delete("http://localhost:8088/users/2")
                .then()
                .statusCode(HttpStatus.OK.value());

        RestAssured.given().auth().preemptive()
                .basic("loginMaks", "password1")
                .when()
                .delete("http://localhost:8088/users/2")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());

    }
}

