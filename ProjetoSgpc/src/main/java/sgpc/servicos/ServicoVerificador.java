package sgpc.servicos;


/**
 * Servi�o de verifica��o de itens contra o sistema de informa��es mantido pela
 * aplica��o.
 *
 */
public abstract class ServicoVerificador extends Servico {
  
  /**
   * M�todo utilizado para verificar a exist�ncia de um registro no sistema de
   * informa��es da aplica��o.
   * 
   * @return <code>true</code> caso o registro exista.
   *         <code>false</code> caso contr�rio.
   */
  public abstract boolean executar();
}
