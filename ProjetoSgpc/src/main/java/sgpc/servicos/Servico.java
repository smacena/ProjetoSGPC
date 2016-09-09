package sgpc.servicos;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Representação mais genérica de um serviço da aplicação. 
 * 
 */
public abstract class Servico {
  
   static final String TIPO_EIS = "TIPO_EIS";

  /** Encapsula um mapa das propriedades gerais do sistema. */
  protected static Properties propriedades;

  public Servico() {
    if (propriedades == null) {
      propriedades = new Properties();
      try {
        propriedades.load(Servico.class.getResourceAsStream("settings.properties"));
      } catch (IOException ex) {
        Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
