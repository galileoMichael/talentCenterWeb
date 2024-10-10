package com.tujuhsembilan.talentcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = { "com.tujuhsembilan.talentcenter", "lib.i18n", "lib.minio" })
public class TalentCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalentCenterApplication.class, args);
	}

}
