package hello;


import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class, webEnvironment=WebEnvironment.RANDOM_PORT)
public class PatchTest
{
    @Autowired
    CompanyRepository repository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void t1()
    {
		// insert company with 2 employees
		// 1 BOB {1 red pen, 2 blue pen}
		// 2 SUE {3 green pen, 4 yellow pen}
		Company c1=new Company();
		c1.setCompanyId(1);
		c1.setEmployees(new ArrayList<>());
		Employee emp1=new Employee();
		emp1.setPk(new EmployeePk(1, 101));
		emp1.setName("BOB");
		emp1.setPens(new ArrayList<>());
		Pen pen1=new Pen();
		pen1.setPk(new PenPk(emp1.getPk(), 1));
		pen1.setColour("red");
		Pen pen2=new Pen();
		pen2.setPk(new PenPk(emp1.getPk(), 2));
		pen2.setColour("blue");
		emp1.getPens().add(pen1);
		emp1.getPens().add(pen2);
		
		Employee emp2=new Employee();
		emp2.setPk(new EmployeePk(1, 102));
		emp2.setName("SUE");
		emp2.setPens(new ArrayList<>());
		Pen pen3=new Pen();
		pen3.setPk(new PenPk(emp2.getPk(), 3));
		pen3.setColour("green");
		Pen pen4=new Pen();
		pen4.setPk(new PenPk(emp2.getPk(), 4));
		pen4.setColour("yellow");
		emp2.getPens().add(pen3);
		emp2.getPens().add(pen4);
		
		c1.setCompanyId(1);
		c1.getEmployees().add(emp1);
		c1.getEmployees().add(emp2);
		
		repository.saveAndFlush(c1);
    	
		// everything inserted ok
		System.out.println(jdbcTemplate.queryForList("select * from company"));
		System.out.println(jdbcTemplate.queryForList("select * from employee"));
		System.out.println(jdbcTemplate.queryForList("select * from pen"));
		
		// PATCH
		// employee 101 BOB is gone
		// employee 102 SUE is unchanged
		// employee 103 JIM is new with purple pen
		String jsonPatch="{  "+
				"  \"companyId\" : 1,  "+
				"  \"employees\" : [  "+
				"    {  "+
				"      \"pk\" : {  "+
				"        \"companyId\" : 1,  "+
				"        \"employeeId\" : 102  "+
				"      },  "+
				"      \"name\" : \"SUE\",  "+
				"	  \"pens\" : [ "+
				"		{ "+
				"			\"pk\" : { "+
				"				\"employeePk\" : { "+
				"					\"companyId\" : 1, "+
				"					\"employeeId\" : 102 "+
				"				}, "+
				"				\"num\" : 1 "+
				"			}, "+
				"			\"colour\" : \"red\" "+
				"		}, "+
				"		{ "+
				"			\"pk\" : { "+
				"				\"employeePk\" : { "+
				"					\"companyId\" : 1, "+
				"					\"employeeId\" : 102 "+
				"				}, "+
				"				\"num\" : 2 "+
				"			}, "+
				"			\"colour\" : \"blue\" "+
				"		}		 "+
				"	  ] "+
				"    },  "+
				"    {  "+
				"      \"pk\": {  "+
				"        \"companyId\": 1,  "+
				"        \"employeeId\": 103  "+
				"      },  "+
				"      \"name\": \"JIM\" , "+
				"	  \"pens\" : [ "+
				"		{ "+
				"			\"pk\" : { "+
				"				\"employeePk\" : { "+
				"					\"companyId\" : 1, "+
				"					\"employeeId\" : 103 "+
				"				}, "+
				"				\"num\" : 5 "+
				"			}, "+
				"			\"colour\" : \"purple\" "+
				"		} "+
				"	  ] "+
				"    }  "+
				"  ]  "+
				" } ";    	

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jsonPatch ,headers);
		String response=restTemplate.patchForObject("/companies/1", entity, String.class);

		// response is:
		// {"cause":{"cause":null,"message":null},"message":"Could not read payload!; nested exception is java.lang.NullPointerException"}
		// see stacktrace on console
    }
}
