/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosteste;

import br.inf.portalfiscal.nfe.wsdl.nfestatusservico.NfeStatusServico;
import br.inf.portalfiscal.nfe.wsdl.nfestatusservico.NfeStatusServicoSoap;
import java.security.Security;

/**
 *
 * @author Marcelo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Main().teste();
    }

    private void teste() {
        try {

//            String codigoEstado = "42";
//
//            URL url = new URL("https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/nferecepcao/NfeRecepcao2.asmx");
//
            String caminhoDoCertificadoDoCliente = getClass().getResource("/projetosteste/certificados/Teste.pfx").getFile();
            String senhaDoCertificadoDoCliente = "1234";
            String arquivoCacertsGeradoParaCadaEstado = getClass().getResource("/projetosteste/certificados/jssecacerts").getFile();

            String nfeCabecMsg =
                    "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<cabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe\" "
                    + "versao=\"1.02\">" + "<versaoDados>1.07</versaoDados>"
                    + "</cabecMsg>";

            String nfeDadosMsg =
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<consStatServ " + " versao=\"1.07\""
                    + " xmlns=\"http://www.portalfiscal.inf.br/nfe\">"
                    + "<tpAmb>2</tpAmb>" + "<cUF>53</cUF>"
                    + "<xServ>STATUS</xServ>" + "</consStatServ>";



            /**
             * Informações do Certificado Digital.
             */
            System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
            System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());


            System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");

            System.clearProperty("javax.net.ssl.keyStore");
            System.clearProperty("javax.net.ssl.keyStorePassword");
            System.clearProperty("javax.net.ssl.trustStore");

            System.setProperty("javax.net.ssl.keyStore", caminhoDoCertificadoDoCliente);
            System.setProperty("javax.net.ssl.keyStorePassword", senhaDoCertificadoDoCliente);

            System.setProperty("javax.net.ssl.trustStoreType", "JKS");
            System.setProperty("javax.net.ssl.trustStore", arquivoCacertsGeradoParaCadaEstado);

            NfeStatusServico service = new NfeStatusServico();
            try {
                NfeStatusServicoSoap nfeStatus = service.getNfeStatusServicoSoap();
                System.out.println(nfeStatus.nfeStatusServicoNF(nfeCabecMsg,
                        nfeDadosMsg));

//                SSLServerSocketFactory sssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
//                SSLServerSocket ss = (SSLServerSocket) sssf.createServerSocket(4444);
//                SSLSocket s = (SSLSocket) ss.accept();
//                BufferedReader buff = new BufferedReader(new InputStreamReader(s.getInputStream()));
//                String line;
//                
//                while (buff.ready()) {                    
//                    System.out.println(buff.readLine());
//                }
                
                
//                Thread t = new Thread(new Writer(s));
//                t.start();
            } catch (Throwable e1) {
                e1.printStackTrace();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
