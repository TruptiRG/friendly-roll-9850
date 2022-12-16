package com.bus.Implementation;

import com.bus.Model.CurrentSession;
import com.bus.Model.LoginDTO;
import com.bus.Model.User;
import com.bus.Repository.SessionRepo;
import com.bus.Repository.UserRepo;
import com.bus.Service.LoginService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepo uRepo;

    @Autowired
    private SessionRepo sRepo;

    @Override
    public String logIntoAccount(LoginDTO dto) throws LoginException {

           User existUser= uRepo.findBycontact(dto.getContact());

            if(existUser==null){
                throw new LoginException("Please Enter Valid Contact");
            }

        Optional<CurrentSession> validCustomerSessionOpt =  sRepo.findById(existUser.getUserloginId());

        if(validCustomerSessionOpt.isPresent()) {

            throw new LoginException("User already Logged In with this number");

        }

        if(existUser.getPassword().equals(dto.getPassword())) {

            String key= RandomString.make(6);



            CurrentSession currentUserSession = new CurrentSession(existUser.getUserloginId(),key, LocalDateTime.now());

            sRepo.save(currentUserSession);

            return currentUserSession.toString();
        }
        else
            throw new LoginException("Please Enter a valid password");

    }

    @Override
    public String logOutFromAccount(String key) throws LoginException {
        CurrentSession validCustomerSession = sRepo.findByUuid(key);


        if(validCustomerSession == null) {
            throw new LoginException("User Not Logged In with this number");

        }

        sRepo.delete(validCustomerSession);

        return "Logged Out !";
    }
}
