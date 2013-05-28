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

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Marcelo Lopes da Silva
 */
//@Embeddable
@Entity 
public class Municipio implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ufId;
    private String uf;
    private String municipioId;
    private String municipio;
    private String mesoregiaoId;
    private String mesoregiao;
    private String microregiaoId;
    private String microregiao;
    private String distritoId;
    private String distrito;
    private String subdistritoId;
    private String subdistrito;


    /**
     * @return the ufId
     */
    public String getUfId() {
        return ufId;
    }

    /**
     * @param ufId the ufId to set
     */
    public void setUfId(String ufId) {
        this.ufId = ufId;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * @return the municipioId
     */
    public String getMunicipioId() {
        return municipioId;
    }

    /**
     * @param municipioId the municipioId to set
     */
    public void setMunicipioId(String municipioId) {
        this.municipioId = municipioId;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the mesoregiaoId
     */
    public String getMesoregiaoId() {
        return mesoregiaoId;
    }

    /**
     * @param mesoregiaoId the mesoregiaoId to set
     */
    public void setMesoregiaoId(String mesoregiaoId) {
        this.mesoregiaoId = mesoregiaoId;
    }

    /**
     * @return the mesoregiao
     */
    public String getMesoregiao() {
        return mesoregiao;
    }

    /**
     * @param mesoregiao the mesoregiao to set
     */
    public void setMesoregiao(String mesoregiao) {
        this.mesoregiao = mesoregiao;
    }

    /**
     * @return the microregiaoId
     */
    public String getMicroregiaoId() {
        return microregiaoId;
    }

    /**
     * @param microregiaoId the microregiaoId to set
     */
    public void setMicroregiaoId(String microregiaoId) {
        this.microregiaoId = microregiaoId;
    }

    /**
     * @return the microregiao
     */
    public String getMicroregiao() {
        return microregiao;
    }

    /**
     * @param microregiao the microregiao to set
     */
    public void setMicroregiao(String microregiao) {
        this.microregiao = microregiao;
    }

    /**
     * @return the distritoId
     */
    public String getDistritoId() {
        return distritoId;
    }

    /**
     * @param distritoId the distritoId to set
     */
    public void setDistritoId(String distritoId) {
        this.distritoId = distritoId;
    }

    /**
     * @return the distrito
     */
    public String getDistrito() {
        return distrito;
    }

    /**
     * @param distrito the distrito to set
     */
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    /**
     * @return the subdistritoId
     */
    public String getSubdistritoId() {
        return subdistritoId;
    }

    /**
     * @param subdistritoId the subdistritoId to set
     */
    public void setSubdistritoId(String subdistritoId) {
        this.subdistritoId = subdistritoId;
    }

    /**
     * @return the subdistrito
     */
    public String getSubdistrito() {
        return subdistrito;
    }

    /**
     * @param subdistrito the subdistrito to set
     */
    public void setSubdistrito(String subdistrito) {
        this.subdistrito = subdistrito;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

}
