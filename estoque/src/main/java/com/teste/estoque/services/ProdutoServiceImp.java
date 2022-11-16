import com.teste.estoque.entities.Produto;
import com.teste.estoque.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<Produto> getProdutos() {
        List<Produto> produtos = new ArrayList<>();
        produtoRepository.findAll().forEach(produtos::add);
        return produtos;
    }

    @Override
    public Produto getProdutoById(Long id) {
        return produtoRepository.findById(id).get();
    }

    @Override
    public Produto insert(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public void updateProduto(Long id, Produto produto) {
        /*Mudar os métodos. NÃO ESQUECE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! */
        Produto produtoFromDb = produtoRepository.findById(id).get();
        System.out.println(produtoFromDb.toString());
        produtoFromDb.setProdutoStatus(produto.getProdutoStatus());
        produtoFromDb.setDescription(produto.getDescription());
        produtoFromDb.setTitle(produto.getTitle());
        produtoRepository.save(produtoFromDb);
    }

    @Override
    public void deleteProduto(Long produtoId) {
        produtoRepository.deleteById(produtoId);
    }
}
