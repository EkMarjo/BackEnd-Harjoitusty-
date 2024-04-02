package harjoitustyo.backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import harjoitustyo.backend.model.AppUser;
import harjoitustyo.backend.model.AppUserRepository;

//mahdollistaa kirjautumisen järjestelmään
@Service
public class UserDetailService implements UserDetailsService {
    private final AppUserRepository aRepository;

	@Autowired
	public UserDetailService(AppUserRepository userRepository) {
		this.aRepository = userRepository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	AppUser curruser = aRepository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getHashPassword(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }   



}
