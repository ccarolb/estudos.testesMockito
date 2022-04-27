package leilao;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.model.Leilao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelloWorldMockito {

    @Test
    public void HelloWorld() {
        //LeilaoDao = classe que queremos mockar e que representa uma dependência de banco de dados.
        //mockito.mock retorna um objeto da classe LeilaoDao, que guardamos na variável mock
        LeilaoDao mock = Mockito.mock(LeilaoDao.class);

                                //por meio da variável mock acessamos os métodos de LeilaoDao
        List<Leilao> todos = mock.buscarTodos();
    //mock.buscarTodos(); nos devolve uma lista vazia (por ser mockado, se não fosse mockado não seria vazia).

        //verificando se buscarTodos() realmente retornou uma lista vazia.
        assertTrue(todos.isEmpty());
    }
}
