package br.com.fiap.fiapstock;

import br.com.fiap.fiapstock.dto.StockDTO;
import br.com.fiap.fiapstock.model.Stock;
import br.com.fiap.fiapstock.repository.StockRepository;
import br.com.fiap.fiapstock.service.StockService;
import br.com.fiap.fiapstock.service.StockServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class FiapstockUnitTests {

    @Test
    public void findAllStocks() {
        // Preparação
        StockRepository stockRepository = Mockito.mock(StockRepository.class);
        List<Stock> stockList = Arrays.asList(new Stock() {{
            setNome("MGLU3");
            setDescricao("Magazine Luiza");
        }});
        Mockito.when(stockRepository.buscaPorNome("MGLU3")).thenReturn(stockList);

        // Execução
        StockService stockService = new StockServiceImpl(null, stockRepository);
        // Verificação
        List<StockDTO> mglu = stockService.findAll("MGLU3");
        Assertions.assertNotNull(mglu);
        Assertions.assertEquals(1, mglu.size());
        Assertions.assertEquals("MGLU3", mglu.get(0).getNome());
        Assertions.assertEquals("Magazine Luiza", mglu.get(0).getDescricao());
    }
}
