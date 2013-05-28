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

import br.pr.corumbatai.pm.nfse.utils.DateUtils;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Marcelo Lopes da Silva
 */
@MappedSuperclass
public class Entidade implements Serializable{
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    public Entidade() {
        dataCadastro = DateUtils.getDateNow();
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }
}
