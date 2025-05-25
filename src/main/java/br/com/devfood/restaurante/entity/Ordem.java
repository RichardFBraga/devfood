package br.com.devfood.restaurante.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordens")

public class Ordem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Column(name = "Data_de_criação")
    private LocalDateTime dataDeCriaca = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    /*
    *ALL = Realiza todas as operações em cascata
    * DETACH = Operação detach executada no pai e no filho
    * MERGE = Salva pai e filho, podendo já haver entidade gerenciada
    * PERSIST = Cria pai e filho
    * REFRESH = Atualiza entidade com operações dp banco
    * REMOVE = Propaga remoção entre pai e filho
    * */


    @OneToMany(mappedBy = "ordem", cascade = CascadeType.ALL)
    private List<OrdensCardapio> ordensCardapioList = new ArrayList<>();

//    @JoinTable(
//            name = "ordens_cardapio",
//            joinColumns = @JoinColumn(name = "ordens_id"),
//            inverseJoinColumns = @JoinColumn(name = "cardapio_id")
//    )

    public Ordem(Cliente cliente) {
        this.cliente = cliente;
    }

    public Ordem(){

    }
    public void  addOrdensCardapio(OrdensCardapio ordensCardapio){
        ordensCardapio.setOrdem(this);
        this.ordensCardapioList.add(ordensCardapio);
        this.valorTotal = valorTotal.add(ordensCardapio.getValorDeRegistro()
                .multiply(BigDecimal.valueOf(ordensCardapio.getQuantidade())));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataDeCriaca() {
        return dataDeCriaca;
    }

    public void setDataDeCriaca(LocalDateTime dataDeCriaca) {
        this.dataDeCriaca = dataDeCriaca;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<OrdensCardapio> getOrdensCardapioList() {
        return ordensCardapioList;
    }

    public void setOrdensCardapioList(List<OrdensCardapio> ordensCardapioList) {
        this.ordensCardapioList = ordensCardapioList;
    }

    @Override
    public String toString() {
        return "Ordem{" +
                "id=" + id +
                ", valorTotal=" + valorTotal +
                ", dataDeCriaca=" + dataDeCriaca +
                ", cliente=" + cliente +
                ", ordensCardapioList=" + ordensCardapioList +
                '}';
    }
}
