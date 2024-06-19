/**
 * Copyright (C) 2024 Jorge Arias Leal
 * SPDX-License-Identifier: LGPL-3.0-only OR Zetta-Commercial
 */

import cl.sii.siidte.DTEDefType;
import cl.sii.siidte.EnvioDTE;
import cl.sii.siidte.MedioPagoType;
import cl.sii.siidte.ObjectFactory;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class EnvioDTETest {
    private final ObjectFactory objectFactory = new ObjectFactory();
    private final EnvioDTE envioDTE = objectFactory.createEnvioDTE();
    private final Faker faker = new Faker();

    @Test
    void createEnvioDTE() throws JAXBException {
        DTEDefType dteDefType = objectFactory.createDTEDefType();
        dteDefType.setDocumento(objectFactory.createDTEDefTypeDocumento());
        DTEDefType.Documento documento = dteDefType.getDocumento();
        documento.setEncabezado(objectFactory.createDTEDefTypeDocumentoEncabezado());
        DTEDefType.Documento.Encabezado encabezado = documento.getEncabezado();
        encabezado.setIdDoc(objectFactory.createDTEDefTypeDocumentoEncabezadoIdDoc());
        DTEDefType.Documento.Encabezado.IdDoc idDoc = encabezado.getIdDoc();

        idDoc.setMedioPago(MedioPagoType.CH);
        idDoc.setFmaPago(new BigInteger("1"));

        DTEDefType.Documento.Detalle item1 = objectFactory.createDTEDefTypeDocumentoDetalle();
        item1.setNroLinDet(0);
        DTEDefType.Documento.Detalle.CdgItem cdg1 = objectFactory.createDTEDefTypeDocumentoDetalleCdgItem();
        cdg1.setTpoCodigo("EAN");
        cdg1.setVlrCodigo(faker.code().ean13());
        item1.getCdgItem().add(cdg1);
        item1.setNmbItem(faker.commerce().productName());

        documento.getDetalle().add(item1);

        envioDTE.setSetDTE(objectFactory.createEnvioDTESetDTE());
        EnvioDTE.SetDTE setDTE = envioDTE.getSetDTE();

        // Add Document to SetDTE
        setDTE.getDTE().add(dteDefType);

        StringWriter writer = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
        jaxbMarshaller.marshal(envioDTE, writer);

        StringReader reader = new StringReader(writer.toString());
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        jaxbUnmarshaller.setEventHandler(new jakarta.xml.bind.helpers.DefaultValidationEventHandler());
        jaxbUnmarshaller.unmarshal(reader);
    }
}
