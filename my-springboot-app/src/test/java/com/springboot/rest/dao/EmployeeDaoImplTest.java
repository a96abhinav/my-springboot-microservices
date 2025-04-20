/*
 * package com.springboot.dao;
 * 
 * import static org.hamcrest.CoreMatchers.is; import static
 * org.hamcrest.Matchers.greaterThan; import static
 * org.hamcrest.Matchers.hasSize; import static org.junit.Assert.assertThat;
 * import static org.junit.jupiter.api.Assertions.assertEquals;
 * 
 * import java.util.List;
 * 
 * import jakarta.transaction.Transactional;
 * 
 * import org.junit.jupiter.api.Test; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest;
 * 
 * import com.springboot.entity.Employee; import
 * com.springboot.repository.EmployeeCustomRepository; import
 * com.springboot.rest.SpringbootRestApiApplication;
 * 
 * @SpringBootTest(classes = SpringbootRestApiApplication.class)
 * 
 * @Transactional class EmployeeDaoImplTest {
 * 
 * @Autowired EmployeeCustomRepository employeeDao;
 * 
 * @Test void test_Dao__findAll() {
 * 
 * List<Employee> empList = employeeDao.findAll();
 * 
 * //assertEquals(16, empList.size()); assertThat(empList,
 * hasSize(greaterThan(1))); }
 * 
 * @Test public void test_getEmployeeById() { Employee e =
 * employeeDao.getEmployeeById(3); assertThat(e.getEmpName(),
 * is("Martin Theron")); }
 * 
 * 
 * @Test public void test_saveEmployee() { Employee e = new Employee(0,
 * "Aaryan Khan", 24, "M", "Java", 2023, 3000); Employee e1 =
 * employeeDao.saveEmployee(e); Long count =
 * (employeeDao.findAll()).stream().filter(x ->
 * (x.getEmpName()).equals(e.getEmpName())).count(); System.out.println(count +
 * "  xxxxxxxxxxxxxxxx"); assertEquals(1, count);
 * 
 * }
 * 
 * 
 * @Test public void test_delete() {
 * 
 * Integer id = employeeDao.delete(4); Long count =
 * (employeeDao.findAll()).stream().filter(x ->
 * (Integer.valueOf(x.getEmpId())).equals(id)).count(); System.out.println(count
 * + "  xxxxxxxxxxxxxxxx"); assertEquals(0, count); }
 * 
 * }
 */