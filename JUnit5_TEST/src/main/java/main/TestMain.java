package main;

import dao.EmployeeDAO;

public class TestMain {

	public static void main(String[] args) {
		System.out.println("Hello World");
		String result = EmployeeDAO.getInstance().selectEmployeeList(4);
		System.out.println(result);
		System.out.println("______________________________________________________________________________________________________________________________________________");
		result = EmployeeDAO.getInstance().selectBottom5Salary();
		System.out.println(result);
	}

}
