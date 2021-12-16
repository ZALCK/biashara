package com.elgroup.biashara.user.moderator;

import java.util.List;

import com.elgroup.biashara.exception.UserAlreadyExistException;

public interface IModeratorService {
	public void create (Moderator customer);
	public void update (Moderator customer);
	public Moderator getModerator (Long id);
	public void delete (Moderator moderator);
	public List<Moderator> getAll();
	
	public Moderator registerNewModeratorAccount(Moderator moderator) throws UserAlreadyExistException;
	public Moderator findByEmail(String email);
}
