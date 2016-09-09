package sgpc.servicos;

/**
 * Representa��o gen�rica de cadastro de dados na aplica��o.
 *
 */
public abstract class ServicoCadastro<T> extends Servico {
  
  protected T entidade;
  
  /**
   * Instancia��o de um servi�o de cadastro.
   * 
   * @param entidade Registro a ser cadastrado no sistema de informa��es da aplica��o.
   */
  public ServicoCadastro(T entidade) {
    this.entidade = entidade;
  }
  
  /** M�todo utilizado para cadastrar um novo registro na aplica��o. */
  public abstract boolean cadastrar();
  public abstract boolean alterar();
  public abstract boolean excluir();
}
