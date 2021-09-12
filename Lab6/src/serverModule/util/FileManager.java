package serverModule.util;

import common.data.Route;
import common.exceptions.NoAccessToFileException;
import common.xml.Routes;

import javax.xml.bind.*;
import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Operates the file for saving/loading collection.
 */
public class FileManager {
    private final String envVariable;

    public FileManager(String envVariable) {
        this.envVariable = envVariable;
    }

    private InputStreamReader getInputStreamReader() throws FileNotFoundException, NoAccessToFileException {
        File file = new File(System.getenv(envVariable));
        if (file.exists() && !file.canRead()) throw new NoAccessToFileException();
        return new InputStreamReader(new FileInputStream(file));
    }

    private BufferedWriter getBufferedWriter() throws IOException, NoAccessToFileException {
        File file = new File(System.getenv(envVariable));
        if (file.exists() && !file.canWrite()) throw new NoAccessToFileException();
        return new BufferedWriter(new FileWriter(new File(System.getenv(envVariable))));
    }

    /**
     * Writes collection to a file.
     * @param collection Collection to write.
     */
    public void writeCollection(ArrayList<Route> collection) {
        if (System.getenv().get(envVariable) != null) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Routes.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                Routes result = new Routes();
                result.setRoutes(collection);
                marshaller.marshal(result, getBufferedWriter());

                System.out.println("Collection successfully saved to file!");
            } catch (JAXBException | IOException e) {
                System.out.println("Error saving to file!");
            } catch (NoAccessToFileException e) {
                System.out.println("File access denied!");
            }
        } else System.out.println("System variable with boot file not found!");
    }

    /**
     * Reads collection from a file.
     * @return Readed collection.
     */
    public ArrayList<Route> readCollection() {
        if (System.getenv().get(envVariable) != null) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Routes.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                unmarshaller.unmarshal(getInputStreamReader());
                Routes routes = (Routes) unmarshaller.unmarshal(new File(System.getenv(envVariable)));
                ArrayList<Route> collection = routes.getRoutes();
                ResponseOutputer.append("Collection loaded successfully!\n");
                return collection;
            } catch (NoSuchElementException exception) {
                ResponseOutputer.append("Your collection is empty\n");
                ArrayList<Route> collection = new ArrayList<>();
                ResponseOutputer.append("New collection created!\n");
            } catch (NullPointerException exception) {
                ResponseOutputer.append("Required collection not found in boot file!\n");
            } catch (IllegalStateException exception) {
                ResponseOutputer.append("Unexpected error!\n");
            } catch (UnmarshalException e){
                ResponseOutputer.append("You file is empty");
                ArrayList<Route> collection = new ArrayList<>();
                ResponseOutputer.append("New collection created!");
                return collection;
            } catch (JAXBException e) {
                ResponseOutputer.append("Error reading XML file\n");
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                ResponseOutputer.append("File not found!\n");
            } catch (NoAccessToFileException e) {
                ResponseOutputer.append("НFile access denied!\n");
            }
        } else ResponseOutputer.append("System variable with boot file not found!\n");
        ResponseOutputer.append("Check your environment variable " + envVariable + " and start again.\n");
        System.exit(0);
        return new ArrayList<>();

    }
}


