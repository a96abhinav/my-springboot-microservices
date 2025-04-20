package com.springboot.rest.controller;

import com.springboot.rest.constants.CommonConstants;
import com.springboot.rest.entity.myspringappdb.Image;
import com.springboot.rest.service.ImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Tag(name = CommonConstants.FILE_SERVICE)
@RestController
//@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(CommonConstants.API_PATH + CommonConstants.FILE_SERVICE)
public class FileController {

	@Autowired
	private ImageService imageService;

	@PostMapping(path = "/images/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> uploadImage(@RequestPart("file") MultipartFile file) {
		try {
			// Convert MultipartFile to byte array
			byte[] imageData = file.getBytes();

			// Create Image entity and save to the database
			Image image = new Image();
			image.setData(imageData);
			imageService.saveImage(image);

			return ResponseEntity.ok("Image uploaded successfully with file Id : " + image.getId());
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Failed to upload image");
		}
	}

	@GetMapping(path = "/images/download/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) {
		// Retrieve the Image entity from the database
		Image image = imageService.getImageById(id);

		// Check if the image exists
		if (image == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		// Set the response headers
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.IMAGE_JPEG); // Adjust the media type based on your image type
		headers.setContentLength(image.getData().length);
		headers.setContentDispositionFormData("attachment", "image" + id + ".jpg");

		// Return the image data as a ResponseEntity
		return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
	}

	@PostMapping(path = "/uploadAndRetriveCsvFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> uploadCsvFile(@RequestPart("file") MultipartFile file) {
		try {
			byte[] csvContent = file.getBytes();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", "csvfile.csv");
			return new ResponseEntity<>(csvContent, headers, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).body(null);
		}
	}

	@PostMapping(path = "/compare-files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> compareFiles(@RequestPart("file1") MultipartFile file1, @RequestPart("file2") MultipartFile file2) {
//		try (InputStream is1 = file1.getInputStream(); InputStream is2 = file2.getInputStream()) {
		try (InputStream is1 = new ByteArrayInputStream(file1.getBytes()); InputStream is2 = new ByteArrayInputStream(file2.getBytes());) {
			boolean isEqual = compareXlsxFiles(is1, is2);

			String response = isEqual ? "TRUE" : "FALSE";
			return ResponseEntity.ok(response);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).body(null);
		}
	}

	private boolean compareXlsxFiles(InputStream is1, InputStream is2) throws IOException {
		Workbook wb1 = new XSSFWorkbook(is1);
		Workbook wb2 = new XSSFWorkbook(is2);

		if (wb1.getNumberOfSheets() != wb2.getNumberOfSheets()) {
			return false;
		}

		for (int i = 0; i < wb1.getNumberOfSheets(); i++) {
			Sheet sheet1 = wb1.getSheetAt(i);
			Sheet sheet2 = wb2.getSheetAt(i);

			if (sheet1.getPhysicalNumberOfRows() != sheet2.getPhysicalNumberOfRows()) {
				return false;
			}

			for (int j = 0; j < sheet1.getPhysicalNumberOfRows(); j++) {
				Row row1 = sheet1.getRow(j);
				Row row2 = sheet2.getRow(j);

				if (row1.getPhysicalNumberOfCells() != row2.getPhysicalNumberOfCells()) {
					return false;
				}

				for (int k = 0; k < row1.getPhysicalNumberOfCells(); k++) {
					Cell cell1 = row1.getCell(k);
					Cell cell2 = row2.getCell(k);

					if (!compareCells(cell1, cell2)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private boolean compareCells(Cell cell1, Cell cell2) {
		if (cell1 == null && cell2 == null) {
			return true;
		}
		if (cell1 == null || cell2 == null) {
			return false;
		}

		if (cell1.getCellType() != cell2.getCellType()) {
			return false;
		}

		switch (cell1.getCellType()) {
			case BOOLEAN:
				return cell1.getBooleanCellValue() == cell2.getBooleanCellValue();
			case NUMERIC:
				return cell1.getNumericCellValue() == cell2.getNumericCellValue();
			case STRING:
				return cell1.getStringCellValue().equals(cell2.getStringCellValue());
			case FORMULA:
				return cell1.getCellFormula().equals(cell2.getCellFormula());
			default:
				return false;
		}
	}
}
