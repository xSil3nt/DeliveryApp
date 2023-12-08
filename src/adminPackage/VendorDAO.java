package adminPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VendorDAO {
    private static final String FILE_PATH = ("programData\\vendorCreds.txt");

    public void addVendor(Vendor vendor) throws IOException {
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(vendor.getUsername() + ";" + vendor.getPassword() + ";" + vendor.getPhoneNumber());
        }
    }

    public List<Vendor> getAllVendors() throws IOException {
        File file = new File(FILE_PATH);
        List<Vendor> vendors = new ArrayList<>();

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Vendor vendor = parseVendor(line);
                    if (vendor != null) {
                        vendors.add(vendor);
                    }
                }
            }
        }
        return vendors;
    }

    public void updateVendor(Vendor vendorToUpdate) throws IOException {
        List<Vendor> vendors = getAllVendors();
        for (int i = 0; i < vendors.size(); i++) {
            Vendor vendor = vendors.get(i);
            if (vendor.getUsername().equals(vendorToUpdate.getUsername())) {
                vendors.set(i, vendorToUpdate);
                break;
            }
        }
        saveAllVendors(vendors);
    }

    public void deleteVendor(String username) throws IOException {
        List<Vendor> vendors = getAllVendors();
        vendors.removeIf(vendor -> vendor.getUsername().equals(username));
        saveAllVendors(vendors);
    }

    private void saveAllVendors(List<Vendor> vendors) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Vendor vendor : vendors) {
                out.println(vendor.getUsername() + ";" + vendor.getPassword() + ";" + vendor.getPhoneNumber());
            }
        }
    }

    private Vendor parseVendor(String line) {
        String[] parts = line.split(";");
        if (parts.length < 3) return null; 
        return new Vendor(parts[0], parts[1], parts[2]); 
    }
}
