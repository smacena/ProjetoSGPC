package sgpc.servicos;


/**
 * Serviço de verificação de itens contra o sistema de informações mantido pela
 * aplicação.
 *
 */
public abstract class ServicoVerificador extends Servico {
  
  /**
   * Método utilizado para verificar a existência de um registro no sistema de
   * informações da aplicação.
   * 
   * @return <code>true</code> caso o registro exista.
   *         <code>false</code> caso contrário.
   */
  public abstract boolean executar();
}
