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
package br.pr.corumbatai.pm.nfse.webservice.processos;

import br.pr.corumbatai.pm.nfse.persistencia.dao.DaoPessoa;
import br.pr.corumbatai.pm.nfse.persistencia.entidades.Pessoa;
import com.m4Rc310.nfse.ConsultarPessoaEnvioDocument;
import com.m4Rc310.nfse.ConsultarPessoaRespostaDocument;
import com.m4Rc310.nfse.TcMensagemRetorno;
import org.apache.xmlbeans.XmlException;
import resource.B;

/**
 *
 * @author marcelo
 */
public class ProcessPesquisarPessoa implements WsProcess{

    @Override
    public String process(String xml) {
        ConsultarPessoaRespostaDocument.ConsultarPessoaResposta res = ConsultarPessoaRespostaDocument.ConsultarPessoaResposta.Factory.newInstance();
        String cpfcnpj = "";
        try {
            ConsultarPessoaEnvioDocument cped = ConsultarPessoaEnvioDocument.Factory.parse(xml);
            cpfcnpj = cped.getConsultarPessoaEnvio().getCpfCnpj();
            Pessoa pessoa = new DaoPessoa().getPessoaPorDocumento(cpfcnpj);
            res = (ConsultarPessoaRespostaDocument.ConsultarPessoaResposta) pessoa.toXmlObject();
            return res.xmlText();
        } catch (XmlException e) {
            TcMensagemRetorno msg = res.addNewListaMensagemRetorno().addNewMensagemRetorno();
            msg.setCodigo("1");
            msg.setMensagem(B.getString("xml.invalido"));
            msg.setCorrecao(B.getString("xml.invalido_"));
            return res.xmlText();
        }catch(NullPointerException e){
            TcMensagemRetorno msg = res.addNewListaMensagemRetorno().addNewMensagemRetorno();
            msg.setMensagem(B.getString("pessoa.nao.cadastrada", cpfcnpj));
            return res.xmlText();
        }catch(RuntimeException e){
            TcMensagemRetorno msg = res.addNewListaMensagemRetorno().addNewMensagemRetorno();
            msg.setCodigo("2");
            msg.setMensagem(B.getString(e.getMessage()));
            msg.setCorrecao(B.getString(e.getMessage()));
            return res.xmlText();
        }
    }
    
}
