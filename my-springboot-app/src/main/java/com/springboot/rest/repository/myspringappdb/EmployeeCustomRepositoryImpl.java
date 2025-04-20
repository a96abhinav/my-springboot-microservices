package com.springboot.rest.repository.myspringappdb;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Repository;

import com.springboot.rest.config.logging.Loggable;
import com.springboot.rest.constants.QueryConstants;
import com.springboot.rest.entity.myspringappdb.Employee;
import com.springboot.rest.model.MaleAndFemaleCountByDepartment;

import jakarta.persistence.FlushModeType;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@Loggable
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {

	@Autowired
	private jakarta.persistence.EntityManager entityManager;
	
	@Override
	@Loggable(value = LogLevel.INFO)
	public List<Employee> findAll() {

		Session session = entityManager.unwrap(Session.class);

		List<Employee> emp = session.createQuery("from Employee", Employee.class).getResultList();

		return emp;
		// ************************************************************
		/*
		 * CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		 * CriteriaQuery<Employee> cr = cb.createQuery(Employee.class); Root<Employee>
		 * root = cr.from(Employee.class); cr.select(root); TypedQuery<Employee> tq =
		 * entityManager.createQuery(cr);
		 * 
		 * return tq.getResultList();
		 */

		// **********************************************************

		/*
		 * CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		 * CriteriaQuery<Employee> criteriaQuery =
		 * criteriaBuilder.createQuery(Employee.class); Root<Employee> EmployeeRoot =
		 * criteriaQuery.from(Employee.class); criteriaQuery.select(EmployeeRoot);
		 * 
		 * Map<Parameter,Object> parameters = new HashMap<>();
		 * ParameterExpression<String> empName =
		 * criteriaBuilder.parameter(String.class); ParameterExpression<ArrayList>
		 * empGender = criteriaBuilder.parameter(ArrayList.class);
		 * 
		 * List<Predicate> predicates = new ArrayList<Predicate>();
		 * 
		 * predicates.add(criteriaBuilder.equal(EmployeeRoot.get("EmpName"), empName));
		 * parameters.put(empName, "Paul Niksui");
		 * predicates.add(EmployeeRoot.get("EmpGender").in(empGender));
		 * parameters.put(empGender,Arrays.asList("Male","Female"));
		 * 
		 * criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		 * 
		 * TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
		 * for(Entry<Parameter,Object> entryObj : parameters.entrySet()) {
		 * typedQuery.setParameter(entryObj.getKey(), entryObj.getValue()); }
		 * 
		 * return typedQuery.getResultList();
		 */
	}

	@Override
	@Loggable(value = LogLevel.INFO)
	public Employee getEmployeeById(int id) {
		
		  Session session = entityManager.unwrap(Session.class);
		  
		  Employee emp = session.get(Employee.class,id);
		  
		  //Employee emp = entityManager.find(Employee.class,id);
		 
		/*
		 * CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		 * CriteriaQuery<Employee> cr = cb.createQuery(Employee.class); Root<Employee>
		 * root = cr.from(Employee.class);
		 * 
		 * cr.select(root).where(cb.equal(root.get("EmpId"), id));
		 * 
		 * return entityManager.createQuery(cr).getSingleResult();
		 */
		  return emp;
	}

	@Override
	@Loggable(value = LogLevel.INFO)
	public Employee saveEmployee(Employee e) {

		Session session = entityManager.unwrap(Session.class);
		
		/*
		 * List<Project> projects = session.createQuery("from Project",
		 * Project.class).getResultList();
		 * 
		 * Employee e1 = new Employee("Jai", 30, "M", "IT", 2023, 100); Employee e2 =
		 * new Employee("om", 31, "M", "EE", 2024, 200);
		 */

		/*
		 * for(Project p : e.getProjects()) { p.addEmployee(e); }
		 */

		session.merge(e);
		return e;
	}

	@Override
	@Loggable(value = LogLevel.WARN)
	public int delete(int id) {
		Session session = entityManager.unwrap(Session.class);
		org.hibernate.query.Query<String> q = session.createQuery("delete from Employee where EmpId =:id");
		q.setParameter("id", id);
		q.executeUpdate();
		return id;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Loggable(value = LogLevel.INFO)
	public List<MaleAndFemaleCountByDepartment> findMaleAndFemaleCountByDepartment() {
		List<MaleAndFemaleCountByDepartment> countByDepartment = new ArrayList<MaleAndFemaleCountByDepartment>();
		String query = QueryConstants.MALE_AND_FEMALE_COUNT_BY_DEPARTMENT_QUERY;
		Query nativeQuery = entityManager.createNativeQuery(query, Tuple.class).setFlushMode(FlushModeType.AUTO);
		List<Tuple> tupleList = nativeQuery.unwrap(NativeQuery.class).getResultList();
		for (Tuple tuple : tupleList) {
			/*
			 * row.setDepartment(); row.setTotalMaleCount(); row.setTotalFemaleCount();
			 */
			countByDepartment
					.add(MaleAndFemaleCountByDepartment.builder().department(tuple.get("emp_department", String.class))
							.totalMaleCount(tuple.get("male_count", Long.class))
							.totalFemaleCount(tuple.get("female_count", Long.class)).build());
		}
		return countByDepartment;
	}

}
