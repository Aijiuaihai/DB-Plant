public class DAOFactory {
    private static DAOFactory daoFactory;
    static {
        daoFactory = new DAOFactory();
    }
    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        return daoFactory;
    }
    public userDAO getuserDAO(){
        return new userDAOim();
    }
    public plantsDAO getplantsDAO(){
        return new plantsDAOim();
    }
}
