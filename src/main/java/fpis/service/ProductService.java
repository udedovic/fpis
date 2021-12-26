package fpis.service;

import fpis.domain.Product;
import fpis.dto.ProductDTO;
import fpis.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Integer getNewId() {
        Integer newId = productRepository.getNewId();
        return newId == null ? 1 : newId + 1;
    }

    public ProductDTO getProduct(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.map(this::fromEntity).orElse(null);
    }

    @Transactional
    public Boolean insertProduct(ProductDTO productDTO) {
        Product product = toEntity(productDTO);
        productRepository.save(product);
        return true;
    }

    @Transactional
    public Boolean updateProduct(ProductDTO productDTO) {
        Product source = toEntity(productDTO);
        Optional<Product> targetOptional = productRepository.findById(productDTO.getSifraProizvoda());
        if (targetOptional.isPresent()) {
            Product target = targetOptional.get();
            BeanUtils.copyProperties(source, target, "sifra");
            productRepository.save(target);
            return true;
        }
        return false;
    }

    private Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setSifra(productDTO.getSifraProizvoda());
        product.setDuzina(productDTO.getDuzinaProizvoda());
        product.setSirina(productDTO.getSirinaProizvoda());
        product.setVisina(productDTO.getVisinaProizvoda());
        product.setDebljinaStakla(productDTO.getDebljinaStakla());
        return product;
    }

    private ProductDTO fromEntity(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setSifraProizvoda(product.getSifra());
        dto.setDuzinaProizvoda(product.getDuzina());
        dto.setSirinaProizvoda(product.getSirina());
        dto.setVisinaProizvoda(product.getVisina());
        dto.setDebljinaStakla(product.getDebljinaStakla());
        return dto;
    }
}
