package fpis.controller;

import fpis.domain.Product;
import fpis.dto.ProductDTO;
import fpis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/new-id")
    public Integer getNewId() {
        return productService.getNewId();
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable("id") Integer id) {
        return productService.getProduct(id);
    }

    @PostMapping("/")
    public Boolean insertProduct(@RequestBody ProductDTO productDTO) {
        try {
            return productService.insertProduct(productDTO);
        } catch (Exception e) {
            return false;
        }
    }

    @PutMapping("/")
    public Boolean updateProduct(@RequestBody ProductDTO productDTO) {
        try {
            return productService.updateProduct(productDTO);
        } catch (Exception e) {
            return false;
        }
    }
}
