package br.pr.corumbatai.pm.nfse;

import br.pr.corumbatai.pm.nfse.persistencia.DaoGenerico;
import br.pr.corumbatai.pm.nfse.persistencia.dao.DaoMunicipio;
import br.pr.corumbatai.pm.nfse.persistencia.dao.DaoPessoa;
import br.pr.corumbatai.pm.nfse.persistencia.entidades.Endereco;
import br.pr.corumbatai.pm.nfse.persistencia.entidades.Municipio;
import br.pr.corumbatai.pm.nfse.persistencia.entidades.PessoaFisica;
import br.pr.corumbatai.pm.nfse.utils.DateUtils;
import br.pr.corumbatai.pm.nfse.webservice.WebService;
import com.m4Rc310.nfse.CadastroPessoaEnvioDocument;
import com.m4Rc310.nfse.ConsultarPessoaEnvioDocument;
import com.m4Rc310.nfse.ConsultarPessoaRespostaDocument.ConsultarPessoaResposta;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Endpoint;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

/**
 *
 * @author Marcelo
 */
public class Main {

    public static void main(String[] args) {
        //Basico
//        System.out.println(B.getString("ola.mundo"));
//        System.out.println(B.getString("teste.number", 12300.9));
//        //Avançado
//        System.out.println(B.getString("nome.usuario", "Marcelo", new Date()));
//
//        System.out.println(NFSeUtils.getNewProtocolNumber(12l));

//        HibernateUtil.getInstance().apagarBancoDados();
//        HibernateUtil.getInstance().gerarBancoDados();

//        new Main().teste1();
//        new Main().popularMunicipios();
        new Main().teste();
//        new Main().publicarWebservice();

    }

    private void teste() {
        org.apache.log4j.Logger.getLogger(getClass()).debug("------");
        DaoPessoa dao = new DaoPessoa();
        try {
            PessoaFisica fisica = new PessoaFisica();
            fisica.setCpf("03057532900");
            fisica.setNome("Marcelo Lopes da Silva");
            fisica.setEmail("marcelo.utfpr@me.com");
            fisica.setTelefone("4499198063");
            fisica.setDataNascimento(DateUtils.getDate("18/01/1910"));

            Endereco e = new Endereco();
            e.setLogradouro("Rua Tocantins");
            e.setBairro("Novo centro");
            e.setCep("86970-000");

            DaoMunicipio daoM = new DaoMunicipio();
            Municipio mun = daoM.getMunicipioCodAndUF("06555", "41");
            e.setMunicipio(mun);
//            e.setUf("PR");
//            e.setCodMunicipio(12225);
//            e.setMunicipio("Corumbataí do Sul");
            e.setComplemento("CASA");
            e.setNumero(775);


//            new DaoGenerico<Endereco>(){}.persist(e);
            fisica.setEndereco(e);
//            dao.persist(fisica);
            
            
            CadastroPessoaEnvioDocument cped = CadastroPessoaEnvioDocument.Factory.newInstance();
            cped.addNewCadastroPessoaEnvio().setCpfCnpj("03057532900");
            ConsultarPessoaResposta cpr = (ConsultarPessoaResposta) fisica.toXmlObject();
            cped.getCadastroPessoaEnvio().setPessoa(cpr.getDados());
            
            
//            
            System.out.println(new WebService().cadastrarPessoa(cped.xmlText()));

//            System.out.println(cpr.getDados().xmlText());
//              System.out.println(fisica.toXmlObject().xmlText());
//            System.out.println(new Validador().processaCadastroPessoa(cped.getCadastroPessoaEnvio()));

        } catch (ParseException | RuntimeException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        ConsultarPessoaEnvioDocument cpe = ConsultarPessoaEnvioDocument.Factory.newInstance();
        cpe.addNewConsultarPessoaEnvio().setCpfCnpj("03057532900");

//        System.out.println(new WebService().pesquisarPessoa(cpe.xmlText()));
//
//        for (Pessoa pessoa : dao.getAll()) {
//            System.out.println(pessoa.getEndereco().getLogradouro());
//        }
//        
//        for (Pessoa pessoa : dao.getPessoasPorNome("marcelo", true)) {
//            PessoaFisica fisica = (PessoaFisica) pessoa;
//            System.out.println(fisica.getNome());
//        }

//        System.out.println(dao.getPessoasPorNome("marcelo", true));
//        System.out.println(dao.getPessoasPorNomeRazao("marcelo", true));
    }

    private void teste1() {
        DaoMunicipio dao = new DaoMunicipio();
        Municipio mun = dao.getMunicipioCodAndUF("06555", "41");
        System.out.println(mun.getDistrito());
    }

    private void publicarWebservice() {
        WebService ws = new WebService();
        Endpoint endpoint = Endpoint.publish("http://localhost:8080/nfse", ws);
        System.out.println(endpoint.isPublished());
    }

    private void popularMunicipios() {
        try {
            String dir = getClass().getResource("/br/pr/corumbatai/pm/nfse/dados/importados/xls").getFile();
            String arqExcel = "dtb_2013.xls";

            File file = new File(String.format("%s/%s", dir, arqExcel));
            FileInputStream in = new FileInputStream(file);
            WorkbookSettings settings = new WorkbookSettings();
            settings.setEncoding("Cp1252");
            Workbook workbook = Workbook.getWorkbook(in, settings);
            in.close();

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
