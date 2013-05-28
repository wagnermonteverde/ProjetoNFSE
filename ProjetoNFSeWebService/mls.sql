
    alter table Endereco 
        drop 
        foreign key FK6B07CBE95900BD67

    alter table PessoaFisica 
        drop 
        foreign key FK10C9871EC470F4AD

    alter table PessoaJuridica 
        drop 
        foreign key FK1D1F784CC470F4AD

    drop table if exists Endereco

    drop table if exists Municipio

    drop table if exists PessoaFisica

    drop table if exists PessoaJuridica

    create table Endereco (
        id bigint not null auto_increment,
        dataCadastro datetime,
        bairro varchar(255),
        cep varchar(255),
        complemento varchar(255),
        logradouro varchar(255),
        numero integer not null,
        municipio_id bigint,
        primary key (id)
    )

    create table Municipio (
        id bigint not null auto_increment,
        distrito varchar(255),
        distritoId varchar(255),
        mesoregiao varchar(255),
        mesoregiaoId varchar(255),
        microregiao varchar(255),
        microregiaoId varchar(255),
        municipio varchar(255),
        municipioId varchar(255),
        subdistrito varchar(255),
        subdistritoId varchar(255),
        uf varchar(255),
        ufId varchar(255),
        primary key (id)
    )

    create table PessoaFisica (
        id bigint not null auto_increment,
        dataCadastro datetime,
        email varchar(255),
        telefone varchar(255),
        cpf varchar(255) unique,
        dataNascimento date,
        nome varchar(255),
        endereco_id bigint,
        primary key (id)
    )

    create table PessoaJuridica (
        id bigint not null auto_increment,
        dataCadastro datetime,
        email varchar(255),
        telefone varchar(255),
        cnpj varchar(255),
        inscricaoMunicipal varchar(255),
        nomeFantasia varchar(255),
        nomeRazaoSocial varchar(255),
        endereco_id bigint,
        primary key (id)
    )

    alter table Endereco 
        add index FK6B07CBE95900BD67 (municipio_id), 
        add constraint FK6B07CBE95900BD67 
        foreign key (municipio_id) 
        references Municipio (id)

    alter table PessoaFisica 
        add index FK10C9871EC470F4AD (endereco_id), 
        add constraint FK10C9871EC470F4AD 
        foreign key (endereco_id) 
        references Endereco (id)

    alter table PessoaJuridica 
        add index FK1D1F784CC470F4AD (endereco_id), 
        add constraint FK1D1F784CC470F4AD 
        foreign key (endereco_id) 
        references Endereco (id)
