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
import br.pr.corumbatai.pm.nfse.persistencia.entidades.PessoaFisica;
import br.pr.corumbatai.pm.nfse.persistencia.entidades.PessoaJuridica;
import br.pr.corumbatai.pm.nfse.utils.NFSeUtils;
import br.pr.corumbatai.pm.nfse.utils.log.MLog;
import com.m4Rc310.nfse.CadastroPessoaEnvioDocument.CadastroPessoaEnvio;
import com.m4Rc310.nfse.CadastroPessoaRespostaDocument.CadastroPessoaResposta;
import com.m4Rc310.nfse.TcDados;
import com.m4Rc310.nfse.TcMensagemRetorno;
import java.util.Calendar;
import org.brazilutils.br.cpfcnpj.CpfCnpj;
import resource.B;

/**
 *
 * @author Marcelo Lopes da Silva
 */
public class Validador {

    private MLog log = MLog.getInstance(this);
    
    public CadastroPessoaResposta processaCadastroPessoa(CadastroPessoaEnvio envio) {
        DaoPessoa daoPessoa = new DaoPessoa();

        CadastroPessoaResposta resposta = CadastroPessoaResposta.Factory.newInstance();
        addMsgRetorno(envio.getCpfCnpj() == null, resposta, "0", B.getString("cpf.ou.cnpj.nullo"), B.getString("cpf.ou.cnpj.nullo.correcao"));
        addMsgRetorno(!CpfCnpj.isValid(envio.getCpfCnpj()), resposta, "1", B.getString("cpf.cnpj.invalido", envio.getCpfCnpj()), B.getString("cpf.cnpj.invalido.correcao"));
        addMsgRetorno(envio.getPessoa() == null, resposta, "2", B.getString("dado.pessoa.nullo"), B.getString("dado.pessoa.nullo.correcao"));
        String cc1 = envio.getCpfCnpj();
        String cc2 = envio.getPessoa().getIdentificacao().getCpfCnpj().getCpfCnpj();
        addMsgRetorno(!cc1.equals(cc2), resposta, "4", B.getString("cpf.cnpj.validacao.nao.conferem", cc1, cc2), B.getString("cpf.cnpj.validacao.nao.conferem.correcao"));

        TcDados dados = envio.getPessoa();

        Pessoa pessoa = null;

        if (dados != null) {
            try {
                CpfCnpj cc = new CpfCnpj(envio.getCpfCnpj());
                pessoa = daoPessoa.getPessoaPorDocumento(cc.getCpfCnpj());
                if (pessoa == null) {
                    pessoa = cc.isCpf() ? new PessoaFisica() : new PessoaJuridica();
                }
                pessoa.fromXmlObject(dados);
            } catch (Exception e) {
                addMsgRetorno(true, resposta, "3", B.getString(e.getMessage()), B.getString("erro.sistema"));
            }
        }
        
        if (resposta.isSetListaMensagemRetorno()) {
            log.debug("retornando sequencia de divergencias que impedem a persistencia");
            return resposta;
        }

        log.debug("persistindo a entidade {0}.", pessoa);
        daoPessoa.persist(pessoa);
        resposta.setDataRecebimento(Calendar.getInstance());
        resposta.setStatus(B.getString("pessoa.cadastrada.sucesso", envio.getCpfCnpj()));
        
        String protocolo = NFSeUtils.getNewProtocolNumber(1l);
        log.info("Sucesso! Operação referente ao protocolo: {0}", protocolo);
        resposta.setProtocolo(protocolo);
        
        return resposta;
    }

    private void addMsgRetorno(boolean condicao, CadastroPessoaResposta resposta, String codErro, String mensagem, String correcao) {
        if (condicao) {
            TcMensagemRetorno msg = resposta.isSetListaMensagemRetorno()
                    ? resposta.getListaMensagemRetorno().addNewMensagemRetorno()
                    : resposta.addNewListaMensagemRetorno().addNewMensagemRetorno();

            msg.setCodigo(codErro);
            msg.setCorrecao(correcao);
            msg.setMensagem(mensagem);
        }
    }

    private String mostrarDivergencia(String a, String b) {
        String maior = b.length() > a.length() ? b : a;
        String menor = maior.equals(a) ? b : a;

        StringBuilder sb = new StringBuilder();
        StringBuilder di = new StringBuilder();
        boolean c = false;
        int err = 0;
        char[] cMenor = menor.toCharArray();
        for (int i = 0; i < cMenor.length; i++) {
            char ca = cMenor[i];
            sb.append(ca);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Validador().mostrarDivergencia("03057532900", "03057232901"));
    }
}
