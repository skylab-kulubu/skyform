package com.weblab.skyform.core.utilities.excel;

import com.weblab.skyform.business.abstracts.FormService;
import com.weblab.skyform.business.abstracts.ResponseService;
import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.ErrorDataResult;
import com.weblab.skyform.core.utilities.results.SuccessDataResult;
import com.weblab.skyform.entities.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExcelFileHelper {


    private final FormService formService;


    private final ResponseService responseService;


    public ExcelFileHelper(FormService formService, ResponseService responseService) {
        this.formService = formService;
        this.responseService = responseService;
    }


    public DataResult<byte[]> exportFormResponsesToExcel(int formId) {

        var formResult = formService.getFormById(formId);
        if (!formResult.isSuccess()) {
            return new ErrorDataResult<>(null, formResult.getMessage());
        }
        var form = formResult.getData();

        var responsesResult = responseService.getResponsesByFormId(formId);
        if (!responsesResult.isSuccess()) {
            return new ErrorDataResult<>(null, responsesResult.getMessage());
        }
        var responses = responsesResult.getData();

        var excelFileResponse = createExcelFile(form,responses);
        if (!excelFileResponse.isSuccess()){
            return new ErrorDataResult<>(null, excelFileResponse.getMessage());
        }

        return new SuccessDataResult<>(excelFileResponse.getData(), "Form responses exported to excel file successfully.");


    }

    private DataResult<byte[]> createExcelFile(Form form, List<Response> responses) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(form.getName());

            Row headerRow = sheet.createRow(0);
            List<String> columns = form.getQuestions().stream().map(Question::getQuestionText).toList();
            for (int i = 0; i < columns.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns.get(i));
            }

            Set<String> sessions = responses.stream().map(Response::getResponseSession).collect(Collectors.toSet());

            int rowIndex = 1;
            for (String session : sessions) {
                Row row = sheet.createRow(rowIndex++);

                for (int i = 0; i < columns.size(); i++) {
                    Cell cell = row.createCell(i);

                    var question = form.getQuestions().get(i);

                    if (question.getQuestionType().getValue().equals(QuestionType.TYPE_TEXT.getValue())) {
                        var response = (ResponseText) responses.stream()
                                .filter(r -> r.getResponseSession().equals(session) && r.getQuestion().getId() == question.getId())
                                .findFirst().orElse(new ResponseText());
                        cell.setCellValue(response.getText());
                    } else if (question.getQuestionType().getValue().equals(QuestionType.TYPE_DATE.getValue())) {
                        var response = (ResponseDate) responses.stream()
                                .filter(r -> r.getResponseSession().equals(session) && r.getQuestion().getId() == question.getId())
                                .findFirst().orElse(new ResponseDate());
                        cell.setCellValue(response.getDate().toString());
                    } else if (question.getQuestionType().getValue().equals(QuestionType.TYPE_RATING.getValue())) {
                        var response = (ResponseRating) responses.stream()
                                .filter(r -> r.getResponseSession().equals(session) && r.getQuestion().getId() == question.getId())
                                .findFirst().orElse(new ResponseRating());
                        cell.setCellValue(response.getRating());
                    } else {
                        cell.setCellValue("");
                    }
                }
            }

            workbook.write(outputStream);
            outputStream.close();

            return new SuccessDataResult<>(outputStream.toByteArray(), "Excel dosyası başarıyla oluşturuldu!");

        } catch (Exception e) {
            return new ErrorDataResult<>(null, e.getMessage());
        }
    }

}
