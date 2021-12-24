package com.elgroup.biashara.command;

import java.util.List;

public interface ICommandService {
	public Command create (Command command);
	public void update (Command command);
	public Command getCommand (Long id);
	public void delete (Command command);
	public List<Command> getAll();
	
}
