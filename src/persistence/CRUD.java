// Servidor

package persistence;

import java.util.List;
import models.Manipulation;

public interface CRUD {
    void include(Manipulation object) throws Exception;
    List<Manipulation> read() throws Exception;
    void update(Manipulation object) throws Exception;
}
