package sgpc.servicos;

import java.util.List;

/**
 * Contrato que estabelece o comportamento de servi�os utilizados para importar
 * informa��o do banco de dados da aplica��o.
 * 
 */
public abstract class ServicoCarga<T> extends Servico {

/**
   * Carrega dados do sistema de informa��es da aplica��o.
   * 
   * @return Os dados desejados.
   */
  public abstract List<T> carregarDados(String ... criterios);  
  
  public abstract List<T> carregarDados();  
}

