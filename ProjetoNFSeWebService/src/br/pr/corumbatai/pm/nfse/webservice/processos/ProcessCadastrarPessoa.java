/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pr.corumbatai.pm.nfse.webservice.processos;

import br.pr.corumbatai.pm.nfse.persistencia.entidades.Pessoa;
import com.m4Rc310.nfse.CadastroPessoaEnvioDocument;
import com.m4Rc310.nfse.CadastroPessoaRespostaDocument;
import com.m4Rc310.nfse.TcMensagemRetorno;

/**
 *
 * @author marcelo
 */
public class ProcessCadastrarPessoa implements WsProcess{

    @Override
    public String process(String xml) {
        CadastroPessoaRespostaDocument.CadastroPessoaResposta cpr = CadastroPessoaRespostaDocument.CadastroPessoaResposta.Factory.newInstance();
        try {
            CadastroPessoaEnvioDocument cped = CadastroPessoaEnvioDocument.Factory.parse(xml);
            Pessoa pessoa = new Pessoa();
            pessoa.fromXmlObject(cped.getCadastroPessoaEnvio().getPessoa());
            return cpr.xmlText();
        } catch (Exception e) {
            TcMensagemRetorno msg = cpr.addNewListaMensagemRetorno().addNewMensagemRetorno();
            msg.setMensagem(e.getMessage());
            return cpr.xmlText();
        }
    }
    
}
