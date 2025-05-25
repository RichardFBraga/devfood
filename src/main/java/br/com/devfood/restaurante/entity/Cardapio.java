package br.com.devfood.restaurante.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cardapio")

public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String nome;

    private String descricao;

    private Boolean disponivel;

    private BigDecimal valor;

    /*
    * ManyToOne - relacionamento de muitos para um
    * ManyToMany - muitos para muitos
    * OneToMany - um para muitos
    * OneToOne - um para um
    * */

    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

//    @ManyToMany(mappedBy = "cardapioList")

 //  private List<Ordem> ordemList;

    @Column(name = "data_de_registro")
    private LocalDateTime dataDeRegistro = LocalDateTime.now();

    public Cardapio(String nome, String descricao, Boolean disponivel, BigDecimal valor, Categoria categoria, LocalDateTime dataDeRegistro) {
        this.nome = nome;
        this.descricao = descricao;
        this.disponivel = disponivel;
        this.valor = valor;
        this.categoria = categoria;
        this.dataDeRegistro = dataDeRegistro;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Cardapio() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataDeRegistro() {
        return dataDeRegistro;
    }

    public void setDataDeRegistro(LocalDateTime dataDeRegistro) {
        this.dataDeRegistro = dataDeRegistro;
    }

    @Override
    public String toString() {
        return "Prato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", disponivel=" + disponivel +
                ", valor=" + valor +
                ", categoria=" + (categoria != null ? categoria.getNome() : "sem categoria") +
                ", dataDeRegistro=" + dataDeRegistro +
                '}';
    }
}
