/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pr.corumbatai.pm.nfse.persistencia.entidades;

import br.pr.corumbatai.pm.nfse.webservice.XmlParser;
import com.m4Rc310.nfse.ConsultarPessoaRespostaDocument;
import com.m4Rc310.nfse.ConsultarPessoaRespostaDocument.ConsultarPessoaResposta;
import com.m4Rc310.nfse.TcContato;
import com.m4Rc310.nfse.TcDados;
import com.m4Rc310.nfse.TcEndereco;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import org.apache.xmlbeans.XmlObject;

/**
 *
 * @author Marcelo
 */
@MappedSuperclass
public class Pessoa extends Entidade implements Serializable, XmlParser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String telefone;
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

//    public ConsultarPessoaResposta toConsultarPessoaResposta() {
//        ConsultarPessoaResposta res = ConsultarPessoaRespostaDocument.ConsultarPessoaResposta.Factory.newInstance();
//        TcDados dados = res.addNewDados();
//        if (getEndereco() != null) {
//            dados.setEndereco(getEndereco().toTcEndereco());
//        }
//        
//        dados.addNewContato();
//        dados.getContato().setEmail(email);
//        dados.getContato().setTelefone(telefone);
//        
//        return res;
//    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public XmlObject toXmlObject() {
        ConsultarPessoaResposta res = ConsultarPessoaRespostaDocument.ConsultarPessoaResposta.Factory.newInstance();
        TcDados dados = res.addNewDados();
        if (getEndereco() != null) {
            dados.setEndereco((TcEndereco) getEndereco().toXmlObject());
        }
        
        dados.addNewContato();
        dados.getContato().setEmail(email);
        dados.getContato().setTelefone(telefone);
        
        return res;
    }

    @Override
    public void fromXmlObject(XmlObject xo) {
        TcDados d = (TcDados) xo;
        if (d != null) {
            TcContato con = d.getContato();
            setEmail(con.getEmail());
            setTelefone(con.getTelefone());
        }
        if (d.getEndereco() != null) {
            endereco = getEndereco() == null ? new Endereco() : endereco;
            endereco.fromXmlObject(d.getEndereco());
        }
    }
}
