package model;

public class ClientAddress extends Address
{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString()
	{
		String name = getName();
		String cep = getCep();
		String number = Integer.toString(getDoorNumber());
		String comp = getComplement();
		
		String msg = String.format("%s;%s;%s;%s", name, cep, number, comp);
		
		return msg;
	}
}
