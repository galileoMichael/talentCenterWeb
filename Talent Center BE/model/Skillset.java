package com.tujuhsembilan.talentcenter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "skillset")

public class Skillset implements java.io.Serializable {

    private static final long serialVersionUID = -5894679636266655135L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "skillset_id", updatable = false, nullable = false)
    private UUID skillsetId;

    @Column(name = "skillset_type_id")
    private UUID skillsetTypeId;

    @Column(name = "skillset_name")
    private String skillsetName;

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


