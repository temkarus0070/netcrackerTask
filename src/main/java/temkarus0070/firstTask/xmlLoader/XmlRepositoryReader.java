package temkarus0070.firstTask.xmlLoader;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import temkarus0070.firstTask.repository.ContractRepositoryImpl;

import java.io.FileInputStream;

public class XmlRepositoryReader {
    public ContractRepositoryImpl read(String file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            JAXBContext context = JAXBContext.newInstance(ContractRepositoryImpl.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (ContractRepositoryImpl) unmarshaller.unmarshal(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
