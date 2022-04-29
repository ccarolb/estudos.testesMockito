package leilao;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.service.FinalizarLeilaoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FinalizarLeilaoServiceTest {

    @Mock
    private LeilaoDao leilaoDao;

    @InjectMocks
    private FinalizarLeilaoService finalizarLeilaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveFinalizarLeilao() {
        //Arrange
        List<Leilao> leiloes = leiloes();
        when(leilaoDao.buscarLeiloesExpirados())
                .thenReturn(leiloes);

        //Act
        finalizarLeilaoService.finalizarLeiloesExpirados();

        //Assert
        Leilao leilao = leiloes.get(0);
        BigDecimal lanceVencedor = new BigDecimal(900);
        assertEquals(lanceVencedor, leilao.getLanceVencedor().getValor());
        assertTrue(leilao.isFechado());

        verify(leilaoDao).salvar(leilao);
    }


//Lista mockada
    private List<Leilao> leiloes() {
        List<Leilao> lista = new ArrayList<>();

        Leilao leilao = new Leilao("Celular",
                new BigDecimal("500"),
                new Usuario("Fulano"));

        Lance primeiro = new Lance(new Usuario("Beltrano"),
                new BigDecimal("600"));
        Lance segundo = new Lance(new Usuario("Ciclano"),
                new BigDecimal("900"));

        leilao.propoe(primeiro);
        leilao.propoe(segundo);

        lista.add(leilao);

        return lista;

    }
}
