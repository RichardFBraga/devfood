package br.com.devfood.restaurante.util;

import br.com.devfood.restaurante.dao.CardapioDao;
import br.com.devfood.restaurante.dao.CategoriaDao;
import br.com.devfood.restaurante.dao.ClienteDao;
import br.com.devfood.restaurante.dao.OrdemDao;
import br.com.devfood.restaurante.entity.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CargaDeDadosUtil {

    public static void cadastrarCategoria(EntityManager entityManager){
        Categoria entrada = new Categoria("Entradas");
        Categoria salada = new Categoria("Saladas");
        Categoria principal = new Categoria("Prato Principal");

        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        categoriaDao.cadastrar(entrada);
        entityManager.flush();
        categoriaDao.cadastrar(salada);
        entityManager.flush();
        categoriaDao.cadastrar(principal);
        entityManager.flush();
        entityManager.clear();
    }

    public static void cadastrarProdutosCardapio(EntityManager entityManager){
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        CardapioDao cardapioDao = new CardapioDao(entityManager);

        List<Categoria> categorias = categoriaDao.consultarTodos();
        Cardapio moqueca = new Cardapio("Moqueca", "Peixe branco, banana da terra, arroz e farofa",true, BigDecimal.valueOf(95.00), categorias.get(2), LocalDateTime.now());
        Cardapio spaguetti = new Cardapio("Spaguetti", "Spaguetti ao molho parmesão e cogumelos",true, BigDecimal.valueOf(68.00), categorias.get(2), LocalDateTime.now());
        Cardapio bife = new Cardapio("Bife", "Bife acebolado, com farofa e batata frita",true, BigDecimal.valueOf(59.00), categorias.get(2), LocalDateTime.now());
        Cardapio cordeito = new Cardapio("Cordeiro", "Cordeio com risoto de Funghi", true, BigDecimal.valueOf(88.00),categorias.get(2), LocalDateTime.now());
        Cardapio burrata = new Cardapio("Burrata", "Tomates queimados, rúcula e torrada", true, BigDecimal.valueOf(15.00),categorias.get(0), LocalDateTime.now());
        Cardapio bruschetta = new Cardapio("Bruschetta", "Tomate, mucarela e manjericão", true, BigDecimal.valueOf(20.00),categorias.get(0), LocalDateTime.now());
        Cardapio scappeta = new Cardapio("Scappeta", "Ragu de linguiça e torradinhas", true, BigDecimal.valueOf(25.00),categorias.get(0), LocalDateTime.now());
        Cardapio caprese = new Cardapio("Caprese", "Mini rucula e mucarela", true, BigDecimal.valueOf(47.00),categorias.get(1), LocalDateTime.now());
        Cardapio caesar = new Cardapio("Caesar", "Salada de frango com molho caesar", true, BigDecimal.valueOf(40.00),categorias.get(1), LocalDateTime.now());
        Cardapio chevre = new Cardapio("Chevre", "Mix de folhas, mostarda e mel", true, BigDecimal.valueOf(59.00),categorias.get(1), LocalDateTime.now());


        cardapioDao.cadastrar(moqueca );
        cardapioDao.cadastrar(bife);
        cardapioDao.cadastrar(cordeito);
        cardapioDao.cadastrar(burrata);
        cardapioDao.cadastrar(bruschetta);
        cardapioDao.cadastrar(scappeta);
        cardapioDao.cadastrar(caprese);
        cardapioDao.cadastrar(caesar);
        cardapioDao.cadastrar(chevre);
        cardapioDao.cadastrar(spaguetti);
        entityManager.flush();
        entityManager.clear();
    }
    public static void cadastrarClientes(EntityManager entityManager) {
        Endereco endereco1 = new Endereco("3333333333", "Rua A", "Apto 101", "Rio Grande do Sul", "RS");
        Endereco endereco2 = new Endereco("222222222", "Rua B", "Apto 202", "São Paulo", "SP");

        Cliente cliente1 = new Cliente("3131313131","felipecostatemplario1@hotmail.com", "Felipe Fernades");
        cliente1.addEndereco(endereco1);
        Cliente cliente2 = new Cliente("22222223232","anacosta_gataindia@outlook.com", "Ana Lima");
        cliente2.addEndereco(endereco2);

        ClienteDao clienteDao = new ClienteDao(entityManager);




        clienteDao.cadastrar(cliente1);
        clienteDao.cadastrar(cliente2);

        entityManager.flush();
        entityManager.clear();

        System.out.println("Clientes cadastrados com sucesso!");
    }

    public static void cadastrarOrdensClientes(EntityManager entityManager) {
        ClienteDao clienteDao = new ClienteDao(entityManager);

        ClienteId clienteId1 = new ClienteId("felipecosta1@hotmail.com","111111111111");
        Cliente cliente1 = clienteDao.consultarPorId(clienteId1);

        ClienteId clienteId2 = new ClienteId("anacosta_gata@outlook.com","222222222222");
        Cliente cliente2 = clienteDao.consultarPorId(clienteId2);



        CardapioDao cardapioDao = new CardapioDao(entityManager);
        Cardapio moqueca = cardapioDao.consultarPorId(1);
        Cardapio spaguetti = cardapioDao.consultarPorId(2);

        Ordem ordem1 = new Ordem(cliente1);
        ordem1.addOrdensCardapio(new OrdensCardapio(moqueca, 2));
        ordem1.addOrdensCardapio(new OrdensCardapio(spaguetti, 1));

        Ordem ordem2 = new Ordem(cliente2);
        ordem2.addOrdensCardapio(new OrdensCardapio(spaguetti, 3));
        ordem2.addOrdensCardapio(new OrdensCardapio(moqueca, 1));

        OrdemDao ordemDao = new OrdemDao(entityManager);
        ordemDao.cadastrar(ordem1);
        ordemDao.cadastrar(ordem2);

        entityManager.flush();
        entityManager.clear();

        System.out.println("Ordens cadastradas com sucesso!");

    }

}
