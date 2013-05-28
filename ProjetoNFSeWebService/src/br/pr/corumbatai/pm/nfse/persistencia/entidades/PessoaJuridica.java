/*
 * Copyright 2013 Wagner.
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
package br.pr.corumbatai.pm.nfse.persistencia.entidades;

import com.m4Rc310.nfse.ConsultarPessoaRespostaDocument;
import com.m4Rc310.nfse.ConsultarPessoaRespostaDocument.ConsultarPessoaResposta;
import com.m4Rc310.nfse.TcDados;
import com.m4Rc310.nfse.TcIdentificacao;
import java.io.Serializable;
import javax.persistence.Entity;
import org.apache.xmlbeans.XmlObject;

/**
 *
 * @author wagner
 */
@Entity
public class PessoaJuridica extends Pessoa implements Serializable {

    private String cnpj;
    private String nomeRazaoSocial;
    private String nomeFantasia;
    private String inscricaoMunicipal;

    /**
     * @return the nomeRazaoSocial
     */
    public String getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }

    /**
     * @param nomeRazaoSocial the nomeRazaoSocial to set
     */
    public void setNomeRazaoSocial(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public XmlObject toXmlObject() {
        ConsultarPessoaResposta res = (ConsultarPessoaResposta) super.toXmlObject();
        TcIdentificacao ide = res.getDados().addNewIdentificacao();
        ide.addNewCpfCnpj().setCpfCnpj(getCnpj());
        TcDados dados = res.getDados();
        dados.setRazaoSocial(getNomeRazaoSocial());
        return res;
    }

    @Override
    public void fromXmlObject(XmlObject xo) {
         super.fromXmlObject(xo);
        TcDados d = (TcDados) xo;
        if (d != null) {
            TcIdentificacao ide = d.getIdentificacao();
            if (ide != null) {
                setCnpj(ide.getCpfCnpj().getCpfCnpj());
                setNomeRazaoSocial(d.getRazaoSocial());
                setInscricaoMunicipal(ide.getInscricaoMunicipal());
            }
        }
    }

    /**
     * @return the nomeFantasia
     */
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    /**
     * @param nomeFantasia the nomeFantasia to set
     */
    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    /**
     * @return the inscricaoMunicipal
     */
    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    /**
     * @param inscricaoMunicipal the inscricaoMunicipal to set
     */
    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }
}
