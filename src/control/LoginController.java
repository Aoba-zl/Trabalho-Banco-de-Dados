package control;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class LoginController implements EventHandler<Event>
{
	private TextField tfUserName;
	private TextField tfpassword;
	
	public LoginController(TextField tfUserName, TextField tfPassword)
	{
		this.tfUserName = tfUserName;
		this.tfpassword = tfPassword;
	}

	@Override
	public void handle(Event event) 
	{
		
	}
	
	
	
}
