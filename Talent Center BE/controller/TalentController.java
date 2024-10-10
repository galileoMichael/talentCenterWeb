package com.tujuhsembilan.talentcenter.controller;

import com.tujuhsembilan.talentcenter.dto.request.*;
import com.tujuhsembilan.talentcenter.response.MessageResponse;
import com.tujuhsembilan.talentcenter.service.TalentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/talent-management")
public class TalentController {
    @Autowired
    TalentService talentService;

    @GetMapping("/talents")
    public ResponseEntity<Object> getTalents(
            @PageableDefault(page = 1, size = 8, sort = "experience", direction = Sort.Direction.DESC) Pageable pageable,
            @ModelAttribute TalentFilterDto talentFilterDto) {
        log.info("test");
        return talentService.getTalents(pageable, talentFilterDto);
    }

    @PostMapping("/talents/add-to-list")
    public ResponseEntity<Object> addTalentToList(@RequestBody AddToListRequest addToListRequest) {
        MessageResponse messageResponse = talentService.addToList(addToListRequest);
        log.info("Added to list: {}", messageResponse);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping("/talents/download-cv")
    public ResponseEntity<Object> downloadCV(@Valid @RequestBody DownloadCvRequest request) {
        ResponseEntity<Object> messageResponse = talentService.postDownloadCV(request);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

}