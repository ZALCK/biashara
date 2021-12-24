package com.elgroup.biashara.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandServiceImpl implements ICommandService{

	@Autowired
	ICommandDAO icd;
	@Autowired
	ICommandLineService icls;
	
	@Override
	public Command create(Command command) {
		// TODO Auto-generated method stub
		Command newCommand = icd.save(command);
		for (CommandLine commandLine : newCommand.getCommandLines()) {
			commandLine.setCommand(newCommand);
			icls.create(commandLine);
		}
		return newCommand;
	}

	@Override
	public void update(Command command) {
		// TODO Auto-generated method stub
		icd.saveAndFlush(command);
	}

	@Override
	public Command getCommand(Long id) {
		// TODO Auto-generated method stub
		return icd.getById(id);
	}

	@Override
	public void delete(Command command) {
		// TODO Auto-generated method stub
		icd.delete(command);
	}

	@Override
	public List<Command> getAll() {
		// TODO Auto-generated method stub
		return icd.findAll();
	}

}
