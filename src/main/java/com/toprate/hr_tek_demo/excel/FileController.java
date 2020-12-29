package com.toprate.hr_tek_demo.excel;

import com.toprate.hr_tek_demo.secvice.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("contact/import")
    public String importFile(MyFile myFile, Model model) {

        System.out.println("Vào được đây là OK rồi");

        fileService.saveContactInSheet(myFile.getMultipartFile());
        fileService.saveContactWorkSkillInSheet(myFile.getMultipartFile());
        fileService.saveContactPositionInSheet(myFile.getMultipartFile());

        System.out.println("Finish Import Data");

        return "redirect:/view-contacts";
    }

//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//    public String uploadFile(MyFile myFile, Model model) {
//        model.addAttribute("message", "Upload success");
//        model.addAttribute("description", myFile.getDescription());
//        try {
//            MultipartFile multipartFile = myFile.getMultipartFile();
//            String fileName = multipartFile.getOriginalFilename();
//            File file = new File(this.getFolderUpload(), fileName);
//            multipartFile.transferTo(file);
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("message", "Upload failed");
//        }
//        return "result";
//    }
//
//    public File getFolderUpload() {
//        File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
//        if (!folderUpload.exists()) {
//            folderUpload.mkdirs();
//        }
//        return folderUpload;
//    }

}
