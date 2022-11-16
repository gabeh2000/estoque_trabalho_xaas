import com.teste.estoque.entities.Produto;
import com.teste.estoque.services.ProdutoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {
    ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    //The function receives a GET request, processes it and gives back a list of Produto as a response.
    @GetMapping
    public ResponseEntity<List<Produto>> getAllProdutos() {
        List<Produto> produtos = produtoService.getProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }
    //The function receives a GET request, processes it, and gives back a list of Produto as a response.
    @GetMapping({"/{produtoId}"})
    public ResponseEntity<Produto> getProduto(@PathVariable Long produtoId) {
        return new ResponseEntity<>(produtoService.getProdutoById(produtoId), HttpStatus.OK);
    }
    //The function receives a POST request, processes it, creates a new Produto and saves it to the database, and returns a resource link to the created produto.           @PostMapping
    public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto) {
        Produto produto1 = produtoService.insert(produto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("produto", "/api/v1/produto/" + produto1.getId().toString());
        return new ResponseEntity<>(produto1, httpHeaders, HttpStatus.CREATED);
    }
    //The function receives a PUT request, updates the Produto with the specified Id and returns the updated Produto
    @PutMapping({"/{produtoId}"})
    public ResponseEntity<Produto> updateProduto(@PathVariable("produtoId") Long produtoId, @RequestBody Produto produto) {
        produtoService.updateProduto(produtoId, produto);
        return new ResponseEntity<>(produtoService.getProdutoById(produtoId), HttpStatus.OK);
    }
    //The function receives a DELETE request, deletes the Produto with the specified Id.
    @DeleteMapping({"/{produtoId}"})
    public ResponseEntity<Produto> deleteProduto(@PathVariable("produtoId") Long produtoId) {
        produtoService.deleteProduto(produtoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
