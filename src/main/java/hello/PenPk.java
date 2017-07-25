package hello;

import java.io.Serializable;

public class PenPk implements Serializable
{
	private EmployeePk employeePk;
	private int num;
	
	public PenPk()
	{
	}
	
	public PenPk(EmployeePk employeePk, int num)
	{
		this.employeePk = employeePk;
		this.num = num;
	}
	
	public EmployeePk getEmployeePk()
	{
		return employeePk;
	}
	public void setEmployeePk(EmployeePk employeePk)
	{
		this.employeePk = employeePk;
	}
	public int getNum()
	{
		return num;
	}
	public void setNum(int num)
	{
		this.num = num;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeePk == null) ? 0 : employeePk.hashCode());
		result = prime * result + num;
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
		PenPk other = (PenPk) obj;
		if (employeePk == null)
		{
			if (other.employeePk != null)
				return false;
		}
		else if (!employeePk.equals(other.employeePk))
			return false;
		if (num != other.num)
			return false;
		return true;
	}
	
	
}
