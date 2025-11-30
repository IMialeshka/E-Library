package by.vadarod.E_Library.exception.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse {
    private int code;
    private String message;
    private List<String> messages = new ArrayList<>();
}
