package com.cursoudemy.persons.dto.dtoV01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponseDTO {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long fileSize;
}
