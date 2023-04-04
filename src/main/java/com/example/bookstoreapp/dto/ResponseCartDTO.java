package com.example.bookstoreapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseCartDTO {
    private  String message;
    private Object data;

    public ResponseCartDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
