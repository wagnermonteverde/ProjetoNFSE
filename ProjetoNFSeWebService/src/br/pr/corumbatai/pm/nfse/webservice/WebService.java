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

import br.pr.corumbatai.pm.nfse.webservice.processos.ProcessCadastrarPessoa;
import br.pr.corumbatai.pm.nfse.webservice.processos.ProcessPesquisarPessoa;
import br.pr.corumbatai.pm.nfse.webservice.processos.WsProcessManager;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Marcelo Lopes da Silva
 */
@javax.jws.WebService
public class WebService {
    @WebMethod()
    public String cadastrarPessoa(@WebParam String  cc){
        return WsProcessManager.getInstance().getWsProcess(ProcessCadastrarPessoa.class).process(cc);
    }
    @WebMethod()
    public String pesquisarPessoa(@WebParam String  cc){
        return WsProcessManager.getInstance().getWsProcess(ProcessPesquisarPessoa.class).process(cc);
    }
}
