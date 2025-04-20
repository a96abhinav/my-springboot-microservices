/*
 * package com.springboot.service;
 * 
 * import static org.junit.Assert.assertThat; import static
 * org.junit.jupiter.api.Assertions.*; import static org.mockito.Mockito.never;
 * import static org.mockito.Mockito.times; import static
 * org.mockito.Mockito.verify; import static org.mockito.Mockito.when;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import org.junit.jupiter.api.Test; import org.junit.runner.RunWith; import
 * org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.Mockito;
 * import org.mockito.runners.MockitoJUnitRunner; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.test.context.junit4.SpringRunner;
 * 
 * import com.springboot.entity.Employee; import
 * com.springboot.repository.EmployeeCustomRepository; import
 * com.springboot.repository.EmployeeCustomRepositoryImpl; import
 * com.springboot.rest.SpringbootRestApiApplication;
 * 
 * @SpringBootTest(classes = SpringbootRestApiApplication.class) class
 * EmployeeServiceImplTest {
 * 
 * @Mock EmployeeCustomRepositoryImpl employeeCustomRepositoryImpl;
 * 
 * @InjectMocks EmployeeServiceImpl employeeServiceImpl;
 * 
 * 
 * @Test void test_service__findAll() { System.out.println("Test Started");
 * List<Employee> empList = new ArrayList<>(); empList.add(new Employee(01,
 * "Abhinav Gupta", 25, "M", "JAVA", 2023, 25000)); empList.add(new Employee(02,
 * "Aman Gupta", 25, "M", "C#", 2023, 25000));
 * 
 * when(employeeCustomRepositoryImpl.findAll()).thenReturn(empList);
 * 
 * List<Employee> e = employeeServiceImpl.findAll();
 * 
 * //verify(employeeDaoImpl, never()).findAll();
 * verify(employeeCustomRepositoryImpl, times(1)).findAll(); assertEquals(2,
 * e.size()); System.out.println("Test Ended"); }
 * 
 * 
 * @Test void test_service__findAll02() { System.out.println("Test Started");
 * 
 * Mockito.doReturn(null).when(employeeCustomRepositoryImpl).findAll();
 * 
 * List<Employee> e = employeeServiceImpl.findAll();
 * 
 * // verify(employeeDaoImpl, never()).findAll();
 * verify(employeeCustomRepositoryImpl, times(1)).findAll();
 * 
 * System.out.println("Test Ended"); }
 * 
 * }
 */