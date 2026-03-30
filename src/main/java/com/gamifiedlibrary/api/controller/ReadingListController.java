package com.gamifiedlibrary.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamifiedlibrary.api.infrastructure.dto.ReadingListCheckDTO;
import com.gamifiedlibrary.api.service.ReadingListService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/reading-list")
public class ReadingListController {

    ReadingListService readingListService;

    public ReadingListController(ReadingListService readingListService) {
        this.readingListService = readingListService;
    }

    @GetMapping("/{userId}/contains/{bookId}")
    public ResponseEntity<ReadingListCheckDTO> isBookOnUserReadingList(@PathVariable Long userId, @PathVariable Long bookId) {
        boolean exists = readingListService.isBookOnUserList(userId, bookId);
        return ResponseEntity.ok().body(new ReadingListCheckDTO(exists));
    }
}
