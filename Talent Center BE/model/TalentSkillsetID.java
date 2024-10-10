package com.tujuhsembilan.talentcenter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EntityListeners(AuditingEntityListener.class)
public class TalentSkillsetID implements Serializable {
    private static final long serialVersionUID = 5786260781211770131L;

    @Column(name = "talent_id")
    private UUID talentId;

    @Column(name = "skillset_id")
    private UUID skillsetId;

}
