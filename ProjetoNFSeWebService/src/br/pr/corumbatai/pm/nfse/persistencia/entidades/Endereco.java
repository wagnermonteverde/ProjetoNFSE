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

import br.pr.corumbatai.pm.nfse.webservice.XmlParser;
import com.m4Rc310.nfse.TcEndereco;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.apache.xmlbeans.XmlObject;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Marcelo Lopes da Silva
 */
@Entity
public class Endereco extends Entidade implements XmlParser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cep;
    
    @ManyToOne() 
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Municipio municipio;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

//    public TcEndereco toTcEndereco(){
//        
//   }
    @Override
    public XmlObject toXmlObject() {
        TcEndereco res = TcEndereco.Factory.newInstance();
        res.setBairro(getBairro());
        res.setNumero(getNumero() + "");
        res.setEndereco(getLogradouro());
        if (getMunicipio() != null) {
            res.setMunicipio(getMunicipio().getDistrito());
            Integer codMun = Integer.parseInt(getMunicipio().getMunicipioId());
            res.setCodigoMunicipio(codMun);
        }
        res.setComplemento(getComplemento());
        res.setCep(Integer.parseInt(getCep().replace("-", "").replace(".", "")));
        return res;
    }
    
    @Override
    public void fromXmlObject(XmlObject xo) {
        TcEndereco res = (TcEndereco) xo;
        setBairro(res.getBairro());
        setCep(res.getCep() + "");
        if (getMunicipio() != null) {
            Municipio m = new Municipio();
            m.setDistrito(res.getMunicipio());
            m.setMunicipioId(res.getCodigoMunicipio() + "");
//            m.setUf(res.getUf().toString());
            setMunicipio(m);
        }
        setComplemento(res.getComplemento());
        setLogradouro(res.getEndereco());
        setNumero(Integer.parseInt(res.getNumero()));
    }

    /**
     * @return the municipio
     */
    public Municipio getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
}
