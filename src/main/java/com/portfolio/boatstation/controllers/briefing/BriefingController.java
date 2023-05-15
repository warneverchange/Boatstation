package com.portfolio.boatstation.controllers.briefing;

import com.portfolio.boatstation.entities.BriefingLog;
import com.portfolio.boatstation.entities.BriefingType;
import com.portfolio.boatstation.entities.views.BriefingsView;
import com.portfolio.boatstation.services.briefing.BriefingService;
import com.portfolio.boatstation.services.briefing.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/briefing")
public class BriefingController {
    private final BriefingService briefingService;
    private final ResourceLoader resourceLoader;
    private final ReportService reportService;


    @Autowired
    public BriefingController(BriefingService briefingService, ResourceLoader resourceLoader, ReportService reportService) {
        this.briefingService = briefingService;
        this.resourceLoader = resourceLoader;
        this.reportService = reportService;
    }

    @GetMapping("/reported")
    public ResponseEntity<List<Long>> getReportedBriefings() {
        return ResponseEntity.ok(briefingService.getReportedBriefings());
    }

    @GetMapping
    public ResponseEntity<List<BriefingsView>> getBriefings() {
        return ResponseEntity.ok(briefingService.getBriefings());
    }

    @GetMapping("/types")
    public ResponseEntity<List<BriefingType>> getBriefingTypes() {
        return ResponseEntity.ok(briefingService.getBriefingTypes());
    }

    @PostMapping
    public void createBriefingLog(@RequestBody BriefingLog briefingLog) {
        briefingService.createBriefingLog(briefingLog);
    }


    /*@GetMapping("/report/template")
    public ResponseEntity<?> downloadReportTemplate() {
        return ResponseEntity.status(HttpStatus.OK)
        //TODO: .contentType(MediaType.)
    }*/

    @RequestMapping(
            value = "/rental/{briefingLogId}/report/upload",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadScan(
            @PathVariable("briefingLogId") Long briefingLogId,
            @RequestPart("scan") MultipartFile file) throws IOException {
        reportService.uploadReport(briefingLogId, file.getBytes());
    }

    @GetMapping("{briefingLogId}/report/download")
    public ResponseEntity<Resource> downloadReportScan(@PathVariable("briefingLogId") Long briefingLogId) {
        var report = reportService.getReportScanByBriefingLogId(briefingLogId);
        return ResponseEntity.ok(new ByteArrayResource(report.getScan()));
    }

    @GetMapping("id/rental/{rentalLogId}")
    public ResponseEntity<Long> getBriefingLogIdByRentalLogId(@PathVariable("rentalLogId") Long rentalLogId) {
        return ResponseEntity.ok(briefingService.getBriefingLogIdByRentalLogId(rentalLogId));
    }


    @GetMapping("/report/template")
    public ResponseEntity<Resource> downloadReportTemplate() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/static/report.docx");
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.asMediaType(MimeType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document")))
                .contentLength(resource.contentLength())
                .body(resource);

    }
}
