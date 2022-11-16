import com.teste.estoque.entities.Produto;
import com.teste.estoque.entities.ProdutoStatus;
import com.teste.estoque.repositories.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
/*Tem que trocar os valores aqui!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! */
@Component
public class ProdutoLoader implements CommandLineRunner {
    public final ProdutoRepository produtoRepository;

    public ProdutoLoader(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadProdutos();
    }

    private void loadProdutos() {
        if (produtoRepository.count() == 0) {
            produtoRepository.save(
                    Produto.builder()
                            .title("Go to market")
                            .description("Buy eggs from market")
                            .produtoStatus(ProdutoStatus.NOT_COMPLETED)
                            .build()
            );
            produtoRepository.save(
                    Produto.builder()
                            .title("Go to school")
                            .description("Complete assignments")
                            .produtoStatus(ProdutoStatus.NOT_COMPLETED)
                            .build()
            );
            System.out.println("Sample Produtos Loaded");
        }
    }
}
