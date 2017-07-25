package hello;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Pen
{
	@EmbeddedId
	private PenPk pk;
	@Column(name="COLOUR")
	private String colour;
	
	public PenPk getPk()
	{
		return pk;
	}
	public void setPk(PenPk pk)
	{
		this.pk = pk;
	}
	public String getColour()
	{
		return colour;
	}
	public void setColour(String colour)
	{
		this.colour = colour;
	}
	@Override
	public String toString()
	{
		return "Pen [pk=" + pk + ", colour=" + colour + "]";
	}
}
