package hello;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Company
{
	@Id
	@Column(name="COMPANY_ID")
	private long companyId;
	
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID")
    private List<Employee> employees ;//= new ArrayList<>();= new ArrayList<>();
    
	public long getCompanyId()
	{
		return companyId;
	}

	public void setCompanyId(long companyId)
	{
		this.companyId = companyId;
	}

	public List<Employee> getEmployees()
	{
		return employees;
	}

	public void setEmployees(List<Employee> employees)
	{
		this.employees = employees;
	}

	@Override
	public String toString()
	{
		return "Company [companyId=" + companyId + ", employees=" + employees + "]";
	}
}
