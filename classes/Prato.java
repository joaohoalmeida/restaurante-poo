package classes;

public class Prato {

    private String nome;
    private double preco;
    private int quantidade;

    public Prato(String nome, double preco, int quantidade){
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome(){ return this.nome; }
    public double getPreco(){ return this.preco; }
    public int getQntd(){ return this.quantidade; }
    
    void setNome(String newNome){ this.nome = newNome; }
    void setPreco(double newPreco){ this.preco = newPreco; }
    void setQntd(int newQuantidade){ this.quantidade = newQuantidade; }

    public void removerEstoque(){
        this.quantidade -= 1;
    }
}
