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
package br.pr.corumbatai.pm.nfse.persistencia.dao;

import br.pr.corumbatai.pm.nfse.persistencia.DaoGenerico;
import br.pr.corumbatai.pm.nfse.persistencia.entidades.Municipio;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

/**
 *
 * @author Marcelo Lopes da Silva
 */
public class DaoMunicipio extends DaoGenerico<Municipio> {

    public List<Municipio> getMunicipiosPorNome(String nome, boolean any) {
        Criteria c = getCriteria();
        c.add(Expression.like("distrito", nome, any ? MatchMode.ANYWHERE : MatchMode.START));
        return c.list();
    }

    public Municipio getMunicipioCodAndUF(String cod, String uf) {
        Criteria c = getCriteria();
        c.add(Expression.eq("municipioId", cod));
        c.add(Expression.eq("ufId", uf));
        return (Municipio) c.uniqueResult();
    }
}
