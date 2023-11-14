package steps;

import utils.DbUtils;

import java.util.List;
import java.util.Map;

public class DbTester {

    public static void main(String[] args) {

        String query="select firstname, middlename, lastname from employee where employeeid=13;";

        List<Map<String, String >> tableData= DbUtils.getTableDataAsListOFMap(query);

        System.out.println(tableData);
    }
}
