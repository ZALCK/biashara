package com.elgroup.biashara.user.partner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elgroup.biashara.exception.UserAlreadyExistException;
import com.elgroup.biashara.security.IRoleDAO;
import com.elgroup.biashara.security.Role;

@Service
public class PartnerServiceImpl implements IPartnerService {
	
	private String PARTNER_ROLE_IN_DATABASE ="PARTENAIRE";
	
	@Autowired
	IPartnerDAO ipd;
	@Autowired
	IRoleDAO ird;
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void create(Partner partner) {
		// TODO Auto-generated method stub
		partner.setEnabled(false);
		partner.setPassword(passwordEncoder.encode(partner.getPassword()));
		// Get Partner Role
		Role rolePartenaire = ird.findByName(PARTNER_ROLE_IN_DATABASE);
        partner.setRole(rolePartenaire);
		ipd.save(partner);
	}

	@Override
	public void update(Partner partner) {
		// TODO Auto-generated method stub
		ipd.saveAndFlush(partner);
	}

	@Override
	public Partner getPartner(Long id) {
		// TODO Auto-generated method stub
		return ipd.getById(id);
	}

	@Override
	public void delete(Partner partner) {
		// TODO Auto-generated method stub
		ipd.delete(partner);
	}

	@Override
	public List<Partner> getAll() {
		// TODO Auto-generated method stub
		return ipd.findAll();
	}

	@Override
	public Partner registerNewPartnerAccount(Partner partner) throws UserAlreadyExistException {
		// TODO Auto-generated method stub
		if (emailExists(partner.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + partner.getEmail());
        }
        partner.setPassword(passwordEncoder.encode(partner.getPassword()));
        return ipd.save(partner);
	}

	@Override
	public Partner findByEmail(String email) {
		// TODO Auto-generated method stub
		return ipd.findByEmail(email);
	}

	private boolean emailExists(final String email) {
        return ipd.findByEmail(email) != null;
    }

}
