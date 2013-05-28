/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pr.corumbatai.pm.nfse.persistencia.dao;

import br.pr.corumbatai.pm.nfse.persistencia.DaoGenerico;
import br.pr.corumbatai.pm.nfse.persistencia.entidades.PessoaFisica;
import br.pr.corumbatai.pm.nfse.persistencia.entidades.PessoaJuridica;
import br.pr.corumbatai.pm.nfse.persistencia.entidades.Pessoa;
import java.util.List;
import org.brazilutils.br.cpfcnpj.CpfCnpj;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import resource.B;

/**
 *
 * @author Marcelo
 */
public class DaoPessoa extends DaoGenerico<Pessoa> {

    private DaoGenerico<PessoaJuridica> subDaoPessoaJuridica;
    private DaoGenerico<PessoaFisica> subDaoPessoaFisica;

    public DaoPessoa() {
        subDaoPessoaJuridica = new DaoGenerico<PessoaJuridica>() {
        };
        subDaoPessoaFisica = new DaoGenerico<PessoaFisica>() {
        };
    }

    public Pessoa getPessoaPorDocumento(String cc) throws RuntimeException {
        try {
            Criteria c;
            CpfCnpj cpfCnpj = new CpfCnpj(cc);
            if (cpfCnpj.isCpf()) {
                c = subDaoPessoaFisica.getCriteria();
                c.add(Expression.eq("cpf", cpfCnpj.getNumber()));
                return (Pessoa) c.uniqueResult();
            }
            if (cpfCnpj.isCnpj()) {
                c = subDaoPessoaJuridica.getCriteria();
                c.add(Expression.eq("cnpj", cpfCnpj.getNumber()));
                return (Pessoa) c.uniqueResult();
            }
            
            throw new Exception(B.getString("nao.eh.cpf.cnpj", cc));
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Pessoa> getPessoasPorNomeRazao(String nome, boolean any) {
        Criteria c = subDaoPessoaJuridica.getCriteria();
        c.add(Expression.ilike("nomeRazaoSocial", nome, any ? MatchMode.ANYWHERE : MatchMode.EXACT));
        return c.list();
    }

    public List<Pessoa> getPessoasPorNome(String nome, boolean any) {
        Criteria c = subDaoPessoaFisica.getCriteria();
        c.add(Expression.ilike("nome", nome, any ? MatchMode.ANYWHERE : MatchMode.EXACT));
        return c.list();
    }

    @Override
    public void persist(Pessoa t) throws RuntimeException {
        validar(t == null, "erro.entidade.nula");
//        validar(t.getNomeRazaoSocial() == null, "erro.pessoa.nome.branco");
//        validar(t.getNomeRazaoSocial().isEmpty(), "erro.pessoa.nome.branco");
        super.persist(t); //To change body of generated methods, choose Tools | Templates.
    }
}
