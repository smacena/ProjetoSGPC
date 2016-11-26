package sgpc.servicos;

import java.util.List;

/**
 * Contrato que estabelece o comportamento de serviços utilizados para importar
 * informação do banco de dados da aplicação.
 * 
 */
public abstract class ServicoCarga<T> extends Servico {

/**
   * Carrega dados do sistema de informações da aplicação.
   * 
   * @return Os dados desejados.
   */
  public abstract List<T> carregarDados(String ... criterios);  
  
  public abstract List<T> carregarDados();  
}

