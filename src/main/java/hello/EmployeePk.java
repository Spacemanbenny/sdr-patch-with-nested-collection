package hello;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeePk implements Serializable
{
	@Column(name="COMPANY_ID")
	private long companyId;
	@Column(name="EMPLOYEE_ID")
	private long employeeId;
	
	public EmployeePk()
	{
	}
	
	public EmployeePk(long companyId, long employeeId)
	{
		this.companyId = companyId;
		this.employeeId = employeeId;
	}
	public long getCompanyId()
	{
		return companyId;
	}
	public void setCompanyId(long companyId)
	{
		this.companyId = companyId;
	}
	public long getEmployeeId()
	{
		return employeeId;
	}
	public void setEmployeeId(long employeeId)
	{
		this.employeeId = employeeId;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (companyId ^ (companyId >>> 32));
		result = prime * result + (int) (employeeId ^ (employeeId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeePk other = (EmployeePk) obj;
		if (companyId != other.companyId)
			return false;
		if (employeeId != other.employeeId)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "EmployeePk [companyId=" + companyId + ", employeeId=" + employeeId + "]";
	}
}
