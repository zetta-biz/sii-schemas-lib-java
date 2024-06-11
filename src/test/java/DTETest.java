import cl.sii.siidte.DTEDefType;
import cl.sii.siidte.ObjectFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DTETest {
    private final ObjectFactory objectFactory = new ObjectFactory();
    private final DTEDefType dteDefType = objectFactory.createDTEDefType();
    private final DTEDefType.Documento documento = objectFactory.createDTEDefTypeDocumento();
    private final DTEDefType.Documento.Encabezado encabezado = objectFactory.createDTEDefTypeDocumentoEncabezado();
    private final DTEDefType.Documento.Encabezado.IdDoc idDoc = objectFactory.createDTEDefTypeDocumentoEncabezadoIdDoc();

    @Test
    void setDocumento() {
        dteDefType.setDocumento(documento);
        assertAll("documento",
                () -> assertNotNull(dteDefType.getDocumento()),
                () -> assertInstanceOf(DTEDefType.Documento.class, dteDefType.getDocumento())
        );
    }

    @Test
    void setEncabezado() {
        documento.setEncabezado(encabezado);
        assertAll("encabezado",
                () -> assertNotNull(documento.getEncabezado()),
                () -> assertInstanceOf(DTEDefType.Documento.Encabezado.class, documento.getEncabezado())
        );
    }

    @Test
    void setIdDoc() {
        encabezado.setIdDoc(idDoc);
        assertAll("idDoc",
                () -> assertNotNull(encabezado.getIdDoc()),
                () -> assertInstanceOf(DTEDefType.Documento.Encabezado.IdDoc.class, encabezado.getIdDoc())
        );
    }
}
