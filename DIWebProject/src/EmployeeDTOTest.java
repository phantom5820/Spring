import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import di.DIContainer;
import dto.EmployeeDTO;

class EmployeeDTOTest {

	private static AnnotationConfigApplicationContext context;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		context = DIContainer.getContext();
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

	
	@DisplayName("DI를 적용하지 않은 셋팅")
	@Test
	void testEmployeeDTOStringStringStringInt() {
		String eno = "AA11";
		String name = "홍길동";
		String department = "영업";
		int position = 1;
		
		for (int i = 0; i < 1000; i++) {
			EmployeeDTO dto = new EmployeeDTO(eno+i, name, department, null, 0, position);
			System.out.println(dto.toString());
		}
	}
	
	@DisplayName("DI를 적용한 셋팅")
	@Test
	void testEmployeeDTO() {
		String eno = "AA11";
		String name = "홍길동";
		String department = "영업";
		int position = 1;
		
		for (int i = 0; i < 1000; i++) {
			EmployeeDTO dto = context.getBean(EmployeeDTO.class);
			dto.init(eno+i, name, department, null, 0, position);
			System.out.println(dto.toString());
		}
	}
	
		
	
	

}
