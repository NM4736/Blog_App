package com.blog_Application.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    String message;

}
