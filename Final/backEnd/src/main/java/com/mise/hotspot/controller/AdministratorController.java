//package com.mise.hotspot.controller;
//import cn.dev33.satoken.stp.StpUtil;
//import com.mise.hotspot.exception.BizError;
//import com.mise.hotspot.pojo.vo.administrator.*;
//import com.mise.hotspot.incantation.CommonResponse;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//
//@CrossOrigin(originPatterns = "*", allowCredentials = "true")
//@RestController
//@RequestMapping("administrator")
//@RequiredArgsConstructor
//public class AdministratorController {
//    private final AdministratorService administratorService;
//    private static final String imgPath = "D://images/";
//    @PostMapping("/upload")
//    public CommonResponse<?> upload(MultipartFile image) throws IOException {
//        String imgName = image.getOriginalFilename();
//        String imgFilePath = imgPath + imgName;
//        OutputStream out = new FileOutputStream(imgFilePath);
//        out.write(image.getBytes());
//        out.flush();
//        out.close();
//        return CommonResponse.success();
//    }
//    @PostMapping("login")
//    public CommonResponse<?> login(@Valid @RequestBody LoginRequest request) {
//        if (request.getUsername() == null || request.getPassword() == null) {
//            return CommonResponse.error(BizError.INVALID_CREDENTIAL);
//        }
//        return CommonResponse.success(administratorService.login(request.getUsername(), request.getPassword()));
//    }
//    @PostMapping("register")
//    public CommonResponse<?> register(@Valid @RequestBody RegisterRequest request) {
//        // Throws BizException if register failed.
//        administratorService.register(request.getUsername(), request.getPassword());
//        return CommonResponse.success();
//    }
//    @PutMapping("edit")
//    public CommonResponse<?> editInfo(@Valid @RequestBody EditUserInfoRequest request) {
//        return CommonResponse.success(administratorService.editInfo(request.getUsername(), request.getEmail()));
//    }
//    @PostMapping("addbook")
//    public CommonResponse<?> addBook(@Valid @RequestBody AddBookRequest request) {
//        if(administratorService.addBook(request.getTitle(), request.getLabel(), request.getAuthor(), request.getIntroduction(), request.getUrl(), request.getText(),request.getImage())){
//            return CommonResponse.success();
//        };
//        return CommonResponse.error(BizError.BOOK_EXISTS);
//    }
//    @DeleteMapping("deletebook")
//    public CommonResponse<?> deleteBook(@Valid @RequestBody DeleteBookRequest request) {
//        administratorService.deleteBook(request.getTitle());
//        return CommonResponse.success();
//    }
//    @PutMapping("editbook")
//    public CommonResponse<?> editBook(@Valid @RequestBody EditBookRequest request) {
//
//        return CommonResponse.success(administratorService.editBook(request.getTitle(), request.getLabel(), request.getAuthor(), request.getIntroduction(), request.getUrl(), request.getText(),request.getImage()));
//    }
//    @DeleteMapping("out")
//    public CommonResponse<?> logout() {
//         StpUtil.checkLogin();
//        return CommonResponse.success(200);
//    }
//
//    @GetMapping("getallbook")
//    public CommonResponse<?> getAllBook() {
//        return CommonResponse.success(administratorService.getAllBook());
//    }
//
//}
