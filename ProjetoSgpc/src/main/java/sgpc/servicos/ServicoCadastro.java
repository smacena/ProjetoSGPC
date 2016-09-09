package sgpc.servicos;

/**
 * Representação genérica de cadastro de dados na aplicação.
 *
 */
public abstract class ServicoCadastro<T> extends Servico {
  
  protected T entidade;
  
  /**
   * Instanciação de um serviço de cadastro.
   * 
   * @param entidade Registro a ser cadastrado no sistema de informações da aplicação.
   */
  public ServicoCadastro(T entidade) {
    this.entidade = entidade;
  }
  
  /** Método utilizado para cadastrar um novo registro na aplicação. */
  public abstract boolean cadastrar();
  public abstract boolean alterar();
  public abstract boolean excluir();
}
