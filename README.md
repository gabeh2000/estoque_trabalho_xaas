# estoque_trabalho_xaas
Repositório pro trabalho de xaas

# Rodando os códigos

Então, primeiro é importante ir no aplication.yaml do estoque e da reserva e mexer no campo uri mongodb+srv://<trocar o user>:<trocar a senha>@cluster0.wrc5ilu.mongodb.net/?retryWrites=true&w=majority. O mongo Atlas gera uma uri sozinho pra ti, é só copiar e colar ali e colocar o user e senha nos respectivos campos.

Agora é executar o estoque e a reserva. Pra adicionar produtos e reservas é através do POST request mesmo.

Por fim execute o central. Ele fica rodando e verificando se um produto está com pouco estoque(baseado no valor limite da reserva) e aumenta(ou "compra") as unidades do protudo caso necessário. Infelizmente o registro das compras ainda não foi implementado por conta de algumas difiuldades.

# Os jsons

Aqui temos alguns exemplos dos jsons pra cada uma das apis

## Produto

{
        "produtoId": "rb28389r9",
        "nome": "bala juquinha",
        "qtd": 3000,
        "preco": 0.5
}

O produtoId é o identificador gerado aleatóriamente, portanto não precisa ser adicionado no POST request.

O nome é o nome do produto.

O qtd são quantas unidades do produto tem em estoque.

O preco é quanto custa cada unidade do produto.

## Reserva

{
        "reservaId": "qb3ur38bf",
        "produto": "bala juquinha",
        "limite": 500,
        "qtd_reservada": 3000
}

O reservaId é o identificador gerado aleatóriamente, portanto não precisa ser adicionado no POST request.

O produti é o nome do produto a qual essa reserva se aplica. É muito importante que este campo seja preenchido da mesma forma(igualzinho) ao do produto que a reserva se relaciona.

O limite é quantas unidades do produto tem que ter para não ter uma ordem de compra. Ex. se tiverem menos de 500 balas juquinha em estoque o programa vai comprar mais bala juquinha.

O qtd_reservada é quantas unidades serão compradas caso tenham menos unidades no estoque do que no limite.
