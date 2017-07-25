package hello;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;

@Entity
public class Employee
{
    @EmbeddedId
    private EmployeePk pk;
	@Column
	private String name;
	
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumns({
    	@JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID"),
    	@JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    })
    private List<Pen> pens ;//= new ArrayList<>();	
	
	public EmployeePk getPk()
	{
		return pk;
	}
	public void setPk(EmployeePk pk)
	{
		this.pk = pk;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public List<Pen> getPens()
	{
		return pens;
	}
	public void setPens(List<Pen> pens)
	{
		this.pens = pens;
	}
	@Override
	public String toString()
	{
		return "Employee [pk=" + pk + ", name=" + name + ", pens=" + pens + "]";
	}
}
