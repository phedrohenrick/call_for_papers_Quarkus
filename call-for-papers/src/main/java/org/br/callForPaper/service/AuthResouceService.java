package org.br.callForPaper.service;

import org.br.callForPaper.dto.UserDetailsDTO;

public interface AuthResouceService {

    void requestLogin(UserDetailsDTO user);
}
