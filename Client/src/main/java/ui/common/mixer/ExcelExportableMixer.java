package ui.common.mixer;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import jxl.Workbook;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import ui.managerui.TempManagerLauncher;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public interface ExcelExportableMixer {
    /**
     * 没有办法的innerClass
     */
    class InnerExcelSheet {
        private static WritableSheet excelSheet;
    }
    // TODO 这里只能是static。怎么才能让interface持有变量。而且fxml也不会来调exportExcel，所以总的来说目前java的interface根本不能当mixer来用

    /**
     * to be implement
     * */

    String getExcelName();
    void writeSheet() throws WriteException; // 注意所以调用这个的都只能用

    /**
     * default methods
     * */

    default void myAddCell(int c, int r, String cont) throws WriteException {
        jxl.write.Label label = new jxl.write.Label(c, r, cont);
        InnerExcelSheet.excelSheet.addCell(label);
    }

    default void myAddCell(int c, int r, double val) throws WriteException {
        Number number = new Number(c, r, val);
        InnerExcelSheet.excelSheet.addCell(number);
    }

    // 这个FXML里必须要写这个名字
    @FXML
    default void exportExcelMixer() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(getExcelName() + LocalDate.now().toString());

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Microsoft Excel (*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(TempManagerLauncher.primaryStage);
        if (file != null) {
            writeExcel(file);
        }
    }

    default void writeExcel(File file) {
        WritableWorkbook myFirstWbook = null;
        try {

            myFirstWbook = Workbook.createWorkbook(file);

            // create an Excel sheet
            InnerExcelSheet.excelSheet = myFirstWbook.createSheet("Sheet 1", 0);

            writeSheet();

            myFirstWbook.write();
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        } finally {

            if (myFirstWbook != null) {
                try {
                    myFirstWbook.close();
                } catch (IOException | WriteException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
