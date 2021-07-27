package com.pthon.renue.controllers;

import com.pthon.renue.RenueApplication;
import com.pthon.renue.configurations.AppProperties;
import com.pthon.renue.models.ParcerModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@Controller
public class MainController {

    //get column from args
    @Value("${column:0}")
    private int column;

    public void startParcer() throws URISyntaxException, IOException {
        Logger logger = Logger.getLogger("parcerLogger");
        //get query
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку:");
        String query = scanner.nextLine();
        //if empty query
        if (query.equals("")) {logger.warning("Введён пустой запрос"); return;}

        int column = this.column == 0 ? AppProperties.getDefaultColumn() : this.column;
        String fileName = AppProperties.getFileName();

        String jarPath = new File(RenueApplication.class.getProtectionDomain().getCodeSource().getLocation()
                .toURI()).getPath() + "/" + fileName;

        File file = new File(jarPath);
        //check file exists
        if (!file.exists()) {
            logger.warning(file.getPath() + " Не существует.");
            return;
        }

        ParcerModel parcerModel = new ParcerModel();

        long startTime = new Date().getTime();

        List<String> result = parcerModel.parceFile(file, column, query);

        long endTime = new Date().getTime();

        if (result == null) {logger.warning("Парсер вернул null. Возможно, номер нужной колонки равен нулю или меньше нуля."); return;}

        for (String str : result) {
            System.out.println(str);
        }

        System.out.println("Количество найденных строк: " + result.size());
        System.out.println("Время, затраченное на поиск: " + (endTime - startTime) + " мс");



    }


}
