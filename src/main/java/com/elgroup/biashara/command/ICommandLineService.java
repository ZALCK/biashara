package com.elgroup.biashara.command;

import java.util.List;

public interface ICommandLineService {
	public void create (CommandLine commandLine);
	public void update (CommandLine commandLine);
	public CommandLine getCommandLine (Long id);
	public void delete (CommandLine commandLine);
	public List<CommandLine> getAll();
	
}
