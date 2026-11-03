package tp;

public class ServiceXy {

    static void main() {
        DaoXy daoXy = new DaoXy();
        ServiceXy serviceXy = new ServiceXy(daoXy);
        serviceXy.useXyData();
    }

    private DaoXy daoXy;

    public ServiceXy(DaoXy daoXy) {
        this.daoXy = daoXy;
    }

    public void useXyData() {
        String data = daoXy.getData();
        System.out.println("data used in ServiceXy: " + data);
    }
}
