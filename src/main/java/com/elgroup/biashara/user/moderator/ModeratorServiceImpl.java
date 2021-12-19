package com.elgroup.biashara.user.moderator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elgroup.biashara.exception.UserAlreadyExistException;
import com.elgroup.biashara.security.IRoleDAO;
import com.elgroup.biashara.security.Role;

@Service
public class ModeratorServiceImpl implements IModeratorService {
	
	private String MODERATOR_ROLE_IN_DATABASE ="MODERATEUR";
	
	@Autowired
	IModeratorDAO imd;
	@Autowired
	IRoleDAO ird;
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void create(Moderator moderator) {
		// TODO Auto-generated method stub
		moderator.setEnabled(false);
		moderator.setPassword(passwordEncoder.encode(moderator.getPassword()));
		// Get Moderator Role
		Role rolePartenaire = ird.findByName(MODERATOR_ROLE_IN_DATABASE);
        moderator.setRole(rolePartenaire);
		imd.save(moderator);
	}

	@Override
	public void update(Moderator moderator) {
		// TODO Auto-generated method stub
		imd.saveAndFlush(moderator);
	}

	@Override
	public Moderator getModerator(Long id) {
		// TODO Auto-generated method stub
		return imd.getById(id);
	}

	@Override
	public void delete(Moderator moderator) {
		// TODO Auto-generated method stub
		imd.delete(moderator);
	}

	@Override
	public List<Moderator> getAll() {
		// TODO Auto-generated method stub
		return imd.findAll();
	}

	@Override
	public Moderator registerNewModeratorAccount(Moderator moderator) throws UserAlreadyExistException {
		// TODO Auto-generated method stub
		if (emailExists(moderator.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + moderator.getEmail());
        }
        moderator.setPassword(passwordEncoder.encode(moderator.getPassword()));
        return imd.save(moderator);
	}

	@Override
	public Moderator findByEmail(String email) {
		// TODO Auto-generated method stub
		return imd.findByEmail(email);
	}

	private boolean emailExists(final String email) {
        return imd.findByEmail(email) != null;
    }

}
