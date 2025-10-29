package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userId, String userName, String firstName, String lastName, String email, String pwd, String phone) {

        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(pwd);
        userPayload.setPhone(phone);

        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        //Assert.assertEquals(response.getStatusCode(), 200);
    }


    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName) {

        System.out.println("Attempting to delete user: " + userName);
        Response response = UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(), 200);


    }
}
