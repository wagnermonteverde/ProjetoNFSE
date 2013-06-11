/*
 * Copyright (C) 2013 marcelo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.pr.corumbatai.pm.nfse.dados.importados.xls;

import br.pr.corumbatai.pm.nfse.persistencia.dao.DaoMunicipio;
import br.pr.corumbatai.pm.nfse.persistencia.entidades.Municipio;
import br.pr.corumbatai.pm.nfse.utils.log.MLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

/**
 *
 * @author marcelo
 */
public class ImportUtils {
    private final MLog log = MLog.getInstance(this);
    
    public static void main(String[] args) {
        new ImportUtils().importCNAEFromExcelfile();
    }
    
    private void importMunicipiosFromExcelfile(){
        try {
            String dir = getClass().getResource("/br/pr/corumbatai/pm/nfse/dados/importados/xls").getFile();
            String arqExcel = "dtb_2013.xls";

            File file = new File(String.format("%s/%s", dir, arqExcel));
            Workbook workbook;
            try (FileInputStream in = new FileInputStream(file)) {
                WorkbookSettings settings = new WorkbookSettings();
                settings.setEncoding("Cp1252");
                workbook = Workbook.getWorkbook(in, settings);
            }

            List<Municipio> municipios = new ArrayList<>();

            Sheet sheet = workbook.getSheets()[0];
            for (int row = 1; row < sheet.getRows(); row++) {
                Municipio municipio = new Municipio();
                municipio.setUfId(sheet.getCell(0, row).getContents());
                municipio.setUf(sheet.getCell(1, row).getContents());
                municipio.setMesoregiaoId(sheet.getCell(2, row).getContents());
                municipio.setMesoregiao(sheet.getCell(3, row).getContents());
                municipio.setMicroregiaoId(sheet.getCell(4, row).getContents());
                municipio.setMicroregiao(sheet.getCell(5, row).getContents());
                municipio.setMunicipioId(sheet.getCell(6, row).getContents());
                municipio.setMunicipio(sheet.getCell(7, row).getContents());
                municipio.setDistritoId(sheet.getCell(8, row).getContents());
                municipio.setDistrito(sheet.getCell(9, row).getContents());
                municipio.setSubdistritoId(sheet.getCell(10, row).getContents());
                municipio.setSubdistrito(sheet.getCell(11, row).getContents());
                municipios.add(municipio);
            }

            DaoMunicipio dao = new DaoMunicipio();
            dao.deleteAll();
            dao.persist(municipios);

        } catch (IOException | BiffException | RuntimeException e) {
            log.err(e);
        }
    }
    private void importCNAEFromExcelfile(){
        try {
            String dir = getClass().getResource("/br/pr/corumbatai/pm/nfse/dados/importados/xls").getFile();
            String arqExcel = "CNAE20_Correspondencia20x10.xls";

            File file = new File(String.format("%s/%s", dir, arqExcel));
            Workbook workbook;
            try (FileInputStream in = new FileInputStream(file)) {
                WorkbookSettings settings = new WorkbookSettings();
                settings.setEncoding("Cp1252");
                workbook = Workbook.getWorkbook(in, settings);
            }

            
            Sheet sheet = workbook.getSheets()[0];
            for (int row = 1; row < sheet.getRows(); row++) {
                System.out.println(sheet.getCell(0, row).getContents());
            }

        } catch (IOException | BiffException | RuntimeException e) {
            log.err(e);
        }
    
    }
}
