package org.kenux.security.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "persistent_login")
@Data
@ToString
@NoArgsConstructor
public class PersistentLogin implements Serializable {

    @Id
    private String series;

    private String username;
    private String token;
    private Date lastUsed;

    public PersistentLogin(PersistentRememberMeToken token) {
        this.series = token.getSeries();
        this.username = token.getUsername();
        this.token = token.getTokenValue();
        this.lastUsed = token.getDate();
    }

    private static final long seralVersionUID = 8433999509932007961L;
}
