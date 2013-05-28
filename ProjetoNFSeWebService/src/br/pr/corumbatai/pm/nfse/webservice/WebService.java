/*
 * Copyright 2013 Marcelo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.pr.corumbatai.pm.nfse.webservice;

import br.pr.corumbatai.pm.nfse.persistencia.dao.DaoPessoa;
import br.pr.corumbatai.pm.nfse.persistencia.entidades.Pessoa;
import com.m4Rc310.nfse.CadastroPessoaEnvioDocument;
import com.m4Rc310.nfse.CadastroPessoaRespostaDocument.CadastroPessoaResposta;
import com.m4Rc310.nfse.ConsultarPessoaEnvioDocument;
import com.m4Rc310.nfse.ConsultarPessoaRespostaDocument.ConsultarPessoaResposta;
import com.m4Rc310.nfse.TcMensagemRetorno;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.apache.xmlbeans.XmlException;
import resource.B;

/**
 *
 * @author Marcelo Lopes da Silva
 */
@javax.jws.WebService
public class WebService {
    @WebMethod()
    public String cadastrarPessoa(@WebParam String  cc){
        CadastroPessoaResposta cpr = CadastroPessoaResposta.Factory.newInstance();
        try {
            CadastroPessoaEnvioDocument cped = CadastroPessoaEnvioDocument.Factory.parse(cc);
//            cpr = new Validador().processaCadastroPessoa(cped.getCadastroPessoaEnvio());
            
            Pessoa pessoa = new Pessoa();
            pessoa.fromXmlObject(cped.getCadastroPessoaEnvio().getPessoa());
            
            System.out.println(cped.getCadastroPessoaEnvio().getPessoa().getEndereco().getCodigoMunicipio());
            
            return cpr.xmlText();
        } catch (Exception e) {
            TcMensagemRetorno msg = cpr.addNewListaMensagemRetorno().addNewMensagemRetorno();
            msg.setMensagem(e.getMessage());
            return cpr.xmlText();
        }
        
    }
    @WebMethod()
    public String pesquisarPessoa(@WebParam String  cc){
        ConsultarPessoaResposta res = ConsultarPessoaResposta.Factory.newInstance();
        String cpfcnpj = "";
        try {
            ConsultarPessoaEnvioDocument cped = ConsultarPessoaEnvioDocument.Factory.parse(cc);
            cpfcnpj = cped.getConsultarPessoaEnvio().getCpfCnpj();
            Pessoa pessoa = new DaoPessoa().getPessoaPorDocumento(cpfcnpj);
            res = (ConsultarPessoaResposta) pessoa.toXmlObject();
            return res.xmlText();
        } catch (XmlException e) {
            TcMensagemRetorno msg = res.addNewListaMensagemRetorno().addNewMensagemRetorno();
            msg.setCodigo("1");
            msg.setMensagem(B.getString(e.getMessage()));
            msg.setCorrecao(B.getString("xml.invalido"));
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
