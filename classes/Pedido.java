package classes;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Cliente cliente;
    private List<Prato> listaDePedidos;
    private double conta;
    
    public Pedido(Cliente cliente){
        this.cliente = cliente;
        this.listaDePedidos = new ArrayList<>();
        this.conta = 0;
    }

    public void addPedido(Prato prato){    
        this.listaDePedidos.add(prato);
        this.conta += prato.getPreco();
        prato.removerEstoque();
    }

    public Cliente getCliente(){ return this.cliente; }
    public double getConta(){ return this.conta; }

    public String toString(){

        StringBuilder nota = new StringBuilder();

        nota.append("==============================\n");
        nota.append("       DETALHES DO PEDIDO     \n");
        nota.append("==============================\n");
        nota.append("Cliente: " + this.cliente.getNome() + "\n");
        nota.append("------------------------------\n");

        if (this.listaDePedidos.isEmpty()){
            nota.append("(Nenhum item adicionado)\n");
        }

        else{
            for(Prato p : this.listaDePedidos){
                nota.append(String.format("%-20s R$ %.2f\n", p.getNome(), p.getPreco()));
            }

            nota.append(String.format("%-20s R$ %.2f\n", "Total:", getConta()));
        }

        return nota.toString();
    }
}
