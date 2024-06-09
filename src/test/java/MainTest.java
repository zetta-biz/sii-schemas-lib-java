import cl.sii.siidte.MedioPagoType;
import cl.sii.siidte.TipMonType;
import org.junit.jupiter.api.Test;
import cl.sii.siidte.DTEDefType.Documento.Encabezado.IdDoc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class MainTest {
    @Test
    void document() {
        IdDoc idDoc = new IdDoc();
        assertInstanceOf(IdDoc.class, idDoc);

        idDoc.setMedioPago(MedioPagoType.CH);
        assertEquals(idDoc.getMedioPago().value(), "CH");

        System.out.println(idDoc.toString());
    }

    @Test
    void setMedioPago() {
        IdDoc idDoc = new IdDoc();
        idDoc.setMedioPago(MedioPagoType.CH);
        assertEquals(idDoc.getMedioPago().value(), "CH");
    }

    @Test
    void tipMonType() {
        TipMonType tipMonType = TipMonType.PESO_CL;
        assertInstanceOf(TipMonType.class, tipMonType);
    }
}
