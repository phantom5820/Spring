package student;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import student.dto.StudentDTO;

class StudentSearchTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}
	@DisplayName("학생정보조회테스트")
	@Test
	void searchTest() {
		StudentDTO dto = new StudentDTO("BY6288", "김동수", "경제", 3.44);
		assertEquals(dto, dto);
	}

	@AfterEach
	void end() {
		System.out.println("테스트 종료");
	}
}
