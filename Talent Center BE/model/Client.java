package com.tujuhsembilan.talentcenter.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "client")

public class Client implements java.io.Serializable {


    private static final long serialVersionUID = -5894679636266655135L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id", updatable = false, nullable = false)
    private UUID clientId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_position_id")
    private ClientPosition clientPosition;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "agency_address")
    private String agencyAddress;

    @Column(name= "agency_name")
    private String agencyName;

    @Column(name="client_name")
    private String clientName;

    @Column(name="email")
    private String email;

    @Column(name="gender")
    private String gender;

    @Column(name = "birth_date")
    private Timestamp birthDate;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_modified_by")
    private String modifiedBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time", length = 29)
    private Timestamp createdTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_time", length = 29)
    private Timestamp modifiedTime;

}

