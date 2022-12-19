package khamzin.springproject.SocksApplication.controllers;

import jakarta.validation.Valid;
import khamzin.springproject.SocksApplication.dto.SockDTO;
import khamzin.springproject.SocksApplication.models.Sock;
import khamzin.springproject.SocksApplication.services.SocksService;
import khamzin.springproject.SocksApplication.util.SockErrorResponse;
import khamzin.springproject.SocksApplication.util.SocksException;
import khamzin.springproject.SocksApplication.util.SocksNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static khamzin.springproject.SocksApplication.util.ErrorsUtil.returnErrorsToClient;


@RestController
@RequestMapping("/api/socks")
public class SockController {
    private final SocksService socksService;
    private final ModelMapper modelMapper;

    @Autowired
    public SockController(SocksService socksService, ModelMapper modelMapper) {
        this.socksService = socksService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/income")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid SockDTO sockDTO,
                                           BindingResult bindingResult) {
        int quantity = sockDTO.getQuantity();
        Sock sockToAdd = convertToSock(sockDTO);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        socksService.saveAll(sockToAdd.getColor(), sockToAdd.getCottonPart(), quantity);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/outcome")
    public ResponseEntity<HttpStatus> remove(@RequestBody @Valid SockDTO sockDTO,
                                             BindingResult bindingResult) {
        Sock sockToRemove = convertToSock(sockDTO);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        socksService.removeAll(sockToRemove, sockDTO.getQuantity());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public int findAllSelectedSocks(@RequestParam("color") String color,
                                    @RequestParam("operation") String operation,
                                    @RequestParam("cottonPart") int cottonPart) {

        return socksService.numberOfSocks(color, operation, cottonPart);
    }

    @ExceptionHandler
    private ResponseEntity<SockErrorResponse> handleException(SocksNotFoundException e) {
        SockErrorResponse errorResponse = new SockErrorResponse(HttpStatus.NOT_FOUND, e.getMessage(),
                new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<SockErrorResponse> handleException(SocksException e) {
        SockErrorResponse errorResponse = new SockErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(),
                new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private Sock convertToSock(SockDTO sockDTO) {
        return modelMapper.map(sockDTO, Sock.class);
    }
}
