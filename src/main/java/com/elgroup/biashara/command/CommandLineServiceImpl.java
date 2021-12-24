package com.elgroup.biashara.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandLineServiceImpl implements ICommandLineService{

	@Autowired
	ICommandLineDAO icld;
	
	@Override
	public void create(CommandLine commandLine) {
		// TODO Auto-generated method stub
		icld.save(commandLine);
	}

	@Override
	public void update(CommandLine commandLine) {
		// TODO Auto-generated method stub
		icld.saveAndFlush(commandLine);
	}

	@Override
	public CommandLine getCommandLine(Long id) {
		// TODO Auto-generated method stub
		return icld.getById(id);
	}

	@Override
	public void delete(CommandLine commandLine) {
		// TODO Auto-generated method stub
		icld.delete(commandLine);
	}

	@Override
	public List<CommandLine> getAll() {
		// TODO Auto-generated method stub
		return icld.findAll();
	}

}
