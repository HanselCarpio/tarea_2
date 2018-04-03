package File;

import Domain.Vehicule;
import java.io.*;

public class RamdonAccessFile {

    private static RandomAccessFile myFile;
    private static int regSize;
    private static int regQuantity = 200;

    public static void start(File file) throws IOException {
        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + " no es un archivo");
        }
        myFile = new RandomAccessFile(file, "rw");
        regSize = (int) Math.ceil(
                (double) myFile.length() / (double) regQuantity);
    }

    public static void close() throws IOException {
        myFile.close();
    }

    public static boolean setVehicule(int i, Vehicule vehicule) throws IOException {
        if(i >= 0 && i <= getNumberRegs()) {
            if(vehicule.getSize() > regQuantity) {
                System.out.println("\nTamaño de registro excedido.");
            } else {
                myFile.seek(i*regQuantity);
                myFile.writeUTF(vehicule.getNameCar());
                myFile.writeInt(vehicule.getAnnoCar());
                myFile.writeFloat(vehicule.getKmCar());
                myFile.writeUTF(vehicule.getCountryCar());
                myFile.writeInt(vehicule.getSerieCar());
                myFile.writeBoolean(vehicule.isActive());
                return true;
            }
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
        }
        return false;
    }

    private static int searchInactiveReg() throws IOException {
        String name;
        for(int i=0; i<getNumberRegs(); i++) 
        {
            myFile.seek(i * regQuantity);
            if(!getVehicule(i).isActive()) 
                return i;
        }
        return -1;        
    }
    
    public static boolean deletedVehicule(int toDelete) throws IOException {
        int pos = regSearch(toDelete);
        if(pos == -1) return false;
        Vehicule vehicule = getVehicule(pos);
        vehicule.setActive(false);
        setVehicule(pos, vehicule);
        return true;
    }
    
    public static void compactArchive(File file) throws IOException {
        start(file); // Abrimos el flujo.
        Vehicule[] vs = new Vehicule[regSize];
        for(int i=0; i<regSize; ++i)
            vs[i] = getVehicule(i);
        close(); // Cerramos el flujo.
        file.delete(); // Borramos el archivo.

        File tempo = new File("temporal.dad");
        start(tempo); // Como no existe se crea.
        for(Vehicule vehicule : vs)
            if(vehicule.isActive())
                addVehicule(vehicule);
        close();
        
        tempo.renameTo(file); // Renombramos.
    }
    
    public static void addVehicule(Vehicule  vehicule) throws IOException {
        int inactivo = searchInactiveReg();
        if(setVehicule(inactivo==-1?regSize:inactivo,  vehicule)) 
            regSize++;        
    }

    public static int getNumberRegs() {
        return regSize;
    }

    public static Vehicule getVehicule(int i) throws IOException {
        if(i >= 0 && i <= getNumberRegs()) {
            myFile.seek(i * regQuantity);
            return new Vehicule(myFile.readUTF(), myFile.readInt(),myFile.readFloat(), myFile.readUTF(), myFile.readInt(), myFile.readBoolean());
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
            return null;
        }
    }

    public static int regSearch(int buscado) throws IOException {
        Vehicule vehicule;
//        if (buscado ==null) {
//            return -1;
//        }
        for(int i=0; i<getNumberRegs(); i++) {
            myFile.seek(i * regQuantity);
            vehicule = getVehicule(i);
            if(vehicule.getSerieCar()==(buscado) && vehicule.isActive()) {
                return i;
            }
        }
        return -1;
    }
    
}