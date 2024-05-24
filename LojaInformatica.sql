create database lojainformatica;

use lojainformatica;

create table Cliente(
Id int not null auto_increment
,Nome varchar(200) not null 
,Email varchar(200) not null
,DtNasc DATE not null
,CPF VARCHAR(14) not null
,Telefone VARCHAR(15) not null
,Sexo int not null
,Ativo int not null
,primary key (Id)
);
create table Marca(
Id int not null auto_increment
,Nome varchar(200) not null
,primary key (Id)
);

create table Categorias(
Id int not null auto_increment
,Nome varchar(200) not null
,primary key (Id)
);
create table Produto(
Id int not null auto_increment
,Id_Marca int not null
,Id_Categoria int not null
,Produto varchar(200) not null
,Preco decimal(10,2) not null
,Qtd int not null
,Ativo int not null
,primary key (Id)
,constraint fk_Produto_Marca
foreign key (Id_Marca) references Marca(Id)
,constraint fk_Produto_Categorias
foreign key (Id_Categoria) references Categorias(Id)
);

create table Venda(
Id int not null auto_increment
,Id_Cliente int not null
,Valor double(10,2) not null
,Dt date not null
,primary key (Id)
,constraint fk_Venda_Cliente
foreign key (Id_Cliente) references Cliente(Id)
);

create table VendaProduto(
Id int not null auto_increment
,Id_Venda int not null
,Id_Produto int not null
,Qtd int not null
,Total double(10,2) not null
,primary key (Id)
,constraint fk_VendaProduto_Venda
foreign key (Id_Venda) references Venda(Id)
,constraint fk_VendaProduto_Produto
foreign key (Id_Produto) references Produto(Id)
);
