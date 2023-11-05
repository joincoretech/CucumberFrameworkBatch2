package utils;

public class ApiPayLoad {

    public static String createEmployeePayload() {

        String createEmployeePayload = "{\n" +
                "      \"EmpId\": \"11\",\n" +
                "       \"EmployeeId\": \"EMP12\",\n" +
                "       \"FirstName\": \"Azizullah\", \n" +
                "       \"MiddleName\": \"A\", \n" +
                "       \"LastName\": \"Ayoubi\", \n" +
                "       \"Birthdate\": \"1990-01-01\",\n" +
                "        \"Gender\": \"Male\",\n" +
                "        \"Address1\": \"123 Main St\", \n" +
                "        \"Address2\": \"\", \n" +
                "        \"Address3\": \"\",\n" +
                "        \"CityId\": 20, \n" +
                "        \"Mobile\": \"1234567890\", \n" +
                "        \"Email\": \"aziz123@gmail.com\",\n" +
                "        \"Password\": \"pass123\",\n" +
                "        \"AadharNumber\": \"123456789012\",\n" +
                "        \"MaritalStatus\": \"Maried\",\n" +
                "        \"PositionId\": \"3\", \n" +
                "        \"CreatedBy\": \"Admin\", \n" +
                "        \"CreatedDate\": \"2023-10-31\",\n" +
                "        \"ModifiedBy\": \"\", \n" +
                "        \"ModifiedDate\": \"\", \n" +
                "        \"JoinDate\": \"2023-08-01\",\n" +
                "        \"LeaveDate\": \"\", \n" +
                "        \"LastLogin\": \"\", \n" +
                "        \"LastLogout\": \"\",\n" +
                "        \"StatusId\": \"1\",\n" +
                "        \"RoleId\": \"3\",\n" +
                "        \"ImageName\": \"john.jpg\",\n" +
                "        \"MacAddress\": \"00:1A:2B:3C:4D:5E\" \n" +
                "            }";
        return createEmployeePayload;
    }


    public static String deleteEmployeePayload() {
        String deleteEmployeePayload = "{ \"EmpId\": \"11\" }";
        return deleteEmployeePayload;
    }
}