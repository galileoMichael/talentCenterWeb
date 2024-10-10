package com.tujuhsembilan.talentcenter.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "talent")
@Builder
public class Talent implements java.io.Serializable{
    private static final long serialVersionUID = -5894679636266655135L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "talent_id", columnDefinition = "CHAR(36)")
    private UUID talentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "talent_level_id")
    private TalentLevel talentLevel;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "talent_status_id")
    private TalentStatus talentStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_status_id")
    private EmployeeStatus employeeStatus;

    @Column(name = "talent_name")
    private String talentName;

    @Column(name = "talent_photo_filename")
    private String talentPhotoFilename;

    @Column(name = "employee_number")
    private String employeeNumber;

    @Column(name = "gender")
    private Character gender;

    @Column(name = "birth_date")
    private Timestamp birthDate;

    @Column(name = "talent_description")
    private String talentDescription;

    @Column(name = "talent_cv_filename")
    private String talentCvFilename;

    @Column(name = "experience")
    private int experience;

    @Column(name = "email")
    private String email;

    @Column(name = "cellphone")
    private String cellphone;

    @Column(name = "biography_video_url")
    private String biographyVideoUrl;

    @Column(name = "is_add_to_list_enable")
    private Boolean isAddToListEnable;

    @Setter
    @Getter
    @Column(name = "talent_availability")
    private Boolean talentAvailability;

    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "created_by")
    private String createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time", length = 29)
    private Timestamp createdTime;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_time", length = 29)
    private Timestamp lastModifiedTime;

    @Column(name = "sex")
    private String sex;

    @Column(name = "talent_cv_url")
    private String talentCVUrl;

    @Column(name = "talent_photo_url")
    private String talentPhotoUrl;

    @Column(name = "total_project_completed")
    private Integer totalProjectCompleted;

    @OneToMany(mappedBy = "id.talentId")
    private List<TalentPosition> positions;

    @OneToMany(mappedBy = "id.talentId")
    private List<TalentSkillset> skillsets;

}
