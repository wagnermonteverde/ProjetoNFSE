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
package br.pr.corumbatai.pm.nfse.persistencia.entidades;

import com.m4Rc310.nfse.ConsultarPessoaRespostaDocument.ConsultarPessoaResposta;
import com.m4Rc310.nfse.TcDados;
import com.m4Rc310.nfse.TcIdentificacao;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import org.apache.xmlbeans.XmlObject;

/**
 *
 * @author Marcelo Lopes da Silva
 */
@Entity
public class PessoaFisica extends Pessoa implements Serializable {

    @Column(unique = true)
    private String cpf;
    private String nome;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the dataNascimento
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

//    @Override
//    public ConsultarPessoaResposta toConsultarPessoaResposta() {
//    }
    @Override
    public XmlObject toXmlObject() {
        ConsultarPessoaResposta res = (ConsultarPessoaResposta) super.toXmlObject(); //To change body of generated methods, choose Tools | Templates.
        TcIdentificacao ide = res.getDados().addNewIdentificacao();
        ide.addNewCpfCnpj().setCpfCnpj(getCpf());
        TcDados dados = res.getDados();
        dados.setRazaoSocial(getNome());
        return res;
    }

    @Override
    public void fromXmlObject(XmlObject xo) {
        super.fromXmlObject(xo);
        TcDados d = (TcDados) xo;
        if (d != null) {
            TcIdentificacao ide = d.getIdentificacao();
            if (ide != null) {
                setCpf(ide.getCpfCnpj().getCpfCnpj());
                setNome(d.getRazaoSocial());
            }
        }
    }
}
