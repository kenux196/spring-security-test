package org.kenux.security.domain.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "access_ip")
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessIp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ip_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "ip_¡address", nullable = false)
    private String ipAddress;
}
