import java.util.List;

public interface ProdutoService {
    List<Produto> getTodos();

    Produto getTodoById(Long id);

    Produto insert(Produto produto);

    void updateTodo(Long id, Produto produto);

    void deleteTodo(Long produtoId);
}
