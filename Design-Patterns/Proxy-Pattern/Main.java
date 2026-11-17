
interface Document {
    void display();
}

class RealDocument implements Document {
    public final String fileName;

    public RealDocument(String fileName) {
        this.fileName = fileName;
        this.loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading document from Disk: " + this.fileName);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        System.out.println("Document loaded successfully...");
    }

    @Override
    public void display() {
        System.out.println("Displaying document: " + this.fileName);
    }
}

class DocumentProxy implements Document {
    private final String fileName;
    private final String userRole;

    private Document realDocument;

    public DocumentProxy(String fileName, String userRole) {
        this.fileName = fileName;
        this.userRole = userRole;
    }

    @Override
    public void display() {
        if (!"ADMIN".equals(this.userRole)) {
            System.out.println("Access denied! Only admins can view documents.");
            return;
        }

        if (this.realDocument == null) this.realDocument = new RealDocument(this.fileName);

        this.realDocument.display();
    }

}

public class Main {
    public static void main(String[] args) {
        
        Document d1 = new DocumentProxy("confidential.txt", "USER");

        d1.display();

        System.out.println("\n------------------------------------------------\n");

        Document d2 = new DocumentProxy("confidential.txt", "ADMIN");

        d2.display();

        System.out.println("\nAgain loading document but from cache:\n");

        d2.display();

    }
}


