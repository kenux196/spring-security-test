package org.kenux.security.repository;

import lombok.RequiredArgsConstructor;
import org.kenux.security.domain.entity.PersistentLogin;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class JpaPersistentTokenRepository implements PersistentTokenRepository {

    private final RememberMeTokenRepository rememberMeTokenRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        PersistentLogin newToken = new PersistentLogin(token);
        rememberMeTokenRepository.save(newToken);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        PersistentLogin token = rememberMeTokenRepository.findBySeries(series);
        if (token != null) {
            token.setToken(tokenValue);
            token.setLastUsed(lastUsed);
            rememberMeTokenRepository.save(token);
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        PersistentLogin token = rememberMeTokenRepository.findBySeries(seriesId);
        return new PersistentRememberMeToken(token.getUsername(),
                token.getSeries(),
                token.getToken(),
                token.getLastUsed());
    }

    @Override
    public void removeUserTokens(String username) {
        List<PersistentLogin> tokens = rememberMeTokenRepository.findByUsername(username);
        rememberMeTokenRepository.deleteAll(tokens);

    }
}
