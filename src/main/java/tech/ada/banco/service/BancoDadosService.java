package tech.ada.banco.service;

public  class BancoDadosService {
    /*

    private static final List<Conta> contaList = new ArrayList<>();

    private static final List<Cliente> clienteList = new ArrayList<>();

    public static void incluirConta(Conta conta){
        contaList.add(conta);
    }

    public static void incluirCliente(Cliente cliente){
        clienteList.add(cliente);
    }

    public static ClientePF getClientePF(String cpf){
        Optional<Cliente> clienteOptional = clienteList.stream().filter(cliente -> ((ClientePF) cliente).getCpf().equals(cpf)).findFirst();
        if(clienteOptional.isEmpty()){
            return null;
        }else {
            return (ClientePF) clienteOptional.get();
        }
    }

    public static Integer getNumeroConta(){
        return contaList.size()+1;
    }

    public static ContaInvestimento verificarExistenciaContaInvestimento(Cliente cliente){
        return null;//(ContaInvestimento) cliente.getContaList().stream().filter(conta -> conta instanceof ContaInvestimento).findFirst().orElse(null);
    }

    public static Conta getContaByNumero(Integer numeroConta){
        Optional<Conta> contaOrigem = contaList.stream().filter(conta -> conta.getNumero().equals(numeroConta)).findFirst();
        if(contaOrigem.isEmpty()){
            return null;
        }
        return contaOrigem.get();
    }

    public static Conta getContaByCPF(String cpf){
        Optional<Conta> contaRetorno = contaList.stream().filter(conta -> ((ClientePF) conta.getCliente()).getCpf().equals(cpf)).findFirst();
        if(contaRetorno.isEmpty()){
            return null;
        }
        return contaRetorno.get();
    }

    public static Conta getContaByCNPJ(String cpnj){
        Optional<Conta> contaRetorno = contaList.stream().filter(conta -> ((ClientePJ) conta.getCliente()).getCnpj().equals(cpnj)).findFirst();
        if(contaRetorno.isEmpty()){
            return null;
        }
        return contaRetorno.get();
    }

    public static Conta getContaByCliente(Cliente cliente){
        Optional<Conta> contaRetorno = contaList.stream().filter(conta -> conta.getCliente().equals(cliente)).findFirst();
        if(contaRetorno.isEmpty()){
            return null;
        }
        return contaRetorno.get();
    }

     */
}
