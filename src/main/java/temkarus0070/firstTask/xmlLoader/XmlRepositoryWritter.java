package temkarus0070.firstTask.xmlLoader;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import temkarus0070.firstTask.repository.ContractRepositoryImpl;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class XmlRepositoryWritter {
    public void write(String file, ContractRepositoryImpl contractRepository) {
        try (OutputStream outputStream = new FileOutputStream(file)) {

            JAXBContext jaxbContext = JAXBContext.newInstance(ContractRepositoryImpl.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(contractRepository, outputStream);
            outputStream.flush();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
