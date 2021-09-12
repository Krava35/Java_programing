package util;

import XML.Routes;
import exceptions.NoAccessToFileException;
import data.Route;

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

    private FileInputStream getFileInputStream() throws FileNotFoundException, NoAccessToFileException {
        File file = new File(System.getenv(envVariable));
        if (file.exists() && !file.canRead()) throw new NoAccessToFileException();
        return new FileInputStream(file);
    }

    private FileOutputStream getFileOutputStream() throws IOException, NoAccessToFileException {
        File file = new File(System.getenv(envVariable));
        if (file.exists() && !file.canWrite()) throw new NoAccessToFileException();
        return new FileOutputStream(file);
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
                marshaller.marshal(result, getFileOutputStream());

                Console.println("Collection successfully saved to file!");
            } catch (JAXBException | IOException e) {
                Console.printError("Error saving to file!");
            } catch (NoAccessToFileException e) {
                Console.printError("File access denied!");
            }
        } else Console.printError("System variable with boot file not found!");
    }

    /**
     * Reads collection from a file.
     * @return Collection.
     */
    public ArrayList<Route> readCollection() {
        if (System.getenv().get(envVariable) != null) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Routes.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                Routes routes = (Routes) unmarshaller.unmarshal(getFileInputStream());
                ArrayList<Route> collection = routes.getRoutes();
                if (collection == null) throw new NoSuchElementException();
                Console.println("Collection loaded successfully!");
                return collection;
            } catch (NoSuchElementException exception) {
                Console.printError("Your collection is empty");
                ArrayList<Route> collection = new ArrayList<>();
                Console.println("New collection created!");
                return collection;
            } catch (NullPointerException exception) {
                Console.printError("Required collection not found in boot file!");
            } catch (IllegalStateException exception) {
                Console.printError("Unexpected error!");
            } catch (UnmarshalException e){
                Console.printError("You file is empty");
                ArrayList<Route> collection = new ArrayList<>();
                Console.println("New collection created!");
                return collection;
            } catch (JAXBException e) {
                Console.printError("Error reading XML file");
            } catch (FileNotFoundException e) {
                Console.printError("File not found");
            } catch (NoAccessToFileException e) {
                Console.printError("File access denied!");
            }
        } else Console.printError("System variable with boot file not found!");
        Console.println("Check your environment variable " + envVariable + " and start again.");
        System.exit(0);
        return new ArrayList<>();
    }
}
